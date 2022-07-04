package com.zakiis.spring.test.service.checker;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.pool.ValidConnectionChecker;
import com.alibaba.druid.pool.ValidConnectionCheckerAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.druid.util.JdbcUtils;
import com.alibaba.druid.util.Utils;
import com.mysql.cj.MysqlConnection;
import com.mysql.cj.NativeSession;
import com.mysql.cj.protocol.a.NativeProtocol;

public class MySqlValidConnectionChecker extends ValidConnectionCheckerAdapter implements ValidConnectionChecker, Serializable {
    public static final int DEFAULT_VALIDATION_QUERY_TIMEOUT = 1;
    public static final String DEFAULT_VALIDATION_QUERY = "SELECT 1";

    private static final long serialVersionUID = 1L;
    private static final Log LOG = LogFactory.getLog(MySqlValidConnectionChecker.class);

    private Class<?> clazz;
    private Method ping;
    private boolean usePingMethod;
    
    public MySqlValidConnectionChecker() {
        this(false);
    }

    public MySqlValidConnectionChecker(boolean usePingMethod) {
        try {
            clazz = Utils.loadClass("com.mysql.jdbc.MySQLConnection");
            if (clazz == null) {
                clazz = Utils.loadClass("com.mysql.cj.jdbc.ConnectionImpl");
            }

            if (clazz != null) {
                ping = clazz.getMethod("pingInternal", boolean.class, int.class);
            }

            if (ping != null && usePingMethod == true) {
                this.usePingMethod = true;
            }
        } catch (Exception e) {
            LOG.warn("Cannot resolve com.mysql.jdbc.Connection.ping method.  Will use 'SELECT 1' instead.", e);
        }

        configFromProperties(System.getProperties());
    }

    @Override
    public void configFromProperties(Properties properties) {
        if (properties == null) {
            return;
        }

        String property = properties.getProperty("druid.mysql.usePingMethod");
        if ("true".equals(property)) {
            setUsePingMethod(true);
        } else if ("false".equals(property)) {
            setUsePingMethod(false);
        }
    }

    public boolean isUsePingMethod() {
        return usePingMethod;
    }

    public void setUsePingMethod(boolean usePingMethod) {
        this.usePingMethod = usePingMethod;
    }

    public boolean isValidConnection(Connection conn,
                                     String validateQuery,
                                     int validationQueryTimeout) throws Exception {
        if (conn.isClosed()) {
            return false;
        }
        if (validationQueryTimeout <= 0) {
            validationQueryTimeout = DEFAULT_VALIDATION_QUERY_TIMEOUT;
        }
        if (usePingMethod) {
            if (conn instanceof DruidPooledConnection) {
                conn = ((DruidPooledConnection) conn).getConnection();
            }

            if (conn instanceof ConnectionProxy) {
                conn = ((ConnectionProxy) conn).getRawObject();
            }

            if (clazz.isAssignableFrom(conn.getClass())) {
                try {
                    ping.invoke(conn, true, validationQueryTimeout * 1000);
                } catch (InvocationTargetException e) {
                    Throwable cause = e.getCause();
                    if (cause instanceof SQLException) {
                        throw (SQLException) cause;
                    }
                    throw e;
                }
                return true;
            }
        }

        String query = validateQuery;
        if (validateQuery == null || validateQuery.isEmpty()) {
            query = DEFAULT_VALIDATION_QUERY;
        }

        Statement stmt = null;
        ResultSet rs = null;
        NativeProtocol protocol = null;
        int oldTimeout = -1;
        Connection druidConn = conn;
        try {
        	if (conn instanceof DruidPooledConnection) {
                conn = ((DruidPooledConnection) conn).getConnection();
            }

            if (conn instanceof ConnectionProxy) {
                conn = ((ConnectionProxy) conn).getRawObject();
            }
            if (conn instanceof MysqlConnection) {
            	MysqlConnection mysqlConn = (MysqlConnection)conn;
            	NativeSession session = (NativeSession)mysqlConn.getSession();
            	protocol = session.getProtocol();
        		oldTimeout = protocol.getSocketConnection().getMysqlSocket().getSoTimeout();
        		protocol.getSocketConnection().getMysqlSocket().setSoTimeout(validationQueryTimeout * 1000);
            }
            stmt = druidConn.createStatement();
            if (oldTimeout != -1) {
                stmt.setQueryTimeout(validationQueryTimeout);
            }
            rs = stmt.executeQuery(query);
            return true;
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
            if (oldTimeout != -1 && protocol.getSocketConnection().getMysqlSocket() != null) {
            	protocol.getSocketConnection().getMysqlSocket().setSoTimeout(oldTimeout);
            }
        }

    }

}
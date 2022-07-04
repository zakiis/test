package com.zakiis.spring.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

public class DruidTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException, Exception {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUsername("");
		druidDataSource.setPassword("");
		druidDataSource.setUrl("jdbc:mysql://root:123456@192.168.137.105:3306/zakiis?serverTimezone=GMT%2b8&useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&allowMultiQueries=true");
		druidDataSource.setValidationQuery("select 1");
		druidDataSource.setMinEvictableIdleTimeMillis(100000);
		druidDataSource.setMaxEvictableIdleTimeMillis(2520000);
		druidDataSource.setKeepAliveBetweenTimeMillis(120000);
		druidDataSource.setMinIdle(2);
		druidDataSource.setMaxActive(2);
		druidDataSource.setValidationQueryTimeout(5);
		druidDataSource.setTimeBetweenEvictionRunsMillis(70000);
		druidDataSource.setKeepAlive(true);
		druidDataSource.init();
		DruidPooledConnection connection1 = druidDataSource.getConnection();
		DruidPooledConnection connection2 = druidDataSource.getConnection();
		System.out.println("conn1 id: " + connection1.getConnectionHolder().getConnectionId() + ", conn2 id:" + connection2.getConnectionHolder().getConnectionId());
		System.out.println(connection1.getConnectionHolder() == connection2.getConnectionHolder());
		connection2.close();
		Thread.sleep(95000);
		connection1.close();
		Thread.sleep(140000);
		DruidPooledConnection connection3 = druidDataSource.getConnection();
		DruidPooledConnection connection4 = druidDataSource.getConnection();
		System.out.println(connection3.getConnectionHolder() == connection4.getConnectionHolder());
		System.out.println("conn3 id: " + connection3.getConnectionHolder().getConnectionId() + ", conn4 id:" + connection4.getConnectionHolder().getConnectionId());
		connection3.close();
		connection4.close();
	}
}

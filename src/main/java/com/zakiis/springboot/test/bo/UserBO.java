package com.zakiis.springboot.test.bo;

import com.zakiis.springboot.test.model.Address;
import com.zakiis.springboot.test.model.User;

public class UserBO extends User {

	Address residenceAddress;

	public Address getResidenceAddress() {
		return residenceAddress;
	}

	public void setResidenceAddress(Address residenceAddress) {
		this.residenceAddress = residenceAddress;
	}
	
}

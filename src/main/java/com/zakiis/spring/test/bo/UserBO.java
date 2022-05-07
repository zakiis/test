package com.zakiis.spring.test.bo;

import com.zakiis.spring.test.model.Address;
import com.zakiis.spring.test.model.User;

public class UserBO extends User {

	Address residenceAddress;

	public Address getResidenceAddress() {
		return residenceAddress;
	}

	public void setResidenceAddress(Address residenceAddress) {
		this.residenceAddress = residenceAddress;
	}
	
}

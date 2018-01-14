package com.homefun.services.impl;

import org.springframework.stereotype.Service;

import com.homefun.beans.UserInfo;
import com.homefun.services.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Override
	public UserInfo findByUsername(String username) {
		
		return null;
	}

}

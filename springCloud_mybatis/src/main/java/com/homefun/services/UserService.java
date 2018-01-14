package com.homefun.services;

import com.homefun.beans.UserInfo;

public interface UserService {
	UserInfo findUserInfo(String userName);
	void save(UserInfo userInfo);
}

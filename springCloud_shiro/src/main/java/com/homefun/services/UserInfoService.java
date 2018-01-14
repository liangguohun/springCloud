package com.homefun.services;

import com.homefun.beans.UserInfo;

public interface UserInfoService {

	UserInfo findByUsername(String username);

}

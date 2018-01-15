package com.homefun.servercesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefun.beans.UserInfo;
import com.homefun.dao.UserInfoDao;
import com.homefun.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoDao userInfoDao;
	@Override
	public UserInfo findUserInfo(String userName) {
		return userInfoDao.findByUsername(userName);
	}
	//在方法和类上面都可以  
	@Transactional
	@Override
	public void save(UserInfo userInfo) {
		
	}
	
}

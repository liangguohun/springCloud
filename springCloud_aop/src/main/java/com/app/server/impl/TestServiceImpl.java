package com.app.server.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.app.beans.TestRecord;
import com.app.dao.TestRepos;
import com.app.server.TestService;
import com.app.utils.sys.ApplicationContextProvider;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	TestRepos testRepos;
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	@Transactional(readOnly=false)
	@Override
	public void addData(Integer num) {
		List<TestRecord> recordList = new ArrayList<>();
		String name = "hunge";
		//jpa
//		for(int i=0;i<num; i++) {
//			recordList.add(new TestRecord(name+i));
//		}
//		testRepos.save(recordList);
		
		//hibernate
//		HibernateTemplate hibernateTemplate = ApplicationContextProvider.getBean(HibernateTemplate.class);
//		for(int i=0;i<num; i++) {
//			hibernateTemplate.save(new TestRecord(name+i));
//		}
		
//		HibernateTemplate hibernateTemplate = ApplicationContextProvider.getBean(HibernateTemplate.class);
//		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
//		for(int i=0;i<num; i++) {
//			session.save(new TestRecord(name+i));
//		}
//		session.flush();
//		session.clear();
//		tx.commit();
		
		//jdbc
		try {
			String sql = null;
			Connection connect = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement pstm = connect.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}

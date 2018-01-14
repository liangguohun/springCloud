package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.beans.TestRecord;

@Repository
public interface TestRepos extends JpaRepository<TestRecord, Integer> {

}

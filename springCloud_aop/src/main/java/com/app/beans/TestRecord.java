package com.app.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="record")
public class TestRecord implements Serializable {

	private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private Integer id;
	@Id
//	@GenericGenerator(name = "system-uuid", strategy = "uuid")
//	@GeneratedValue(generator = "system-uuid")
	   @Column(unique = true, length = 36, nullable = false)  
	   @GeneratedValue(generator = "uuid")  
	   @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String id;
	private String name;
	
	public TestRecord() {
		
	}
	public TestRecord(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}

package com.inter.consumer.dao;

public interface ReWriteDao {

	Integer getOrderNumberBySequence(String sequence);

	String queryHomepageAddr(Integer orderNumber);

}

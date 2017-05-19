package com.ai.springboot.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ai.springboot.model.StatusUpdate;

@Repository
public interface StatusUpdateDao extends PagingAndSortingRepository<StatusUpdate, Long> {
	
	StatusUpdate findFirstByOrderByAddedDesc();
	

}

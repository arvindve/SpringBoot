package com.ai.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ai.springboot.dao.StatusUpdateDao;
import com.ai.springboot.model.StatusUpdate;

@Service
public class StatusUpdateService {

	private static final int PAGE_SIZE = 3;

	@Autowired
	private StatusUpdateDao statusUpdateDao;

	public void save(StatusUpdate status) {

		statusUpdateDao.save(status);

	}

	public StatusUpdate getLatest() {
		return statusUpdateDao.findFirstByOrderByAddedDesc();
	}

	public Page<StatusUpdate> getPage(int pageNumber) {

		PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "added");

		return statusUpdateDao.findAll(pageRequest);

	}

	public void delete(Long id) {

		statusUpdateDao.delete(id);

	}

	public StatusUpdate get(Long id) {
		return statusUpdateDao.findOne(id);

	}

}

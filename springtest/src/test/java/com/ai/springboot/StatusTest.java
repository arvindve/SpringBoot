package com.ai.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ai.springboot.dao.StatusUpdateDao;
import com.ai.springboot.model.StatusUpdate;

import static junit.framework.Assert.*;

import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class StatusTest {

	@Autowired
	private StatusUpdateDao statusUpdateDao;

	@Test
	public void testSave() {

		StatusUpdate status = new StatusUpdate("This is a test status update");

		statusUpdateDao.save(status);

		assertNotNull("Non-Null ID", status.getId());
		assertNotNull("Non-Null Date", status.getAdded());

		StatusUpdate retrieved = statusUpdateDao.findOne(status.getId());

		assertEquals("Status Equals", status, retrieved);

	}

	@Test
	public void testLastStatusUpdate() {

		Calendar calender = Calendar.getInstance();

		StatusUpdate lastStatusUpdate = null;

		for (int i = 0; i < 10; i++) {

			calender.add(Calendar.DAY_OF_YEAR, 1);

			StatusUpdate status = new StatusUpdate("Status Update " + i, calender.getTime());
			statusUpdateDao.save(status);

			lastStatusUpdate = status;

		}

		StatusUpdate retrieved = statusUpdateDao.findFirstByOrderByAddedDesc();

		assertEquals("Lastest StatusUpdate", lastStatusUpdate, retrieved);

	}

}

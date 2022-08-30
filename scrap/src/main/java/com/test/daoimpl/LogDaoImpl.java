package com.test.daoimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dao.ILogDao;
import com.test.model.LogObject;
import com.test.repository.LogRepository;

@Repository
public class LogDaoImpl implements ILogDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogDaoImpl.class);
	
	@Autowired
	LogRepository logRepo;
	
	@Override
	public LogObject saveEvent(LogObject logObject) throws Exception {

		LOGGER.info(" Going to save data ");
		
		return logRepo.save(logObject);
		
	}

}

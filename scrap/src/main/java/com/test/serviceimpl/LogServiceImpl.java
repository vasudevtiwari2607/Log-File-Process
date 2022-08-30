package com.test.serviceimpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.test.dao.ILogDao;
import com.test.model.LogObject;
import com.test.service.ILogService;

@Service
public class LogServiceImpl implements ILogService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogServiceImpl.class);

	@Autowired
	ILogDao logDao;

	@Override
	public void readFile(String filePath) throws Exception {
		BufferedReader reader;
		Map<String, LogObject> logMap = new HashMap<String, LogObject>();
		try {
			LOGGER.info(" Going to read file ", filePath);
			reader = new BufferedReader(new FileReader(filePath));
			String line = null;
			while ((line = reader.readLine()) != null) {
				loadDataInMemory(logMap, line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			LOGGER.error(" File not found ", e.getMessage());
		} catch (IOException e) {
			LOGGER.error(" Exception occured in File read Operation  ", e.getMessage());
		}

	}

	public void loadDataInMemory(Map<String, LogObject> logMap, String data) {
		LOGGER.debug("inside loadDataInMemory ");

		Gson gson = new Gson();

		LogObject logObject = gson.fromJson(data, LogObject.class);

		LogObject priorObject = logMap.get(logObject.getId());
		
		
		
		if ((logMap.get(logObject.getId()) != null) && (priorObject.getState() != logObject.getState())) {

			LOGGER.info(" Going to get opbject in memory ", logObject.getId());

			checkEventTimeDiff(logMap.get(logObject.getId()).getTimestamp(), logObject);

			try {
				LogObject saveEvent = logDao.saveEvent(logObject);

				LOGGER.info(" Going to remove key ", saveEvent.getId());

				logMap.keySet().remove(saveEvent.getId());

			} catch (Exception e) {
				LOGGER.error(" Exception on saving data  ", e.getMessage());
			}

		} else
			logMap.put(logObject.getId(), logObject);
	}

	public void checkEventTimeDiff(long timstamp, LogObject logObj) {

		LOGGER.debug(" inside checkevent time diff ");

		long diff = Math.abs(timstamp - logObj.getTimestamp());

		if (diff > 4l) {
			LOGGER.info(" Time diff is more then generating alert thrashold ");
			logObj.setAlert(true);
		} else
			logObj.setAlert(false);

		logObj.setEventDuration(diff);
	}

}

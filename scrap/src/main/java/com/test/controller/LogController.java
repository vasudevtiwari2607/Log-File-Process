package com.test.controller;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.LogObject;
import com.test.repository.LogRepository;
import com.test.service.ILogService;

@RestController
@RequestMapping("/logController")
public class LogController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogController.class);

	@Autowired
	ILogService logService;

	@Autowired
	public LogRepository logRepo;

//	Logger LOGGER = LoggerFactory.getLogger("jsonLogger");

	@PostMapping(value = "/readFile")
	public void readLofFile(@RequestParam String filePath) {

		LOGGER.info("Going to rad file from file path ", filePath);

		try {
			logService.readFile(filePath);

			Iterable<LogObject> findAll = logRepo.findAll();

			Iterator<LogObject> iterator = findAll.iterator();

			while (iterator.hasNext()) {

				System.out.println("***** printing output ***** " + iterator.next().toString());

			}

		} catch (Exception e) {
			LOGGER.error(" Exception while processing data ", e.getMessage());
		}

	}

}

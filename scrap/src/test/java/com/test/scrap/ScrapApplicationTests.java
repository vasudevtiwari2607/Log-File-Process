package com.test.scrap;

import java.util.Iterator;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.model.LogObject;
import com.test.repository.LogRepository;
import com.test.service.ILogService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScrapApplicationTests {

	@Autowired
	public LogRepository logRepo;

	@Autowired
	public ILogService service;
	
	@Autowired
    private ResourceLoader resourceLoader = null;

	@org.junit.jupiter.api.Test
	public void checkEventTimeDiffTest() {


		try {

			Resource resource = resourceLoader.getResource("classpath:log.txt");
			
			service.readFile(resource.getFile().getPath());

			Iterable<LogObject> findAll = logRepo.findAll();

			Iterator<LogObject> iterator = findAll.iterator();

			while (iterator.hasNext()) {

				Assertions.assertInstanceOf(LogObject.class, iterator.next());
				
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	@org.junit.jupiter.api.Test
	public void saveLog() {

		LogObject logObject = new LogObject();

		logObject.setId("123");
		logObject.setEventDuration(5l);
		logObject.setState("d");
		logObject.setHost("dfss");

		LogObject save = logRepo.save(logObject);

		Optional<LogObject> log = logRepo.findById(save.getDbId());

		Assertions.assertEquals(true, log.isPresent());

	}

}

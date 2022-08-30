package com.test.dao;

import com.test.model.LogObject;


public interface ILogDao {
	
	public LogObject saveEvent(LogObject logObject) throws Exception;

}

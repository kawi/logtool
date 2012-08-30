package com.noname.logtool.service.impl;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.noname.logtool.service.LogtoolService;

@Service
public class LogtoolServiceImpl implements LogtoolService {
	
	 private Map<String, Logger> loggersMap;

	@Override 
	public Map<String, Logger> getLoggersMap() {
	    if (CollectionUtils.isEmpty(loggersMap)) {
	        Enumeration<Logger> loggers = LogManager.getCurrentLoggers();
	        loggersMap = new HashMap<String, Logger>();
	        Logger rootLogger = LogManager.getRootLogger();
	        if (!loggersMap.containsKey(rootLogger.getName())) {
	            loggersMap.put(rootLogger.getName(), rootLogger);
	        }
	        while (loggers.hasMoreElements()) {
	            Logger logger = loggers.nextElement();
	            if (!loggersMap.containsKey(logger.getName())) {
	                loggersMap.put(logger.getName(), logger);
	            }
	        }
	    }
	    return loggersMap;
	}

}

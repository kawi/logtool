package com.noname.logtool.service;

import java.util.Map;

import org.apache.log4j.Logger;

public interface LogtoolService {

	Map<String, Logger> getLoggersMap();

}

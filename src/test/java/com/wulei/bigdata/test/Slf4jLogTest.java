package com.wulei.bigdata.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Slf4jLogTest {
	private static final Logger logger = LoggerFactory.getLogger(Slf4jLogTest.class);
	public static void main(String[] args) {
		logger.info("This is {} test!", "wulei");
	}
	
}
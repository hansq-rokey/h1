package com.ibaixiong.manage.test;

import java.io.FileNotFoundException;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

/**
 * 加载log4j配置文件
 * @author chenzehe
 *
 */
public class Log4jJUnit4ClassRunner extends SpringJUnit4ClassRunner {
	
	static {  
        try {  
            Log4jConfigurer.initLogging(Log4jJUnit4ClassRunner.class.getResource("/")+"log4j-test.properties");
        } catch (FileNotFoundException ex) {
            System.err.println("Cannot Initialize log4j["+ex.getMessage()+"]");  
        }  
    }
	
	public Log4jJUnit4ClassRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}
	
}

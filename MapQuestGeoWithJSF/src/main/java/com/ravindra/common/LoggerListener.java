package com.ravindra.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.bridge.SLF4JBridgeHandler;

public class LoggerListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized....");

		//remove the jsf root logger, avoid duplicated logging
		//try comment out this and see the different on the console
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("context destroyed...");
	}

}

package com.tealium.tutorial.inmemorymongo.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.tealium.tutorial.inmemorymongo.module.TutorialAppModule;
import com.tealium.tutorial.inmemorymongo.module.TutorialRestModule;

public class ServletConfig extends GuiceServletContextListener {

	private Injector injector;

	private ServletContext servletContext;

	@Override
	protected Injector getInjector() {
		return injector;
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		this.servletContext = servletContextEvent.getServletContext();

		injector = Guice.createInjector(new TutorialRestModule(servletContext), new TutorialAppModule());

		super.contextInitialized(servletContextEvent);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		injector = null;
	}
	
	
}

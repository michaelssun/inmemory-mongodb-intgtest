package com.tealium.tutorial.inmemorymongo.module;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.tealium.tutorial.inmemorymongo.web.AccountRestResource;
import com.tealium.tutorial.inmemorymongo.web.AccountRestResourceImpl;

public class TutorialRestModule extends ServletModule {
	private final ServletContext servletContext;

	private static Logger logger = LoggerFactory.getLogger(TutorialRestModule.class);

	@Override
	protected void configureServlets() {
		logger.debug("IN configureServlets");
		configureResources();
		configureRequestHandler();
		super.configureServlets();
	}

	public TutorialRestModule(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	private void configureResources(){
		bind(AccountRestResource.class).to(AccountRestResourceImpl.class);;
	}
	
	private void configureRequestHandler(){
		Map<String, String> params=new HashMap<String, String>();
		serve( "/*" ).with( GuiceContainer.class );
	}

}

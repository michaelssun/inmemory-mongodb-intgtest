package com.tealium.tutorial.inmemorymongo.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.tealium.tutorial.inmemorymongo.service.AccountService;
import com.tealium.tutorial.inmemorymongo.service.AccountServiceImpl;

public class TutorialAppModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(AccountService.class).to(AccountServiceImpl.class).in(Singleton.class);
		
		
	}

}

/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mocking.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.mocking.auth.model.Client;
import com.mocking.auth.repository.ClientDao;
import com.mocking.auth.repository.SubscriptionDao;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Component
public class DatabaseLoader implements CommandLineRunner {

	private final ClientDao clients;
	private final SubscriptionDao subscriptions;

	@Autowired
	public DatabaseLoader(ClientDao ClientDao,
						  SubscriptionDao SubscriptionDao) {

		this.clients = ClientDao;
		this.subscriptions = SubscriptionDao;
	}

	@Override
	public void run(String... strings) throws Exception {

		Client Tony=new Client("hello","hello","hello","hello","hello","hello");
		this.clients.save(Tony);
		//this.clients.findAll();
	}
}
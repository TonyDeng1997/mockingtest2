package com.mocking.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mocking.auth.model.Client;
import com.mocking.auth.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public void save(Client user) {
		clientRepository.save(user);
	}

	@Override
	public Client findByClientname(String username) {
		return clientRepository.findByName(username);
	}
}

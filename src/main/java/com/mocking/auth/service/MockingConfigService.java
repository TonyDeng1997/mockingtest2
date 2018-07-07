package com.mocking.auth.service;

import com.mocking.auth.model.MockingConfig;

public interface MockingConfigService {

	void save(MockingConfig mc);
	MockingConfig findMockingConfig(Long client_id);
	MockingConfig findMockingConfig(String candidate_id);
	MockingConfig findMockingConfig(Long client_id__id, String candidate_id);
}

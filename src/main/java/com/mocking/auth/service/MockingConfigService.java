package com.mocking.auth.service;

import java.util.Optional;
import com.mocking.auth.model.MockingConfig;

public interface MockingConfigService {

	void save(MockingConfig mc);

	Optional<MockingConfig> findMockingConfig(Long client_id);

	MockingConfig findMockingConfig(String candidate_id);

	MockingConfig findMockingConfig(Long client_id__id, String candidate_id);
}

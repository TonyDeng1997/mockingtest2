package com.mocking.auth.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mocking.auth.model.MockingConfig;
import com.mocking.auth.repository.MockingConfigRepository;

@Service
public class MockingConfigServiceImpl implements MockingConfigService {
	@Autowired
	private MockingConfigRepository mockingConfigRepository;

	@Override
	public void save(MockingConfig mc) {
		mockingConfigRepository.save(mc);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<MockingConfig> findMockingConfig(Long id) {
		return mockingConfigRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public MockingConfig findMockingConfig(String candidate_id) {
		return mockingConfigRepository.findByCandidateId(candidate_id);
	}

	@Override
	@Transactional(readOnly = true)
	public MockingConfig findMockingConfig(Long id, String candidate_id) {
		return mockingConfigRepository.find(id, candidate_id);
	}
}

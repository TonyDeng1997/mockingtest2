package com.mocking.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mocking.auth.model.MockingConfig;
import com.mocking.auth.repository.MockingConfigDao;

@Service
public class MockingConfigServiceImpl implements MockingConfigService {
		
		@Autowired
	    private MockingConfigDao mockingConfigDao;


		@Override
		public void save(MockingConfig mc) {
			// TODO Auto-generated method stub
			mockingConfigDao.save(mc);
		}

		@Override
		@Transactional(readOnly = true)
		public MockingConfig findMockingConfig(Long id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		@Transactional(readOnly = true)
		public MockingConfig findMockingConfig(String candidate_id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		@Transactional(readOnly = true)
		public MockingConfig findMockingConfig(Long id, String candidate_id) {
			// TODO Auto-generated method stub
			return null;
		}
}

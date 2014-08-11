package com.derbysoft.chapaai.adapter.pusher.application.service;

import com.derbysoft.chapaai.adapter.pusher.domain.repository.AbnormalPushEntryRepository;
import org.junit.Before;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;

public class AbnormalPushEntryServiceImplTest {

    private AbnormalPushEntryServiceImpl service;
    private AbnormalPushEntryRepository repository;



    @Before
    public void setup(){
        service = new AbnormalPushEntryServiceImpl();

        repository = mock(AbnormalPushEntryRepository.class);
        service.setAbnormalPushEntryRepository(repository);
    }
}

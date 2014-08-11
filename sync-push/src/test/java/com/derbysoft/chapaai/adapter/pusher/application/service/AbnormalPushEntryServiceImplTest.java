package com.derbysoft.chapaai.adapter.pusher.application.service;

import com.derbysoft.chapaai.adapter.pusher.application.service.impl.AbnormalPushEntryServiceImpl;
import com.derbysoft.chapaai.adapter.pusher.domain.model.abnormalpush.AbnormalPushEntry;
import com.derbysoft.chapaai.adapter.pusher.domain.model.abnormalpush.BlockPushEntry;
import com.derbysoft.chapaai.adapter.pusher.domain.model.abnormalpush.LowFrequencyPushEntry;
import com.derbysoft.chapaai.adapter.pusher.domain.repository.AbnormalPushEntryRepository;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

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

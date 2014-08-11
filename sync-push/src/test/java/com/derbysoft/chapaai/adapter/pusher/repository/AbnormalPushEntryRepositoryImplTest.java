package com.derbysoft.chapaai.adapter.pusher.repository;


import com.derbysoft.chapaai.adapter.pusher.repository.impl.AbnormalPushEntryRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AbnormalPushEntryRepositoryImplTest {


    private AbnormalPushEntryRepositoryImpl repository;

    private SessionFactory mockSessionFactory;
    private Session mockSession;



    @Before
    public void setup() {
        mockSession = mock(Session.class);
        mockSessionFactory = mock(SessionFactory.class);
        when(mockSessionFactory.getCurrentSession())
                .thenReturn(mockSession);

        repository = new AbnormalPushEntryRepositoryImpl();


    }


}

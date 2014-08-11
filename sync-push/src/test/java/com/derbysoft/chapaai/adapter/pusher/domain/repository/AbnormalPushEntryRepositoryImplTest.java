package com.derbysoft.chapaai.adapter.pusher.domain.repository;


import com.derbysoft.chapaai.adapter.pusher.domain.model.abnormalpush.AbnormalPushEntry;
import com.derbysoft.chapaai.adapter.pusher.domain.repository.impl.AbnormalPushEntryRepositoryImpl;
import junit.framework.Assert;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
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

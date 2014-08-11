package com.derbysoft.chapaai.adapter.pusher.service;

import com.derbysoft.chapaai.adapter.pusher.domain.model.RatePlan;
import com.derbysoft.chapaai.adapter.pusher.domain.model.RoomType;
import com.derbysoft.chapaai.adapter.pusher.repository.RatePlanRepository;
import com.derbysoft.chapaai.adapter.pusher.repository.RoomTypeRepository;
import org.junit.Assert;
import org.junit.Before;
import com.derbysoft.chapaai.adapter.pusher.service.impl.MappingServiceImpl;
import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MappingServiceImplTest {

    MappingServiceImpl mappingService;


    @Test
    public void testTranslateRatePlan(){
        String providerRatePlan = "providerRatePlan";
        String channelRatePlan = "channelRatePlan";
        RatePlan ratePlan = new RatePlan();
        ratePlan.setRatePlanCode(providerRatePlan);
        ratePlan.setChannelRatePlanCode(channelRatePlan);
        RatePlanRepository ratePlanRepository = mappingService.getRatePlanRepository();
        when(ratePlanRepository.getRatePlan(eq(providerRatePlan))).thenReturn(ratePlan);


        String result = mappingService.translateRatePlan(providerRatePlan);

        Assert.assertEquals(channelRatePlan,result);
    }

    @Test
    public void testTranslateRoomType(){
        String providerRoomType = "providerRoomType";
        String channelRoomType  = "channelRoomType";
        RoomType roomType = new RoomType();
        roomType.setRoomTypeCode(providerRoomType);
        roomType.setChannelRoomTypeCode(channelRoomType);
        RoomTypeRepository repository = mappingService.getRoomTypeRepository();
        when(repository.getRoomType(eq(providerRoomType))).thenReturn(roomType);

        String result = mappingService.translateRoomType(providerRoomType);

        Assert.assertEquals(channelRoomType,result);
    }

    @Before
    public void setup(){
        mappingService = new MappingServiceImpl();

        RatePlanRepository ratePlanRepository = mock(RatePlanRepository.class);
        RoomTypeRepository roomTypeRepository = mock(RoomTypeRepository.class);

        mappingService.setRatePlanRepository(ratePlanRepository);
        mappingService.setRoomTypeRepository(roomTypeRepository);
    }
}

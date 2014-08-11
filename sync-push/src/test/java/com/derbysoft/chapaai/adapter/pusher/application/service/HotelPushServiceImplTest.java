package com.derbysoft.chapaai.adapter.pusher.application.service;


import com.derbysoft.chapaai.adapter.pusher.application.service.impl.HotelPushServiceImpl;
import com.derbysoft.chapaai.adapter.pusher.domain.repository.ChangeRepository;
import com.derbysoft.synchronizer.dto.ChangesDTO;
import com.derbysoft.synchronizer.dto.HotelKeysDTO;
import com.derbysoft.synchronizer.dto.KeyTokenDTO;
import com.derbysoft.synchronizer.dto.LOSRateChangesDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HotelPushServiceImplTest {

    private HotelPushServiceImpl hotelPushServiceImpl;

    static final String HotelCode = "hotelCode";

    @Test
    public void testSyncPush(){
        String provideCode = "provideCode";
        String hotelCode = "hotelCode";
        HotelKeysDTO hotelKeysDTO = new HotelKeysDTO();
        hotelKeysDTO.setHotel(hotelCode);
        ChangeRepository changeRepository = hotelPushServiceImpl.getChangeRepository();
        HotelPushServiceImpl spyHotelPushServiceImpl = spy(hotelPushServiceImpl);

        List<ChangesDTO> changesDTOs = new ArrayList<ChangesDTO>();
        setupEnvForSyncPush(provideCode, hotelKeysDTO, changeRepository,changesDTOs);

        spyHotelPushServiceImpl.syncPush(provideCode,hotelKeysDTO);

        verify(spyHotelPushServiceImpl,times(1)).pushChanges(provideCode, hotelCode,changesDTOs);

    }



    @Test
    public void testPushChanges(){
        String providerCode = "providerCode";
        String hotelCode   = "hotelCode";
        HotelPushServiceImpl spyHotelPushServiceImpl = spy(hotelPushServiceImpl);
        ChangeRepository changeRepository = spyHotelPushServiceImpl.getChangeRepository();
        List<ChangesDTO> changesDTOs = new ArrayList<ChangesDTO>();
        ChangesDTO changesDTO1 = new ChangesDTO();
        ChangesDTO changesDTO2 = new ChangesDTO();
        changesDTOs.add(changesDTO1);
        changesDTOs.add(changesDTO2);


        KeyTokenDTO keyTokens1  = new KeyTokenDTO();
        KeyTokenDTO keyTokens2  = new KeyTokenDTO();


        setMockEnvForPushChanges(spyHotelPushServiceImpl,changesDTO1,changesDTO2,keyTokens1,keyTokens2);


        spyHotelPushServiceImpl.pushChanges(providerCode,hotelCode,changesDTOs);

        verify(spyHotelPushServiceImpl,times(1)).push(changesDTO1,hotelCode);
        verify(spyHotelPushServiceImpl,times(1)).push(changesDTO2,hotelCode);
        verify(changeRepository,times(1)).deleteChanges(providerCode, hotelCode,
                Arrays.asList(keyTokens1));
        verify(changeRepository,times(1)).deleteChanges(providerCode, hotelCode,
                Arrays.asList(keyTokens2));
    }

    @Test
    public void testCreateKeyTokensSuccess(){

        String losRateToken = "losRateToken";
        String key = "key";

        LOSRateChangesDTO losRateChanges = new LOSRateChangesDTO();
        losRateChanges.setToken(losRateToken);
        ChangesDTO changesDTO = createChangesDTO(key,losRateChanges);

        KeyTokenDTO tokens = hotelPushServiceImpl.createKeyTokens(changesDTO);

        Assert.assertEquals(key,tokens.getKey());
        Assert.assertEquals(1, tokens.getTokensList().size());
        Assert.assertTrue(tokens.getTokensList().contains(losRateToken));
    }


    @Test
    public void testCreateKeyTokensLOSRateNotExisted(){

        String key = "key";

        LOSRateChangesDTO losRateChanges = null;
        ChangesDTO changesDTO = createChangesDTO(key,losRateChanges);

        KeyTokenDTO tokens = hotelPushServiceImpl.createKeyTokens(changesDTO);

        Assert.assertEquals(key,tokens.getKey());
        Assert.assertEquals(0, tokens.getTokensList().size());
    }


    @Test
    public void testPushPushStayAvailability(){
        String providerCode = "providerCode";
        ChangesDTO changesDTO = new ChangesDTO();

        List<ChangePushService> changePushServices = Arrays.asList(
                mock(ChangePushService.class),mock(ChangePushService.class),mock(ChangePushService.class)
        );
        hotelPushServiceImpl.setPushServices(changePushServices);


        hotelPushServiceImpl.push(changesDTO,HotelCode);

      for(ChangePushService changePushService:changePushServices){
          verify(changePushService,times(1)).syncPush(changesDTO,HotelCode);
      }
    }

    private void setupEnvForSyncPush(String provideCode, HotelKeysDTO hotelKeys, ChangeRepository changeRepository,List<ChangesDTO> changesDTOs) {
        when(changeRepository.getChanges(eq(provideCode),eq(hotelKeys))).thenReturn(changesDTOs);

    }

    private void setMockEnvForPushChanges(HotelPushServiceImpl spyHotelPushServiceImpl, ChangesDTO changesDTO1, ChangesDTO changesDTO2,  KeyTokenDTO keyTokens1,  KeyTokenDTO keyTokens2) {

        when(spyHotelPushServiceImpl.createKeyTokens(changesDTO1)).thenReturn(keyTokens1);
        when(spyHotelPushServiceImpl.createKeyTokens(changesDTO2)).thenReturn(keyTokens2);

    }

    private ChangesDTO createChangesDTO(String key, LOSRateChangesDTO losRateChanges) {
        ChangesDTO result =new ChangesDTO();
        result.setKey(key);
        result.setLosRateChanges(losRateChanges);
        return result;
    }




    @Before
    public void setup(){
        hotelPushServiceImpl = new HotelPushServiceImpl();

        ChangeRepository changeRepository = mock(ChangeRepository.class);
        List<ChangePushService> stayAvailabilityPushService = new ArrayList<ChangePushService>();

        hotelPushServiceImpl.setChangeRepository(changeRepository);
        hotelPushServiceImpl.setPushServices(stayAvailabilityPushService);
    }
}

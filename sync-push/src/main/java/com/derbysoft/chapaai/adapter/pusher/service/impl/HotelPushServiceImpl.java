package com.derbysoft.chapaai.adapter.pusher.service.impl;

import com.derbysoft.chapaai.adapter.pusher.service.ChangePushService;
import com.derbysoft.chapaai.adapter.pusher.service.HotelPushService;
import com.derbysoft.chapaai.adapter.pusher.repository.ChangeRepository;
import com.derbysoft.synchronizer.dto.ChangesDTO;
import com.derbysoft.synchronizer.dto.HotelKeysDTO;
import com.derbysoft.synchronizer.dto.KeyTokenDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotelPushServiceImpl implements HotelPushService {

    private ChangeRepository changeRepository;

    private List<ChangePushService> pushServices;

    @Override
    public void syncPush(String providerCode, HotelKeysDTO hotelKeysDTO) {
        List<ChangesDTO> changesDTOs = changeRepository.getChanges(providerCode, hotelKeysDTO);
        pushChanges(providerCode, hotelKeysDTO.getHotel(), changesDTOs);
    }

    public void pushChanges(String providerCode, String hotelCode,List<ChangesDTO> changesDTOs) {
        for (ChangesDTO changes : changesDTOs) {
            push(changes,hotelCode);
            changeRepository.deleteChanges(providerCode, hotelCode,
                    Arrays.asList(createKeyTokens(changes)));
        }
    }

    public KeyTokenDTO createKeyTokens(ChangesDTO changes) {
        KeyTokenDTO result = new KeyTokenDTO();

        result.setKey(changes.getKey());

        ArrayList<String> tokens = new ArrayList<String>();

        if (changes.getLosRateChanges() != null) {
            tokens.add(changes.getLosRateChanges().getToken());
        }

        result.setTokensList(tokens);
        return result;
    }

    public void push(ChangesDTO changes,String hotelCode) {
           for(ChangePushService pushService:pushServices){
               pushService.syncPush(changes,hotelCode);
           }

    }


    public ChangeRepository getChangeRepository() {
        return changeRepository;
    }

    public void setChangeRepository(ChangeRepository changeRepository) {
        this.changeRepository = changeRepository;
    }


    public List<ChangePushService> getPushServices() {
        return pushServices;
    }

    public void setPushServices(List<ChangePushService> pushServices) {
        this.pushServices = pushServices;
    }
}

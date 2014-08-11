package com.derbysoft.chapaai.adapter.pusher.domain.repository.impl;

import com.derbysoft.chapaai.adapter.pusher.domain.exception.GetChangeException;
import com.derbysoft.chapaai.adapter.pusher.domain.model.Hotel;
import com.derbysoft.chapaai.adapter.pusher.domain.repository.ChangeRepository;
import com.derbysoft.dswitch.dto.common.ErrorDTO;
import com.derbysoft.dswitch.remote.hotel.dto.RequestHeader;
import com.derbysoft.synchronizer.dto.ChangesDTO;
import com.derbysoft.synchronizer.dto.DeleteKeysRQ;
import com.derbysoft.synchronizer.dto.GetChangesRQ;
import com.derbysoft.synchronizer.dto.GetKeysRQ;
import com.derbysoft.synchronizer.dto.HotelKeysDTO;
import com.derbysoft.synchronizer.dto.KeyTokenDTO;
import com.derbysoft.synchronizer.dto.Level;
import com.derbysoft.synchronizer.remote.buyer.BuyerSynchronizerRemoteService;
import com.derbysoft.synchronizer.remote.dto.DeleteKeysRequest;
import com.derbysoft.synchronizer.remote.dto.DeleteKeysResponse;
import com.derbysoft.synchronizer.remote.dto.GetChangesRequest;
import com.derbysoft.synchronizer.remote.dto.GetChangesResponse;
import com.derbysoft.synchronizer.remote.dto.GetKeysRequest;
import com.derbysoft.synchronizer.remote.dto.GetKeysResponse;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ChangeRepositoryImpl implements ChangeRepository {

    private BuyerSynchronizerRemoteService synchronizeChangeService;

    private String channel;

    @Override
    public List<HotelKeysDTO> getKeys(List<Hotel> hotels) {
        if (CollectionUtils.isEmpty(hotels)) {
            return Collections.emptyList();
        }
        GetKeysResponse response = synchronizeChangeService.getKeys(CreateGetKeysRequest(hotels));
        errorCheck(response.getError());

        return response.getGetKeysRS().getHotelKeysList();
    }

    @Override
    public List<ChangesDTO> getChanges(String provider, HotelKeysDTO hotelKeysDTO) {

        GetChangesResponse response = synchronizeChangeService.getChanges(createGetChangesRequest(provider, hotelKeysDTO));
        errorCheck(response.getError());

        return response.getGetChangesRS().getChangesList();
    }

    @Override
    public void deleteChanges(String provider, String hotelCode, List<KeyTokenDTO> keyTokens) {

        DeleteKeysResponse response = synchronizeChangeService.deleteKeys(
                createDeleteKeysRequest(provider, hotelCode, keyTokens));
        errorCheck(response.getError());
    }

    public void errorCheck(ErrorDTO error) {
        if(error!= null){
            //TODO to do more handle
            throw new GetChangeException(error);
        }
    }

    private GetKeysRequest CreateGetKeysRequest(List<Hotel> hotels) {
        GetKeysRQ getKeysRQ = new GetKeysRQ(Level.RATE);
        getKeysRQ.setHotelsList(convertToHotelCodeList(hotels));
        return new GetKeysRequest(new RequestHeader(channel, hotels.get(0).getProviderCode(), generateTaskId()), getKeysRQ);
    }

    public List<String> convertToHotelCodeList(List<Hotel> hotels) {
        List<String> hotelCodes = new ArrayList<String>();
        for (Hotel hotel : hotels) {
            hotelCodes.add(hotel.getProviderHotelCode());
        }
        return hotelCodes;
    }

    private GetChangesRequest createGetChangesRequest(String provider, HotelKeysDTO hotelKeysDTO) {
        return new GetChangesRequest(new RequestHeader(channel, provider, generateTaskId()), createGetChangesRQ(hotelKeysDTO));
    }

    private GetChangesRQ createGetChangesRQ(HotelKeysDTO hotelKeysDTO) {
        GetChangesRQ getChangesRQ = new GetChangesRQ(hotelKeysDTO.getHotel());
        getChangesRQ.setKeysList(hotelKeysDTO.getKeysList());
        return getChangesRQ;
    }

    private DeleteKeysRequest createDeleteKeysRequest(String provider, String hotelCode, List<KeyTokenDTO> keyTokens) {
        return new DeleteKeysRequest(new RequestHeader(channel, provider, generateTaskId()), createDeleteKeysRQ(hotelCode, keyTokens));
    }

    private String generateTaskId() {
        return UUID.randomUUID().toString();
    }

    public DeleteKeysRQ createDeleteKeysRQ(String hotelCode, List<KeyTokenDTO> keyTokens) {
        DeleteKeysRQ deleteKeysRQ = new DeleteKeysRQ(hotelCode);
        deleteKeysRQ.setKeyTokensList(keyTokens);
        return deleteKeysRQ;
    }

    public BuyerSynchronizerRemoteService getSynchronizeChangeService() {
        return synchronizeChangeService;
    }

    public void setSynchronizeChangeService(BuyerSynchronizerRemoteService synchronizeChangeService) {
        this.synchronizeChangeService = synchronizeChangeService;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}

package com.derbysoft.chapaai.adapter.pusher.repository.impl;



import com.derbysoft.chapaai.adapter.pusher.domain.model.Hotel;
import com.derbysoft.chapaai.adapter.pusher.repository.HotelRepository;

import java.util.List;

public class HotelRepositoryImpl extends ChapaaiEntryRepositoryImpl<Hotel> implements HotelRepository {

    public static final String ProvideCode = "ProviderCode";

    @Override
    public List<Hotel> getHotel(String providerCode, boolean disabled) {
        return this.listChapaaiMappingEntry(ProvideCode,providerCode,disabled);
    }

    @Override
    public Hotel getHotel(String hotelCode) {
        return this.getChapaaiMappingEntry(hotelCode);
    }

}

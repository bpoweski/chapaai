package com.derbysoft.chapaai.adapter.pusher.domain.repository.impl;



import com.derbysoft.ccs.core.MappingCache;
import com.derbysoft.chapaai.adapter.pusher.domain.model.Hotel;
import com.derbysoft.chapaai.adapter.pusher.domain.repository.HotelRepository;
import com.derbysoft.chapaai.adapter.pusher.utils.RepositoryUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

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

package com.derbysoft.chapaai.adapter.pusher.repository;



import com.derbysoft.chapaai.adapter.pusher.domain.model.Hotel;

import java.util.List;

public interface HotelRepository{

    List<Hotel> getHotel(String providerCode, boolean disabled);

    Hotel getHotel(String hotelCode);
}

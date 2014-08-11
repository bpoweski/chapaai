package com.derbysoft.chapaai.adapter.pusher.domain.repository;

import com.derbysoft.chapaai.adapter.pusher.domain.model.Hotel;
import com.derbysoft.synchronizer.dto.ChangesDTO;
import com.derbysoft.synchronizer.dto.HotelKeysDTO;
import com.derbysoft.synchronizer.dto.KeyTokenDTO;

import java.util.List;

public interface ChangeRepository {

    List<HotelKeysDTO> getKeys(List<Hotel> hotels);

    List<ChangesDTO> getChanges(String provider, HotelKeysDTO hotelKeysDTO);

    void deleteChanges(String provider, String hotelCode, List<KeyTokenDTO> keyTokens);
}

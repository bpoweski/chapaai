package com.derbysoft.chapaai.adapter.pusher.application.task.generator;


import com.derbysoft.chapaai.adapter.pusher.application.task.PushTask;
import com.derbysoft.chapaai.adapter.pusher.domain.model.Hotel;
import com.derbysoft.chapaai.adapter.pusher.domain.repository.ChangeRepository;
import com.derbysoft.chapaai.adapter.pusher.domain.repository.HotelRepository;
import com.derbysoft.synchronizer.dto.HotelKeysDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PushTaskGenerator {

    public static final boolean ENABLE = false;

    private  HotelRepository hotelRepository;

    private ChangeRepository changeRepository;

    public  List<PushTask> pushGenerate(String providerCode) {
        return generate(providerCode);
    }

    private  List<PushTask> generate(String providerCode) {

        try {
            List<Hotel> hotels = hotelRepository.getHotel(providerCode, ENABLE);

            List<HotelKeysDTO> hotelKeysDTOs = changeRepository.getKeys(hotels);
            if (hotels == null) {
                return new ArrayList<PushTask>();
            }
            return getPushTasks(providerCode, hotelKeysDTOs);
        } catch (RuntimeException e) {
            //TODO add log here
            return Collections.emptyList();
        } finally {
        }
    }

    public  List<PushTask> getPushTasks(String providerCode, List<HotelKeysDTO> hotelKeysDTOs) {
        List<PushTask> pushTasks = new ArrayList<PushTask>();
        for (HotelKeysDTO hotelKeysDTO : hotelKeysDTOs) {
            pushTasks.add(new PushTask(providerCode, hotelKeysDTO));
        }
        return pushTasks;
    }


    public void setHotelRepository(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public HotelRepository getHotelRepository() {
        return hotelRepository;
    }

    public ChangeRepository getChangeRepository() {
        return changeRepository;
    }

    public void setChangeRepository(ChangeRepository changeRepository) {
        this.changeRepository = changeRepository;
    }
}

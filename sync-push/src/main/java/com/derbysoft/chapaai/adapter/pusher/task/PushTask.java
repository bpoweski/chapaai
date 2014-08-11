package com.derbysoft.chapaai.adapter.pusher.task;

import com.derbysoft.chapaai.adapter.core.concurrent.PriorityTask;
import com.derbysoft.chapaai.adapter.pusher.domain.model.Hotel;
import com.derbysoft.synchronizer.dto.HotelKeysDTO;

public class PushTask implements PriorityTask {
    private String id;
    private String providerCode;
    private HotelKeysDTO hotelKeys;
    private Hotel hotel;

    public PushTask(String providerCode, HotelKeysDTO hotelKeys) {
        this.providerCode = providerCode;
        this.hotelKeys = hotelKeys;
        this.id =  "Push:provider=" + providerCode.trim() + ",hotel=" + hotelKeys.getHotel().trim();
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public HotelKeysDTO getHotelKeys() {
        return hotelKeys;
    }

    public void setHotelKeys(HotelKeysDTO hotelKeys) {
        this.hotelKeys = hotelKeys;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String getTaskId() {
        return id;
    }

    @Override
    public int getPriority() {
        return  1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PushTask)) return false;

        PushTask pushTask = (PushTask) o;

        if (hotelKeys != null ? !hotelKeys.equals(pushTask.hotelKeys) : pushTask.hotelKeys != null) return false;
        if (providerCode != null ? !providerCode.equals(pushTask.providerCode) : pushTask.providerCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = providerCode != null ? providerCode.hashCode() : 0;
        result = 31 * result + (hotelKeys != null ? hotelKeys.hashCode() : 0);
        return result;
    }
}

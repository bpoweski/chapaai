package com.derbysoft.chapaai.adapter.pusher.domain.model;

public class Hotel implements ChapaaiMappingEntry {

    private String id;
    private String providerHotelCode;
    private String channelHotelCode;
    private String providerCode;
    private boolean disabled;


    public Hotel() {
    }

    public Hotel(String providerCode, String providerHotelCode) {
        this.providerCode = providerCode;
        this.providerHotelCode = providerHotelCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProviderHotelCode() {
        return providerHotelCode;
    }

    public void setProviderHotelCode(String providerHotelCode) {
        this.providerHotelCode = providerHotelCode;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    @Override
    public boolean isDisabled() {
        return disabled;
    }

    @Override
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getChannelHotelCode() {
        return channelHotelCode;
    }

    public void setChannelHotelCode(String channelHotelCode) {
        this.channelHotelCode = channelHotelCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;

        Hotel hotel = (Hotel) o;

        if (providerHotelCode != null ? !providerHotelCode.equals(hotel.providerHotelCode) : hotel.providerHotelCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return providerHotelCode != null ? providerHotelCode.hashCode() : 0;
    }
}

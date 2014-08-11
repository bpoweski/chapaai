package com.derbysoft.chapaai.adapter.pusher.domain.model;

public class PushEntry {

    private String id;

    private String hotelCode;

    private String ratePlanCode;

    private String roomTypeCode;

    /**
     * this method is ugly, the parameter should be one Object,not three string.
     * but there's no class can represent a push. so I use this way.
     * @param hotelCode
     * @param ratePlanCode
     * @param roomTypeCode
     * @return
     */
    public Boolean isSame(String hotelCode, String ratePlanCode,String roomTypeCode){
        return (this.hotelCode.equals(hotelCode)&&
                this.ratePlanCode.equals(ratePlanCode)&&
                this.roomTypeCode.equals(roomTypeCode));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getRatePlanCode() {
        return ratePlanCode;
    }

    public void setRatePlanCode(String ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
    }

    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PushEntry)) return false;

        PushEntry pushEntry = (PushEntry) o;

        if (!hotelCode.equals(pushEntry.hotelCode)) return false;
        if (!ratePlanCode.equals(pushEntry.ratePlanCode)) return false;
        if (!roomTypeCode.equals(pushEntry.roomTypeCode)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hotelCode.hashCode();
        result = 31 * result + ratePlanCode.hashCode();
        result = 31 * result + roomTypeCode.hashCode();
        return result;
    }
}

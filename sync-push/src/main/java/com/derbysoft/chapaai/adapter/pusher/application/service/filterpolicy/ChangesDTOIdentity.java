package com.derbysoft.chapaai.adapter.pusher.application.service.filterpolicy;

import com.derbysoft.synchronizer.dto.ChangesDTO;

public class ChangesDTOIdentity {

    public final static ChangesDTOIdentity EMPTY = null;

    private ChangesDTO changesDTO;

    private String hotelCode;

    private String ratePlanCode;

    private String roomTypeCode;


    protected static ChangesDTOIdentity parseIdentity(ChangesDTO changesDTO){
        return EMPTY;
    }

    private ChangesDTOIdentity(ChangesDTO changesDTO){
        this.changesDTO = changesDTO;
    }

    protected void parse(ChangesDTO changesDTO){

    }

    public ChangesDTO getChangesDTO() {
        return changesDTO;
    }

    public void setChangesDTO(ChangesDTO changesDTO) {
        this.changesDTO = changesDTO;
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
        if (!(o instanceof ChangesDTOIdentity)) return false;

        ChangesDTOIdentity that = (ChangesDTOIdentity) o;

        if (hotelCode != null ? !hotelCode.equals(that.hotelCode) : that.hotelCode != null) return false;
        if (ratePlanCode != null ? !ratePlanCode.equals(that.ratePlanCode) : that.ratePlanCode != null) return false;
        if (roomTypeCode != null ? !roomTypeCode.equals(that.roomTypeCode) : that.roomTypeCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hotelCode != null ? hotelCode.hashCode() : 0;
        result = 31 * result + (ratePlanCode != null ? ratePlanCode.hashCode() : 0);
        result = 31 * result + (roomTypeCode != null ? roomTypeCode.hashCode() : 0);
        return result;
    }
}

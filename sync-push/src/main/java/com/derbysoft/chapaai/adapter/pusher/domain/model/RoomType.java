package com.derbysoft.chapaai.adapter.pusher.domain.model;

import com.derbysoft.ccs.core.MappingEntry;

public class RoomType implements ChapaaiMappingEntry {

    private String roomTypeCode;

    private String channelRoomTypeCode;

    private boolean disabled;

    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }

    public String getChannelRoomTypeCode() {
        return channelRoomTypeCode;
    }

    public void setChannelRoomTypeCode(String channelRoomTypeCode) {
        this.channelRoomTypeCode = channelRoomTypeCode;
    }

    @Override
    public boolean isDisabled() {
        return disabled;
    }

    @Override
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomType)) return false;

        RoomType roomType = (RoomType) o;

        if (channelRoomTypeCode != null ? !channelRoomTypeCode.equals(roomType.channelRoomTypeCode) : roomType.channelRoomTypeCode != null)
            return false;
        if (roomTypeCode != null ? !roomTypeCode.equals(roomType.roomTypeCode) : roomType.roomTypeCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roomTypeCode != null ? roomTypeCode.hashCode() : 0;
        result = 31 * result + (channelRoomTypeCode != null ? channelRoomTypeCode.hashCode() : 0);
        return result;
    }
}

package com.derbysoft.chapaai.adapter.pusher.application.service.filterpolicy;

import com.derbysoft.synchronizer.dto.ChangesDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChangesDTOIdentityTest {

    private ChangesDTOIdentity changesDTOIdentity;

    private String HOTEL_CODE = "hotelCode";
    private String RATE_PLAN_CODE = "RatePlanCode";
    private String ROME_TYPE_CODE = "RoomTypeCode";

    @Test
    public void testParseIdentityEmpty() {
        Assert.assertEquals(ChangesDTOIdentity.EMPTY,ChangesDTOIdentity.parseIdentity(null));
    }


    @Test
    public void testParse() {

    }


    public ChangesDTO assembleChangesDTO(String hotelCode,String ratePlanCode, String roomTypeCode){
        ChangesDTO changesDTO = new ChangesDTO();
        return null;
    }

    @Before
    public void setup() {
        changesDTOIdentity = ChangesDTOIdentity.parseIdentity(null);
    }
}

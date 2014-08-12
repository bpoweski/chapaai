package com.derbysoft.chapaai.adapter.pusher.mock.object;

import com.derbysoft.dswitch.dto.hotel.cds.LOSRateChangeDTO;
import com.derbysoft.dswitch.dto.hotel.cds.LOSRateDTO;
import com.derbysoft.dswitch.dto.hotel.cds.OccupancyRateDTO;
import com.derbysoft.dswitch.dto.hotel.cds.RatesDTO;
import com.derbysoft.synchronizer.dto.ChangesDTO;
import com.derbysoft.synchronizer.dto.DeleteKeysRS;
import com.derbysoft.synchronizer.dto.GetChangesRS;
import com.derbysoft.synchronizer.dto.GetKeysRS;
import com.derbysoft.synchronizer.dto.HotelKeysDTO;
import com.derbysoft.synchronizer.dto.KeyTokenDTO;
import com.derbysoft.synchronizer.dto.LOSRateChangesDTO;
import com.derbysoft.synchronizer.remote.buyer.BuyerSynchronizerRemoteService;
import com.derbysoft.synchronizer.remote.dto.DeleteKeysRequest;
import com.derbysoft.synchronizer.remote.dto.DeleteKeysResponse;
import com.derbysoft.synchronizer.remote.dto.GetChangesRequest;
import com.derbysoft.synchronizer.remote.dto.GetChangesResponse;
import com.derbysoft.synchronizer.remote.dto.GetKeysRequest;
import com.derbysoft.synchronizer.remote.dto.GetKeysResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuyerSynchronizerRemoteServiceMockFactory {


    public static final String KEY_1 = "1";
    public static final String KEY_2 = "2";
    public static final String LOS_TOKEN_1 = "LOS_TOKEN_1";
    public static final String LOS_TOKEN_2 = "LOS_TOKEN_2";

    public BuyerSynchronizerRemoteService createMockBuyerSynchronizerRemoteService(){
        BuyerSynchronizerRemoteService result = mock(BuyerSynchronizerRemoteService.class);
        when(result.getKeys(any(GetKeysRequest.class))).thenReturn(createMockGetKeysResponse());
        when(result.getChanges(any(GetChangesRequest.class))).thenReturn(createMockGetChangeResponse());
        when(result.deleteKeys(any(DeleteKeysRequest.class))).thenReturn(createMockDeleteChangeResponse());

        return result;
    }


    public GetKeysResponse createMockGetKeysResponse(){
        GetKeysResponse result = new GetKeysResponse();
        GetKeysRS rs = new GetKeysRS();

        List<HotelKeysDTO> hotelKeys = new ArrayList<HotelKeysDTO>();
        HotelKeysDTO hotelKeysDTO1 = new HotelKeysDTO();
        hotelKeysDTO1.setHotel("HOTEL_1");
        hotelKeysDTO1.setKeysList(Arrays.asList(KEY_1, KEY_2));

        hotelKeys.add(hotelKeysDTO1);


        rs.setHotelKeysList(hotelKeys);
        result.setGetKeysRS(rs);
        return result;
    }

    public GetChangesResponse createMockGetChangeResponse(){
        GetChangesResponse result = new GetChangesResponse();
        GetChangesRS getChangesRS = new GetChangesRS();

        ChangesDTO changesDTO1 = new ChangesDTO();
        changesDTO1.setKey(KEY_1);
        LOSRateChangesDTO losRateChanges1 = new LOSRateChangesDTO();
        LOSRateChangeDTO dto1 = createLosRateChangeDTO("2014-07-02");
        LOSRateChangeDTO dto2 = createLosRateChangeDTO("2014-07-03");
        losRateChanges1.setToken(LOS_TOKEN_1);
        losRateChanges1.setLosRateChangesList(Arrays.asList(dto1, dto2));
        changesDTO1.setLosRateChanges(losRateChanges1);

        ChangesDTO changesDTO2 = new ChangesDTO();
        changesDTO2.setKey(KEY_2);
        LOSRateChangesDTO losRateChanges2 = new LOSRateChangesDTO();
        LOSRateChangeDTO dto3 = createLosRateChangeDTO("2014-07-02");
        LOSRateChangeDTO dto4 = createLosRateChangeDTO("2014-07-03");
        losRateChanges2.setToken(LOS_TOKEN_2);
        losRateChanges2.setLosRateChangesList(Arrays.asList(dto3, dto4));
        changesDTO2.setLosRateChanges(losRateChanges2);


        getChangesRS.setHotel("HOTEL_1");
        getChangesRS.setChangesList(Arrays.asList(changesDTO1,changesDTO1));
        result.setGetChangesRS(getChangesRS);
        return result;
    }

    public DeleteKeysResponse createMockDeleteChangeResponse(){
        DeleteKeysResponse result = new DeleteKeysResponse();
        DeleteKeysRS rs = new DeleteKeysRS();
        KeyTokenDTO keyTokenDTO1 = new KeyTokenDTO();
        keyTokenDTO1.setKey(KEY_1);
        keyTokenDTO1.setTokensList(Arrays.asList(LOS_TOKEN_1));

        KeyTokenDTO keyTokenDTO2 = new KeyTokenDTO();
        keyTokenDTO2.setKey(KEY_2);
        keyTokenDTO2.setTokensList(Arrays.asList(LOS_TOKEN_2));

        rs.setKeyTokensList(Arrays.asList(keyTokenDTO1,keyTokenDTO2));
        result.setDeleteKeysRS(rs);
        return result;
    }

    private LOSRateChangeDTO createLosRateChangeDTO(String checkInDate) {
        LOSRateChangeDTO dto1 = new LOSRateChangeDTO();
        dto1.setCancelPolicy("cancelPolicy");
        dto1.setCheckInDate(checkInDate);
        dto1.setCurrency("USD");
        dto1.setFplos("1");
        dto1.setMaxAdult(100);
        dto1.setMaxChild(100);
        dto1.setMaxOccupancy(100);
        dto1.setRatePlanCode("RatePlan1");
        dto1.setRoomTypeCode("RoomType1");

        LOSRateDTO LOSRateDTO1 = new LOSRateDTO();
        LOSRateDTO1.setInventory(100);
        LOSRateDTO1.setLos(1);
        RatesDTO ratesDTO1 = new RatesDTO();
        ratesDTO1.setStart(0);
        ratesDTO1.setEnd(0);
        OccupancyRateDTO OccupancyRateDTO1 = createOccupancyRateDTO(100,100,1.0,2.0);
        OccupancyRateDTO OccupancyRateDTO2 = createOccupancyRateDTO(10,10,3.0,5.0);
        ratesDTO1.setOccupancyRatesList(Arrays.asList(OccupancyRateDTO1, OccupancyRateDTO2));
        RatesDTO ratesDTO2 = new RatesDTO();
        ratesDTO2.setStart(0);
        ratesDTO2.setEnd(0);
        OccupancyRateDTO OccupancyRateDTO3 = createOccupancyRateDTO(100,100,1.0,2.0);
        OccupancyRateDTO OccupancyRateDTO4 = createOccupancyRateDTO(10,10,3.0,5.0);
        ratesDTO2.setOccupancyRatesList(Arrays.asList(OccupancyRateDTO3, OccupancyRateDTO4));
        LOSRateDTO1.setRatesList(Arrays.asList(ratesDTO1,ratesDTO2));

        LOSRateDTO LOSRateDTO2 = new LOSRateDTO();
        LOSRateDTO2.setInventory(100);
        LOSRateDTO2.setLos(2);
        RatesDTO ratesDTO3 = new RatesDTO();
        ratesDTO3.setStart(0);
        ratesDTO3.setEnd(1);
        OccupancyRateDTO OccupancyRateDTO5 = createOccupancyRateDTO(100,100,1.0,2.0);
        OccupancyRateDTO OccupancyRateDTO6 = createOccupancyRateDTO(10,10,3.0,5.0);
        ratesDTO3.setOccupancyRatesList(Arrays.asList(OccupancyRateDTO5, OccupancyRateDTO6));
        RatesDTO ratesDTO4 = new RatesDTO();
        ratesDTO4.setStart(0);
        ratesDTO4.setEnd(1);
        OccupancyRateDTO OccupancyRateDTO7 = createOccupancyRateDTO(100,100,1.0,2.0);
        OccupancyRateDTO OccupancyRateDTO8 = createOccupancyRateDTO(10,10,3.0,5.0);
        ratesDTO4.setOccupancyRatesList(Arrays.asList(OccupancyRateDTO7, OccupancyRateDTO8));
        LOSRateDTO2.setRatesList(Arrays.asList(ratesDTO3, ratesDTO4));

        dto1.setLosRatesList(Arrays.asList(LOSRateDTO1,LOSRateDTO2));
        return dto1;
    }

    private OccupancyRateDTO createOccupancyRateDTO(int adult,int child,double afterTax,double beforeTax) {
        OccupancyRateDTO OccupancyRateDTO = new OccupancyRateDTO();
        OccupancyRateDTO.setAdult(adult);
        OccupancyRateDTO.setChild(child);
        OccupancyRateDTO.setAmountAfterTax(afterTax);
        OccupancyRateDTO.setAmountBeforeTax(beforeTax);
        return OccupancyRateDTO;
    }
}

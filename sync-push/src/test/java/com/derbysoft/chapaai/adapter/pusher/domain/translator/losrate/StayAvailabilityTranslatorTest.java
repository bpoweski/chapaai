package com.derbysoft.chapaai.adapter.pusher.domain.translator.losrate;


import com.derbysoft.chapaai.adapter.pusher.domain.stay.StayAvailability;
import com.derbysoft.chapaai.adapter.pusher.domain.stay.StayAvailabilityUpdateRequest;
import com.derbysoft.chapaai.adapter.pusher.service.MappingService;
import com.derbysoft.chapaai.adapter.pusher.integrationtest.XMLUtils;
import com.derbysoft.dswitch.dto.hotel.cds.LOSRateChangeDTO;
import com.derbysoft.dswitch.dto.hotel.cds.LOSRateDTO;
import com.derbysoft.dswitch.dto.hotel.cds.RatesDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class StayAvailabilityTranslatorTest {

    private StayAvailabilityTranslator translator;

    @Test
    public void testTransferRoomTypeDiff() {
        String expected_request = XMLUtils.readXML("TranslateStayAvailability1.xml");

        String currency = "USD";
        List<RatesDTO> cdsRates1 = mock(List.class);
        List<RatesDTO> cdsRates2 = mock(List.class);
        List<LOSRateChangeDTO> LOSRateChanges = createLosRateChanges(currency, cdsRates1, cdsRates2);

        StayAvailability.DayRates dayRatesResult = mock(StayAvailability.DayRates.class);
        DailyRateTranslator dailyRateTranslator = translator.getDailyRateTranslator();
        when(dailyRateTranslator.translate(
                eq(cdsRates1), eq(currency)
        )).thenReturn(dayRatesResult);
        when(dailyRateTranslator.translate(
                eq(cdsRates2), eq(currency)
        )).thenReturn(dayRatesResult);

        List<StayAvailability> result = translator.translate(LOSRateChanges);
        StayAvailabilityUpdateRequest request = createEmptyStayAvailabilityUpdateRequest();
        request.setStayAvailabilityList(result);

        Assert.assertEquals(expected_request,XMLUtils.marshall(request));

    }

    @Test
    public void testCreateNewAvailableStay() {

        String roomTypeCode = "roomTypeCode";
        String channelRoomTypeCode = "channelRoomTypeCode";
        String ratePlanCode = "RatePlanCode";
        String channelRatePlanCode = "channelRatePlanCode";
        Integer los = 3;
        String checkInDate = "2014-10-15";
        StayAvailability.DayRates dayRates = mock(StayAvailability.DayRates.class);

        MappingService mappingService = translator.getMappingService();
        when(mappingService.translateRatePlan(eq(ratePlanCode))).thenReturn(channelRatePlanCode);
        when(mappingService.translateRoomType(eq(roomTypeCode))).thenReturn(channelRoomTypeCode);

        StayAvailability result = translator.createNewAvailableStay(
                roomTypeCode, ratePlanCode, los, checkInDate, dayRates
        );

        Assert.assertEquals(channelRoomTypeCode, result.getRoomTypeCode());
        Assert.assertEquals(channelRatePlanCode, result.getRatePlanCode());
        Assert.assertEquals(BigInteger.valueOf(los), result.getLOS());
        Assert.assertEquals(checkInDate, result.getDateSpan().getEnd());
        Assert.assertEquals(checkInDate, result.getDateSpan().getStart());
        Assert.assertEquals(dayRates, result.getDayRates());
    }


    private List<LOSRateChangeDTO> createLosRateChanges(String currency, List<RatesDTO> cdsRates1, List<RatesDTO> cdsRates3) {

        Integer los = 2;
        String roomType1 = "roomType1";
        String roomType2 = "roomType2";
        String ratePlan = "ratePlan1";
        String checkInDate1 = "2014-06-10";
        String checkInDate2 = "2014-06-11";
        String fplos = "1010";

        MappingService mappingService = translator.getMappingService();
        when(mappingService.translateRatePlan(eq(ratePlan))).thenReturn("channelRatePlan1");
        when(mappingService.translateRoomType(eq(roomType1))).thenReturn("chanelRoomType1");
        when(mappingService.translateRoomType(eq(roomType2))).thenReturn("chanelRoomType2");

        //prepare Data
        List<LOSRateChangeDTO> LOSRateChanges = new ArrayList<LOSRateChangeDTO>();

        //losRateChange1
        List<LOSRateDTO> losRates1 = new ArrayList<LOSRateDTO>();
        LOSRateDTO losRate1 = createMockLOSRate(los, cdsRates1);
        losRates1.add(losRate1);


        LOSRateChangeDTO losRateChange1 = createMockLOSChange(currency,
                roomType1,
                ratePlan,
                checkInDate1,
                fplos,
                losRates1);

        //losRateChange2
        List<LOSRateDTO> losRates2 = new ArrayList<LOSRateDTO>();
        LOSRateDTO losRate3 = createMockLOSRate(los, cdsRates3);
        losRates2.add(losRate3);


        LOSRateChangeDTO losRateChange2 = createMockLOSChange(currency,
                roomType2,
                ratePlan,
                checkInDate2,
                fplos,
                losRates2);

        LOSRateChanges.add(losRateChange1);
        LOSRateChanges.add(losRateChange2);
        return LOSRateChanges;
    }


    public LOSRateDTO createMockLOSRate(int los, List<RatesDTO> ratesDTOs) {
        LOSRateDTO result = new LOSRateDTO();
        result.setLos(los);
        result.setRatesList(ratesDTOs);
        return result;
    }


    public LOSRateChangeDTO createMockLOSChange(String currency, String roomType, String ratePlan,
                                                String checkInDate, String fplos, List<LOSRateDTO> losRates) {
        LOSRateChangeDTO result = new LOSRateChangeDTO();

        result.setRoomTypeCode(roomType);
        result.setRatePlanCode(ratePlan);
        result.setCurrency(currency);
        result.setCheckInDate(checkInDate);
        result.setFplos(fplos);
        result.setLosRatesList(losRates);
        return result;
    }

    public StayAvailabilityUpdateRequest createEmptyStayAvailabilityUpdateRequest() {

        StayAvailabilityUpdateRequest request = new StayAvailabilityUpdateRequest();
        request.setHotelCode("HotelCode");
        request.setPassword("PassWord");
        request.setToken("token");
        request.setUserName("UserName");
        List<StayAvailability> stayAvailabilityList = new ArrayList<StayAvailability>();
        request.setStayAvailabilityList(stayAvailabilityList);
        return request;

    }


    @Before
    public void setup() {

        translator = new StayAvailabilityTranslator();

        DailyRateTranslator dailyRateTranslator = mock(DailyRateTranslator.class);
        translator.setDailyRateTranslator(dailyRateTranslator);

        MappingService mappingService = mock(MappingService.class);
        translator.setMappingService(mappingService);
    }


}

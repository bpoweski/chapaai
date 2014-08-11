package com.derbysoft.chapaai.adapter.pusher.domain.translator.losrate;


import com.derbysoft.chapaai.adapter.pusher.domain.stay.DateSpan;
import com.derbysoft.chapaai.adapter.pusher.domain.stay.DayRate;
import com.derbysoft.chapaai.adapter.pusher.domain.stay.Rate;
import com.derbysoft.chapaai.adapter.pusher.domain.stay.StayAvailability;
import com.derbysoft.chapaai.adapter.pusher.domain.stay.StayAvailabilityUpdateRequest;
import com.derbysoft.chapaai.adapter.pusher.integrationtest.XMLUtils;
import com.derbysoft.dswitch.dto.hotel.cds.OccupancyRateDTO;
import com.derbysoft.dswitch.dto.hotel.cds.RatesDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DailyRateTranslatorTest {

    public static final String CURRENCY = "USD";
    private DailyRateTranslator translator;

    @Test
    public void testTranslator() {
        //Prepare data
        List<RatesDTO> cdsRates = createRatesDTOForTranslate();

        StayAvailabilityUpdateRequest expected_request = createEmptyStayAvailabilityUpdateRequest();
        expected_request.getStayAvailabilityList().get(0).setDayRates(createExpectedDayRatesForTranslate());
        String expected_xml = XMLUtils.marshall(expected_request);

        //execute
        StayAvailability.DayRates result = translator.translate(cdsRates, CURRENCY);
        StayAvailabilityUpdateRequest actual_result = createEmptyStayAvailabilityUpdateRequest();
        actual_result.getStayAvailabilityList().get(0).setDayRates(result);
        String actual_xml = XMLUtils.marshall(actual_result);


        //verify
        Assert.assertEquals(expected_xml,actual_xml);
    }

    private StayAvailability.DayRates createExpectedDayRatesForTranslate() {
        StayAvailability.DayRates result = new StayAvailability.DayRates();
        List<DayRate> dayRates = new ArrayList<DayRate>();

        DayRate dayRate1 = new DayRate();
        dayRate1.setCurrencyCode(CURRENCY);
        dayRate1.setDayPattern("0,1");
        Rate rate1 = new Rate();
        rate1.setAdultCount(BigInteger.valueOf(2));
        rate1.setAfterTaxAmount(3.0);
        rate1.setBeforeTaxAmount(2.0);
        Rate rate2 = new Rate();
        rate2.setAdultCount(BigInteger.valueOf(3));
        rate2.setAfterTaxAmount(6.0);
        rate2.setBeforeTaxAmount(5.0);
        List<Rate> rateList1 = new ArrayList<Rate>();
        rateList1.add(rate1);
        rateList1.add(rate2);
        DayRate.Rates rates1 = new DayRate.Rates();
        rates1.setRateList(rateList1);
        dayRate1.setRates(rates1);


        DayRate dayRate2 = new DayRate();
        dayRate2.setCurrencyCode(CURRENCY);
        dayRate2.setDayPattern("2");
        Rate rate3 = new Rate();
        rate3.setAdultCount(BigInteger.valueOf(2));
        rate3.setAfterTaxAmount(10.0);
        rate3.setBeforeTaxAmount(9.0);
        Rate rate4 = new Rate();
        rate4.setAdultCount(BigInteger.valueOf(3));
        rate4.setAfterTaxAmount(13.0);
        rate4.setBeforeTaxAmount(12.0);
        List<Rate> rateList4 = new ArrayList<Rate>();
        rateList4.add(rate3);
        rateList4.add(rate4);
        DayRate.Rates rates2 = new DayRate.Rates();
        rates2.setRateList(rateList4);
        dayRate2.setRates(rates2);


        dayRates.add(dayRate1);
        dayRates.add(dayRate2);

        result.setDayRateList(dayRates);
        return result;
    }

    private List<RatesDTO> createRatesDTOForTranslate() {
        List<RatesDTO> cdsRates = new ArrayList<RatesDTO>();
        //cdsRate1
        OccupancyRateDTO occupancyRate1 = createMockOccupancyRate(2, 2, 2.0, 3.0);
        OccupancyRateDTO occupancyRate2 = createMockOccupancyRate(3, 3, 5.0, 6.0);
        List<OccupancyRateDTO> occupancyRates1 = new ArrayList<OccupancyRateDTO>();
        occupancyRates1.add(occupancyRate1);
        occupancyRates1.add(occupancyRate2);
        RatesDTO cdsRate1 = createMockRatesDTO(0, 1, occupancyRates1);

        //cdsRate2
        OccupancyRateDTO occupancyRate3 = createMockOccupancyRate(2, 2, 9.0, 10.0);
        OccupancyRateDTO occupancyRate4 = createMockOccupancyRate(3, 3, 12.0, 13.0);

        List<OccupancyRateDTO> occupancyRates2 = new ArrayList<OccupancyRateDTO>();
        occupancyRates2.add(occupancyRate3);
        occupancyRates2.add(occupancyRate4);

        RatesDTO cdsRate2 = createMockRatesDTO(2, 2, occupancyRates2);

        cdsRates.add(cdsRate1);
        cdsRates.add(cdsRate2);
        return cdsRates;
    }



    @Test
    public void testGenerateDayPatternNewOneDay() {
        String old = "";
        int start = 1;
        int end = 1;

        String result = translator.generateDayPattern(old, start, end);
        Assert.assertEquals("1", result);
    }


    @Test
    public void testGenerateDayPatternNewMulitDay() {
        String old = "";
        int start = 1;
        int end = 3;

        String result = translator.generateDayPattern(old, start, end);
        Assert.assertEquals("1,2,3", result);
    }

    @Test
    public void testGenerateDayPatternOldOneDay() {
        String old = "1";
        int start = 2;
        int end = 2;

        String result = translator.generateDayPattern(old, start, end);
        Assert.assertEquals("1,2", result);
    }

    @Test
    public void testGenerateDayPatternOldMulitDay() {
        String old = "1,2";
        int start = 5;
        int end = 8;

        String result = translator.generateDayPattern(old, start, end);
        Assert.assertEquals("1,2,5,6,7,8", result);
    }

    public RatesDTO createMockRatesDTO(int start, int end, List<OccupancyRateDTO> occupancyRates1) {
        RatesDTO result = new RatesDTO();
        result.setStart(start);
        result.setEnd(end);
        result.setOccupancyRatesList(occupancyRates1);
        return result;
    }

    public OccupancyRateDTO createMockOccupancyRate(int adult, int child, double beforeTax, double afterTax) {
        OccupancyRateDTO occupancyRate1 = new OccupancyRateDTO();
        occupancyRate1.setAdult(adult);
        occupancyRate1.setChild(child);
        occupancyRate1.setAmountAfterTax(afterTax);
        occupancyRate1.setAmountBeforeTax(beforeTax);
        return occupancyRate1;
    }


    public StayAvailabilityUpdateRequest createEmptyStayAvailabilityUpdateRequest() {

        StayAvailabilityUpdateRequest request = new StayAvailabilityUpdateRequest();
        request.setHotelCode("HotelCode");
        request.setPassword("PassWord");
        request.setToken("token");
        request.setUserName("UserName");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD");
        StayAvailability stayAvailability1 = new StayAvailability();
        stayAvailability1.setAvailableRooms(BigInteger.valueOf(1));
        stayAvailability1.setClosed(true);
        stayAvailability1.setLOS(BigInteger.valueOf(90));
        stayAvailability1.setRatePlanCode("RatePlan1");
        stayAvailability1.setRoomTypeCode("RoomType1");
        DateSpan dateSpan1 = new DateSpan();
        dateSpan1.setStart("2014-06-19");
        dateSpan1.setEnd("2014-06-20");
        stayAvailability1.setDateSpan(dateSpan1);
        List<DayRate> dayRatesList1 = new ArrayList<DayRate>();
        StayAvailability.DayRates dayRates1 = new StayAvailability.DayRates();
        dayRates1.setDayRateList(dayRatesList1);
        stayAvailability1.setDayRates(dayRates1);

        List<StayAvailability> stayAvailabilityList = new ArrayList<StayAvailability>();
        stayAvailabilityList.add(stayAvailability1);
        request.setStayAvailabilityList(stayAvailabilityList);
        return request;

    }


    @Before
    public void setup() {
        translator = new DailyRateTranslator();
    }
}

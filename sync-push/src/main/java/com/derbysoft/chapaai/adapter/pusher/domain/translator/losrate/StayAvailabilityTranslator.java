package com.derbysoft.chapaai.adapter.pusher.domain.translator.losrate;

import com.derbysoft.chapaai.adapter.pusher.application.service.MappingService;
import com.derbysoft.chapaai.adapter.pusher.domain.stay.DateSpan;
import com.derbysoft.chapaai.adapter.pusher.domain.stay.StayAvailability;
import com.derbysoft.dswitch.dto.hotel.cds.LOSRateChangeDTO;
import com.derbysoft.dswitch.dto.hotel.cds.LOSRateDTO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class StayAvailabilityTranslator {

    private MappingService mappingService;
    private DailyRateTranslator dailyRateTranslator;

    public List<StayAvailability> translate(List<LOSRateChangeDTO> LOSRateChanges) {

        List<StayAvailability> result = new ArrayList<StayAvailability>();

        for (LOSRateChangeDTO losRateChange : LOSRateChanges) {
            for (LOSRateDTO lostRate : losRateChange.getLosRatesList()) {
                result.add(translateStayAvailability(losRateChange, lostRate));
            }
        }

        return result;
    }

    /**
     *
     * @param losRateChange
     * @param lostRate
     */
    public StayAvailability translateStayAvailability(LOSRateChangeDTO losRateChange, LOSRateDTO lostRate) {

        String roomType = losRateChange.getRoomTypeCode();
        String ratePlan = losRateChange.getRatePlanCode();
        String checkInDate = losRateChange.getCheckInDate();
        String currency = losRateChange.getCurrency();
        Integer los = lostRate.getLos();
        StayAvailability.DayRates dayRates = dailyRateTranslator.translate(
                lostRate.getRatesList(), currency
        );
        return createNewAvailableStay(roomType, ratePlan, los, checkInDate, dayRates);

    }


    public StayAvailability createNewAvailableStay(String roomTypeCode, String ratePlanCode, Integer los,
                                                   String checkInDate, StayAvailability.DayRates dayRates) {
        StayAvailability result = new StayAvailability();
        result.setRoomTypeCode(mappingService.translateRoomType(roomTypeCode));
        result.setRatePlanCode(mappingService.translateRatePlan(ratePlanCode));
        result.setLOS(BigInteger.valueOf(los));
        DateSpan span = new DateSpan();
        span.setStart(checkInDate);
        span.setEnd(checkInDate);
        result.setDateSpan(span);
        result.setDayRates(dayRates);

        return result;
    }



    public DailyRateTranslator getDailyRateTranslator() {
        return dailyRateTranslator;
    }

    public void setDailyRateTranslator(DailyRateTranslator dailyRateTranslator) {
        this.dailyRateTranslator = dailyRateTranslator;
    }

    public MappingService getMappingService() {
        return mappingService;
    }

    public void setMappingService(MappingService mappingService) {
        this.mappingService = mappingService;
    }
}

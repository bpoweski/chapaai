package com.derbysoft.chapaai.adapter.pusher.domain.translator.losrate;



import com.derbysoft.chapaai.adapter.pusher.domain.stay.DayRate;
import com.derbysoft.chapaai.adapter.pusher.domain.stay.Rate;
import com.derbysoft.chapaai.adapter.pusher.domain.stay.StayAvailability;
import com.derbysoft.dswitch.dto.hotel.cds.OccupancyRateDTO;
import com.derbysoft.dswitch.dto.hotel.cds.RatesDTO;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DailyRateTranslator {

    public static final String SPLIT_PERIOD = ",";

    public StayAvailability.DayRates translate(List<RatesDTO> losRates, String currency) {

        List<DayRate> dayRates = new ArrayList<DayRate>();

        for (RatesDTO ratesDTO : losRates) {
            DayRate dayRate = new DayRate();
            dayRate.setCurrencyCode(currency);
            dayRate.setRates(translateRate(ratesDTO.getOccupancyRatesList()));
            dayRate.setDayPattern(calculateDayPattern(ratesDTO));
            dayRates.add(dayRate);
        }

        StayAvailability.DayRates result = new StayAvailability.DayRates();
        result.setDayRateList(dayRates);
        return result;
    }

    public DayRate.Rates translateRate(List<OccupancyRateDTO> occupancyRateDTOs) {

        List<Rate> rateList =  new ArrayList<Rate>();
        for (OccupancyRateDTO occupancyRateDTO : occupancyRateDTOs) {
            Rate rate = new Rate();
            rate.setAdultCount(BigInteger.valueOf(occupancyRateDTO.getAdult()));
            rate.setAfterTaxAmount(occupancyRateDTO.getAmountAfterTax());
            rate.setBeforeTaxAmount(occupancyRateDTO.getAmountBeforeTax());
            rateList.add(rate);
        }

        DayRate.Rates rates = new DayRate.Rates();
        rates.setRateList(rateList);
        return rates;

    }

    public String calculateDayPattern(RatesDTO rateDTO){
        StringBuilder result = new StringBuilder();
        int start = rateDTO.getStart();
        int end   = rateDTO.getEnd();
        result.append(start);
        for (int i = start + 1; i <= end; i++) {
            result.append(SPLIT_PERIOD);
            result.append(i);
        }

        return result.toString();

    }

    /*it wll be moved to merge service*/
    @Deprecated
    public void assignDayPattern(DayRate dayRate, RatesDTO cdsRate, HashMap<List<Rate>, DayRate> dayRates) {
        DayRate realDayRate;
        if (dayRates.containsKey(dayRate.getRates())) {
            realDayRate = dayRates.get(dayRate.getRates());
        } else {
            realDayRate = dayRate;
            dayRates.put(dayRate.getRates().getRateList(), dayRate);
        }

        realDayRate.setDayPattern(generateDayPattern(
                realDayRate.getDayPattern(), cdsRate.getStart(), cdsRate.getEnd()
        ));
    }

    /*it wll be moved to merge service*/
    @Deprecated
    public String generateDayPattern(String old, int start, int end) {
        StringBuilder result = new StringBuilder();
        //it's new
        if (!StringUtils.isBlank(old)) {
            result.append(old);
            result.append(SPLIT_PERIOD);
        }

        result.append(start);
        for (int i = start + 1; i <= end; i++) {
            result.append(SPLIT_PERIOD);
            result.append(i);
        }

        return result.toString();
    }

}

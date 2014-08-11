package com.derbysoft.chapaai.adapter.pusher.service.impl;


import com.derbysoft.chapaai.adapter.pusher.service.ChangePushService;
import com.derbysoft.chapaai.adapter.pusher.service.MappingService;
import com.derbysoft.chapaai.adapter.pusher.domain.stay.StayAvailability;
import com.derbysoft.chapaai.adapter.pusher.domain.stay.StayAvailabilityUpdateRequest;
import com.derbysoft.chapaai.adapter.pusher.domain.stay.StayAvailabilityUpdateResponse;
import com.derbysoft.chapaai.adapter.pusher.domain.translator.losrate.StayAvailabilityTranslator;
import com.derbysoft.synchronizer.dto.ChangesDTO;
import com.derbysoft.synchronizer.dto.LOSRateChangesDTO;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.ArrayList;
import java.util.List;

public class StayAvailabilityPushServiceImpl implements ChangePushService {

    private WebServiceTemplate webServiceTemplate;

    private StayAvailabilityTranslator stayAvailabilityTranslator;

    private MappingService mappingService;

    @Override
    public void syncPush(ChangesDTO changes, String hotelCode) {

        LOSRateChangesDTO losRateChangesDTO = changes.getLosRateChanges();
        if (losRateChangesDTO == null) {
            return;
        }

        List<StayAvailability> stayAvailabilities =
                stayAvailabilityTranslator.translate(losRateChangesDTO.getLosRateChangesList());

        List<StayAvailabilityUpdateRequest> stayAvailabilityUpdateRequests
                = composeRequest(stayAvailabilities, mappingService.translateHotelCode(hotelCode));

        pushResult(stayAvailabilityUpdateRequests);
    }

    public void pushResult(List<StayAvailabilityUpdateRequest> stayAvailabilityUpdateRequests) {

        for (StayAvailabilityUpdateRequest request : stayAvailabilityUpdateRequests) {
            try {
                StayAvailabilityUpdateResponse response
                        = (StayAvailabilityUpdateResponse) webServiceTemplate.marshalSendAndReceive(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public List<StayAvailabilityUpdateRequest> composeRequest(List<StayAvailability> stayAvailabilities, String hotelCode) {
        List<StayAvailabilityUpdateRequest> result = new ArrayList<StayAvailabilityUpdateRequest>();

        StayAvailabilityUpdateRequest request = new StayAvailabilityUpdateRequest();
        request.setHotelCode(hotelCode);
        request.setPassword("password");
        request.setToken("token");
        request.setUserName("UserName");
        request.setStayAvailabilityList(stayAvailabilities);
        result.add(request);

        return result;
    }

    public StayAvailabilityTranslator getStayAvailabilityTranslator() {
        return stayAvailabilityTranslator;
    }

    public void setStayAvailabilityTranslator(StayAvailabilityTranslator stayAvailabilityTranslator) {
        this.stayAvailabilityTranslator = stayAvailabilityTranslator;
    }

    public WebServiceTemplate getWebServiceTemplate() {
        return webServiceTemplate;
    }

    public void setWebServiceTemplate(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public MappingService getMappingService() {
        return mappingService;
    }

    public void setMappingService(MappingService mappingService) {
        this.mappingService = mappingService;
    }
}

package com.derbysoft.chapaai.adapter.pusher.application.service;

import com.derbysoft.chapaai.adapter.pusher.application.service.impl.StayAvailabilityPushServiceImpl;
import com.derbysoft.chapaai.adapter.pusher.domain.stay.StayAvailability;
import com.derbysoft.chapaai.adapter.pusher.domain.stay.StayAvailabilityUpdateRequest;
import com.derbysoft.chapaai.adapter.pusher.domain.translator.losrate.StayAvailabilityTranslator;
import com.derbysoft.dswitch.dto.hotel.cds.LOSRateChangeDTO;
import com.derbysoft.synchronizer.dto.ChangesDTO;
import com.derbysoft.synchronizer.dto.LOSRateChangesDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StayAvailabilityPushServiceImplTest {

    private StayAvailabilityPushServiceImpl service;

    static final String HotelCode = "hotelCode";

    @Test
    public void testSyncPushNormal() {
        StayAvailabilityPushServiceImpl spyService = spy(service);
        ChangesDTO changes = new ChangesDTO();
        List<StayAvailabilityUpdateRequest> requests = new ArrayList<StayAvailabilityUpdateRequest>();
        setupMockEnvForSyncPush(spyService, changes, requests);


        spyService.syncPush(changes, HotelCode);
        verify(spyService, times(1)).pushResult(requests);

    }

    private void setupMockEnvForSyncPush(StayAvailabilityPushServiceImpl spyService, ChangesDTO changes, List<StayAvailabilityUpdateRequest> requests) {
        LOSRateChangesDTO losRateChangesDTO = new LOSRateChangesDTO();
        List<LOSRateChangeDTO> changesDTOList = mock(List.class);
        losRateChangesDTO.setLosRateChangesList(changesDTOList);
        changes.setLosRateChanges(losRateChangesDTO);

        List<StayAvailability> stayAvailabilities = mock(List.class);

        when(spyService.getStayAvailabilityTranslator().translate(eq(changesDTOList)))
                .thenReturn(stayAvailabilities);
        MappingService mappingService = spyService.getMappingService();
        when(mappingService.translateHotelCode(HotelCode)).thenReturn(HotelCode);
        when(spyService.composeRequest(stayAvailabilities, HotelCode)).thenReturn(requests);


    }

    @Test
    public void testSyncPushEmpty() {
        StayAvailabilityPushServiceImpl spyService = spy(service);
        ChangesDTO changes = new ChangesDTO();
        List<StayAvailabilityUpdateRequest> requests = new ArrayList<StayAvailabilityUpdateRequest>();
        setupMockEnvForSyncPush(spyService, changes, requests);
        changes.setLosRateChanges(null);

        spyService.syncPush(changes, HotelCode);
        verify(spyService, never()).pushResult(requests);
    }

    @Test
    public void testPushResult() {
        List<StayAvailabilityUpdateRequest> stayAvailabilityUpdateRequests
                = new ArrayList<StayAvailabilityUpdateRequest>();
        for (int i = 0; i < 10; i++) {
            stayAvailabilityUpdateRequests.add(mock(StayAvailabilityUpdateRequest.class));
        }

        service.pushResult(stayAvailabilityUpdateRequests);


        WebServiceTemplate webServiceTemplate = service.getWebServiceTemplate();

        for (int i = 0; i < 10; i++) {
            verify(webServiceTemplate, times(1)).marshalSendAndReceive(
                    stayAvailabilityUpdateRequests.get(i)
            );
        }

        verify(webServiceTemplate, times(10)).marshalSendAndReceive(any(
                StayAvailabilityUpdateRequest.class
        ));
    }


    @Before
    public void setup() {
        service = new StayAvailabilityPushServiceImpl();
        StayAvailabilityTranslator translator = mock(StayAvailabilityTranslator.class);
        WebServiceTemplate webServiceTemplate = mock(WebServiceTemplate.class);
        MappingService mappingService = mock(MappingService.class);



        service.setStayAvailabilityTranslator(translator);
        service.setWebServiceTemplate(webServiceTemplate);
        service.setMappingService(mappingService);
    }
}

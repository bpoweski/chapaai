package com.derbysoft.chapaai.adapter.pusher.domain.repository;


import com.derbysoft.chapaai.adapter.pusher.domain.exception.GetChangeException;
import com.derbysoft.chapaai.adapter.pusher.domain.model.Hotel;
import com.derbysoft.chapaai.adapter.pusher.domain.repository.impl.ChangeRepositoryImpl;
import com.derbysoft.dswitch.dto.common.ErrorDTO;
import com.derbysoft.synchronizer.dto.ChangesDTO;
import com.derbysoft.synchronizer.dto.DeleteKeysRS;
import com.derbysoft.synchronizer.dto.GetChangesRS;
import com.derbysoft.synchronizer.dto.GetKeysRS;
import com.derbysoft.synchronizer.dto.HotelKeysDTO;
import com.derbysoft.synchronizer.dto.KeyTokenDTO;
import com.derbysoft.synchronizer.dto.Level;
import com.derbysoft.synchronizer.remote.buyer.BuyerSynchronizerRemoteService;
import com.derbysoft.synchronizer.remote.dto.DeleteKeysRequest;
import com.derbysoft.synchronizer.remote.dto.DeleteKeysResponse;
import com.derbysoft.synchronizer.remote.dto.GetChangesRequest;
import com.derbysoft.synchronizer.remote.dto.GetChangesResponse;
import com.derbysoft.synchronizer.remote.dto.GetKeysRequest;
import com.derbysoft.synchronizer.remote.dto.GetKeysResponse;
import org.easymock.Capture;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

public class ChangeRepositoryImplTest {

    public static final String PROVIDER = "PROVIDER";
    public static final String HOTEL = "Hotel1";
    public static final String KEY_1 = "Key1";
    public static final String CHANNEL = "CHANNEL";
    public static final List<Hotel> HOTELS = Arrays.asList(new Hotel(PROVIDER,HOTEL));
    private Capture<GetKeysRequest> getKeysRequestCapture = new Capture<GetKeysRequest>();
    private Capture<GetChangesRequest> getChangesRequestCapture = new Capture<GetChangesRequest>();
    private Capture<DeleteKeysRequest> deleteKeysRequestCapture = new Capture<DeleteKeysRequest>();

    @Test
    public void getKeysSuccess() throws Exception {
        List<HotelKeysDTO> keys = initServiceForGetKeys(new GetKeysResponse(null, fakeGetKeysRS()), false).getKeys(HOTELS);
        Assert.assertEquals(CHANNEL, getKeysRequestCapture.getValue().getHeader().getSource());
        Assert.assertEquals(PROVIDER, getKeysRequestCapture.getValue().getHeader().getDestination());
        Assert.assertNotNull(getKeysRequestCapture.getValue().getHeader().getTaskId());
        Assert.assertEquals(Level.RATE, getKeysRequestCapture.getValue().getGetKeysRQ().getLevel());
        Assert.assertTrue(getKeysRequestCapture.getValue().getGetKeysRQ().getHotelsList().contains(HOTEL));
        List<HotelKeysDTO> expectedResult = Arrays.asList(fakeHotelKeysDTO());
        AssertEqual(keys, expectedResult);
    }

    private void AssertEqual(List<HotelKeysDTO> keys, List<HotelKeysDTO> expectedResult) {
        Assert.assertEquals(expectedResult.size(), keys.size());
        Assert.assertEquals(keys.get(0).getHotel(),expectedResult.get(0).getHotel());
        Assert.assertEquals(keys.get(0).getKeysList(),expectedResult.get(0).getKeysList());
    }

    @Test(expected = GetChangeException.class)
    public void getKeysError() throws Exception {
        initServiceForGetKeys(new GetKeysResponse(null, fakeGetKeysRS()), true).getKeys(HOTELS);

    }

    @Test
    public void getChangeSuccess() {
        List<ChangesDTO> expectedList = createMock(List.class);
        HotelKeysDTO hotelKeysDTO = new HotelKeysDTO();
        List<ChangesDTO> actualResult = initServiceForGetChange(new GetChangesResponse(null, fakeGetChangeRS(expectedList)), false).getChanges(PROVIDER, hotelKeysDTO);

        Assert.assertEquals(expectedList, actualResult);

    }

    @Test(expected = GetChangeException.class)
    public void getChangeFail() {
        List<ChangesDTO> expectedList = createMock(List.class);
        HotelKeysDTO hotelKeysDTO = new HotelKeysDTO();
        initServiceForGetChange(new GetChangesResponse(null, fakeGetChangeRS(expectedList)), true).getChanges(PROVIDER, hotelKeysDTO);
    }

    @Test
    public void deleteKeysSuccess() {
        initServiceForDeleteKeys(new DeleteKeysResponse(null, new DeleteKeysRS()), false).deleteChanges(PROVIDER,HOTEL,new ArrayList<KeyTokenDTO>());
    }

    @Test(expected = GetChangeException.class)
    public void deleteKeysFail() {
        initServiceForDeleteKeys(new DeleteKeysResponse(null, new DeleteKeysRS()), true).deleteChanges(PROVIDER,HOTEL,new ArrayList<KeyTokenDTO>());
    }

    private GetChangesRS fakeGetChangeRS(List<ChangesDTO> expectedList) {
        GetChangesRS getChangesRS = new GetChangesRS();
        getChangesRS.setChangesList(expectedList);
        return getChangesRS;
    }


    private GetKeysRS fakeGetKeysRS() {
        GetKeysRS getKeysRS = new GetKeysRS();
        getKeysRS.setHotelKeysList(Arrays.asList(fakeHotelKeysDTO()));
        return getKeysRS;
    }

    private HotelKeysDTO fakeHotelKeysDTO() {
        HotelKeysDTO hotelKeysDTO = new HotelKeysDTO();
        hotelKeysDTO.setHotel(HOTEL);
        hotelKeysDTO.setKeysList(Arrays.asList(KEY_1));
        return hotelKeysDTO;
    }


    private ChangeRepositoryImpl initServiceForGetKeys(GetKeysResponse response, boolean hasError) {
        ChangeRepositoryImpl changeRepository = new ChangeRepositoryImpl();
        BuyerSynchronizerRemoteService synchronizeChangeService = createMock(BuyerSynchronizerRemoteService.class);

        if (hasError) {
            response.setError(new ErrorDTO());
        }

        expect(synchronizeChangeService.getKeys(capture(getKeysRequestCapture))).andReturn(response);
        replay(synchronizeChangeService);
        changeRepository.setSynchronizeChangeService(synchronizeChangeService);
        changeRepository.setChannel(CHANNEL);
        return changeRepository;
    }

    private ChangeRepositoryImpl initServiceForGetChange(GetChangesResponse response, boolean hasError) {
        ChangeRepositoryImpl changeRepository = new ChangeRepositoryImpl();
        BuyerSynchronizerRemoteService synchronizeChangeService = createMock(BuyerSynchronizerRemoteService.class);

        if (hasError) {
            response.setError(new ErrorDTO());
        }

        expect(synchronizeChangeService.getChanges(capture(getChangesRequestCapture))).andReturn(response);
        replay(synchronizeChangeService);
        changeRepository.setSynchronizeChangeService(synchronizeChangeService);
        changeRepository.setChannel(CHANNEL);
        return changeRepository;
    }

    private ChangeRepositoryImpl initServiceForDeleteKeys(DeleteKeysResponse response, boolean hasError) {
        ChangeRepositoryImpl changeRepository = new ChangeRepositoryImpl();
        BuyerSynchronizerRemoteService synchronizeChangeService = createMock(BuyerSynchronizerRemoteService.class);

        if (hasError) {
            response.setError(new ErrorDTO());
        }

        expect(synchronizeChangeService.deleteKeys(capture(deleteKeysRequestCapture))).andReturn(response);
        replay(synchronizeChangeService);
        changeRepository.setSynchronizeChangeService(synchronizeChangeService);
        changeRepository.setChannel(CHANNEL);
        return changeRepository;
    }

}

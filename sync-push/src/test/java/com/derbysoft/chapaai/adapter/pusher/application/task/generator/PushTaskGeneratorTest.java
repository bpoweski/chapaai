package com.derbysoft.chapaai.adapter.pusher.application.task.generator;

import com.derbysoft.chapaai.adapter.pusher.application.task.PushTask;
import com.derbysoft.chapaai.adapter.pusher.domain.model.Hotel;
import com.derbysoft.chapaai.adapter.pusher.domain.repository.ChangeRepository;
import com.derbysoft.chapaai.adapter.pusher.domain.repository.HotelRepository;
import com.derbysoft.synchronizer.dto.HotelKeysDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class PushTaskGeneratorTest {

    public static final String HOTEL_1 = "HOTELS1";
    public static final String HOTEL_2 = "HOTELS2";
    public static final List<String> KEYS_1 = Arrays.asList("1");
    public static final List<String> KEYS_2 = Arrays.asList("2");
    private PushTaskGenerator generator;


    @Test
    public void testPushGenerateSuccessful() {
        String providerCode = "Provider";
        HotelRepository repository = generator.getHotelRepository();
        ChangeRepository changeRepository = generator.getChangeRepository();
        PushTaskGenerator spyGenerator = spy(generator);
        List<PushTask> expectResult = mock(List.class);

        List<Hotel> hotels = new ArrayList<Hotel>();
        List<HotelKeysDTO> hotelKeysDTOs = new ArrayList<HotelKeysDTO>();
        when(repository.getHotel(eq(providerCode), eq(false))).thenReturn(hotels);
        when(changeRepository.getKeys(eq(hotels))).thenReturn(hotelKeysDTOs);
        when(spyGenerator.getPushTasks(providerCode, hotelKeysDTOs)).thenReturn(expectResult);

        List<PushTask> actualResult = spyGenerator.pushGenerate(providerCode);

        Assert.assertEquals(expectResult, actualResult);
    }

    @Test
    public void testPushGenerateError() {
        String providerCode = "Provider";
        HotelRepository repository = generator.getHotelRepository();
        PushTaskGenerator spyGenerator = spy(generator);

        when(repository.getHotel(eq(providerCode), eq(false))).thenThrow(new RuntimeException());
        List<PushTask> actualResult = spyGenerator.pushGenerate(providerCode);

        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void testGetPushTasks() {
        List<Hotel> hotels = new ArrayList<Hotel>();
        String providerCode = "Provider";
        Hotel hotel1 = new Hotel();
        hotel1.setProviderHotelCode(HOTEL_1);
        Hotel hotel2 = new Hotel();
        hotel2.setProviderHotelCode(HOTEL_2);
        hotels.add(hotel1);
        hotels.add(hotel2);

        List<PushTask> expectedResult = new ArrayList<PushTask>();

        setupEnvForGetPushTasks(providerCode, expectedResult);

        List<PushTask> actualResult = generator.getPushTasks(providerCode,
                Arrays.asList(
                        createHotelKeys(HOTEL_1, KEYS_1),
                        createHotelKeys(HOTEL_2, KEYS_2)
                ));

        AssertEqual(expectedResult, actualResult);

    }

    private void AssertEqual(List<PushTask> expectedResult, List<PushTask> actualResult) {
        Assert.assertEquals(expectedResult.size(), actualResult.size());
        Assert.assertEquals(expectedResult.get(0).getHotelKeys().getKeysList(),
                actualResult.get(0).getHotelKeys().getKeysList());
        Assert.assertEquals(expectedResult.get(0).getHotelKeys().getHotel(),
                actualResult.get(0).getHotelKeys().getHotel());
        Assert.assertEquals(expectedResult.get(1).getHotelKeys().getKeysList(),
                actualResult.get(1).getHotelKeys().getKeysList());
        Assert.assertEquals(expectedResult.get(1).getHotelKeys().getHotel(),
                actualResult.get(1).getHotelKeys().getHotel());
        Assert.assertEquals(expectedResult.get(0).getProviderCode(),
                actualResult.get(0).getProviderCode());
        Assert.assertEquals(expectedResult.get(1).getProviderCode(),
                actualResult.get(1).getProviderCode());
    }

    private void setupEnvForGetPushTasks(String providerCode, List<PushTask> expectedResult) {
        PushTask task1 = new PushTask(providerCode, createHotelKeys(HOTEL_1, KEYS_1));
        PushTask task2 = new PushTask(providerCode, createHotelKeys(HOTEL_2, KEYS_2));
        expectedResult.add(task1);
        expectedResult.add(task2);
    }

    private HotelKeysDTO createHotelKeys(String hotel, List<String> keys) {
        HotelKeysDTO hotelKeysDTO = new HotelKeysDTO(hotel);
        hotelKeysDTO.setKeysList(keys);
        return hotelKeysDTO;
    }

    @Before
    public void setup() {
        generator = new PushTaskGenerator();
        HotelRepository repository = mock(HotelRepository.class);
        ChangeRepository changeRepository = mock(ChangeRepository.class);

        generator.setHotelRepository(repository);
        generator.setChangeRepository(changeRepository);
    }
}

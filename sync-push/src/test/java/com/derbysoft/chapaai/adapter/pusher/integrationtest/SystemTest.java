package com.derbysoft.chapaai.adapter.pusher.integrationtest;

import com.derbysoft.chapaai.adapter.pusher.timer.PushTimer;
import com.derbysoft.chapaai.adapter.pusher.domain.model.Hotel;
import com.derbysoft.chapaai.adapter.pusher.domain.model.Provider;
import com.derbysoft.chapaai.adapter.pusher.repository.HotelRepository;
import com.derbysoft.chapaai.adapter.pusher.repository.ProviderRepository;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.test.client.MockWebServiceServer;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring_system_test_application.xml")
public class SystemTest extends AbstractJUnit4SpringContextTests {

    public static final String PROVIDER_1 = "Provider1";
    public static final String HOTEL_1 = "Hotel1";

    @Resource(name = "PushTimer")
    private PushTimer pushTimer;

    @Resource(name="ProviderRepository")
    private ProviderRepository providerRepository;

    @Resource(name="hotelRepository")
    private HotelRepository hotelRepository;

    @Resource(name="webServiceTemplate")
    private WebServiceTemplate webServiceTemplate;

    private MockWebServiceServer mockServer;


    @Before
    public void setup(){
        //mockServer = MockWebServiceServer.createServer(webServiceTemplate);
    }

    @Test
    public void test() throws InterruptedException {
        pushTimer.start();

        Thread.sleep(3000 * 1000);
    }

    @Test
    public void setupData() {
        Hotel hotel = new Hotel();
        hotel.setDisabled(false);
        hotel.setProviderCode(PROVIDER_1);
        hotel.setProviderHotelCode(HOTEL_1);
        //hotelRepository.save(hotel);

        Provider provider = new Provider();
        provider.setDisabled(false);
        provider.setProviderCode(PROVIDER_1);
        provider.setPushStayAvailability(true);
        provider.setPushThreadPoolSize(5);
        providerRepository.save(provider);
    }


}

package pl.iseebugs.joboffers.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import pl.iseebugs.joboffers.BaseIntegrationTest;
import pl.iseebugs.joboffers.JobOffersApplication;
import pl.iseebugs.joboffers.domain.offersfetcher.OffersFetcherFacade;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = JobOffersApplication.class, properties = "scheduling.enabled=true")
public class HttpOffersSchedulerTest extends BaseIntegrationTest {

    @SpyBean
    OffersFetcherFacade offersFetcherFacade;

    @Test
    public void should_run_scheduler_given_times(){
         await()
                .atMost(Duration.ofSeconds(2))
                .untilAsserted(() -> verify(offersFetcherFacade, times(2)).onScheduleFetchAllOffersAndSaveAllIfNotExists());
    }
}

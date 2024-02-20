package pl.iseebugs.JobOffers.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import pl.iseebugs.JobOffers.BaseIntegrationTest;
import pl.iseebugs.JobOffers.JobOffersApplication;
import pl.iseebugs.JobOffers.domain.offersFetcher.OffersFetcherFacade;
import pl.iseebugs.JobOffers.domain.scheduler.SchedulerFacade;

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

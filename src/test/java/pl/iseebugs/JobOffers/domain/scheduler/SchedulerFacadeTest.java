package pl.iseebugs.JobOffers.domain.scheduler;

import org.junit.jupiter.api.Test;
import pl.iseebugs.JobOffers.AdjustableClock;
import pl.iseebugs.JobOffers.projection.OfferReadModel;
import pl.iseebugs.JobOffers.domain.offersFetcher.OffersFetcherFacade;
import pl.iseebugs.JobOffers.infrastructure.security.cacheManager.CacheManagerFacade;

import java.time.*;
import java.util.List;

import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SchedulerFacadeTest {

   static AdjustableClock clock = new AdjustableClock(LocalDateTime.of(2024, 2, 6, 10, 0, 0).toInstant(UTC), ZoneId.systemDefault());

    @Test
    void getAll_time_over_one_hour(){
        //given
        SchedulerFacade toTest = SchedulerFacadeTestConfiguration();
        toTest.getAll();
        //when
        clock.advanceInTimeBy(Duration.ofHours(2));
        List<OfferReadModel> result = toTest.getAll();
        //then
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    void getAll_time_under_one_hour_result_received_by_cache(){
        //given
        SchedulerFacade toTest = SchedulerFacadeTestConfiguration();
        toTest.getAll();
        //when
        clock.advanceInTimeBy(Duration.ofMinutes(35));
        List<OfferReadModel> result = toTest.getAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void eventListener_is_called_when_scheduler_starts(){
        //given
        SchedulerFacade toTest = SchedulerFacadeTestConfiguration();
        //then
        verify(toTest.fetchListener, times(1)).onScheduleFetchAllOffersAndSaveAllIfNotExists();
    }

    private static SchedulerFacade SchedulerFacadeTestConfiguration() {
        SchedulerRepository mockInMemoryRepository =mock(SchedulerRepository.class);
        when(mockInMemoryRepository.getAll()).thenReturn(List.of(
                OfferSchedulerEntity.builder()
                        .id("Repository_id_1")
                        .url("url_3")
                        .build(),
                OfferSchedulerEntity.builder()
                        .id("Repository_id_2")
                        .url("url_4")
                        .build(),
                OfferSchedulerEntity.builder()
                        .id("Repository_id_3")
                        .url("url_5")
                        .build()
        ));
        CacheManagerFacade mockCacheManagerFacade = mock(CacheManagerFacade.class);
        when(mockCacheManagerFacade.getAll()).thenReturn(List.of(
                OfferReadModel.builder()
                        .id("Cache_id_1")
                        .url("url_1")
                        .build(),
                OfferReadModel.builder()
                        .id("Cache_id_2")
                        .url("url_2")
                        .build()
        ));
        SchedulerFetchListener mockFetchListener = mock(OffersFetcherFacade.class);

        return SchedulerConfiguration.toSchedulerFacadeToTest(
                mockInMemoryRepository,
                mockCacheManagerFacade,
                clock,
                mockFetchListener);
    }

    
}
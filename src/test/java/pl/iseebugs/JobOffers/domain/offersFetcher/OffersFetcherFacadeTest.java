package pl.iseebugs.JobOffers.domain.offersFetcher;

import org.junit.jupiter.api.Test;
import pl.iseebugs.JobOffers.domain.offers.projection.OfferWriteModel;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OffersFetcherFacadeTest {

    @Test
    void should_return_list_with_additional_offers_when_exist_new_offers(){
        //given
        FetcherRepository fetcherRepository = new InMemoryOffersFetcherTestImpl();
        fetcherRepository.saveOffer(OfferFetch.builder().url("foo_url").build());
        fetcherRepository.saveOffer(OfferFetch.builder().url("bar_url").build());
        OffersFetchable mockOffersFetchable = mock(OffersFetchable.class);
        when(mockOffersFetchable.getOffers()).thenReturn(List.of(
                OfferWriteModel.builder()
                        .url("external_foo").build(),
                OfferWriteModel.builder()
                        .url("external_bar").build(),
                OfferWriteModel.builder()
                        .url("foo_url").build()));
        OffersFetcherFacade toTest = FetcherConfiguration.offersFetcherFacade(fetcherRepository, mockOffersFetchable);
        //when
        toTest.onScheduleFetchAllOffersAndSaveAllIfNotExists();
        List<OfferFetch> result = fetcherRepository.getAll();
        //then
        assertThat(result.size()).isEqualTo(4);
    }
}
package pl.iseebugs.JobOffers.domain.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import pl.iseebugs.JobOffers.domain.offers.projection.OffersReadModel;
import pl.iseebugs.JobOffers.domain.offers.projection.OffersWriteModel;


class OffersFacadeTest {

    @Test
    void save_should_throw_IllegalArgumentException_when_url_already_exists() {
        //given
        OffersRepository mockRepository = mock(OffersRepository.class);
        when(mockRepository.existsByUrl(anyString())).thenReturn(true);
        IdGenerable mockGenerator = mock(IdGenerable.class);
        OffersFacade toTest = OffersConfiguration.offersFacade(mockRepository,mockGenerator);
        OffersWriteModel toSave = OffersWriteModel.builder()
                .url("123")
                .build();
        //when
        var exception = catchThrowable(() -> toTest.save(toSave));
        //then
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Offer with that url already exists");
    }

    @Test
    void save_should_throw_IllegalArgumentException_when_id_already_exists(){
        //given
        OffersRepository mockRepository = mock(OffersRepository.class);
        when(mockRepository.existsByUrl(anyString())).thenReturn(false);
        when(mockRepository.existsById(anyString())).thenReturn(true);
        IdGenerable mockGenerator = mock(IdGenerable.class);
        OffersFacade toTest = OffersConfiguration.offersFacade(mockRepository,mockGenerator);
        OffersWriteModel toSave = OffersWriteModel.builder()
                .url("123")
                .id("123")
                .build();
        //when
        var exception = catchThrowable(() -> toTest.save(toSave));
        //then
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Offer with that Id already exists");
    }

    @Test
    void save_should_saves_object(){
        //given
        OffersRepository repository = new InMemoryRepositoryOffersTestImpl();
        IdGenerable generator = new IdGeneratorUUID();
        OffersFacade offersFacade = OffersConfiguration.offersFacade(repository,generator);
        OffersWriteModel toSave = OffersWriteModel.builder()
                .url("foo")
                .jobPosition("bar")
                .salaryLowerBound(2500.5d)
                .salaryUpperBound(10000.53d)
                .build();
        //when
        OffersReadModel saved = offersFacade.save(toSave);
        //then
        assertThat(saved.getUrl()).isEqualTo(toSave.getUrl());
        assertThat(saved.getJobPosition()).isEqualTo(toSave.getJobPosition());
        assertThat(saved.getSalaryLowerBound()).isEqualTo(toSave.getSalaryLowerBound());
        assertThat(saved.getSalaryUpperBound()).isEqualTo(toSave.getSalaryUpperBound());
    }
}
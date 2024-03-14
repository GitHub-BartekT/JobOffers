package pl.iseebugs.joboffers.domain.offers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import pl.iseebugs.joboffers.projection.OfferReadModel;
import pl.iseebugs.joboffers.projection.OfferWriteModel;
import pl.iseebugs.joboffers.domain.scheduler.SchedulerFacade;

import java.util.List;


class OffersFacadeTest {

    @Test
    void getOffer_should_returns_when_ok() throws OfferNotFoundException {
        //given
        OffersRepository offersRepository = new InMemoryRepositoryOffersTestImpl();
        IdGenerable mockIdGenerator = mock(IdGenerable.class);
        SchedulerFacade mockSchedulerFacade = mock(SchedulerFacade.class);
        when(mockIdGenerator.createNewId()).thenReturn("foo");
        OfferWriteModel toSave = OfferWriteModel.builder()
                .url("bar")
                .jobPosition("foobar")
                .salaryLowerBound(2500.5d)
                .salaryUpperBound(10000.53d)
                .build();
        //System Under Test
        OffersFacade toTest = OffersConfiguration.offersFacade(offersRepository, mockIdGenerator, mockSchedulerFacade);
        //when
        toTest.save(toSave);
        OfferReadModel readOffer = toTest.getOffer("foo");
        //then
        assertThat(readOffer.getId()).isEqualTo("foo");
        assertThat(readOffer.getUrl()).isEqualTo(toSave.getUrl());
        assertThat(readOffer.getJobPosition()).isEqualTo(toSave.getJobPosition());
        assertThat(readOffer.getSalaryLowerBound()).isEqualTo(toSave.getSalaryLowerBound());
        assertThat(readOffer.getSalaryUpperBound()).isEqualTo(toSave.getSalaryUpperBound());
    }

    @Test
    void getOffer_should_throws_OfferNotFoundException_when_id_not_found(){
        //given
        OffersRepository mockRepository = mock(OffersRepository.class);
        when(mockRepository.existsById(anyString())).thenReturn(false);
        IdGenerable mockGenerator = mock(IdGenerable.class);
        SchedulerFacade mockSchedulerFacade = mock(SchedulerFacade.class);
        String id = "foo";
        //System Under Test
        OffersFacade toTest = OffersConfiguration.offersFacade(mockRepository, mockGenerator, mockSchedulerFacade);
        //when
        var exception = catchThrowable(() ->toTest.getOffer(id));
        //then
        assertThat(exception).isInstanceOf(OfferNotFoundException.class);
    }

    @Test
    void getAll_return_list_when_objects_in_repository(){
        //given
        OffersRepository inMemoryRepositoryOffersTest = new InMemoryRepositoryOffersTestImpl();
        IdGenerable idGenerator = new IdGeneratorUUID();
        SchedulerFacade mockSchedulerFacade = mock(SchedulerFacade.class);
        when(mockSchedulerFacade.getAll()).thenReturn(List.of(
                OfferReadModel.builder()
                    .url("foo")
                    .build(),
                OfferReadModel.builder()
                    .url("bar")
                    .build())
        );
        //System Under Test
        OffersFacade toTest = OffersConfiguration.offersFacade(inMemoryRepositoryOffersTest, idGenerator, mockSchedulerFacade);
        //when
        List<OfferReadModel> result = toTest.getAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.stream()
                .map(OfferReadModel::getUrl)
                .toList()).containsExactlyInAnyOrder("foo","bar");
    }

    @Test
    void save_should_throw_IllegalArgumentException_when_url_already_exists() {
        //given
        OffersRepository mockRepository = mock(OffersRepository.class);
        when(mockRepository.existsByUrl(anyString())).thenReturn(true);
        IdGenerable mockGenerator = mock(IdGenerable.class);
        SchedulerFacade mockSchedulerFacade = mock(SchedulerFacade.class);
        OfferWriteModel toSave = OfferWriteModel.builder()
                .url("123")
                .build();
        //System Under Test
        OffersFacade toTest = OffersConfiguration.offersFacade(mockRepository, mockGenerator, mockSchedulerFacade);
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
        IdGenerable generator = new IdGeneratorUUID();
        SchedulerFacade mockSchedulerFacade = mock(SchedulerFacade.class);
        OfferWriteModel toSave = OfferWriteModel.builder()
                .url("123")
                .id("123")
                .companyName("foo Company")
                .jobPosition("bar position")
                .build();
        //System Under Test
        OffersFacade toTest = OffersConfiguration.offersFacade(mockRepository, generator, mockSchedulerFacade);
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
        SchedulerFacade mockSchedulerFacade = mock(SchedulerFacade.class);
        OfferWriteModel toSave = OfferWriteModel.builder()
                .url("foo")
                .jobPosition("bar")
                .salaryLowerBound(2500.5d)
                .salaryUpperBound(10000.53d)
                .build();
        //System Under Test
        OffersFacade offersFacade = OffersConfiguration.offersFacade(repository, generator, mockSchedulerFacade);
        //when
        OfferReadModel saved = offersFacade.save(toSave);
        //then
        assertThat(saved.getUrl()).isEqualTo(toSave.getUrl());
        assertThat(saved.getJobPosition()).isEqualTo(toSave.getJobPosition());
        assertThat(saved.getSalaryLowerBound()).isEqualTo(toSave.getSalaryLowerBound());
        assertThat(saved.getSalaryUpperBound()).isEqualTo(toSave.getSalaryUpperBound());
    }
}
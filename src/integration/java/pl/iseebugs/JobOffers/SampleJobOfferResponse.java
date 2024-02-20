package pl.iseebugs.JobOffers;

public interface SampleJobOfferResponse {
    default String bodyWithZeroOffersJson(){
      return "[]";
    }

    default String bodyWithOneOffersJson(){
        return "[{\"title\":\"Junior Java Developer\",\"company\":\"JSoft Sp. z o.o.\",\"salary\":\"7 000 – 9 000 PLN\",\"offerUrl\":\"https://nosite.com/pl/job/junior-java-developer\"}]";
    }
}

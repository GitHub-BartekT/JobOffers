package pl.iseebugs.JobOffers;

public interface SampleJobOfferResponse {
    default String bodyWithZeroOffersJson(){
      return "[]";
    }
}

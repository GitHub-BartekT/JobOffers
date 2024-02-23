package pl.iseebugs.JobOffers.domain.offers;

public class OfferNotFoundException extends RuntimeException{
    public OfferNotFoundException() {super("Offer not found.");}
    public OfferNotFoundException(String offerId) {super("Offer with id " + offerId + " not found");}
}

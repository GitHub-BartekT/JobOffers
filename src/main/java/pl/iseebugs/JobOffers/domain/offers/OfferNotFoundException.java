package pl.iseebugs.JobOffers.domain.offers;

public class OfferNotFoundException extends Exception{
    public OfferNotFoundException() {super("Offer not found.");}
    public OfferNotFoundException(String message) {super(message);}
}

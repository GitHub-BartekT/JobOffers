package pl.iseebugs.JobOffers.domain.offers;

public class OfferNotFoundException extends Exception{
    public OfferNotFoundException() {super("User Cost not found.");}
    public OfferNotFoundException(String message) {super(message);}
}

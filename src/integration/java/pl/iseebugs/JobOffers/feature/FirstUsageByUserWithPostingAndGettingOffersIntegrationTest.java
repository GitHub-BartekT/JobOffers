package pl.iseebugs.JobOffers.feature;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.iseebugs.JobOffers.BaseIntegrationTest;
import pl.iseebugs.JobOffers.domain.offers.projection.OfferWriteModel;
import pl.iseebugs.JobOffers.domain.offersFetcher.OffersFetchable;

import java.util.List;

public class FirstUsageByUserWithPostingAndGettingOffersIntegrationTest extends BaseIntegrationTest {

    @Autowired
    OffersFetchable offersFetchable;

    @Test
    void should_user_register_post_get_offers(){

        List<OfferWriteModel> result = offersFetchable.getOffers();

//   Step 1: There are no offers on in external HTTP server(http://ec2-3-120-147-150.eu-central-1.compute.amazonaws.com:5057/offers).
//   Step 2: Scheduler ran 1st time and made GET to external server and system add 0 offers to database.
//   Step 3: User tried to get JWT token by requesting POST /token with username-someUser, password=somePassword and system returned UNAUTHORIZED(401)
//   Step 4: User made GET /offers with no jwt token and system returned UNAUTHORIZED(401)
//   Step 5: user made POST /register with username=someUser, password=somePassword and system registered user with status OK(200)
//   Step 6: user tried to get JWT token by requesting POST /token with username=someUser, password=somePassword and system returned OK(200) and jwttoken=AAAA.BBBB.CCC
//   Step 7: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 0 offers
//   Step 8: there are 2 new offers in external HTTP server
//   Step 9: scheduler ran 2nd time and made GET to external server and system added 2 new offers with ids: 1000 and 2000 to database
//   Step 10: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 2 offers with ids: 1000 and 2000
//   Step 11: user made GET /offers/9999 and system returned NOT_FOUND(404) with message “Offer with id 9999 not found”
//   Step 12: user made GET /offers/1000 and system returned OK(200) with offer
//   Step 13: there are 2 new offers in external HTTP server
//   Step 14: scheduler ran 3rd time and made GET to external server and system added 2 new offers with ids: 3000 and 4000 to database
//   Step 15: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 4 offers with ids: 1000,2000, 3000 and 4000

    }
//   Step 1: There are no offers on in external HTTP server.
//   Step 2: Scheduler ran 1st time and made GET to external server and system add 0 offers to database
//   Step 3: User tried to get JWT token by requesting POST /token with username-someUser, password=somePassword and system returned UNAUTHORIZED(401)
//   Step 4: User made GET /offers with no jwt token and system returned UNAUTHORIZED(401)
//   Step 5: user made POST /register with username=someUser, password=somePassword and system registered user with status OK(200)
//   Step 6: user tried to get JWT token by requesting POST /token with username=someUser, password=somePassword and system returned OK(200) and jwttoken=AAAA.BBBB.CCC
//   Step 7: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 0 offers
//   Step 8: there are 2 new offers in external HTTP server
//   Step 9: scheduler ran 2nd time and made GET to external server and system added 2 new offers with ids: 1000 and 2000 to database
//   Step 10: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 2 offers with ids: 1000 and 2000
//   Step 11: user made GET /offers/9999 and system returned NOT_FOUND(404) with message “Offer with id 9999 not found”
//   Step 12: user made GET /offers/1000 and system returned OK(200) with offer
//   Step 13: there are 2 new offers in external HTTP server
//   Step 14: scheduler ran 3rd time and made GET to external server and system added 2 new offers with ids: 3000 and 4000 to database
//   Step 15: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 4 offers with ids: 1000,2000, 3000 and 4000

}

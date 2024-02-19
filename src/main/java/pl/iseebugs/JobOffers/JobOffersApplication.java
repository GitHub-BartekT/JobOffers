package pl.iseebugs.JobOffers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.iseebugs.JobOffers.infrastructure.security.http.offersAWS.OfferAWSFetcherClientProperties;

@SpringBootApplication
@EnableConfigurationProperties({OfferAWSFetcherClientProperties.class})
public class JobOffersApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobOffersApplication.class, args);
    }

}


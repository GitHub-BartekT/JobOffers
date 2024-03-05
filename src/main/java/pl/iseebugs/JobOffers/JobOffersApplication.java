package pl.iseebugs.JobOffers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.iseebugs.JobOffers.infrastructure.http.offersAWS.OfferAWSFetcherClientProperties;

@SpringBootApplication
@EnableConfigurationProperties({OfferAWSFetcherClientProperties.class})
@EnableMongoRepositories
public class JobOffersApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobOffersApplication.class, args);
    }

}


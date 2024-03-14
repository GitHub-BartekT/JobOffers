package pl.iseebugs.joboffers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import pl.iseebugs.joboffers.infrastructure.http.offersAWS.OfferAWSFetcherClientProperties;
import pl.iseebugs.joboffers.infrastructure.security.jwt.JwtConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({OfferAWSFetcherClientProperties.class, JwtConfigurationProperties.class})
@EnableMongoRepositories
public class JobOffersApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobOffersApplication.class, args);
    }

}


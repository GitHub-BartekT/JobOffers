package pl.iseebugs.JobOffers.infrastructure.clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Configuration
public class ClockConfig {

    @Bean
    Clock clock(){
        return Clock.systemUTC();
    }
}

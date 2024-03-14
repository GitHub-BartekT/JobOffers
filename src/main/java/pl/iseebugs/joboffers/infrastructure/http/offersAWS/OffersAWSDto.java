package pl.iseebugs.joboffers.infrastructure.http.offersAWS;

import lombok.Builder;

@Builder
record OffersAWSDto(String title, String company, String salary, String offerUrl) {
}

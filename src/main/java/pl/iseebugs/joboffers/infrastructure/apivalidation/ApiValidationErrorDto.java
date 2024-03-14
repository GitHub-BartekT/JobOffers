package pl.iseebugs.joboffers.infrastructure.apivalidation;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
public record ApiValidationErrorDto(List<String> message, HttpStatus status) {
}
package pl.iseebugs.JobOffers;

public interface SampleJobOfferResponse {
    default String bodyWithZeroOffersJson(){
      return "[]";
    }

    default String bodyWithOneOffersJson(){
        return "[{\"title\":\"Junior Java Developer\",\"company\":\"JSoft Sp. z o.o.\",\"salary\":\"7 000 – 9 000 PLN\",\"offerUrl\":\"https://nosite.com/pl/job/junior-java-developer\"}]";
    }

    default String bodyWithTwoOffersJson() {
        return """
                [
                {
                    "title": "Junior Java Developer",
                    "company": "CyberJanusz",
                    "salary": "2k - 4k PLN",
                    "offerUrl": "https://nojobs.com/"
                },
                {
                    "title": "Junior DevOps",
                    "company": "OCC",
                    "salary": "7k - 8k PLN",
                    "offerUrl": "https://OCK.io"
                }
                ]
                """.trim();
    }

    default String bodyWithFourOffersJson() {
        return """
                [
                {
                    "title": "Junior Java Developer",
                    "company": "CyberJanusz",
                    "salary": "2k - 4k PLN",
                    "offerUrl": "https://nojobs.com/"
                },
                {
                    "title": "Junior DevOps",
                    "company": "OCC",
                    "salary": "7k - 8k PLN",
                    "offerUrl": "https://OCK.io"
                },
                {
                    "title": "Mid Fullstack",
                    "company": "Oprogramex",
                    "salary": "5k - 6k PLN",
                    "offerUrl": "https://oprogramex.com/"
                },
                {
                    "title": "Full, Senior, AI, Blockchain Master",
                    "company": "OCC",
                    "salary": "7,5k - 7,75k PLN",
                    "offerUrl": "https://teresa.tanie.oprogramowanie.com.pl"
                }
                ]
                """.trim();
    }
}

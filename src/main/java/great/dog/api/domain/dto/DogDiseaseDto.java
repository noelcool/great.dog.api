package great.dog.api.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class DogDiseaseDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String name;
        private String region;
        private String comment;
        private Long dogId;
    }

    @Getter
    @NoArgsConstructor
    public static class SaveRequest {
        private String name;
        private String region;
        private String comment;
        private Long dogId;
    }

    @Getter
    @NoArgsConstructor
    public static class UpdateRequest {
        private String name;
        private String region;
        private String comment;
        private String delYn;
        private Long dogId;
    }

}

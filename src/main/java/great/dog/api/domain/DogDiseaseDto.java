package great.dog.api.domain;


import great.dog.api.domain.entity.Dog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DogDiseaseDto {

    private Long id;
    private String name;
    private String region;
    private String comment;
    private Dog dog;
    private Long dogId;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Request {
        private String name;
        private String region;
        private String comment;
        private Long dogId;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String name;
        private String region;
        private String comment;
        private Dog dog;
    }
}

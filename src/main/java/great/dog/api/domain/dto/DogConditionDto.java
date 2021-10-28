package great.dog.api.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DogConditionDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Request {
        private Long dogId;
        private Float weight;
        private Float height;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private Long dogId;
        private Float weight;
        private Float height;
    }
}

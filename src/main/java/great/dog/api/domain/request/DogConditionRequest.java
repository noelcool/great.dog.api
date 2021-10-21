package great.dog.api.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DogConditionRequest {

    private Long dogId;
    private Float weight;
    private Float height;

}


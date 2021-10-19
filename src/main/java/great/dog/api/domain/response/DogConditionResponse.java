package great.dog.api.domain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DogConditionResponse {

    private Float weight;
    private Float height;
}

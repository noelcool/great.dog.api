package great.dog.api.domain.response;

import great.dog.api.domain.entity.DogCondition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DogResponse {

    //private UserResponse user;
    private String name;
    private String type;
    private Long userId;
    private List<DogCondition> dogConditions;
}

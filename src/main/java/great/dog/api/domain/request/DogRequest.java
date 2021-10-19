package great.dog.api.domain.request;

import great.dog.api.domain.entity.DogCondition;
import great.dog.api.domain.entity.DogFeeding;
import great.dog.api.domain.entity.DogHospital;
import great.dog.api.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DogRequest {

    private Long id;
    private String name;
    private String type;
    private Long userId;
    private String delYn;
    private User user;
    private DogCondition dogCondition;
    private DogFeeding feeding;
    private DogHospital hospital;

}

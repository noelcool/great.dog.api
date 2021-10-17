package great.dog.api.domain.request;

import great.dog.api.domain.entity.DogCondition;
import great.dog.api.domain.entity.Feeding;
import great.dog.api.domain.entity.Hospital;
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
    private User user;
    private DogCondition dogCondition;
    private Feeding feeding;
    private Hospital hospital;

}

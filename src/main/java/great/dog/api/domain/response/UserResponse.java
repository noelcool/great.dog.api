package great.dog.api.domain.response;

import java.util.List;

import great.dog.api.domain.entity.Dog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private String name;
    private String password;
    private String nickName;
    private List<Dog> dogs;
}

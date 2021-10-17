package great.dog.api.domain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DogResponse {

    private UserResponse user;
    private String name;
    private String type;

}

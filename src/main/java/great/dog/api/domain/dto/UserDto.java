package great.dog.api.domain.dto;

import great.dog.api.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

        private String name;
        private String password;
        private String nickname;

}

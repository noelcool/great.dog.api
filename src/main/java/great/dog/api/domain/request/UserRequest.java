package great.dog.api.domain.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserRequest {
	
	private String name;
	private String password;
	private String password_re;
	private String nickName;
	private String delYn;

}

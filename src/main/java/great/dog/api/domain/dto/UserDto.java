package great.dog.api.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserDto {


	@Getter @Setter
	@NoArgsConstructor
	public static class Request {
		private String name;
		private String password;
		private String password_re;
		private String nickName;
		private String delYn;
	}

	@Getter @Setter
	@NoArgsConstructor
	public static class Response {
		private String name;
		private String password;
		private String nickName;
	}
}

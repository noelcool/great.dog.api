package great.dog.api.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserDto {


	@Getter @Setter
	@NoArgsConstructor
	public static class SaveRequest {
		private String email;
		private String name;
		private String password;
	}

	@Getter @Setter
	@NoArgsConstructor
	public static class UpdateRequest {
		private String password;
		private String delYn;
	}
	@Getter @Setter
	@NoArgsConstructor
	public static class Response {
		private String email;
		private String name;
		private String password;
	}
}

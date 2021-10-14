package great.dog.api.controller.api;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import great.dog.api.domain.dto.UserDto;
import great.dog.api.service.UserService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

	private final UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Optional<UserDto> user = userService.findById(id);		
		if (user.isPresent()) return ResponseEntity.ok().body(user.get());
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody UserDto dto) {
		int res = userService.save(dto);
//		if (baseRes.getResResult() != 0) {
//			baseRes.setResCode(Message.codeFail);
//		}
//		return ResponseEntity.ok().body(baseRes);
		return null;
	}
}

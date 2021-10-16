package great.dog.api.controller.api;

import java.util.Optional;

import great.dog.api.domain.dto.UserDto;
import great.dog.api.domain.entity.User;
import great.dog.api.domain.response.DefaultRes;
import great.dog.api.util.StatusCode;
import great.dog.api.util.StatusMsg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import great.dog.api.domain.request.UserRequest;
import great.dog.api.service.UserService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

	private final UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		UserDto user = userService.findById(id);

		if (user != null)
			return new ResponseEntity(DefaultRes.res(
				StatusCode.OK, StatusMsg.READ_USER, user),
				HttpStatus.OK);

		return new ResponseEntity(DefaultRes.res(
				StatusCode.NOT_FOUND, StatusMsg.NOT_FOUND_USER, id),
				HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity save(@RequestBody UserRequest dto) {
		int res = userService.save(dto);
		if (res < 0)
			return new ResponseEntity(DefaultRes.res(
					StatusCode.UNAUTHORIZED, StatusMsg.DUPLICATED_USER, dto),
					HttpStatus.NO_CONTENT);

		return new ResponseEntity(DefaultRes.res(
				StatusCode.OK, StatusMsg.CREATED_USER, dto),
				HttpStatus.OK);
	}
}

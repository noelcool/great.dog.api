package great.dog.api.controller.api;

import great.dog.api.domain.dto.UserDto;
import great.dog.api.domain.response.DefaultRes;
import great.dog.api.util.StatusCode;
import great.dog.api.util.StatusMsg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import great.dog.api.domain.request.UserRequest;
import great.dog.api.service.UserService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

	private final UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		UserDto user = userService.findById(id);
		return user != null ?
			new ResponseEntity(DefaultRes.res(
				StatusCode.OK, StatusMsg.READ_USER, user),
				HttpStatus.OK)
		:
			new ResponseEntity(DefaultRes.res(
					StatusCode.NOT_FOUND, StatusMsg.NOT_FOUND_USER, id),
					HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity save(@RequestBody UserRequest dto) {
		int res = userService.save(dto);

		return res == -1 ?
			new ResponseEntity(DefaultRes.res(
					StatusCode.UNAUTHORIZED, StatusMsg.DISCORD_PASSWORD, dto),
					HttpStatus.OK)
			:
				res == -2 ?
					new ResponseEntity(DefaultRes.res(
							StatusCode.UNAUTHORIZED, StatusMsg.DUPLICATED_USER, dto),
							HttpStatus.OK)
				:
					new ResponseEntity(DefaultRes.res(
							StatusCode.OK, StatusMsg.CREATED_USER, dto),
							HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity update(@RequestBody UserRequest dto) {
		int res= userService.update(dto);
		return res == -2 ?
				new ResponseEntity(DefaultRes.res(
						StatusCode.UNAUTHORIZED, StatusMsg.NOT_FOUND_USER, dto),
						HttpStatus.OK)
				:
				new ResponseEntity(DefaultRes.res(
						StatusCode.OK, StatusMsg.UPDATE_USER, dto),
						HttpStatus.OK);
	}

}

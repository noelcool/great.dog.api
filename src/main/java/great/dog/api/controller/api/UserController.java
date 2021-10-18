package great.dog.api.controller.api;

import great.dog.api.domain.request.UserRequest;
import great.dog.api.domain.response.DefaultRes;
import great.dog.api.domain.response.UserResponse;
import great.dog.api.util.StatusCode;
import great.dog.api.util.StatusMsg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import great.dog.api.service.UserService;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

	private final UserService userService;

	@GetMapping("")
	public ResponseEntity<?> findAll() {
		DefaultRes<Object> defaultRes = new DefaultRes<Object>(StatusCode.BAD_REQUEST, StatusMsg.READ_FAIL);
		List<UserResponse> user = userService.findAll();
		if (user != null) {
			defaultRes.setResCode(StatusCode.OK);
			defaultRes.setResMsg(StatusMsg.READ_SUCCESS);
			defaultRes.setData(user);
		}
		return new ResponseEntity<Object>(defaultRes, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		DefaultRes<Object> defaultRes = new DefaultRes<Object>(StatusCode.BAD_REQUEST, StatusMsg.READ_FAIL);
		UserResponse user = userService.findById(id);
		if (user != null) {
			defaultRes.setResCode(StatusCode.OK);
			defaultRes.setResMsg(StatusMsg.READ_SUCCESS);
			defaultRes.setData(user);
		}
		return new ResponseEntity<Object>(defaultRes, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<DefaultRes<?>> save(@RequestBody UserRequest dto) {
		DefaultRes<?> defaultRes = new DefaultRes<Object>(StatusCode.BAD_REQUEST, StatusMsg.READ_FAIL);
		int result = userService.save(dto);
		if (result != 0) {
			defaultRes.setResCode(StatusCode.OK);
			defaultRes.setResMsg(StatusMsg.CREATED_SUCCESS);
			return new ResponseEntity<DefaultRes<?>>(defaultRes, HttpStatus.OK);
		}
		return new ResponseEntity<DefaultRes<?>>(defaultRes, HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<DefaultRes<?>> update(@RequestBody UserRequest dto) {
		DefaultRes<?> defaultRes = new DefaultRes<Object>(StatusCode.BAD_REQUEST, StatusMsg.READ_FAIL);
		int result = userService.update(dto);
		if (result != 0) {
			defaultRes.setResCode(StatusCode.OK);
			defaultRes.setResMsg(StatusMsg.UPDATE_SUCCESS);
			return new ResponseEntity<DefaultRes<?>>(defaultRes, HttpStatus.OK);
		}
		return new ResponseEntity<DefaultRes<?>>(defaultRes, HttpStatus.OK);
	}

}

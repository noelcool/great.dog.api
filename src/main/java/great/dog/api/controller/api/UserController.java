package great.dog.api.controller.api;

import great.dog.api.domain.dto.UserDto;
import great.dog.api.domain.response.DefaultRes;
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
		DefaultRes<Object> defaultRes = new DefaultRes<Object>();
		List<UserDto.Response> user = userService.findAll();
		if (user != null) {
			defaultRes.setResCode(StatusCode.OK);
			defaultRes.setResMsg(StatusMsg.READ_SUCCESS);
			defaultRes.setData(user);
		}
		return new ResponseEntity<Object>(defaultRes, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		DefaultRes<Object> defaultRes = new DefaultRes<>();
		UserDto.Response user = userService.findById(id);
		if (user != null) {
			defaultRes.setResCode(StatusCode.OK);
			defaultRes.setResMsg(StatusMsg.READ_SUCCESS);
			defaultRes.setData(user);
		}
		return new ResponseEntity<Object>(defaultRes, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<DefaultRes<?>> save(@RequestBody UserDto.SaveRequest dto) {
		int result = userService.save(dto);
		DefaultRes<?> defaultRes = new DefaultRes<Object>(dto);
		if (result > 0) {
			defaultRes.setResCode(StatusCode.OK);
			defaultRes.setResMsg(StatusMsg.CREATED_SUCCESS);
		} else {
			defaultRes.setResMsg(StatusMsg.CREATED_FAIL);
		}
		return new ResponseEntity(defaultRes, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DefaultRes<?>> update(@PathVariable("id") Long id, @RequestBody UserDto.UpdateRequest dto) {
		int result = userService.update(id, dto);
		DefaultRes<?> defaultRes = new DefaultRes<Object>(dto);
		if (result > 0) {
			defaultRes.setResCode(StatusCode.OK);
			defaultRes.setResMsg(StatusMsg.UPDATE_SUCCESS);
		} else {
			defaultRes.setResMsg(StatusMsg.UPDATE_FAIL);
		}
		return new ResponseEntity(defaultRes, HttpStatus.OK);
	}

}

package great.dog.api.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import great.dog.api.domain.entity.Dog;
import great.dog.api.service.TestService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor //클래스에 존재하는 모든 필드에 대한 생성자를 자동 생성
@RequestMapping("/test")
public class TestController {
	
	private final TestService testService;
	
	@GetMapping("")
	public String index() {
		return "test";
	}
	
	@GetMapping("/dogs")
	public void getDogs() {
		List<Dog> oDogs = testService.findAll();
		for (Dog d : oDogs) {
			System.out.println(d.getDogName());
		}
	}
	
	

}

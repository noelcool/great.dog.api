package great.dog.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import great.dog.api.domain.entity.Dog;
import great.dog.api.repository.DogRepository;
import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor //초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 준다
@Service
public class TestService {
	
	private final DogRepository dogRepository;
	
	public List<Dog> findAll() {
		return dogRepository.findAll();
	}

}

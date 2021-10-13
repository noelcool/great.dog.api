package great.dog.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import great.dog.api.domain.entity.Dog;
import great.dog.api.domain.entity.DogDisease;
import great.dog.api.domain.entity.User;
import great.dog.api.repository.DogDiseaseRepository;
import great.dog.api.repository.DogRepository;
import great.dog.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor //초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 준다
@Service
public class TestService {
	
	private final DogRepository dogRepository;
	private final UserRepository userRepository;
	private final DogDiseaseRepository dogDiseaseRepository;
	
	public List<Dog> findDog() {
		return dogRepository.findAll();
	}
	
	@Transactional
	public void findDog2() {
		Dog dog = dogRepository.findById(3L).orElseThrow(RuntimeException::new);
		System.out.println(dog.getDogDiseases());
		System.out.println(dog.getDogConditions());
		System.out.println("..");
	}
	
	public List<User> findUser() {
		return userRepository.findAll();
	}
	
	public List<DogDisease> findDogDisease() {
		return dogDiseaseRepository.findAll();
	}
	
	

}

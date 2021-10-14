package great.dog.api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import great.dog.api.domain.dto.UserDto;
import great.dog.api.domain.entity.User;
import great.dog.api.domain.entity.User.UserBuilder;
import great.dog.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public int save(UserDto dto) {
		if (!dto.getPassword().equals(dto.getPassword_re())) {
			return -1;
		}
		
		if (userRepository.findByUserNameAndDelYn(dto.getUserName(), "N").isPresent()) {
			return -1;
		}
		
		UserBuilder user = User.builder().
				userName(dto.getUserName()).
				password(dto.getPassword()).
				nickName(dto.getNickName());
		return userRepository.save(user.build()) != null ? 1 : 0; //great.dog.api.domain.entity.User@3a2749e0
	}

	public Optional<UserDto> findById(Long id) {
		userRepository.findById(id);
		// TODO Auto-generated method stub
		return null;
	}

	
}

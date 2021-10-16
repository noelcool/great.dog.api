package great.dog.api.service;

import java.util.Optional;

import great.dog.api.domain.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import great.dog.api.domain.request.UserRequest;
import great.dog.api.domain.entity.User;
import great.dog.api.domain.entity.User.UserBuilder;
import great.dog.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	
	public int save(UserRequest dto) {
		if (!dto.getPassword().equals(dto.getPassword_re())) {
			return -1;
		}
		
		if (userRepository.findByUserNameAndDelYn(dto.getUserName(), "N").isPresent()) {
			return -2;
		}
		
		UserBuilder userBuilder = User.builder().
				userName(dto.getUserName()).
				password(dto.getPassword()).
				nickName(dto.getNickName());
		return userRepository.save(userBuilder.build()) != null ? 1 : 0; //great.dog.api.domain.entity.User@3a2749e0
	}

	public UserDto findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.map(value -> modelMapper.map(value, UserDto.class)).orElse(null);
	}

	public int update(Long id, UserRequest userRequest) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			return -2;
		}
		user.ifPresent(u -> {
			UserBuilder userBuilder = User.builder().
					userName(userRequest.getUserName()).
					password(userRequest.getPassword()).
					nickName(userRequest.getNickName());
			userRepository.save(userBuilder.build());
		});
		return 1;
	}




}

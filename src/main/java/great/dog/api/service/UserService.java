package great.dog.api.service;

import java.util.List;
import java.util.Optional;

import great.dog.api.domain.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import great.dog.api.domain.entity.User;
import great.dog.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public UserResponse findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.map(value -> modelMapper.map(value, UserResponse.class)).orElse(null);
	}

	public int save(great.dog.api.domain.request.UserRequest dto) {
		if (!dto.getPassword().equals(dto.getPassword_re())) return -1;

		if (userRepository.findByNameAndDelYn(dto.getName(), "N").isPresent()) return -2;

		User.UserBuilder userBuilder = User.builder().
				name(dto.getName()).
				password(dto.getPassword()).
				nickName(dto.getNickName());
		return userRepository.save(userBuilder.build()) != null ? 1 : 0; //great.dog.api.domain.entity.User@3a2749e0
	}

	@Transactional
	public int update(great.dog.api.domain.request.UserRequest dto) {
		Optional<User> user = userRepository.findByNameAndDelYn(dto.getName(), "N");
		if (!user.isPresent()) {
			return -2;
		}
		user.ifPresent(u -> {
			u.setPassword(dto.getPassword());
			u.setNickName(dto.getNickName());
			userRepository.save(u);
		});
		return 1;
	}




}

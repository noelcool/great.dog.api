package great.dog.api.service;

import great.dog.api.config.SecurityUser;
import great.dog.api.domain.entity.User;
import great.dog.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public SecurityUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findWithUserRolesByEmail(email);
        System.out.println(email + "-----------------------------" + user.get());
        if(!user.isPresent()){
            throw new UsernameNotFoundException(email);
        }
        return new SecurityUser(user.get());
    }
}

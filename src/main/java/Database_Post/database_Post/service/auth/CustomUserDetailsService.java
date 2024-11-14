package Database_Post.database_Post.service.auth;


import Database_Post.database_Post.bo.CustomUserDetails;
import Database_Post.database_Post.entity.UserEntity;
import Database_Post.database_Post.exception.UserNotFoundException;
import Database_Post.database_Post.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return buildCustomUserDetailsOfUsername(username);
    }

    private CustomUserDetails buildCustomUserDetailsOfUsername(String username){
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Incorrect Username"));

        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setId(user.getId());
        userDetails.setUserName(user.getUsername());
        userDetails.setPassword(user.getPassword());
        userDetails.setRole(user.getRole());

        return userDetails;
    }
}

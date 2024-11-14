package Database_Post.database_Post.service;

import Database_Post.database_Post.bo.UpdateUserProfileRequest;
import Database_Post.database_Post.bo.UserResponse;
import Database_Post.database_Post.entity.UserEntity;
import Database_Post.database_Post.exception.UserNotFoundException;
import Database_Post.database_Post.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserResponse updateUserProfile(UpdateUserProfileRequest request, String loggedInUsername) {
        // Find the logged-in user by username
        UserEntity userEntity = userRepository.findByUsername(loggedInUsername)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Update fields if provided
        if (request.getUsername() != null) userEntity.setUsername(request.getUsername());
        if (request.getEmail() != null) userEntity.setUsername(request.getEmail());
        if (request.getPhoneNumber() != null) userEntity.setPhoneNumber(request.getPhoneNumber());
        if (request.getAddress() != null) userEntity.setAddress(request.getAddress());
        if (request.getPassword() != null) userEntity.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        // Save the updated user
        userRepository.save(userEntity);

        return new UserResponse(userEntity.getId(),userEntity.getUsername(),userEntity.getPhoneNumber(),userEntity.getEmail(),userEntity.getAddress(),userEntity.getRole().toString());
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }




    @Override
    public UserEntity getUserProfile(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }
}

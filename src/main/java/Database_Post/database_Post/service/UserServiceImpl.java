package Database_Post.database_Post.service;

import Database_Post.database_Post.bo.CustomUserDetails;
import Database_Post.database_Post.bo.UpdateUserProfileRequest;
import Database_Post.database_Post.bo.UpdateUserResponse;
import Database_Post.database_Post.bo.UserResponse;
import Database_Post.database_Post.config.JWTUtil;
import Database_Post.database_Post.entity.UserEntity;
import Database_Post.database_Post.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTUtil jwtUtil;
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserEntity getUserProfile(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public UpdateUserResponse updateUserProfile(UpdateUserProfileRequest request, String loggedInUsername) {
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
        userEntity = userRepository.save(userEntity);

        // Generate a new token
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setId(userEntity.getId());
        userDetails.setUserName(userEntity.getUsername());
        userDetails.setRole(userEntity.getRole());
        userDetails.setStatus(userEntity.getStatus().toString());

        String newToken = jwtUtil.generateToken(userDetails);

        // Return the updated user with a new token
        return new UpdateUserResponse(
                new UserResponse(userEntity.getId(), userEntity.getName(), userEntity.getStatus().toString()),
                "Bearer " + newToken
        );
    }



}





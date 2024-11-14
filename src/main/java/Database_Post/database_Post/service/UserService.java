package Database_Post.database_Post.service;

//import com.example.demo.bo.CreateUserRequest;
//import com.example.demo.bo.UserResponse;

import Database_Post.database_Post.bo.CreateUserRequest;
import Database_Post.database_Post.bo.UpdateUserProfileRequest;
import Database_Post.database_Post.bo.UpdateUserResponse;
import Database_Post.database_Post.bo.UserResponse;
import Database_Post.database_Post.entity.UserEntity;

import java.util.List;

public interface UserService {
//    UserResponse createUser(CreateUserRequest request);

    UpdateUserResponse updateUserProfile(UpdateUserProfileRequest request, String loggedInUsername);
    UserEntity getUserProfile(Long userId);
    List<UserEntity> getAllUsers();
    UserEntity getUserById(Long userId);
    void deleteUserById(Long userId);
}

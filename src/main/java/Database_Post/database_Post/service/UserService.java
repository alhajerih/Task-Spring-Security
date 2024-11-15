package Database_Post.database_Post.service;



import Database_Post.database_Post.bo.UpdateUserProfileRequest;
import Database_Post.database_Post.bo.UpdateUserResponse;
import Database_Post.database_Post.bo.UserResponse;
import Database_Post.database_Post.entity.UserEntity;

import java.util.List;


public interface UserService {

    List<UserEntity> getAllUsers();
    UserEntity getUserById(Long userId);
    void deleteUserById(Long userId);
    UserResponse updateUserProfile(UpdateUserProfileRequest request, String loggedInUsername);
    UserEntity getUserProfile(String username);
  
}

package Database_Post.database_Post.service;



import Database_Post.database_Post.bo.UpdateUserProfileRequest;
import Database_Post.database_Post.bo.UserResponse;
import Database_Post.database_Post.entity.UserEntity;


public interface UserService {

    UserResponse updateUserProfile(UpdateUserProfileRequest request, String loggedInUsername);
    UserEntity getUserProfile(String username);

}

package Database_Post.database_Post.service;

import Database_Post.database_Post.bo.CreateUserRequest;
import Database_Post.database_Post.bo.UserResponse;
import Database_Post.database_Post.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService{
    @Autowired
    List<UserEntity> getAllUsers();

    UserResponse createUser(CreateUserRequest request);
    boolean updateUserStatus(Long id ,String status);
    List<UserResponse> searchUserByStatus(String status);

}

package Database_Post.database_Post.service;

//import com.example.demo.bo.CreateUserRequest;
//import com.example.demo.bo.UserResponse;

import Database_Post.database_Post.bo.CreateUserRequest;
import Database_Post.database_Post.bo.UserResponse;
import Database_Post.database_Post.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);
    List<UserEntity> getAllUsers();
}

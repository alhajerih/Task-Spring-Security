package Database_Post.database_Post.service;

import Database_Post.database_Post.bo.CreateUserRequest;
import Database_Post.database_Post.bo.UserRespone;
import Database_Post.database_Post.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService{
    @Autowired
    List<UserEntity> getAllUsers();

    UserRespone createUser(CreateUserRequest request);
}

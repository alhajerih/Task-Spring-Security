package Database_Post.database_Post.service.auth;

//import com.example.demo.bo.auth.AuthenticationResponse;
//import com.example.demo.bo.auth.CreateLoginRequest;
//import com.example.demo.bo.auth.LogoutResponse;

import Database_Post.database_Post.bo.CreateUserRequest;
import Database_Post.database_Post.bo.UserResponse;
import Database_Post.database_Post.bo.auth.AuthenticationResponse;
import Database_Post.database_Post.bo.auth.CreateLoginRequest;
import Database_Post.database_Post.bo.auth.LogoutResponse;
import Database_Post.database_Post.entity.UserEntity;

public interface AuthService {
    AuthenticationResponse login(CreateLoginRequest createLoginRequest);

    void logout(LogoutResponse logoutResponse);

    UserResponse createUser(CreateUserRequest request);
}

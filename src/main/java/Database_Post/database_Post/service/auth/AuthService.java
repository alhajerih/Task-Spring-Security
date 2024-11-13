package Database_Post.database_Post.service.auth;

//import com.example.demo.bo.auth.AuthenticationResponse;
//import com.example.demo.bo.auth.CreateLoginRequest;
//import com.example.demo.bo.auth.LogoutResponse;

import Database_Post.database_Post.bo.auth.AuthenticationResponse;
import Database_Post.database_Post.bo.auth.CreateLoginRequest;
import Database_Post.database_Post.bo.auth.LogoutResponse;

public interface AuthService {

    AuthenticationResponse login(CreateLoginRequest createLoginRequest);

    void logout(LogoutResponse logoutResponse);
}

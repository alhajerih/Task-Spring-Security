package Database_Post.database_Post.service.auth;


import Database_Post.database_Post.bo.CreateUserRequest;
import Database_Post.database_Post.bo.CustomUserDetails;
import Database_Post.database_Post.bo.UserResponse;
import Database_Post.database_Post.bo.auth.AuthenticationResponse;
import Database_Post.database_Post.bo.auth.CreateLoginRequest;
import Database_Post.database_Post.bo.auth.LogoutResponse;
import Database_Post.database_Post.config.JWTUtil;
import Database_Post.database_Post.entity.RoleEntity;
import Database_Post.database_Post.entity.UserEntity;
import Database_Post.database_Post.exception.BodyGuardException;
import Database_Post.database_Post.exception.UserNotFoundException;
import Database_Post.database_Post.repository.RoleRepository;
import Database_Post.database_Post.repository.UserRepository;
import Database_Post.database_Post.service.AccountService;
import Database_Post.database_Post.util.Roles;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AccountService accountService;
    private final JWTUtil jwtUtil;
    private final RoleRepository roleRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AccountService accountService, JWTUtil jwtUtil, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.accountService = accountService;

        this.jwtUtil = jwtUtil;
        this.roleRepository = roleRepository;
    }

    /*
    - It checks that the **`username`** and **`password`** in the **`authenticationRequest`** are not null or empty using the **`requiredNonNull`** method.
    - It converts the **`username`** to lowercase (standardizing it).
    - It calls the **`authenticate`** method to perform user authentication using Spring Security's **`AuthenticationManager`**.
    - It loads user details using the **`userDetailsService`**.
    - It generates an access token using the **`jwtUtil`**.
    - Finally, it constructs an **`AuthenticationResponse`** object with the user details and access token and returns it.
     */
    @Override
    public AuthenticationResponse login(CreateLoginRequest authenticationRequest) {
        String username = authenticationRequest.getUsername().toLowerCase(); // Standardize input
        String password = authenticationRequest.getPassword();

        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

        // Validate password
        if (!bCryptPasswordEncoder.matches(password, userEntity.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        // Generate token
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setId(userEntity.getId());
        userDetails.setUserName(userEntity.getUsername());
        userDetails.setRole(userEntity.getRole().toString());

        String token = jwtUtil.generateToken(userDetails);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setId(userEntity.getId());
        response.setUsername(userEntity.getUsername());
        response.setRole(userEntity.getRole().toString());
        response.setToken("Bearer " + token);

        return response;
    }





    @Override
    public void logout(LogoutResponse logoutResponse) {
        requiredNonNull(logoutResponse.getToken(),"token");
    }

    private void requiredNonNull(Object obj, String name) {
        if (obj == null || obj.toString().isEmpty()){
            throw new BodyGuardException(name + " can't be empty");
        }
    }

    private void authenticate(String username, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        }catch (BadCredentialsException e){
            throw  new BadCredentialsException("Incorrect password");
        }catch (AuthenticationServiceException e){
            throw  new UserNotFoundException("Incorrect username");
        }
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getEmail()); // Store email as username
        userEntity.setEmail(request.getEmail());
        userEntity.setPhoneNumber(request.getPhoneNumber());
        userEntity.setAddress(request.getAddress());
        userEntity.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));


        RoleEntity role =  roleRepository.findByRoleName(Roles.valueOf(request.getRole())).orElse(null);

        if(role ==null){
             role= new RoleEntity();
            role.setRoleName(Roles.valueOf(request.getRole()));
            roleRepository.save(role);

        }


        userEntity.setRole(role);

        userEntity = userRepository.save(userEntity);

        // Only create an account if the user is not an admin
        if ("USER".equalsIgnoreCase(request.getRole().toString())) {
            accountService.createAccount(userEntity.getId());
        }

        return new UserResponse(userEntity.getId(),userEntity.getUsername(),
                userEntity.getEmail(),userEntity.getAddress(),userEntity.getPhoneNumber(),userEntity.getRole().getRoleName().toString());
    }
}

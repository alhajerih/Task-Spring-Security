package Database_Post.database_Post.controller;

import Database_Post.database_Post.bo.UpdateUserProfileRequest;
import Database_Post.database_Post.bo.UpdateUserResponse;
import Database_Post.database_Post.bo.UserResponse;
import Database_Post.database_Post.entity.UserEntity;
import Database_Post.database_Post.service.UserService;
import Database_Post.database_Post.util.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/sayHi")
    public String sayHi(){
        return "Hi, you are an authenticated user";
    }



    @PostMapping("/update-profile")
    public ResponseEntity<UpdateUserResponse> updateUserProfile(
            @RequestBody UpdateUserProfileRequest request,
            @AuthenticationPrincipal UserDetails currentUser) {

        // Get the username of the currently authenticated user
        String loggedInUsername = currentUser.getUsername();

        // Update the profile of the logged-in user
        UpdateUserResponse updatedUser = userService.updateUserProfile(request, loggedInUsername);

        return ResponseEntity.ok(updatedUser);
    }



    @GetMapping("/profile")
    public ResponseEntity<UserEntity> getUserProfile(@RequestParam Long userId) {
        UserEntity user = userService.getUserProfile(userId);
        return ResponseEntity.ok(user);
    }


}

package Database_Post.database_Post.controller;

import Database_Post.database_Post.bo.UpdateUserProfileRequest;
import Database_Post.database_Post.bo.UserResponse;
import Database_Post.database_Post.entity.UserEntity;
import Database_Post.database_Post.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/update-profile")
    public ResponseEntity<UserResponse> updateUserProfile(
            @RequestBody UpdateUserProfileRequest request,
            @AuthenticationPrincipal UserDetails currentUser) {

        // Get the username of the currently authenticated user
        String loggedInUsername = currentUser.getUsername();

        // Update the profile of the logged-in user
        UserResponse updatedUser = userService.updateUserProfile(request, loggedInUsername);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserEntity> getProfile() {
        // Extract logged-in username from the security context
        String loggedInUsername = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        UserEntity userProfile = userService.getUserProfile(loggedInUsername);

        return ResponseEntity.ok(userProfile);
    }

}

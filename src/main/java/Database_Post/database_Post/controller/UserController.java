package Database_Post.database_Post.controller;

import Database_Post.database_Post.bo.CreateUserRequest;
import Database_Post.database_Post.bo.UserResponse;
import Database_Post.database_Post.entity.UserEntity;
import Database_Post.database_Post.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserEntity>getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createuser(@RequestBody CreateUserRequest request){
        List<String> validStatus = Arrays.asList("ACTIVE","INACTIVE");
        request.setStatus(request.getStatus().toUpperCase());
        if(!validStatus.contains(request.getStatus())){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //Create the user

        UserResponse response = userService.createUser(request);

    if(response != null){
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<?>updateUserStatus(Long userId , String status){
        if(!status.equalsIgnoreCase("Active")&&!status.equalsIgnoreCase("Inactive")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid status, it should either Active or Inactive");
        }
        boolean updated= userService.updateUserStatus(userId,status);
        if(updated){
            return ResponseEntity.ok("User status updated successfully");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID: "+userId+" not found");
        }
    }

    @GetMapping("/searchByStatus")
    public ResponseEntity<List<UserResponse>> searchUserByStatus(@RequestParam String status){

        if(!status.equalsIgnoreCase("Active")&&!status.equalsIgnoreCase("Inactive")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        List<UserResponse> users = userService.searchUserByStatus(status);
        return ResponseEntity.ok(users);
    }


}

package Database_Post.database_Post.controller;

import Database_Post.database_Post.bo.CreateUserRequest;
import Database_Post.database_Post.bo.UserRespone;
import Database_Post.database_Post.entity.UserEntity;
import Database_Post.database_Post.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserRespone> createuser(@RequestBody CreateUserRequest request){
        UserRespone response = userService.createUser(request);
    if(response != null){
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    }
}

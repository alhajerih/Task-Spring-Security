package Database_Post.database_Post.controller;

import Database_Post.database_Post.entity.UserEntity;
import Database_Post.database_Post.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/allUsers")
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsers();

    }
}

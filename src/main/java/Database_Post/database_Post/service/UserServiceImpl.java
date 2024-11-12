package Database_Post.database_Post.service;

import Database_Post.database_Post.Status;
import Database_Post.database_Post.bo.CreateUserRequest;
import Database_Post.database_Post.bo.UserResponse;
import Database_Post.database_Post.entity.UserEntity;
import Database_Post.database_Post.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }
@Override
public UserResponse createUser(CreateUserRequest request){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(request.getName());
        userEntity.setStatus(Status.valueOf(request.getStatus()));

        userEntity=userRepository.save(userEntity);
    UserResponse response = new UserResponse(
            userEntity.getId(),
            userEntity.getName(),
            userEntity.getStatus().name()
    );

    return response;
    }


    // Update user status
    public boolean updateUserStatus(Long id , String status){
        Optional<UserEntity> updated =  userRepository.findById(id);
        if(updated.isPresent()){
            UserEntity userEntity = updated.get();
            userEntity.setStatus(Status.valueOf(status));
            userRepository.save(userEntity);
            return true;
        }else {
            return false;
        }
    }

    // searchbar
    @Override
    public List<UserResponse> searchUserByStatus(String status) {
        Status enumStatus = Status.valueOf(status.toUpperCase());
        List<UserEntity>users = userRepository.findUserByStatus(enumStatus);
        return users.stream().map(user-> new UserResponse(user.getId(),user.getName(),user.getStatus().toString())).collect(Collectors.toList());
    }

}

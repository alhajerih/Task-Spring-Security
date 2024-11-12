package Database_Post.database_Post.service;

import Database_Post.database_Post.bo.CreateUserRequest;
import Database_Post.database_Post.bo.UserRespone;
import Database_Post.database_Post.entity.UserEntity;
import Database_Post.database_Post.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
public UserRespone createUser(CreateUserRequest request){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(request.getName());
        userEntity.setStatus(request.getStatus());

        userEntity=userRepository.save(userEntity);
        UserRespone response = new UserRespone(userEntity.getId(),userEntity.getName(),userEntity.getStatus());
        return response;
    }

}

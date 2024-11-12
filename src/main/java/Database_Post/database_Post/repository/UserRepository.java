package Database_Post.database_Post.repository;

import Database_Post.database_Post.Status;
import Database_Post.database_Post.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    List<UserEntity> findUserByStatus(Status status);
}

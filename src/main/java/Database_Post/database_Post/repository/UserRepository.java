package Database_Post.database_Post.repository;

import Database_Post.database_Post.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}

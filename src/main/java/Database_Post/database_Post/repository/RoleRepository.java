package Database_Post.database_Post.repository;

import Database_Post.database_Post.entity.RoleEntity;
import Database_Post.database_Post.util.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    Optional<RoleEntity> findByRoleName(Roles roleName);
}

package Database_Post.database_Post.entity;


import Database_Post.database_Post.util.Roles;

import javax.persistence.*;

@Entity
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false, unique = true)
    private Roles roleName; // Example: "ADMIN", "USER"


    //private UserEntity user;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Roles getRoleName() {
        return roleName;
    }

    public void setRoleName(Roles roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName.toString();
    }
}

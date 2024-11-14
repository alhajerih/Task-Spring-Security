package Database_Post.database_Post.bo;

public class UserResponse {

    private Long id;
    private String username;
    private String phoneNumber;
    private String email;
    private String address;
    private String role;

    public UserResponse(Long id, String username, String phoneNumber, String email, String address, String role) {
        this.id = id;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

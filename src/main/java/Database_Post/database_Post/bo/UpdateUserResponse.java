package Database_Post.database_Post.bo;

public class UpdateUserResponse {
    private UserResponse userResponse;
    private String token;

    public UpdateUserResponse(UserResponse userResponse, String token) {
        this.userResponse = userResponse;
        this.token = token;
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

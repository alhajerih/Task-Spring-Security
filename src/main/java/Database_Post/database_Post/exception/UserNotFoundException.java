package Database_Post.database_Post.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String str){
        super(str);
    }
}

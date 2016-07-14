package vtimoshenko.utils.pman.entity;

/**
 * Created by vtimoshenko on 14.07.2016.
 */
public class User {
    private String name;
    private String password;
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

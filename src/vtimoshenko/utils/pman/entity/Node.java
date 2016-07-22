package vtimoshenko.utils.pman.entity;

import java.util.HashMap;

/**
 * Created by vtimoshenko on 14.07.2016.
 */
public class Node {
    private String node;
    private HashMap<String, User> users;

    public Node(String node) {
        this.node = node;
        users = new HashMap<>();
    }

    public boolean existUser(String username){
        return users.containsKey(username);
    }

    public String getNode() {
        return node;
    }

    public void setNode(String adress) {
        this.node = adress;
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public void setUser(User usr) {
        this.users.put(usr.getUsername(), usr);
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }
}

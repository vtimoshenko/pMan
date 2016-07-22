package vtimoshenko.utils.pman;

import vtimoshenko.utils.pman.entity.Node;
import vtimoshenko.utils.pman.entity.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vtimoshenko on 21.07.2016.
 */
public class PassMan {
    private HashMap<String, Node> systems;
    private String currentNode;

    public PassMan() {
        this.systems = new HashMap<>();
        currentNode = "/";
    }

    public String newPass(String node, String username, String password, boolean overwrite){
        StringBuffer buf = new StringBuffer();
        if (!systems.containsKey(node)){
            systems.put(node, new Node(node));
            buf.append("node " + node + " created\n");
        }
        Node nod = systems.get(node);
        if (!nod.existUser(username)) {
            nod.setUser(new User(username, password));
            buf.append("user " + username + " created\n"); }
        else if (overwrite) {
            nod.getUser(username).setPassword(password);
            buf.append("user " + username + " changed\n"); }
        else
            buf.append("user " + username + " alredy exist. Use -o for overwrite.\n");
        return buf.toString();
    }

    public String listPass(String node){
        StringBuffer buf = new StringBuffer();
        ArrayList<String> results = new ArrayList<>();
        systems.forEach((nk, nv) -> {
            nv.getUsers().forEach((uk, uv) -> {
                if (nk.startsWith(currentNode)) results.add(nk.substring(currentNode.length()) + "\t" + nv.getUser());
            });
        });
        return buf.toString();
    }

}

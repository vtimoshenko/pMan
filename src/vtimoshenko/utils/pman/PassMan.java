package vtimoshenko.utils.pman;

import vtimoshenko.utils.pman.entity.Node;
import vtimoshenko.utils.pman.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by vtimoshenko on 21.07.2016.
 */
public class PassMan {
    private HashMap<String, Node> nodes;
    private String currentNode;

    public PassMan() {
        this.nodes = new HashMap<>();
        currentNode = "";
    }

    public String newPass(String node, String username, String password, boolean overwrite){
        StringBuffer buf = new StringBuffer();
        if (!nodes.containsKey(node)){
            nodes.put(node, new Node(node));
            buf.append("node " + node + " created\n");
        }
        Node nod = nodes.get(node);
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
        nodes.forEach((nk, nv) -> {
            nv.getUsers().forEach((uk, uv) -> {
                if (nk.startsWith(currentNode)) results.add(nk.substring(currentNode.length()) + "\t" + uk);
            });
        });
        Collections.sort(results);
        for (String a : results) buf.append(a + "\n");
        return buf.toString();
    }

    public String getPass(String node, String user){
        StringBuffer buf = new StringBuffer();
        if (!nodes.containsKey(currentNode + "/" + node))
            buf.append("node not found");
        else if (!nodes.get(currentNode + "/" + node).existUser(user))
            buf.append("user not found");
        else
            buf.append("pass: " + nodes.get(currentNode + "/" + node).getUser(user).getPassword());
        return buf.toString();
    }

}

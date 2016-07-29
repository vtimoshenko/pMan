package vtimoshenko.utils.pman;

import com.sun.org.apache.xalan.internal.xsltc.dom.CurrentNodeListFilter;
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
        currentNode = "/";
    }

    /**
     * создание пароля
     *
     *
     * @param node
     * @param username
     * @param password
     * @param overwrite
     * @return
     */
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


    public String listPass(String node, boolean recursive){
        StringBuffer buf = new StringBuffer();
        ArrayList<String> results = new ArrayList<>();
        nodes.forEach((nk, nv) -> {
            nv.getUsers().forEach((uk, uv) -> {
                if (nk.startsWith(currentNode + "/" + node))
                    results.add(nk.substring(currentNode.length()) + "\t" + uk);
            });
        });
        Collections.sort(results);
        for (String a : results) buf.append(a + "\n");
        return buf.toString();
    }

    /**
     * получение пароля
     *
     * @param node
     * @param user
     * @return
     */
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

    public String changeNode(String node){
        StringBuffer buf = new StringBuffer();
        if (node.equals("..")){
            if (currentNode.equals("/")) buf.append("you are in /");
            else {
                currentNode = currentNode.substring(0, currentNode.lastIndexOf('/'));
            }
        }
        else if (node.equals("")){
            buf.append(currentNode);
        }
        else {
            String newCNode = currentNode + "/" + node;
            if (nodes.containsKey(newCNode)) currentNode = newCNode;
            else buf.append("node " + newCNode + " nof found");
        }
        return buf.toString();
    }
}

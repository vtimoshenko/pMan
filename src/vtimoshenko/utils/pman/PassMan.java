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

        String targetNode;
        if (node.equals("")) targetNode = currentNode;
        else if (currentNode.equals("/")) targetNode = currentNode + node;
        else targetNode = currentNode + "/" + node;

        if (!nodes.containsKey(targetNode)){
            nodes.put(targetNode, new Node(targetNode));
            buf.append("node " + targetNode + " created\n");
        }
        Node nod = nodes.get(targetNode);
        if (!nod.existUser(username)) {
            nod.setUser(new User(username, password));
            buf.append("user " + username + " created"); }
        else if (overwrite) {
            nod.getUser(username).setPassword(password);
            buf.append("user " + username + " changed"); }
        else
            buf.append("user " + username + " alredy exist. Use -r for replace.");
        return buf.toString();
    }


    public String listPass(String node, boolean recursive, boolean withpass){
        StringBuffer buf = new StringBuffer();

        String targetNode;
        if (node.equals("")) targetNode = currentNode;
        else if (currentNode.equals("/")) targetNode = currentNode + node;
        else targetNode = currentNode + "/" + node;

        ArrayList<String> results = new ArrayList<>();
        nodes.forEach((nk, nv) -> {
            nv.getUsers().forEach((uk, uv) -> {
                if ((nk.startsWith(targetNode) && recursive) || (nk.equals(targetNode) && !recursive))
                    if (withpass)
                        results.add(nk.substring(currentNode.length()) + "\t" + uk + "/" + uv.getPassword());
                    else
                        results.add(nk.substring(currentNode.length()) + "\t" + uk);
            });
        });
        Collections.sort(results);
        for (String a : results) buf.append(a + "\n");
        return buf.toString();
    }

    public String listNodes(String node, boolean recursive){
        StringBuffer buf = new StringBuffer();

        String targetNode;
        if (node.equals("")) targetNode = currentNode;
        else if (currentNode.equals("/")) targetNode = currentNode + node;
        else targetNode = currentNode + "/" + node;

        ArrayList<String> results = new ArrayList<>();
        nodes.forEach((nk, nv) -> {
                if ((nk.startsWith(targetNode) && recursive) || (nk.startsWith(targetNode) && !recursive && nk.substring(targetNode.length()).indexOf('/')<0))
                        results.add(nk.substring(currentNode.length()));
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

        String targetNode;
        if (node.equals("")) targetNode = currentNode;
        else if (currentNode.equals("/")) targetNode = currentNode + node;
        else targetNode = currentNode + "/" + node;

        if (!nodes.containsKey(targetNode))
            buf.append("node not found");
        else if (!nodes.get(targetNode).existUser(user))
            buf.append("user not found");
        else
            buf.append("pass: " + nodes.get(targetNode).getUser(user).getPassword());
        return buf.toString();
    }

    public String changeNode(String node){
        StringBuffer buf = new StringBuffer();
        if (node.equals("..")){
            if (currentNode.equals("/")) buf.append(currentNode);
            else {
                currentNode = currentNode.substring(0, currentNode.lastIndexOf('/'));
                if (currentNode.equals("")) currentNode = "/";
                buf.append(currentNode);
            }
        }
        else if (node.equals("")){
            buf.append(currentNode);
        }
        else {
            String newCNode;
            if (currentNode.equals("/")) newCNode = currentNode + node;
            else newCNode = currentNode + "/" + node;

            nodes.forEach((nk, nv) -> {
                if ((nk.startsWith(newCNode + "/"))){
                    currentNode = newCNode;
                }
            });
            buf.append(currentNode);
        }
        return buf.toString();
    }

    public String debug(){
        StringBuffer buf = new StringBuffer();
        ArrayList<String> results = new ArrayList<>();
        nodes.forEach((nk, nv) -> {
            nv.getUsers().forEach((uk, uv) -> {
                results.add(nk + "\t" + uk + "/" + uv.getPassword());
            });
        });
        Collections.sort(results);
        for (String a : results) buf.append(a + "\n");
        return buf.toString();
    }
}

package ConnectedNodes;

import java.util.ArrayList;
import java.util.Objects;

import static ConnectedNodes.NodeManager.*;

public class Node {

    public ArrayList<Connection> list;
    public int data;
    public String nodeName;
    public String type;

    public Node(int data, String nodeName, String type) {
        this.data = data;
        this.nodeName = nodeName;
        this.type = type;
    }
    public Node(int data, String nodeName) {
        this(data, nodeName, "default");
    }

    public void createConnection(String name, Node node) {
        if (list == null) list = new ArrayList<>();
        list.add(new Connection(name, node));
    }
    public void createConnection(String name, Node node, int length) {
        if (list == null) list = new ArrayList<>();
        list.add(new Connection(name, node, length));
    }

    //deafult addNode method
    public void addNode(int data, String localNodeName, String type, int length) {
        Node newNode = new Node(data, localNodeName, type);
        createConnection(childNodeNamer(nodeName, localNodeName), newNode, length);
    }

    //Overloaded addNode methods
    public void addNode(int data, String localNodeName) {
        addNode(data, localNodeName, "default", 0);
    }
    public void addNode(int data, String localNodeName, int length) {
        addNode(data, localNodeName, "default", length);
    }
    public void addNode(int data, String localNodeName, String type) {
        addNode(data, localNodeName, type, 0);
    }

    //Display Methods
    public void displayNode() {
        System.out.println("Node Name: " + nodeName);
        System.out.println("Node Data: " + data);
        System.out.println();
    }
    public void displayConnections() {
        if (list == null || list.isEmpty()) {
            System.out.println("No connections.");
            return;
        }

        for (Connection current : list) {
            System.out.print(current.connectionName + " ");
        }
        System.out.println();
    }

    //Find node reference
    public Node findNode(String targetName) {
        return NodeManager.findNode(this, targetName);
    }


    //Node data collections Total Value
    public void printSum() {
        System.out.println();
        System.out.println("Total Value Of Data Sum : " + returnSum());
        System.out.println();
    }
    public int returnSum() {
        return NodeManager.returnTotalSum(this);
    }
    public void printSumByType(String targetType) {
        int total = returnSumByType(targetType);
        System.out.println();
        System.out.println("Total Value Of Data Sum " + total + " For Type " + targetType);
        System.out.println();
    }
    public int returnSumByType(String targetType) {
        return NodeManager.returnSumByType(this, targetType);
    }
    public void printSumByType() {
        printSumByType("default");
    }
    public int returnSumByType() {
        return returnSumByType("default");
    }

    //All tree type changer
    public void replaceTypeForAll(String fromType, String toType) {
        NodeManager.replaceTypeForAll(this, fromType, toType);
    }

    //Remove Methods
    public void removeConnection(String connectionName) {
        if (list == null || list.isEmpty()) {
            System.out.println("No connections to remove.");
            return;
        }

        boolean removed = list.removeIf(
                current -> Objects.equals(current.connectionName, connectionName)
        );

        if (removed)
            System.out.println("Required Connection Deleted");
        else
            System.out.println("No Connection Found To Delete");
    }
    public void izolateNode(Node target) {
        NodeManager.deleteNode(this, target);
    }
    public void fullDeleteNode(Node target) {
        izolateNode(target);
        if (target.list != null) {
            target.list.clear();
        }
    }
}

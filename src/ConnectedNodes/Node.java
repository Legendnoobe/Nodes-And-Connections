package ConnectedNodes;

import java.util.ArrayList;
import java.util.HashSet;
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
    public void createConnection(String name,Node node){

        Connection newConnection = new Connection();
        newConnection.connectedTo = node;
        newConnection.connectionName=name;
        if (list==null){
            list = new ArrayList<>();
        }
            list.add(newConnection);
    }

    public void addNode(int data,String localNodeName){
        Node newNode = new Node(data,localNodeName);
        createConnection(nodeName+"-"+localNodeName,newNode);
        displayConnections();
    }
    public void addNode(int data,String localNodeName,String type){
        Node newNode = new Node(data,localNodeName,type);
        createConnection(nodeName+"-"+localNodeName,newNode);
        displayConnections();
    }


    public void readNode(){
        System.out.println("Node Name:"+nodeName);
        System.out.println("Node Data:"+data);
        System.out.println();
    }
    public void displayConnections(){
        if (!list.isEmpty()) {
            for (Connection current : list) {
                System.out.print(current.connectionName+" ");
            }
        }
        System.out.println();
    }

    public Node findNode(String targetName) {
        return findNodeDFS(this, targetName, new HashSet<>());
    }



    public void printSum() {
        System.out.println();
        System.out.println("Total Value Of Data Sum :" +returnSum());
        System.out.println();
    }
    public int returnSum() {
        HashSet<Node> visited = new HashSet<>();
        return dfsSum(this, visited, 0, true);
    }




    public void printSumByType(String targetType) {
        System.out.println();
        System.out.println("Total Value Of Data Sum " + returnSumByType()+ " For Type " + targetType);
        System.out.println();
    }
    public int returnSumByType(String targetType) {
        HashSet<Node> visited = new HashSet<>();
        return dfsPrintByType(this, visited, 0, true, targetType);

    }
    public void printSumByType() {
        printSumByType("default"); // default tipi burada belirledik
    }
    public int returnSumByType() {
        HashSet<Node> visited = new HashSet<>();
        return dfsPrintByType(this, visited, 0, true, "default");

    }




    public void removeConnection(String connectionName){
        for (Connection current:list){
            if (Objects.equals(current.connectionName, connectionName)){
                list.remove(current);
                System.out.println("Required Connection Deleted");
                break;
            }
            System.out.println("No Conncetion Found to Delete");
        }

    }

    public void replaceTypeForAll(String fromType, String toType) {
        java.util.HashSet<Node> visited = new java.util.HashSet<>();
        dfsReplaceType(this, visited, fromType, toType);
    }





}

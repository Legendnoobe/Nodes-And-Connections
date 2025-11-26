package ConnectedNodes;

import java.util.ArrayList;
import java.util.HashSet;

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

    // Önceki constructor’ı koruyabilirsin
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
    // Toplam ve yazdırma metodları
    public void printSum() {
        HashSet<Node> visited = new HashSet<>();
        int total = dfsSum(this, visited, 0, true);
        System.out.println();
        System.out.println("Total Value Of Data Sum :" + total);
        System.out.println();
    }

    private int dfsSum(Node node, HashSet<Node> visited, int depth, boolean isStart) {
        if (node == null || visited.contains(node)) return 0;

        visited.add(node);
        printNodeLine(node, depth, isStart);


        int sum = node.data;

        if (node.list != null && !node.list.isEmpty()) {
            for (Connection conn : node.list) {
                Node child = conn.connectedTo;
                sum += dfsSum(child, visited, depth + 1, false);
            }
        }

        return sum;
    }

    private void printNodeLine(Node node, int depth, boolean isStart) {
        if (isStart) {
            System.out.println("Start " + node.nodeName + " (data=" + node.data + ")");
            return;
        }

        if (depth == 1) {
            // Start'ın doğrudan çocukları → her zaman flat, indent yok
            System.out.println(node.nodeName + " (data=" + node.data + ")");
            return;
        }

        // depth >= 2 → hiyerarşik zincir node’lar
        String indent = "  ".repeat(depth - 1);
        System.out.println(indent + "└─ " + node.nodeName + " (data=" + node.data + ")");
    }

    public void printSumByType(String targetType) {
        HashSet<Node> visited = new HashSet<>();
        int total = dfsPrintByType(this, visited, 0, true, targetType);
        System.out.println();
        System.out.println("Total Value Of Data Sum " + total+ " For Type " + targetType);
        System.out.println();
    }
    public void printSumByType() {
        printSumByType("default"); // default tipi burada belirledik
    }

    private int dfsPrintByType(Node node, HashSet<Node> visited, int depth, boolean isStart, String targetType) {
        if (node == null || visited.contains(node)) return 0;

        visited.add(node);

        int sum = 0;

        // Tipi eşleşen node'ları yazdır
        if (node.type.equals(targetType)) {
            printNodeLineByType(node, depth, isStart);
            sum += node.data;
        }

        if (node.list != null && !node.list.isEmpty()) {
            for (Connection conn : node.list) {
                sum += dfsPrintByType(conn.connectedTo, visited, depth + 1, false, targetType);
            }
        }

        return sum;
    }

    private void printNodeLineByType(Node node, int depth, boolean isStart) {
        if (isStart) {
            System.out.println("Start " + node.nodeName + " (data=" + node.data + ")");
            return;
        }

        if (depth == 1) {
            // Start çocukları → flat yaz
            System.out.println(node.nodeName + " (data=" + node.data + ")");
            return;
        }

        // depth >=2 → hiyerarşik zincir
        String indent = "  ".repeat(depth - 1);
        System.out.println(indent + "└─ " + node.nodeName + " (data=" + node.data + ")");
    }

    public void removeConnection(String connectionName){
        for (Connection current:list){
            if (current.connectionName==connectionName){
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

    private void dfsReplaceType(Node node, java.util.HashSet<Node> visited, String fromType, String toType) {
        if (node == null || visited.contains(node)) return;
        visited.add(node);
        if (node.type != null && node.type.equals(fromType)) {
            node.type = toType;
        }
        if (node.list != null) {
            for (Connection c : node.list) {
                dfsReplaceType(c.connectedTo, visited, fromType, toType);
            }
        }
    }




}

package ConnectedNodes;

import java.util.HashSet;

public class NodeManager {

    //DFS (Depth-First Search)
    protected static void dfsReplaceType(Node node, java.util.HashSet<Node> visited, String fromType, String toType) {
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
    protected static void printNodeLineByType(Node node, int depth, boolean isStart) {
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
    protected static int dfsPrintByType(Node node, HashSet<Node> visited, int depth, boolean isStart, String targetType) {
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
    protected static void printNodeLine(Node node, int depth, boolean isStart) {
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
    protected static int dfsSum(Node node, HashSet<Node> visited, int depth, boolean isStart) {
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
    protected static Node findNodeDFS(Node current, String targetName, HashSet<Node> visited) {
        if (current == null || visited.contains(current)) return null;
        visited.add(current);

        if (current.nodeName.equals(targetName)) return current;

        if (current.list != null) {
            for (Connection c : current.list) {
                Node result = findNodeDFS(c.connectedTo, targetName, visited);
                if (result != null) return result;
            }
        }

        return null;
    }
    protected static void sendPulseInternal(WireNode node, HashSet<Node> visited) {
        if (node == null || visited.contains(node)) return;
        visited.add(node);

        if (node.list == null) return;


        for (Connection conn : node.list) {
            WireNode next = (WireNode) conn.connectedTo;

            if (next.list==null){
                System.out.println("Your Node is Not Wire Node");
            }
            next.data += node.data; // pulse etkisi
            sendPulseInternal(next, visited);
        }
    }




}

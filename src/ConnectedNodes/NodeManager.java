package ConnectedNodes;

import java.util.*;

public final class NodeManager {

    private NodeManager() {}
    private static void traverseDFS(Node node, Set<Node> visited, NodeVisitor visitor) {
        if (node == null || visited.contains(node)) return;
        visited.add(node);

        visitor.visit(node);

        if (node.list != null) {
            for (Connection c : node.list) {
                if (c.active) {
                    traverseDFS(c.connectedTo, visited, visitor);
                }
            }
        }
    }
    static void replaceTypeForAll(Node root, String fromType, String toType) {
        traverseDFS(root, new HashSet<>(), node -> {
            if (node.type != null && node.type.equals(fromType)) {
                node.type = toType;
            }
        });
    }

    public static int returnSumByType(Node root, String targetType) {
        Set<Node> visited = new HashSet<>();
        final int[] sum = {0};

        traverseDFS(root, visited, node -> {
            if (node.type.equals(targetType)) {
                System.out.println(node.nodeName + " (data=" + node.data + ")");
                sum[0] += node.data;
            }
        });

        System.out.println("Total Sum For Type [" + targetType + "] = " + sum[0]);
        return sum[0];
    }
    public static int returnTotalSum(Node root) {
        HashSet<Node> visited = new HashSet<>();
        return dfsSum(root, visited, 0, true);
    }
    private static void printNode(Node node, int depth, boolean isStart) {
        if (isStart) {
            System.out.println("Start " + node.nodeName + " (data=" + node.data + ")");
            return;
        }

        String indent = "  ".repeat(Math.max(0, depth - 1));
        System.out.println(indent + "└─ " + node.nodeName + " (data=" + node.data + ")");
    }


    private static int dfsSum(Node node, HashSet<Node> visited, int depth, boolean isStart) {
        if (node == null || visited.contains(node)) return 0;

        visited.add(node);
        printNode(node, depth, isStart);

        int sum = node.data;

        if (node.list != null) {
            for (Connection c : node.list) {
                if (c.active) {
                    sum += dfsSum(c.connectedTo, visited, depth + 1, false);
                }
            }
        }

        return sum;
    }

    public static Node findNode(Node root, String name) {
        return findNodeDFS(root, name, new HashSet<>());
    }
    private static Node findNodeDFS(Node current, String target, Set<Node> visited) {
        if (current == null || visited.contains(current)) return null;
        visited.add(current);

        if (current.nodeName.equals(target)) return current;

        if (current.list != null) {
            for (Connection c : current.list) {
                if (c.active) {
                    Node found = findNodeDFS(c.connectedTo, target, visited);
                    if (found != null) return found;
                }
            }
        }
        return null;
    }

    public static void deleteNode(Node root, Node target) {
        dfsDeleteConnections(root, target, new HashSet<>());
    }
    private static void dfsDeleteConnections(Node current, Node target, Set<Node> visited) {
        if (current == null || visited.contains(current)) return;
        visited.add(current);

        if (current.list != null) {
            current.list.removeIf(c -> c.connectedTo == target);

            for (Connection c : current.list) {
                dfsDeleteConnections(c.connectedTo, target, visited);
            }
        }
    }

    // wire node methods
    public static void sendPulse(WireNode start) {
        sendPulseInternal(start, new HashSet<>());
    }
    private static void sendPulseInternal(WireNode node, Set<Node> visited) {
        if (node == null || visited.contains(node)) return;
        visited.add(node);

        if (node.list == null) return;

        for (Connection c : node.list) {
            if (c.active && c.connectedTo instanceof WireNode next) {
                next.data += node.data;
                sendPulseInternal(next, visited);
            }
        }
    }

    public static void printShortestPath(Node start, Node target) {

        Map<Node, Integer> distance = new HashMap<>();
        Map<Node, Node> prevNode = new HashMap<>();
        Map<Node, Connection> prevConn = new HashMap<>();

        PriorityQueue<Node> queue = new PriorityQueue<>(
                Comparator.comparingInt(distance::get)
        );

        distance.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current == target) break;
            if (current.list == null) continue;

            for (Connection c : current.list) {
                Node next = c.connectedTo;
                int newDist = distance.get(current) + c.length;

                if (!distance.containsKey(next) || newDist < distance.get(next)) {
                    distance.put(next, newDist);
                    prevNode.put(next, current);
                    prevConn.put(next, c);
                    queue.add(next);
                }
            }
        }

        if (!distance.containsKey(target)) {
            System.out.println("No path found.");
            return;
        }

        List<Object> path = new ArrayList<>();
        Node current = target;

        while (current != start) {
            path.add(current);
            path.add(prevConn.get(current));
            current = prevNode.get(current);
        }

        path.add(start);
        Collections.reverse(path);

        for (Object o : path) {
            if (o instanceof Node n)
                System.out.print(n.nodeName);
            else if (o instanceof Connection c)
                System.out.print(" -> " + c.connectionName + "(" + c.length + ") -> ");
        }

        System.out.println("\nTotal Distance: " + distance.get(target));
    }
    public static String childNodeNamer(String parent, String child) {
        return "(" + parent + "-" + child + ")";
    }
    @FunctionalInterface
    protected interface NodeVisitor {
        void visit(Node node);
    }
}

package ConnectedNodes;

import java.util.HashSet;

public class WireNode extends Node {

    public WireNode(int data, String nodeName, String type) {
        super(data, nodeName, type);
    }

    public WireNode(int data, String nodeName) {
        super(data, nodeName);
    }

    public void sendPulse() {
        sendPulseInternal(this, new HashSet<>());
    }

    private void sendPulseInternal(WireNode node, HashSet<Node> visited) {
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


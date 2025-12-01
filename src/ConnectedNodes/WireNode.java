package ConnectedNodes;

import java.util.HashSet;
import static ConnectedNodes.NodeManager.*;

//this special Node type Can send Pulse
//Pulse method makes current node data equals current node data sum fist node data
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

}


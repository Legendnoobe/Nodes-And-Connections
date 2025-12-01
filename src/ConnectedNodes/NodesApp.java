package ConnectedNodes;

public class NodesApp {

    public static void main(String[] args) {
        Node Node0 = new Node(35,"Node0","A");
        Node Node1 = new Node(8,"Node1","B");
        Node Node2 = new Node(1,"Node2","A");
        Node Node3 = new Node(81,"Node3","B");
        Node Node5 = new Node(28,"Node5","C");
        Node Node6 = new Node(3,"Node6");
        Node Node7 = new Node(5,"Node7");
        Node Node9 = new Node(44,"Node9","C");

        Node0.createConnection("c1",Node1);
        Node0.createConnection("c2",Node2);
        Node0.createConnection("c3",Node3);
        Node2.createConnection("c4",Node3);
        Node3.createConnection("c5",Node5);
        Node5.createConnection("c6",Node1);
        Node5.createConnection("c7",Node6);
        Node6.createConnection("c8",Node7);
        Node6.createConnection("c9",Node9);

        Node0.addNode(101,"Node101");

        Node0.printSum();
        Node0.printSumByType("A");

        Node0.replaceTypeForAll("C","A");

        Node0.printSumByType("A");

        Node0.findNode("Node101").readNode();




    }
}

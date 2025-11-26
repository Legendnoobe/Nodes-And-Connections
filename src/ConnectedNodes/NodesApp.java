package ConnectedNodes;

public class NodesApp {

    public static void main(String[] args) {
    Node n0 = new Node(5,"Node0","A");
    Node n1 = new Node(10,"Node1","B");
    Node n2 = new Node(15,"Node2","C");
    Node n3 = new Node(20,"Node3","B");
    Node n4 = new Node(25,"Node4","C");
    Node n5 = new Node(30,"Node5","C");
    Node n6 = new Node(35,"Node6","A");
    Node n7 = new Node(12,"Node7");
    Node n8 = new Node(22,"Node8");

    n0.createConnection("con1",n1);
    n0.createConnection("con2",n2);
    n0.createConnection("con3",n3);
    n0.createConnection("con4",n4);
    n1.createConnection("con5",n5);
    n5.createConnection("con6",n6);
    n2.createConnection("con7",n7);
    n4.createConnection("con8",n8);
    n8.createConnection("con9",n1);


n0.removeConnection("con1");
//n8.removeConnection("con9");
    n0.displayConnections();

    n0.printSumByType("A");
    n0.replaceType("B","A");

   n0.printSum();
   n0.printSumByType("A");






    }
}

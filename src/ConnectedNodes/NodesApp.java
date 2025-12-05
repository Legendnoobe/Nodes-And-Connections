package ConnectedNodes;

public class NodesApp {

    public static void main(String[] args) {
       Node n0 = new Node(30,"n0","A");
       Node n1 = new Node(5,"n1","B");
       Node n2 = new Node(110,"n2","A");
       Node n3 = new Node(12,"n3","C");
       Node n4 = new Node(30,"n4","A");
       Node n5 = new Node(44,"n5");
       Node n6 = new Node(85,"n6");
       Node n7 = new Node(90,"n7","C");

       n0.createConnection("c0",n1,12);
       n0.createConnection("c3",n3,30);
       n0.createConnection("c4",n4,40);
       n1.createConnection("c2",n2,25);
       n2.createConnection("c6",n6,8);
       n2.createConnection("c33",n3,5);
       n3.createConnection("c5",n5,5);
       n4.createConnection("c77",n5,15);
       n4.createConnection("c55",n5,22);
       n5.createConnection("c66",n6,3);
       n5.createConnection("c7",n7,7);
       n6.createConnection("c777",n7,15);
       n3.createConnection("c444",n4,1);
       n4.createConnection("c7777",n7,10);

       NodeManager.printShortestPath(n0,n7);

       n0.printSumByType("D");


       WireNode wn0 = new WireNode(12,"wn0");
       WireNode wn1 = new WireNode(12,"wn1");
       WireNode wn2 = new WireNode(12,"wn2");

       wn0.createConnection("cwn0",wn1);
       wn0.createConnection("cwn2",wn2);
       wn1.createConnection("cwn1",wn2);

       NodeManager.sendPulse(wn0);





    }
}

Nodes And Connections – Java Project

This project demonstrates a customizable and expandable node–connection data structure implemented in Java.
Each Node object stores a numeric value, a name, a type (optional), and maintains a list of Connection objects that link it to other nodes.
With this structure, users can build flexible networks, visualize connections, and perform grouped data calculations.

📌 Features
🔗 Node & Connection Architecture
Each Node contains:
A numeric value
A name (string)
A type (string — optional)
A list of outgoing connections

Each Connection contains:
A connection name
The target node to which it links

+This design allows creation of dynamic networks, graphs, or hierarchical structures.

📂 Displaying Connections
Using the displayConnections() method on any node prints all reachable connections visually in a tree-like format.
Special formatting rules:

The starting node is shown with the label "Start Node"
Direct children of the starting node are printed in a flat aligned format
Deeper levels are displayed as a branching tree for easier readability

Example Output:
Start Node: Node0
Node1
Node2
Node3
Node4
 └─ Node5
     └─ Node6
 └─ Node7

➕ Summing Node Values
Two calculation methods exist:
ConnectionsDataSum()
Traverses all connected nodes recursively
Prints the total sum of values found in the entire network

printSumByType(String type)
Sums only nodes whose type matches
If no type is given, the method uses the caller node's type as the default

printSumByType(); // Uses current node's own type
printSumByType("TypeA"); // Uses explicit type

🚀 Example Usage (Main)
Node n0 = new Node(5,"Node0");
Node n1 = new Node(10,"Node1");
Node n2 = new Node(15,"Node2");
Node n3 = new Node(20,"Node3");
Node n4 = new Node(25,"Node4");
Node n5 = new Node(30,"Node5");
Node n6 = new Node(35,"Node6");
Node n7 = new Node(12,"Node7");

n0.createConnection("con1",n1);
n0.createConnection("con2",n2);
n0.createConnection("con3",n3);
n0.createConnection("con4",n4);
n1.createConnection("con5",n5);
n5.createConnection("con6",n6);
n2.createConnection("con7",n7);

n0.displayConnections();
n0.ConnectionsDataSum();

🔧 Where This System Can Be Used
Use-Case	Example
Game development	Skill trees, crafting networks, tech trees
Data visualization	Family trees, organization charts, linked datasets
AI / Pathfinding	Weighted graph structures
Education	Teaching recursion, graph traversal, OOP
📌 Summary

This project is an example of an object-oriented and recursive graph system built with Java. It supports:

custom node-to-node relationships

hierarchical visualization

full and grouped numeric calculations

It provides a strong foundation for building more advanced graph-based applications.

Hello, my name is Salih efe Tosunbayraktar. I am a Computer Engineer Student and Im studying in Turkiye/Sivas/Cumhuriyet Univercity.
This Project i focused on object-oriented programming and data-driven systems.
I designed a customizable node–connection architecture that supports hierarchical structures, visual connection mapping, and advanced data aggregation.

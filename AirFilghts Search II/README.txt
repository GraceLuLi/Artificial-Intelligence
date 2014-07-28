/**
Name: Lu Li
student ID: lli619
email: lli619@usc.edu
**/

Brief Description: 

there are three .java files: Node.java, graph2.java, main.java 
      and three .class files: Node.class, graph2.class, main.class
Node.java: define the Node class, with Attributes of 
           label ---- the city name
           index ---- give every city a index, which will be used in Graph
           parent ----- the parent node of this node
           Edistance ----- the estimated distance from this node to destination(in this programm it's NewYork)
           Efare ----- the estimated air fare from this node to destination(in this programm it's NewYork)
           value ---- the fare or distance cost (g(n)+h(n)), only used in A* search
           visited ---- true if this node is visited, false if not
Graph.java: there are two kinds of functions in Graph
            1st: for setup
                 including setRootNode(), getRootNode(),addNode(),connectNode()
            2nd: for searching
                 search(method, cost): method could be 'greedy' or 'Astar' and cost could be 'fare' or 'distance'
                 p.s. they are only different when sorting the queue (sort by cost from minimal to maxmum, they use different costs)
                      greedy search uses greedySortNopen(cost) while A* search uses AstarSortNopen(cost)
main.java: setup nodes and connect nodes, search nodes and output

Instructions on compile and execute code:
/******
compile main.java with command --> javac main.java
execute main.java with command --> java main
it will show the outcome as follows: 
Greedy Search (distance):
Los Angeles  Dallas  Atlanta  DC  Chicago  New York  
Number of nodes expanded:  6
Traveling distance: 3866 miles
Air Fare: 1095 dollars

A* Search (distance):
Los Angeles  Dallas  Atlanta  DC  New Orleans  San Francisco  Denver  Chicago  New York  
Number of nodes expanded:  9
Traveling distance: 2933 miles
Air Fare: 816 dollars

Greedy Search (air fare):
Los Angeles  Dallas  Atlanta  DC  Chicago  New York  
Number of nodes expanded:  6
Traveling distance: 3866 miles
Air Fare: 1095 dollars

A* Search (air fare):
Los Angeles  Dallas  Seattle  San Francisco  DC  Atlanta  Chicago  New Orleans  New York  
Number of nodes expanded:  9
Traveling distance: 3415 miles
Air Fare: 757 dollars
*******/
it ends;

if want to search again, please rerun it with command --> java main
# -*- coding: utf-8 -*-
"""path.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1j3P2g-l4UhlPLuKEdvvjyH9tZn0QvfUn
"""

import networkx.generators.small
import matplotlib.pyplot as plt
import networkx as nx

g = networkx.generators.small.krackhardt_kite_graph()
nx.draw(g, with_labels=True)
plt.show()

n=g.nodes()
print (n)

g.number_of_edges()

g.adj

from networkx.algorithms import traversal

def DFS_nodes(graph, node, visited=[]):
  print("node", node)
  visited.append(node)
  print("Visited", visited)
  for neighbor in graph[node]:
        print("neighbor", neighbor)
        if not neighbor in visited:
              DFS_nodes(graph, neighbor, visited)
  return visited
DFS_nodes(g, 0)

def DFS_edges(graph, node, visited=[], edges=[]):
  print ("node", node)
  visited.append(node)
  print("visited", visited)
  for ni in graph[node]:
    print ("ni", ni)
    if not ni in visited:
     edges.append((node, ni))
     DFS_edges(graph, ni, visited, edges)
  return edges
DFS_edges(g, 0)

list(g.edges())

n=g.adj

edges = traversal.dfs_edges(g)
edges
list(edges)

traversal.dfs_successors(g)

tree = traversal.dfs_tree(g)
list(tree.successors(0))

tree.succ

from networkx import algorithms
algorithms.shortest_path(g,1,8)

algorithms.average_shortest_path_length(g)

dict(nx.all_pairs_shortest_path(g))

algorithms.dijkstra_path(g, 1, 9)
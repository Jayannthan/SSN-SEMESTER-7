
import matplotlib.pyplot as plot
import networkx as nx

g = nx.read_pajek("/egypt_retweets.net")
len(g)

nx.number_connected_components(g)

x = [len(c) for c in nx.connected_components(g)]
plot.hist(x)

cc = nx.connected_components(g)
[len(c) for c in nx.connected_components(g) if len(c) > 10]
print(list(cc))

# Define a function to trim edges based on weight
def trim_edges(g, weight=1):
    g2 = nx.Graph()
    for f, to, edata in g.edges(data=True):
        if edata['weight'] > weight:
            g2.add_edge(f, to, edata)
    return g2
	
def island_method(g, iterations=5):
    weights = [edata['weight'] for f, to, edata in g.edges(data=True)]
    mn = int(min(weights))
    mx = int(max(weights))
    # compute the size of the step, so we get a reasonable step in iterations
    step = int((mx-mn)/iterations)
    return [[threshold, trim_edges(g, threshold)] for threshold in range(mn, mx, step)]


cc = nx.node_connected_component(g)
islands = island_method(cc)

for i in islands:
	print(i[0], len(i[1]), len(nx.connected_component_subgraphs(i[1])))

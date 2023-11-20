

import networkx as nx
import matplotlib.pyplot as plt

import urllib.request


def read_lj_friends(g, name):
    # fetch the friend-list from LiveJournal
    response = urllib.request.urlopen(
        'http://www.livejournal.com/misc/fdata.bml?user='+name)
    # walk through the lines in the response and add each one as an edge in a network
    for line in response.readlines():
        print(line)
        if line.startswith(b'#'):
            continue
        parts = line.split()
        if len(parts) == 0:
            continue
        # add the edge to the network
        if parts[0] == '<':
            g.add_edge(parts[1], name)
        else:
            g.add_edge(name, parts[1])


def snowball_sampling(g, center, max_depth, current_depth, friends_list=[]):
    print(center, current_depth, max_depth, friends_list)
    if current_depth == max_depth:
        print('out of depth')
        return friends_list

    if center in friends_list:
        return friends_list
    else:
        friends_list.append(center)

    read_lj_friends(g, center)
	
    for node in g.neighbors(center):
        print(str(node))
        friends_list = snowball_sampling(g, str(node), max_depth, current_depth+1, friends_list)
    return friends_list


g = nx.Graph()
read_lj_friends(g, 'togranada')
snowball_sampling(g, 'togranada', 2, 0)
nx.draw(g)
plt.savefig("friends.png")

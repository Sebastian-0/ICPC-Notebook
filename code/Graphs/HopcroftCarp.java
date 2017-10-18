public class HopcroftCarp {
  public static final int INF = Integer.MAX_VALUE;
  public static final int NIL = 0;
  Node[] L, R, G;
  
  // All indices for the nodes must be unique!
  public HopcroftCarp(Node[] L, Node[] R) {
    this.L = L;
    this.R = R;
    G = new Node[L.length + R.length + 1];
    for (Node n : L)
      G[++n.index] = n;
    for (Node n : R)
      G[++n.index] = n;
    G[NIL] = new Node(0);
  }
  
  public Set<Node> solveMinVTC() {
    Map<Node, Node> Lm = solveMatching();
    Map<Node, Node> Rm = Lm.entrySet().stream().
        collect(Collectors.toMap(Map.Entry::getValue,
            Map.Entry::getKey));
    
    Queue<Node> queue = new LinkedList<>();
    boolean[] Z = new boolean[L.length + R.length + 1];
    for (Node n : L) {
      if (!Lm.containsKey(n)) {
        Z[n.index] = true;
        queue.add(G[n.index]);
      }
    }
    
    while (!queue.isEmpty()) {
      Node u = queue.poll();
      Node parent = Lm.getOrDefault(u, null);
      for (Edge e : u.edges) {
        Node v = e.end == u ? e.start : e.end;
        if (!Z[v.index] && v != parent) { // Parent check unnecessary?
          Z[v.index] = true;
          if (Rm.containsKey(v)) {
            Node w = Rm.get(v);
            if (!Z[w.index]) {
              Z[w.index] = true;
              queue.add(w);
            }
          }
        }
      }
    }
    
    Set<Node> K = new HashSet<>();
    for (Node node : L) {
      if (!Z[node.index])
        K.add(node);
    }
    for (Node node : R) {
      if (Z[node.index])
        K.add(node);
    }
    return K;
  }
  
  public Map<Node, Node> solveMatching() {
    int[] pairs = new int[L.length + R.length + 1];
    int[] distance = new int[L.length + R.length + 1];
    
    while (bfs(G, L, pairs, distance)) {
      for (Node n : L) {
        if (pairs[n.index] == NIL)
          dfs(G, pairs, distance, n);
      }
    }
    Map<Node, Node> matches = new HashMap<>();
    for (Node n : L) {
      if (pairs[n.index] != NIL)
        matches.put(n, G[pairs[n.index]]);
    }
    return matches;
  }
  
  private boolean bfs(Node[] G, Node[] L, int[] pair,
      int[] distance) {
    Queue<Node> queue = new LinkedList<>();
    for (Node u : L) {
      if (pair[u.index] == NIL) {
        distance[u.index] = 0;
        queue.offer(u);
      } else {
        distance[u.index] = INF;
      }
    }
    
    distance[NIL] = INF;
    while (!queue.isEmpty()) {
      Node u = queue.poll();
      if (distance[u.index] < distance[NIL]) {
        for (Edge e : u.edges) {
          Node v = e.end == u ? e.start : e.end;
          if (distance[pair[v.index]] == INF) {
            distance[pair[v.index]] = distance[u.index] + 1;
            queue.offer(G[pair[v.index]]);
          }
        }
      }
    }
    return distance[NIL] < INF;
  }
  
  private boolean dfs(Node[] G, int[] pair, int[] distance,
      Node u) {
    if (u.index != NIL) {
      for (Edge e : u.edges) {
        Node v = e.end == u ? e.start : e.end;
        if (distance[pair[v.index]] == distance[u.index] + 1 &&
            dfs(G, pair, distance, G[pair[v.index]])) {
          pair[v.index] = u.index;
          pair[u.index] = v.index;
          return true;
        }
      }
      distance[u.index] = INF;
      return false;
    }
    
    return true;
  }
}
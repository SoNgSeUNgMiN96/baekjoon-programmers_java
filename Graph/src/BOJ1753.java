import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1753 {

    static  int d[];
    static class edge implements Comparable<edge>{
        int node;
        int w;
        edge(int node , int w){
            this.node = node;
            this.w = w;
        }

        @Override
        public int compareTo(edge o) {
            if(this.w > o.w)
                return 1;
            else if (this.w == o .w)
                return 0;
            else return -1;
        }
    }

    public static void main(String[] args) throws Exception{
        int V, E, K, u, v, w;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        ArrayList <edge> Nodes[] = new ArrayList[V+1];
        d= new int[V+1];

        for (int i = 1; i < V+1; i++) {
            Nodes[i] = new ArrayList<edge>();
            d[i] = 900001;
        }


        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            Nodes[u].add(new edge(v,w));
        }

        dijkstra(K, Nodes);

        for (int i = 1; i < V+1; i++)
        {
            if(d[i]==900001)
                bw.write("INF\n");
            else
                bw.write(d[i]+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static void dijkstra(int start ,ArrayList <edge> Nodes[]) {
        PriorityQueue<edge> edgePriorityQueue = new PriorityQueue<>();
        edgePriorityQueue.add(new edge(start,0));
        d[start] = 0;

        while (edgePriorityQueue.size()>0) {

            edge temp = edgePriorityQueue.poll();
            int current = temp.node;

            int distance = temp.w;
            for (int i = 0; i < Nodes[current].size(); i++) {
                int to = Nodes[current].get(i).node;
                if(distance + Nodes[current].get(i).w<d[to]) {
                    d[to] = distance + Nodes[current].get(i).w;

                    edgePriorityQueue.add(new edge(to, d[to]));
                }
            }

        }
    }
}

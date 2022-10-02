// 다익을 2번 -> 가는거 + 오는거 최대 구하기

package dijk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P_1238 {
    static int N, M, X;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    static int[] dist_party;
    static int[] dist_total;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N+1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
        }

        dist_party = new int[N+1];
        dist_total = new int[N+1];
        visited = new boolean[N+1];


        // 집에서 파티장으로
        for (int i = 1; i <= N; i++) {
            dijkstra_party(i, X);
            dist_total[i] += dist_party[X];
        }

        // 파티장에서 집으로
        for (int i = 1; i <= N; i++) {
            dijkstra_party(X, i);
            dist_total[i] += dist_party[i];
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            if(max < dist_total[i]){
                max = dist_total[i];
            }
        }

        System.out.println(max);
    }

    static void dijkstra_party(int start ,int end){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost)); //우선순위큐 cost 오름차순

        pq.offer(new Node(start, 0));
        // 거리를 모두 최대값으로 설정해준다
        Arrays.fill(dist_party, Integer.MAX_VALUE);
        // 시작점 거리 0
        dist_party[start] = 0;

        while(!pq.isEmpty()){
            Node tmp = pq.poll();
            if(dist_party[tmp.node] < tmp.cost){
                continue;
            }
            for (int i = 0; i < graph.get(tmp.node).size(); i++) {
                Node nextnode = graph.get(tmp.node).get(i);
                if(dist_party[nextnode.node] > dist_party[tmp.node] + nextnode.cost){
                    dist_party[nextnode.node] = dist_party[tmp.node] + nextnode.cost;
                    pq.offer(new Node(nextnode.node, nextnode.cost));
                }
            }

        }
    }

    static class Node implements Comparator<Node> {
        int node; // 도착
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        // cost별로 오름차순 정렬
        @Override
        public int compare(Node o1, Node o2) {
            return o1.cost - o2.cost;
        }
    }
}

/**
 * 테스트케이스
 * 2 -> 5 : 5    2 -> 6 : 6
 * 2 -> 1 : 1    1 -> 3 : 3    3 -> 5 : 7    -> 합 : 11
 *                             3 -> 6 : 2    -> 합 : 6 (o)  -> answer : 6
 * 2 -> 3 : 4    3 -> 1 : 1    1 -> 5 : 6    -> 합 : 11
 *                             1 -> 6 : 5    -> 합 : 10
 * g h 를 지났는지 확인하는법
 * 다익스트라 메서드를 돌려서 확인
 * dijk(시작, 목적지) = dijk(시작, g) + dijk(g, h) + dijk(h, 목적지)
 *              or  = dijk(시작, h) + dijk(h, g) + dijk(g, 목적지)
 * 케이스 2개 중 최소값이 dijk(시작, 목적지)이면 g h를 지났다 판단
 */

// 6636ms

package dijk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P_9370 {
    static int T;
    static int n, m, t; // 교차로, 도로, 목적지후보개수
    static int s, g, h; // 출발지, 거치는 노드 g,h
    static int[] endpoint;
    static int[] dp;
    static ArrayList<ArrayList<Node>> graph;
    static ArrayList<Integer> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            endpoint = new int[t];

            st = new StringTokenizer(br.readLine(), " ");
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph.get(a).add(new Node(b, d));
                graph.get(b).add(new Node(a, d));
            }
            for (int i = 0; i < t; i++) {
                endpoint[i] = Integer.parseInt(br.readLine());
            }
            dp = new int[n+1];

            solution();
        }
    }

    static void solution(){
        answer = new ArrayList<>();

        for (int i = 0; i < endpoint.length; i++) {
            int dijk_total = dijkstra(s, endpoint[i]);
            int dijk_g_h = dijkstra(s, g) + dijkstra(g, h) + dijkstra(h, endpoint[i]);
            int dijk_h_g = dijkstra(s, h) + dijkstra(h, g) + dijkstra(g, endpoint[i]);

            if(dijk_total == Math.min(dijk_g_h, dijk_h_g)){
                answer.add(endpoint[i]);
            }
        }

        Collections.sort(answer);
        for(int val : answer){
            System.out.print(val + " ");
        }
        System.out.println("");
    }

    static int dijkstra(int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node tmp = pq.poll();
            if(dp[tmp.end] < tmp.distance){
                continue;
            }
            for (int i = 0; i < graph.get(tmp.end).size(); i++) {
                Node nextNode = graph.get(tmp.end).get(i);
                if(dp[nextNode.end] > dp[tmp.end] + nextNode.distance){
                    dp[nextNode.end] = dp[tmp.end] + nextNode.distance;
                    pq.offer(new Node(nextNode.end, nextNode.distance));
                }
            }
        }
        return dp[end];
    }

    static class Node implements Comparable<Node>{
        int end; // 목적지
        int distance; // 거리

        public Node(int end, int distance) {
            this.end = end;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }
}

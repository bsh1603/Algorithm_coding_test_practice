package dijk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P_10282 {
    static int T;
    static int n, d, c;
    static ArrayList<ArrayList<Node>> graph;
    static int[] dp;
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken()); // 컴퓨터 수
            d = Integer.parseInt(st.nextToken()); // 의존성 개수
            c = Integer.parseInt(st.nextToken()); // 시작점

            graph = new ArrayList<ArrayList<Node>>();
            for (int i = 0; i <= n+1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine()," ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph.get(b).add(new Node(a, s));
            }

            // 1번부터 n번까지 -> n+1
            // 감염될 때까지의 최소시간을 담는 배열
            dp = new int[n+1];
            // 감염된 컴퓨터 == true
            visited = new boolean[n+1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            // 감염된 컴퓨터 개수 세기
            int cnt = 0;

            dijkstra(c);

            int min_time = 0;
            for (int i = 0; i < dp.length; i++) {
                // 감염되지 않은 컴퓨터의 경우 값이 무한 -> 제외해줘야함
                if(min_time < dp[i] && dp[i] != Integer.MAX_VALUE){
                    min_time = dp[i];
                }
            }
            for (int i = 0; i < visited.length; i++) {
                if(visited[i]){
                    cnt++;
                }
            }

            System.out.println(cnt + " " + min_time);
        }
    }

    // dp 배열을 반환하는 함수
    static int[] dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.time, o2.time)); // time별 오름차순정렬

        pq.offer(new Node(start, 0));
        dp[start] = 0;

        while(!pq.isEmpty()){
            Node tmp = pq.poll();
            visited[tmp.node] = true;
            if(dp[tmp.node] < tmp.time){
                continue;
            }
            for (int i = 0; i < graph.get(tmp.node).size(); i++) {
                Node nextNode = graph.get(tmp.node).get(i);
                if(dp[nextNode.node] > dp[tmp.node] + nextNode.time){
                    dp[nextNode.node] = dp[tmp.node] + nextNode.time;
                    pq.offer(new Node(nextNode.node, nextNode.time));
                }
            }
        }
        return dp;
    }
    
    static class Node implements Comparator<Node> {
        int node; // 가는곳
        int time;

        public Node(int node, int time) {
            this.node = node;
            this.time = time;
        }

        @Override
        public int compare(Node o1, Node o2) {
            return o1.time - o2.time;
        }
    }
}

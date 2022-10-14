/**
 * 최소비용 구하기
 * 다익스트라로 풀이
 */

package dijk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P_1916 {
    static int N, M;
    static ArrayList<ArrayList<Node>> graph; // 입력받은 노드와 간선들을 나타내는 graph
    static int[] dp; // 시작점에서 각 노드까지 최단거리를 저장하는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        graph = new ArrayList<ArrayList<Node>>();
        for (int i = 0; i < N+1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b,c));
        }

        st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dijkstra(start);
        System.out.println(dp[end]);
    }

    // 다익스트라 실행 함수
    static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
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
    }
    
    static class Node implements Comparable<Node>{
        int end; // 갈 수 있는 곳
        int distance; // 간선의 가중치, 거리

        public Node(int end, int distance) {
            this.end = end;
            this.distance = distance;
        }

        // 오름차순 정렬
        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }
}

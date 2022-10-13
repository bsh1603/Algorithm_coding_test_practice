/**
 * 최소 스패닝 트리
 *
 * 입력 그래프를 cost순으로 오름차순 정렬
 * union - find 사용
 * ex) 1 2 1
 *     2 3 2
 *     1 3 3
 *     -> 1과 2를 union해 연결 : 조상 1
 *     -> 2와 3을 union해 연결 : 조상 2 -> 조상 1
 *     -> 1과 3은 이미 같은 조상을 가지므로 연결X
 * union을 할지 말지 -> 같은 조상 가지는지 확인(isSameParent)
 */

package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P_1197 {
    static int V, E;
    static ArrayList<Node> graph;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 각각의 node들의 조상값을 자기자신으로 초기화
        parent = new int[V+1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        graph = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.add(new Node(a, b, c));
        }

        Collections.sort(graph);

        // 둘이 다른 부모 즉 연결이 안되어있으면 연결 및 answer에 +
        // 둘이 같은 부모 즉 연결이 되어있으면 pass
        int answer = 0;
        for(Node tmp : graph){
            if(!isSameParent(tmp.start, tmp.end)){
                union(tmp.start, tmp.end);
                answer += tmp.cost;
            }
        }

        System.out.println(answer);
    }

    static int find(int n){
        if(parent[n] == n) {
            return n;
        }
        else{
            return parent[n] = find(parent[n]);
        }
    }

    static void union(int a, int b){
        int fa = find(a);
        int fb = find(b);

        if(fa != fb){
            parent[fb] = fa;
        }
    }

    // 두 값이 같은 부모를 가지고 있는지 확인
    static boolean isSameParent(int a, int b){
        int fa = find(a);
        int fb = find(b);

        if(fa == fb){
            return true;
        }
        else{
            return false;
        }
    }

    // cost순으로 오름차순 정렬
    static class Node implements Comparable<Node>{
        int start;
        int end;
        int cost;

        public Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}

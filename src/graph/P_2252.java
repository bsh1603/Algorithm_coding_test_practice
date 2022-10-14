/**
 * 줄 세우기
 * 위상정렬 문제
 *
 * 문제에서 제시한 노드들은 단방향 연결로 생각한다.
 * 노드의 차수를 기록할 degree 배열을 생성한다.
 * 노드의 차수가 0이 되면 답에 저장한다.
 *
 * Queue를 이용하여 그래프를 탐색한다.
 */

package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P_2252 {
    static int N, M;
    static int[] degree; // 노드의 차수를 저장하는 배열
    static ArrayList<ArrayList<Integer>> graph; // 입력 노드들의 그래프
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<ArrayList<Integer>>();
        degree = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 노드를 연결하고 뒤에 있어야 하는 노드의 차수를 1 증가시킨다.
            graph.get(a).add(b);
            degree[b]++;
        }

        // 일단 노드의 차수가 0인 노드들을 큐에 다 넣는다.
        for (int i = 1; i < degree.length; i++) {
            if(degree[i] == 0){
                queue.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
            // 차수가 0인 노드를 뽑아와 그 뒤에 와야하는(연결된) 노드들을 탐색한다.
            int tmp = queue.poll();
            sb.append(tmp + " ");

            // 차수를 하나씩 빼준다.
            for (int i = 0; i < graph.get(tmp).size(); i++) {
                degree[graph.get(tmp).get(i)]--;

                if(degree[graph.get(tmp).get(i)] == 0){
                    queue.offer(graph.get(tmp).get(i));
                }
            }
        }

        System.out.println(sb);
    }
}


















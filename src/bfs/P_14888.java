/**
 * dfs : 완전탐색
 * 입력받은 연산자 개수 배열 : 2 1 1 1 이라면
 * operation_num = [2, 1, 1, 1]
 * 1 : +,   2 : -   3 : *   4 : /
 * operation = {1, 1, 2, 3, 4}
 */

package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P_14888 {
    static int N;
    static int[] num; // 입력받는 숫자 저장
    static int[] operation_num; // 입력받는 연산자 개수 숫자 저장
    static boolean[] visited; // 방문여부 확인
    static ArrayList<Integer> operation; // 연산자 개수만큼 연산자 저장하는 리스트
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        num = new int[N];
        operation_num = new int[4];
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            operation_num[i] = Integer.parseInt(st.nextToken());
        }

        operation = new ArrayList<>();
        for (int i = 1; i <= operation_num.length; i++) {
            if (operation_num[i-1] > 0){
                for (int j = 1; j <= operation_num[i-1]; j++) {
                    operation.add(i);
                }
            }
        }
        visited = new boolean[operation.size()];

        dfs(1, num[0]);
        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int depth, int sum){
        if(depth == num.length){
            max = Math.max(max, sum);
            min = Math.min(min, sum);
        }
        else{
            for (int i = 0; i < operation.size(); i++) {
                if(visited[i] == false){
                    visited[i] = true;
                    int new_sum = calculate(sum, num[depth], operation.get(i));
                    dfs(depth+1, new_sum);
                    visited[i] = false;
                }

            }
        }
    }

    // 1:+, 2:-, 3:*, 4:/ 계산하는 메서드
    static int calculate(int a, int b, int c){
        int sum = 0;
        if(c == 1){
            sum = a+b;
        }
        else if(c == 2){
            sum = a-b;
        }
        else if(c == 3){
            sum = a*b;
        }
        else{
            sum = a/b;
        }
        return sum;
    }
}

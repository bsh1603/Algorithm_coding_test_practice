// 각 열마다 들어가고 안들어가고 체크
// 이차원 배열 dp[][]
// 최대값만 dp에 저장
// dp[0][i] = Math.max(dp[1][i-1] + map[0][i] , dp[1][i-2] + map[0][i])
// dp[1][i] = Math.max(dp[0][i-1] + map[1][i] , dp[0][i-2] + map[0][i])
// 왼쪽 위 or 아래 대각선 과 그 옆 왼쪽 칸을 비교
// 708ms

package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_9465 {
    static int T, N;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            N = Integer.parseInt(br.readLine());
            map = new int[2][N];
            dp = new int[2][N];
            StringTokenizer st;
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 초기화
            dp[0][0] = map[0][0];
            dp[1][0] = map[1][0];

            // N = 1 일 경우 arrayindexofbound
            if(N > 1){
                // 초기화
                dp[0][1] = dp[1][0] + map[0][1];
                dp[1][1] = dp[0][0] + map[1][1];

                // 왼쪽 대각선과 그 대각선의 왼쪽을 보면서 비교
                for (int i = 2; i < N; i++) {
                    dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + map[0][i];
                    dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + map[1][i];
                }
            }

            System.out.println(Math.max(dp[0][N-1], dp[1][N-1]));
        }
    }
}














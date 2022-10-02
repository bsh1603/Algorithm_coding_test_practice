// 날짜 별로 조건에 맞춰 최대값을 dp[]에 저장
// N=1 : N+T1 = 4 -> dp[N+T1] = Math.max(dp[N+T1], dp[N] + P1) -> 0 vs 0+10
// N=2 : N+T2 = 7 -> dp[N+T2] = Math.max(dp[N+T2], dp[N] + P2) -> 0 vs 0+20
// N=3 : N+T3 = 4 -> dp[N+T3] = Math.max(dp[N+T3], dp[N] + P3) -> 10 vs 0+10
// N=4 : N+T4 = 5 -> dp[N+T4] = Math.max(dp[N+T4], dp[N] + P4) -> 0 vs 10+20
// 조건 : N+T1 <= 8 (7일차에 T=1일 경우 일할 수 있음)

// 최대값을 dp에 저장하며 들고 다녀야함 -> 안했더니 예제4번을 틀림
// dp[N+TN] = Math.max(dp[N+TN] , max + PN)

// 904ms

package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_15486 {
    static int N;
    static int[][] map;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[N+1][2];
        dp = new int[N+2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int max = 0;

        for (int i = 1; i <= N; i++) {
            int n = i+map[i][0];

            // max를 들고 다녀야됨
            if(max < dp[i]){
                max = dp[i];
            }

            if(n <= N+1){
                dp[n] = Math.max(dp[n], max + map[i][1]);
            }
        }

        // dp 배열을 돌며 최대값(답) 구하기
        int answer = 0;
        for (int i = 0; i < dp.length; i++) {
            if(answer < dp[i]){
                answer = dp[i];
            }
        }
        System.out.println(answer);
    }
}















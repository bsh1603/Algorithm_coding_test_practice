// 196ms
// 물건을 넣냐 안넣냐로 나눔
// 이차원 표에서 하나 넣을 때마다 값을 업데이트

package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_12865 {
    static int N, K;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N+1][K+1];
        Things[] input = new Things[N+1];

        for (int i = 0; i < input.length; i++) {
            input[i] = new Things(0, 0);
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            input[i].w = Integer.parseInt(st.nextToken());
            input[i].v = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                // 담을 수 없음
                // dp(n) = dp(n-1)
                if(input[i].w > j){
                    dp[i][j] = dp[i-1][j];
                }
                // 담을 수 있음
                // dp(n) = dp(n-1) + v(n) - w(n)
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-input[i].w]+input[i].v);
                }
            }
        }

        System.out.println(dp[N][K]);
    }

    static class Things{
        int w;
        int v;

        public Things(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }
}

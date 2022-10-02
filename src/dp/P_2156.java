// n/3 개는 못먹음
// 부분합 배열?
// 총 3가지 경우의 수 : oox / oxo / xoo
// 1차원 dp[]에 max값만 넣어줌
// oox ->  dp[i-1]
// oxo -> dp[i-2] + input[i]
// xoo -> dp[i-3] + input[i-1] + input[i] (단 i>=3)이여야 함

package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P_2156 {
    static int N;
    static int[] input;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = new int[N];
        dp = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }
        // 초기화
        dp[0] = input[0];

        // 조건문을 안 걸어줄 경우 -> runtime error
        // n = 1인경우 dp[1], input[1]이 존재하지 않음
        if(N > 1){
            // 초기화
            dp[1] = input[0] + input[1];

            for (int i = 2; i < N; i++) {
                // oox
                int a = dp[i-1];
                // oxo
                int b = dp[i-2] + input[i];
                // xoo
                int c = 0;
                if(i>=3){
                    c = dp[i-3] + input[i-1] + input[i];
                }
                else{
                    c = input[i-1] + input[i];
                }

                // 3개 조건 최대값 비교
                int max = Math.max(a,b);
                dp[i] = Math.max(max, c);
            }
        }

        System.out.println(dp[N-1]);
    }
}















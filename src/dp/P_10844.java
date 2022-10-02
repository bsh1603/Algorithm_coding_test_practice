// 1~8까지 : dp[길이][시작숫자] = dp[길이-1][시작숫자-1] + dp[길이-1][시작숫자+1]
// 0 : dp[길이-1][1]
// 9 : dp[길이-1][8]
// 10억으로 나눈 답을 요구 -> 자료형 long형
// long형으로 진행하다가 계속 틀려서 dp에 값을 담기전 mod로 나눠줬음 -> int 형으로 진행
// 124ms

package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P_10844 {
    static int N;
    static int[][] dp;
    static int mod = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][10];
        int sum = 0;

        // 길이가 1인 경우 초기화
        dp[1][0] = 0;
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }
        // 길이가 2이상인 경우 dp 진행
        if(N > 1){
            for (int i = 2; i <= N; i++) {
                for (int j = 0; j < 10; j++) {
                    if(j == 0){
                        dp[i][j] = dp[i-1][1] % mod;
                    }
                    else if(j == 9){
                        dp[i][j] = dp[i-1][8] % mod;
                    }
                    else{
                        dp[i][j] = ((dp[i-1][j-1] % mod) + (dp[i-1][j+1] % mod)) % mod;
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            sum = (sum + dp[N][i]) % mod;
        }
        System.out.println(sum);
    }
}















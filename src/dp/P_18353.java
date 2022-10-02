package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_18353 {
    static int N;
    static int[] soldier; // 전투력 입력 배열
    static int[] dp; // i번째 병사 배치할 때 최대 병사 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        soldier = new int[N];
        dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N; i++) {
            soldier[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = 1;

        for (int i = 1; i < soldier.length; i++) {
            int max = 0;
            for (int j = i-1; j >= 0; j--) {
                if(soldier[j] > soldier[i]){
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
        }

        int max = 0;
        for(int val : dp){
            if(max < val){
                max = val;
            }
        }

        System.out.println(N - max);
    }
}

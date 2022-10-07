/**
 * <퇴사>
 *
 * 1일차부터 n일차까지 생각
 * 각각 일했을 때 다음으로 일할 수 있는 해당 날짜에 수입 max를 갱신해준다
 *      -> max : 벌 수 있는 수입
 * next = i + counceller[i].time
 * dp[next] = Math.max(dp[next], max + counceller[i].pay)
 * ex) 7일차에 완료기간이 1이라면 일할 수 있으므로 dp배열은 n+2로 설정해준다(1~8까지 생각)
 */

package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_14501 {
    static int N;
    static work[] counceller; // 백준이 상담사가 일하는 정보를 저장하는 배열
    static int[] dp; // 최대 수입을 얻기 위하여 갱신해주는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        counceller = new work[N+1];
        for (int i = 0; i < counceller.length; i++) {
            counceller[i] = new work(0,0);
        }

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            counceller[i].time = t;
            counceller[i].pay = p;
        }

        // 돈 받는 건 주어진 N일의 마지막 다음날까지 받을 수 있음
        dp = new int[N+2];

        int max = 0; // 최대값을 계속 들고 다닌다
        for (int i = 1; i <= N; i++) {
            int next = counceller[i].time + i;

            if(max < dp[i]){
                max = dp[i];
            }

            // 현재 날짜 + 완료기간은 N+1을 넘으면 안된다
            if(next <= N+1){
                dp[next] = Math.max(dp[next], max + counceller[i].pay);
            }
        }

        // dp배열에서 최대값 구하기 -> 정답
        int answer = 0;
        for(int val : dp){
            answer = Math.max(val, answer);
        }
        System.out.println(answer);
    }

    // 얼마나 걸리고 얼마나 받는지를 나타내는 클래스
    static class work{
        int time;
        int pay;

        public work(int time, int pay) {
            this.time = time;
            this.pay = pay;
        }
    }
}

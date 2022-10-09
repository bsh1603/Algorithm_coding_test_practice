/**
 * <부분합>
 * 투 포인터
 *
 * sum < S -> end++, sum += input[end]
 * sum >= S -> sum -= input[start]
 *          -> len = end - start + 1
 *          -> ans = Math.min(ans, len)
 *          -> start++
 */

package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_1806 {
    static int N, S;
    static int[] input; // 입력받은 숫자들 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        input = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0; // 시작점
        int end = 0; // 끝점
        int sum = input[start];
        int answer = Integer.MAX_VALUE;
        boolean flag = false; // 답을 도출할 수 있는지 확인하는 변수 -> 답 안나오면 0

        while(end < N){

            if(sum < S){
                end++;
                if(end == N){
                    break;
                }
                sum += input[end];
            }
            else{
                int len = end - start + 1;
                sum -= input[start];
                answer = Math.min(answer, len);
                start++;
                flag = true;
            }
        }

        if(flag){
            System.out.println(answer);
        }
        else{
            System.out.println(0);
        }
    }
}

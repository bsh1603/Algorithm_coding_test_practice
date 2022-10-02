// 수식에서 최대의 괄호 갯수 : (N+1) / 4  -> dfs 조건
// ( 추가 -> 3칸 뒤 무조건 ) 추가
// ( 추가 조건 : index 0
//             앞이 연산자
//             남아 있는 len >= 3 -> len-3까지는 가능
// 계산은 연산자 수 만큼 진행 -> 탈출조건

package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P_16637 {
    static int N;
    static String input;
    static char[] math;
    static int max = Integer.MIN_VALUE;

    static void dfs(int depth, int sum){
        if(depth >= N){
            max = Math.max(max, sum);
            return;
        }
        else{
            // 괄호 없이 바로 계산
            dfs(depth+2, cal(math[depth-1], sum, math[depth]-'0'));

            // 괄호 있이 계산 -> 오른쪽에 괄호 생성
            if(depth + 2 < N){
                int right = cal(math[depth+1], math[depth]-'0', math[depth+2]-'0');
                sum = cal(math[depth-1], sum, right);
                dfs(depth+4, sum); // +4인 이유 -> 괄호 중첩 불가능
            }
        }
   }

    static int cal(char c, int x, int y){
        if(c == '+'){
            return x + y;
        }
        else if(c == '*'){
            return x * y;
        }
        else{
            return x - y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = br.readLine();
        math = new char[N];

        for (int i = 0; i < N; i++) {
            math[i] = input.charAt(i);
        }

        // 맨 앞 괄호는 필요없음
        dfs(2, math[0]-'0');
        System.out.println(max);
    }
}

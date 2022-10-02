package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_1107 {
    static int N, M;
    static String origin; // 이동할 채널번호 N을 String형으로
    static String change = ""; // 전체 탐색을 위한 String
    static boolean[] btn; // 누를 수 없는 번호를 받아 boolean형으로 저장(true로 바꿈)
    static int min = Integer.MAX_VALUE;

    // 풀이 방법 dfs
    static void dfs(String str, int depth){
        // 탈출조건
        // depth가 6, 즉 0레벨부터 시작하므로 change가 7자리가 되면 반환
        if(depth == 6){
            return;
        }
        else{
            for (int i = 0; i < btn.length; i++) {
                // 0~9까지 btn 배열을 돌며 false인 경우(누를 수 있는 버튼)
                if(!btn[i]){
                    // 0부터 6자리 수까지 탐색하기 위하여 str에 i를 붙여 새로운 String change를 생성
                    change = str + Integer.toString(i);
                    // 최소값 구하기
                    min = Math.min(min, cal(change, Integer.parseInt(change)));
                    dfs(change, depth+1);
                }
            }
        }
    }

    // 채널 이동 수 구하기 : 현재 채널 번호 길이 + (이동할 채널 번호 - 현재 채널 번호)
    static int cal(String str, int num){
        return str.length() + Math.abs(N - num);
    }

    public static void main(String[] args) throws IOException {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        origin = String.valueOf(N);

        btn = new boolean[10];
        // M이 0일 경우 btn[] 배열에 저장할 필요X
        if(M != 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int number_false = Integer.parseInt(st.nextToken());
                btn[number_false] = true;
            }
        }

        // +, - 두 번 이내로 가능한 경우를 계산해주어야 함
        // ex) 101 / 2 / 4 5
        int just = Math.abs(N - 100);
        min = Math.min(just, min);

        // 100일 경우 0을 반환환
        if(N == 100){
            min = 0;
        }

        dfs("", 0);
        System.out.println(min);

    }
}

// 모든 숫자를 탐색하지 않고 입력된 N에서 각 자리마다 비교하여 숫자를 바꿔줄 경우
// 80000같은 경우 앞의 8만 바뀌어 답이 나오지 않아 모든 자리수 0~9까지 완전탐색을 진행
package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_15684 {
    static int N, M, H;
    static boolean possible[][];
    static boolean flag = false; // 더 이상 탐색을 진행하지 않기 위해 설정

    // 사다리 이동 함수
    static boolean move(){
        for (int i = 1; i <= N; i++) {
            int cur = i; // 1~N까지 초기 위치 설정
            for(int j = 1; j<= H+1; j++) {
                // 배열 왼쪽 칸이 true일 경우 위치를 왼쪽으로 이동
                if(possible[j][cur-1]==true){
                    cur --;
                }
                // 배열 해당 칸이 true일 경우 위치를 오른쪽으로 이동
                else if(possible[j][cur] == true){
                    cur ++;
                }

            }
            // 초기 위치와 이동한 위치가 같다.
            if(i == cur){
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }

    static void dfs(int depth, int cnt){
        if(flag){
            return;
        }

        if(cnt == depth){
            if(move()){
                flag = true;
            }
        }
        else{
            for (int i = 1; i < N; i++) {
                for (int j = 1; j <= H; j++) {
                    // 왼쪽, 오른쪽, 해당 칸이 모두 false일 경우 사다리 설치
                    if(possible[j][i-1]==false && possible[j][i+1]==false && possible[j][i]==false){
                        possible[j][i] = true;
                        dfs(depth+1, cnt);
                        possible[j][i] = false;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        possible = new boolean[H+2][N+1];

        // 문제의 최대 답 값이 3이므로 4로 설정
        int answer = 4;

        if(M != 0){
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                possible[a][b] = true;
            }
        }

        // 사다리를 0개부터 3개까지 설치해보는 완전탐색 진행
        for (int i = 0; i <= 3; i++) {
            dfs(0, i);
            if(flag){
                answer = i;
                System.out.println(answer);
                break;
            }
        }
        if(answer > 3){
            answer = -1;
            System.out.println(answer);
        }

    }
}

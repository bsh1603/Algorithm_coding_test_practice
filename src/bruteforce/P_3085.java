/**
 * 사탕 게임
 * bruteforce 알고리즘
 *
 * 초기 주어진 보드에서 한 번을 바꿔 얻을 수 있는 최대값을 구하는 문제
 * 한 번만 바꾼 후 최대값을 구하고, 다시 초기화한다.
 * 바꿀 수 있는 모든 경우의 수에서 최대값을 구하고 그 최대값을 계속 갱신하며 답을 구한다.
 */

package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P_3085 {
    static int N;
    static char[][] map; // 조작할 지도
    static char[][] origin_map; // 처음 상태의 지도

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        origin_map = new char[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j);
                origin_map[i][j] = input.charAt(j);
            }
        }

        int answer = 0; // 답

        // i : 0~N-1  j : 0~N-2 까지 범위 설정을 해줘 array의 indexofBound를 막는다.
        // (마지막 위치는 오른쪽, 아래쪽과 더 이상 비교할 필요 없다.)
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length-1; j++) {
                // 왼쪽 오른쪽 바꿈
                change_row(map, i, j);
                // answer 값 얻기(최대값)
                answer = Math.max(answer, count(map));
                // 초기화
                initialize(map);

                // 위쪽 아래쪽 바꿈
                change_col(map, j, i);
                // answer 값 얻기(최대값)
                answer = Math.max(answer, count(map));
                // 초기화
                initialize(map);
            }
        }

        System.out.println(answer);

    }

    // 열 안에서 인접한 두 문자를 바꾸는 함수(왼쪽 오른쪽 바꿈)
    static char[][] change_row(char[][] arr, int x, int y){
        char tmp = arr[x][y];
        arr[x][y] = arr[x][y+1];
        arr[x][y+1] = tmp;

        return arr;
    }

    // 행 안에서 인접한 두 문자를 바꾸는 함수(위쪽 아래쪽 바꿈)
    static char[][] change_col(char[][] arr, int x, int y){
        char tmp = arr[x][y];
        arr[x][y] = arr[x+1][y];
        arr[x+1][y] = tmp;

        return arr;
    }

    // map을 조작하고 count를 한 뒤 원래의 기본 상태 map으로 바꾸는 함수
    static char[][] initialize(char[][] arr){
        for (int i = 0; i < origin_map.length; i++) {
            for (int j = 0; j < origin_map[i].length; j++) {
                arr[i][j] = origin_map[i][j];
            }
        }
        return arr;
    }

    // 열과 행을 돌며 인접한 문자가 같을 경우 cnt를 1씩 더해줘 마지막에 max 값을 리턴하는 함수
    static int count(char[][] arr){
        int max = 0; // 리턴할 최대값

        for (int i = 0; i < arr.length; i++) {
            int cnt = 1;
            for (int j = 0; j < arr[i].length-1; j++) {
                if(arr[i][j] == arr[i][j+1]){
                    cnt++;
                }
                // 왼쪽의 문자와 같지 않을 경우 다시 cnt를 1로 초기화
                else{
                    cnt = 1;
                }
                max = Math.max(max, cnt);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            int cnt = 1;
            for (int j = 0; j < arr[i].length-1; j++) {
                if(arr[j][i] == arr[j+1][i]){
                    cnt++;
                }
                // 위쪽의 문자와 같지 않을 경우 다시 cnt를 1로 초기화
                else{
                    cnt = 1;
                }
                max = Math.max(max, cnt);
            }
        }

        return max;
    }
}











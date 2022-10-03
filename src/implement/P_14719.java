/**
 * 투포인터를 사용하여 조건을 걸며 답을 얻는다(start, end)
 * map의 row를 한줄씩 탐색한다
 * 최대 500 * 500 이므로 시간초과는 나지 않는다
 * 1) start(T) end(F) -> end++
 * 2) start(F) end(T) -> start++, end++
 * 3) start(F) end(F) -> start++, end++
 * 4) start(T) end(T)
 *    4-1) end - start >= 2 -> cnt = cnt + (end-start) - 1 -> start = end, end = end+1
 *    4-2) end - start < 2 -> start++, end++
 */

package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_14719 {
    static int h, w;
    static boolean[][] map; // 빗물이 차 있는지 확인하는 이차원 배열
    static int[] rain; // 입력받는 빗물 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        h = Integer.parseInt(st.nextToken()); // 행
        w = Integer.parseInt(st.nextToken()); // 열
        map = new boolean[h][w];
        rain = new int[w];

        // 입력받기
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < rain.length; i++) {
            rain[i] = Integer.parseInt(st.nextToken());
        }

        // map의 아래서부터 채움
        // 빗물이 있으면 true 없으면 false
        for (int i = 0; i < w; i++) {
            for (int j = h-1; j >= h-rain[i]; j--) {
                map[j][i] = true;
            }
        }
        // 채우는 횟수 count
        int cnt = 0;

        // 한 줄씩, 즉 한 행마다 채우는 경우를 count
        for (int i = 0; i < h; i++) {
            // two pointer 사용
            int start = 0;
            int end = 1;

            // end가 map의 오른쪽까지 가면 종료
            // 위 주석의 경우의 수에 따라 조건문 사용
            while(end < w){
                if(map[i][start] == true && map[i][end] == false){
                    end++;
                }
                else if(map[i][start] == false && map[i][end] == true){
                    start++;
                    end++;
                }
                else if(map[i][start] == false && map[i][end] == false) {
                    start++;
                    end++;
                }
                else{
                    if(end - start >= 2){
                        cnt = cnt + (end - start) - 1;
                        start = end;
                        end = end + 1;
                    }
                    else{
                        start++;
                        end++;
                    }
                }
            }
        }

        System.out.println(cnt);
    }
}

/**
 * dfs로 처음 시도했으나 막힘
 * 규칙 찾기
 * 이동거리 중 최대값 : max
 * 이동횟수 : cnt
 * 거리 : distance
 * max = 루트(distance)
 * max * max == distance -> cnt = max * 2 - 1
 * max * max < distance <= max * max + max -> cnt = max * 2
 * 나머지 : cnt = max * 2 + 1
 */

package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_1011 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cnt = 1;
            int distance = end - start;
            int max = (int) Math.sqrt(distance);

            if(max * max == distance){
                cnt = max * 2 - 1;
            }
            else if (max * max < distance && distance <= max * max + max) {
                cnt = max * 2;
            }
            else{
                cnt = max * 2 +1;
            }

            System.out.println(cnt);
        }
    }
}










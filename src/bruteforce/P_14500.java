/**
 * 테트로미노
 *
 * dfs를 이용한 풀이
 * 주어진 테트로미노 5개 중 ㅗ 모양을 제외한 나머지 모양들은 그냥 시작점에서 dfs를 하면 전부 만들어진다.
 * 하지만 ㅗ 모양은 dfs를 진행하기가 껄끄러우므로 ㅗ,ㅜ,ㅏ,ㅓ 모양 4개를 각각 구해 max를 구한다.
 *
 * 시간복잡도 측면에서 보면 일단 한 점에서 dfs를 할 경우 depth가 4이므로 4!이다.
 * map의 모든 점을 시작점으로 삼으므로 O(N*M)이지만 N,M <= 500 이므로 충분하다.
 */

package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_14500 {
    static int N, M;
    static int[][] map; // 주어진 지도
    static boolean[][] visited; // 방문 여부 확인용 배열
    static final int[] mx = {-1,1,0,0}; // x축 이동
    static final int[] my = {0,0,-1,1}; // y축 이동
    static int[] nums; // 총 4개의 숫자를 받을 배열
    static int max = Integer.MIN_VALUE; // 답

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        nums = new int[4];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                nums[0] = map[i][j];
                dfs(1, i, j);
                max = Math.max(max, mount(i, j));
            }
        }

        System.out.println(max);

    }

    // ㅗ 모양을 제외한 나머지를 구할 수 있는 dfs
    // 출발점을 map의 모든 점으로 설정할 수 있게끔 매개변수 선언
    static void dfs(int depth, int b, int a){
        // 처음 시작점을 방문처리 해준다.
        visited[b][a] = true;
        // 테트로미노는 4칸짜리이므로 4에서 return
        if(depth == 4){
            int sum = 0;
            for(int val : nums){
                sum += val;
            }
            max = Math.max(max, sum);
            return;
        }
        // x축, y축 이동
        for (int i = 0; i < 4; i++) {
            int y = b + my[i];
            int x = a + mx[i];

            // x, y의 범위가 map 안에 존재해야함
            if(0<=y && y<N && 0<=x && x<M){
                // 방문 안한 곳이라면
                if(visited[y][x] == false){
                    // 방문처리해주고
                    visited[y][x] = true;
                    // 배열에 그 값을 집어넣는다.
                    nums[depth] = map[y][x];
                    // 다음 탐색
                    dfs(depth+1, y, x);
                    // 다시 방문처리를 false
                    visited[y][x] = false;
                }
            }
        }
        // 처음 시작점의 방문처리를 false
        visited[b][a] = false;
    }

    // ㅗ 모양이 산(욕 안됨)이므로 mount라 함
    // 나올 수 있는 4가지 경우의 수를 모두 구해 max를 구한 후 return
    // 각 범위는 알아보기 쉽게 연산하지 않았다.
    static int mount(int y, int x){
        int mount_max = Integer.MIN_VALUE;
        // ㅜ
        if(y+1<N && x+2<M){
            int sum = 0;
            sum += (map[y][x] + map[y][x+1] + map[y+1][x+1] + map[y][x+2]);
            mount_max = Math.max(mount_max, sum);
        }
        // ㅗ
        if(0<=y-1 && x+2<M){
            int sum = 0;
            sum += (map[y][x] + map[y][x+1] + map[y-1][x+1] + map[y][x+2]);
            mount_max = Math.max(mount_max, sum);
        }
        // ㅏ
        if(y+2<N && x+1<M){
            int sum = 0;
            sum += (map[y][x] + map[y+1][x] + map[y+1][x+1] + map[y+2][x]);
            mount_max = Math.max(mount_max, sum);
        }
        // ㅓ
        if(y+2<N && 0<=x-1){
            int sum = 0;
            sum += (map[y][x] + map[y+1][x] + map[y+1][x-1] + map[y+2][x]);
            mount_max = Math.max(mount_max, sum);
        }

        return mount_max;
    }

}

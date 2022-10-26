/**
 * 연구소
 * bfs와 dfs 알고리즘
 *
 * dfs를 통해 벽을 설치
 * bfs를 통해 바이러스 퍼뜨리기
 * -> map안에 0인 부분 최대값 구하기
 */

package bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P_14502 {
    static int N, M;
    static int[][] map; // 지도
    static int[] mx = {-1,1,0,0}; // x축 이동
    static int[] my = {0,0,-1,1}; // y축 이동
    static Queue<Point> queue = new LinkedList<>(); // 바이러스를 담을 자료구조 큐
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        System.out.println(answer);
    }

    // 벽 3개를 설치하기 위한 dfs
    static void dfs(int depth){
        if(depth == 3){
            // 벽 3개를 설치하면 bfs를 진행하여 값을 구한다.
            bfs();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0){
                    map[i][j] = 1;
                    dfs(depth+1);
                    map[i][j] = 0;
                }
            }
        }
    }

    // 바이러스 퍼지는 bfs
    static void bfs(){
        // 기존의 지도가 아닌 복사본을 이용한다.
        int[][] copy_map = new int[N][M];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                copy_map[i][j] = map[i][j];
                if(copy_map[i][j] == 2){
                    queue.offer(new Point(i, j));
                }
            }
        }

        // bfs 시작
        while(!queue.isEmpty()){
            Point p = queue.poll();

            for (int i = 0; i < 4; i++) {
                int y = p.y + my[i];
                int x = p.x + mx[i];

                // map 안에 있으면
                if(0<=y && y<N && 0<=x && x<M){
                    // 빈 공간이라면
                    if(copy_map[y][x] == 0){
                        copy_map[y][x] = 2;
                        queue.offer(new Point(y, x));
                    }
                }
            }
        }
        // 결과값을 뽑는다.(0인 개수)
        count(copy_map);
    }

    // 0인 개수(최대값)을 찾기 위한 메서드
    static int count(int[][] arr){
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] == 0){
                    cnt++;
                }
            }
        }
        answer = Math.max(answer, cnt);
        return answer;
    }

    // 좌표 클래스
    static class Point{
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}

/**
 * 도착지점이 아닌 출발지점으로 생각하고 나머지 모든 지점들로 가는 bfs 문제로 해석
 * -1 처리 : 방문하지 않았고 값이 0이 아니면 -1이다
 */

package bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P_14940 {
    static final int[] mx = {-1, 1, 0, 0};
    static final int[] my = {0, 0, -1, 1};
    static int n, m;
    static int[][] map; // 초기 입력 배열
    static int[][] count; // bfs탐색 시 이동거리 체크 배열
    static boolean[][] visited; // 방문여부 확인 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        count = new int[n][m];
        visited = new boolean[n][m];

        // 시작점을 설정
        Point start = new Point(0,0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                count[i][j] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    start = new Point(i, j);
                }
                if(map[i][j] == 0){
                    count[i][j] = 0;
                }
            }
        }
        // 초기 출발지점 값을 0으로 초기화
        count[start.y][start.x] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bfs(start, new Point(i, j));
                // 방문하지 않았는데 값도 0이 아니라면 도달 불가 -> -1이다
                if(visited[i][j] == false && map[i][j] != 0){
                    count[i][j] = -1;
                }
                System.out.print(count[i][j] + " ");
            }
            System.out.println("");
        }
    }

    static void bfs(Point start, Point end){
        Queue<Point> queue = new LinkedList<>();

        queue.offer(start);
        visited[start.y][start.x] = true;

        while(!queue.isEmpty()){
            Point p = queue.poll();

            if(p == end){
                break;
            }

            for (int i = 0; i < 4; i++) {
                int y = p.y + my[i];
                int x = p.x + mx[i];
                // map안에 존재해야함
                if(0<=x && x<m && 0<=y && y<n){
                    // map값이 1이여야 하고 방문하지 않은 곳이어야 함
                    if(map[y][x] == 1 && visited[y][x] == false){
                        count[y][x] = count[p.y][p.x] + 1;
                        visited[y][x] = true;
                        queue.offer(new Point(y,x));
                    }
                }
            }
        }

    }

    static class Point{
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}

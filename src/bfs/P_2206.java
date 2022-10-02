// 왔던 곳을 다시 가는 것은 최소 조건이 아님 -> 방문처리

package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P_2206 {
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    static int N, M;
    static int[][] map;
    static boolean[][][] visited; // 벽을 부수고 탐색하는경우 || 벽을 부수지 않고 탐색하는 경우
    static Queue<Point> queue;
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        visited = new boolean[N][M][2];
        queue = new LinkedList<>();

        Point start = new Point(0, 0 , 1, false);
        Point end = new Point(N-1, M-1, 0, false);

        queue.offer(start);

        while(!queue.isEmpty()){
            // 1. 큐에서 꺼내옴
            Point p = queue.poll();
            // 2. 목적지인가
            if(p.y == end.y && p.x == end.x){
                end = p;
                flag = true;
                break;
            }
            // 위 아래 왼쪽 오른쪽 이동
            for (int i = 0; i < 4; i++) {
                int y = p.y + my[i];
                int x = p.x + mx[i];

                // 맵 안에 존재해야 이동할 수 있음
                if(y >= 0 && y <= N-1 && x >= 0 && x <= M-1){
                    // 벽이 아님
                    if(map[y][x] == '0'){
                        // 부신 벽이 0개
                        if(p.wall == false && visited[y][x][0] == false){
                            visited[y][x][0] = true;
                            queue.offer(new Point(y, x, p.cnt+1, false));
                        }
                        // 부신 벽이 1개
                        else if(p.wall == true && visited[y][x][1] == false){
                            visited[y][x][1] = true;
                            queue.offer(new Point(y, x, p.cnt+1, true));
                        }
                    }
                    // 벽임
                    else{
                        // 부신 벽이 0개
                        if(p.wall == false && visited[y][x][1] == false){
                            visited[y][x][1] = true;
                            queue.offer(new Point(y, x, p.cnt+1, true));
                        }
                        // 부신 벽이 1개
                        // 넘어감
                    }
                }
            }
        }
        if(flag){
            System.out.println(end.cnt);
        }
        else{
            System.out.println(-1);
        }

    }

    static class Point{
        int y;
        int x;
        int cnt; // 이동한 횟수를 카운트하는 변수
        boolean wall; // 부신 벽이 있는지 없는지

        public Point(int y, int x, int cnt, boolean wall) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.wall = wall;
        }
    }
}

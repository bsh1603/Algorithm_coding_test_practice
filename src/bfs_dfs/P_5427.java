// 불을 큐에 넣음 -> start 큐에 넣음
// 탈출 조건 -> 0 or h-1 // 0 or w-1
// start 이동 -> . 경우만 이동 && 방문하지 않은곳
// 불 이동 -> . @ 경우 이동

package bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P_5427 {
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    static int N;
    static int w, h;
    static char[][] map;
    static boolean[][] visited; // 방문확인
    static int[][] move; // 이동 거리 체크
    static Queue<Point> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int n = 1; n <= N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            visited = new boolean[h][w];
            move = new int[h][w];
            queue = new LinkedList<>();

            int answer = 0;
            boolean possible = false;

            Point start = null;
            Point fire = null;

            for (int i = 0; i < h; i++) {
                String input = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = input.charAt(j);
                    if(map[i][j] == '@'){
                        start = new Point(i, j, map[i][j]);
                    }
                    else if(map[i][j] == '*'){
                        fire = new Point(i, j, map[i][j]);
                        queue.offer(fire);
                    }
                }
            }
            queue.offer(start);

            while(!queue.isEmpty()){
                Point p = queue.poll();
                // 탈출 조건
                if((p.y == 0 || p.y == h-1 || p.x == 0 || p.x == w-1) && p.type != '*'){
                    possible = true;
                    answer = move[p.y][p.x] + 1;
                    break;
                }

                // 연결된 곳을 순회
                for (int i = 0; i < 4; i++) {
                    int y = p.y + my[i];
                    int x = p.x + mx[i];

                    // 갈 수 있는가 -> map 안에 존재
                    if(0 <= y && y <= h-1 && 0 <= x && x <= w-1){
                        // start(@ && .) -> . 인것만 갈 수 있음 && visited = false
                        if(p.type == '@' || p.type == '.'){
                            if(map[y][x] == '.' && visited[y][x] == false){
                                visited[y][x] = true;
                                move[y][x] = move[p.y][p.x] + 1;
                                start = new Point(y, x, map[y][x]);
                                queue.offer(start);
                            }
                        }
                        // 불(*) -> . , @ 인것만 갈 수 있음 -> 간 곳을 *로 체크
                        else if(p.type == '*'){
                            if(map[y][x] == '.' || map[y][x] == '@'){
                                map[y][x] = '*';
                                queue.offer(new Point(y, x, map[y][x]));
                            }
                        }
                    }
                }
            }

            if(possible){
                System.out.println(answer);
            }
            else{
                System.out.println("IMPOSSIBLE");
            }
            queue.clear();
        }
    }

    static class Point{
        int y;
        int x;
        char type;

        public Point(int y, int x, char type) {
            this.y = y;
            this.x = x;
            this.type = type;
        }
    }
}

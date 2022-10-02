/**
 * 50m * 20 = 1000m 최대 1000m이동가능
 * <이렇게 접근 시 편의점 - 편의점 가는 것을 생각 못함>
 * if(집 - 락페 거리 <= 1000 : 한번에 갈수있음) return happy
 * else
 *      if(집 - 편의점 거리 <= 1000)
 *          if(편의점 - 락페 <= 1000) return happy
 *          else return sad
 *      else return sad
 *
 * <bfs 풀이>
 * queue에 start를 넣고 시작 -> 바로 락페로 갈 경우 break; -> answer = happy
 * start -> 편의점 : 편의점 queue에 넣음 -> 락페로 가는 경우 -> answer = happy
 *      이 경우 방문처리
 * 나머지 : answer = sad
 *
 * 156ms
 */

package bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P_9205 {
    static int t; // 테스트 케이스 개수
    static int n; // 편의점 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            n = Integer.parseInt(br.readLine());
            StringTokenizer st;

            Point[] map = new Point[2]; // start와 end만 담는 배열
            Point[] con = new Point[n]; // convenience 담는 배열
            boolean visited[] = new boolean[n+2]; // visited[0]과 visited[n+1]은 start, end이고, 나머지는 편의점 관련 방문 배열
            visited[0] = true; // start는 방문처리

            // 입력 받기 : start -> 편의점들 -> end
            st = new StringTokenizer(br.readLine(), " ");
            map[0] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            Point start = map[0];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                con[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            st = new StringTokenizer(br.readLine(), " ");
            map[1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            Point end = map[1];

            String answer = "sad";

            Queue<Point> queue = new LinkedList<>();
            queue.offer(start);
            // bfs
            while(!queue.isEmpty()){
                Point p = queue.poll();
                // 큐에서 뺀 값과 end 지점 거리가 1000이하이면 종료
                if(distance(p, end) <= 1000){
                    answer = "happy";
                    break;
                }
                else{
                    for (int i = 0; i < con.length; i++) {
                        // 내 위치와 편의점사이의 거리가 1000m이하 && 방문하지 않은 편의점
                        if(distance(p, con[i]) <= 1000 && visited[i+1] == false){
                            visited[i+1] = true;
                            queue.offer(con[i]);
                        }
                    }
                }
            }

            System.out.println(answer);
        }

//        System.out.printf(start.x + " " + start.y);
//        System.out.println("");
//        for (int i = 0; i < con.length; i++) {
//            System.out.printf(con[i].x + " " + con[i].y);
//            System.out.println("");
//        }
//        System.out.printf(end.x + " " + end.y);
//        System.out.println("");

//        if(distance(start, end) <= 1000){
//            answer = "happy";
//            System.out.println(1);
//        }
//        else{
//            for (int i = 0; i < n; i++) {
//                if(distance(start, con[i]) <= 1000){
//                    if(distance(con[i], end) <= 1000){
//                        System.out.println(2);
//                        answer = "happy";
//                        break;
//                    }
//                    else{
//                        System.out.println(3);
//                        continue;
//                    }
//                }
//                else{
//                    System.out.println(4);
//                }
//            }
//        }
//
//        System.out.println(answer);
    }

    // 두 지점 사이의 거리 구하는 메서드
    static int distance(Point a, Point b){
        int dist = Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
        return dist;
    }

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

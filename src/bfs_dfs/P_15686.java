/**
 * 치킨 배달
 *
 * dfs를 이용해 전체 치킨집 중 M개의 치킨집을 고른다.
 * 모든 집에서 선택된 각각의 치킨집까지의 거리의 최소값을 구한 후 더한다.
 * 이 중 가장 최소의 값을 답으로 한다.
 */

package bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P_15686 {
    static int N, M;
    static int[][] map; // 입력된 지도
    static ArrayList<Point> home; // map에서 집인 좌표를 담는 리스트
    static ArrayList<Point> chicken; // map에서 치킨집인 좌표를 담는 리스트
    static Point[] choice; // M개만큼 선택된 치킨집의 좌표를 담는 배열
    static int result = Integer.MAX_VALUE; // 답

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        home = new ArrayList<>();
        chicken = new ArrayList<>();
        choice = new Point[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 1인 경우 집이므로 home에 추가
                if(map[i][j] == 1){
                    home.add(new Point(i, j));
                }
                // 2인 경우 치킨집이므로 chicken에 추가
                if(map[i][j] == 2){
                    chicken.add(new Point(i, j));
                }
            }
        }

        dfs(0, 0);
        System.out.println(result);

    }

    // 전체 치킨집 중 M개만큼 선택하기 위한 메서드
    static void dfs(int depth, int index){
        if(depth == M){
            // 모든 치킨집 조합 중 거리가 가장 작은 result를 반환한다.
            result = Math.min(result, solve(home, choice));
            return;
        }
        // 조합
        for (int i = index; i < chicken.size(); i++) {
            choice[depth] = chicken.get(i);
            dfs(depth+1, i+1);
        }
    }

    // 두 좌표간의 거리를 구하는 메서드
    static int distance(Point h, Point c){
        int dist = Math.abs(h.y - c.y) + Math.abs(h.x - c.x);
        return dist;
    }

    // 선택된 치킨집들과 각각의 모든 집들간에 거리를 구하고
    // 가장 최소값을 리턴하는 메서드
    static int solve(ArrayList<Point> h, Point[] c){
        int answer = 0;
        for (int i = 0; i < h.size(); i++) {
            // 한 집에서 다른 치킨집들 간에 거리
            int dist = 0;
            // 한 집에서 다른 치킨집들 간에 가장 짧은 거리
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < c.length; j++) {
                dist = distance(h.get(i), c[j]);
                min = Math.min(min, dist);
            }
            // min을 다 더해줌으로써 모든 집들의 치킨집까지의 가장 짧은 거리를 반환한다.
            answer += min;
        }
        return answer;
    }

    // 좌표를 나타내는 클래스
    static class Point{
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}

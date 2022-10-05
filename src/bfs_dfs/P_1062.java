/**
 * 백트래킹을 사용하여 배울 알파벳들을 완전탐색 후 배울 수 있는 단어인 경우 +1
 * a,n,t,i,c는 무조건 배워야 하는 알파벳(5개)
 * K < 5 -> 배울 수 있는 언어는 없다 -> answer = 0
 * K == 26 -> 모든 알파벳을 배우므로 모든 언어를 다 배울 수 있다 -> answer = N
 * 그 외 나머지 완전탐색
 * words[] 를 받아 int형 canRead 메서드로 셀 수 있는 단어를 count한다.
 *
 * 시간초과 해결법
 * 1) 단어를 받아 words[]에 저장할 때 substring을 사용하여 anta, tica를 제외하고 저장한다.
 * 2) 기존에 그냥 백트래킹을 할 시 b-d-e, b-d-f를 탐색하고 d-b-e, d-b-f도 탐색하여 순서만 다를 뿐 같은 알파벳들의 조합을 탐색한다.
 *    -> 탐색할 index, 즉 알파벳을 기준으로 그 뒤의 알파벳들로만 백트래킹을 진행한다.
 *
 * 13% 오류 : K = 5인 경우 -> dfs 탈출 조건 만족시키지 못함
 *    -> answer를 canRead를 통해 그냥 구한다.
 */

package bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_1062 {
    static int N, K;
    static String[] words; // 입력받은 문장을 저장하는 배열
    static boolean[] alphabet; // 26개의 알파벳을 썼는지 확인하는 배열
    static int maxcnt = 0; // answer 답

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        words = new String[N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            // anti, tica를 제외해준다.
            words[i] = input.substring(4, input.length()-4);
        }

        alphabet = new boolean[26];

        // a, n, c, t, i는 필수적으로 배워야함
        alphabet['a' - 'a'] = true;
        alphabet['n' - 'a'] = true;
        alphabet['c' - 'a'] = true;
        alphabet['t' - 'a'] = true;
        alphabet['i' - 'a'] = true;

        // 필수적으로 배우는 게 5개 밑이면 answer = 0
        if(K < 5){
            System.out.println(0);
        }
        // 전부 다 배운다
        else if(K == 26){
            System.out.println(words.length);
        }

        else{
            // K=5일 시 a,n,t,i,c를 배우면 words[]에 있는 것 중 몇개나 읽을 수 있는지 확인한다.(dfs 필요 X)
            if(K==5){
                maxcnt = canRead(words);
            }
            else{
                for (int i = 0; i < 26; i++) {
                    // 좀 더 시간을 줄이기 위하여 a,n,t,i,c는 제외
                    if(alphabet[i] == false){
                        dfs(1, i);
                    }
                }
            }
            System.out.println(maxcnt);
        }

    }

    // 백트래킹
    // depth : 배우는 글자 수 / index : 배우는 알파벳 시작점
    static void dfs(int depth, int index){
        alphabet[index] = true;
        // 탈출조건
        if(depth == K-5){
            maxcnt = Math.max(maxcnt, canRead(words));
        }
        else{
            for (int i = index + 1; i < 26; i++) {
                if(alphabet[i] == false){
                    dfs(depth+1, i);
                }
            }
        }
        alphabet[index] = false;
    }

    // 읽을 수 있는 문장 개수 세기
    static int canRead(String[] str){
        int cnt = 0; // 읽을 수 있는 개수
        for (int i = 0; i < str.length; i++) {
            boolean flag = true;
            for (int j = 0; j < str[i].length(); j++) {
                if(alphabet[str[i].charAt(j) - 'a'] == true){
                    flag = true;
                }
                // 읽을 수 없으면 바로 stop
                else{
                    flag = false;
                    break;
                }
            }
            // 문장에 있는 글자 전부 읽을 수 있을 때 cnt++
            if(flag){
                cnt++;
            }
        }
        return cnt;
    }
}

















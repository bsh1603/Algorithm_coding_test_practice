/**
 * 앞서 푼 문제 시간초과 이유
 * 1) R : collections.reverse() 사용 -> O(n^2) 시간초과
 * 2) 배열 입력을 받을 때 그것을 String으로 저장하고 [, ] 를 제거한 새로운 String을 또 생성
 *    그리고 그것을 ,로 구분한 StringTokenizer를 사용하여 시간초과
 *
 * 해결책
 * 1) deque를 사용하여 앞 또는 뒤를 제거
 *    -> boolean형 isFirst를 생성하여 true이면 포인터가 앞, false이면 포인터가 뒤로 설정
 *    -> D를 만나면 포인터 위치에서 제거
 * 2) 배열 입력을 받고 바로 substring함수를 사용하여 [ ] 제거
 *    -> split(,)을 사용하여 바로 숫자만 deque에 저장
 */

package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class P_5430_deque {
    static int T; // 전체 Test case 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            String P = br.readLine(); // R,D 명령어

            int n = Integer.parseInt(br.readLine()); // 숫자 배열 수
            String arr_input = br.readLine(); // 입력받은 배열
            Deque<Integer> deque = new ArrayDeque<>(); // 문제 해결을 위한 deque

            // [ ] 제거 후 ,로 구분하여 숫자만 deque에 저장
            for(String s : arr_input.substring(1, arr_input.length()-1).split(",")){
                // 조건문을 걸어주지 않으면 맨 마지막 테스트 케이스처럼 [] 아무것도 들어오지 않을 때 nullpointerror가 난다
                if(!s.equals("")){
                    deque.add(Integer.valueOf(s));
                }
            }

            boolean isFirst = true; // 포인터가 맨 앞인지 뒤인지 체크하는 변수
            boolean flag = false; // 답을 나타내기 위한 변수 -> true면 error

            for (int i = 0; i < P.length(); i++) {
                // reverse
                if(P.charAt(i) == 'R'){
                    isFirst = !isFirst; // 뒤집어준다
                }
                // delete
                else{
                    if(deque.size() == 0){
                        flag = true;
                        break;
                    }
                    // 포인터가 맨 앞이면 deque에서 맨 앞 제거
                    if(isFirst){
                        deque.pollFirst();
                    }
                    // 포인터가 맨 뒤이면 deque에서 맨 뒤 제거
                    else{
                        deque.pollLast();
                    }
                }
            }

            if(flag){
                System.out.println("error");
            }
            else{
                StringBuilder sb = new StringBuilder("[");
                while(!deque.isEmpty()){
                    // isFirst == true : 맨 앞부터 출력
                    if(isFirst){
                        sb.append(deque.pollFirst());
                    }
                    // isFirst == false : 맨 뒤부터 출력
                    else{
                        sb.append(deque.pollLast());
                    }

                    if(deque.size() != 0){
                        sb.append(',');
                    }
                }
                sb.append(']');
                System.out.println(sb.toString());
            }
        }
    }
}

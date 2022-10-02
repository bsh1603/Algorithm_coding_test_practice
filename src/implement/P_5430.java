/**
 * 시간 초과 코드
 * Arraylist 사용
 * collections.reverse() 사용
 * d_cnt의 개수를 세서 그 개수가 n보다 크면 error
 * 시간 복잡도 : T(100) * P(100000) * N(100000) * N(100000)
 */

package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P_5430 {
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            String P = br.readLine();
            int d_cnt = 0; // D의 개수
            for (int i = 0; i < P.length(); i++) {
                if(P.charAt(i) == 'D'){
                    d_cnt++;
                }
            }
            int n = Integer.parseInt(br.readLine());
            String arr_input = br.readLine();

            // D의 개수가 n보다 크면 error
            if(d_cnt > n){
                System.out.println("error");
            }
            else{
                // 입력받은 것을 arraylist에 저장
                String input = "";
                for (int i = 0; i < arr_input.length(); i++) {
                    if(arr_input.charAt(i) == '[' || arr_input.charAt(i) == ']'){
                        continue;
                    }
                    else{
                        input += arr_input.charAt(i);
                    }
                }
                StringTokenizer st = new StringTokenizer(input, ",");
                ArrayList<Integer> num = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    num.add(Integer.parseInt(st.nextToken()));
                }

                // Arraylist인 num을 주어진 P에 따라 조작
                for (int i = 0; i < P.length(); i++) {
                    if(P.charAt(i) == 'R'){
                        reverse(num);
                    }
                    else{
                        delete(num);
                    }
                }

                // answer를 출력
                String answer = "[";
                for(int val : num){
                    answer += Integer.toString(val) + ",";
                }
                answer = answer.substring(0, answer.length()-1);
                answer += "]";
                System.out.println(answer);
            }
        }
    }

    // R : 뒤집는 메서드
    static ArrayList<Integer> reverse(ArrayList<Integer> arr){
        Collections.reverse(arr);
        return arr;
    }

    // D : arraylist의 처음을 삭제
    static ArrayList<Integer> delete(ArrayList<Integer> arr){
        arr.remove(0);
        return arr;
    }
}










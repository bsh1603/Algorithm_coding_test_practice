/**
 * 문자열 폭발
 * stack을 이용하여 풀이
 *
 * 기존 생각 : String compare를 셋팅, stack에서 pop될 때는 맨 위에부터 나오므로 폭탄 bomb을
 *           반대로 설정(reverse_bomb)
 *           그래서 stack에서 꺼낸 compare과 reverse_bomb을 비교해
 *           둘이 같으면 이미 pop되있기 때문에 넘어가고
 *           둘이 다르면 다시 compare을 뒤집어 stack에 push를 해줌
 *
 * 메모리초과로 문제 계속 틀림
 * <해결>
 * -> stack에서 꺼내서 비교하는 게 아닌 stack의 위치를 get으로 받아와 bomb의 charAt과 비교
 *      -> stack.get(stack.size() - bomb.length() - j)와 bomb.charAt(j)를 비교(bomb의 길이만큼)
 * -> 그래도 메모리초과
 * -> answer를 String형태로 stack에서 pop해와 더하는 방식이 아닌 StringBuilder를 이용해 append방식으로 구현
 * -> 해결
 */

package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P_9935 {
    static String input; // 초기 문자열
    static String bomb; // 폭탄 문자열
//    static String reverse_bomb = ""; 기존의 잘못된 풀이
    static Stack<Character> stack; // 문자열의 문자를 담을 stack 자료구조
//    static String answer = ""; StringBuilder를 이용한다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        bomb = br.readLine();
        int len_input = input.length();
        int len_bomb = bomb.length();

//        for (int i = len_bomb - 1; i >= 0; i--) {
//            reverse_bomb += bomb.charAt(i);
//        }

        stack = new Stack<>();

        for (int i = 0; i < len_input; i++) {
            // stack에 하나씩 넣는다.
            stack.add(input.charAt(i));

            // stack의 크기가 bomb의 길이보다 큰 경우만 폭탄이 터지는지 아닌지 확인
            if(stack.size() >= len_bomb){
                // flag가 true이면 비교대상과 bomb은 다름
                // flag가 false이면 비교대상과 bomb이 같음 -> pop해서 제거
                boolean flag = false;
                for (int j = 0; j < len_bomb; j++) {
                    if(stack.get(stack.size() - len_bomb + j) != bomb.charAt(j)){
                        flag = true;
                        // 하나라도 다르면 전체가 다르다고 볼 수 있다.
                        break;
                    }
                }
                if(!flag){
                    for (int j = 0; j < len_bomb; j++) {
                        stack.pop();
                    }
                }
                
//                String compare = "";
//                for (int j = 0; j < len_bomb; j++) {
//                    compare += stack.pop();
//                }
//                if(!compare.equals(reverse_bomb)){
//                    for (int j = compare.length()-1; j >= 0; j--) {
//                        stack.add(compare.charAt(j));
//                    }
//                }
            }
        }

        // StringBuilder를 이용하니 메모리 초과를 해결했다.
        StringBuilder sb = new StringBuilder();
        for(char val : stack){
            sb.append(val);
        }
        // 답 출력
        if(sb.length() == 0){
            System.out.println("FRULA");
        }
        else{
            System.out.println(sb);
        }

//        if(stack.size()>0){
//            flag = true;
//        }
//        for(char val : stack){
//            answer += val;
//        }
//
//        if(flag){
//            System.out.println(answer);
//        }
//        else{
//            System.out.println("FLRUA");
//        }

//        if(stack.size()>0){
//            for(char val : stack){
//                answer += val;
//            }
//            System.out.println(answer);
//        }
//        else{
//            System.out.println("FRULA");
//        }


    }
}

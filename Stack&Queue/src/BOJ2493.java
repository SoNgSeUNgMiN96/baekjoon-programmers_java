import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;

public class BOJ2493 {


    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));//선언
        int N  , answer[] , minheight ;

        int[] tower;
        String temp[];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        temp = new String[N];
        answer = new int[N];
        temp = br.readLine().split(" ");

        Stack<int[]> stack = new Stack();

        minheight = Integer.parseInt(temp[N-1]);       //높이 동기화


        for (int i=N-1;i>=0;i--){
            if(minheight<Integer.parseInt(temp[i])){        //최저층보다 높은 층이 나타난다면
                while (stack.size()>0&&((int[])stack.peek())[1]<Integer.parseInt(temp[i])){   //방금 빌딩보다 작은 애들을
                    answer[((int[])stack.pop())[0]] = i+1 ;        //answer에 기록
                }
            }
            tower = new int[2];
            tower[0] = i; tower[1] = Integer.parseInt(temp[i]);
            stack.add(tower);
            minheight = Integer.parseInt(temp[i]);  //최저층 동기화
        }
        for (int i=0;i<N;i++){
            bw.write(answer[i]+" ");
        }
        bw.flush();
        bw.close();
    }
}

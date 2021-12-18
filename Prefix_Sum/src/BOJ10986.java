import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10986 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N,M  ,remainder[], temp, sum=0;
        long answer=0, count;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        remainder = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < remainder.length; i++) {
            temp = Integer.parseInt(st.nextToken());
            sum += temp;
            remainder[i] = sum %=M;
            if (remainder[i]==0)
                answer++;
        }
        Arrays.sort(remainder);

        for (int i = 0; i < N;) {
            count =0;
            temp = remainder[i];
            while (i<N&&remainder[i]==temp){
                count++;
                i++;
            }
            answer += (long)count * (count-1)/2L;
        }
        System.out.println(answer);
    }
}

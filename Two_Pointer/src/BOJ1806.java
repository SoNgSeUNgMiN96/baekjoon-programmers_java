import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    10,000 이하의 자연수로 이루어진 길이 N짜리 수열이 주어진다. 이 수열에서 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중,
    가장 짧은 것의 길이를 구하는 프로그램을 작성하시오.

    입력 : 첫째 줄에 N (10 ≤ N < 100,000)과 S (0 < S ≤ 100,000,000)가 주어진다.
    둘째 줄에는 수열이 주어진다. 수열의 각 원소는 공백으로 구분되어져 있으며, 10,000이하의 자연수이다.

    예제입력 : 10 15
              5 1 3 5 10 7 4 9 2 8
    예제 출력 : 2

    포인터 두개를 이용한다.
 */

public class BOJ1806 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N , S , arr[],j=0 , tempsum=0, min=Integer.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {       //i라는 포인터는 i가 카운트 될 때 마다 합을 빼줍니다.
            while (j<N&&tempsum<S)      //j라는 포인터는  배열의 마지막까지 가지만, 현재까지의 연속 부분합이, 원하는 S보다 클까지 더합니다.
                tempsum += arr[j++];
            if (tempsum>=S)     //현재 더해진 수가 S보다 크다면
                if (min>(j-i))      //그리고 이전보다 짧은 구간이라면
                    min = j-i;      //min 갱신
            tempsum -= arr[i];      //i가 올라갈때마다 현재의 포인터에 있던 수를 줄여줍니다.
        }
        if (min==Integer.MAX_VALUE) //만약 갱신이 되지않은 애라면 0을 출력
            System.out.println(0);
        else System.out.println(min);       //갱신이되었다면 그 최솟값을 출력.
    }
}

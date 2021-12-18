import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ11660 {
    public static void main(String[] args) throws Exception{
        int N,M, arr[][],presum[][] ,a1,a2,b1,b2 ,sum;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        presum = new int[N+1][N+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                presum[i+1][j+1] = presum[i+1][j]+arr[i][j];
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a1 = Integer.parseInt(st.nextToken());
            b1 =Integer.parseInt(st.nextToken());
            a2 =Integer.parseInt(st.nextToken());
            b2 =Integer.parseInt(st.nextToken());
            sum =0;
            for (int j=a1;j<=a2;j++){
                sum += (presum[j][b2]-presum[j][b1-1]);
            }
            bw.write(sum+"\n");
        }
        bw.flush();
        bw.close();
    }
}

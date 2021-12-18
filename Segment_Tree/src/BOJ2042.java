import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ2042 {
    public static void main(String[] args) throws Exception{
        int N,M,K, a;
        long b,c;
        long arr[] , segmentTree[];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new long[N];
        segmentTree = new long[N*4];
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        initarr(0,N-1,1,segmentTree,arr);

        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Long.parseLong(st.nextToken());
            c = Long.parseLong(st.nextToken());
            if (a==1){
                update(0,N-1,1,(int)(b-1),c-arr[(int)(b-1)],segmentTree);
                arr[(int)(b-1)] = c;
            }else{
                bw.write(sum(0,N-1,1,b-1,c-1,segmentTree)+"\n");
            }
        }

        bw.flush();
        bw.close();
    }
    public static long initarr(int start,int end,int node,long []segmentTree,long []arr){
        if(start==end)
            return segmentTree[node] = arr[start];
        int mid = (start + end)/2;
        return segmentTree[node] = initarr(start,mid,node*2,segmentTree,arr) + initarr(mid+1,end,node*2+1,segmentTree,arr);
    }

    public static void update(int start, int end, int node, int index, long diff, long []segmentTree){
        if (index<start||index>end) return;
        segmentTree[node] +=diff;
        if (start==end) return;
        int mid = (start + end)/2;
        if(index<=mid)
            update(start,mid,node*2,index,diff,segmentTree);
        else
            update(mid+1,end,node*2+1,index,diff,segmentTree);
    }
    public static long sum(int start, int end, int node, long left, long right, long []segmentTree){
        if (left>end||right<start) return 0;
        if (left <=start && end<=right)
            return segmentTree[node];
        int mid = (start + end)/2;
        return sum(start,mid,node*2,left,right,segmentTree) + sum(mid+1,end,node*2+1,left,right,segmentTree);
    }
}

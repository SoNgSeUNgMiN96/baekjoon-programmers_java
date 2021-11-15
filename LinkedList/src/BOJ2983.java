import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ2983 {

    static class coord{ //좌표
        int x;
        int y;
        coord right;
        coord left;
        coord(int x,int y){
            this.x = x;
            this.y = y;
            right = null;
            left = null;
        }
    }
    static class Linked{
        static coord  head, tail , cursor , frog;
        static int size=0;

        Linked(coord first){
            head = first;
            tail = first;
            frog = head;
            size++;
        }

        static void addFirst(coord temp){
            temp.right = head;
            head.left = temp;
            head = temp;
            size++;
        }
        static void addLast(coord temp){
            temp.left = tail;
            tail.right = temp;
            tail = temp;
            size++;
        }

        static boolean insertIdx(int idx , coord temp){
            int i;
            if (idx <1 ||idx>=size)
                return false;
            if(idx<size/2){
                i=0;
                for (cursor = head;i<idx-1;cursor= cursor.right);
                temp.left = cursor;
                temp.right = cursor.right;
                cursor.right.left = temp;
                cursor.right = temp;
            }else{
                i=size;
                for (cursor = tail;--i>idx;cursor= cursor.right);
                temp.left = cursor.left;
                temp.right = cursor;
                cursor.left.right = temp;
                cursor.left = temp;
            }
            return true;
        }


        static void delete(coord temp){


            if (temp ==head) { //head라면
                temp.right.left = null;
                head= head.right;
            }
            else if (temp ==tail){
                tail.left.right = null;
                tail = tail.left;
            }else{
                temp.left.right = temp.right;
                temp.right.left = temp.left;
            }
            size--;
        }




    }


    public static void main(String[] args) throws Exception {
        coord tempcursor;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int commadIdx=0;  /*direc은  A= 기울기 1 , x로의 증가방향
        B = 기울기 -1 , x로의 증가방향  C = 기울기 -1  , x로의 감소방향  D = 기울기 1 x로의 감소방향 */

        boolean failCode[] = new boolean[4];
        for (int j=0;j<4;j++)
            failCode[j] = false;
        boolean success =false;

        String temp[], command;
        int N, K;
        temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        K = Integer.parseInt(temp[1]);

        int arraycoord[][] = new int[N][2];

        command = br.readLine();

        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            arraycoord[i][0] = Integer.parseInt(temp[0]);
            arraycoord[i][1] = Integer.parseInt(temp[1]);
        }

        int tempfrog[] = new int[]{arraycoord[0][0],arraycoord[0][1]};

        Arrays.sort(arraycoord, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]==o2[0])
                    return o1[1]-o2[1];
                return o1[0] - o2[0];
            }
        });

        Linked linkedList = new Linked(new coord(arraycoord[0][0],arraycoord[0][1]));
        if (tempfrog[0]==linkedList.head.x&&tempfrog[1]==linkedList.head.y)
            linkedList.frog = linkedList.head;

        for (int i = 1; i < N; i++) {
            linkedList.addLast(new coord(arraycoord[i][0],arraycoord[i][1]));
            if (tempfrog[0]==arraycoord[i][0]&&tempfrog[1]==arraycoord[i][1])
                linkedList.frog = linkedList.tail;
        }


        while (commadIdx<K){

            if (failCode[command.charAt(commadIdx)-'A']){
                commadIdx++;
                continue;
            }
            success = false;

            if((command.charAt(commadIdx)=='A')||(command.charAt(commadIdx)=='B')){      //오른쪽 탐색
                for(tempcursor = linkedList.frog.right;tempcursor!=null;tempcursor = tempcursor.right){
                    if (   (  (command.charAt(commadIdx)=='A')&& ( (tempcursor.y - linkedList.frog.y) ==(tempcursor.x - linkedList.frog.x)))    || (  ((command.charAt(commadIdx)=='B')) && ( (linkedList.frog.y-tempcursor.y) == (tempcursor.x - linkedList.frog.x)))  ){
                        linkedList.delete(linkedList.frog);
                        linkedList.frog = tempcursor;
                        for (int i=0;i<4;i++)
                            failCode[i] = false;
                        success = true;
                        break;
                    }
                }
            }else { //왼쪽 탐색
                for(tempcursor = linkedList.frog.left;tempcursor!=null;tempcursor = tempcursor.left){
                    if (  (  ((command.charAt(commadIdx)=='D'))&& ( (tempcursor.y - linkedList.frog.y) ==(tempcursor.x - linkedList.frog.x)))  ||  ( ((command.charAt(commadIdx)=='C'))&& ( ( linkedList.frog.y -tempcursor.y) == (tempcursor.x - linkedList.frog.x)))  ){
                        linkedList.delete(linkedList.frog);
                        linkedList.frog = tempcursor;
                        for (int i=0;i<4;i++)
                            failCode[i] = false;
                        success = true;
                        break;
                    }
                }
            }
            if (!success)
                failCode[command.charAt(commadIdx)-'A'] = true;
            commadIdx++;
        }

        System.out.println(linkedList.frog.x+" "+linkedList.frog.y);
    }

}

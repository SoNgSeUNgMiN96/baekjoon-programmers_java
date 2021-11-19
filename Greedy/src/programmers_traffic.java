import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class programmers_traffic {

    public static int solution(String[] lines) {
        int answer = 0 ;
        Double  timeStartEnd[] = new Double[2];
        //Double.parseDouble(str)

        int timeStartEndi[][] = new int[lines.length][4];


        PriorityQueue <int[]>endTime = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]==o2[0])
                    return  o1[1] -o2[1];
                return  o1[0] - o2[0];
            }
        });

        for (int i=0; i<lines.length; i++) {
            timeStartEndi[i] = getTime(lines[i]);
        }

        Arrays.sort(timeStartEndi, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]==o2[0])
                    return  o1[1] -o2[1];
                return  o1[0] - o2[0];
            }
        });


        for (int i=0; i<lines.length; i++){

            int timeEnd [] = new int[2];
            timeEnd[0] = timeStartEndi[i][2];
            timeEnd[1] = timeStartEndi[i][3];

           while (endTime.size()>0&&endTime.peek()[0]<=(timeStartEndi[i][0]-1)){
                if (endTime.peek()[0]==(timeStartEndi[i][0]-1)) {
                    if (endTime.peek()[1] > timeStartEndi[i][1])
                        break;
                }
                endTime.poll();
            }

            endTime.add(timeEnd);


            if (endTime.size()>answer){
                answer = endTime.size();
            }

        }

        return answer;
    }
    public static int[] getTime(String s){
        int timeEndStart[] = new int[4];
        String take[] = new String[2];

        String []temparray = s.split(" ");
        String []time = temparray[1].split(":");        //시 분 초를 담음
        String ms = time[2].substring(3,6);   //초와 미리초를 담음
        time[2] = time[2].substring(0,2);       //초를 담음 , 미리세컨은 ms[1]  번에 담음
        timeEndStart[2] = 3600*Integer.parseInt(time[0])+60*Integer.parseInt(time[1]) +Integer.parseInt(time[2]);
        timeEndStart[3] = Integer.parseInt(ms);
        temparray[2] = temparray[2].substring(0,temparray[2].length()-1);     //걸린 시간을 담음

        if (temparray[2].contains(".")){

            take = temparray[2].split("\\.");

            timeEndStart[0] = timeEndStart[2] - Integer.parseInt(take[0]);
            if (timeEndStart[3]+1<Integer.parseInt(take[1]) ){   //뺄 소수가 더 큰 경우
                timeEndStart[0]--;          //초에서 미리세컨으로 빌려옴
                timeEndStart[1] =   (1000 +timeEndStart[3] -Integer.parseInt(take[1])+1);
            }else{
                timeEndStart[1] =  (timeEndStart[3] - Integer.parseInt(take[1]) +1);
            }
        }
        else{       //소수점 단위가 없었을 경우.
            timeEndStart[0] = timeEndStart[2] - Integer.parseInt(temparray[2]);
            timeEndStart[1] = timeEndStart[3]+1;
        }
        return timeEndStart;
    }



    public static void main(String[] args) {

        String temp[] = {"2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"};
        System.out.println(solution(temp));
    }


}

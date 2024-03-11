import java.util.*;

public class Main {
    public static boolean canDoTrips(int n, int k, List<Integer> sheep, int weight){

        Queue<Integer> sheepQueue = new ArrayDeque<>(sheep) ;

        for (int i = 0; i < k; i++){
            int salWeight = weight ;
            int sheepQueueSize = sheepQueue.size() ;

            for (int j = 0; j < sheepQueueSize; j++){
                int newSheep = sheepQueue.peek() ;

                salWeight -= newSheep ;
                if(salWeight < 0){

                    if(j == sheepQueueSize - 1){
                        sheepQueue.add(sheepQueue.poll()) ;
                        break ;
                    }
                    salWeight += newSheep ;
                    sheepQueue.add(sheepQueue.poll()) ;
                    continue;
                }

                sheepQueue.poll() ;
            }
        }

        if(sheepQueue.isEmpty()){
            return true ;
        }else{
            return false ;
        }
    }
    public static int checkMinimumWeight(int n, int k, List<Integer> sheep, int salMinimumWeightGuess){
        for (int i = salMinimumWeightGuess;  ; i++){
            if(canDoTrips(n, k, sheep, i)){
                return i ;
            }
        }
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in) ;

        int n = scanner.nextInt() ;
        int k = scanner.nextInt();

        List<Integer> sheep = new ArrayList<>() ;
        //input
        for (int i = 0; i < n; i++){
            sheep.add(scanner.nextInt()) ;
        }
        /////
        //sort array
        sheep.sort((o1, o2) -> {
            return o2 - o1;
        });
        //find sum of weights
        int weightsSum = 0 ;

        for(int i = 0; i < sheep.size(); i++){
            weightsSum += sheep.get(i) ;
        }
        //
        int salMinimumWeightGuess = (int)Math.ceil(weightsSum/k) ;

        int salMinimumWeight = checkMinimumWeight(n, k, sheep, salMinimumWeightGuess) ;

        System.out.println(salMinimumWeight);
    }
}
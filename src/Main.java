import jdk.jshell.JShell;

import java.util.*;

public class Main {
    //checks if trips with the current sal weight are possible
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
    //checks what is the minimum sal weight starting from arithmetic mean
    public static int checkMinimumWeight(int n, int k, List<Integer> sheep, int salMinimumWeightGuess){
        for (int i = salMinimumWeightGuess;  ; i++){
            if(canDoTrips(n, k, sheep, i)){
                return i ;
            }
        }
    }
    public static int findWeightSum(List<Integer> sheep, int n){
        int weightsSum = 0 ;

        for(int i = 0; i < sheep.size(); i++){
            weightsSum += sheep.get(i) ;
        }

        return weightsSum ;
    }
    public static void input(List<Integer> sheep, Scanner scanner, int n){
        for (int i = 0; i < n; i++){
            sheep.add(scanner.nextInt()) ;
        }
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in) ;

        List<Integer> sheep = new ArrayList<>() ;

        int n = scanner.nextInt() ;
        int k = scanner.nextInt();


        //input
        input(sheep, scanner, n) ;
        /////

        //sort array
        sheep.sort((o1, o2) -> {
            return o2 - o1;
        });

        //find sum of weights
        int weightsSum = findWeightSum(sheep, n) ;
        //

        //find arithmetic mean of sheep weights per trip
        int salMinimumWeightGuess = (int)Math.floor(weightsSum/k) ;
        //

        //find sal minimum wight
        int salMinimumWeight = checkMinimumWeight(n, k, sheep, salMinimumWeightGuess) ;
        //

        //print minimum weight
        System.out.println(salMinimumWeight);
    }
}
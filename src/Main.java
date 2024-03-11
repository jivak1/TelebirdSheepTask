import jdk.jshell.JShell;
import java.util.*;

//testafter reinstalling git

public class Main {
    //checks if trips with the current sal weight are possible
    public static boolean canDoTrips(int n, int k, List<Integer> sheep, int weight){
        //adds sheep tp a queue
        Queue<Integer> sheepQueue = new ArrayDeque<>(sheep) ;

        //iterates over every trip
        for (int i = 0; i < k; i++){
            int salWeight = weight ;
            int sheepQueueSize = sheepQueue.size() ;

            //adds sheep to sal on each trip
            for (int j = 0; j < sheepQueueSize; j++){
                int newSheep = sheepQueue.peek() ;

                //subtract sheep weight
                salWeight -= newSheep ;

                //checks if weight is positive and sheep can be added to sal
                if(salWeight < 0){
                    //checks if this is the last sheep to be checked
                    if(j == sheepQueueSize - 1){
                        sheepQueue.add(sheepQueue.poll()) ;
                        break ;
                    }

                    //fixes remaining sal weight if current sheep cant be added
                    salWeight += newSheep ;

                    //returns sheep to queue if it can't be added
                    sheepQueue.add(sheepQueue.poll()) ;

                    //goes to next sheep in queue
                    continue;
                }else{
                    //removes sheep if it can be added to the sal
                    sheepQueue.poll();
                }
            }
        }

        //if queue is empty than sal weight can transport the sheep
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

    //finds sheep weight sum
    public static int findWeightSum(List<Integer> sheep, int n){
        int weightsSum = 0 ;

        for(int i = 0; i < sheep.size(); i++){
            weightsSum += sheep.get(i) ;
        }

        return weightsSum ;
    }

    //renders sheep input from system in
    public static void input(List<Integer> sheep, Scanner scanner, int n){
        for (int i = 0; i < n; i++){
            sheep.add(scanner.nextInt()) ;
        }
    }

    public static void main(String[] args) {
        //scanner takes input from terminal
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
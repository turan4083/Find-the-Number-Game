import java.io.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;


public class numberFinderWithoutTimer {


    // get a random 3 digit number that is not prime
    public static int notPrime3digitRandom() {
        int primeNumbers[] = new int[] {103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193,
                197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313,
                317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443,
                449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587,
                593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719,
                727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859,
                863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971};
        int targetNumber = ThreadLocalRandom.current().nextInt(100, 1000);
        while (Arrays.asList(primeNumbers).contains(targetNumber)){
            targetNumber = ThreadLocalRandom.current().nextInt(100, 1000);
        }
        return targetNumber;
    }

    public static String[] mathParser(String expressionStr){
        int strSize = expressionStr.length();
        int firstOperandNum = 0;
        String firstOperand = "";
        String operator = "";
        String secondOperand = "";
        for (int i = 0; i < strSize; i++){
            firstOperandNum++;
            if (expressionStr.charAt(firstOperandNum) == '+'|| expressionStr.charAt(firstOperandNum) == '-' ||
                    expressionStr.charAt(firstOperandNum) == '*' || expressionStr.charAt(firstOperandNum) == '/') {
                firstOperand = expressionStr.substring(0,firstOperandNum);
                operator = expressionStr.substring(firstOperandNum, firstOperandNum + 1);
                secondOperand = expressionStr.substring(firstOperandNum + 1);
                break;
            }
        }
        String[] parsedArr = {firstOperand, operator, secondOperand};
        return parsedArr;
    }

    public static int pointCalc(int result, int targetNum) {
        int point;
        if (result - targetNum == 0 || targetNum - result == 0) {
            return  25;
        } else if (result - targetNum == 1 || targetNum - result == 1) {
            System.out.println("Hedef say??ya " + 1 + " rakam yak??n bir say?? buldunuz");
            return  20;
        } else if (result - targetNum == 2 || targetNum - result == 2) {
            System.out.println("Hedef say??ya " + 2 + " rakam yak??n bir say?? buldunuz");
            return  15;
        } else if (result - targetNum == 3 || targetNum - result == 3) {
            System.out.println("Hedef say??ya " + 3 + " rakam yak??n bir say?? buldunuz");
            return 10;
        } else if (result - targetNum == 4 || targetNum - result == 4) {
            System.out.println("Hedef say??ya " + 4 + " rakam yak??n bir say?? buldunuz");
            return  5;
        } else {
            return 0;
        }
    }

    public static void mainMenuGreet(){
        System.out.println("\nSay?? bulma oyununa ho??geldiniz");
        System.out.println("*********** KURALLAR **********");
        System.out.println("Soru ba????na s??re 3 dakikad??r");
        System.out.println("*******************************");
        System.out.println("Yeni oyuna ge??mek i??in 'y' tu??una bas??n??z");
        System.out.println("Oyunu kapatmak i??in 'q' tu??una bas??n??z");
        System.out.println("Y??ksek Skorlara bakmak i??in 'skor' yaz??n??z");

    }

    public static void main(String[] args) {
        try {

            File file = new File("highScores.txt");

            FileWriter writer = new FileWriter("highScores.txt", true);

            mainMenuGreet(); // print the overview of game

            outerLoop: // game loop, if you break this loop game will close
            while (true) { // this loop starts a new game
                Scanner input = new Scanner(System.in);
                int targetNumber = notPrime3digitRandom(); // generate 3 digit not prime number
                ArrayList<Integer> guessNumbers = new ArrayList<Integer>();
                System.out.println("\nYeni Oyun");

                // generate one digit number and add to the list 5 times
                int numberOneDigit;
                for (int i = 0; i < 5; i++) {
                    numberOneDigit = ThreadLocalRandom.current().nextInt(1, 10);
                    guessNumbers.add(numberOneDigit);
                }
                // generate 2 digit number one time
                int numberTwoDigit = ThreadLocalRandom.current().nextInt(10, 100);
                guessNumbers.add(numberTwoDigit);
                int count = 1; // at the beginning of the turn make the count number 1
                while (true) { // loop for each game turn
                    try {
                        if (count != 5) { // if guess is not over
                            System.out.println("Hedef Say??: " + targetNumber);
                            System.out.println("Kullan??labilecek say??lar: " + guessNumbers.toString());
                        }
                        if (count == 5) { // if guess is over
                            System.out.println("????lem hakk??n??z bitti");
                            System.out.println("Yeni oyuna ge??iliyor...");
                            break;
                        }

                        System.out.println(count + ". i??lem"); // print the guess number
                        System.out.println("????lem giriniz: ");
                        String op = input.nextLine(); // take the operation as string

                        if (op.equals("y")) {
                            System.out.println("Yeni oyuna ge??iliyor...");
                            break;
                        }
                        if (op.equals("q")) {
                            System.out.println("Kapan??yor...");
                            writer.flush();
                            System.exit(0);
                        }
                        if (op.equals("skor")) {
                            BufferedReader br = new BufferedReader(new FileReader(file));
                            count--;
                            String st;
                            // Condition holds true till
                            // there is character in a string
                            while ((st = br.readLine()) != null)
                                // Print the string
                                System.out.println(st);
                        }
                        else {
                            String[] asd = mathParser(op);
                            int firstOperand = Integer.parseInt(asd[0]);
                            int secondOperand = Integer.parseInt(asd[2]);
                            if (!(guessNumbers.contains(firstOperand) && guessNumbers.contains(secondOperand))) {
                                System.out.println("????lemdeki say??lar kullan??labilecek say??lar aras??nda de??il");
                                throw new Exception();
                            }
                            char operator = asd[1].charAt(0);
                            int result = 0;
                            if (operator == '+') {
                                result = firstOperand + secondOperand;
                            } else if (operator == '-') {
                                result = firstOperand - secondOperand;
                            } else if (operator == '*') {
                                result = firstOperand * secondOperand;
                            } else if (operator == '/') {
                                result = firstOperand / secondOperand;
                            }
                            System.out.println("Sonu??: " + result);
                            guessNumbers.remove(new Integer(firstOperand));
                            guessNumbers.remove(new Integer(secondOperand));
                            guessNumbers.add(result);
                            int point = pointCalc(result, targetNumber);
                            if (point == 25) {
                                System.out.println("Tebrikler... Say??y?? buldunuz");
                                System.out.println("Puan??n??z: " + point);
                                writer.write("\n" + point);
                                break;
                            } else if (point > 0) {
                                if (count == 4) {
                                    System.out.println("????lem hakk??n??z bitti");
                                    System.out.println(point + " Puan ald??n??z");
                                    System.out.println("Yeni oyuna ge??iliyor...");
                                    break;
                                } else {
                                    System.out.println(point + " Puan ald??n??z");
                                    System.out.println("Say??y?? bulmaya devam Etmek ister misiniz (yes/no)");
                                    String decision = input.nextLine();
                                    if (decision.equals("no")) {
                                        System.out.println("Puan??n??z: " + point);
                                        writer.write("\n" + point);
                                        break;
                                    } else {
                                        count++;
                                        continue;
                                    }
                                }
                            }
                        }
                        count++;
                    } catch (Exception e) {
                        System.out.println("hatal?? i??lem");
                    }
                }
                writer.flush();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}

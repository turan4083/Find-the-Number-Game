import java.io.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class numberFinder {

    public static class timeIsUp extends TimerTask {

        public static boolean timeChecker = false;

        @Override
        public void run(){
            timeChecker = true;
            System.out.println("Zaman doldu");
            System.out.println("Lütfen yeni oyun başlatınız ('y' tuşunu giriniz)");
        }
    }

    public static class timeLeft20 extends TimerTask {

        @Override
        public void run(){
            System.out.println("20 saniyeniz kaldı");
        }
    }

    public static class timeLeft10 extends TimerTask {

        @Override
        public void run(){
            System.out.println("10 saniyeniz kaldı");
        }
    }

    public static class timeLeft5 extends TimerTask {

        @Override
        public void run(){
            System.out.println("5 saniyeniz kaldı");
        }
    }

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
            System.out.println("Hedef sayıya " + 1 + " rakam yakın bir sayı buldunuz");
            return  20;
        } else if (result - targetNum == 2 || targetNum - result == 2) {
            System.out.println("Hedef sayıya " + 2 + " rakam yakın bir sayı buldunuz");
            return  15;
        } else if (result - targetNum == 3 || targetNum - result == 3) {
            System.out.println("Hedef sayıya " + 3 + " rakam yakın bir sayı buldunuz");
            return 10;
        } else if (result - targetNum == 4 || targetNum - result == 4) {
            System.out.println("Hedef sayıya " + 4 + " rakam yakın bir sayı buldunuz");
            return  5;
        } else {
            return 0;
        }
    }

    public static void mainMenuGreet(){
        System.out.println("\nSayı bulma oyununa hoşgeldiniz");
        System.out.println("*********** KURALLAR **********");
        System.out.println("Soru başına süre 3 dakikadır");
        System.out.println("*******************************");
        System.out.println("Yeni oyuna geçmek için 'y' tuşuna basınız");
        System.out.println("Oyunu kapatmak için 'q' tuşuna basınız");
        System.out.println("Yüksek Skorlara bakmak için 'skor' yazınız");
        System.out.println("Ana menüye dönmek için 'm' tuşuna basınız");

    }

    public static void main(String[] args) {
        try {

            File fileObj = new File("C:\\Users\\turan\\IdeaProjects\\extra\\src\\highScores.txt");

            FileWriter writer = new FileWriter("C:\\Users\\turan\\IdeaProjects\\extra\\src\\highScores.txt", true);

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

                // create the timers
                Timer timer = new Timer();
                timer.schedule(new timeIsUp(), 180000); // 3 min
                timer.schedule(new timeLeft20(), 160000); // 20 sec left
                timer.schedule(new timeLeft10(), 170000); // 10 sec left
                timer.schedule(new timeLeft5(), 175000); // 5 sec left

                long start = System.currentTimeMillis(); // count the elapsed time to find the game time

                int count = 1; // at the beginning of the turn make the count number 1
                while (true) { // loop for game turn
                    try {
                        if (count != 5) { // if guess is not over
                            System.out.println("Hedef Sayı: " + targetNumber);
                            System.out.println("Kullanılabilecek sayılar: " + guessNumbers.toString());
                        }
                        if (count == 5) { // if guess is over
                            System.out.println("İşlem hakkınız bitti");
                            System.out.println("Yeni oyuna geçiliyor...");
                            break;
                        }

                        System.out.println(count + ". işlem"); // print the guess number
                        System.out.println("İşlem giriniz: ");
                        String op = input.nextLine(); // take the operation as string
                        if (timeIsUp.timeChecker) { // if timerTask(timeIsUp) runs start a new game
                            System.out.println("Yeni oyuna geçiliyor...");
                            timer.cancel();
                            timer.purge();
                            timeIsUp.timeChecker = false;
                            break;
                        }
                        if (op.equals("y")) {
                            System.out.println("Yeni oyuna geçiliyor...");
                            break;
                        }
                        if (op.equals("q")) {
                            System.out.println("Kapanıyor...");
                            System.exit(0);
                        }
                        String[] asd = mathParser(op);
                        int firstOperand = Integer.parseInt(asd[0]);
                        int secondOperand = Integer.parseInt(asd[2]);
                        if (!(guessNumbers.contains(firstOperand) && guessNumbers.contains(secondOperand))) {
                            System.out.println("İşlemdeki sayılar kullanılabilecek sayılar arasında değil");
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
                        System.out.println("Sonuç: " + result);
                        guessNumbers.remove(new Integer(firstOperand));
                        guessNumbers.remove(new Integer(secondOperand));
                        guessNumbers.add(result);
                        int point = pointCalc(result, targetNumber);
                        if (point == 25) {
                            timer.cancel();
                            long end = System.currentTimeMillis();
                            long elapsedTime = (end - start) / 1000;
                            System.out.println("Tebrikler... Sayıyı buldunuz");
                            System.out.println("Puanınız: " + point);
                            writer.write(point + "\t" + java.time.LocalDate.now() + "\t" + elapsedTime);
                            break;
                        }
                        else if (point != 0) {
                            System.out.println(point + " Puan aldınız");
                            System.out.println("Sayıyı bulmaya devam Etmek ister misiniz (evet/hayır)");
                            String decision = input.nextLine();
                            if (decision.equals("hayır")) {
                                timer.cancel();
                                timer.purge();
                                long end = System.currentTimeMillis();
                                long elapsedTime = (end - start) / 1000;
                                System.out.println("Puanınız: " + point);
                                writer.write(point + "\t" + java.time.LocalDate.now() + "\t" + elapsedTime);
                                count = 0;
                                break;
                            } else {
                                continue;
                            }
                        }
                        writer.close();
                        count++;
                    } catch (Exception e) {
                        System.out.println("hatalı işlem");
                    }
                }
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

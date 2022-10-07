import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();
        String[] texts = new String[100_000];

        Thread rand;
        rand = new Thread(()->{
            for (int i = 0; i < texts.length; i++) {
                texts[i] = generateText("abc", 3 + random.nextInt(3));
            }
        });
        rand.start();

        Calculation calculation1 = new Calculation(texts,1,rand);
        Calculation calculation2 = new Calculation(texts,2,rand);
        Calculation calculation3 = new Calculation(texts,3,rand);

        try {
            calculation1.t.join();
            calculation2.t.join();
            calculation3.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Красивых слов с длиной 3: " + Calculation.counter1 + " шт");
        System.out.println("Красивых слов с длиной 4: " + Calculation.counter2 + " шт");
        System.out.println("Красивых слов с длиной 5: " + Calculation.counter3 + " шт");

    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

}

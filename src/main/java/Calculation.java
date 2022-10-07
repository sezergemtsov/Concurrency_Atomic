import java.util.concurrent.atomic.AtomicInteger;

public class Calculation implements Runnable{

    Thread t;
    Thread running;
    String[] texts;
    int mode;

    public static AtomicInteger counter1 = new AtomicInteger(0);
    public static AtomicInteger counter2 = new AtomicInteger(0);
    public static AtomicInteger counter3 = new AtomicInteger(0);

    public Calculation(String[] texts, int mode, Thread running) {
        t = new Thread(this);
        this.texts = texts;
        this.mode = mode;
        this.running = running;
        t.start();
    }

    @Override
    public void run() {
        try {
            running.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean isTrue = false;

        for (String x:texts) {
            switch (mode) {
                case 1: isTrue = palindrome(x);
                break;
                case 2: isTrue = same(x);
                break;
                case 3: isTrue = increasing(x);
                break;
            }
            if (isTrue&x.length()==3) {
                counter1.incrementAndGet();
            } else if (isTrue & x.length() == 4) {
                counter2.incrementAndGet();
            } else if (isTrue & x.length() == 5) {
                counter3.incrementAndGet();
            }
        }

    }

    public static boolean palindrome(String name) {
        String revers ="";
        for (int i = name.length()-1; i < 0; i++) {
            revers += name.charAt(i);
        }
        if (name.equals(revers)) {
            return true;
        }
        return false;
    }
    public static boolean same(String name) {
        boolean isTrue = true;
        for (int i = 1; i < name.length(); i++) {
            if ((int)name.charAt(i) != (int)name.charAt(i - 1)) {
                isTrue = false;
            }
        }
        return isTrue;
    }
    public static boolean increasing(String name) {
        boolean isTrue = true;
        for (int i = 1; i < name.length(); i++) {
            if (name.charAt(i) < name.charAt(i - 1)) {
                isTrue = false;
            }
        }
        return isTrue;
    }
}

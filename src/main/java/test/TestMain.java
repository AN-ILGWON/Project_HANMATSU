package test;

public class TestMain {
    public static void main(String[] args) {
        System.out.println("Starting TestMain...");
        try {
            for (int i = 0; i < 60; i++) {
                System.out.println("Sleep " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("TestMain finished.");
    }
}

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(0);

        Thread depositThread = new Thread(() -> {
            Random random = new Random();
            while (true) {
                int amount = random.nextInt(501); // генерируем число от 0 до 500
                account.deposit(amount);
                try {
                    Thread.sleep(random.nextInt(2000)); // задержечка
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        depositThread.start();

        int requiredAmount = 500; //при какой сумме снимать
        while (true) {
            synchronized (account) {
                while (account.getBalance() < requiredAmount) {
                    account.wait();
                }
                account.withdraw(requiredAmount);
            }
        }
    }
}
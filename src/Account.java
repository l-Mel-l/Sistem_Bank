public class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println("Пополнение на " + amount + ". Новый баланс: " + balance);
        notifyAll();
    }

    public synchronized void withdraw(int amount) throws InterruptedException {
        while (balance < amount) {
            wait();
        }
        balance -= amount;
        System.out.println("Снятие " + amount + ". Новый баланс: " + balance);
    }

    public int getBalance() {
        return balance;
    }
}
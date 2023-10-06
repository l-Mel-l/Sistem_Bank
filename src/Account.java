public class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public synchronized void deposit(int amount) { //пополнение баланса
        balance += amount;
        System.out.println("Пополнение на " + amount + ". Новый баланс: " + balance);
        notifyAll(); //повещаем все потоки
    }

    public synchronized void withdraw(int amount) throws InterruptedException { //снятие с баланса
        while (balance < amount) { //ждём, если на балансе меньше 500 рублей
            wait();
        }
        balance -= amount;
        System.out.println("Снятие " + amount + ". Новый баланс: " + balance);
    }

    public int getBalance() {
        return balance;
    }
}
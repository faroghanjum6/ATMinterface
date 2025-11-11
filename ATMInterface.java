import java.util.Scanner;

// 4. Class to represent the user's bank account
class BankAccount {
    private String accountHolderName;
    private double balance;

    public BankAccount(String name, double initialBalance) {
        this.accountHolderName = name;
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        System.out.println("Deposit successful. Amount: " + amount);
    }

    // Withdraw method
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds. Current balance: " + balance);
            return false;
        }
        balance -= amount;
        System.out.println("Withdrawal successful. Amount: " + amount);
        return true;
    }
}

// 1. Class to represent the ATM machine
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // 2. Design the user interface for the ATM
    public void start() {
        System.out.println("Welcome, " + account.getAccountHolderName() + "!");

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                continue;
            }

            switch (choice) {
                case 1:
                    // 3. Implement method checkBalance()
                    checkBalance();
                    break;
                case 2:
                    depositOption();
                    break;
                case 3:
                    withdrawOption();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please select 1-4.");
            }
        }
    }

    private void checkBalance() {
        System.out.println("Your current balance is: " + account.getBalance());
    }

    private void depositOption() {
        System.out.print("Enter amount to deposit: ");
        double amount = 0;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered.");
            return;
        }
        account.deposit(amount);
        System.out.println("Updated balance: " + account.getBalance());
    }

    private void withdrawOption() {
        System.out.print("Enter amount to withdraw: ");
        double amount = 0;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered.");
            return;
        }
        boolean success = account.withdraw(amount);
        if (success) {
            System.out.println("Please collect your cash.");
        }
        System.out.println("Updated balance: " + account.getBalance());
    }
}

// Main class to run the ATM simulation
public class ATMInterface {
    public static void main(String[] args) {
        // Create a bank account for the user
        BankAccount myAccount = new BankAccount("Alice", 1000.0);

        // Create ATM and link to the user's bank account
        ATM atm = new ATM(myAccount);

        // Start the ATM interface
        atm.start();
    }
}
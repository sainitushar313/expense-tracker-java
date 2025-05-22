package src;

import java.time.LocalDate;
import java.util.Scanner;

public class ExpenseTracker {
    public static void main(String[] args) {
        TransactionManager manager=new TransactionManager();
        Scanner scanner=new Scanner(System.in);
        String fileName="transactions.csv";

                 // Defining  subcategoy
        String[] incCategories={"Salary","Business"};
        String[] expCategories={"Food","Rent","Travel"};

        while(true){
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Monthly Summary");
            System.out.println("4. Load from file");
            System.out.println("5. Save to file");
            System.out.println("6. Exit");
            System.out.println();
            System.out.print("Enter choice: ");

            int choice=scanner.nextInt();
            scanner.nextLine();


            switch(choice){
                case 1-> {
                    TransactionType type=TransactionType.INCOME;
                    System.out.println("Choose income Sub-Category:");
                    for (int i=0;i<incCategories.length;i++) {
                        System.out.println((i + 1)+". "+incCategories[i]);}

                    System.out.println();
                    System.out.print("Enter choice: ");

                    int catChoice=scanner.nextInt();
                    scanner.nextLine();
                    String category=incCategories[catChoice - 1];


                    System.out.print("Enter amount: ");
                    double amount=scanner.nextDouble();
                    scanner.nextLine();

                    Transaction t=new Transaction(LocalDate.now(),type,category,amount);
                    manager.addTransaction(t);
                    System.out.println("Income added successfully."); }

                case 2->{
                    TransactionType type=TransactionType.EXPENSE;
                    System.out.println("Choose expense sub-category:");
                    for (int i=0;i <expCategories.length;i++) {
                        System.out.println((i + 1)+ ". "+expCategories[i]);
                    }
                    int catChoice=scanner.nextInt();
                    scanner.nextLine();
                    String category=expCategories[catChoice - 1];

                    System.out.print("Enter amount: ");
                    double amount=scanner.nextDouble();
                    scanner.nextLine();

                    Transaction t=new Transaction(LocalDate.now(),type,category,amount);
                    manager.addTransaction(t);
                    System.out.println("Expense added successfully");
                }

                case 3->{
                    System.out.print("Enter month (1-12): ");
                    int month=scanner.nextInt();
                    System.out.print("Enter year: ");
                    int year=scanner.nextInt();
                    manager.monthlySummary(month,year);
                }

                case 4->{
                    System.out.print("Enter file name too load from: ");
                    String inputFile=scanner.nextLine();
                    manager.loadFromFile(inputFile);
                }

                case 5->{
                    System.out.print("Enter file name to save to: ");
                    String saveFile =scanner.nextLine();
                    manager.saveToFile(saveFile);
                }

                case 6->{
                    System.out.println("Exit");
                    return;
                }

                default -> System.out.println("Invalid choice");
            }
        }
    }
}

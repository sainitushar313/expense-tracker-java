package src;

import java.time.LocalDate;

public class Transaction{
    private LocalDate date;
    private TransactionType type;
    private String category;
    private double amount ;

    public Transaction(LocalDate date,TransactionType type,String category,double amount){
        this.date=date;
        this.type= type;
        this.category=category;
        this.amount=amount;
    }

    public LocalDate getDate(){
        return date;
    }

    public TransactionType getType(){
        return type;
    }

    public String getCategory(){
        return category;
    }

    public double getAmount(){
        return amount; }

    @Override
    public String toString(){
        return date + "," +type + "," +category+ "," +amount;
    }
}

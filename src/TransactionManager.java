package src;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TransactionManager{
    private List<Transaction> transactions=new ArrayList<>();
    private final DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void addTransaction(Transaction t){
        transactions.add(t); }

    public void loadFromFile(String filename){
        try(BufferedReader br=new BufferedReader(new FileReader(filename))) {
            String line;
            while((line= br.readLine()) !=null) {
                String[] parts=line.split(",");
                LocalDate date=LocalDate.parse(parts[0],formatter);
                TransactionType type=TransactionType.valueOf(parts[1]);
                String category=parts[2];
                double amount=Double.parseDouble(parts[3]);
                transactions.add(new Transaction(date,type,category,amount));
            }
            System.out.println("Data loaded from file successfully.");
        } catch(IOException e){
            System.out.println("Error reading the file: "+e.getMessage());
        }
    }

    public void saveToFile(String filename){
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(filename))) {
            for(Transaction t:transactions) {
                bw.write(t.toString());
                bw.newLine(); }
            System.out.println("Data saved successfuully");
        } catch(IOException e) {
            System.out.println("Error writing the file: "+e.getMessage());
        }}

    public void monthlySummary(int month,int year) {
        double incTotal=0, expTotal=0;
        Map<String, Double> categorySummary = new HashMap<>();

        for (Transaction t : transactions) {
            if (t.getDate().getMonthValue()==month && t.getDate().getYear()==year) {
                categorySummary.put(t.getCategory(),
                        categorySummary.getOrDefault(t.getCategory(),0.0)+t.getAmount());

                if(t.getType()==TransactionType.INCOME)
                    incTotal+= t.getAmount();
                else{
                    expTotal +=t.getAmount();
            }}
        }

        System.out.println("Monthly Summary for "+ month + "/" +year+ ":");
        System.out.println("Total Income: "+ incTotal);
        System.out.println("Total Expenses: "+ expTotal);
        System.out.println("Breakdown by Category:");
        categorySummary.forEach((k, v) -> System.out.println(k + ": "+v));
    }
}


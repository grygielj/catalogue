package pl.kubie.catalogue;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInput {
    private final Scanner scanner;

    public UserInput(Scanner scanner) {
        this.scanner = scanner;
    }
    public Game game(){
        String name = readString("Enter name");
        String type = readString("Enter type");
        String comment = readString("Enter comment");
        Double rate = readDouble("Enter rate");
        return new Game(name, type, comment, rate);
    }
    public String gameId(){
        return readString("Enter name:");
    }
    public Game update(Game that){
        String type = readString("Enter type",that.getType());
        String comment = readString("Enter comment",that.getComment());
        Double rate = readDouble("Enter rate",that.getRate());
        return new Game(that.getName(),type,comment,rate);
    }
    public int menuChoice(){
        System.out.println(Menu.mainMenu());
        System.out.println("Enter choice");
        return Integer.parseInt(scanner.nextLine());
    }
    public int findChoice(){
        System.out.println(Menu.findMenu());
        return Integer.parseInt(scanner.nextLine());
    }
    public double rateToFind() {
        return readDouble("Enter rate:");
    }

    public LocalDate dateToFind() {
        return LocalDate.parse(readString("Enter date (format: yyyy-MM-dd):"));
    }

    private String readString(String message){
        System.out.println(message);
        return scanner.nextLine();
    }
    private Double readDouble(String message){
        System.out.println(message);
        return Double.parseDouble(scanner.nextLine());
    }
    private String readString(String message,String oldValue){
        System.out.println(message);
        String s = scanner.nextLine();
        return s.isEmpty() ? oldValue : s;
    }
    private Double readDouble(String message,Double oldValue){
        System.out.println(message);
        String s = scanner.nextLine();
        return s.isEmpty() ? oldValue : Double.parseDouble(s);
    }

}

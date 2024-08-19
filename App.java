import java.util.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;


public class App {
    private static Map<String, Account> userDatabase = new HashMap<>(); // Store user accounts
    private static Map<String, Account> info = new HashMap<>(); // Store temporary user info for sign up
    private static Account currAccount;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        loadUser();

        String red = "\033[0;31m"; // Red color text
        String reset = "\033[0m";  // Reset color to default
        String green = "\u001B[32m"; // Green color text
        String blue = "\033[0;34m"; // Blue color text
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println(green + "           Welcome to Bank Managment Project"+ reset);
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        System.out.println("");
        System.out.println(blue + "1" + reset+ ". Login");
        System.out.println(blue + "2" +reset + ". Signup");
        System.out.println(blue + "3" + reset +". Exit");
        int input = sc.nextInt();

        if(input ==3){
            System.exit(0);
            saveUser();
        }
        if(input == 1){
           login(sc);
        }
        if(input == 2){
            signUp(sc);
        }
        saveUser();
    }
    private static void login(Scanner sc){
        String reset = "\033[0m";  // Reset color to default
        String green = "\u001B[32m"; // Green color text
        String red = "\033[0;31m"; // Red color text
        String blue = "\033[0;34m"; // Blue color text
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println(green + "         Welcome back to Bank Managment Project"+ reset);
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Enter 0 at any moment to exit the program.");
        System.out.println("");
        String userName,passWord;

            System.out.print("Username: ");
            userName = sc.next();
            if(userName.equals("0")){
                System.exit(0);
                saveUser();
            }
            System.out.print("Password: ");
            passWord = sc.next();
                if(passWord.equals("0")){
                    System.exit(0);
                    saveUser();
                }
                Account dataBase = userDatabase.get(userName);
                 if (dataBase != null) {
        // Check if the password matches
        String databasePass = dataBase.getPassword();
        if (passWord.equals(databasePass)) {
            currAccount = dataBase;
            System.out.println(blue + "Login successful!" + reset);
            accMenu(sc);
        } else {
            System.out.println(red + "Incorrect password. Please try again." + reset);
            try {
                Thread.sleep(3000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            login(sc); // Retry login
        }
    } else {
        System.out.println(red+ "Username not found. Please try again." + reset);
        try {
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        login(sc); // Retry login
    }
    saveUser();
        }
    private static void signUp(Scanner sc){
    String reset = "\033[0m";  // Reset color to default
    String green = "\u001B[32m"; // Green color text
    String red = "\033[0;31m"; // Red color text
    String blue = "\033[0;34m"; // Blue color text

    String fullName;
    String userName;
    String passWord;
    String email;
    int balance;
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println(green + "            Welcome to Bank Project - Sign Up"+ reset);
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        System.out.println("");
        System.out.println("Please enter the following details to create a new account: ");
        System.out.println("---------------------------------------------------------");
    boolean isValid,passValid;
    System.out.println("Enter 0 at any moment to exit the program.");
System.out.println("");
    System.out.print("Enter your full name: ");
    sc.nextLine();
        fullName = sc.nextLine();
        if(fullName.equals("0")){
            System.exit(0);
        }
        //Username 
    do{
        System.out.print("Create a usernname: ");
            userName = sc.next();
            if(userName.equals("0")){
                System.exit(0);
            }
            sc.nextLine();
            isValid = checkUser(userName);
        if(!isValid){
            System.out.println(red + "Enter a valid username."+"\n" + reset);
        }
    }while(!isValid);
        //Password
    do{
        System.out.print("Create a password: ");
            passWord = sc.next();
            if(passWord.equals("0")){
                System.exit(0);
            }
            passValid = checkPass(passWord);
            
        if(!passValid){
            System.out.println(red + "Enter a valid password."+"\n" + reset);
        }
    }while(!passValid);
        //Email 
    do{
    System.out.print("Enter your email address: ");
    email = sc.next();
    if(email.equals("0")){
        System.exit(0);
    }
    if(!checkEmail(email)){
        System.out.println(red + "Invalid email address."+"\n" + reset);
    }
    }while(!checkEmail(email));
    System.out.print("Enter Starting balance: ");
        balance = sc.nextInt();
        
    System.out.println("");
        System.out.println(blue + "Account creation successful." + reset);
        System.out.println("");
        Account account = new Account(fullName, passWord, email, balance);
       info.put(userName,account);
       userDatabase.put(userName,account);
       //5-second delay
        try {
        Thread.sleep(3000); 
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    saveUser();
login(sc);
    }

    public static void loadUser() {
        try (FileInputStream fis = new FileInputStream("userDatabase.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            userDatabase = (HashMap<String, Account>) ois.readObject();
            System.out.println("User database loaded: " + userDatabase);

        } catch (IOException | ClassNotFoundException e) {
            userDatabase = new HashMap<>();
        }
    }
    public static void saveUser() {
    try (FileOutputStream fos = new FileOutputStream("userDatabase.ser");
         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
        oos.writeObject(userDatabase);
        System.out.println("User database saved: " + userDatabase);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private static boolean checkUser(String str){
        boolean isValid = true;
        if(str.length() < 12){
            System.out.println("Username must be more than 12 characters.");
            isValid = false;
        }if(!str.matches(".*\\d.*")){
            System.out.println("Username must contain atleast one number.");
            isValid = false;
        }
        if(str.matches(".*[^a-zA-Z0-9].*")){
            System.out.println("Username cannot contain a special character.");
            isValid = false;
        }
        return isValid;
    }
    private static boolean checkPass(String str){
        boolean passValid=true;
        if(str.length() < 8){
            System.out.println("Password must be more than 8 characters.");
            passValid = false;
        }if(!str.matches(".*\\d.*")){
            System.out.println("Password must contain atleast one number.");
            passValid = false;
        }
        if(!str.matches(".*[^a-zA-Z0-9].*")){
            System.out.println("Password must contain atleast one special character.");
            passValid = false;
        }
        return passValid;
    }
    private static boolean checkEmail(String str){
        String email = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return str.matches(email);
    }

    private static void accMenu(Scanner sc){
        String red = "\033[0;31m"; // Red color text
        String reset = "\033[0m";  // Reset color to default
        String green = "\u001B[32m"; // Green color text
        String blue = "\033[0;34m"; // Blue color text

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println(green + "            Bank Project - Main Menu"+ reset);
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            System.out.println(blue + "1" + reset + ". View Account Balance");
            System.out.println(blue + "2" + reset + ". Deposit Money");
            System.out.println(blue + "3" + reset + ". Withdraw Money");
            System.out.println(blue + "4" + reset + ". Transfer Money");
            System.out.println(blue + "5" + reset + ". View Transaction History");
            System.out.println(blue + "6" + reset + ". Logout");
            System.out.println(blue + "7" + reset + ". Exit");
            int input = sc.nextInt();

        if(input ==1){
            accBalance(sc, currAccount);
        }
        if(input ==2){
            accDeposit(sc, currAccount);
        }
        if(input ==3){
            accWithdraw(sc, currAccount);
        }
        if(input ==4){
            accTransfer(sc, currAccount);
        }
        if(input ==5){
            acctransHist(sc, currAccount);
        }
        if(input ==6){
            saveUser();
        loadUser();
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println(green + "           Welcome to Bank Managment Project"+ reset);
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        System.out.println("");
        System.out.println( red + "1" + reset+ ". Login");
        System.out.println(red + "2" +reset + ". Signup");
        System.out.println(red+ "3" + reset +". Exit");
        int choice = sc.nextInt();
        if(choice ==3){
            System.exit(0);
            saveUser();
        }
        if(choice == 1){
           login(sc);
        }
        if(choice == 2){
            signUp(sc);
        }
        saveUser();
        }
        if(input ==7){
            System.exit(0);
            saveUser();
        }     
    }
    private static void accBalance(Scanner sc, Account account){
        String red = "\033[0;31m"; // Red color text
        String reset = "\033[0m";  // Reset color to default
        String green = "\u001B[32m"; // Green color text
        String blue = "\033[0;34m"; // Blue color text
    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    System.out.println(green + "          Bank Project - Account Balance"+ reset);
    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    System.out.println("");
    System.out.println("Account Balance: $" + account.getBalance());
    System.out.println("");
    System.out.println(green + "Press Enter to return to the main menu..." + reset);
    sc.nextLine(); 
    sc.nextLine();
    saveUser();
        accMenu(sc);
    }
    private static void accDeposit(Scanner sc, Account account){
        String red = "\033[0;31m"; // Red color text
        String reset = "\033[0m";  // Reset color to default
        String green = "\u001B[32m"; // Green color text
        String blue = "\033[0;34m"; // Blue color text

    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    System.out.println(green + "          Bank Project - Account Deposit"+ reset);
    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    System.out.println("");
        System.out.print("Enter the amount to deposit: $");
        int deposit = sc.nextInt();

        account.setBalance(account.getBalance() + deposit);

        System.out.println("Depositing $" + deposit + " to your account...");
        System.out.println("");
        System.out.println(blue + "Deposit successful!" + reset);
        System.out.println("New balance: $" + account.getBalance());
        System.out.println("");
        System.out.println(green + "Press Enter to return to the main menu..." + reset);
    sc.nextLine(); 
    sc.nextLine();
        accMenu(sc);
        saveUser();

    }
    private static void accWithdraw(Scanner sc, Account account){
        String red = "\033[0;31m"; // Red color text
        String reset = "\033[0m";  // Reset color to default
        String green = "\u001B[32m"; // Green color text
        String blue = "\033[0;34m"; // Blue color text
    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    System.out.println(green + "          Bank Project - Account Withdraw"+ reset);
    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    System.out.println("");
        System.out.print("Enter the amount to withdraw: $");
        int withdraw = sc.nextInt();

        account.setBalance(account.getBalance() - withdraw);
        System.out.println("Withdrawing $" + withdraw + " to your account...");
        System.out.println("");
        System.out.println(blue + "Withdraw successful!" + reset);
        System.out.println("New balance: $" + account.getBalance());
        System.out.println("");
        System.out.println(green + "Press Enter to return to the main menu..." + reset);
    sc.nextLine(); 
    sc.nextLine();
    saveUser();
        accMenu(sc); 
    }
    private static void accTransfer(Scanner sc,Account account){
        String red = "\033[0;31m"; // Red color text
        String reset = "\033[0m";  // Reset color to default
        String green = "\u001B[32m"; // Green color text
        String blue = "\033[0;34m"; // Blue color text

    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    System.out.println(green + "          Bank Project - Account Transfer"+ reset);
    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    System.out.println("");
    System.out.print("Enter the username to trasnfer to: ");
    int accountNum = sc.nextInt();
    if (!userDatabase.containsKey(accountNum)) {
        System.out.println(red + "Account not found. Please try again." + reset);
        return;
    }
    int changedBalance = account.getBalance();

    System.out.print("Enter the amount to transfer: $");
    int amount = sc.nextInt();
    Account targetAccount = userDatabase.get(changedBalance);
    if (account.getBalance() < amount) {
        System.out.println(red + "Insufficient balance. Transfer canceled." + reset);
        return;
    }
    account.setBalance(account.getBalance() - amount);
    targetAccount.setBalance(targetAccount.getBalance() + amount);
    saveUser();

    System.out.println(blue + "Transfer successful!" + reset);
    System.out.println("");
    System.out.println(blue + "Press Enter to return to the main menu..." + reset);
    sc.nextLine(); 
    sc.nextLine();
        accMenu(sc);
    }
    private static void acctransHist(Scanner sc){
        String red = "\033[0;31m"; // Red color text
        String reset = "\033[0m";  // Reset color to default
        String green = "\u001B[32m"; // Green color text
        List<String> history = account.getTransactionHistory();

    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    System.out.println(green + "  Bank Project - Account Transaction History"+ reset);
    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    System.out.println("");
    System.out.println("-----------------------------------------------------------");
    for(String transaction : history){
        System.out.println(transaction);
    }
    System.out.println("-----------------------------------------------------------");

    System.out.println(green + "Press Enter to return to the main menu..." + reset);
    sc.nextLine();
    sc.nextLine();
    accMenu(sc);
    }
}

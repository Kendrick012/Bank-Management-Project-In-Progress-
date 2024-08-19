import java.io.Serializable;

public class Account implements Serializable {
    private String fullName;
    private String passWord;
    private String email;
    private int balance;
    private List<String> transactionHistory; // List to store transaction history

    Account(String fullName,String passWord,String email,int balance){
        this.fullName = fullName;
        this.passWord = passWord;
        this.email = email;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }
    @Override
    public String toString() {
        return " Account{" +
                " Fullname ='" + fullName + '\'' +
                ", Password ='" + passWord + '\'' +
                ", Balance =" + balance +
                '}';
    }
    public void deposit(int amount){
        balance += amount;
        transactionHistory.add("Deposited: $" + amount);
    }
    public void withdraw(int amount){
        balance -= amount;
        transactionHistory.add("Withdrew $" + amount);
    }
public String getName(){
    return fullName;
}
public String getPassword(){
    return passWord;
}
public void setBalance(int newBalance){
    this.balance = newBalance;
}
public Integer getBalance(){
    return balance;
}
public List<String> getTransactionHistory(){
    return transactionHistory;
}

}

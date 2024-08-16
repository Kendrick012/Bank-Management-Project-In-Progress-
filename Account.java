import java.io.Serializable;

public class Account implements Serializable {
    private String fullName;
    private String passWord;
    private String email;
    private int balance;
    int add;
    int reduce;
    Account(String fullName,String passWord,String email,int balance){
        this.fullName = fullName;
        this.passWord = passWord;
        this.email = email;
        this.balance = balance;
    }
    @Override
    public String toString() {
        return " Account{" +
                " Fullname ='" + fullName + '\'' +
                ", Password ='" + passWord + '\'' +
                ", Balance =" + balance +
                '}';
    }
    void deposit(){
        balance += add;
    }
    void withdraw(){
        balance -= reduce;
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

}
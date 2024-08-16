# Banking-Management-Project
Main Features:

1 - User Authentication:
Login: Users can log in by entering their username and password. The system checks if the username exists in the database and whether the password matches.
Sign-Up: New users can create an account by providing their full name, username, password, email, and starting balance. The username and password are validated to meet specific criteria (e.g., username length and character requirements).

2 - Account Management:
View Account Balance: Users can check the current balance in their account.
Deposit Money: Users can deposit a specific amount into their account, which updates their balance.
Withdraw Money: Users can withdraw money from their account, provided they have sufficient funds.
Transfer Money: Users can transfer money to another account by specifying the recipient's username and the amount to be transferred.
View Transaction History: This functionality is set up to show users their transaction history (though the implementation is not fully detailed in the provided code).

3 - Data Persistence:
Saving and Loading User Data: The program saves user account data to a file (userDatabase.ser) and loads it upon starting, ensuring user data persists between sessions.

4 - Input Validation:
Username and Password Validation: The program ensures that usernames and passwords meet specific requirements (e.g., username length, presence of numbers, and restrictions on special characters).
Email Validation: Email addresses are validated to ensure they match a specific format.

Technical Aspects:
The program uses a Map<String, Account> to store user data, where the key is the username, and the value is the associated Account object.
It employs FileInputStream and FileOutputStream for serializing and deserializing user data, allowing the persistence of user accounts between sessions.
Basic input and output are handled using the Scanner class, and the program includes color-coded text output for better user experience.

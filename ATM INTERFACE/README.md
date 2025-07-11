# 🏦 Indian ATM Simulator (Java Console Application)

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![License](https://img.shields.io/badge/License-MIT-green)

A complete ATM simulator with PIN authentication, transaction history, and file persistence. Designed to demonstrate core Java concepts including OOP, file I/O, and collections.

## ✨ Features

### 💳 Account Management
- Secure PIN authentication (4-digit)
- Account balance tracking
- PIN change functionality
- Transaction history with timestamps
- File-based persistence (auto-save)

### 💰 Banking Operations
- Cash deposits (minimum ₹100)
- Cash withdrawals (multiples of ₹100)
- Balance inquiries
- Detailed transaction history

### 🛠 Technical Highlights
- Object-oriented design (BankAccount class)
- File I/O for account persistence
- Input validation and error handling
- Clean console UI with ASCII borders
- Sample accounts for demo purposes

### Login Screen
🏁 When the Program Starts:
```
Loading existing accounts...
Loaded account: 123456789
Loaded account: 987654321
Account loading complete. Total accounts loaded: 2

═══════════════════════════════════
      WELCOME TO INDIAN BANK ATM
═══════════════════════════════════

1. Login
2. Exit
Enter choice:
```
###👨‍💻 If the user chooses 1 (Login):
```
═══════════════════════════════════
            LOGIN
═══════════════════════════════════
Enter account number: 123456789
Enter PIN: 1234

✅ Login successful!
```

###📋 After successful login – Main Menu:
```
═══════════════════════════════════
            MAIN MENU
═══════════════════════════════════
1. Check Balance
2. Deposit Money
3. Withdraw Money
4. Transaction History
5. Change PIN
6. Logout
Enter choice:
```
Depending on the user’s choice:

###💰 Check Balance:
```
💰 Current Account Balance: ₹10,000.00
```

###💵 Deposit Money:
```
═══════════════════════════════════
            DEPOSIT
═══════════════════════════════════
Enter amount (minimum ₹100): 500

✅ Deposit successful. New balance: ₹10,500.00
```
###🏧 Withdraw Money:
```
═══════════════════════════════════
            WITHDRAW
═══════════════════════════════════
Enter amount (multiples of ₹100): 1000

✅ Withdrawal successful. New balance: ₹9,500.00
```
Or error messages if input is invalid:

```
Withdrawal less than ₹100:
```
```
❌ Error: Minimum withdrawal is ₹100
Not in multiple of ₹100:
```
```
❌ Error: Amount must be in multiples of ₹100
Insufficient funds:


❌ Error: Insufficient funds. Available balance: ₹500.00
```
###📜 Transaction History:
```
═══════════════════════════════════
      TRANSACTION HISTORY
═══════════════════════════════════
11-07-2025 10:35:21 - Account opened with initial balance: ₹10,000.00
11-07-2025 10:38:45 - Deposit: ₹500.00
11-07-2025 10:40:10 - Withdrawal: ₹1,000.00
```
###🔐 Change PIN:
```
═══════════════════════════════════
            CHANGE PIN
═══════════════════════════════════
Enter current PIN: 1234
Enter new PIN (4 digits): 5678

✅ PIN changed successfully
```
###🔚 Logout:
```
✅ Logged out successfully.
```
```
❌ If the user enters an invalid account or PIN:
❌ Invalid account number or PIN
```
###🚪 If the user chooses 2 (Exit):
```
🙏 Thank you for using our ATM. Goodbye!

Saving all account data...
All accounts saved.
```
## 🚀 Getting Started

### Prerequisites
- Java JDK 17 or later
- Command line/terminal access

### Installation
```bash
git clone https://github.com/yourusername/indian-atm-simulator.git
cd indian-atm-simulator
```
Run the Application 
```
javac IndianATM.java
java IndianATM
```


📂 Project Structure
```
indian-atm-simulator/
├── IndianATM.java          # Main application class
├── BankAccount.java        # Bank account logic
├── accounts/               # Directory for account files
│   ├── 123456789.txt       # Sample account 1
│   └── 987654321.txt       # Sample account 2
└── README.md               # This documentation
```
🔐 Sample Accounts
For testing purposes, two accounts are pre-configured:
```
Account Number	PIN	Initial Balance
123456789	1234	₹10,000.00
987654321	4321	₹5,000.00
```
---
🛠️ Technical Details
- Data Persistence
- Each account is saved to [account-number].txt
---
File format includes:
- Account number
- PIN (hashed in real applications)
- Current balance
- Complete transaction history
---
Key Classes
 - BankAccount
 - Handles all account operations
 - Manages transaction history
 - Implements file I/O
 - ATM
 - Handles user interaction
 - Manages account authentication
 - Provides menu navigation
---
📜 License
---
- This project is licensed under the MIT License - see the LICENSE file for details.
---
🤝 Contributing
---
- Contributions are welcome! Please follow these steps:
- Fork the repository
- Create your feature branch (git checkout -b feature/your-feature)
- Commit your changes (git commit -m 'Add some feature')
- Push to the branch (git push origin feature/your-feature)
- Open a Pull Request
---
🌟 Future Enhancements
---
- Add administrator mode
- Implement proper password hashing
- Add fund transfer between accounts
- Support for multiple currencies
- Graphical user interface (GUI)
---
🙏 Acknowledgements
---
- Java Documentation
- ASCII art inspiration
---

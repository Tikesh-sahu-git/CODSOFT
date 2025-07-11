# 💱 Currency Converter (Java Console Application)

A user-friendly console-based Java application that allows users to convert between multiple international currencies with real-time formatted output. Designed for learning purposes and practicing input validation, control structures, and modular design in Java.

## 📌 Features

- **Multi-currency Conversion**:
  - US Dollar (USD) $
  - Euro (EUR) €
  - British Pound (GBP) £
  - Japanese Yen (JPY) ¥
  - Indian Rupee (INR) ₹

- **User Experience**:
  - Beautifully formatted console UI with ASCII borders
  - Intuitive menu-driven interface
  - Option to repeat conversions or exit

- **Technical Features**:
  - Accurate 2-step conversion via INR as base currency
  - Comprehensive input validation
  - Clean, modular code with extensive comments
  - Formatted numerical output with proper symbols

## 💻 Tech Stack

- **Language**: Java 17+
- **Environment**: Console/Terminal
- **Key Libraries**:
  - `java.util.Scanner` for user input
  - `java.text.DecimalFormat` for numerical formatting

## 🚀 Getting Started

### Prerequisites
- JDK 17 or above installed
- Basic command line knowledge

### Installation & Execution

```bash
# Clone the repository
git clone https://github.com/yourusername/currency-converter-java.git
cd currency-converter-java

# Compile and run
javac CurrencyConverter.java
java CurrencyConverter

```

---
🧠 How It Works

Currency Selection:
User selects source and target currencies from numbered menu
Input validation ensures proper selection
Conversion Process:
Amount is first converted to INR (base currency)
INR amount is then converted to target currency
Fixed exchange rates are used (configurable)

```
╔══════════════════════════════╗
║   INDIAN CURRENCY CONVERTER   ║
╚══════════════════════════════╝

Available currencies:
1. US Dollar (USD) $
2. Euro (EUR) €
3. British Pound (GBP) £
4. Japanese Yen (JPY) ¥
5. Indian Rupee (INR) ₹
6. Exit

Select source currency (1-6): 1
Select target currency (1-5): 5
Enter amount to convert: 100

════════════ Conversion Details ════════════
Conversion Rate: 1 USD = 85.50 INR

════════════ Conversion Result ════════════
100.00 USD (US Dollar) = 8,550.00 INR (Indian Rupee)
$100.00 = ₹8,550.00
══════════════════════════════════════════
```
📂 Project Structure
```
currency-converter-java/
├── CurrencyConverter.java    # Main application code
├── README.md                # This documentation
└── LICENSE                  # MIT License file
```
---

🛡️ License
This project is licensed under the MIT License - see the LICENSE file for details.

🙌 Acknowledgements
Java Official Documentation

Community tutorials on currency conversion algorithms

ASCII art inspiration for console UI borders

🤝 Contributing
Contributions are welcome!
---


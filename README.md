# Scientific Calculator

## Project Overview
This project implements a Scientific Calculator using Java, featuring a graphical user interface (GUI) built with AWT/Swing. The calculator supports basic arithmetic operations as well as scientific functions, and it incorporates event handling and exception handling to ensure a smooth user experience.

## Features
- User-friendly interface with buttons for numbers (0-9), arithmetic operations (+, −, ×, ÷), and scientific functions (sin, cos, tan, log, √, x², π).
- Real-time display of input and output in a text field.
- Exception handling for invalid operations, including:
  - Division by zero
  - Invalid or empty input
  - General unexpected errors

## Project Structure
```
ScientificCalculator
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── calculator
│   │   │               ├── Main.java
│   │   │               ├── CalculatorGUI.java
│   │   │               ├── CalculatorEngine.java
│   │   │               └── operations
│   │   │                   ├── ScientificFunctions.java
│   │   │                   └── OperationUtils.java
│   │   └── resources
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── calculator
│                       └── CalculatorEngineTest.java
├── pom.xml
├── .gitignore
└── README.md
```

## Setup Instructions
1. Clone the repository to your local machine.
2. Open the project in your preferred Java IDE (e.g., IntelliJ, Eclipse, or NetBeans).
3. Ensure you have Maven installed to manage dependencies.
4. Build the project using Maven commands:
   ```
   mvn clean install
   ```
5. Run the application by executing the `Main.java` file.

## Usage
- Launch the application to open the calculator GUI.
- Click on the buttons to input numbers and operations.
- Press the "=" button to calculate the result.
- Use the "AC" button to clear the display.

## Technologies Used
- Java
- AWT/Swing for GUI
- Maven for project management

## License
This project is licensed under the MIT License. See the LICENSE file for more details.
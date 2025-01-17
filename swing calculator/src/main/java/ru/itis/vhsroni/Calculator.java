package ru.itis.vhsroni;

import ru.itis.vhsroni.exception.CalculatorInvocationException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class Calculator extends JFrame {

    private JPanel contentPane;
    private JPanel keyPanel;
    private JTextField numberField;
    private JButton[] numberButtons = new JButton[10];
    private JButton clearBtn, resultBtn, plusBtn, minusBtn, multiplyBtn, divideBtn;
    private JButton sqrtBtn, powerBtn, negateBtn;

    private static final Color BACKGROUND_COLOR = new Color(237, 157, 214);
    private static final Color OPERATION_BUTTON_COLOR = new Color(214, 64, 119);
    private static final Color NUMBERS_BUTTON_COLOR = new Color(201, 75, 155);

    private double memory = 0;
    private Operation operation = Operation.NONE;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Calculator frame = new Calculator();
                frame.setVisible(true);
            } catch (Exception e) {
                throw new CalculatorInvocationException(e);
            }
        });
    }

    public Calculator() {
        setTitle("My best Calculator <3");

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icon.png")));
        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(300, 100, 400, 600);

        contentPane = new JPanel();
        contentPane.setBackground(BACKGROUND_COLOR);
        contentPane.setBorder(new EmptyBorder(15, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 10));

        numberField = new JTextField();
        numberField.setEditable(false);
        numberField.setHorizontalAlignment(SwingConstants.RIGHT);
        numberField.setFont(new Font("Arial", Font.BOLD, 24));
        numberField.setText("");
        contentPane.add(numberField, BorderLayout.NORTH);

        keyPanel = new JPanel();
        keyPanel.setLayout(new GridLayout(5, 4, 5, 5));
        keyPanel.setBackground(BACKGROUND_COLOR);
        contentPane.add(keyPanel, BorderLayout.CENTER);

        for (int i = 1; i <= 9; i++) {
            final int number = i;
            numberButtons[i] = createCustomNumberButton(String.valueOf(i), () -> addNumberToScreen(number));
        }
        numberButtons[0] = createCustomNumberButton("0", () -> addNumberToScreen(0));

        negateBtn = createCustomOperationButton("±", this::negateAction);
        sqrtBtn = createCustomOperationButton("√", this::sqrtAction);
        powerBtn = createCustomOperationButton("^", () -> operationButtonAction(Operation.POWER));
        divideBtn = createCustomOperationButton("/", () -> operationButtonAction(Operation.DIVIDE));
        multiplyBtn = createCustomOperationButton("*", () -> operationButtonAction(Operation.MULTIPLY));
        minusBtn = createCustomOperationButton("-", () -> operationButtonAction(Operation.MINUS));
        plusBtn = createCustomOperationButton("+", () -> operationButtonAction(Operation.PLUS));
        clearBtn = createCustomOperationButton("C", this::clearAction);
        resultBtn = createCustomOperationButton("=", this::resultAction);

        addElementsToKeyPanel();
    }

    private JButton createCustomOperationButton(String text, Runnable action) {
        JButton button = createCustomButton(text, action);
        button.setBackground(OPERATION_BUTTON_COLOR);
        return button;
    }

    private JButton createCustomNumberButton(String text, Runnable action) {
        JButton button = createCustomButton(text, action);
        button.setBackground(NUMBERS_BUTTON_COLOR);
        return button;
    }

    private JButton createCustomButton(String text, Runnable action) {
        MyCustomRoundButton button = new MyCustomRoundButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.addActionListener(event -> action.run());
        button.setPreferredSize(new Dimension(60, 60));
        return button;
    }

    private void addNumberToScreen(int number) {
        String currentText = numberField.getText();
        if (currentText.equals("0")) {
            numberField.setText(String.valueOf(number));
        } else {
            numberField.setText(currentText + number);
        }
    }

    private void operationButtonAction(Operation op) {
        try {
            memory = getNumberFromScreen();
            operation = op;
            numberField.setText("");
        } catch (NumberFormatException e) {
            showError("Invalid input");
        }
    }

    private void resultAction() {
        try {
            double currentNumber = getNumberFromScreen();
            switch (operation) {
                case PLUS -> memory += currentNumber;
                case MINUS -> memory -= currentNumber;
                case MULTIPLY -> memory *= currentNumber;
                case POWER -> memory = Math.pow(memory, currentNumber);
                case DIVIDE -> {
                    if (currentNumber == 0) throw new ArithmeticException("Division by zero");
                    memory /= currentNumber;
                }
                default -> {}
            }
            numberField.setText(formatResult(memory));
            operation = Operation.NONE;
        } catch (ArithmeticException e) {
            showError(e.getMessage());
        } catch (NumberFormatException e) {
            showError("Invalid input");
        }
    }

    private void sqrtAction() {
        try {
            double currentNumber = getNumberFromScreen();
            if (currentNumber < 0) throw new ArithmeticException("Cannot take square root of negative number");
            numberField.setText(formatResult(Math.sqrt(currentNumber)));
        } catch (NumberFormatException e) {
            showError("Invalid input");
        } catch (ArithmeticException e) {
            showError(e.getMessage());
        }
    }

    private void negateAction() {
        try {
            double currentNumber = getNumberFromScreen();
            numberField.setText(formatResult(-currentNumber));
        } catch (NumberFormatException e) {
            showError("Invalid input");
        } catch (ArithmeticException e) {
            showError(e.getMessage());
        }
    }

    private void clearAction() {
        memory = 0;
        operation = Operation.NONE;
        numberField.setText("");
    }


    private double getNumberFromScreen() {
        return Double.parseDouble(numberField.getText());
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.valueOf((long) result);
        } else {
            return String.valueOf(result);
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void addElementsToKeyPanel() {

        keyPanel.add(plusBtn);
        keyPanel.add(minusBtn);
        keyPanel.add(multiplyBtn);
        keyPanel.add(clearBtn);

        keyPanel.add(numberButtons[1]);
        keyPanel.add(numberButtons[2]);
        keyPanel.add(numberButtons[3]);
        keyPanel.add(divideBtn);

        keyPanel.add(numberButtons[4]);
        keyPanel.add(numberButtons[5]);
        keyPanel.add(numberButtons[6]);
        keyPanel.add(sqrtBtn);

        keyPanel.add(numberButtons[7]);
        keyPanel.add(numberButtons[8]);
        keyPanel.add(numberButtons[9]);
        keyPanel.add(powerBtn);

        keyPanel.add(negateBtn);
        keyPanel.add(numberButtons[0]);
        keyPanel.add(resultBtn);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[8];
    JButton addButton, subtractButton, multiplyButton, divideButton;
    JButton decimalButton, equalsButton, delButton, clearButton;
    JPanel panel;

    Font myFont = new Font("Courier New", Font.BOLD, 24);

    double num1 = 0, num2 = 0, result = 0;
    char operator;
    boolean operatorPressed = false;

    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(240, 240, 255)); 

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);
        textfield.setBackground(Color.WHITE);
        textfield.setForeground(Color.BLACK);
        frame.add(textfield);

        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        decimalButton = new JButton(".");
        equalsButton = new JButton("=");
        delButton = new JButton("Delete");
        clearButton = new JButton("Clear");

        functionButtons[0] = addButton;
        functionButtons[1] = subtractButton;
        functionButtons[2] = multiplyButton;
        functionButtons[3] = divideButton;
        functionButtons[4] = decimalButton;
        functionButtons[5] = equalsButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clearButton;

        for (int i = 0; i < 8; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        delButton.setBackground(Color.RED);
        delButton.setForeground(Color.WHITE);

        clearButton.setBackground(Color.GRAY);
        clearButton.setForeground(Color.WHITE);

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subtractButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(multiplyButton);
        panel.add(decimalButton);
        panel.add(numberButtons[0]);
        panel.add(equalsButton);
        panel.add(divideButton);

        frame.add(panel);

        delButton.setBounds(50, 420, 145, 50);
        clearButton.setBounds(205, 420, 145, 50);
        frame.add(delButton);
        frame.add(clearButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText() + i);
            }
        }

        if (e.getSource() == decimalButton) {
            if (!textfield.getText().contains(".")) {
                textfield.setText(textfield.getText() + ".");
            }
        }

        if (e.getSource() == addButton) {
            setOperator('+');
        }

        if (e.getSource() == subtractButton) {
            
            String current = textfield.getText();
            if (current.isEmpty() || current.equals("-")) {
                textfield.setText("-");
            } else if (!operatorPressed) {
                setOperator('-');
            }
        }

        if (e.getSource() == multiplyButton) {
            setOperator('*');
        }

        if (e.getSource() == divideButton) {
            setOperator('/');
        }

        if (e.getSource() == equalsButton) {
            String input = textfield.getText();
            int operatorIndex = findOperatorIndex(input);
            if (operatorIndex != -1) {
                try {
                    String first = input.substring(0, operatorIndex);
                    String second = input.substring(operatorIndex + 1);
                    num1 = Double.parseDouble(first);
                    num2 = Double.parseDouble(second);

                    switch (operator) {
                        case '+': result = num1 + num2; break;
                        case '-': result = num1 - num2; break;
                        case '*': result = num1 * num2; break;
                        case '/': result = (num2 != 0) ? num1 / num2 : 0; break;
                    }
                    textfield.setText(String.valueOf(result));
                    operatorPressed = false;
                } catch (Exception ex) {
                    textfield.setText("Error");
                }
            }
        }

        if (e.getSource() == clearButton) {
            textfield.setText("");
            operatorPressed = false;
        }

        if (e.getSource() == delButton) {
            String str = textfield.getText();
            if (!str.isEmpty()) {
                textfield.setText(str.substring(0, str.length() - 1));
            }
        }
    }

   
    private void setOperator(char op) {
        if (!operatorPressed && !textfield.getText().isEmpty()) {
            textfield.setText(textfield.getText() + op);
            operator = op;
            operatorPressed = true;
        }
    }

    
    private int findOperatorIndex(String text) {
        for (int i = 1; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                operator = ch;
                return i;
            }
        }
        return -1;
    }
}

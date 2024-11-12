import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class ExpressionCheckerGUI extends JFrame {

    private JTextField textField;

    public ExpressionCheckerGUI() {
        setTitle("Expression Checker");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Textfield for input expression
        textField = new JTextField();
        add(textField, BorderLayout.NORTH);

        // Button for checking expression
        JButton checkButton = new JButton("Check");
        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkExpression();
            }
        });
        add(checkButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private void checkExpression() {
        String expression = textField.getText();

        if (isBalanced(expression)) {
            JOptionPane.showMessageDialog(this, "The Expression is balanced");
        } else {
            int position = findUnbalancedPosition(expression);
            JOptionPane.showMessageDialog(this, "The expression is unbalanced at character " + position);
        }
    }

    private boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();

        for (char c : expression.toCharArray()) {
            if (c == '[' || c == '{' || c == '<') {
                stack.push(c);
            } else if (c == ']' || c == '}' || c == '>') {
                if (stack.isEmpty()) {
                    return false; // Closing bracket with no corresponding opening bracket
                }

                char openBracket = stack.pop();
                if (!((openBracket == '[' && c == ']') ||
                        (openBracket == '{' && c == '}') ||
                        (openBracket == '<' && c == '>'))) {
                    return false; // Mismatched brackets
                }
            }
        }

        return stack.isEmpty(); // Expression is balanced if the stack is empty at the end
    }

    private int findUnbalancedPosition(String expression) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '[' || c == '{' || c == '<') {
                stack.push(c);
            } else if (c == ']' || c == '}' || c == '>') {
                if (stack.isEmpty()) {
                    return i + 1; // 1-indexed position of unbalanced character
                }

                char openBracket = stack.pop();
                if (!((openBracket == '[' && c == ']') ||
                        (openBracket == '{' && c == '}') ||
                        (openBracket == '<' && c == '>'))) {
                    return i + 1; // 1-indexed position of unbalanced character
                }
            }
        }

        if (!stack.isEmpty()) {
            return expression.length() + 1; // Unbalanced at the end of the expression
        }

        return -1; // Should not reach here if the expression is balanced
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ExpressionCheckerGUI().setVisible(true);
            }
        });
    }
}

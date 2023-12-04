import java.util.Stack;

public class Notation {
    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
        Stack<Character> operatorStack = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {
            char currentC = infix.charAt(i);

            if (Character.isDigit(currentC)) {
                postfix.append(currentC);
            } else if (currentC == '(') {
                operatorStack.push(currentC);
            } else if (currentC == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfix.append(operatorStack.pop());
                }
                if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                    operatorStack.pop(); // Pop the opening parenthesis
                } else {
                    throw new InvalidNotationFormatException("Invalid infix expression");
                }
            } else if (isOperator(currentC)) {
                while (!operatorStack.isEmpty() && hasHigherPrecedence(operatorStack.peek(), currentC)) {
                    postfix.append(operatorStack.pop());
                }
                operatorStack.push(currentC);
            } else if (currentC != ' ') {
                throw new InvalidNotationFormatException("Invalid character in infix expression");
            }
        }

        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek() == '(') {
                throw new InvalidNotationFormatException("Mismatched parentheses in infix expression");
            }
            postfix.append(operatorStack.pop());
        }

        return postfix.toString();
    }

    public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
        Stack<String> operandStack = new Stack<>();

        for (int i = 0; i < postfix.length(); i++) {
            char currentChar = postfix.charAt(i);

            if (Character.isDigit(currentChar)) {
                operandStack.push(String.valueOf(currentChar));
            } else if (isOperator(currentChar)) {
                if (operandStack.size() < 2) {
                    throw new InvalidNotationFormatException("Invalid postfix expression");
                }
                String operand2 = operandStack.pop();
                String operand1 = operandStack.pop();
                String result = "(" + operand1 + currentChar + operand2 + ")";
                operandStack.push(result);
            } else if (currentChar != ' ') {
                throw new InvalidNotationFormatException("Invalid character in postfix expression");
            }
        }

        if (operandStack.size() != 1) {
            throw new InvalidNotationFormatException("Invalid postfix expression");
        }

        return operandStack.pop();
    }

    public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
        Stack<Double> operandStack = new Stack<>();

        for (int i = 0; i < postfixExpr.length(); i++) {
            char currentChar = postfixExpr.charAt(i);

            if (Character.isDigit(currentChar)) {
                operandStack.push(Double.parseDouble(String.valueOf(currentChar)));
            } else if (isOperator(currentChar)) {
                if (operandStack.size() < 2) {
                    throw new InvalidNotationFormatException("Invalid postfix expression");
                }
                double operand2 = operandStack.pop();
                double operand1 = operandStack.pop();
                double result = performOperation(operand1, operand2, currentChar);
                operandStack.push(result);
            } else if (currentChar != ' ') {
                throw new InvalidNotationFormatException("Invalid character in postfix expression");
            }
        }

        if (operandStack.size() != 1) {
            throw new InvalidNotationFormatException("Invalid postfix expression");
        }

        return operandStack.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean hasHigherPrecedence(char op1, char op2) {
        return (op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-');
    }

    private static double performOperation(double operand1, double operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}

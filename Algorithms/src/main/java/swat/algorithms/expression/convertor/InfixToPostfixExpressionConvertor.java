package swat.algorithms.expression.convertor;

import org.apache.commons.lang3.StringUtils;
import swat.algorithms.expression.Expression;

import java.util.Stack;

/**
 * @author Sujal
 */
public class InfixToPostfixExpressionConvertor extends AbstractExpressionConvertor {

	public InfixToPostfixExpressionConvertor(Expression expression) {
		super(expression);
	}

	@Override
	public Expression convert() {
		String expressionStr = expression.getExpression();

		if(StringUtils.isBlank(expressionStr)) {
			return new Expression("");
		}

		StringBuilder convertedExpression = new StringBuilder();
		Stack<Character> operatorStack = new Stack<Character>();

		for(char currentChar : expressionStr.toCharArray()) {
			if(isOperand(currentChar)) {
				convertedExpression.append(currentChar);
			} else if(isOperator(currentChar)) {
				if(operatorStack.isEmpty()
						|| '(' == operatorStack.peek()
						|| isHighPriority(currentChar, operatorStack.peek())) {
					operatorStack.push(currentChar);
				} else {
					while(! operatorStack.isEmpty()
							&& '(' != operatorStack.peek()
							&& isLowOrEqualPriority(currentChar, operatorStack.peek())) {
						convertedExpression.append(operatorStack.pop());
					}
					operatorStack.push(currentChar);
				}
			} else if('(' == currentChar) {
				operatorStack.push(currentChar);
			} else if(')' == currentChar) {
				while(! operatorStack.isEmpty() && operatorStack.peek() != '(') {
					convertedExpression.append(operatorStack.pop());
				}
				operatorStack.pop();
			}
		}

		while(! operatorStack.isEmpty()) {
			convertedExpression.append(operatorStack.pop());
		}

		return new Expression(convertedExpression.toString());
	}

	private boolean isLowOrEqualPriority(char char1, char char2) {
		return ! isHighPriority(char1, char2);
	}

	private boolean isHighPriority(char char1, Character char2) {
		if(char1 == '/' && char2 != '/') {
			return true;
		}

		if(char1 == '*' && (char2=='+' || char2=='-')) {
			return true;
		}

		if(char1=='+' && char2=='-') {
			return true;
		}

		return false;
	}

	private boolean isOperator(char c) {
		return c=='/' || c=='*' || c=='+' || c=='-';
	}

	private boolean isOperand(char c) {
		return !isOperator(c) && c!='(' && c!=')';
	}
}

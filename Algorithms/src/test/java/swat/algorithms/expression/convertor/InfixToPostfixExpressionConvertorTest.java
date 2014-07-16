package swat.algorithms.expression.convertor;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import swat.algorithms.expression.Expression;

/**
 * @author Sujal
 */
public class InfixToPostfixExpressionConvertorTest {

	@DataProvider(name = "infixToPostfixDataProvider")
	public static Object[][] infixToPostfixDataProvider() {
		return new Object[][] {
				{"", ""},
				{"a", "a"},
				{"(a)", "a"},
				{"a+b", "ab+"},
				{"a+b+c", "ab+c+"},
				{"a+b*c+d", "abc*+d+"},
				{"a+b*(c+d)", "abcd+*+"},
				{"(a+b)*c+d", "ab+c*d+"},
				{"a+(b*c)+d", "abc*+d+"}
		};
	}

	@Test(dataProvider = "infixToPostfixDataProvider")
	public void testConvertor(String infixExpressionStr, String expectedPostfixExpressionStr) {
		Expression expression = new Expression(infixExpressionStr);
		AbstractExpressionConvertor expressionConvertor = new InfixToPostfixExpressionConvertor(expression);

		Expression computedPostfixExpression = expressionConvertor.convert();
		assert expectedPostfixExpressionStr.equals(computedPostfixExpression.getExpression());
	}
}

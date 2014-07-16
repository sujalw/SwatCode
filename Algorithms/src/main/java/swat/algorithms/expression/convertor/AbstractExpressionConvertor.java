package swat.algorithms.expression.convertor;

import swat.algorithms.expression.Expression;

/**
 * @author Sujal
 */
public abstract class AbstractExpressionConvertor {

	Expression expression;

	public AbstractExpressionConvertor(Expression expression) {
		this.expression = expression;
	}

	public abstract Expression convert();
}

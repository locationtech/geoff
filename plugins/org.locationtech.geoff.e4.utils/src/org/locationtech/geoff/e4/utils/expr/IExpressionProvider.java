package org.locationtech.geoff.e4.utils.expr;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.expressions.Expression;
import org.eclipse.core.internal.expressions.WithExpression;

@SuppressWarnings("restriction")
public interface IExpressionProvider {

	default WithExprBuilder with(String varName) {
		return WithExprBuilder.with(varName);
	}

	public static abstract class ExprBuilder {

		public abstract Expression get();
	}

	public static abstract class CompositeExprBuilder extends ExprBuilder {

		protected List<ExprBuilder> children = new ArrayList<>();

		public CompositeExprBuilder child(ExprBuilder childBuilder) {
			children.add(childBuilder);
			return this;
		}
	}

	public static class WithExprBuilder extends CompositeExprBuilder {

		private WithExpression expr;

		private WithExprBuilder(String varName) {
			expr = new WithExpression(varName);
		}

		public static WithExprBuilder with(String varName) {
			WithExprBuilder withExprBuilder = new WithExprBuilder(varName);
			return withExprBuilder;
		}

		@Override
		public WithExpression get() {
			children.forEach((eb) -> expr.add(eb.get()));
			return expr;
		}
	}
}
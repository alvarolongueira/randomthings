package com.github.alvarolongueira.randomthings.grouper;

import org.immutables.value.Value;

@Value.Immutable
public abstract class DistributionLine<S extends DistributionLineSelector> {

	public static class Builder<S extends DistributionLineSelector> extends ImmutableDistributionLine.Builder<S> {
	}

	public static <S extends DistributionLineSelector> Builder<S> builder(DistributionTarget target, S selector) {
		Builder<S> builder = new Builder<S>();
		builder.target(target);
		builder.selector(selector);

		return builder;
	}

	public abstract S getSelector();

	public abstract DistributionTarget getTarget();

	public DistributionLine.Builder<S> modify() {
		return new Builder<S>().from(this);
	}
}
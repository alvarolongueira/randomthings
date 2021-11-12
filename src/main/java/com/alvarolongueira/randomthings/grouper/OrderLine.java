package com.alvarolongueira.randomthings.grouper;

import org.immutables.value.Value;

@Value.Immutable
public abstract class OrderLine<S extends OrderLineSelector> {

	public static class Builder<S extends OrderLineSelector>
			extends ImmutableOrderLine.Builder<S> {

	}

	public static <S extends OrderLineSelector> Builder<S> builder(OrderTarget target, S selector) {
		Builder<S> builder = new Builder<S>();
		builder.target(target);
		builder.selector(selector);

		return builder;
	}

	public abstract long getReference();

	public abstract OrderType<S> orderType();

	public abstract OrderTarget getTarget();

	public abstract S getSelector();

	public Builder<S> modify() {
		return new Builder<S>().from(this);
	}

}

package com.alvarolongueira.randomthings.grouper;

import org.immutables.value.Value;

@Value.Immutable(builder = false, copy = false, prehash = true)
public abstract class OrderLineGrouperKey<T, S extends OrderLineSelector> extends LineGrouperKey<T, S> {

	protected static <T, S extends OrderLineSelector> OrderLineGrouperKey<T, S> of(T target, S selector) {
		return ImmutableOrderLineGrouperKey.of(target, selector);
	}

	@Override
	@Value.Parameter
	public abstract T getTarget();

	@Override
	@Value.Parameter
	public abstract S getSelector();

	public OrderLineGrouperKey<T, S> getKeyWithAllEm() {
		return this;
	}

	public OrderLineGrouperKey<T, S> getKeyWithoutEm() {
		return this;
	}

}
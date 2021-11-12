package com.alvarolongueira.randomthings.grouper;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class DistributionLineGrouperKey<S extends DistributionLineSelector> extends LineGrouperKey<DistributionTarget, S> {

	public static <S extends DistributionLineSelector> DistributionLineGrouperKey<S> of(DistributionTarget target, S selector) {
		return ImmutableDistributionLineGrouperKey.of(target, selector);
	}

	@Override
	@Value.Parameter
	public abstract DistributionTarget getTarget();

	@Override
	@Value.Parameter
	public abstract S getSelector();

}
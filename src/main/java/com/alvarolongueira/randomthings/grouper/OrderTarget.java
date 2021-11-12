package com.alvarolongueira.randomthings.grouper;

import java.util.Optional;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class OrderTarget {

	public static OrderTarget of(DistributionTarget distributionTarget) {
		return ImmutableOrderTarget.of(distributionTarget, Optional.empty());
	}

	public static OrderTarget of(DistributionTarget distributionTarget, Integer billingLocation) {
		return ImmutableOrderTarget.of(distributionTarget, Optional.of(billingLocation));
	}

	@Value.Parameter
	public abstract DistributionTarget getDistributionTarget();

	@Value.Parameter
	public abstract Optional<Integer> getBillingLocation();

}
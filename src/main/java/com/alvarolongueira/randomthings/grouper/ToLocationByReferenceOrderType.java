package com.alvarolongueira.randomthings.grouper;

import org.immutables.value.Value;

@Value.Immutable(singleton = true, builder = false, intern = true)
public abstract class ToLocationByReferenceOrderType implements OrderType<ByReferenceOrderLineSelector> {
	
	public static ToLocationByReferenceOrderType of() {
		return ImmutableToLocationByReferenceOrderType.of();
	}
	
	@Override
	public String toString() {
		return "TO_LOCATION_BY_REFERENCE";
	}

	@Override
	public OrderLineType orderLineType() {
		return OrderLineType.TO_LOCATION_BY_REFERENCE;
	}
	
}

package com.github.alvarolongueira.randomthings.grouper;

import java.util.Optional;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class ByCommercialGroupOrderLineSelector implements OrderLineSelector {

	public static ByCommercialGroupOrderLineSelector of(Optional<Integer> commercialGroupCode, Optional<Integer> location) {
		return ImmutableByCommercialGroupOrderLineSelector.of(commercialGroupCode, location);
	}

	@Value.Parameter
	public abstract Optional<Integer> getCommercialGroup();

	@Value.Parameter
	public abstract Optional<Integer> getLocation();

	@Override
	public boolean equalsType(OrderLineSelector selector) {
		if (selector instanceof ByCommercialGroupOrderLineSelector) {
			return true;
		}
		return false;
	}
	
	@Override
	public OrderLineType getType() {
		return OrderLineType.TO_LOCATION_BY_COMMERCIAL_GROUP;
	}
}

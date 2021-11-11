package com.github.alvarolongueira.randomthings.grouper;

import java.util.Optional;
import java.util.Set;

import org.immutables.value.Value;

@Value.Immutable
public abstract class ByReferenceOrderLineSelector implements OrderLineSelector {
	
	public static class Builder extends ImmutableByReferenceOrderLineSelector.Builder {
		
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public abstract String getGarmentRef();

	public abstract Set<Integer> getGoodsStatusOriginal();
	
	public abstract Optional<Integer> getUnitsPerLot();
	
	@Override
	public boolean equalsType(OrderLineSelector selector) {
		return selector.getType().equals(this.getType());
	}
	
	@Override
	public OrderLineType getType() {
		return OrderLineType.TO_LOCATION_BY_REFERENCE;
	}

	public Builder modify() {
		return builder().from(this);
	}
	
}

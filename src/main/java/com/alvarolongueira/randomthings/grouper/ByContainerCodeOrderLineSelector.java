package com.alvarolongueira.randomthings.grouper;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class ByContainerCodeOrderLineSelector implements OrderLineSelector {

	public static ByContainerCodeOrderLineSelector of(String containerCode) {
		return ImmutableByContainerCodeOrderLineSelector.of(containerCode);
	}

	@Value.Parameter
	public abstract String getContainerCode();

	@Override
	public boolean equalsType(OrderLineSelector selector) {
		return selector.getType().equals(this.getType());
	}
	
	@Override
	public OrderLineType getType() {
		return OrderLineType.TO_LOCATION_BY_BARCODE;
	}
	
	@Override
	public String toString() {
		return "ByContainerCodeOrderLineSelector{" + getContainerCode() + "}";
	}
}

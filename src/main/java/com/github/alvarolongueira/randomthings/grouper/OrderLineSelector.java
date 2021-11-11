package com.github.alvarolongueira.randomthings.grouper;

public interface OrderLineSelector {

	boolean equalsType(OrderLineSelector selector);
	
	OrderLineType getType();
	
}

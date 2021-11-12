package com.alvarolongueira.randomthings.grouper;

public interface OrderType<S extends OrderLineSelector> {

	OrderLineType orderLineType();

}

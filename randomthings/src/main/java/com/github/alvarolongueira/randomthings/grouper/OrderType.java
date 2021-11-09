package com.github.alvarolongueira.randomthings.grouper;

public interface OrderType<S extends OrderLineSelector> {

	OrderLineType orderLineType();

}

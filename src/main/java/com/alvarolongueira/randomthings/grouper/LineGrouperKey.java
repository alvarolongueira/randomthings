package com.alvarolongueira.randomthings.grouper;

public abstract class LineGrouperKey<T, S> {

	public abstract T getTarget();

	public abstract S getSelector();

}
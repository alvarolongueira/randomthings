package com.github.alvarolongueira.randomthings.genericfilters;

import java.util.Set;

import org.immutables.value.Value;

@Value.Immutable
public abstract class RuleFilter<T> {

	public static class Builder<T> extends ImmutableRuleFilter.Builder<T> {

	}

	public static <T> Builder<T> builder(Iterable<T> values) {
		return new Builder<T>().addAllValues(values);
	}

	public abstract Set<T> values();

	public abstract RuleFilterType<T> type();

	public T first() {
		return this.values().stream().findFirst().get();
	}

	public RuleFilter<T> add(T value) {
		return new RuleFilter.Builder<T>().from(this).addValues(value).build();
	}

	public boolean match(T other) {
		return this.values().stream().anyMatch(value -> value.equals(other));
	}

	@Override
	public String toString() {
		return "RuleFilter[" + type() + ", values=" + values() + "]";
	}
	
}

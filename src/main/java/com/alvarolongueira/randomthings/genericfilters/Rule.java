package com.alvarolongueira.randomthings.genericfilters;

import java.util.Optional;
import java.util.Set;

public abstract class Rule {

	public abstract Set<RuleFilter<?>> filters();

	public abstract Optional<Long> getId();

	public abstract int getSequence();
	
	public abstract Rule withId(long id);
	
	public abstract Rule withSequence(int id);

	public abstract Rule updateSequence(int sequence);
	
	public boolean match(Set<RuleFilter<?>> values){
		return RuleMatcher.match(this, values);
    }
	
	public <T> Optional<T> getFirst(Optional<RuleFilter<T>> filter) {
		return filter.isPresent() ? Optional.of(filter.get().first()) : Optional.empty();
	}
	
	@SuppressWarnings("unchecked")
	public <T> Optional<RuleFilter<T>> getFilter(RuleFilterType<T> type) {
		RuleFilter<T> result = null;

		for (RuleFilter<?> current : this.filters()) {
			if (current.type().equals(type)) {
				result = (RuleFilter<T>) current;
			}
		}

		return Optional.ofNullable(result);
	}
	
	public abstract <T> Rule add(RuleFilter<T> filter);
	
	public abstract <T> Rule remove(RuleFilter<T> filter);

}

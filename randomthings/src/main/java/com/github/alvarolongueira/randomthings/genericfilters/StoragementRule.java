package com.github.alvarolongueira.randomthings.genericfilters;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.immutables.value.Value;

@Value.Immutable
public abstract class StoragementRule extends Rule implements Serializable {

	private static final long serialVersionUID = 291707691725259018L;

	public static class Builder extends ImmutableStoragementRule.Builder {

	}

	public static Builder builder() {
		return new Builder();
	}

	public abstract Optional<StoragementRuleType> getType();

	@Override
	public StoragementRule withId(long id) {
		return this.modify().id(Optional.of(id)).build();
	}

	@Override
	public StoragementRule withSequence(int sequence) {
		return this.modify().sequence(sequence).build();
	}

	public Builder modify() {
		return new StoragementRule.Builder().from(this);
	}

	@Override
	public StoragementRule updateSequence(int sequence) {
		return modify().sequence(sequence).build();
	}

	public Optional<Boolean> getIsRfid() {
		return getFirst(getFilter(RuleFilterType.RFID));
	};

	public Optional<Boolean> getIsProblematic() {
		return getFirst(getFilter(RuleFilterType.PROBLEMATIC_RFID));
	};

	public Optional<Integer> getUnitsPerLot() {
		return getFirst(getFilter(RuleFilterType.UNITS_PER_LOT));
	};

	public Optional<Integer> getMaxUnitsByReference() {
		return getFirst(getFilter(RuleFilterType.MAX_UNITS));
	};

	public Optional<Integer> getMaxPackagesByReference() {
		return getFirst(getFilter(RuleFilterType.MAX_CONTAINERS));
	};

	public Optional<Boolean> getIsAssortment() {
		return getFirst(getFilter(RuleFilterType.ASSORTMENT));
	}

	@Override
	public <T> StoragementRule add(RuleFilter<T> filter) {
		Optional<RuleFilter<T>> oldFilter = this.getFilter(filter.type());
		if (oldFilter.isPresent()) {
			this.remove(oldFilter.get());
		}
		return this.modify().addFilters(filter).build();
	}
	
	@Override
	public <T> StoragementRule remove(RuleFilter<T> filter) {
		Optional<RuleFilter<T>> found = this.getFilter(filter.type());
		if (found.isPresent()) {
			Set<RuleFilter<?>> newFilters = new HashSet<>();
			newFilters.addAll(this.filters());
			newFilters.remove(found.get());
			
			return this.modify().filters(newFilters).build();
		}
		
		return this;
	}
	
	public boolean hasMaxFilters() {
		return this.getFilter(RuleFilterType.MAX_UNITS).isPresent() || this.getFilter(RuleFilterType.MAX_CONTAINERS).isPresent();
	}
}

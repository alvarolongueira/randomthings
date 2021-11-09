package com.github.alvarolongueira.randomthings.grouper;

import java.util.Optional;

@SuppressWarnings("rawtypes")
public class DistributionLineGrouper extends LineGrouperWithPriority<DistributionLineGrouperKey, DistributionLine> {

	public static DistributionLineGrouper of() {
		return new DistributionLineGrouper();
	}

	private DistributionLineGrouper() {
		super();
	}

	@Override
	protected boolean sameType(DistributionLineGrouperKey key1, DistributionLineGrouperKey key2) {
		return key1.getSelector().equalsType(key2.getSelector());
	}

	@Override
	protected boolean isByContainerCode(DistributionLineGrouperKey key) {
		return key.getSelector() instanceof ByContainerCodeOrderLineSelector;
	}

	@Override
	protected boolean isByCommercialGroup(DistributionLineGrouperKey key) {
		return key.getSelector() instanceof ByCommercialGroupOrderLineSelector;
	}
	
	@Override
	protected boolean isByReference(DistributionLineGrouperKey key) {
		return false;
	}
	
	@Override
	protected int compareByReference(DistributionLineGrouperKey key1, DistributionLineGrouperKey key2) {
		return compareTarget(key1, key2);
	}
	
	@Override
	protected String getContainerCode(DistributionLineGrouperKey key) {
		return ((ByContainerCodeOrderLineSelector) key.getSelector()).getContainerCode();
	}
	
	@Override
	protected Optional<Integer> getLocation(DistributionLineGrouperKey key) {
		if (isByCommercialGroup(key)) {
			return ((ByCommercialGroupOrderLineSelector) key.getSelector()).getLocation();
		}
		return Optional.empty();
	}
	
	@Override
	protected Optional<Integer> getCommercial(DistributionLineGrouperKey key) {
		if (isByCommercialGroup(key)) {
			return ((ByCommercialGroupOrderLineSelector) key.getSelector()).getCommercialGroup();
		}
		return Optional.empty();
	}

	@Override
	protected DistributionLineGrouperKey getKey(DistributionLine line) {
		return DistributionLineGrouperKey.of(line.getTarget(), line.getSelector());
	}


}

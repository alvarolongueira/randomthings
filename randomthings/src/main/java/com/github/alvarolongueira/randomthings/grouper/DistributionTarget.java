package com.github.alvarolongueira.randomthings.grouper;

import org.immutables.value.Value;

@Value.Immutable(builder=false, intern=true)
@Value.Style(weakInterning = true)
public abstract class DistributionTarget{
	
	public static DistributionTarget of(String locationCode, String sectionType, String separated) {
		return ImmutableDistributionTarget.of(locationCode, sectionType, separated);
	}

	@Value.Parameter
	public abstract String getLocationCode();

	@Value.Parameter
	public abstract String getSectionType();

	@Value.Parameter
	public abstract String getSeparated();

	@Override
	public String toString() {
		return getLocationCode() + "/" + getSectionType() + "/" + getSeparated();
	}
}

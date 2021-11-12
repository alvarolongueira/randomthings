package com.alvarolongueira.randomthings.grouper;

public interface DistributionLineSelector {

	boolean equalsType(DistributionLineSelector selector);
	
	DistributionLineType getType();
}

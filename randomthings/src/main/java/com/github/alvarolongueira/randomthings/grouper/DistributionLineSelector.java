package com.github.alvarolongueira.randomthings.grouper;

public interface DistributionLineSelector {

	boolean equalsType(DistributionLineSelector selector);
	
	DistributionLineType getType();
}

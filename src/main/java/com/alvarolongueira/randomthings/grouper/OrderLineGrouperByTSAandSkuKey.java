package com.alvarolongueira.randomthings.grouper;

import java.util.Set;

import org.immutables.value.Value;

import com.google.common.collect.ImmutableSet;

@Value.Immutable(builder = false, copy = false, prehash = true)
public abstract class OrderLineGrouperByTSAandSkuKey extends OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector> {

	protected static OrderLineGrouperByTSAandSkuKey of(DistributionTarget target, ByReferenceOrderLineSelector selector) {
		return ImmutableOrderLineGrouperByTSAandSkuKey.of(target, selector, ImmutableSet.of());
	}

	protected static OrderLineGrouperByTSAandSkuKey of(DistributionTarget target, ByReferenceOrderLineSelector selector, Set<Integer> goodsStatusList) {
		return ImmutableOrderLineGrouperByTSAandSkuKey.of(target, selector, goodsStatusList);
	}

	@Override
	@Value.Parameter
	public abstract DistributionTarget getTarget();

	@Override
	@Value.Parameter
	public abstract ByReferenceOrderLineSelector getSelector();

	@Value.Parameter
	public abstract Set<Integer> goodsStatusList();

	@Override
	public OrderLineGrouperByTSAandSkuKey getKeyWithAllEm() {
		return getNewKey(true);
	}

	@Override
	public OrderLineGrouperByTSAandSkuKey getKeyWithoutEm() {
		return getNewKey(false);
	}

	private OrderLineGrouperByTSAandSkuKey getNewKey(boolean withAllEm) {

		ByReferenceOrderLineSelector newSelector = this.getSelector();
		
		if (withAllEm) {
			newSelector = this.getSelector().modify().goodsStatusOriginal(this.goodsStatusList()).build();
			
		} else {
			if (this.goodsStatusList().size() > 1) {
				newSelector = this.getSelector().modify().goodsStatusOriginal(ImmutableSet.of()).build();
			} else {
				newSelector = this.getSelector().modify().goodsStatusOriginal(this.goodsStatusList()).build();
			}
		}

		OrderLineGrouperByTSAandSkuKey newKey = OrderLineGrouperByTSAandSkuKey.of(this.getTarget(), newSelector, this.goodsStatusList());
		return newKey;
	}
	
}
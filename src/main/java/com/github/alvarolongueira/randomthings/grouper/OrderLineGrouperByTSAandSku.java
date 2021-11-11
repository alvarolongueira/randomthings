package com.github.alvarolongueira.randomthings.grouper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;

public class OrderLineGrouperByTSAandSku extends OrderLineGrouper<DistributionTarget, ByReferenceOrderLineSelector> {

	private final Set<OrderLineGrouperByTSAandSkuKey> keysWithMulipleEm = new HashSet<>();
	private final Multimap<OrderLineGrouperByTSAandSkuKey, Integer> keysWithAllEm = MultimapBuilder.linkedHashKeys().linkedHashSetValues().build();

	public static OrderLineGrouperByTSAandSku of() {
		return new OrderLineGrouperByTSAandSku();
	}

	private OrderLineGrouperByTSAandSku() {
		super(OrderLineGrouperType.DISTRIBUTION_TARGET);
	}
	
	@Override
	public OrderLineGrouperByTSAandSkuKey getKey(OrderLine<ByReferenceOrderLineSelector> line) {

		ByReferenceOrderLineSelector newSelector = getSelector(line.getSelector(), false);
		OrderLineGrouperByTSAandSkuKey key = OrderLineGrouperByTSAandSkuKey.of(line.getTarget().getDistributionTarget(), newSelector);

		if (!keysWithMulipleEm.contains(key)) {
			newSelector = getSelector(line.getSelector(), true);
			key = OrderLineGrouperByTSAandSkuKey.of(line.getTarget().getDistributionTarget(), newSelector, newSelector.getGoodsStatusOriginal());

		} else {
			Set<Integer> goodStatuses = ImmutableSet.copyOf(keysWithAllEm.get(key));
			newSelector = getSelector(line.getSelector(), false);

			key = OrderLineGrouperByTSAandSkuKey.of(line.getTarget().getDistributionTarget(), newSelector, goodStatuses);
		}
		
		return key;
	}
	
	private ByReferenceOrderLineSelector getSelector(ByReferenceOrderLineSelector selector, boolean withEM) {
		
		Set<Integer> goodsStatusList = ImmutableSet.of();
		if (withEM) {
			goodsStatusList = selector.getGoodsStatusOriginal();
		}
		
		ByReferenceOrderLineSelector newSelector = ByReferenceOrderLineSelector.builder()
				.garmentRef(selector.getGarmentRef())
				.unitsPerLot(selector.getUnitsPerLot())
				.addAllGoodsStatusOriginal(goodsStatusList)
				.build();
		return newSelector;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void add(Collection<?> lines) {
		
		lines.stream()
			.map(line -> ((OrderLine<ByReferenceOrderLineSelector>) line))
			.forEach((line) -> {
				ByReferenceOrderLineSelector newSelector = getSelector(line.getSelector(), false);
				OrderLineGrouperByTSAandSkuKey key = OrderLineGrouperByTSAandSkuKey.of(line.getTarget().getDistributionTarget(), newSelector);
				if (line.getSelector().getGoodsStatusOriginal().size() > 1) {
					keysWithMulipleEm.add(key);
				}
				keysWithAllEm.get(key).addAll(line.getSelector().getGoodsStatusOriginal());
			});
		
		super.add(lines);
	}
}

package com.alvarolongueira.randomthings.grouper;

import java.util.Collection;
import java.util.Optional;

public class OrderLineGrouper<T, S extends OrderLineSelector> extends LineGrouperWithPriority<OrderLineGrouperKey<T, S>, OrderLine<S>> {
 
	public static <S extends OrderLineSelector> OrderLineGrouper<OrderTarget, S> ofOrderTarget() {
		return new OrderLineGrouper<OrderTarget, S>(OrderLineGrouperType.ORDER_TARGET);
	}

	public static <S extends OrderLineSelector> OrderLineGrouper<Integer, S> ofBillingLocation() {
		return new OrderLineGrouper<Integer, S>(OrderLineGrouperType.BILLING_LOCATION);
	}

	public static <S extends OrderLineSelector> OrderLineGrouper<DistributionTarget, S> ofDistributionTarget() {
		return new OrderLineGrouper<DistributionTarget, S>(OrderLineGrouperType.DISTRIBUTION_TARGET);
	}

	private final OrderLineGrouperType type;

	protected OrderLineGrouper(OrderLineGrouperType type) {
		super();
		this.type = type;
	}

	protected enum OrderLineGrouperType {
		ORDER_TARGET, 
		BILLING_LOCATION, 
		DISTRIBUTION_TARGET
	}
	
	@Override
	protected boolean sameType(OrderLineGrouperKey<T, S> key1, OrderLineGrouperKey<T, S> key2) {
		return key1.getSelector().equalsType(key2.getSelector());
	}

	@Override
	protected boolean isByContainerCode(OrderLineGrouperKey<T, S> key) {
		return key.getSelector() instanceof ByContainerCodeOrderLineSelector;
	}

	@Override
	protected boolean isByCommercialGroup(OrderLineGrouperKey<T, S> key) {
		return key.getSelector() instanceof ByCommercialGroupOrderLineSelector;
	}
	
	@Override
	protected boolean isByReference(OrderLineGrouperKey<T, S> key) {
		return key.getSelector() instanceof ByReferenceOrderLineSelector;
	}

	@Override
	protected String getContainerCode(OrderLineGrouperKey<T, S> key) {
		return ((ByContainerCodeOrderLineSelector) key.getSelector()).getContainerCode();
	}
	
	@Override
	protected Optional<Integer> getLocation(OrderLineGrouperKey<T, S> key) {
		if (isByCommercialGroup(key)) {
			return ((ByCommercialGroupOrderLineSelector) key.getSelector()).getLocation();
		}
		return Optional.empty();
	}

	@Override
	protected Optional<Integer> getCommercial(OrderLineGrouperKey<T, S> key) {
		if (isByCommercialGroup(key)) {
			return ((ByCommercialGroupOrderLineSelector) key.getSelector()).getCommercialGroup();
		}
		return Optional.empty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public OrderLineGrouperKey<T, S> getKey(OrderLine<S> line) {
		
		S selector = line.getSelector();
		
		if (selector.getType().equals(OrderLineType.TO_LOCATION_BY_REFERENCE)){
			ByReferenceOrderLineSelector currentSelector  = ((ByReferenceOrderLineSelector) selector);
			selector = (S) ByReferenceOrderLineSelector.builder()
					.garmentRef(currentSelector.getGarmentRef())
					.unitsPerLot(currentSelector.getUnitsPerLot())
					.build();
		}
		
		if (this.type.equals(OrderLineGrouperType.ORDER_TARGET)) {
			return (OrderLineGrouperKey<T, S>) OrderLineGrouperKey.of(line.getTarget(), selector);
		} else if (this.type.equals(OrderLineGrouperType.BILLING_LOCATION)) {
			return (OrderLineGrouperKey<T, S>) OrderLineGrouperKey.of(line.getTarget().getBillingLocation().orElse(null), selector);
		} else if (this.type.equals(OrderLineGrouperType.DISTRIBUTION_TARGET)) {
			return (OrderLineGrouperKey<T, S>) OrderLineGrouperKey.of(line.getTarget().getDistributionTarget(), selector);
		}
		
		throw new IllegalArgumentException("Tipo de agrupador no permitido: " + this.type);
	}
	
	@Override
	protected int compareByReference(OrderLineGrouperKey<T, S> key1, OrderLineGrouperKey<T, S> key2) {
		if (!isByReference(key1) || !isByReference(key2)) {
			throw new IllegalArgumentException(" " + key1 + " - " + key2);
		}
		
		ByReferenceOrderLineSelector selector1 = (ByReferenceOrderLineSelector) key1.getSelector();
		ByReferenceOrderLineSelector selector2 = (ByReferenceOrderLineSelector) key2.getSelector();

		if (selector1.equals(selector2)) {
			return compareTarget(key1, key2);
		}
		
		int counter1 = this.counterOptionalsPresent(selector1);
		int counter2 = this.counterOptionalsPresent(selector2);
		int result = Integer.compare(counter1, counter2);
		
		if (result == 0) {
			result = this.calculateFieldsPriority(selector1, selector2);
		}
		
		if (result == 0) {
			result = selector1.toString().compareTo(selector2.toString());
		}
	
		return result;
	}
	
	private int calculateFieldsPriority(ByReferenceOrderLineSelector selector1, ByReferenceOrderLineSelector selector2) {
		int result = 0;

		result = compareCollectionField(selector1.getGoodsStatusOriginal(), selector2.getGoodsStatusOriginal());
		if (result != 0) {
			return result;
		}

		result = compareField(selector1.getUnitsPerLot(), selector2.getUnitsPerLot());
		if (result != 0) {
			return result;
		}
		
		return result;
	}

	private int compareField(Optional<?> object1, Optional<?> object2) {
		if (object1.isPresent()) {
			if (object2.isPresent()) {
				return object1.toString().compareTo(object2.toString());
			} else {
				return -1;
			}  
		} else if (object2.isPresent()) {
			return 1;
		}
		
		return 0;
	}
	
	private int compareCollectionField(Collection<?> object1, Collection<?> object2) {
		if (!object1.isEmpty()) {
			if (!object2.isEmpty()) {
				if (object1.size() == object2.size()) {
					return object1.toString().compareTo(object2.toString());
				} else {
					return Integer.compare(object2.size(), object1.size());
				}
			} else {
				return -1;
			}  
		} else if (!object2.isEmpty()) {
			return 1;
		}
		
		return 0;
	}

	private int counterOptionalsPresent(ByReferenceOrderLineSelector selector) {
		int counter = 0;

		if (!selector.getGoodsStatusOriginal().isEmpty()) {
			counter--;
		}
		if (selector.getUnitsPerLot().isPresent()) {
			counter--;
		}
		return counter;
	}

}


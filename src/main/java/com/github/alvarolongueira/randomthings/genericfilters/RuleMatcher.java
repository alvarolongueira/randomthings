package com.github.alvarolongueira.randomthings.genericfilters;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class RuleMatcher {

	public static Optional<Rule> findRuleToApply(List<Rule> rulesToMatch, Set<RuleFilter<?>> values) {
		for (Rule currentRule: rulesToMatch){
			if (match(currentRule, values)){
				return Optional.of(currentRule);
			}
		}
		return Optional.empty();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> boolean match(Rule rule, Set<RuleFilter<?>> containerValues) {
		for (RuleFilter ruleFilter : rule.filters()) {
			
			if (ruleFilter.values().isEmpty()) {
				continue;
			}
			
			if (ruleFilter.type().equals(RuleFilterType.MAX_CONTAINERS) || ruleFilter.type().equals(RuleFilterType.MAX_UNITS)){
				if (!matchMaxFilters(rule.getFilter(RuleFilterType.MAX_CONTAINERS).get().first(), containerValues, ruleFilter)) {
					return false;
				}
			} else {
				Optional<RuleFilter<T>> containerValue = getFilter(containerValues, ruleFilter.type());
				
				if (!containerValue.isPresent() || !ruleFilter.values().containsAll(containerValue.get().values())) {
					return false;
				}
			}
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	private static <T> Optional<RuleFilter<T>> getFilter(Set<RuleFilter<?>> values, RuleFilterType<T> type) {
		RuleFilter<T> result = null;

		for (RuleFilter<?> current : values) {
			if (current.type().equals(type)) {
				result = (RuleFilter<T>) current;
			}
		}

		return Optional.ofNullable(result);
	}
	
	private static boolean matchMaxFilters(Integer targetFacility, Set<RuleFilter<?>> values, RuleFilter<Integer> filter) {
		Optional<RuleFilter<Integer>> maxValueToMatchHolder = getFilter(values, RuleFilterType.MAX_CONTAINERS);
		Integer maxValueFromRule = filter.first();
		
		if (maxValueToMatchHolder.isPresent()) {
			Optional<Integer> infoForTargetFacility = maxValueToMatchHolder.get().values().stream()
					.filter(info -> info.equals(targetFacility))
					.findFirst();
		
			if (infoForTargetFacility.isPresent()) {
				if (filter.type().equals(RuleFilterType.MAX_CONTAINERS)) {
					return infoForTargetFacility.get() <= maxValueFromRule;
				} else if (filter.type().equals(RuleFilterType.MAX_UNITS)) {
					return infoForTargetFacility.get() <= maxValueFromRule;
				}
			}
		}

		return false;
	}
	
}

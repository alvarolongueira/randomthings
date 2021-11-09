package com.github.alvarolongueira.randomthings.grouper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import com.google.common.collect.ImmutableList;

@SuppressWarnings("rawtypes")
public abstract class LineGrouperWithPriority<K extends LineGrouperKey, L> {

	private final Map<K, List<L>> map;

	protected LineGrouperWithPriority() {
		this.map = new TreeMap<K, List<L>>((key1, key2) -> this.compare(key1, key2));
	}

	@SuppressWarnings("unchecked")
	public void add(Collection<?> lines) {
		lines.forEach(line -> add((L) line));
	}

	private void add(L line) {
		K key = getKey(line);
		List<L> list = null;
		if (!this.map.containsKey(key)) {
			list = new ArrayList<L>();
		} else {
			list = map.get(key);
		}

		list.add(line);
		this.map.put(key, list);
	}

	public Set<Entry<K, List<L>>> getKeyLines() {
		return this.map.entrySet();
	}
	
	public Set<K> getKeys() {
		return this.map.keySet();
	}

	public List<L> getLines(K key) {
		return this.map.get(key);
	}
	
	public List<L> getAllLines() {
		return this.map.entrySet()
				.stream()
				.map(entry -> entry.getValue())
				.flatMap(List::stream)
				.collect(ImmutableList.toImmutableList());
	}

	private int compare(K key1, K key2) {
		if (key1.equals(key2)) {
			return 0;
		}
		int res = this.compareNotEqualsKeys(key1, key2);

		return res;
	}

	private int compareNotEqualsKeys(K key1, K key2) {
		if (sameType(key1, key2)) {
			if (isByCommercialGroup(key1)) {
				return compareByCommercial(key1, key2);
			}
			if (isByReference(key1)) {
				return compareByReference(key1, key2);
			}
			if (isByContainerCode(key1)) {
				return compareByContainerCode(key1, key2);
			}
			return compareTarget(key1, key2);
		}

		if (isByContainerCode(key1)) {
			return -1;
		}
		if (isByContainerCode(key2)) {
			return 1;
		}
		if (isByCommercialGroup(key1)) {
			return -1;
		}
		if (isByCommercialGroup(key2)) {
			return 1;
		}
		if (isByReference(key1)) {
			return -1;
		}
		if (isByReference(key2)) {
			return 1;
		}

		return compareTarget(key1, key2);
	}

	private int compareByContainerCode(K key1, K key2) {
		String code1 = getContainerCode(key1);
		String code2 = getContainerCode(key2);

		int resultCompare = code1.compareTo(code2);
		if (resultCompare != 0) {
			return resultCompare;
		}
		return compareTarget(key1, key2);
	}

	private int compareByCommercial(K key1, K key2) {
		Optional<Integer> location1 = getLocation(key1);
		Optional<Integer> location2 = getLocation(key2);

		if (location1.isPresent() && location2.isPresent()) {
			int resultCompare = Integer.compare(location1.get(), location2.get());
			if (resultCompare != 0) {
				return resultCompare;
			}
		} else if (location1.isPresent()) {
			return -1;
		} else if (location2.isPresent()) {
			return 1;
		}

		Optional<Integer> commercial1 = getCommercial(key1);
		Optional<Integer> commercial2 = getCommercial(key2);

		if (commercial1.isPresent() && commercial2.isPresent()) {
			int resultCompare = Integer.compare(commercial1.get(), commercial2.get());
			if (resultCompare != 0) {
				return resultCompare;
			}
		} else if (commercial1.isPresent()) {
			return -1;
		} else if (commercial2.isPresent()) {
			return 1;
		}

		return compareTarget(key1, key2);
	}
	
	protected int compareTarget(K key1, K key2) {
		if (key1.getTarget().equals(key2.getTarget())) {
			return 0;
		}
		return key1.getTarget().toString().compareTo(key2.getTarget().toString());
	}
	
	protected abstract int compareByReference(K key1, K key2);

	protected abstract boolean sameType(K key1, K key2);

	protected abstract boolean isByContainerCode(K key);

	protected abstract boolean isByCommercialGroup(K key);

	protected abstract boolean isByReference(K key);

	protected abstract K getKey(L line);
	
	protected abstract String getContainerCode(K key);

	protected abstract Optional<Integer> getLocation(K key);

	protected abstract Optional<Integer> getCommercial(K key);

}

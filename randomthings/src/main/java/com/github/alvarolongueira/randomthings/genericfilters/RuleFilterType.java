package com.github.alvarolongueira.randomthings.genericfilters;

import java.io.Serializable;

public final class RuleFilterType<T> implements Serializable {

	private static final long serialVersionUID = 7393675966394383844L;
	
	public static final RuleFilterType<String> RETAILER = new RuleFilterType<String>(String.class,"RETAILER" );
	public static final RuleFilterType<String> CAMPAIGN = new RuleFilterType<String>(String.class, "CAMPAIGN");
	public static final RuleFilterType<String> SECTION_TYPE = new RuleFilterType<String>(String.class, "SECTION_TYPE");
	public static final RuleFilterType<String> SUBSECTION = new RuleFilterType<String>(String.class, "SUBSECTION");
	public static final RuleFilterType<String> SUPPLIER = new RuleFilterType<String>(String.class, "SUPPLIER" );
	public static final RuleFilterType<String> FAMILY = new RuleFilterType<String>(String.class, "FAMILY");
	public static final RuleFilterType<String> SUBFAMILY = new RuleFilterType<String>(String.class, "SUBFAMILY");
	public static final RuleFilterType<Integer> GOODS_STATUS = new RuleFilterType<Integer>(Integer.class, "GOODS_STATUS");
	public static final RuleFilterType<Boolean> RFID = new RuleFilterType<Boolean>(Boolean.class, "RFID");
	public static final RuleFilterType<Boolean> PROBLEMATIC_RFID = new RuleFilterType<Boolean>(Boolean.class, "PROBLEMATIC_RFID");
	public static final RuleFilterType<String> SIZE_KEY = new RuleFilterType<String>(String.class, "SIZE_KEY");
	public static final RuleFilterType<Integer> UNITS_PER_LOT = new RuleFilterType<Integer>(Integer.class, "UNITS_PER_LOT");
	public static final RuleFilterType<String> CONTAINER_TYPE = new RuleFilterType<String>(String.class, "CONTAINER_TYPE");
	public static final RuleFilterType<String> SOURCE_FACILITY = new RuleFilterType<String>(String.class, "SOURCE_FACILITY");
	public static final RuleFilterType<String> SUBFACILITY = new RuleFilterType<String>(String.class, "SUBFACILITY");
	public static final RuleFilterType<String> CONTAINER_DIMENSION_TYPE = new RuleFilterType<String>(String.class, "CONTAINER_DIMENSION_TYPE");
	public static final RuleFilterType<Integer> MAX_UNITS = new RuleFilterType<Integer>(Integer.class, "MAX_UNITS");
	public static final RuleFilterType<Integer> MAX_CONTAINERS = new RuleFilterType<Integer>(Integer.class, "MAX_CONTAINERS");
	public static final RuleFilterType<Boolean> ASSORTMENT = new RuleFilterType<Boolean>(Boolean.class, "ASSORMENT");
	public static final RuleFilterType<String> DIMENSIONS_INFO = new RuleFilterType<String>(String.class, "DIMENSIONS_INFO");
	public static final RuleFilterType<Integer> PRODUCT = new RuleFilterType<Integer>(Integer.class, "PRODUCT");
	public static final RuleFilterType<Integer> MODEL = new RuleFilterType<Integer>(Integer.class, "MODEL");
	public static final RuleFilterType<Integer> QUALITY = new RuleFilterType<Integer>(Integer.class, "QUALITY");
	public static final RuleFilterType<Integer> COLOR = new RuleFilterType<Integer>(Integer.class, "COLOR");
	public static final RuleFilterType<Integer> SIZE = new RuleFilterType<Integer>(Integer.class, "SIZE");
	public static final RuleFilterType<String> GOODS_OWNER = new RuleFilterType<String>(String.class, "GOODS_OWNER");

	private final Class<T> classType;
	private final String name;

	public static <T> RuleFilterType<T> of(Class<T> type, String name){
		return new RuleFilterType<T>(type, name);
	}
	
	private RuleFilterType(Class<T> classType, String name) {
		this.classType = classType;
		this.name = name;
	}
	
	public String getType(){
		return this.classType.getName();
	}
	
	public String getName(){
		return this.name;
	}
	
	@Override
	public boolean equals(Object type) {
		if (type instanceof RuleFilterType){
			return this.getType().equals(((RuleFilterType<?>) type).getType())
					&& this.getName().equals(((RuleFilterType<?>) type).getName());
		}
		return false; 
	}

	@Override
	public String toString() {
		return "RuleFilterType[" + this.getName() + "]";
	}
}

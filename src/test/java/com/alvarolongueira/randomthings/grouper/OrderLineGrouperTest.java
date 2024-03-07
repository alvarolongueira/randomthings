package com.alvarolongueira.randomthings.grouper;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"rawtypes", "deprecation"})
public class OrderLineGrouperTest {

  private static final Integer COMMERCIAL_GROUP_1225 = 1225;
  private static final Integer LOCATION_93 = 93;
  private static final Integer LOCATION_96 = 96;

  private static final String CODE_100000000000000000001116 = "100000000000000000001116";
  private static final String CODE_100000000000000000001117 = "100000000000000000001117";
  private static final String CODE_100000000000000000001118 = "100000000000000000001118";

  private static final String REF_0045841892299 = "REF_0045841892299";
  private static final String REF_0888888980032 = "REF_0888888980032";
  private static final String REF_0100110025005 = "REF_0100110025005";
  private static final String REF_0639540082299 = "REF_0639540082299";
  private static final String REF_0989020980201 = "REF_0989020980201";

  private static final DistributionTarget TARGET_19_3_1 = DistributionTarget.of("19", "3", "1");
  private static final DistributionTarget TARGET_20_3_1 = DistributionTarget.of("20", "3", "1");

  private static final OrderTarget TARGET_INFO_19_3_1_FOR_93 = OrderTarget.of(TARGET_19_3_1, LOCATION_93);
  private static final OrderTarget TARGET_INFO_19_3_1_FOR_96 = OrderTarget.of(TARGET_19_3_1, LOCATION_96);
  private static final OrderTarget TARGET_INFO_20_3_1_FOR_93 = OrderTarget.of(TARGET_20_3_1, LOCATION_93);
  private static final OrderTarget TARGET_INFO_20_3_1_FOR_96 = OrderTarget.of(TARGET_20_3_1, LOCATION_96);

  private static final ByCommercialGroupOrderLineSelector COMM_GROUP_SELECTOR_2007_I = ByCommercialGroupOrderLineSelector.of(
      Optional.empty(), Optional.empty());
  private static final ByCommercialGroupOrderLineSelector COMM_GROUP_SELECTOR_2009_V_CG1225 = ByCommercialGroupOrderLineSelector.of(
      Optional.of(COMMERCIAL_GROUP_1225), Optional.empty());
  private static final ByCommercialGroupOrderLineSelector COMM_GROUP_SELECTOR_2009_V_LOC93 = ByCommercialGroupOrderLineSelector.of(
      Optional.empty(), Optional.of(LOCATION_93));
  private static final ByCommercialGroupOrderLineSelector COMM_GROUP_SELECTOR_1963_I_CG1225_LOC93 = ByCommercialGroupOrderLineSelector.of(
      Optional.of(COMMERCIAL_GROUP_1225), Optional.of(LOCATION_93));

  private static final ByContainerCodeOrderLineSelector CODE_SELECTOR_100000000000000000001116 = ByContainerCodeOrderLineSelector.of(
      CODE_100000000000000000001116);
  private static final ByContainerCodeOrderLineSelector CODE_SELECTOR_100000000000000000001117 = ByContainerCodeOrderLineSelector.of(
      CODE_100000000000000000001117);
  private static final ByContainerCodeOrderLineSelector CODE_SELECTOR_100000000000000000001118 = ByContainerCodeOrderLineSelector.of(
      CODE_100000000000000000001118);

  private static final ByReferenceOrderLineSelector REF_SELECTOR_0045841892299_00_00_00 = referenceSelector(
      REF_0045841892299, ImmutableSet.of(), null);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0045841892299_01_00_00 = referenceSelector(
      REF_0045841892299, ImmutableSet.of(1), null);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0045841892299_00_00_01 = referenceSelector(
      REF_0045841892299, ImmutableSet.of(), 1);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0045841892299_00_00_02 = referenceSelector(
      REF_0045841892299, ImmutableSet.of(), 2);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0045841892299_00_00_03 = referenceSelector(
      REF_0045841892299, ImmutableSet.of(), 3);

  private static final ByReferenceOrderLineSelector REF_SELECTOR_0100110025005_00_00_00 = referenceSelector(
      REF_0100110025005, ImmutableSet.of(), null);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0100110025005_04_00_00 = referenceSelector(
      REF_0100110025005, ImmutableSet.of(4), null);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0100110025005_25_00_00 = referenceSelector(
      REF_0100110025005, ImmutableSet.of(2, 5), null);

  private static final ByReferenceOrderLineSelector REF_SELECTOR_0639540082299_00_00_00 = referenceSelector(
      REF_0639540082299, ImmutableSet.of(), null);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0639540082299_01_00_00 = referenceSelector(
      REF_0639540082299, ImmutableSet.of(1), null);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0639540082299_02_00_00 = referenceSelector(
      REF_0639540082299, ImmutableSet.of(2), null);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0639540082299_03_00_00 = referenceSelector(
      REF_0639540082299, ImmutableSet.of(3), null);

  private static final ByReferenceOrderLineSelector REF_SELECTOR_0989020980201_00_00_00 = referenceSelector(
      REF_0989020980201, ImmutableSet.of(), null);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0989020980201_01_00_00 = referenceSelector(
      REF_0989020980201, ImmutableSet.of(1), null);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0989020980201_02_00_00 = referenceSelector(
      REF_0989020980201, ImmutableSet.of(2), null);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0989020980201_03_00_00 = referenceSelector(
      REF_0989020980201, ImmutableSet.of(3), null);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0989020980201_05_00_00 = referenceSelector(
      REF_0989020980201, ImmutableSet.of(5), null);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0989020980201_45_00_00 = referenceSelector(
      REF_0989020980201, ImmutableSet.of(4, 5), null);

  private static final ByReferenceOrderLineSelector REF_SELECTOR_0888888980032_00_00_00 = referenceSelector(
      REF_0888888980032, ImmutableSet.of(), null);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0888888980032_13_00_00 = referenceSelector(
      REF_0888888980032, ImmutableSet.of(1, 3), null);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0888888980032_03_00_00 = referenceSelector(
      REF_0888888980032, ImmutableSet.of(3), null);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0888888980032_00_00_02 = referenceSelector(
      REF_0888888980032, ImmutableSet.of(), 2);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0888888980032_03_NE_02 = referenceSelector(
      REF_0888888980032, ImmutableSet.of(3), 2);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0888888980032_03_NO_02 = referenceSelector(
      REF_0888888980032, ImmutableSet.of(3), 2);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0888888980032_03_00_02 = referenceSelector(
      REF_0888888980032, ImmutableSet.of(3), 2);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0888888980032_00_00_03 = referenceSelector(
      REF_0888888980032, ImmutableSet.of(), 3);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0888888980032_03_00_03 = referenceSelector(
      REF_0888888980032, ImmutableSet.of(3), 3);
  private static final ByReferenceOrderLineSelector REF_SELECTOR_0888888980032_13_00_03 = referenceSelector(
      REF_0888888980032, ImmutableSet.of(1, 3), 3);

  @Test
  public void test_tree_map_order_problem() {

    ImmutableList<OrderLine> lines = ImmutableList.of(
        buildLine(21, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_02),
        buildLine(22, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_00_00_00),
        buildLine(23, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00),
        buildLine(24, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
        buildLine(25, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00),
        buildLine(26, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_01),
        buildLine(27, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_03),
        buildLine(28, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_01_00_00),
        buildLine(29, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225),
        buildLine(10, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_LOC93),
        buildLine(11, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2007_I),
        buildLine(12, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(13, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001117),
        buildLine(14, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001118)
    );

    OrderLineGrouper<OrderTarget, OrderLineSelector> grouper = OrderLineGrouper.ofOrderTarget();
    grouper.add(lines);

    List<OrderLine<OrderLineSelector>> actual = grouper.getAllLines();
    List<OrderLine<OrderLineSelector>> expected = ImmutableList.of(
        buildLine(12, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(13, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001117),
        buildLine(14, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001118),
        buildLine(10, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_LOC93),
        buildLine(29, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225),
        buildLine(11, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2007_I),
        buildLine(26, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_01),
        buildLine(21, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_02),
        buildLine(27, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_03),
        buildLine(23, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00),
        buildLine(28, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_01_00_00),
        buildLine(22, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_00_00_00),
        buildLine(24, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
        buildLine(25, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00)
    );

    List<OrderLineGrouperKey<OrderTarget, OrderLineSelector>> keyList = ImmutableList.of(
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001117),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001118),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_LOC93),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2007_I),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_01),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_02),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_03),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_00_00_00),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00)
    );

    Map<OrderLineGrouperKey<OrderTarget, OrderLineSelector>, List<Integer>> keysWithLines = ImmutableMap.<OrderLineGrouperKey<OrderTarget, OrderLineSelector>, List<Integer>>builder()
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
            ImmutableList.of(12))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001117),
            ImmutableList.of(13))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001118),
            ImmutableList.of(14))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_LOC93), ImmutableList.of(10))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225), ImmutableList.of(29))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2007_I), ImmutableList.of(11))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_01),
            ImmutableList.of(26))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_02),
            ImmutableList.of(21))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_03),
            ImmutableList.of(27))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00),
            ImmutableList.of(23, 28))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_00_00_00),
            ImmutableList.of(22))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00),
            ImmutableList.of(24, 25))
        .build();

    grouper.getKeys().stream()
        .forEach((grouperKey) -> {
          Assertions.assertNotNull(grouper.getLines(grouperKey));
        });

//		print(actual, expected,  grouper.getKeys());
    Assertions.assertTrue(assertKeyCount(keyList, grouper));
    Assertions.assertTrue(assertEquals(expected, actual));
    Assertions.assertTrue(assertKeysWithLines(keysWithLines, grouper));
  }

  @Test
  public void test_grouper_by_selector() {
    ImmutableList<OrderLine> lines = ImmutableList.of(
        buildLine(21, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2007_I),
        buildLine(22, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225),
        buildLine(11, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
        buildLine(23, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00),
        buildLine(24, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_LOC93),
        buildLine(12, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(25, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225),
        buildLine(13, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
        buildLine(16, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(26, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(27, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_1963_I_CG1225_LOC93),
        buildLine(28, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(29, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_01_00_00)
    );

    OrderLineGrouper<OrderTarget, OrderLineSelector> grouper = OrderLineGrouper.ofOrderTarget();
    grouper.add(lines);

    List<OrderLine<OrderLineSelector>> actual = grouper.getAllLines();
    List<OrderLine<OrderLineSelector>> expected = ImmutableList.of(
        buildLine(26, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(27, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_1963_I_CG1225_LOC93),
        buildLine(24, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_LOC93),
        buildLine(22, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225),
        buildLine(25, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225),
        buildLine(21, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2007_I),
        buildLine(11, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
        buildLine(12, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(16, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(23, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00),
        buildLine(29, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_01_00_00),
        buildLine(13, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
        buildLine(28, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00)
    );

    List<OrderLineGrouperKey<OrderTarget, OrderLineSelector>> keyList = ImmutableList.of(
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_1963_I_CG1225_LOC93),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_LOC93),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2007_I),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_02),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00)
    );

    Map<OrderLineGrouperKey<OrderTarget, OrderLineSelector>, List<Integer>> keysWithLines = ImmutableMap.<OrderLineGrouperKey<OrderTarget, OrderLineSelector>, List<Integer>>builder()
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
            ImmutableList.of(26))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_1963_I_CG1225_LOC93),
            ImmutableList.of(27))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_LOC93), ImmutableList.of(24))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225),
            ImmutableList.of(22, 25))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2007_I), ImmutableList.of(21))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_02),
            ImmutableList.of(11, 12))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03),
            ImmutableList.of(16))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00),
            ImmutableList.of(23, 29))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00),
            ImmutableList.of(13, 28))
        .build();

//		print(actual, expected,  grouper.getKeys());
    Assertions.assertTrue(assertKeyCount(keyList, grouper));
    Assertions.assertTrue(assertEquals(expected, actual));
    Assertions.assertTrue(assertKeysWithLines(keysWithLines, grouper));
  }

  @Test
  public void test_grouper_by_selector_change_insert_sequence() {
    ImmutableList<OrderLine> lines = ImmutableList.of(
        buildLine(25, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(21, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2007_I),
        buildLine(23, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00),
        buildLine(29, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_01_00_00),
        buildLine(22, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225),
        buildLine(14, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
        buildLine(24, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_LOC93),
        buildLine(12, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(28, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
        buildLine(15, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225),
        buildLine(11, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(18, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_1963_I_CG1225_LOC93),
        buildLine(27, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00)
    );

    OrderLineGrouper<OrderTarget, OrderLineSelector> grouper = OrderLineGrouper.ofOrderTarget();
    grouper.add(lines);

    List<OrderLine<OrderLineSelector>> actual = grouper.getAllLines();
    List<OrderLine<OrderLineSelector>> expected = ImmutableList.of(
        buildLine(11, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(18, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_1963_I_CG1225_LOC93),
        buildLine(24, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_LOC93),
        buildLine(22, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225),
        buildLine(15, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225),
        buildLine(21, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2007_I),
        buildLine(14, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
        buildLine(12, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(25, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(23, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00),
        buildLine(29, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_01_00_00),
        buildLine(28, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
        buildLine(27, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00)
    );

    List<OrderLineGrouperKey<OrderTarget, OrderLineSelector>> keyList = ImmutableList.of(
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_1963_I_CG1225_LOC93),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_LOC93),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2007_I),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_02),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00)
    );

    Map<OrderLineGrouperKey<OrderTarget, OrderLineSelector>, List<Integer>> keysWithLines = ImmutableMap.<OrderLineGrouperKey<OrderTarget, OrderLineSelector>, List<Integer>>builder()
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
            ImmutableList.of(11))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_1963_I_CG1225_LOC93),
            ImmutableList.of(18))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_LOC93), ImmutableList.of(24))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225),
            ImmutableList.of(22, 15))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2007_I), ImmutableList.of(21))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_02),
            ImmutableList.of(14, 12))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03),
            ImmutableList.of(25))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00),
            ImmutableList.of(23, 29))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00),
            ImmutableList.of(28, 27))
        .build();

//		print(actual, expected,  grouper.getKeys());
    Assertions.assertTrue(assertKeyCount(keyList, grouper));
    Assertions.assertTrue(assertEquals(expected, actual));
    Assertions.assertTrue(assertKeysWithLines(keysWithLines, grouper));
  }

  @Test
  public void test_grouper_by_order_target() {
    ImmutableList<OrderLine> lines = ImmutableList.of(
        buildLine(1, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(2, TARGET_INFO_20_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116),
        buildLine(3, TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(4, TARGET_INFO_19_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116),
        buildLine(5, TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(6, TARGET_INFO_19_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116),
        buildLine(7, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(8, TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116)
    );

    OrderLineGrouper<OrderTarget, ByContainerCodeOrderLineSelector> grouper = OrderLineGrouper.ofOrderTarget();
    grouper.add(lines);

    List<OrderLine<ByContainerCodeOrderLineSelector>> actual = grouper.getAllLines();
    List<OrderLine<ByContainerCodeOrderLineSelector>> expected = ImmutableList.of(
        buildLine(1, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(7, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(4, TARGET_INFO_19_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116),
        buildLine(6, TARGET_INFO_19_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116),
        buildLine(3, TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(5, TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(8, TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(2, TARGET_INFO_20_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116)
    );

    List<OrderLineGrouperKey<OrderTarget, ByContainerCodeOrderLineSelector>> keyList = ImmutableList.of(
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116),
        OrderLineGrouperKey.of(TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        OrderLineGrouperKey.of(TARGET_INFO_20_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116)
    );

    Map<OrderLineGrouperKey<OrderTarget, ByContainerCodeOrderLineSelector>, List<Integer>> keysWithLines = ImmutableMap.<OrderLineGrouperKey<OrderTarget, ByContainerCodeOrderLineSelector>, List<Integer>>builder()
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
            ImmutableList.of(1, 7))
        .put(OrderLineGrouperKey.of(TARGET_INFO_19_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116),
            ImmutableList.of(4, 6))
        .put(OrderLineGrouperKey.of(TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
            ImmutableList.of(3, 5, 8))
        .put(OrderLineGrouperKey.of(TARGET_INFO_20_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116),
            ImmutableList.of(2))
        .build();

//		print(actual, expected,  grouper.getKeys());
    Assertions.assertTrue(assertKeyCount(keyList, grouper));
    Assertions.assertTrue(assertEquals(expected, actual));
    Assertions.assertTrue(assertKeysWithLines(keysWithLines, grouper));
  }

  @Test
  public void test_grouper_by_distribution_target() {
    ImmutableList<OrderLine> lines = ImmutableList.of(
        buildLine(1, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(2, TARGET_INFO_20_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116),
        buildLine(3, TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(4, TARGET_INFO_19_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116),
        buildLine(5, TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(6, TARGET_INFO_19_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116),
        buildLine(7, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(8, TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116)
    );

    OrderLineGrouper<DistributionTarget, ByContainerCodeOrderLineSelector> grouper = OrderLineGrouper.ofDistributionTarget();
    grouper.add(lines);

    List<OrderLine<ByContainerCodeOrderLineSelector>> actual = grouper.getAllLines();
    List<OrderLine<ByContainerCodeOrderLineSelector>> expected = ImmutableList.of(
        buildLine(1, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(4, TARGET_INFO_19_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116),
        buildLine(6, TARGET_INFO_19_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116),
        buildLine(7, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(2, TARGET_INFO_20_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116),
        buildLine(3, TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(5, TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(8, TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116)
    );

    List<OrderLineGrouperKey<DistributionTarget, ByContainerCodeOrderLineSelector>> keyList = ImmutableList.of(
        OrderLineGrouperKey.of(TARGET_19_3_1, CODE_SELECTOR_100000000000000000001116),
        OrderLineGrouperKey.of(TARGET_20_3_1, CODE_SELECTOR_100000000000000000001116)
    );

    Map<OrderLineGrouperKey<DistributionTarget, ByContainerCodeOrderLineSelector>, List<Integer>> keysWithLines = ImmutableMap.<OrderLineGrouperKey<DistributionTarget, ByContainerCodeOrderLineSelector>, List<Integer>>builder()
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, CODE_SELECTOR_100000000000000000001116),
            ImmutableList.of(1, 4, 6, 7))
        .put(OrderLineGrouperKey.of(TARGET_20_3_1, CODE_SELECTOR_100000000000000000001116),
            ImmutableList.of(2, 3, 5, 8))
        .build();

    //print(actual, expected,  grouper.getKeys());
    Assertions.assertTrue(assertKeyCount(keyList, grouper));
    Assertions.assertTrue(assertEquals(expected, actual));
    Assertions.assertTrue(assertKeysWithLines(keysWithLines, grouper));
  }

  @Test
  public void test_grouper_by_billing_location() {
    ImmutableList<OrderLine> lines = ImmutableList.of(
        buildLine(1, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(2, TARGET_INFO_20_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116),
        buildLine(3, TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(4, TARGET_INFO_19_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116),
        buildLine(5, TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(6, TARGET_INFO_19_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116),
        buildLine(7, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(8, TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116)
    );

    OrderLineGrouper<Integer, ByContainerCodeOrderLineSelector> grouper = OrderLineGrouper.ofBillingLocation();
    grouper.add(lines);

    List<OrderLine<ByContainerCodeOrderLineSelector>> actual = grouper.getAllLines();
    List<OrderLine<ByContainerCodeOrderLineSelector>> expected = ImmutableList.of(
        buildLine(1, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(3, TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(5, TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(7, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(8, TARGET_INFO_20_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(2, TARGET_INFO_20_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116),
        buildLine(4, TARGET_INFO_19_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116),
        buildLine(6, TARGET_INFO_19_3_1_FOR_96, CODE_SELECTOR_100000000000000000001116)
    );

    List<OrderLineGrouperKey<Integer, ByContainerCodeOrderLineSelector>> keyList = ImmutableList.of(
        OrderLineGrouperKey.of(LOCATION_93, CODE_SELECTOR_100000000000000000001116),
        OrderLineGrouperKey.of(LOCATION_96, CODE_SELECTOR_100000000000000000001116)
    );

    Map<OrderLineGrouperKey<Integer, ByContainerCodeOrderLineSelector>, List<Integer>> keysWithLines = ImmutableMap.<OrderLineGrouperKey<Integer, ByContainerCodeOrderLineSelector>, List<Integer>>builder()
        .put(OrderLineGrouperKey.of(LOCATION_93, CODE_SELECTOR_100000000000000000001116),
            ImmutableList.of(1, 3, 5, 7, 8))
        .put(OrderLineGrouperKey.of(LOCATION_96, CODE_SELECTOR_100000000000000000001116), ImmutableList.of(2, 4, 6))
        .build();

    //print(actual, expected,  grouper.getKeys());
    Assertions.assertTrue(assertKeyCount(keyList, grouper));
    Assertions.assertTrue(assertEquals(expected, actual));
    Assertions.assertTrue(assertKeysWithLines(keysWithLines, grouper));
  }

  @Test
  public void test_grouper_by_distribution_target_classic() {
    ImmutableList<OrderLine> lines = ImmutableList.of(
        buildLine(41, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(42, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2007_I),
        buildLine(43, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
        buildLine(44, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225),
        buildLine(45, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_05_00_00),
        buildLine(46, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
        buildLine(47, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_01),
        buildLine(48, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(49, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_LOC93),
        buildLine(10, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_02),
        buildLine(11, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_45_00_00),
        buildLine(12, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_01_00_00),
        buildLine(13, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(14, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00),
        buildLine(15, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(16, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_25_00_00),
        buildLine(17, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_03),
        buildLine(18, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(19, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225),
        buildLine(20, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
        buildLine(21, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_02_00_00),
        buildLine(22, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(23, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_03_00_00),
        buildLine(24, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
        buildLine(25, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_02_00_00),
        buildLine(26, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_02),
        buildLine(27, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(28, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(29, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00),
        buildLine(30, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_1963_I_CG1225_LOC93),
        buildLine(31, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(32, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_01_00_00),
        buildLine(33, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_04_00_00),
        buildLine(34, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_00_00_00),
        buildLine(35, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_03_00_00),
        buildLine(36, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_01_00_00),
        buildLine(37, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03)
    );

    OrderLineGrouper<DistributionTarget, OrderLineSelector> grouper = OrderLineGrouper.ofDistributionTarget();
    grouper.add(lines);

    List<OrderLine<OrderLineSelector>> actual = grouper.getAllLines();
    List<OrderLine<OrderLineSelector>> expected = ImmutableList.of(
        buildLine(28, TARGET_INFO_19_3_1_FOR_93, CODE_SELECTOR_100000000000000000001116),
        buildLine(30, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_1963_I_CG1225_LOC93),
        buildLine(49, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_LOC93),
        buildLine(44, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225),
        buildLine(19, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2009_V_CG1225),
        buildLine(42, TARGET_INFO_19_3_1_FOR_93, COMM_GROUP_SELECTOR_2007_I),
        buildLine(47, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_01),
        buildLine(26, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_02),
        buildLine(46, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
        buildLine(10, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_02),
        buildLine(18, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(20, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
        buildLine(22, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(17, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_03),
        buildLine(41, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(27, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(37, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03),
        buildLine(14, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00),
        buildLine(32, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_01_00_00),
        buildLine(16, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_25_00_00),
        buildLine(33, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_04_00_00),
        buildLine(34, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_00_00_00),
        buildLine(12, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_01_00_00),
        buildLine(25, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_02_00_00),
        buildLine(35, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_03_00_00),
        buildLine(43, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
        buildLine(48, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(13, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(15, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(24, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
        buildLine(29, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00),
        buildLine(31, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(45, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_05_00_00),
        buildLine(11, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_45_00_00),
        buildLine(21, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_02_00_00),
        buildLine(23, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_03_00_00),
        buildLine(36, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_01_00_00)
    );

    List<OrderLineGrouperKey<DistributionTarget, OrderLineSelector>> keyList = ImmutableList.of(
        OrderLineGrouperKey.of(TARGET_19_3_1, CODE_SELECTOR_100000000000000000001116),
        OrderLineGrouperKey.of(TARGET_19_3_1, COMM_GROUP_SELECTOR_1963_I_CG1225_LOC93),
        OrderLineGrouperKey.of(TARGET_19_3_1, COMM_GROUP_SELECTOR_2009_V_LOC93),
        OrderLineGrouperKey.of(TARGET_19_3_1, COMM_GROUP_SELECTOR_2009_V_CG1225),
        OrderLineGrouperKey.of(TARGET_19_3_1, COMM_GROUP_SELECTOR_2007_I),
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_01),
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_02),
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_02),
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_03),
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_03),
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_00),
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0100110025005_00_00_00),
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0639540082299_00_00_00),
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_00),
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_00_00_00)
    );

    Map<OrderLineGrouperKey<DistributionTarget, OrderLineSelector>, List<Integer>> keysWithLines = ImmutableMap.<OrderLineGrouperKey<DistributionTarget, OrderLineSelector>, List<Integer>>builder()
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, CODE_SELECTOR_100000000000000000001116), ImmutableList.of(28))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, COMM_GROUP_SELECTOR_1963_I_CG1225_LOC93), ImmutableList.of(30))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, COMM_GROUP_SELECTOR_2009_V_LOC93), ImmutableList.of(49))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, COMM_GROUP_SELECTOR_2009_V_CG1225), ImmutableList.of(44, 19))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, COMM_GROUP_SELECTOR_2007_I), ImmutableList.of(42))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_01), ImmutableList.of(47))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_02), ImmutableList.of(26))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_02),
            ImmutableList.of(46, 10, 18, 20, 22))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_03), ImmutableList.of(17))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_03), ImmutableList.of(41, 27, 37))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_00), ImmutableList.of(14, 32))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0100110025005_00_00_00), ImmutableList.of(16, 33, 34))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0639540082299_00_00_00), ImmutableList.of(12, 25, 35))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_00),
            ImmutableList.of(43, 48, 13, 15, 24, 29, 31))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_00_00_00),
            ImmutableList.of(45, 11, 21, 23, 36))
        .build();

//		print(actual, expected, grouper.getKeys());
    Assertions.assertTrue(assertKeyCount(keyList, grouper));
    Assertions.assertTrue(assertEquals(expected, actual));
    Assertions.assertTrue(assertKeysWithLines(keysWithLines, grouper));
  }

  @Test
  public void test_grouper_by_distribution_target_ignoring_good_status() {
    ImmutableList<OrderLine<ByReferenceOrderLineSelector>> lines = ImmutableList.of(
        buildLine(41, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(43, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
        buildLine(45, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_05_00_00),
        buildLine(46, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
        buildLine(47, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_01),
        buildLine(48, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(10, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_02),
        buildLine(11, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_45_00_00),
        buildLine(12, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_01_00_00),
        buildLine(13, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(14, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00),
        buildLine(15, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(16, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_25_00_00),
        buildLine(17, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_03),
        buildLine(18, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(20, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
        buildLine(21, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_02_00_00),
        buildLine(22, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(23, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_03_00_00),
        buildLine(24, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
        buildLine(25, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_02_00_00),
        buildLine(26, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_02),
        buildLine(27, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(29, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00),
        buildLine(31, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(32, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_01_00_00),
        buildLine(33, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_04_00_00),
        buildLine(34, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_00_00_00),
        buildLine(35, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_03_00_00),
        buildLine(36, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_01_00_00),
        buildLine(37, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03)
    );

    OrderLineGrouper<DistributionTarget, ByReferenceOrderLineSelector> grouper = OrderLineGrouper.ofDistributionTarget();
    grouper.add(lines);

    List<OrderLine<ByReferenceOrderLineSelector>> actual = grouper.getAllLines();
    List<OrderLine<ByReferenceOrderLineSelector>> expected = ImmutableList.of(
        buildLine(47, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_01),
        buildLine(26, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_02),
        buildLine(46, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
        buildLine(10, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_02),
        buildLine(18, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(20, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
        buildLine(22, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(17, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_03),
        buildLine(41, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(27, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(37, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03),
        buildLine(14, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00),
        buildLine(32, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_01_00_00),
        buildLine(16, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_25_00_00),
        buildLine(33, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_04_00_00),
        buildLine(34, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_00_00_00),
        buildLine(12, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_01_00_00),
        buildLine(25, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_02_00_00),
        buildLine(35, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_03_00_00),
        buildLine(43, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
        buildLine(48, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(13, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(15, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(24, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
        buildLine(29, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00),
        buildLine(31, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(45, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_05_00_00),
        buildLine(11, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_45_00_00),
        buildLine(21, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_02_00_00),
        buildLine(23, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_03_00_00),
        buildLine(36, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_01_00_00)
    );

    List<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>> keyList = ImmutableList.of(
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_01),
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_02),
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_02),
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_03),
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_03),
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_00),
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0100110025005_00_00_00),
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0639540082299_00_00_00),
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_00),
        OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_00_00_00)
    );

    Map<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>, List<Integer>> keysWithLines = ImmutableMap.<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>, List<Integer>>builder()
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_01), ImmutableList.of(47))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_02), ImmutableList.of(26))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_02),
            ImmutableList.of(46, 10, 18, 20, 22))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_03), ImmutableList.of(17))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_03), ImmutableList.of(41, 27, 37))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_00), ImmutableList.of(14, 32))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0100110025005_00_00_00), ImmutableList.of(16, 33, 34))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0639540082299_00_00_00), ImmutableList.of(12, 25, 35))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_00),
            ImmutableList.of(43, 48, 13, 15, 24, 29, 31))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_00_00_00),
            ImmutableList.of(45, 11, 21, 23, 36))
        .build();

//		print(actual, expected, grouper.getKeys());
    Assertions.assertTrue(assertKeyCount(keyList, grouper));
    Assertions.assertTrue(assertEquals(expected, actual));
    Assertions.assertTrue(assertKeysWithLines(keysWithLines, grouper));
  }

  @Test
  public void test_grouper_by_distribution_target_watching_how_to_group_by_good_status_When_goodStatus_is_NOT_in_the_key() {
    ImmutableList<OrderLine<ByReferenceOrderLineSelector>> lines = ImmutableList.of(
        buildLine(41, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(43, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
        buildLine(45, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_05_00_00),
        buildLine(46, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
        buildLine(47, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_01),
        buildLine(48, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(10, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_02),
        buildLine(11, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_45_00_00),
        buildLine(12, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_01_00_00),
        buildLine(13, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(14, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00),
        buildLine(15, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(16, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_25_00_00),
        buildLine(17, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_03),
        buildLine(18, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(20, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
        buildLine(21, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_02_00_00),
        buildLine(22, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(23, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_03_00_00),
        buildLine(24, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
        buildLine(25, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_02_00_00),
        buildLine(26, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_02),
        buildLine(27, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(29, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00),
        buildLine(31, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(32, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_01_00_00),
        buildLine(33, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_04_00_00),
        buildLine(34, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_00_00_00),
        buildLine(35, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_03_00_00),
        buildLine(36, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_01_00_00),
        buildLine(37, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03)
    );

    OrderLineGrouperByTSAandSku grouper = OrderLineGrouperByTSAandSku.of();
    grouper.add(lines);

    List<OrderLine<ByReferenceOrderLineSelector>> actual = grouper.getAllLines();
    List<OrderLine<ByReferenceOrderLineSelector>> expected = ImmutableList.of(
        buildLine(46, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
        buildLine(18, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(20, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
        buildLine(22, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(41, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(27, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(32, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_01_00_00),
        buildLine(12, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_01_00_00),
        buildLine(25, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_02_00_00),
        buildLine(35, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_03_00_00),
        buildLine(47, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_01),
        buildLine(26, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_02),
        buildLine(10, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_02),
        buildLine(17, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_03),
        buildLine(37, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03),
        buildLine(14, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00),
        buildLine(16, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_25_00_00),
        buildLine(33, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_04_00_00),
        buildLine(34, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_00_00_00),
        buildLine(43, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
        buildLine(48, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(13, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(15, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(24, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
        buildLine(29, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00),
        buildLine(31, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(45, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_05_00_00),
        buildLine(11, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_45_00_00),
        buildLine(21, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_02_00_00),
        buildLine(23, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_03_00_00),
        buildLine(36, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_01_00_00)
    );

    List<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>> keyList = ImmutableList.of(
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_02, ImmutableSet.of(3)),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_03, ImmutableSet.of(3)),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_01_00_00, ImmutableSet.of(1)),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0639540082299_01_00_00, ImmutableSet.of(1)),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0639540082299_02_00_00, ImmutableSet.of(2)),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0639540082299_03_00_00, ImmutableSet.of(3)),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_01, ImmutableSet.of()),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_02, ImmutableSet.of()),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_02, ImmutableSet.of()),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_03, ImmutableSet.of()),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_03, ImmutableSet.of()),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_00, ImmutableSet.of()),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0100110025005_00_00_00, ImmutableSet.of(2, 4, 5)),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_00, ImmutableSet.of(1, 3)),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_00_00_00,
            ImmutableSet.of(1, 2, 3, 4, 5))
    );

    Map<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>, List<Integer>> keysWithLines = ImmutableMap.<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>, List<Integer>>builder()
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_02),
            ImmutableList.of(46, 18, 20, 22))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_03), ImmutableList.of(41, 27))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_01_00_00), ImmutableList.of(32))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0639540082299_01_00_00), ImmutableList.of(12))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0639540082299_02_00_00), ImmutableList.of(25))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0639540082299_03_00_00), ImmutableList.of(35))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_01), ImmutableList.of(47))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_02), ImmutableList.of(26))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_02), ImmutableList.of(10))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_03), ImmutableList.of(17))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_03), ImmutableList.of(37))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_00), ImmutableList.of(14))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0100110025005_00_00_00), ImmutableList.of(16, 33, 34))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_00),
            ImmutableList.of(43, 48, 13, 15, 24, 29, 31))
        .put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_00_00_00),
            ImmutableList.of(45, 11, 21, 23, 36))
        .build();

//		print(actual, expected, grouper.getKeys());
    Assertions.assertTrue(assertKeyCount(keyList, grouper));
    Assertions.assertTrue(assertEquals(expected, actual));
    Assertions.assertTrue(assertKeysWithLines(keysWithLines, grouper));
  }

  @Test
  public void test_grouper_by_different_distribution_target_and_good_status_When_goodStatus_is_NOT_in_the_key() {
    ImmutableList<OrderLine<ByReferenceOrderLineSelector>> lines = ImmutableList.of(
        buildLine(16, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00),
        buildLine(20, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_00_00_00),
        buildLine(19, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03),
        buildLine(11, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0989020980201_45_00_00),
        buildLine(17, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(14, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(21, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_05_00_00),
        buildLine(22, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_01_00_00),
        buildLine(12, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0989020980201_00_00_00),
        buildLine(18, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(13, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0989020980201_02_00_00),
        buildLine(15, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00)
    );

    OrderLineGrouperByTSAandSku grouper = OrderLineGrouperByTSAandSku.of();
    grouper.add(lines);

    List<OrderLine<ByReferenceOrderLineSelector>> actual = grouper.getAllLines();
    List<OrderLine<ByReferenceOrderLineSelector>> expected = ImmutableList.of(
        buildLine(17, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(18, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(22, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_01_00_00),
        buildLine(21, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_05_00_00),
        buildLine(19, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03),
        buildLine(16, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00),
        buildLine(14, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(15, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
        buildLine(20, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_00_00_00),
        buildLine(11, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0989020980201_45_00_00),
        buildLine(12, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0989020980201_00_00_00),
        buildLine(13, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0989020980201_02_00_00)
    );

    List<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>> keyList = ImmutableList.of(
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_02, ImmutableSet.of(3)),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_03, ImmutableSet.of(3)),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_01_00_00, ImmutableSet.of(1)),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_05_00_00, ImmutableSet.of(5)),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_03, ImmutableSet.of()),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_20_3_1, REF_SELECTOR_0888888980032_00_00_00, ImmutableSet.of(1, 3)),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_00_00_00, ImmutableSet.of()),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_20_3_1, REF_SELECTOR_0989020980201_00_00_00, ImmutableSet.of(2, 4, 5))
    );

    Map<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>, List<Integer>> keysWithLines = ImmutableMap.<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>, List<Integer>>builder()
        .put(OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_02),
            ImmutableList.of(17))
        .put(OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_03),
            ImmutableList.of(18))
        .put(OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_01_00_00),
            ImmutableList.of(22))
        .put(OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_05_00_00),
            ImmutableList.of(21))
        .put(OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_03),
            ImmutableList.of(19))
        .put(OrderLineGrouperByTSAandSkuKey.of(TARGET_20_3_1, REF_SELECTOR_0888888980032_00_00_00),
            ImmutableList.of(16, 14, 15))
        .put(OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_00_00_00),
            ImmutableList.of(20))
        .put(OrderLineGrouperByTSAandSkuKey.of(TARGET_20_3_1, REF_SELECTOR_0989020980201_00_00_00),
            ImmutableList.of(11, 12, 13))
        .build();

//		print(actual, expected, grouper.getKeys());
    Assertions.assertTrue(assertKeyCount(keyList, grouper));
    Assertions.assertTrue(assertEquals(expected, actual));
    Assertions.assertTrue(assertKeysWithLines(keysWithLines, grouper));
  }

  @Test
  public void test_grouper_by_different_good_status_and_uxl_When_goodStatus_is_NOT_in_the_key() {
    ImmutableList<OrderLine<ByReferenceOrderLineSelector>> lines = ImmutableList.of(
        buildLine(11, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00),
        buildLine(18, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03),
        buildLine(13, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_02),
        buildLine(14, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(16, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_02),
        buildLine(17, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(15, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
        buildLine(12, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(19, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_03)
    );

    OrderLineGrouperByTSAandSku grouper = OrderLineGrouperByTSAandSku.of();
    grouper.add(lines);

    List<OrderLine<ByReferenceOrderLineSelector>> actual = grouper.getAllLines();
    List<OrderLine<ByReferenceOrderLineSelector>> expected = ImmutableList.of(
        buildLine(14, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
        buildLine(16, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_02),
        buildLine(15, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
        buildLine(12, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
        buildLine(13, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_02),
        buildLine(18, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03),
        buildLine(17, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
        buildLine(19, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_03),
        buildLine(11, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00)
    );

    List<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>> keyList = ImmutableList.of(
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_02, ImmutableSet.of(3)),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_00, ImmutableSet.of(3)),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_02, ImmutableSet.of()),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_03, ImmutableSet.of(1, 3)),
        OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_00, ImmutableSet.of())
    );

    Map<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>, List<Integer>> keysWithLines = ImmutableMap.<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>, List<Integer>>builder()
        .put(OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_02),
            ImmutableList.of(14, 16, 15))
        .put(OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_00),
            ImmutableList.of(12))
        .put(OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_02),
            ImmutableList.of(13))
        .put(OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_03),
            ImmutableList.of(18, 17, 19))
        .put(OrderLineGrouperByTSAandSkuKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_00),
            ImmutableList.of(11))
        .build();

//		print(actual, expected, grouper.getKeys());
    Assertions.assertTrue(assertKeyCount(keyList, grouper));
    Assertions.assertTrue(assertEquals(expected, actual));
    Assertions.assertTrue(assertKeysWithLines(keysWithLines, grouper));
  }

//	@Test
//	public void test_grouper_by_distribution_target_watching_how_to_group_by_good_status_When_goodStatus_is_in_the_key() {
//		ImmutableList<OrderLine<ByReferenceOrderLineSelector>> lines = ImmutableList.of(
//				buildLine(41, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
//				buildLine(43, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
//				buildLine(45, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_05_00_00),
//				buildLine(46, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
//				buildLine(47, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_01),
//				buildLine(48, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
//				buildLine(10, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_02),
//				buildLine(11, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_45_00_00),
//				buildLine(12, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_01_00_00),
//				buildLine(13, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
//				buildLine(14, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00),
//				buildLine(15, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
//				buildLine(16, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_25_00_00),
//				buildLine(17, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_03),
//				buildLine(18, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
//				buildLine(20, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
//				buildLine(21, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_02_00_00),
//				buildLine(22, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
//				buildLine(23, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_03_00_00),		
//				buildLine(24, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
//				buildLine(25, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_02_00_00),
//				buildLine(26, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_02),
//				buildLine(27, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
//				buildLine(29, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00),
//				buildLine(31, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
//				buildLine(32, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_01_00_00),
//				buildLine(33, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_04_00_00),
//				buildLine(34, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_00_00_00),
//				buildLine(35, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_03_00_00),
//				buildLine(36, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_01_00_00),
//				buildLine(37, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03)
//		);
//		
//		OrderLineGrouperByTSAandSku grouper = OrderLineGrouperByTSAandSku.of();
//		grouper.add(lines);
//		
//		List<OrderLine<ByReferenceOrderLineSelector>> actual = grouper.getAllLines();
//		List<OrderLine<ByReferenceOrderLineSelector>> expected = ImmutableList.of(
//				buildLine(46, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
//				buildLine(18, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
//				buildLine(20, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
//				buildLine(22, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
//				buildLine(41, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
//				buildLine(27, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
//				buildLine(45, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_05_00_00),
//				buildLine(11, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_45_00_00),
//				buildLine(21, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_02_00_00),
//				buildLine(23, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_03_00_00),	
//				buildLine(36, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_01_00_00),
//				buildLine(16, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_25_00_00),
//				buildLine(33, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_04_00_00),
//				buildLine(34, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0100110025005_00_00_00),
//				buildLine(43, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
//				buildLine(48, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
//				buildLine(13, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
//				buildLine(15, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
//				buildLine(24, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
//				buildLine(29, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00),
//				buildLine(31, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),				
//				buildLine(32, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_01_00_00),
//				buildLine(12, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_01_00_00),
//				buildLine(25, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_02_00_00),
//				buildLine(35, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0639540082299_03_00_00),
//				buildLine(47, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_01),
//				buildLine(26, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_02),
//				buildLine(10, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_02),
//				buildLine(17, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_03),
//				buildLine(37, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03),
//				buildLine(14, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0045841892299_00_00_00)
//				);
//		
//		List<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>> keyList = ImmutableList.of(
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_02),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_03),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_13245_00_00),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0100110025005_245_00_00),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_13_00_00),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_01_00_00),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0639540082299_01_00_00),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0639540082299_02_00_00),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0639540082299_03_00_00),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_01),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_02),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_02),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_03),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_03),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_00)
//				);
//		
//		Map<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>, List<Integer>> keysWithLines = ImmutableMap.<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>, List<Integer>> builder()
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_02),	ImmutableList.of(46, 18, 20, 22))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_03),	ImmutableList.of(41, 27))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_13245_00_00), ImmutableList.of(45, 11, 21, 23, 36))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0100110025005_245_00_00),	ImmutableList.of(16, 33, 34))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_13_00_00),	ImmutableList.of(43, 48, 13, 15, 24, 29, 31))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_01_00_00),	ImmutableList.of(32))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0639540082299_01_00_00),	ImmutableList.of(12))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0639540082299_02_00_00),	ImmutableList.of(25))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0639540082299_03_00_00),	ImmutableList.of(35))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_01),	ImmutableList.of(47))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_02),	ImmutableList.of(26))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_02),	ImmutableList.of(10))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_03),	ImmutableList.of(17))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_03),	ImmutableList.of(37))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0045841892299_00_00_00),	ImmutableList.of(14))
//				.build();
//		
////		print(actual, expected, grouper.getKeys());
//		Assertions.assertTrue(assertKeyCount(keyList, grouper));
//		Assertions.assertTrue(assertEquals(expected, actual));
//		Assertions.assertTrue(assertKeysWithLines(keysWithLines, grouper));
//	}
//	
//	@Test
//	public void test_grouper_by_different_distribution_target_and_good_status_When_goodStatus_is_in_the_key() {
//		ImmutableList<OrderLine<ByReferenceOrderLineSelector>> lines = ImmutableList.of(
//				buildLine(16, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00),
//				buildLine(20, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_00_00_00),
//				buildLine(19, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03), 
//				buildLine(11, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0989020980201_45_00_00),
//				buildLine(17, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
//				buildLine(14, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
//				buildLine(21, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_05_00_00),
//				buildLine(22, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_01_00_00),
//				buildLine(12, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0989020980201_00_00_00),
//				buildLine(18, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
//				buildLine(13, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0989020980201_02_00_00),
//				buildLine(15, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00)
//				);
//		
//		OrderLineGrouperByTSAandSku grouper = OrderLineGrouperByTSAandSku.of();
//		grouper.add(lines);
//		
//		List<OrderLine<ByReferenceOrderLineSelector>> actual = grouper.getAllLines();
//		List<OrderLine<ByReferenceOrderLineSelector>> expected = ImmutableList.of(
//				buildLine(17, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
//				buildLine(18, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
//				buildLine(11, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0989020980201_45_00_00),
//				buildLine(12, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0989020980201_00_00_00),
//				buildLine(13, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0989020980201_02_00_00),
//				buildLine(16, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00),
//				buildLine(14, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
//				buildLine(15, TARGET_INFO_20_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_00),
//				buildLine(22, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_01_00_00),
//				buildLine(21, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_05_00_00),
//				buildLine(19, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03),
//				buildLine(20, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0989020980201_00_00_00)
//				);
//		
//		List<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>> keyList = ImmutableList.of(
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_02),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_03),
//				OrderLineGrouperKey.of(TARGET_20_3_1, REF_SELECTOR_0989020980201_245_00_00),
//				OrderLineGrouperKey.of(TARGET_20_3_1, REF_SELECTOR_0888888980032_13_00_00),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_01_00_00),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_05_00_00),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_03),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_00_00_00)
//				);
//		
//		Map<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>, List<Integer>> keysWithLines = ImmutableMap.<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>, List<Integer>> builder()
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_02),	ImmutableList.of(17))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_03),	ImmutableList.of(18))
//				.put(OrderLineGrouperKey.of(TARGET_20_3_1, REF_SELECTOR_0989020980201_245_00_00),	ImmutableList.of(11, 12, 13))
//				.put(OrderLineGrouperKey.of(TARGET_20_3_1, REF_SELECTOR_0888888980032_13_00_00),	ImmutableList.of(16, 14, 15))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_01_00_00),	ImmutableList.of(22))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_05_00_00),	ImmutableList.of(21))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_03),	ImmutableList.of(19))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0989020980201_00_00_00),	ImmutableList.of(20))
//				.build();
//		
////		print(actual, expected, grouper.getKeys());
//		Assertions.assertTrue(assertKeyCount(keyList, grouper));
//		Assertions.assertTrue(assertEquals(expected, actual));
//		Assertions.assertTrue(assertKeysWithLines(keysWithLines, grouper));
//	}
//	
//	@Test
//	public void test_grouper_by_different_good_status_and_uxl_When_goodStatus_is_in_the_key() {
//		ImmutableList<OrderLine<ByReferenceOrderLineSelector>> lines = ImmutableList.of(
//				buildLine(11, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00),
//				buildLine(18, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03),
//				buildLine(13, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_02),
//				buildLine(14, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
//				buildLine(16, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_02),
//				buildLine(17, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
//				buildLine(15, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
//				buildLine(12, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
//				buildLine(19, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_03) 
//				);
//		
//		OrderLineGrouperByTSAandSku grouper = OrderLineGrouperByTSAandSku.of();
//		grouper.add(lines);
//		
//		List<OrderLine<ByReferenceOrderLineSelector>> actual = grouper.getAllLines();
//		List<OrderLine<ByReferenceOrderLineSelector>> expected = ImmutableList.of(
//				buildLine(18, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_03),
//				buildLine(17, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_03),
//				buildLine(19, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_13_00_03), 
//				buildLine(14, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NE_02),
//				buildLine(16, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_02),
//				buildLine(15, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_NO_02),
//				buildLine(12, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_03_00_00),
//				buildLine(13, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_02),
//				buildLine(11, TARGET_INFO_19_3_1_FOR_93, REF_SELECTOR_0888888980032_00_00_00)
//				);
//		
//		List<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>> keyList = ImmutableList.of(
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_13_00_03),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_02),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_00),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_02),
//				OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_00)
//				);
//		
//		Map<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>, List<Integer>> keysWithLines = ImmutableMap.<OrderLineGrouperKey<DistributionTarget, ByReferenceOrderLineSelector>, List<Integer>> builder()
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_13_00_03),	ImmutableList.of(18, 17, 19))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_02),	ImmutableList.of(14, 16, 15))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_03_00_00),	ImmutableList.of(12))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_02),	ImmutableList.of(13))
//				.put(OrderLineGrouperKey.of(TARGET_19_3_1, REF_SELECTOR_0888888980032_00_00_00),	ImmutableList.of(11))
//				.build();
//		
////		print(actual, expected, grouper.getKeys());
//		Assertions.assertTrue(assertKeyCount(keyList, grouper));
//		Assertions.assertTrue(assertEquals(expected, actual));
//		Assertions.assertTrue(assertKeysWithLines(keysWithLines, grouper));
//	}

  private <S extends OrderLineSelector, K extends LineGrouperKey<?, ?>> void print(List<OrderLine<S>> actual,
      List<OrderLine<S>> expected, Collection<K> keyList) {
    System.out.println("\nKEYLIST");
    keyList.forEach(value -> System.out.println(value));
    System.out.println("\nEXPECTED:");
    expected.forEach(value -> System.out.println(value));
    System.out.println("\nACTUAL:");
    actual.forEach(value -> System.out.println(value));
  }

  @SuppressWarnings("unchecked")
  private <S extends OrderLineSelector> OrderLine<S> buildLine(long id, OrderTarget target, S selector) {
    return OrderLine.builder(target, selector)
        .reference(id)
        .orderType((OrderType<S>) ToLocationByReferenceOrderType.of())
        .build();
  }

  private static final ByReferenceOrderLineSelector referenceSelector(String garmentRef, Set<Integer> goodsStatusCodes,
      Integer unitsPerLot) {
    return ByReferenceOrderLineSelector.builder()
        .garmentRef(garmentRef)
        .goodsStatusOriginal(goodsStatusCodes)
        .unitsPerLot(Optional.ofNullable(unitsPerLot))
        .build();
  }

  private <S extends OrderLineSelector> boolean assertEquals(List<OrderLine<S>> expected, List<OrderLine<S>> actual) {
    if (expected.size() != actual.size()) {
      System.err.println("Longitud diferente: expected.size()= " + expected.size());
      System.err.println("Longitud diferente: actual.size()= " + actual.size());
      return false;
    }

    for (int i = 0; i < expected.size(); i++) {
      OrderTarget expectedTarget = expected.get(i).getTarget();
      OrderLineSelector expectedSelector = expected.get(i).getSelector();
      OrderTarget actualTarget = actual.get(i).getTarget();
      OrderLineSelector actualSelector = actual.get(i).getSelector();

      boolean validation = true;

      if (!expectedSelector.equals(actualSelector)) {
        System.err.println("Selector diferente: expectedSelector= " + expectedSelector);
        System.err.println("Selector diferente: actualSelector= " + actualSelector);
        validation = false;
      }
      if (!expectedTarget.equals(actualTarget)) {
        System.err.println("Selector diferente: expectedTarget= " + expectedTarget);
        System.err.println("Selector diferente: actualTarget= " + actualTarget);
        validation = false;
      }
      if (!validation) {
        System.err.println("Elemento con indice= " + i);
        System.err.println("Expected orderLine= " + expected.get(i));
        System.err.println("Actual orderLine= " + actual.get(i));
        return false;
      }
    }
    return true;
  }

  private <K extends LineGrouperKey, S extends OrderLineSelector> boolean assertKeyCount(List<K> expectedKeyList,
      LineGrouperWithPriority<K, OrderLine<S>> grouper) {
    if (grouper.getKeys().size() != expectedKeyList.size()) {
      System.err.println("Longitud diferente: grouper.getKeys().size()= " + grouper.getKeys().size());
      System.err.println("Longitud diferente: expectedKeyList.size()= " + expectedKeyList.size());
      return false;
    }
    int i = 0;
    for (K current : grouper.getKeys()) {
      if (!current.equals(expectedKeyList.get(i))) {
        System.err.println("Clave diferente: current= " + current);
        System.err.println("Clave diferente: keyList.get(i)= " + expectedKeyList.get(i));
        System.err.println("Elemento con indice= " + i);
        return false;
      }
      i++;
    }
    return true;
  }

  private <K extends LineGrouperKey, S extends OrderLineSelector> boolean assertKeysWithLines(
      Map<K, List<Integer>> keysWithLines, LineGrouperWithPriority<K, OrderLine<S>> grouper) {

    for (Entry<K, List<Integer>> current : keysWithLines.entrySet()) {
      List<OrderLine<S>> lines = grouper.getLines(current.getKey());
      if ((lines == null) || lines.isEmpty()) {
        return false;
      }

      List<Long> idsFromGrouper = lines.stream().map(value -> value.getReference())
          .collect(ImmutableList.toImmutableList());
      List<Long> idsFromTest = current.getValue().stream().map(value -> Long.valueOf(value))
          .collect(ImmutableList.toImmutableList());

      if (!idsFromTest.equals(idsFromGrouper)) {
        System.err.println("Clave recupera diferentes ids: current.getKey()= " + current.getKey());
        System.err.println("Clave diferente: idsFromTest= " + idsFromTest);
        System.err.println("Clave diferente: idsFromGrouper= " + idsFromGrouper);
        return false;
      }
    }

    return true;
  }

}

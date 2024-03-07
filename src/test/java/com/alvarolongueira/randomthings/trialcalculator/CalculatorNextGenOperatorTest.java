package com.alvarolongueira.randomthings.trialcalculator;

import com.alvarolongueira.randomthings.trialcalculator.answer.CalculatorNextGenOperator;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeOperator;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeSegment;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeValue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorNextGenOperatorTest {

  private final CalculatorNextGenOperator operator = new CalculatorNextGenOperator();

  @Test
  public void worksMultiply() {
    List<NodeSegment> list = new ArrayList<>();
    list.add(NodeValue.of(3));
    list.add(NodeOperator.of("*"));
    list.add(NodeValue.of(4));

    int result = this.operator.calculate(list);
    Assertions.assertEquals(12, result);
  }

  @Test
  public void worksSum() {
    List<NodeSegment> list = new ArrayList<>();
    list.add(NodeValue.of(2));
    list.add(NodeOperator.of("+"));
    list.add(NodeValue.of(3));

    int result = this.operator.calculate(list);
    Assertions.assertEquals(5, result);
  }

  @Test
  public void worksInOrder() {
    List<NodeSegment> list = new ArrayList<>();
    list.add(NodeValue.of(2));
    list.add(NodeOperator.of("+"));
    list.add(NodeValue.of(3));
    list.add(NodeOperator.of("*"));
    list.add(NodeValue.of(4));

    int result = this.operator.calculate(list);
    Assertions.assertEquals(14, result);
  }

  @Test
  public void worksInOrderComplex() {
//        2 + 3 * 4 * 2 * 3 + 1 * 3
//        2 + (((3 * 4) * 2) * 3) + (1 * 3)
//        2 + 72 + 3 = 77

    List<NodeSegment> list = new ArrayList<>();
    list.add(NodeValue.of(2));
    list.add(NodeOperator.of("+"));
    list.add(NodeValue.of(3));
    list.add(NodeOperator.of("*"));
    list.add(NodeValue.of(4));
    list.add(NodeOperator.of("*"));
    list.add(NodeValue.of(2));
    list.add(NodeOperator.of("*"));
    list.add(NodeValue.of(3));
    list.add(NodeOperator.of("+"));
    list.add(NodeValue.of(1));
    list.add(NodeOperator.of("*"));
    list.add(NodeValue.of(3));

    int result = this.operator.calculate(list);
    Assertions.assertEquals(77, result);
  }

  @Test
  public void notKnowSubtract() {
    List<NodeSegment> list = new ArrayList<>();
    list.add(NodeValue.of(2));
    list.add(NodeOperator.of("-"));
    list.add(NodeValue.of(3));
    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
      this.operator.calculate(list);
    });
  }

  @Test
  public void receiveTwoValue() {
    List<NodeSegment> list = new ArrayList<>();
    list.add(NodeValue.of(2));
    list.add(NodeOperator.of("*"));
    list.add(NodeValue.of(3));
    list.add(NodeOperator.of("+"));
    list.add(NodeValue.of(4));
    list.add(NodeValue.of(5));
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.operator.calculate(list);
    });
  }

  @Test
  public void receiveTwoOperator() {
    List<NodeSegment> list = new ArrayList<>();
    list.add(NodeValue.of(2));
    list.add(NodeOperator.of("*"));
    list.add(NodeValue.of(3));
    list.add(NodeOperator.of("+"));
    list.add(NodeOperator.of("+"));
    list.add(NodeValue.of(4));
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.operator.calculate(list);
    });
  }

  @Test
  public void startsWithOperator() {
    List<NodeSegment> list = new ArrayList<>();
    list.add(NodeOperator.of("+"));
    list.add(NodeValue.of(2));
    list.add(NodeOperator.of("*"));
    list.add(NodeValue.of(3));
    list.add(NodeOperator.of("+"));
    list.add(NodeValue.of(4));
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.operator.calculate(list);
    });
  }

  @Test
  public void endsWithOperator() {
    List<NodeSegment> list = new ArrayList<>();
    list.add(NodeValue.of(2));
    list.add(NodeOperator.of("*"));
    list.add(NodeValue.of(3));
    list.add(NodeOperator.of("+"));
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.operator.calculate(list);
    });
  }
}

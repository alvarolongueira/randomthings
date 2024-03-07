package com.alvarolongueira.randomthings.trialcalculator;

import com.alvarolongueira.randomthings.trialcalculator.answer.CalculatorNextGenOperator;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeOperator;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeSegment;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeValue;
import com.alvarolongueira.randomthings.trialcalculator.answer.operation.Operation;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorNextGenOperatorTestWithNewOperation {

  @Test
  public void notKnowSubtract() {
    CalculatorNextGenOperator operator = new CalculatorNextGenOperator();
    List<NodeSegment> list = new ArrayList<>();
    list.add(NodeValue.of(2));
    list.add(NodeOperator.of("-"));
    list.add(NodeValue.of(3));
    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
      operator.calculate(list);
    });
  }

  @Test
  public void knowsSubtract() {
    CalculatorNextGenOperator operator = new CalculatorNextGenOperator();
    operator.addOperation(new TestOperationSubstract());
    List<NodeSegment> list = new ArrayList<>();
    list.add(NodeValue.of(2));
    list.add(NodeOperator.of("-"));
    list.add(NodeValue.of(3));

    int result = operator.calculate(list);
    Assertions.assertEquals(-1, result);
  }

  private class TestOperationSubstract extends Operation {

    @Override
    public NodeOperator getOperator() {
      return NodeOperator.of("-");
    }

    @Override
    public NodeValue calculate(NodeValue value1, NodeValue value2) {
      return NodeValue.of(value1.get() - value2.get());
    }
  }
}

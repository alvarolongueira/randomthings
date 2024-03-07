package com.alvarolongueira.randomthings.trialcalculator;

import com.alvarolongueira.randomthings.trialcalculator.answer.CalculatorNextGenOperator;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeOperator;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeSegment;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeValue;
import com.alvarolongueira.randomthings.trialcalculator.questions.Node;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

  @Test
  public void calculator_previous() {
    // 2 + 3 * 4
    Node times = new Node("*", 0);
    Node three = new Node(null, 3);
    Node four = new Node(null, 4);

    // first 3 * 4
    times.setLeftNode(three);
    times.setRightNode(four);

    // then 2 + result above
    Node two = new Node(null, 2);
    Node plus = new Node("+", 0);

    plus.setLeftNode(two);
    plus.setRightNode(times);

    int result = plus.calculate();
    Assertions.assertEquals(14, result);
  }

  @Test
  public void calculator_nextGen() {
    // 2 + 3 * 4
    List<NodeSegment> list = new ArrayList<>();
    list.add(NodeValue.of(2));
    list.add(NodeOperator.of("+"));
    list.add(NodeValue.of(3));
    list.add(NodeOperator.of("*"));
    list.add(NodeValue.of(4));
    CalculatorNextGenOperator operator = new CalculatorNextGenOperator();

    int result = operator.calculate(list);
    Assertions.assertEquals(14, result);
  }
}

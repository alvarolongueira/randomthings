package com.alvarolongueira.randomthings.trialcalculator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alvarolongueira.randomthings.trialcalculator.answer.CalculatorNextGenOperator;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeOperator;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeSegment;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeValue;
import com.alvarolongueira.randomthings.trialcalculator.answer.operation.Operation;

public class CalculatorNextGenOperatorTestWithNewOperation {

    @Test(expected = UnsupportedOperationException.class)
    public void notKnowSubtract() {
        CalculatorNextGenOperator operator = new CalculatorNextGenOperator();
        List<NodeSegment> list = new ArrayList<>();
        list.add(NodeValue.of(2));
        list.add(NodeOperator.of("-"));
        list.add(NodeValue.of(3));
        operator.calculate(list);
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
        Assert.assertEquals(-1, result);
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

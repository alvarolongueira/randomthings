package com.alvarolongueira.randomthings.trialcalculator.answer.operation;


import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeOperator;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeValue;

public class OperationSum extends Operation {

    @Override
    public NodeOperator getOperator() {
        return NodeOperator.of("+");
    }

    @Override
    public NodeValue calculate(NodeValue value1, NodeValue value2) {
        return NodeValue.of(value1.get() + value2.get());
    }

}

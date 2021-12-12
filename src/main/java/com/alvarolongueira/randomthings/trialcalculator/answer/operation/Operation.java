package com.alvarolongueira.randomthings.trialcalculator.answer.operation;

import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeOperator;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeValue;

public abstract class Operation {

    public abstract NodeOperator getOperator();

    public abstract NodeValue calculate(NodeValue value1, NodeValue value2);

}

package com.alvarolongueira.randomthings.trialcalculator.answer.operation;


import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeOperator;

public class OperationFactory {

    private static final OperationFactory INSTANCE = new OperationFactory();

    public static OperationFactory get() {
        return INSTANCE;
    }

    private final Map<NodeOperator, Operation> map = new HashMap<>();
    private final Set<NodeOperator> sequence = new LinkedHashSet<>();

    private OperationFactory() {
        Operation sum = new OperationSum();
        Operation multiply = new OperationMultiply();

        this.map.put(sum.getOperator(), sum);
        this.map.put(multiply.getOperator(), multiply);

        this.sequence.add(multiply.getOperator());
        this.sequence.add(sum.getOperator());
    }

    public void addOperation(Operation operation) {
        this.map.put(operation.getOperator(), operation);
        this.sequence.add(operation.getOperator());
    }

    public Operation getOperation(NodeOperator operator) {
        Operation operation = this.map.get(operator);
        if (operation != null) {
            return operation;
        }
        throw new UnsupportedOperationException("Operation not defined for: " + operator);
    }

    public Set<NodeOperator> getSequence() {
        return this.sequence;
    }

    public void redefineSequence(List<NodeOperator> operators) {
        this.sequence.clear();
        operators.stream().forEach(current -> this.sequence.add(current));
    }

}

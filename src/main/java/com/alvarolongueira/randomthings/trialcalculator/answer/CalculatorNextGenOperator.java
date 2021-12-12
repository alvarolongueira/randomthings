package com.alvarolongueira.randomthings.trialcalculator.answer;


import java.util.ArrayList;
import java.util.List;

import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeOperator;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeSegment;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeType;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeValue;
import com.alvarolongueira.randomthings.trialcalculator.answer.operation.Operation;
import com.alvarolongueira.randomthings.trialcalculator.answer.operation.OperationFactory;

public class CalculatorNextGenOperator {

    public void addOperation(Operation operation) {
        OperationFactory factory = OperationFactory.get();
        factory.addOperation(operation);
    }

    public int calculate(List<NodeSegment> list) {
        if (list.isEmpty()) {
            return 0;
        }

        this.validate(list);

        final int result = 0;

        do {
            list = this.doMaths(list);
        } while (!list.isEmpty() && list.size() > 1);

        if (!list.isEmpty()) {
            NodeSegment node = list.get(0);

            if (!node.getType().equals(NodeType.VALUE)) {
                throw new IllegalArgumentException("Error during operation");
            }
            return ((NodeValue) node).get();
        }

        return result;
    }

    private void validate(List<NodeSegment> list) {
        NodeType type = NodeType.VALUE;
        NodeSegment last = null;

        for (NodeSegment node : list) {
            if (!node.getType().equals(type)) {
                throw new IllegalArgumentException("List is not well built");
            }
            if (node.getType().equals(NodeType.OPERATOR)) {
                OperationFactory.get().getOperation((NodeOperator) node);
            }
            type = this.nextNodeType(type);
            last = node;
        }

        if (last.getType().equals(NodeType.OPERATOR)) {
            throw new IllegalArgumentException("List is not well builded");
        }
    }

    private NodeType nextNodeType(NodeType nodeType) {
        return nodeType.equals(NodeType.OPERATOR) ? NodeType.VALUE : NodeType.OPERATOR;
    }

    private List<NodeSegment> doMaths(List<NodeSegment> list) {
        List<NodeSegment> newList = new ArrayList<>();
        OperationFactory factory = OperationFactory.get();

        for (NodeOperator operator : factory.getSequence()) {

            if (list.contains(operator)) {
                for (int i = 0; i < list.size(); i++) {

                    NodeSegment node = list.get(i);
                    if (!node.equals(operator)) {
                        newList.add(node);

                    } else {
                        newList.remove(i - 1);

                        NodeValue previous = ((NodeValue) list.get(i - 1));
                        NodeValue next = ((NodeValue) list.get(i + 1));
                        Operation operation = factory.getOperation(operator);
                        NodeValue value = operation.calculate(previous, next);

                        newList.add(value);
                        newList.addAll(this.getRest(list, i + 2));
                        return newList;
                    }
                }
            }
        }

        return newList;
    }

    private List<NodeSegment> getRest(List<NodeSegment> list, int index) {
        List<NodeSegment> newList = new ArrayList<>();
        for (int i = index; i < list.size(); i++) {
            newList.add(list.get(i));
        }
        return newList;
    }

}

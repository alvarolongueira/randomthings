package com.alvarolongueira.randomthings.trialcalculator.answer.node;

import java.util.Objects;

public class NodeOperator extends NodeSegment {

    private final String operator;

    private NodeOperator(String operator) {
        this.operator = operator;
    }

    public static NodeOperator of(String operator) {
        return new NodeOperator(operator);
    }

    public String get() {
        return this.operator;
    }

    @Override
    public NodeType getType() {
        return NodeType.OPERATOR;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof NodeOperator)) {
            return false;
        }
        NodeOperator newObject = (NodeOperator) object;
        return newObject.get() == this.get();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.operator);
    }

    @Override
    public String toString() {
        return "NodeOperator{" + this.operator + '}';
    }
}

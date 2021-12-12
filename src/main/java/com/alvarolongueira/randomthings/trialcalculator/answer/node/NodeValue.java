package com.alvarolongueira.randomthings.trialcalculator.answer.node;

public class NodeValue extends NodeSegment {

    private final int value;

    private NodeValue(int value) {
        this.value = value;
    }

    public static NodeValue of(int value) {
        return new NodeValue(value);
    }

    public int get() {
        return this.value;
    }

    @Override
    public NodeType getType() {
        return NodeType.VALUE;
    }

    @Override
    public String toString() {
        return "NodeValue{" + +this.value + '}';
    }
}

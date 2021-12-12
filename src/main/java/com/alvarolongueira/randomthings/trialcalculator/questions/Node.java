package com.alvarolongueira.randomthings.trialcalculator.questions;

public class Node {
    private String operator;
    private int value;
    private Node leftNode;
    private Node rightNode;

    public int calculate() {
        if ("+".equals(this.operator)) {
            return this.leftNode.calculate()+ this.rightNode.calculate();
        }
        if ("*".equals(this.operator)) {
            return this.leftNode.calculate()* this.rightNode.calculate();
        }
        return this.value;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Node getLeftNode() {
        return this.leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return this.rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public Node(String operator, int value) {
        this.operator = operator;
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

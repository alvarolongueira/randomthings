package com.alvarolongueira.randomthings.trialcalculator.answer.exampleAddOperation;


import java.util.ArrayList;
import java.util.List;

import com.alvarolongueira.randomthings.trialcalculator.answer.CalculatorNextGenOperator;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeOperator;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeSegment;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeValue;

public class ExampleAddOperation {

    public static void main(String... args) {
        ExampleAddOperation example = new ExampleAddOperation();
        example.run();
    }

    private void run() {
        List<NodeSegment> list = new ArrayList<>();
        list.add(NodeValue.of(2));
        list.add(NodeOperator.of("-"));
        list.add(NodeValue.of(3));
        CalculatorNextGenOperator operator = new CalculatorNextGenOperator();

        try {
            System.out.println(operator.calculate(list));
        } catch (UnsupportedOperationException e) {
            System.err.println("Operation undefined for " + list);
        }

        operator.addOperation(new OperationSubtract());
        System.out.println("I know how to substract");
        System.out.println(operator.calculate(list));
    }

}

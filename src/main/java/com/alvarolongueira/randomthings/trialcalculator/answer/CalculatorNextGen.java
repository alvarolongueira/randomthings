package com.alvarolongueira.randomthings.trialcalculator.answer;


import java.util.ArrayList;
import java.util.List;

import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeOperator;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeSegment;
import com.alvarolongueira.randomthings.trialcalculator.answer.node.NodeValue;

public class CalculatorNextGen {

    public static void main(String... args) {
        CalculatorNextGen calculatorNextGen = new CalculatorNextGen();
        calculatorNextGen.run();
    }

    private void run() {
        List<NodeSegment> list = new ArrayList<>();
        list.add(NodeValue.of(2));
        list.add(NodeOperator.of("+"));
        list.add(NodeValue.of(3));
        list.add(NodeOperator.of("*"));
        list.add(NodeValue.of(4));
        CalculatorNextGenOperator operator = new CalculatorNextGenOperator();
        System.out.println(operator.calculate(list));
    }

}

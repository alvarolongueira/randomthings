package com.alvarolongueira.randomthings.trialcalculator.questions;

public class Calculator {

    public static void main(String... args) {
        Calculator c = new Calculator();
        c.run();
    }

    private void run() {
        // 2 + 3 * 4
        Node times = new Node("*", 0);
        Node three = new Node(null, 3);
        Node four = new Node(null, 4);

        // first 3 * 4
        times.setLeftNode(three);
        times.setRightNode(four);

        // then 2 + result above
        Node two = new Node(null, 2);
        Node plus = new Node("+", 0);

        plus.setLeftNode(two);
        plus.setRightNode(times);
        System.out.println(plus.calculate());
    }
}

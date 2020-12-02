package com.chenjj.base.enum1;

public enum Operation {
    PLUS {
        @Override
        public double eval(double x, double y) {
            return x + y;
        }
    }, MINUS {
        @Override
        public double eval(double x, double y) {
            return x - y;
        }
    }, TIMES {
        @Override
        public double eval(double x, double y) {
            return x * y;
        }
    }, DIVIDE {
        @Override
        public double eval(double x, double y) {
            return x / y;
        }
    };

    public abstract double eval(double x, double y);

    public static void main(String[] args) {
        System.out.println(Operation.PLUS.eval(1, 2));
    }
}

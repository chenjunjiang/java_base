package com.chenjj.base.enum1;

/**
 * 枚举中的 switch 语旬适合给外部的枚举类型(比如：Operation)增加特定于常量
 * 的行为 。例如，假设 Operation 枚举不受你的控制，你希望它有一个实例方法来返回每个
 * 运算的反运算 。
 */
public class Inverse {
    public static Operation inverse(Operation op) {
        switch (op) {
            case PLUS:
                return Operation.MINUS;
            case MINUS:
                return Operation.PLUS;
            case TIMES:
                return Operation.DIVIDE;
            case DIVIDE:
                return Operation.TIMES;

            default:
                throw new AssertionError("Unknown op: " + op);
        }
    }

    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        for (Operation op : Operation.values()) {
            Operation invOp = inverse(op);
            System.out.printf("%f %s %f %s %f = %f%n",
                    x, op, y, invOp, y, invOp.apply(op.apply(x, y), y));
        }
    }
}

package com.chenjj.base.enum1;

/**
 * 根据某工人的基本工资（按小时）以及当天的工作时间 ，来计算他当天的报酬 。 在五个工
 * 作日中，超过正常八小时的工作时间都会产生加班工资 ；在节假日中，所有工作都产生加班
 * 工资 。
 * 这样设计会有一个问题：假设将一个元素添加到该枚举中，或许是一个表示假期天数的特殊值，
 * 但是忘记给 switch 语句添加相应的case 。 程序依然可以编译，
 * 但pay方法会悄悄地将节假日的工资计算成正常工作日的工资 。
 * 我们真正想要的就是每当添加一个枚举常量时，就强制选择一种加班报酬策略。幸运
 * 的是，有一种很好的方法可以实现这一点 。 这种想法就是将加班工资计算移到一个私有的嵌
 * 套枚举中，将这个策略枚举（ strategy enum ）的实例传到PayrollDay枚举的构造器中 。 之
 * 后 PayrollDay枚举将加班工资计算委托给策略枚举，PayrollDay中就不需要switch
 * 语句或者特定于常量的方法实现了。参见：PayrollDay1
 */
public enum PayrollDay {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    private static final int MINS_PER_SHIFT = 8 * 60;

    int pay(int minutesWorked, int payRate) {
        int basePay = minutesWorked * payRate;
        int overtimePay;
        switch (this) {
            case SATURDAY:
            case SUNDAY:
                overtimePay = basePay;
                break;
            default:
                overtimePay = minutesWorked <= MINS_PER_SHIFT ? 0 : (minutesWorked - MINS_PER_SHIFT) * payRate / 2;
        }
        return basePay + overtimePay;
    }
}

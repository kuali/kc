package org.kuali.coeus.s2sgen.impl.budget;

public enum BudgetPeriodNum {
    P1(1),P2(2),P3(3),P4(4),P5(5);

    private final int num;

    BudgetPeriodNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}

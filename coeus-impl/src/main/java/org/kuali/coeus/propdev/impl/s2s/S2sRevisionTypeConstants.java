package org.kuali.coeus.propdev.impl.s2s;

public final class S2sRevisionTypeConstants {
    public static final String INCREASE_AWARD = "A";
    public static final String INCREASE_AWARD_INCREASE_DURATION = "AC";
    public static final String INCREASE_AWARD_DECREASE_DURATION = "AD";
    public static final String DECREASE_AWARD = "B";
    public static final String DECREASE_AWARD_INCREASE_DURATION = "BC";
    public static final String DECREASE_AWARD_DECREASE_DURATION = "BD";
    public static final String INCREASE_DURATION = "C";
    public static final String DECREASE_DURATION = "D";
    public static final String OTHER = "E";

    private S2sRevisionTypeConstants() {
        throw new UnsupportedOperationException("do not call");
    }
}

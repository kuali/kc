/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget;

import java.math.BigDecimal;

import org.kuali.core.util.KualiDecimal;

/**
 * This class extends from KualiDecimal. Its being used in all proposal budget calculations
 */
public class BudgetDecimal extends KualiDecimal{

    private static final int SCALE = 3;
    public static final BudgetDecimal ZERO = new BudgetDecimal(0);
    public BudgetDecimal(String value) {
        super(value);
    }

    public BudgetDecimal(int value) {
        this(value + "");
    }

    public BudgetDecimal(double value) {
        this(value + "");
    }

    public BudgetDecimal(BigDecimal value) {
        this(value.toPlainString());
    }
    public static void main(String args[]){
        System.out.print(new BudgetDecimal("12312341234.1212321").toString());
    }

    /**
     * Wraps BigDecimal's add method to accept and return KualiDecimal instances
     * instead of BigDecimals, so that users of the class don't have to typecast
     * the return value.
     * 
     * @param addend
     * @return result of adding the given addend to this value
     * @throws IllegalArgumentException
     *             if the given addend is null
     */
    public BudgetDecimal add(KualiDecimal addend) {
        return new BudgetDecimal(super.add(addend).bigDecimalValue());
    }

    /**
     * Wraps BigDecimal's subtract method to accept and return KualiDecimal
     * instances instead of BigDecimals, so that users of the class don't have
     * to typecast the return value.
     * 
     * @param subtrahend
     * @return result of the subtracting the given subtrahend from this value
     * @throws IllegalArgumentException
     *             if the given subtrahend is null
     */
    public BudgetDecimal subtract(KualiDecimal subtrahend) {
        return new BudgetDecimal(super.subtract(subtrahend).bigDecimalValue());
    }

    /**
     * Wraps BigDecimal's multiply method to accept and return KualiDecimal
     * instances instead of BigDecimals, so that users of the class don't have
     * to typecast the return value.
     * 
     * @param multiplicand
     * @return result of multiplying this value by the given multiplier
     * @throws IllegalArgumentException
     *             if the given multiplier is null
     */
    public BudgetDecimal multiply(KualiDecimal multiplier) {
        return new BudgetDecimal(super.multiply(multiplier).bigDecimalValue());
    }

    /**
     * This method calculates the mod between to KualiDecimal values by first
     * casting to doubles and then by performing the % operation on the two
     * primitives.
     * 
     * @param modulus
     *            The other value to apply the mod to.
     * @return result of performing the mod calculation
     * @throws IllegalArgumentException
     *             if the given modulus is null
     */
    public BudgetDecimal mod(KualiDecimal modulus) {
        return new BudgetDecimal(super.mod(modulus).bigDecimalValue());
    }

    /**
     * Wraps BigDecimal's divide method to enforce the default Kuali rounding
     * behavior
     * 
     * @param divisor
     * @return result of dividing this value by the given divisor
     * @throws an
     *             IllegalArgumentException if the given divisor is null
     */
    public BudgetDecimal divide(KualiDecimal divisor) {
        return new BudgetDecimal(super.divide(divisor).bigDecimalValue());
    }

    /**
     * Wraps BigDecimal's setScale method to enforce the default Kuali rounding
     * behavior.
     * 
     * @return KualiDecimal instance set to the given scale, rounded with the
     *         default rounding behavior (if necessary)
     */
    public BudgetDecimal setScale(int scale) {
        return new BudgetDecimal(super.setScale(scale).bigDecimalValue());
    }

    /**
     * Simplified wrapper over the setScale() method, this one has no arguments.
     * When used with no arguments, it defaults to the Kuali default Scale and
     * Rounding.
     * 
     * @return a rounded, scaled, KualiDecimal
     */
    public BudgetDecimal setScale() {
        return setScale(SCALE);
    }

    /**
     * @return a KualiDecimal with the same scale and a negated value (iff the
     *         value is non-zero)
     */
    public BudgetDecimal negated() {
        return multiply(new BudgetDecimal("-1"));
    }

    /**
     * @return a KualiDecimal with the same scale and the absolute value
     */
    public BudgetDecimal abs() {
        BudgetDecimal absolute = null;

        if (isNegative()) {
            absolute = negated();
        } else {
            absolute = this;
        }

        return absolute;
    }

    public BudgetDecimal percentage(BudgetDecimal rate) {
        return multiply(rate).divide(new BudgetDecimal(100));
    }
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        boolean equals = false;

        if (obj instanceof BudgetDecimal) {
            BudgetDecimal k = (BudgetDecimal) obj;

            // using KualiDecimal.compareTo instead of BigDecimal.equals since
            // BigDecimal.equals only returns true if the
            // scale and precision are equal, rather than comparing the actual
            // (scaled) values
            equals = (this.compareTo(k) == 0);
        }

        return equals;
    }
}

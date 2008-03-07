/*
 * Copyright 2005-2006 The Kuali Foundation.
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

import org.apache.commons.lang.StringUtils;

/**
 * This class is a wrapper around java.math.BigDecimal. It exposes the only the
 * needed functionality of BigDecimal and uses a standard SCALE of 2 and
 * ROUND_BEHAVIOR of BigDecimal.ROUND_HALF_UP
 * 
 * Members of this class are, like BigDecimal, immutable; even methods which
 * might be expected to change the value (like setScale, for example) actually
 * just return a new instance with the new value.
 */
public class BudgetDecimal extends Number implements Comparable {
    public static final BudgetDecimal ZERO = new BudgetDecimal(0);

    // Default rounding behavior is Banker's Rounding. This means that
    // it rounds towards the "nearest neighbor" unless both neighbors
    // are equidistant, in which case, round towards the even neighbor.
    public static final int ROUND_BEHAVIOR = BigDecimal.ROUND_HALF_UP;

    public static final int SCALE = 2;

    private final BigDecimal value;
    

    /**
     * This is the base constructor, used by constructors that take other types
     * 
     * @param value
     *            String containing numeric value
     * @throws IllegalArgumentException
     *             if the given String is null
     */
    public BudgetDecimal(String value,boolean applyScale) {
        if (value == null) {
            throw new IllegalArgumentException(
                    "invalid (null) String in BudgetDecimal constructor");
        }
        this.value = applyScale?new BigDecimal(value).setScale(SCALE, ROUND_BEHAVIOR):new BigDecimal(value);
    }
    public BudgetDecimal(String value) {
        this(value,true);
    }
    public BudgetDecimal(String value,int scale) {
        if (value == null) {
            throw new IllegalArgumentException(
                    "invalid (null) String in BudgetDecimal constructor");
        }
        this.value = new BigDecimal(value).setScale(scale, ROUND_BEHAVIOR);
    }
    public BudgetDecimal(int value) {
        this.value = new BigDecimal(value).setScale(SCALE, ROUND_BEHAVIOR);
    }

    public BudgetDecimal(double value) {
        this.value = new BigDecimal(value).setScale(SCALE, ROUND_BEHAVIOR);
    }

    public BudgetDecimal(BigDecimal value) {
        this(value.toPlainString());
    }
    public BudgetDecimal(int value,boolean applyScale) {
        this.value = applyScale?new BigDecimal(value).setScale(SCALE, ROUND_BEHAVIOR):new BigDecimal(value);
    }

    public BudgetDecimal(double value,boolean applyScale) {
        this.value = applyScale?new BigDecimal(value).setScale(SCALE, ROUND_BEHAVIOR):new BigDecimal(value);
    }

    public BudgetDecimal(BigDecimal value,boolean applyScale) {
        this(value.toPlainString(),applyScale);
    }

    /**
     * @param operand
     * @return true if this BudgetDecimal is less than the given BudgetDecimal
     */
    public boolean isLessThan(BudgetDecimal operand) {
        if (operand == null) {
            throw new IllegalArgumentException("invalid (null) operand");
        }

        return (this.compareTo(operand) == -1);
    }

    /**
     * @param operand
     * @return true if this BudgetDecimal is greater than the given BudgetDecimal
     */
    public boolean isGreaterThan(BudgetDecimal operand) {
        if (operand == null) {
            throw new IllegalArgumentException("invalid (null) operand");
        }

        return (this.compareTo(operand) == 1);
    }

    /**
     * @param operand
     * @return true if this BudgetDecimal is less than or equal to the given
     *         BudgetDecimal
     */
    public boolean isLessEqual(BudgetDecimal operand) {
        if (operand == null) {
            throw new IllegalArgumentException("invalid (null) operand");
        }

        return !isGreaterThan(operand);
    }

    /**
     * @param operand
     * @return true if this BudgetDecimal is greater than or equal to the given
     *         BudgetDecimal
     */
    public boolean isGreaterEqual(BudgetDecimal operand) {
        if (operand == null) {
            throw new IllegalArgumentException("invalid (null) operand");
        }

        return !isLessThan(operand);
    }

    /**
     * @return true if this BudgetDecimal is less than zero
     */
    public boolean isNegative() {
        return (this.compareTo(ZERO) == -1);
    }

    /**
     * @return true if this BudgetDecimal is greater than zero
     */
    public boolean isPositive() {
        return (this.compareTo(ZERO) == 1);
    }

    /**
     * @return true if this BudgetDecimal is equal to zero
     */
    public boolean isZero() {
        return (this.compareTo(ZERO) == 0);
    }

    /**
     * @return true if this BudgetDecimal is not equal to zero
     */
    public boolean isNonZero() {
        return !this.isZero();
    }

    /**
     * Wraps BigDecimal's add method to accept and return BudgetDecimal instances
     * instead of BigDecimals, so that users of the class don't have to typecast
     * the return value.
     * 
     * @param addend
     * @return result of adding the given addend to this value
     * @throws IllegalArgumentException
     *             if the given addend is null
     */
    public BudgetDecimal add(BudgetDecimal addend) {
        return add(addend,false);
    }

    public BudgetDecimal add(BudgetDecimal addend,boolean applyScale) {
        if (addend == null) {
            throw new IllegalArgumentException("invalid (null) addend");
        }

        BigDecimal sum = this.value.add(addend.value);
        return new BudgetDecimal(sum,applyScale);
    }
    /**
     * Wraps BigDecimal's subtract method to accept and return BudgetDecimal
     * instances instead of BigDecimals, so that users of the class don't have
     * to typecast the return value.
     * 
     * @param subtrahend
     * @return result of the subtracting the given subtrahend from this value
     * @throws IllegalArgumentException
     *             if the given subtrahend is null
     */
    public BudgetDecimal subtract(BudgetDecimal subtrahend,boolean applyScale) {
        if (subtrahend == null) {
            throw new IllegalArgumentException("invalid (null) subtrahend");
        }

        BigDecimal difference = this.value.subtract(subtrahend.value);
        return new BudgetDecimal(difference,applyScale);
    }
    public BudgetDecimal subtract(BudgetDecimal subtrahend) {
        return subtract(subtrahend,false);
    }

    /**
     * Wraps BigDecimal's multiply method to accept and return BudgetDecimal
     * instances instead of BigDecimals, so that users of the class don't have
     * to typecast the return value.
     * 
     * @param multiplicand
     * @return result of multiplying this value by the given multiplier
     * @throws IllegalArgumentException
     *             if the given multiplier is null
     */
    public BudgetDecimal multiply(BudgetDecimal multiplier,boolean applyScale) {
        if (multiplier == null) {
            throw new IllegalArgumentException("invalid (null) multiplier");
        }

        BigDecimal product = this.value.multiply(multiplier.value);
        return new BudgetDecimal(product,applyScale);
    }

    public BudgetDecimal multiply(BudgetDecimal multiplier) {
        return multiply(multiplier, false);
    }
    /**
     * This method calculates the mod between to BudgetDecimal values by first
     * casting to doubles and then by performing the % operation on the two
     * primitives.
     * 
     * @param modulus
     *            The other value to apply the mod to.
     * @return result of performing the mod calculation
     * @throws IllegalArgumentException
     *             if the given modulus is null
     */
    public BudgetDecimal mod(BudgetDecimal modulus) {
        if (modulus == null) {
            throw new IllegalArgumentException("invalid (null) modulus");
        }

        double difference = this.value.doubleValue() % modulus.doubleValue();

        return new BudgetDecimal(new BigDecimal(difference).setScale(SCALE,
                BigDecimal.ROUND_UNNECESSARY));
    }

    public BudgetDecimal mod(BudgetDecimal modulus,boolean applyScale) {
        if (modulus == null) {
            throw new IllegalArgumentException("invalid (null) modulus");
        }

        double difference = this.value.doubleValue() % modulus.doubleValue();

        return new BudgetDecimal(new BigDecimal(difference),applyScale);
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
    public BudgetDecimal divide(BudgetDecimal divisor) {
        return divide(divisor,false);
    }

    public BudgetDecimal divide(BudgetDecimal divisor,boolean applyScale) {
        if (divisor == null) {
            throw new IllegalArgumentException("invalid (null) divisor");
        }

        BigDecimal quotient = this.value.divide(divisor.value, ROUND_BEHAVIOR);
        return new BudgetDecimal(quotient,applyScale);
    }
    
    public void main(String[] str){
        BudgetDecimal op1 = new BudgetDecimal(100);
        BudgetDecimal op2 = new BudgetDecimal(365);
        System.out.println(op1.divide(op2));
        
    }

    /**
     * Wraps BigDecimal's setScale method to enforce the default Kuali rounding
     * behavior.
     * 
     * @return BudgetDecimal instance set to the given scale, rounded with the
     *         default rounding behavior (if necessary)
     */
    public BudgetDecimal setScale(int scale) {
        BigDecimal scaled = this.value.setScale(scale, ROUND_BEHAVIOR);
        return new BudgetDecimal(scaled);
    }

    /**
     * Simplified wrapper over the setScale() method, this one has no arguments.
     * When used with no arguments, it defaults to the Kuali default Scale and
     * Rounding.
     * 
     * @return a rounded, scaled, BudgetDecimal
     */
    public BudgetDecimal setScale() {
        return setScale(SCALE);
    }

    /**
     * @return a BudgetDecimal with the same scale and a negated value (iff the
     *         value is non-zero)
     */
    public BudgetDecimal negated() {
        return multiply(new BudgetDecimal("-1"));
    }

    /**
     * @return a BudgetDecimal with the same scale and the absolute value
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

    /**
     * @return true if the given String can be used to construct a valid
     *         BudgetDecimal
     */
    public static boolean isNumeric(String s) {
        boolean isValid = false;

        if (!StringUtils.isBlank(s)) {
            try {
                new BudgetDecimal(s);
                isValid = true;
            } catch (NumberFormatException e) {
            }
        }

        return isValid;
    }

    // Number methods
    /**
     * @see java.lang.Number#doubleValue()
     */
    @Override
    public double doubleValue() {
        return this.value.doubleValue();
    }

    /**
     * @see java.lang.Number#floatValue()
     */
    @Override
    public float floatValue() {
        return this.value.floatValue();
    }

    /**
     * @see java.lang.Number#intValue()
     */
    @Override
    public int intValue() {
        return this.value.intValue();
    }

    /**
     * @see java.lang.Number#longValue()
     */
    @Override
    public long longValue() {
        return this.value.longValue();
    }

    /**
     * @return the value of this instance as a BigDecimal.
     */
    public BigDecimal bigDecimalValue() {
        return this.value;
    }

    // Comparable methods
    /**
     * Compares this BudgetDecimal with the specified Object. If the Object is a
     * BudgetDecimal, this method behaves like
     * java.lang.Comparable#compareTo(java.lang.Object).
     * 
     * Otherwise, it throws a <tt>ClassCastException</tt> (as BudgetDecimals
     * are comparable only to other BudgetDecimals).
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object o) {
        return compareTo((BudgetDecimal) o);
    }

    /**
     * Returns the result of comparing the values of this BudgetDecimal and the
     * given BudgetDecimal.
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(BudgetDecimal k) {
        return this.value.compareTo(k.value);
    }

    // Object methods
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        boolean equals = false;

        if (obj instanceof BudgetDecimal) {
            BudgetDecimal k = (BudgetDecimal) obj;

            // using BudgetDecimal.compareTo instead of BigDecimal.equals since
            // BigDecimal.equals only returns true if the
            // scale and precision are equal, rather than comparing the actual
            // (scaled) values
            equals = (this.compareTo(k) == 0);
        }

        return equals;
    }
    public BudgetDecimal percentage(BudgetDecimal rate) {
        return multiply(rate).divide(new BudgetDecimal(100));
    }

    /**
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.value.toString();
    }
}

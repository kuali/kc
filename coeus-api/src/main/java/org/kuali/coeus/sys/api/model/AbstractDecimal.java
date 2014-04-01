package org.kuali.coeus.sys.api.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class is a wrapper around {@link java.math.BigDecimal}. It exposes the only the
 * needed functionality of {@link java.math.BigDecimal} and uses a standard
 * {@link java.math.RoundingMode} of {@link java.math.RoundingMode#HALF_UP}.
 *
 * Children this class should be, like {@link java.math.BigDecimal}, immutable; even methods which
 * might be expected to change the value actually just return a new instance
 * with the new value.
 */
public abstract class AbstractDecimal<T extends AbstractDecimal> extends Number implements Comparable<T> {
    public static final RoundingMode ROUND_BEHAVIOR = RoundingMode.HALF_UP;

    protected final BigDecimal value;

    /**
     * This constructor should never be called except during JAXB unmarshalling.
     */
    protected AbstractDecimal() {
        value = null;
    }

    /**
     * This is the base constructor, used by constructors that take other types
     *
     * @param value String containing numeric value - defaults to zero
     */
    protected AbstractDecimal(String value, int scale) {
        if (value == null || value.trim().equals("")) {
            this.value = BigDecimal.ZERO.setScale(scale, ROUND_BEHAVIOR);
        } else {
            this.value = new BigDecimal(value).setScale(scale, ROUND_BEHAVIOR);
        }
    }

    protected AbstractDecimal(int value, int scale) {
        this.value = BigDecimal.valueOf(value).setScale(scale, ROUND_BEHAVIOR);
    }

    protected AbstractDecimal(double value, int scale) {
        this.value = BigDecimal.valueOf(value).setScale(scale, ROUND_BEHAVIOR);
    }

    protected AbstractDecimal(BigDecimal value, int scale) {
        this.value = value.setScale(scale, ROUND_BEHAVIOR);
    }

    /**
     * @param operand the operand to compare against.  cannot be null
     * @return true if this AbstractDecimal is less than the given
     *         AbstractDecimal
     * @throws IllegalArgumentException if operand is null
     */
    public boolean isLessThan(T operand) {
        if (operand == null) {
            throw new IllegalArgumentException("invalid (null) operand");
        }

        return (this.compareTo(operand) == -1);
    }

    /**
     * @param operand the operand to compare against.  cannot be null
     * @return true if this AbstractDecimal is greater than the given
     *         AbstractDecimal
     * @throws IllegalArgumentException if operand is null
     */
    public boolean isGreaterThan(T operand) {
        if (operand == null) {
            throw new IllegalArgumentException("invalid (null) operand");
        }

        return (this.compareTo(operand) == 1);
    }

    /**
     * @param operand the operand to compare against.  cannot be null
     * @return true if this AbstractDecimal is less than or equal to the
     *         given AbstractDecimal
     * @throws IllegalArgumentException if operand is null
     */
    public boolean isLessEqual(T operand) {
        if (operand == null) {
            throw new IllegalArgumentException("invalid (null) operand");
        }

        return !isGreaterThan(operand);
    }

    /**
     * @param operand the operand to compare against.  cannot be null
     * @return true if this AbstractDecimal is greater than or equal to the
     *         given AbstractDecimal
     * @throws IllegalArgumentException if operand is null
     */
    public boolean isGreaterEqual(T operand) {
        if (operand == null) {
            throw new IllegalArgumentException("invalid (null) operand");
        }

        return !isLessThan(operand);
    }

    /**
     * @return true if the given String can be used to construct a valid
     *         AbstractDecimal
     * @throws IllegalArgumentException if operand is null
     */
    public static boolean isNumeric(String s) {
        boolean isValid = false;

        if (!(s == null || s.trim().equals(""))) {
            try {
                new BigDecimal(s);
                isValid = true;
            } catch (NumberFormatException e) {
            }
        }

        return isValid;
    }

    @Override
    public double doubleValue() {
        return this.value.doubleValue();
    }

    @Override
    public float floatValue() {
        return this.value.floatValue();
    }

    @Override
    public int intValue() {
        return this.value.intValue();
    }

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

    /**
     * Returns the result of comparing the values of this {@link T}
     * and the given {@link T}.
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(T k) {
        return this.value.compareTo(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = false;

        if (obj instanceof AbstractDecimal) {
            T k = (T) obj;

            // using AbstractScaleTwoDecimal.compareTo instead of BigDecimal.equals
            // since
            // BigDecimal.equals only returns true if the
            // scale and precision are equal, rather than comparing the actual
            // (scaled) values
            equals = (this.compareTo(k) == 0);
        }

        return equals;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    /**
     * @return true if this T is less than zero
     */
    public boolean isNegative() {
        return (this.compareTo(zero()) == -1);
    }

    /**
     * @return true if this T is greater than zero
     */
    public boolean isPositive() {
        return (this.compareTo(zero()) == 1);
    }

    /**
     * @return true if this T is equal to zero
     */
    public boolean isZero() {
        return (this.compareTo(zero()) == 0);
    }

    /**
     * @return a T with the same scale and the absolute value
     */
    public T abs() {
        final T absolute;

        if (isNegative()) {
            absolute = negated();
        } else {
            absolute = newInstance(this.value, this.value.scale());
        }

        return absolute;
    }

    /**
     * @return true if this T is not equal to zero
     */
    public boolean isNonZero() {
        return !this.isZero();
    }

    /**
     * Wraps BigDecimal's add method to accept and return T instances instead of
     * BigDecimals, so that users of the class don't have to typecast the return
     * value.
     *
     * @param addend the value to add
     * @return result of adding the given addend to this value
     * @throws IllegalArgumentException if the given addend is null
     */
    public T add(T addend) {
        if (addend == null) {
            throw new IllegalArgumentException("invalid (null) addend");
        }

        BigDecimal sum = this.value.add(addend.value);
        return newInstance(sum, sum.scale());
    }

    /**
     * Wraps BigDecimal's subtract method to accept and return T instances
     * instead of BigDecimals, so that users of the class don't have to typecast
     * the return value.
     *
     * @param subtrahend the value to subtract
     * @return result of the subtracting the given subtrahend from this value
     * @throws IllegalArgumentException if the given subtrahend is null
     */
    public T subtract(T subtrahend) {
        if (subtrahend == null) {
            throw new IllegalArgumentException("invalid (null) subtrahend");
        }

        BigDecimal difference = this.value.subtract(subtrahend.value);
        return newInstance(difference, difference.scale());
    }

    /**
     * Wraps BigDecimal's multiply method to accept and return T instances
     * instead of BigDecimals, so that users of the class don't have to typecast
     * the return value.
     *
     * @param multiplier the value to multiply
     * @return result of multiplying this value by the given multiplier
     * @throws IllegalArgumentException if the given multiplier is null
     */
    public T multiply(T multiplier) {
        if (multiplier == null) {
            throw new IllegalArgumentException("invalid (null) multiplier");
        }

        BigDecimal product = this.value.multiply(multiplier.value);
        return newInstance(product, this.value.scale());
    }

    /**
     * This method calculates the mod between to T values by first casting to
     * doubles and then by performing the % operation on the two primitives.
     *
     * @param modulus
     *            The other value to apply the mod to.
     * @return result of performing the mod calculation
     * @throws IllegalArgumentException if the given modulus is null
     */
    public T mod(T modulus) {
        if (modulus == null) {
            throw new IllegalArgumentException("invalid (null) modulus");
        }
        double difference = this.value.doubleValue() % modulus.doubleValue();
        return newInstance(BigDecimal.valueOf(difference), this.value.scale());
    }

    /**
     * Wraps BigDecimal's divide method to enforce the default rounding
     * behavior
     *
     * @param divisor the value to divide by
     * @return result of dividing this value by the given divisor
     * @throws IllegalArgumentException if the given divisor is null
     */
    public T divide(T divisor) {
        if (divisor == null) {
            throw new IllegalArgumentException("invalid (null) divisor");
        }
        BigDecimal quotient = this.value.divide(divisor.value, ROUND_BEHAVIOR);

        return newInstance(quotient, this.value.scale());
    }

    /**
     * @return a T with the same scale and a negated value (iff the value is
     *         non-zero)
     */
    public T negated() {
        return multiply(newInstance(-1));
    }

    public T percentage(T rate) {
        return (T) multiply(rate).divide(oneHundred());
    }

    protected abstract T newInstance(int value);

    protected abstract T newInstance(BigDecimal value, int scale);

    protected abstract T zero();

    protected abstract T oneHundred();
}

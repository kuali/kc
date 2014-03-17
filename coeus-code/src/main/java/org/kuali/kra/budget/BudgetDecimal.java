/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget;

import org.kuali.rice.core.api.util.type.AbstractKualiDecimal;

import java.math.BigDecimal;

/**
 * This class is a wrapper around java.math.BigDecimal. It exposes the only the
 * needed functionality of BigDecimal and uses a standard SCALE of 2 and
 * ROUND_BEHAVIOR of BigDecimal.ROUND_HALF_UP
 * 
 * Members of this class are, like BigDecimal, immutable; even methods which
 * might be expected to change the value (like setScale, for example) actually
 * just return a new instance with the new value.
 */
public class BudgetDecimal extends AbstractKualiDecimal<BudgetDecimal> {

    private static final long serialVersionUID = 1602860735060812811L;

    public static final int SCALE = 2;

    public static final BudgetDecimal ZERO = new BudgetDecimal(0);
    
    /**
     * This is the base constructor, used by constructors that take other types
     * 
     * @param value
     *            String containing numeric value
     * @throws IllegalArgumentException
     *             if the given String is null
     */
    public BudgetDecimal(String value) {
        super(value, SCALE);
    }

    public BudgetDecimal(int value) {
        super(value, SCALE);
    }

    public BudgetDecimal(double value) {
        super(value, SCALE);
    }

    public BudgetDecimal(BigDecimal value) {
        super(value, SCALE);
    }

    protected BudgetDecimal(String value, int scale) {
        super(value, scale);
    }

    protected BudgetDecimal(int value, int scale) {
        super(value, scale);
    }

    protected BudgetDecimal(double value, int scale) {
        super(value, scale);
    }

    protected BudgetDecimal(BigDecimal value, int scale) {
        super(value, scale);
    }
    
//    public BudgetDecimal(double value,boolean applyScale) {
//        BigDecimal bigDecimalDouble = new BigDecimal(value);
//        applyScale?new BudgetDecimal(value,SCALE):
//            new BudgetDecimal(bigDecimalDouble,bigDecimalDouble.scale());
//    }

    public BudgetDecimal percentage(BudgetDecimal rate){
        return multiply(rate).divide(new BudgetDecimal(100)).setScale();
    }
    public BudgetDecimal setScale() {
        return setScale(SCALE);
    }

    public BudgetDecimal setScale(int scale) {
        return new BudgetDecimal(bigDecimalValue().setScale(scale,BigDecimal.ROUND_HALF_UP));
    }
    @Override
    public BudgetDecimal divide(BudgetDecimal divisor){
        return super.divide(divisor, false);
    }
    @Override
    public BudgetDecimal multiply(BudgetDecimal multiplier){
        return super.multiply(multiplier, false);
    }
    @Override
    protected BudgetDecimal newInstance(String value) {
        return new BudgetDecimal(value);
    }

    @Override
    protected BudgetDecimal newInstance(double value) {
        return new BudgetDecimal(value);
    }

    @Override
    protected BudgetDecimal newInstance(double value, int scale) {
        return new BudgetDecimal(value, scale);
    }  

    @Override
    protected BudgetDecimal newInstance(BigDecimal value) {
        return new BudgetDecimal(value);
    }

    @Override
    protected BudgetDecimal newInstance(BigDecimal value, int scale) {
        return new BudgetDecimal(value, scale);
    }
    /**
     * return ZERO if the object is null
     * @param BudgetDecimal
     */
    public static BudgetDecimal returnZeroIfNull(BudgetDecimal value){
        return value==null?BudgetDecimal.ZERO:value;
    }
    
    /**
     * 
     * This method calls the floatValue() funciton.  It is needed so the TAGs can call this function.
     * @return
     */
    public float getFloatValue() {
        return this.floatValue();
    }
}

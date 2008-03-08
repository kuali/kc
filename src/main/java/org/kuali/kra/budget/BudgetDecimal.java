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

import org.kuali.core.util.AbstractKualiDecimal;

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
        return multiply(rate).divide(new BudgetDecimal(100));
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
}

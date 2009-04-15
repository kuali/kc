/*
 * Copyright 2006-2009 The Kuali Foundation
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

import java.math.BigDecimal;

import org.kuali.core.util.AbstractKualiDecimal;

/**
 * This class works like BudgetDecimal, but has a preset SCALE of 3
 */
public class RateDecimal extends AbstractKualiDecimal<RateDecimal> {
    private static final long serialVersionUID = -1132481837308782665L;
    
    public static final int SCALE = 3;
    public static final RateDecimal ZERO_RATE = new RateDecimal(0.000);
    
    public RateDecimal(BigDecimal value) {
        super(value, SCALE);
    }

    public RateDecimal(double value) {
        super(value, SCALE);
    }

    public RateDecimal(int value) {
        super(value, SCALE);
    }

    public RateDecimal(String value) {
        super(value, SCALE);
    }

    protected RateDecimal(BigDecimal value, int scale) {
        super(value, scale);
    }

    protected RateDecimal(double value, int scale) {
        super(value, scale);
    }

    protected RateDecimal(int value, int scale) {
        super(value, scale);
    }

    protected RateDecimal(String value, int scale) {
        super(value, scale);
    }

    @Override
    protected RateDecimal newInstance(String value) {
        return new RateDecimal(value);
    }

    @Override
    protected RateDecimal newInstance(double value) {
        return new RateDecimal(value);
    }

    @Override
    protected RateDecimal newInstance(BigDecimal value) {
        return new RateDecimal(value);
    }

    @Override
    protected RateDecimal newInstance(double value, int scale) {
        return new RateDecimal(value, scale);
    }

    @Override
    protected RateDecimal newInstance(BigDecimal value, int scale) {
        return new RateDecimal(value, scale);
    }
    
}

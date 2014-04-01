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
package org.kuali.coeus.sys.api.model;

import java.math.BigDecimal;

/**
 * This class is a wrapper around {@link java.math.BigDecimal}. It exposes the only the
 * needed functionality of {@link java.math.BigDecimal}, uses a standard
 * {@link java.math.RoundingMode} of {@link java.math.RoundingMode#HALF_UP}
 * and uses a standard SCALE of 2.
 *
 * This class is, like {@link java.math.BigDecimal}, immutable; even methods which
 * might be expected to change the value actually just return a new instance
 * with the new value.
 */
public final class ScaleTwoDecimal extends AbstractDecimal<ScaleTwoDecimal> {

    private static final long serialVersionUID = 1602860735060812811L;

    public static final int SCALE = 2;

    public static final ScaleTwoDecimal ZERO = new ScaleTwoDecimal(0);
    public static final ScaleTwoDecimal ONE_HUNDRED = new ScaleTwoDecimal(100);
    /**
     * This constructor should never be called except during JAXB unmarshalling.
     */
    private ScaleTwoDecimal() {
        super();
    }

    public ScaleTwoDecimal(String value) {
        super(value, SCALE);
    }

    public ScaleTwoDecimal(int value) {
        super(value, SCALE);
    }

    public ScaleTwoDecimal(double value) {
        super(value, SCALE);
    }

    public ScaleTwoDecimal(BigDecimal value) {
        super(value, SCALE);
    }

    private ScaleTwoDecimal(String value, int scale) {
        super(value, scale);
    }

    private ScaleTwoDecimal(int value, int scale) {
        super(value, scale);
    }

    private ScaleTwoDecimal(double value, int scale) {
        super(value, scale);
    }

    private ScaleTwoDecimal(BigDecimal value, int scale) {
        super(value, scale);
    }

    @Override
    protected ScaleTwoDecimal newInstance(int value) {
        return new ScaleTwoDecimal(value);
    }

    @Override
    protected ScaleTwoDecimal newInstance(BigDecimal value, int scale) {
        return new ScaleTwoDecimal(value, scale);
    }

    @Override
    protected ScaleTwoDecimal zero() {
        return ZERO;
    }

    @Override
    protected ScaleTwoDecimal oneHundred() {return ONE_HUNDRED; }

    /**
     * return {@link #ZERO} if the object is null
     * @param value the passed in value or {@link #ZERO}
     */
    public static ScaleTwoDecimal returnZeroIfNull(ScaleTwoDecimal value){
        return value==null ? ScaleTwoDecimal.ZERO : value;
    }
}

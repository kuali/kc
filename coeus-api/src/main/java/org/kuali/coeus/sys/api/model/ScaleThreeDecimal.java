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
 * and uses a standard SCALE of 3.
 *
 * This class is, like {@link java.math.BigDecimal}, immutable; even methods which
 * might be expected to change the value actually just return a new instance
 * with the new value.
 */
public final class ScaleThreeDecimal extends AbstractDecimal<ScaleThreeDecimal> {

    private static final long serialVersionUID = -1132481837308782665L;
    
    public static final int SCALE = 3;

    public static final ScaleThreeDecimal ZERO = new ScaleThreeDecimal(0.000);
    public static final ScaleThreeDecimal ONE_HUNDRED = new ScaleThreeDecimal(100);

    /**
     * This constructor should never be called except during JAXB unmarshalling.
     */
    private ScaleThreeDecimal() {
        super();
    }

    public ScaleThreeDecimal(String value) {
        super(value, SCALE);
    }

    public ScaleThreeDecimal(int value) {
        super(value, SCALE);
    }

    public ScaleThreeDecimal(double value) {
        super(value, SCALE);
    }

    public ScaleThreeDecimal(BigDecimal value) {
        super(value, SCALE);
    }

    private ScaleThreeDecimal(String value, int scale) {
        super(value, scale);
    }

    private ScaleThreeDecimal(int value, int scale) {
        super(value, scale);
    }

    private ScaleThreeDecimal(double value, int scale) {
        super(value, scale);
    }

    private ScaleThreeDecimal(BigDecimal value, int scale) {
        super(value, scale);
    }

    @Override
    protected ScaleThreeDecimal newInstance(int value) {
        return new ScaleThreeDecimal(value);
    }

    @Override
    protected ScaleThreeDecimal newInstance(BigDecimal value, int scale) {
        return new ScaleThreeDecimal(value, scale);
    }

    @Override
    protected ScaleThreeDecimal zero() {
        return ZERO;
    }

    @Override
    protected ScaleThreeDecimal oneHundred() {return ONE_HUNDRED; }
}

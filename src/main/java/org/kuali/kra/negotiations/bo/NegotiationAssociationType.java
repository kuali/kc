/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.negotiations.bo;

import java.io.Serializable;

/**
 * 
 * This class maintains the association type of a negotiation.
 */
public class NegotiationAssociationType  extends NegotiationsGroupingBase implements Serializable {
    
    public static final String NONE_ASSOCIATION = "NO";
    public static final String PROPOSAL_LOG_ASSOCIATION = "PL";
    public static final String INSTITUATIONAL_PROPOSAL_ASSOCIATION = "IP";
    public static final String AWARD_ASSOCIATION = "AWD";
    public static final String SUB_AWARD_ASSOCIATION = "SWD";

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8163060629967261671L;
    
    /**
     * 
     * Constructs a NegotiationAssociationType.java.
     */
    public NegotiationAssociationType() {
        super();
    }
}
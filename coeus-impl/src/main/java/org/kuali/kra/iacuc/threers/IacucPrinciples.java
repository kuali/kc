/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc.threers;

import org.kuali.kra.protocol.ProtocolAssociateBase;

/**
 * 
 * This class represents the "Three R's" or principles of IACUC,
 * (Reduction, Refinement, & Replacement).
 */
public class IacucPrinciples extends ProtocolAssociateBase {

    private static final long serialVersionUID = 580264349919894175L;
    
    private Integer iacucPrinciplesId;
    private String reduction;
    private String refinement;
    private String replacement;
    private String searchRequired;
    private boolean exceptionsPresent;
    
    public Integer getIacucPrinciplesId() {
        return iacucPrinciplesId;
    }
    public void setIacucPrinciplesId(Integer iacucPrinciplesId) {
        this.iacucPrinciplesId = iacucPrinciplesId;
    }
    public String getReduction() {
        return reduction;
    }
    public void setReduction(String reduction) {
        this.reduction = reduction;
    }
    public String getRefinement() {
        return refinement;
    }
    public void setRefinement(String refinement) {
        this.refinement = refinement;
    }
    public String getReplacement() {
        return replacement;
    }
    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }
    
    @Override
    public void resetPersistenceState() {
        this.setIacucPrinciplesId(null);        
    }
    public boolean isExceptionsPresent() {
        return exceptionsPresent;
    }
    public void setExceptionsPresent(boolean exceptionsPresent) {
        this.exceptionsPresent = exceptionsPresent;
    }
    public String getSearchRequired() {
        return searchRequired;
    }
    public void setSearchRequired(String searchRequired) {
        this.searchRequired = searchRequired;
    }
    
}

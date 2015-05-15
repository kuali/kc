/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.iacuc.threers;

import org.kuali.kra.protocol.ProtocolAssociateBase;

/**
 * 
 * This class represents the "Three R's" or principles of IACUC,
 * (Reduction, Refinement, &amp; Replacement).
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

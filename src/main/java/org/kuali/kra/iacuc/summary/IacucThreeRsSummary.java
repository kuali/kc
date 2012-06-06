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
package org.kuali.kra.iacuc.summary;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * This class represents a summary view of the "Three R's" or principles of IACUC,
 * (Reduction, Refinement, & Replacement).
 */
public class IacucThreeRsSummary {

    private String reduction;
    private String refinement;
    private String replacement;
    
    private boolean reductionChanged;
    private boolean refinementChanged;
    private boolean replacementChanged;
    
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
    public boolean isReductionChanged() {
        return reductionChanged;
    }
    public void setReductionChanged(boolean reductionChanged) {
        this.reductionChanged = reductionChanged;
    }
    public boolean isRefinementChanged() {
        return refinementChanged;
    }
    public void setRefinementChanged(boolean refinementChanged) {
        this.refinementChanged = refinementChanged;
    }
    public boolean isReplacementChanged() {
        return replacementChanged;
    }
    public void setReplacementChanged(boolean replacementChanged) {
        this.replacementChanged = replacementChanged;
    }

    public void compare(IacucThreeRsSummary other) {
        reductionChanged = !StringUtils.equals(reduction, other.getReduction());
        refinementChanged = !StringUtils.equals(refinement, other.getRefinement());
        replacementChanged = !StringUtils.equals(replacement, other.getReplacement());
    }
}

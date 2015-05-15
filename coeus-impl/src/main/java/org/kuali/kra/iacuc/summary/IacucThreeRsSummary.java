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
package org.kuali.kra.iacuc.summary;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class represents a summary view of the "Three R's" or principles of IACUC,
 * (Reduction, Refinement, &amp; Replacement).
 */
public class IacucThreeRsSummary implements Serializable {

    private static final long serialVersionUID = -284292353512149490L;

    private String reduction;
    private String refinement;
    private String replacement;
    private boolean searchRequired;
    private List<IacucAlternateSearchSummary> alternateSearchSummaries = new ArrayList<IacucAlternateSearchSummary>();
    
    private boolean reductionChanged;
    private boolean refinementChanged;
    private boolean replacementChanged;
    private boolean searchRequiredChanged;
    
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
    public boolean isSearchRequired() {
        return searchRequired;
    }
    public void setSearchRequired(boolean searchRequired) {
        this.searchRequired = searchRequired;
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
    public boolean isSearchRequiredChanged() {
        return searchRequiredChanged;
    }
    public void setSearchRequiredChanged(boolean searchRequiredChanged) {
        this.searchRequiredChanged = searchRequiredChanged;
    }
    public List<IacucAlternateSearchSummary> getAlternateSearchSummaries() {
        return alternateSearchSummaries;
    }
    public void setAlternateSearchSummaries(List<IacucAlternateSearchSummary> alternateSearchSummaries) {
        this.alternateSearchSummaries = alternateSearchSummaries;
    }

    public void compare(IacucThreeRsSummary other) {
        reductionChanged = !StringUtils.equals(reduction, other.getReduction());
        refinementChanged = !StringUtils.equals(refinement, other.getRefinement());
        replacementChanged = !StringUtils.equals(replacement, other.getReplacement());
        searchRequiredChanged = searchRequired != other.searchRequired;
    }
}

/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.hierarchy;


import java.util.Arrays;

public class ProposalHierarchyErrorWarningDto {
    private boolean severe;
    private String errorKey;
    private String[] errorParameters;

    /**
     * Constructs a ProposalHierarchyErrorDto.
     * @param errorKey
     * @param errorParameters
     */
    public ProposalHierarchyErrorWarningDto(String errorKey, boolean severe, String... errorParameters) {
        this.errorKey = errorKey;
        this.errorParameters = errorParameters;
        this.severe = severe;
    }

    /**
     * Gets the severe attribute.
     * @return Returns the severe.
     */
    public boolean isSevere() {
        return severe;
    }

    /**
     * Sets the errorKey attribute value.
     * @param severe The severe to set.
     */
    public void setSevere(boolean severe) {
        this.severe = severe;
    }

    /**
     * Gets the errorKey attribute. 
     * @return Returns the errorKey.
     */
    public String getErrorKey() {
        return errorKey;
    }

    /**
     * Sets the errorKey attribute value.
     * @param errorKey The errorKey to set.
     */
    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }

    /**
     * Gets the errorParms attribute. 
     * @return Returns the errorParameters.
     */
    public String[] getErrorParameters() {
        return errorParameters;
    }

    /**
     * Sets the errorParameters attribute value.
     * @param errorParameters The errorParameters to set.
     */
    public void setErrorParameters(String... errorParameters) {
        this.errorParameters = errorParameters;
    }

    @Override
    public String toString() {
        return "ProposalHierarchyErrorWarningDto{" +
                "severe=" + severe +
                ", errorKey='" + errorKey + '\'' +
                ", errorParameters=" + Arrays.toString(errorParameters) +
                '}';
    }
}

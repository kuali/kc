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
package org.kuali.coeus.sys.framework.validation;

import java.io.Serializable;
import java.util.Arrays;

/**
 * This class is used to report a soft error (i.e. a warning)
 */
public class SoftError implements Serializable {
    private static final long serialVersionUID = 4044942237931712580L;
    
    private String errorKey;
    private String[] errorParms;
    
    /**
     * Constructs a SoftError
     * @param errorkey
     * @param errorParms
     */
    public SoftError(String errorkey, String[] errorParms) {
        this.errorKey = errorkey;
        this.errorParms = errorParms;
    }

    /**
     * Gets the errorKey attribute. 
     * @return Returns the errorKey.
     */
    public String getErrorKey() {
        return errorKey;
    }

    /**
     * Gets the errorParms attribute. 
     * @return Returns the errorParms.
     */
    public String[] getErrorParms() {
        return errorParms;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((errorKey == null) ? 0 : errorKey.hashCode());
        result = PRIME * result + Arrays.hashCode(errorParms);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SoftError)) {
            return false;
        }
        SoftError other = (SoftError) obj;
        if (errorKey == null) {
            if (other.errorKey != null) {
                return false;
            }
        } else if (!errorKey.equals(other.errorKey)) {
            return false;
        }
        if (!Arrays.equals(errorParms, other.errorParms)) {
            return false;
        }
        return true;
    }
    
    
}

/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.rules;

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
     * @param errorKey
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

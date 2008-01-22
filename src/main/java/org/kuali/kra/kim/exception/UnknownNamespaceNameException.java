/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.kim.exception;

/**
 * An Unknown Namespace Name Exception occurs when KIM request is made
 * and KIM does not recognize the namespace name.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class UnknownNamespaceNameException extends RuntimeException {

    private static final long serialVersionUID = -3288226372968257144L;

    /**
     * Constructs a UnknownNamespaceNameException.
     * @param namespaceName the name of the Namespace
     */
    public UnknownNamespaceNameException(String namespaceName) {
        super("Unknown Namespace name: " + namespaceName);
    }
}

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
package org.kuali.kra.s2s.generator;

/**
 * This class is an exception class and is thrown whenever a generator for a namespoace is not available for form generation
 */
public class S2SGeneratorNotFoundException extends Exception{

    private static final long serialVersionUID = 477143438237989597L;

    /**
     * 
     * Constructs a S2SGeneratorNotFoundException.java.
     */
    public S2SGeneratorNotFoundException(){
        super();
    }
    
    /**
     * 
     * Constructs a S2SGeneratorNotFoundException.java.
     * @param message
     */
    public S2SGeneratorNotFoundException(String message) {
    super(message);
    }
    
    public S2SGeneratorNotFoundException(Throwable cause){
        super(cause);
    }
    /**
     * 
     * Constructs a S2SGeneratorNotFoundException.java.
     * @param message
     * @param cause
     */
    public S2SGeneratorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

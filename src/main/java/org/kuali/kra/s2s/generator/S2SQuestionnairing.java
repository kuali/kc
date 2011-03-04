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
package org.kuali.kra.s2s.generator;

public interface S2SQuestionnairing {
    /**
     * 
     * This method is to return namespace which used to fetch the questionnaire
     * @return namespace of the s2s form
     */
    public String getNamespace();
    /**
     * 
     * This method is to return formname
     * @return s2s formname
     */
    public String getFormName();
}

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
package org.kuali.coeus.common.framework.person.signature;


public interface PersonSignatureService {

    /**
     * 
     * This method is to apply signature to the pdf document.
     * In this method the input pdfBytes are merged with appropriate signature and 
     * the result is returned.
     * @param pdfBytes
     * @return pdfFileData
     * @throws Exception
     */
    byte[] applySignature(byte[] pdfBytes) throws Exception;

}

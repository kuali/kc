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

import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.bo.AttachmentData;

/**
 * 
 * This interface defines the core methods for the Form Generator classes and is meant to be implemented by all generators
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface S2SFormGenerator {


    /**
     * 
     * This method creates an XML document using the APIs generated using XMLBEans by compiling the form's schema and returns it
     * as XmlObject.
     * 
     * @param proposalDevelopmentDocument
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument);

    /**
     * 
     * This method returns all the attachments required for a form in a Map
     * 
     * @return Map
     */
    public List<AttachmentData> getAttachments();
    
    /**
     * 
     * This method creates an XML document using the APIs generated using XMLBEans by compiling the form's schema and returns it
     * as XmlObject.
     * 
     * @param xmlObject
     * @return XmlObject
     */
    
    public XmlObject getFormObject(XmlObject xmlObject);
}

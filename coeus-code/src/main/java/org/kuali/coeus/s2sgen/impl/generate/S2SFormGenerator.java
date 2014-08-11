/*
 * Copyright 2005-2014 The Kuali Foundation.
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
package org.kuali.coeus.s2sgen.impl.generate;

import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.s2sgen.api.generate.AttachmentData;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.api.core.AuditError;

import java.util.List;

/**
 * 
 * This interface defines the core methods for the Form Generator classes and is meant to be implemented by all generators
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface S2SFormGenerator {


    /**
     * 
     * This method creates an XML document using the APIs generated using XMLBeans by compiling the form's schema and returns it
     * as XmlObject.
     */
    XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) throws S2SException;

    List<AttachmentData> getAttachments();

    List<AuditError> getAuditErrors();
}

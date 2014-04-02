/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.rule.event;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent;
/**
 * Event triggered when a personnel attachment state is modified on a 
 * <code>{@link ProposalDevelopmentDocument}</code>
 *
 */
public interface PersonnelAttachmentEvent extends KualiDocumentEvent {
    
    /**
     * @return <code>{@link ProposalPersonBiography}</code> that triggered this event.
     */
    public ProposalPersonBiography getProposalPersonBiography();


}

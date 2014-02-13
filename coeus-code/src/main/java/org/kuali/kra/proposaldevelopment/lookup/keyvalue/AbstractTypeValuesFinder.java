/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.krad.migration.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.kra.proposaldevelopment.bo.AbstractType;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Finds the available set of supported Abstract Types.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class AbstractTypeValuesFinder extends FormViewAwareUifKeyValuesFinderBase {
    
    /**
     * Constructs the list of Proposal Abstract Types.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * type code and the "value" is the textual description that is viewed
     * by a user.  The list is obtained from the Abstract Type database table
     * via the "keyValuesService".  The intent of this method is to provide 
     * a list which is viewed in a GUI.  As such, the first entry in the list 
     * is the generic &lt;"", "select:"&gt; option.
     * 
     * The list is also filtered based upon the abstracts currently in the
     * proposal development document.  Users are not allowed to create two or
     * more abstracts with the same abstract type.  Therefore, if an abstract
     * type is already being used, it is removed from the list.  If the document
     * cannot be found, the entire list is returned (it is not filtered).
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) getDocument();
        KeyValuesService keyValuesService = (KeyValuesService) KcServiceLocator.getService("keyValuesService");
        Collection<AbstractType> abstractTypes = keyValuesService.findAll(AbstractType.class);
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        for (AbstractType abstractType : abstractTypes) {
            if (!hasAbstract(doc, abstractType)) {
                keyValues.add(new ConcreteKeyValue(abstractType.getAbstractTypeCode(), abstractType.getDescription()));
            }
        }
        return keyValues;
    }
    
    /**
     * Does the document already have an abstract using the given abstract type?
     * 
     * @param doc the Proposal Development Document.
     * @param abstractType the abstract type to look for.
     * @return true if the abstract type is found; otherwise false.
     */
    private boolean hasAbstract(ProposalDevelopmentDocument doc, AbstractType abstractType) {
        if (doc != null) {
            List<ProposalAbstract> proposalAbstracts = doc.getDevelopmentProposal().getProposalAbstracts();
            for (ProposalAbstract proposalAbstract : proposalAbstracts) {
                if (proposalAbstract.getAbstractTypeCode().equals(abstractType.getAbstractTypeCode())) {
                    return true;
                }
            }
        }
        return false;
    }
}

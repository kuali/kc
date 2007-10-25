/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.service.KeyValuesService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.AbstractType;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;

/**
 * Finds the available set of supported Abstract Types.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author Donald A. Barre
 */
public class AbstractTypeValuesFinder extends KeyValuesBase {
    
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
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
        ProposalDevelopmentDocument doc = getDocument();
        KeyValuesService keyValuesService = (KeyValuesService) KraServiceLocator.getService("keyValuesService");
        Collection abstractTypes = keyValuesService.findAll(AbstractType.class);
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select:"));
        for (Iterator iter = abstractTypes.iterator(); iter.hasNext();) {
            AbstractType abstractType = (AbstractType) iter.next();
            if (!hasAbstract(doc, abstractType)) {
                keyValues.add(new KeyLabelPair(abstractType.getAbstractTypeCode(), abstractType.getDescription()));
            }
        }
        return keyValues;
    }
    
    /**
     * Get the Proposal Development Document for the current session.  The
     * document is within the current form.
     * 
     * @return the current document or null if not found
     */
    private ProposalDevelopmentDocument getDocument() {
        ProposalDevelopmentDocument doc = null;
        ProposalDevelopmentForm form = (ProposalDevelopmentForm) GlobalVariables.getKualiForm();
        if (form != null) {
            doc = form.getProposalDevelopmentDocument();
        }
        return doc;
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
            List<ProposalAbstract> proposalAbstracts = doc.getProposalAbstracts();
            for (ProposalAbstract proposalAbstract : proposalAbstracts) {
                if (proposalAbstract.getAbstractTypeCode().equals(abstractType.getAbstractTypeCode())) {
                    return true;
                }
            }
        }
        return false;
    }
}

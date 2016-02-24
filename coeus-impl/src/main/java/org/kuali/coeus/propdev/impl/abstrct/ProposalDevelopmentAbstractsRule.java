/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.abstrct;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.criteria.CountFlag;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Business Rule to determine the validity of Proposal Abstracts.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentAbstractsRule extends KcTransactionalDocumentRuleBase implements AbstractsRule {

    private DataObjectService dataObjectService ;
    protected DataObjectService getDataObjectService (){
        if (dataObjectService == null)
            dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        return dataObjectService;
    }

    /**
     * Don't allow abstracts with an invalid abstract type code or duplicate abstracts, i.e.
     * same abstract type code, into the database.
     * 
     * @see org.kuali.coeus.propdev.impl.abstrct.AbstractsRule#processAddAbstractBusinessRules(org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument, org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract)
     */
    public boolean processAddAbstractBusinessRules(ProposalDevelopmentDocument document, ProposalAbstract proposalAbstract) {
        boolean isValid = true;
        String abstractTypeCode = proposalAbstract.getAbstractTypeCode();
        
        if (StringUtils.isBlank(abstractTypeCode)) {
            // If the user didn't select an abstract type, i.e. he/she choose the "select:" option,
            // then the Abstract Type Code will be "blank".
            isValid = false;
            GlobalVariables.getMessageMap().putError(Constants.ABSTRACTS_PROPERTY_KEY + ".abstractTypeCode", 
                                                   KeyConstants.ERROR_ABSTRACT_TYPE_NOT_SELECTED);
        }
        else if (isInvalid(abstractTypeCode)) {
            isValid = false;
            this.reportError(Constants.ABSTRACTS_PROPERTY_KEY, 
                             KeyConstants.ERROR_ABSTRACT_TYPE_INVALID);
        }
        else if (isDuplicate(document, abstractTypeCode)) {
            isValid = false;
            this.reportError(Constants.ABSTRACTS_PROPERTY_KEY, 
                             KeyConstants.ERROR_ABSTRACT_TYPE_DUPLICATE);
        }
        return isValid;
    }
    
    /**
     * Is this an invalid abstract type code?  Query the database for a matching abstract
     * type code.  If found, it is valid; otherwise it is invalid.
     * 
     * @param abstractTypeCode the abstract type code to test against.
     * @return true if invalid; false if valid
     */
    private boolean isInvalid(String abstractTypeCode) {
        if (abstractTypeCode != null) {
            Map<String,String> fieldValues = new HashMap<String,String>();
            fieldValues.put("code", abstractTypeCode);
            if (getDataObjectService().findMatching(AbstractType.class,
                    QueryByCriteria.Builder.andAttributes(fieldValues).setCountFlag(CountFlag.ONLY).build()).getTotalRowCount() == 1) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Is this a duplicate abstract?  Abstracts must have a unique abstract type code.
     * If an abstract already exists with the same abstract type code, do not allow
     * the next abstract.
     * 
     * @param document the proposal development document
     * @param abstractTypeCode the abstract type code to compare against
     * @return true if it is a duplicate; otherwise false
     */
    private boolean isDuplicate(ProposalDevelopmentDocument document, String abstractTypeCode) {
        List<ProposalAbstract> proposalAbstracts = document.getDevelopmentProposal().getProposalAbstracts();
        for (ProposalAbstract proposalAbstract : proposalAbstracts) {
            if (proposalAbstract.getAbstractTypeCode().equals(abstractTypeCode)) {
                return true;
            }
        }
        return false;
    }
}

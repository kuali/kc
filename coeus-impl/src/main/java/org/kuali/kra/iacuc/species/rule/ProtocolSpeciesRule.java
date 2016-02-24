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
package org.kuali.kra.iacuc.species.rule;

import org.kuali.kra.iacuc.species.IacucProtocolSpecies;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * This class is implementation of <code>AddProtocolSpeciesRule</code> interface. Impl makes sure necessary rules are satisfied 
 * before object can be used.
 */
public class ProtocolSpeciesRule extends KcTransactionalDocumentRuleBase implements AddProtocolSpeciesRule {
    private static final String NEW_PROTOCOL_SPECIES_PATH = "iacucProtocolSpeciesHelper.newIacucProtocolSpecies";

    @Override
    public boolean processAddProtocolSpeciesBusinessRules(AddProtocolSpeciesEvent addProtocolSpeciesEvent) {
        
        boolean rulePassed = true;
        MessageMap errorMap = GlobalVariables.getMessageMap();
        errorMap.addToErrorPath(NEW_PROTOCOL_SPECIES_PATH);
        IacucProtocolSpecies protocolSpecies = addProtocolSpeciesEvent.getProtocolSpecies();
        
        getDictionaryValidationService().validateBusinessObject(protocolSpecies);
        rulePassed &= GlobalVariables.getMessageMap().hasNoErrors();

        errorMap.removeFromErrorPath(NEW_PROTOCOL_SPECIES_PATH);

        return rulePassed;
    }

}

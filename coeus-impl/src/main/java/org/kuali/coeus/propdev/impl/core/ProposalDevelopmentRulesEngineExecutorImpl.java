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

package org.kuali.coeus.propdev.impl.core;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.kuali.coeus.common.framework.krms.KcRulesEngineExecuter;
import org.kuali.coeus.common.impl.krms.KcKrmsFactBuilderServiceHelper;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krms.api.engine.Engine;
import org.kuali.rice.krms.api.engine.EngineResults;
import org.kuali.rice.krms.api.engine.Facts;
import org.kuali.rice.krms.api.engine.SelectionCriteria;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;

public class ProposalDevelopmentRulesEngineExecutorImpl  extends KcRulesEngineExecuter  {

    private KcKrmsFactBuilderServiceHelper kcKrmsFactBuilderServiceHelper;
    protected KcKrmsFactBuilderServiceHelper getKcKrmsFactBuilderServiceHelper (){
        if (kcKrmsFactBuilderServiceHelper == null)
            kcKrmsFactBuilderServiceHelper = getProposalDevelopmentFactBuilderService();
        return kcKrmsFactBuilderServiceHelper;
    }
    public EngineResults performExecute(RouteContext routeContext, Engine engine) {
        Map<String, String> contextQualifiers = new HashMap<String, String>();
        contextQualifiers.put("namespaceCode", Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT);
        contextQualifiers.put("name", KcKrmsConstants.ProposalDevelopment.PROPOSAL_DEVELOPMENT_CONTEXT);

        String docContent = routeContext.getDocument().getDocContent();
        String proposalNumber = getElementValue(docContent, "//proposalNumber");
        List<String> unitNumbers = getProposalUnits(proposalNumber, routeContext);
        String unitNumbersAsString = StringUtils.join(unitNumbers,',');
        SelectionCriteria selectionCriteria = SelectionCriteria.createCriteria(null, contextQualifiers,
                Collections.singletonMap(KcKrmsConstants.UNIT_NUMBER, unitNumbersAsString));

        KcKrmsFactBuilderServiceHelper fbService = getProposalDevelopmentFactBuilderService();
        Facts.Builder factsBuilder = Facts.Builder.create();
        fbService.addFacts(factsBuilder, docContent);
        EngineResults results = engine.execute(selectionCriteria, factsBuilder.build(), null);
        return results;
    }
	private KcKrmsFactBuilderServiceHelper getProposalDevelopmentFactBuilderService() {
		return KcServiceLocator.getService("proposalDevelopmentFactBuilderService");
	}
    private List<String> getProposalUnits(String proposalNumber, RouteContext routeContext) {
    	DataObjectService dataObjectService = getDataObjectService();

        String docContent = routeContext.getDocument().getDocContent();
        String unitNumber = getElementValue(docContent, "//ownedByUnitNumber");

        Map<String,String> params = new HashMap<String, String>();
        params.put("developmentProposal.proposalNumber", proposalNumber);

        List<ProposalPerson> proposalPersons = (List<ProposalPerson>) dataObjectService.findMatching(ProposalPerson.class,
        		QueryByCriteria.Builder.andAttributes(params).build()).getResults();
        List<String> units = new ArrayList<String>();
        units.add(unitNumber);

        for (ProposalPerson proposalPerson : proposalPersons) {
            List<ProposalPersonUnit> proposalPersonUnits = proposalPerson.getUnits();
            for (ProposalPersonUnit proposalPersonUnit : proposalPersonUnits) {
                if(!units.contains(proposalPersonUnit.getUnitNumber())){
                    units.add(proposalPersonUnit.getUnitNumber());
                }
            }
        }
        return units;
    }
	private DataObjectService getDataObjectService() {
		return KcServiceLocator.getService(DataObjectService.class);
	}
}

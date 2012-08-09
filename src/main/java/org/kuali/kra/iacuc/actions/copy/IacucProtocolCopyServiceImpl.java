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
package org.kuali.kra.iacuc.actions.copy;

import java.util.HashMap;
import java.util.List;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.customdata.IacucProtocolCustomData;
import org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupBean;
import org.kuali.kra.iacuc.protocol.IacucProtocolNumberService;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;
import org.kuali.kra.iacuc.species.IacucProtocolSpeciesService;
import org.kuali.kra.iacuc.species.exception.IacucProtocolException;
import org.kuali.kra.iacuc.species.exception.IacucProtocolExceptionService;
import org.kuali.kra.iacuc.threers.IacucAlternateSearch;
import org.kuali.kra.iacuc.threers.IacucPrinciples;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.copy.ProtocolCopyServiceImpl;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;

public class IacucProtocolCopyServiceImpl extends ProtocolCopyServiceImpl<IacucProtocolDocument> implements IacucProtocolCopyService{

    private IacucProtocolSpeciesService iacucProtocolSpeciesService;
    private IacucProtocolExceptionService iacucProtocolExceptionService;
    private IacucProtocolProcedureService iacucProtocolProcedureService;
    
    @Override
    protected Class<? extends ProtocolAction> getProtocolActionBOClassHook() {
        return IacucProtocolAction.class;
    }

    @Override
    protected String getProtocolActionProtocolCreatedCodeHook() {
        return IacucProtocolActionType.IACUC_PROTOCOL_CREATED;
    }

    @Override
    protected String getSequenceNumberNameHook() {
        return "SEQ_IACUC_PROTOCOL_ID";
    }

    @Override
    protected void copyRequiredProperties(IacucProtocolDocument srcDoc, IacucProtocolDocument destDoc) {
        super.copyRequiredProperties(srcDoc, destDoc);
        destDoc.getIacucProtocol().setLayStatement1(srcDoc.getIacucProtocol().getLayStatement1());
        destDoc.getIacucProtocol().setLayStatement2(srcDoc.getIacucProtocol().getLayStatement2());
        destDoc.getIacucProtocol().setProtocolProjectTypeCode(srcDoc.getIacucProtocol().getProtocolProjectTypeCode());
        destDoc.getIacucProtocol().setOverviewTimeline(srcDoc.getIacucProtocol().getOverviewTimeline());
        destDoc.getIacucProtocol().setSpeciesStudyGroupIndicator(srcDoc.getIacucProtocol().getSpeciesStudyGroupIndicator());
        destDoc.getIacucProtocol().setAlternativeSearchIndicator(srcDoc.getIacucProtocol().getAlternativeSearchIndicator());
        destDoc.getIacucProtocol().setScientificJustifIndicator(srcDoc.getIacucProtocol().getScientificJustifIndicator());
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected void copyProtocolLists(IacucProtocolDocument srcDoc, IacucProtocolDocument destDoc) {
        super.copyProtocolLists(srcDoc, destDoc);
        addThreeRs(srcDoc, destDoc);
        HashMap<Integer, Integer> speciesIdMapping = addProtocolSpecies(srcDoc.getIacucProtocol().getIacucProtocolSpeciesList(), destDoc);
        addProtocolExceptions(srcDoc.getIacucProtocol().getIacucProtocolExceptions(), destDoc);
        addProtocolProcedures(srcDoc.getIacucProtocol().getIacucProtocolStudyGroups(), destDoc, speciesIdMapping);
        destDoc.getIacucProtocol().setIacucProtocolCustomDataList((List<IacucProtocolCustomData>) 
                deepCopy(srcDoc.getIacucProtocol().getIacucProtocolCustomDataList()));
        
    }
    
    @SuppressWarnings("unchecked")
    protected void addThreeRs(IacucProtocolDocument srcDoc, IacucProtocolDocument destDoc) {
        List<IacucPrinciples> newIacucPrinciples = (List<IacucPrinciples>)deepCopy(srcDoc.getIacucProtocol().getIacucPrinciples());
        for(IacucPrinciples iacucPrinciple : newIacucPrinciples) {
            iacucPrinciple.setIacucPrinciplesId(null);
        }
        destDoc.getIacucProtocol().setIacucPrinciples(newIacucPrinciples); 
        List<IacucAlternateSearch> newIacucAlternateSearches = (List<IacucAlternateSearch>)deepCopy(srcDoc.getIacucProtocol().getIacucAlternateSearches());
        for(IacucAlternateSearch iacucAlternateSearch : newIacucAlternateSearches) {
            iacucAlternateSearch.setIacucAltSearchId(null);
        }
        destDoc.getIacucProtocol().setIacucAlternateSearches(newIacucAlternateSearches); 
    }
    
    /**
     * This method is to copy protocol species
     * Return a map of old species and current species id. 
     * This mapping is required when new set of procedures are created where it is
     * linked to protocol species id.
     * @param sourceProtocolSpecies
     * @param destDoc
     */
    protected HashMap<Integer, Integer> addProtocolSpecies(List<IacucProtocolSpecies> sourceProtocolSpecies, IacucProtocolDocument destDoc) {
        IacucProtocol protocol = destDoc.getIacucProtocol();
        HashMap<Integer, Integer> speciesIdMapping = new HashMap<Integer,Integer>();
        for(IacucProtocolSpecies protocolSpecies : sourceProtocolSpecies) {
            IacucProtocolSpecies newProtocolSpecies = (IacucProtocolSpecies)deepCopy(protocolSpecies);
            newProtocolSpecies = getIacucProtocolSpeciesService().getNewProtocolSpecies(protocol, newProtocolSpecies);
            destDoc.getIacucProtocol().getIacucProtocolSpeciesList().add(newProtocolSpecies);
            speciesIdMapping.put(protocolSpecies.getIacucProtocolSpeciesId(), newProtocolSpecies.getIacucProtocolSpeciesId());
        }
        return speciesIdMapping;
    }
    
    /**
     * This method is to copy protocol exceptions
     * @param sourceProtocolExceptions
     * @param destDoc
     */
    protected void addProtocolExceptions(List<IacucProtocolException> sourceProtocolExceptions, IacucProtocolDocument destDoc) {
        IacucProtocol protocol = destDoc.getIacucProtocol();
        for(IacucProtocolException protocolException : sourceProtocolExceptions) {
            IacucProtocolException newProtocolException = (IacucProtocolException)deepCopy(protocolException);
            newProtocolException = getIacucProtocolExceptionService().getNewProtocolException(protocol, newProtocolException);
            destDoc.getIacucProtocol().getIacucProtocolExceptions().add(newProtocolException);
        }
    }
    
    /**
     * This method is to copy protocol procedures
     * @param sourceProtocolProcedures
     * @param destDoc
     * @param speciesIdMapping
     */
    protected void addProtocolProcedures(List<IacucProtocolStudyGroupBean> sourceProtocolProcedures, IacucProtocolDocument destDoc, 
            HashMap<Integer, Integer> speciesIdMapping) {
        IacucProtocol protocol = destDoc.getIacucProtocol();
        getIacucProtocolProcedureService().createNewStudyGroups(protocol, sourceProtocolProcedures, speciesIdMapping);
    }
    
    @Override
    protected IacucProtocolNumberService getProtocolNumberServiceHook() {
        return (IacucProtocolNumberService)KraServiceLocator.getService("iacucProtocolNumberService");
    }
    
    @Override
    protected IacucProtocolAction getProtocolActionNewInstanceHook(Protocol protocol, ProtocolSubmission protocolSubmission,
            String protocolActionTypeCode) {
        return new IacucProtocolAction((IacucProtocol) protocol, (IacucProtocolSubmission) protocolSubmission, protocolActionTypeCode);
    }

    @Override
    protected String getProtocolAggregatorHook() {
        return RoleConstants.IACUC_PROTOCOL_AGGREGATOR;
    }

    @Override
    protected String getProtocolApproverHook() {
        return RoleConstants.IACUC_PROTOCOL_APPROVER;
    }

    @Override
    protected String getProtocolRoleTypeHook() {
        return RoleConstants.IACUC_ROLE_TYPE;
    }

    public IacucProtocolSpeciesService getIacucProtocolSpeciesService() {
        return iacucProtocolSpeciesService;
    }

    public void setIacucProtocolSpeciesService(IacucProtocolSpeciesService iacucProtocolSpeciesService) {
        this.iacucProtocolSpeciesService = iacucProtocolSpeciesService;
    }

    public IacucProtocolExceptionService getIacucProtocolExceptionService() {
        return iacucProtocolExceptionService;
    }

    public void setIacucProtocolExceptionService(IacucProtocolExceptionService iacucProtocolExceptionService) {
        this.iacucProtocolExceptionService = iacucProtocolExceptionService;
    }

    public IacucProtocolProcedureService getIacucProtocolProcedureService() {
        return iacucProtocolProcedureService;
    }

    public void setIacucProtocolProcedureService(IacucProtocolProcedureService iacucProtocolProcedureService) {
        this.iacucProtocolProcedureService = iacucProtocolProcedureService;
    }

}

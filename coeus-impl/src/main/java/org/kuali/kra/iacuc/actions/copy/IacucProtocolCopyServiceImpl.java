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
package org.kuali.kra.iacuc.actions.copy;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.customdata.IacucProtocolCustomData;
import org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService;
import org.kuali.kra.iacuc.protocol.IacucProtocolNumberService;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;
import org.kuali.kra.iacuc.species.IacucProtocolSpeciesService;
import org.kuali.kra.iacuc.species.exception.IacucProtocolException;
import org.kuali.kra.iacuc.species.exception.IacucProtocolExceptionService;
import org.kuali.kra.iacuc.threers.IacucAlternateSearch;
import org.kuali.kra.iacuc.threers.IacucPrinciples;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.copy.ProtocolCopyServiceImplBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IacucProtocolCopyServiceImpl extends ProtocolCopyServiceImplBase<IacucProtocolDocument> implements IacucProtocolCopyService{

    private IacucProtocolSpeciesService iacucProtocolSpeciesService;
    private IacucProtocolExceptionService iacucProtocolExceptionService;
    private IacucProtocolProcedureService iacucProtocolProcedureService;
    
    @Override
    protected Class<? extends ProtocolActionBase> getProtocolActionBOClassHook() {
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
    
    @Override
    protected void copyProtocolLists(IacucProtocolDocument srcDoc, IacucProtocolDocument destDoc) {
        super.copyProtocolLists(srcDoc, destDoc);
        IacucProtocol srcProtocol = srcDoc.getIacucProtocol();
        IacucProtocol destProtocol = destDoc.getIacucProtocol();
        copyIacucProtocolLists(srcProtocol, destProtocol);
    }

    @SuppressWarnings("unchecked")
    protected void copyIacucProtocolLists(IacucProtocol srcProtocol, IacucProtocol destProtocol) {
        addThreeRs(srcProtocol, destProtocol);
        addProtocolSpecies(srcProtocol, destProtocol);
        addProtocolExceptions(srcProtocol, destProtocol);
        addProtocolProcedures(srcProtocol, destProtocol);
        destProtocol.setIacucProtocolCustomDataList((List<IacucProtocolCustomData>) 
                deepCopy(srcProtocol.getIacucProtocolCustomDataList()));
    }
    
    @SuppressWarnings("unchecked")
    protected void addThreeRs(IacucProtocol srcProtocol, IacucProtocol destProtocol) {
        IacucPrinciples newIacucPrinciple = (IacucPrinciples)deepCopy(srcProtocol.getIacucPrinciples().get(0));
        newIacucPrinciple.setIacucPrinciplesId(null);
        destProtocol.getIacucPrinciples().clear();
        destProtocol.getIacucPrinciples().add(newIacucPrinciple);

        List<IacucAlternateSearch> newIacucAlternateSearches = (List<IacucAlternateSearch>)deepCopy(srcProtocol.getIacucAlternateSearches());
        for(IacucAlternateSearch iacucAlternateSearch : newIacucAlternateSearches) {
            iacucAlternateSearch.setIacucAltSearchId(null);
        }
        destProtocol.setIacucAlternateSearches(newIacucAlternateSearches); 
    }

    public void copyProtocolThreers(IacucProtocol srcProtocol, IacucProtocol destProtocol) {
        addThreeRs(srcProtocol, destProtocol);
    }

    public void copyProtocolExceptions(IacucProtocol srcProtocol, IacucProtocol destProtocol) {
        addProtocolExceptions(srcProtocol, destProtocol);
    }
    
    /**
     * This method is to copy protocol species
     * Return a map of old species and current species id. 
     * This mapping is required when new set of procedures are created where it is
     * linked to protocol species id.
     */
    protected void addProtocolSpecies(IacucProtocol srcProtocol, IacucProtocol destProtocol) {
        List<IacucProtocolSpecies> sourceProtocolSpecies = srcProtocol.getIacucProtocolSpeciesList();
        destProtocol.setIacucProtocolSpeciesList(new ArrayList<IacucProtocolSpecies>());
        for(IacucProtocolSpecies protocolSpecies : sourceProtocolSpecies) {
            destProtocol.getIacucProtocolSpeciesList().add(getNewProtocolSpecies(protocolSpecies, destProtocol));
        }
    }
    
    /**
     * This method is to get a new copy of current protocol species.
     * @param srcProtocolSpecies
     * @param destProtocol
     * @return
     */
    private IacucProtocolSpecies getNewProtocolSpecies(IacucProtocolSpecies srcProtocolSpecies, IacucProtocol destProtocol) {
        IacucProtocolSpecies newProtocolSpecies = (IacucProtocolSpecies)deepCopy(srcProtocolSpecies);
        newProtocolSpecies = getIacucProtocolSpeciesService().getNewProtocolSpecies(destProtocol, newProtocolSpecies);
        newProtocolSpecies.setOldProtocolSpeciesId(srcProtocolSpecies.getIacucProtocolSpeciesId());
        return newProtocolSpecies;
    }

    /**
     * This method is to merge source species with destination species
     * @param srcProtocolSpecies
     * @param dstProtocolSpecies
     */
    protected void mergeProtocolSpecies(IacucProtocolSpecies srcProtocolSpecies, IacucProtocolSpecies dstProtocolSpecies) {
        dstProtocolSpecies.setUsdaCovered(srcProtocolSpecies.getUsdaCovered()); 
        dstProtocolSpecies.setStrain(srcProtocolSpecies.getStrain()); 
        dstProtocolSpecies.setSpeciesCount(srcProtocolSpecies.getSpeciesCount()); 
        dstProtocolSpecies.setPainCategoryCode(srcProtocolSpecies.getPainCategoryCode()); 
        dstProtocolSpecies.setSpeciesCountCode(srcProtocolSpecies.getSpeciesCountCode()); 
        dstProtocolSpecies.setExceptionsPresent(srcProtocolSpecies.getExceptionsPresent());
        dstProtocolSpecies.setProcedureSummary(srcProtocolSpecies.getProcedureSummary());
    }
    
    protected HashMap<Integer, Integer> getNewProtocolSpeciesMap(IacucProtocol protocol) {
        return getIacucProtocolSpeciesService().getNewProtocolSpeciesMap(protocol);
    }
    
    /**
     * This method is to copy protocol exceptions
     */
    protected void addProtocolExceptions(IacucProtocol srcProtocol, IacucProtocol destProtocol) {
        destProtocol.setIacucProtocolExceptions(new ArrayList<IacucProtocolException>());
        List<IacucProtocolException> sourceProtocolExceptions = srcProtocol.getIacucProtocolExceptions();
        for(IacucProtocolException protocolException : sourceProtocolExceptions) {
            IacucProtocolException newProtocolException = (IacucProtocolException)deepCopy(protocolException);
            newProtocolException = getIacucProtocolExceptionService().getNewProtocolException(destProtocol, newProtocolException);
            destProtocol.getIacucProtocolExceptions().add(newProtocolException);
        }
    }
    
    /**
     * This method is to copy protocol procedures
     */
    protected void addProtocolProcedures(IacucProtocol srcProtocol, IacucProtocol destProtocol) { 
        getIacucProtocolProcedureService().createNewProtocolStudyProcedures(srcProtocol, destProtocol);
    }
    
    @Override
    protected IacucProtocolNumberService getProtocolNumberServiceHook() {
        return (IacucProtocolNumberService) KcServiceLocator.getService("iacucProtocolNumberService");
    }
    
    @Override
    protected IacucProtocolAction getProtocolActionNewInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase protocolSubmission,
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

/*
 * Copyright 2005-2013 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.customdata.IacucProtocolCustomData;
import org.kuali.kra.iacuc.procedures.IacucProcedurePersonResponsible;
import org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyCustomData;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroup;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupBean;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupDetailBean;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupLocation;
import org.kuali.kra.iacuc.protocol.IacucProtocolNumberService;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;
import org.kuali.kra.iacuc.species.IacucProtocolSpeciesService;
import org.kuali.kra.iacuc.species.exception.IacucProtocolException;
import org.kuali.kra.iacuc.species.exception.IacucProtocolExceptionService;
import org.kuali.kra.iacuc.threers.IacucAlternateSearch;
import org.kuali.kra.iacuc.threers.IacucPrinciples;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.copy.ProtocolCopyServiceImplBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.rice.krad.util.ObjectUtils;

public class IacucProtocolCopyServiceImpl extends ProtocolCopyServiceImplBase<IacucProtocolDocument> implements IacucProtocolCopyService{

    private IacucProtocolSpeciesService iacucProtocolSpeciesService;
    private IacucProtocolExceptionService iacucProtocolExceptionService;
    private IacucProtocolProcedureService iacucProtocolProcedureService;
    
    public void copyIacucProtocolModules(IacucProtocol srcProtocol, IacucProtocol destProtocol) {
        copyIacucProtocolLists(srcProtocol, destProtocol);
    }
    
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
    

    /**
     * @see org.kuali.kra.iacuc.actions.copy.IacucProtocolCopyService#copyIacucProtocolModules(org.kuali.kra.iacuc.IacucProtocol, org.kuali.kra.iacuc.IacucProtocol)
     */
    public void copyProtocolThreers(IacucProtocol srcProtocol, IacucProtocol destProtocol) {
        addThreeRs(srcProtocol, destProtocol);
    }

    /**
     * @see org.kuali.kra.iacuc.actions.copy.IacucProtocolCopyService#copyProtocolSpeciesAndGroups(org.kuali.kra.iacuc.IacucProtocol, org.kuali.kra.iacuc.IacucProtocol)
     */
    public void copyProtocolSpeciesAndGroups(IacucProtocol srcProtocol, IacucProtocol destProtocol) {
        addProtocolSpecies(srcProtocol, destProtocol);
    }

    /**
     * @see org.kuali.kra.iacuc.actions.copy.IacucProtocolCopyService#copyProtocolProcedures(org.kuali.kra.iacuc.IacucProtocol, org.kuali.kra.iacuc.IacucProtocol)
     */
    public void copyProtocolProcedures(IacucProtocol srcProtocol, IacucProtocol destProtocol) {
        addProtocolProcedures(srcProtocol, destProtocol);
    }

    /**
     * @see org.kuali.kra.iacuc.actions.copy.IacucProtocolCopyService#copyProtocolExceptions(org.kuali.kra.iacuc.IacucProtocol, org.kuali.kra.iacuc.IacucProtocol)
     */
    public void copyProtocolExceptions(IacucProtocol srcProtocol, IacucProtocol destProtocol) {
        addProtocolExceptions(srcProtocol, destProtocol);
    }
    
    /**
     * This method is to copy protocol species
     * Return a map of old species and current species id. 
     * This mapping is required when new set of procedures are created where it is
     * linked to protocol species id.
     * @param sourceProtocolSpecies
     * @param destDoc
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
     * This method...
     * @param srcProtocol
     * @param destProtocol
     */
    private void mergeProtocolProcedureSpecies(IacucProtocol srcProtocol, IacucProtocol destProtocol) {
        for(IacucProtocolStudyGroupBean studyGroupBean : destProtocol.getIacucProtocolStudyGroups()) {
            for(IacucProtocolStudyGroupDetailBean studyGroupDetailBean : studyGroupBean.getIacucProtocolStudyGroupDetailBeans()) {
                for(IacucProtocolStudyGroup studyGroup : studyGroupDetailBean.getIacucProtocolStudyGroups()) {
                    setNewProtocolSpeciesForStudyGroup(studyGroup, destProtocol.getIacucProtocolSpeciesList());
                }
            }
        }
    }
    
    private void setNewProtocolSpeciesForStudyGroup(IacucProtocolStudyGroup studyGroup, List<IacucProtocolSpecies> newIacucProtocolSpeciesList) {
        String destUniqueSpeciesKey = getUniqueSpecies(studyGroup.getIacucProtocolSpecies());
        IacucProtocolSpecies newProtocolSpecies = getNewProtocolSpeciesIdBasedOnUniqueKey(newIacucProtocolSpeciesList, destUniqueSpeciesKey);
        Integer newProtocolSpeciesId = newProtocolSpecies.getIacucProtocolSpeciesId();
        studyGroup.setIacucProtocolSpeciesId(newProtocolSpeciesId);
        studyGroup.setIacucProtocolSpecies(newProtocolSpecies);
    }
    
    private IacucProtocolSpecies getNewProtocolSpeciesIdBasedOnUniqueKey(List<IacucProtocolSpecies> newIacucProtocolSpeciesList, String uniqueSpeciesKey) {
        IacucProtocolSpecies newProtocolSpecies = null;
        for(IacucProtocolSpecies protocolSpecies : newIacucProtocolSpeciesList) {
            String destUniqueSpecies = getUniqueSpecies(protocolSpecies);
            if(destUniqueSpecies.equals(uniqueSpeciesKey)) {
                newProtocolSpecies = protocolSpecies;
                break;
            }
        }
        return newProtocolSpecies;
    }

    /**
     * @see org.kuali.kra.iacuc.actions.copy.IacucProtocolCopyService#mergeProtocolSpeciesAndGroups(org.kuali.kra.iacuc.IacucProtocol, org.kuali.kra.iacuc.IacucProtocol)
     */
    @SuppressWarnings("unchecked")
    public void mergeProtocolSpeciesAndGroups(IacucProtocol srcProtocol, IacucProtocol destProtocol) {
        HashMap<String, IacucProtocolSpecies> currentProtocolSpeciesList = getSpeciesMapWithUniqueKey(destProtocol.getIacucProtocolSpeciesList());
        HashMap<String, IacucProtocolSpecies> amendedProtocolSpeciesList = getSpeciesMapWithUniqueKey(srcProtocol.getIacucProtocolSpeciesList());
        List<IacucProtocolSpecies> deletedProtocolSpeciesList = new ArrayList<IacucProtocolSpecies>();
        for(IacucProtocolSpecies currentProtocolSpecies : destProtocol.getIacucProtocolSpeciesList()) {
            String speciesKey = getUniqueSpecies(currentProtocolSpecies);
            IacucProtocolSpecies amendedProtocolSpecies = amendedProtocolSpeciesList.get(speciesKey);
            if(ObjectUtils.isNotNull(amendedProtocolSpecies)) {
                mergeProtocolSpecies(amendedProtocolSpecies, currentProtocolSpecies);
            }else {
                deletedProtocolSpeciesList.add(currentProtocolSpecies);
            }
        }
        verifyAndAddNewProtocolSpecies(destProtocol, currentProtocolSpeciesList, amendedProtocolSpeciesList);
        destProtocol.getIacucProtocolSpeciesList().removeAll(deletedProtocolSpeciesList);
        mergeProtocolProcedureSpecies(srcProtocol, destProtocol);
    }
    
    private void verifyAndAddNewProtocolSpecies(IacucProtocol destProtocol, HashMap<String, IacucProtocolSpecies> currentProtocolSpeciesList, 
            HashMap<String, IacucProtocolSpecies> amendedProtocolSpeciesList) {
        for (Map.Entry<String, IacucProtocolSpecies> entry : amendedProtocolSpeciesList.entrySet()) {    
            String speciesKey = entry.getKey();
            IacucProtocolSpecies amendedProtocolSpecies = entry.getValue();
            IacucProtocolSpecies currentProtocolSpecies = currentProtocolSpeciesList.get(speciesKey);
            if(ObjectUtils.isNull(currentProtocolSpecies)) {
                IacucProtocolSpecies newProtocolSpecies = getNewProtocolSpecies(amendedProtocolSpecies, destProtocol);
                destProtocol.getIacucProtocolSpeciesList().add(newProtocolSpecies);
            }
        }
    }
    
    private HashMap<String, IacucProtocolSpecies> getSpeciesMapWithUniqueKey(List<IacucProtocolSpecies> protocolSpeciesList) {
        HashMap<String, IacucProtocolSpecies> speciesMap = new HashMap<String, IacucProtocolSpecies>();
        for(IacucProtocolSpecies iacucProtocolSpecies : protocolSpeciesList) {
            String uniqueSpeciesKey = getUniqueSpecies(iacucProtocolSpecies);
            speciesMap.put(uniqueSpeciesKey, iacucProtocolSpecies);
        }
        return speciesMap;
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
    
    /**
     * This method is to get a unique key to compare protocol species
     * @param protocolSpecies
     * @return
     */
    private String getUniqueSpecies(IacucProtocolSpecies protocolSpecies) {
        return protocolSpecies.getSpeciesCode().toString().concat(protocolSpecies.getSpeciesGroup());
    }
    
    protected HashMap<Integer, Integer> getNewProtocolSpeciesMap(IacucProtocol protocol) {
        return getIacucProtocolSpeciesService().getNewProtocolSpeciesMap(protocol);
    }
    
    /**
     * This method is to copy protocol exceptions
     * @param sourceProtocolExceptions
     * @param destDoc
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
    
    @SuppressWarnings("unchecked")
    public void mergeProtocolProcedures(IacucProtocol srcProtocol, IacucProtocol destProtocol) {
        destProtocol.setIacucProtocolStudyGroups(((List<IacucProtocolStudyGroupBean>) deepCopy(srcProtocol.getIacucProtocolStudyGroups())));
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : destProtocol.getIacucProtocolStudyGroups()) {
            iacucProtocolStudyGroupBean.setIacucProtocolStudyGroupHeaderId(null);
            iacucProtocolStudyGroupBean.setProtocolId(destProtocol.getProtocolId());
            iacucProtocolStudyGroupBean.setProtocolNumber(destProtocol.getProtocolNumber());
            iacucProtocolStudyGroupBean.setSequenceNumber(destProtocol.getSequenceNumber());
            for(IacucProtocolStudyGroupDetailBean iacucProtocolStudyGroupDetailBean : iacucProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans()) {
                iacucProtocolStudyGroupDetailBean.setIacucProtocolStudyGroupDetailId(null);
                iacucProtocolStudyGroupDetailBean.setIacucProtocolStudyGroupHeaderId(null);
                for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroupDetailBean.getIacucProtocolStudyGroups()) {
                    iacucProtocolStudyGroup.setIacucProtocolStudyGroupId(null);
                    iacucProtocolStudyGroup.setIacucProtocolStudyGroupDetailId(null);
                    setNewProtocolSpeciesForStudyGroup(iacucProtocolStudyGroup, destProtocol.getIacucProtocolSpeciesList());
                    for(IacucProcedurePersonResponsible iacucProcedurePersonResponsible : iacucProtocolStudyGroup.getIacucProcedurePersonsResponsible()) {
                        iacucProcedurePersonResponsible.setIacucProcedurePersonResponsibleId(null);
                        iacucProcedurePersonResponsible.setIacucProtocolStudyGroupId(null);
                    }
                    for(IacucProtocolStudyGroupLocation iacucProtocolStudyGroupLocation : iacucProtocolStudyGroup.getIacucProtocolStudyGroupLocations()) {
                        iacucProtocolStudyGroupLocation.setIacucProtocolStudyGroupLocationId(null);
                        iacucProtocolStudyGroupLocation.setIacucProtocolStudyGroupId(null);
                        iacucProtocolStudyGroupLocation.setProtocolId(destProtocol.getProtocolId());
                        iacucProtocolStudyGroupLocation.setProtocolNumber(destProtocol.getProtocolNumber());
                        iacucProtocolStudyGroupLocation.setSequenceNumber(destProtocol.getSequenceNumber());
                    }
                    for(IacucProtocolStudyCustomData iacucProtocolStudyCustomData : iacucProtocolStudyGroup.getIacucProtocolStudyCustomDataList()) {
                        iacucProtocolStudyCustomData.setIacucProtocolStudyCustomDataId(null);
                        iacucProtocolStudyCustomData.setIacucProtocolStudyGroupId(null);
                        iacucProtocolStudyCustomData.setProtocolId(destProtocol.getProtocolId());
                        iacucProtocolStudyCustomData.setProtocolNumber(destProtocol.getProtocolNumber());
                        iacucProtocolStudyCustomData.setSequenceNumber(destProtocol.getSequenceNumber());
                    }
                }
            }
        }
    }
    
    /**
     * This method is to copy protocol procedures
     * @param sourceProtocolProcedures
     * @param destDoc
     * @param speciesIdMapping
     */
    protected void addProtocolProcedures(IacucProtocol srcProtocol, IacucProtocol destProtocol) { 
        destProtocol.setIacucProtocolStudyGroupBeans(new ArrayList<IacucProtocolStudyGroupBean>());
        destProtocol.setIacucProtocolStudyGroups(new ArrayList<IacucProtocolStudyGroupBean>());
        HashMap<Integer, Integer> newSpeciesIdMapping = getNewProtocolSpeciesMap(destProtocol);
        List<IacucProtocolStudyGroupBean> sourceProtocolProcedures = srcProtocol.getIacucProtocolStudyGroups();
        getIacucProtocolProcedureService().createNewStudyGroups(destProtocol, sourceProtocolProcedures, newSpeciesIdMapping);
    }
    
    @Override
    protected IacucProtocolNumberService getProtocolNumberServiceHook() {
        return (IacucProtocolNumberService)KraServiceLocator.getService("iacucProtocolNumberService");
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

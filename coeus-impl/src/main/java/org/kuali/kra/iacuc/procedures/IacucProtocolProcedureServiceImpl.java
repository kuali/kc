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
package org.kuali.kra.iacuc.procedures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.kuali.kra.iacuc.IacucPersonTraining;
import org.kuali.kra.iacuc.IacucProcedureCategoryCustomData;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.IacucSpecies;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonTrainingService;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;
import org.kuali.kra.iacuc.species.IacucProtocolSpeciesService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.ObjectUtils;



public class IacucProtocolProcedureServiceImpl implements IacucProtocolProcedureService {

    private BusinessObjectService businessObjectService;
    private ParameterService parameterService;
    private IacucProtocolPersonTrainingService iacucProtocolPersonTrainingService;
    private static final String PROTOCOL_STUDY_GROUP_HEADER_SEQUENCE_ID = "SEQ_IACUC_PROT_STUD_GRP_HDR_ID";
    private static final String PROCEDURE_VIEW_MODE = "PROCEDURE_VIEW_MODE";
    private static final String PROCEDURE_VIEW_MODE_SPECIES = "S";
     
    private IacucProtocolSpeciesService iacucProtocolSpeciesService;
    private SequenceAccessorService sequenceAccessorService;

    
    @Override
    public List<IacucProcedure> getAllProcedures() {
        return (List<IacucProcedure>)getBusinessObjectService().findAllOrderBy(IacucProcedure.class, "procedureCategoryCode", true);
    }

    @Override
    public List<IacucProcedureCategory> getAllProcedureCategories() {
        return (List<IacucProcedureCategory>)getBusinessObjectService().findAllOrderBy(IacucProcedureCategory.class, "procedureCategoryCode", true);
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public List<IacucProtocolSpecies> getProtocolSpecies() {
        Long protocolId = ((IacucProtocolForm) KNSGlobalVariables.getKualiForm()).getIacucProtocolDocument().getProtocol().getProtocolId();
        Map<String, Object> keyMap = new HashMap<String, Object> ();
        keyMap.put("protocolId", protocolId);
        return (List<IacucProtocolSpecies>) getBusinessObjectService().findMatching(IacucProtocolSpecies.class, keyMap);
    }
    
    @Override
    public List<IacucProtocolStudyGroupBean> getRevisedStudyGroupBeans(IacucProtocol iacucProtocol, List<IacucProcedure> allProcedures) {
        List<IacucProtocolStudyGroupBean> studyGroupBeans = iacucProtocol.getIacucProtocolStudyGroupBeans();
        if(studyGroupBeans.isEmpty()) {
            studyGroupBeans = getNewListOfStudyGroupBeans(iacucProtocol, allProcedures);
        }
        if(isProcedureViewedBySpecies()) {
            groupProcedureStudyBySpecies(iacucProtocol);
        }
        return studyGroupBeans;
    }
    
    /**
     * This method is to check if a procedure is persisted.
     * @param allProcedures
     * @param selectedProcedureCode
     */
    private void selectUsedProcedureCategory(List<IacucProcedure> allProcedures, Integer selectedProcedureCode) {
        for(IacucProcedure iacucProcedure : allProcedures) {
            if(iacucProcedure.getProcedureCode().equals(selectedProcedureCode)) {
                iacucProcedure.setProcedureSelected(true);
                break;
            }
        }
    }
    
    
    @Override
    public List<IacucPersonTraining> getIacucPersonTrainingDetails(String personId) {
        return getIacucProtocolPersonTrainingService().getIacucPersonTrainingDetails(personId);
    }

    @Override
    public void setTrainingDetails(IacucProtocol protocol) {
        for(ProtocolPersonBase protocolPerson : protocol.getProtocolPersons()) {
            IacucProtocolPerson iacucProtocolPerson = (IacucProtocolPerson)protocolPerson;
            iacucProtocolPerson.setIacucPersonTrainings(getIacucPersonTrainingDetails(iacucProtocolPerson.getPersonId()));
        }
    }
    
    /**
     * This method is to get all procedure categories and format study group bean to display in ui
     * A study group bean is created for each procedure/category code
     * @return
     */
    private List<IacucProtocolStudyGroupBean> getNewListOfStudyGroupBeans(IacucProtocol protocol, List<IacucProcedure> allProcedures) {
        List<IacucProtocolStudyGroupBean> studyGroupBeans = new ArrayList<IacucProtocolStudyGroupBean>();
        for(IacucProcedure iacucProcedure : getAllProcedures()) {
            IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean = getCurrentStudyGroupForProcedure(allProcedures, protocol, iacucProcedure);
            if(ObjectUtils.isNull(iacucProtocolStudyGroupBean)) {
                iacucProtocolStudyGroupBean = new IacucProtocolStudyGroupBean();
                iacucProtocolStudyGroupBean.setProcedureCategoryCode(iacucProcedure.getProcedureCategoryCode());
                iacucProtocolStudyGroupBean.setProcedureCode(iacucProcedure.getProcedureCode());
            }
            studyGroupBeans.add(iacucProtocolStudyGroupBean);
        }
        return studyGroupBeans;
    }
    
    /**
     * This method is to get matching study group for a given procedure
     * @param protocol
     * @param iacucProcedure
     * @return
     */
    private IacucProtocolStudyGroupBean getCurrentStudyGroupForProcedure(List<IacucProcedure> allProcedures, IacucProtocol protocol, IacucProcedure iacucProcedure) {
        List<IacucProtocolStudyGroupBean> iacucProtocolStudyGroups = protocol.getIacucProtocolStudyGroups();
        IacucProtocolStudyGroupBean currentStudyGroup = null;
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroup : iacucProtocolStudyGroups) {
            if(iacucProtocolStudyGroup.getProcedureCategoryCode().equals(iacucProcedure.getProcedureCategoryCode()) && 
                    iacucProtocolStudyGroup.getProcedureCode().equals(iacucProcedure.getProcedureCode())) {
                currentStudyGroup = iacucProtocolStudyGroup;
                selectUsedProcedureCategory(allProcedures, iacucProtocolStudyGroup.getProcedureCode());
                break;
            }
        }
        return currentStudyGroup;
    }
    
    /**
     * This method is to get custom data attributes for each procedure category
     * @return
     */
    private List<IacucProcedureCategoryCustomData> getIacucProcedureCustomData(Integer procedureCategoryCode) {
        Map<String, Integer> matchingKey = new HashMap<String, Integer>();
        matchingKey.put("procedureCategoryCode", procedureCategoryCode);
        return (List<IacucProcedureCategoryCustomData>)getBusinessObjectService().findMatchingOrderBy(IacucProcedureCategoryCustomData.class, matchingKey, "sortId", true);
    }
    
    /**
     * This method is to get the next sequence number
     * This is the primary key
     * @return
     */
    private Integer getNextSequenceNumber(String sequenceKey, Class clazz) {
        return getSequenceAccessorService().getNextAvailableSequenceNumber(sequenceKey, clazz).intValue();
    }

    public SequenceAccessorService getSequenceAccessorService() {
        return sequenceAccessorService;
    }

    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public IacucProtocolPersonTrainingService getIacucProtocolPersonTrainingService() {
        return iacucProtocolPersonTrainingService;
    }

    public void setIacucProtocolPersonTrainingService(IacucProtocolPersonTrainingService iacucProtocolPersonTrainingService) {
        this.iacucProtocolPersonTrainingService = iacucProtocolPersonTrainingService;
    }

    public IacucProtocolSpeciesService getIacucProtocolSpeciesService() {
        return iacucProtocolSpeciesService;
    }

    public void setIacucProtocolSpeciesService(IacucProtocolSpeciesService iacucProtocolSpeciesService) {
        this.iacucProtocolSpeciesService = iacucProtocolSpeciesService;
    }

    @Override
    public void addProtocolStudyGroup(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, IacucProtocol iacucProtocol) {
        boolean isNewCategoryBean = ObjectUtils.isNull(selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId());
        List<String> protocolSpeciesAndGroups =  selectedProtocolStudyGroupBean.getProtocolSpeciesAndGroups(); 
        if(isNewCategoryBean) {
            setAttributesForNewStudyGroupBean(selectedProtocolStudyGroupBean, iacucProtocol);
            iacucProtocol.getIacucProtocolStudyGroups().add(selectedProtocolStudyGroupBean);
        }
        List<IacucProtocolStudyGroup> newStudyGroups = getNewProtocolStudyGroups(protocolSpeciesAndGroups, iacucProtocol);
        addProcedureCustomData(selectedProtocolStudyGroupBean, newStudyGroups, iacucProtocol);
        selectedProtocolStudyGroupBean.getIacucProtocolStudyGroups().addAll(newStudyGroups);
        
        groupProcedureStudyBySpecies(selectedProtocolStudyGroupBean);
    }

    /**
     * This method is to rearrange procedures by species
     * @param iacucProtocol
     */
    private void groupProcedureStudyBySpecies(IacucProtocol iacucProtocol) {
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : iacucProtocol.getIacucProtocolStudyGroups()) {
            groupProcedureStudyBySpecies(iacucProtocolStudyGroupBean);
        }
    }
    
    /**
     * This method is to rearrange study details to group by species
     * @param selectedProtocolStudyGroupBean
     */
    private void groupProcedureStudyBySpecies(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean) {
        List<IacucProtocolSpeciesStudyGroup> iacucProtocolSpeciesStudyGroups = getListOfProcedureStudyBySpecies(selectedProtocolStudyGroupBean.getIacucProtocolStudyGroups());
        selectedProtocolStudyGroupBean.setIacucProtocolSpeciesStudyGroups(iacucProtocolSpeciesStudyGroups);
    }
    
    /**
     * This method is to build procedures grouped by species
     * @param iacucProtocolStudyGroups
     * @return
     */
    private List<IacucProtocolSpeciesStudyGroup> getListOfProcedureStudyBySpecies(List<IacucProtocolStudyGroup> iacucProtocolStudyGroups) {
        Map<IacucSpecies,IacucProtocolSpeciesStudyGroup> protocolSpeciesStudyGroups = new HashMap<IacucSpecies,IacucProtocolSpeciesStudyGroup>();
        List<IacucProtocolSpeciesStudyGroup> iacucProtocolSpeciesStudyGroups = new ArrayList<IacucProtocolSpeciesStudyGroup>();
        for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroups) {
            IacucSpecies iacucSpecies = iacucProtocolStudyGroup.getIacucProtocolSpecies().getIacucSpecies();
            IacucProtocolSpeciesStudyGroup iacucProtocolSpeciesStudyGroup = protocolSpeciesStudyGroups.get(iacucSpecies);
            if(ObjectUtils.isNull(iacucProtocolSpeciesStudyGroup)) {
                iacucProtocolSpeciesStudyGroup = new IacucProtocolSpeciesStudyGroup();
                iacucProtocolSpeciesStudyGroup.setSpeciesCode(iacucSpecies.getSpeciesCode());
                iacucProtocolSpeciesStudyGroup.setIacucSpecies(iacucSpecies);
                iacucProtocolSpeciesStudyGroup.setIacucProtocolStudyGroup(iacucProtocolStudyGroup);
                iacucProtocolSpeciesStudyGroups.add(iacucProtocolSpeciesStudyGroup);
                protocolSpeciesStudyGroups.put(iacucSpecies, iacucProtocolSpeciesStudyGroup);
            }
            iacucProtocolSpeciesStudyGroup.getIacucProtocolStudyGroups().add(iacucProtocolStudyGroup);
        }
        return iacucProtocolSpeciesStudyGroups;
    }

    /**
     * This method is to build procedures arranged by protocol species group
     * @param iacucProtocolStudyGroups
     * @return
     */
    private List<IacucProtocolSpeciesStudyGroup> getListOfProcedureStudyBySpeciesGroup(List<IacucProtocolStudyGroup> iacucProtocolStudyGroups) {
        Map<IacucProtocolSpecies,IacucProtocolSpeciesStudyGroup> protocolSpeciesStudyGroups = new HashMap<IacucProtocolSpecies,IacucProtocolSpeciesStudyGroup>();
        List<IacucProtocolSpeciesStudyGroup> iacucProtocolSpeciesStudyGroups = new ArrayList<IacucProtocolSpeciesStudyGroup>();
        for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroups) {
            IacucProtocolSpecies iacucProtocolSpecies = iacucProtocolStudyGroup.getIacucProtocolSpecies();
            IacucProtocolSpeciesStudyGroup iacucProtocolSpeciesStudyGroup = protocolSpeciesStudyGroups.get(iacucProtocolSpecies);
            if(ObjectUtils.isNull(iacucProtocolSpeciesStudyGroup)) {
                iacucProtocolSpeciesStudyGroup = new IacucProtocolSpeciesStudyGroup();
                iacucProtocolSpeciesStudyGroup.setIacucProtocolSpeciesId(iacucProtocolSpecies.getIacucProtocolSpeciesId());
                iacucProtocolSpeciesStudyGroup.setIacucProtocolSpecies(iacucProtocolSpecies);
                iacucProtocolSpeciesStudyGroup.setSpeciesCode(iacucProtocolSpecies.getIacucSpecies().getSpeciesCode());
                iacucProtocolSpeciesStudyGroup.setIacucSpecies(iacucProtocolSpecies.getIacucSpecies());
                iacucProtocolSpeciesStudyGroup.setIacucProtocolStudyGroup(iacucProtocolStudyGroup);
                iacucProtocolSpeciesStudyGroup.setTotalSpeciesCount(iacucProtocolSpecies.getSpeciesCount());
                iacucProtocolSpeciesStudyGroups.add(iacucProtocolSpeciesStudyGroup);
                protocolSpeciesStudyGroups.put(iacucProtocolSpecies, iacucProtocolSpeciesStudyGroup);
            }
            iacucProtocolSpeciesStudyGroup.getIacucProtocolStudyGroups().add(iacucProtocolStudyGroup);
        }
        return iacucProtocolSpeciesStudyGroups;
    }
    
    @Override
    public void populateIacucSpeciesPersonProcedures(IacucProtocol iacucProtocol) {
        List<IacucProtocolStudyGroup> iacucProtocolStudyGroups = getAllProcedureStudyGroups(iacucProtocol);
        if(isProcedureViewedBySpecies()) {
            setPersonProceduresBySpecies(iacucProtocol, iacucProtocolStudyGroups);
        }else {
            setPersonProceduresByGroups(iacucProtocol, iacucProtocolStudyGroups);
        }
    }
    
    /**
     * This method is to set procedures handled by person arranged by species
     * @param iacucProtocol
     * @param iacucProtocolStudyGroups
     */
    private void setPersonProceduresBySpecies(IacucProtocol iacucProtocol, List<IacucProtocolStudyGroup> iacucProtocolStudyGroups) {
        for(ProtocolPersonBase protocolPerson : iacucProtocol.getProtocolPersons()) {
            List<IacucProtocolSpeciesStudyGroup> iacucProtocolSpeciesStudyGroups = getListOfProcedureStudyBySpecies(iacucProtocolStudyGroups);
            IacucProtocolPerson iacucProtocolPerson = (IacucProtocolPerson)protocolPerson;
            iacucProtocolPerson.setProcedureDetails(getPersonProcedureDetails(iacucProtocolSpeciesStudyGroups, iacucProtocolPerson));
            iacucProtocolPerson.setAllProceduresSelected(isAllProceduresChecked(iacucProtocolPerson.getProcedureDetails()));
        }
    }

    /**
     * This method is to set procedures handled by person arranged by groups
     * @param iacucProtocol
     * @param iacucProtocolStudyGroups
     */
    private void setPersonProceduresByGroups(IacucProtocol iacucProtocol, List<IacucProtocolStudyGroup> iacucProtocolStudyGroups) {
        for(ProtocolPersonBase protocolPerson : iacucProtocol.getProtocolPersons()) {
            List<IacucProtocolSpeciesStudyGroup> iacucProtocolSpeciesStudyGroups = getListOfProcedureStudyBySpeciesGroup(iacucProtocolStudyGroups);
            IacucProtocolPerson iacucProtocolPerson = (IacucProtocolPerson)protocolPerson;
            iacucProtocolPerson.setProcedureDetails(getPersonProcedureDetails(iacucProtocolSpeciesStudyGroups, iacucProtocolPerson));
            iacucProtocolPerson.setAllProceduresSelected(isAllProceduresChecked(iacucProtocolPerson.getProcedureDetails()));
        }
    }
    
    /**
     * This method is to tag whether all procedures are marked in a group and at the top level
     * @param iacucProtocolSpeciesStudyGroups
     * @return
     */
    private boolean isAllProceduresChecked(List<IacucProtocolSpeciesStudyGroup> iacucProtocolSpeciesStudyGroups) {
        boolean allProceduresSelected = true; 
        for(IacucProtocolSpeciesStudyGroup iacucProtocolSpeciesStudyGroup : iacucProtocolSpeciesStudyGroups) {
            iacucProtocolSpeciesStudyGroup.setAllProceduresSelected(true);
            for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : iacucProtocolSpeciesStudyGroup.getResponsibleProcedures()) {
                if(!iacucProtocolStudyGroupBean.isProcedureSelected()) {
                    allProceduresSelected = false;
                    iacucProtocolSpeciesStudyGroup.setAllProceduresSelected(false);
                    break;
                }
            }
        }
        return allProceduresSelected;
    }
    
    /**
     * This method is to get procedure details performed by each person
     * @param iacucProtocolSpeciesStudyGroups
     * @param iacucProtocolPerson
     * @return
     */
    private List<IacucProtocolSpeciesStudyGroup> getPersonProcedureDetails(List<IacucProtocolSpeciesStudyGroup> iacucProtocolSpeciesStudyGroups, IacucProtocolPerson iacucProtocolPerson) {
        List<IacucProtocolSpeciesStudyGroup> personProcedureDetails = new ArrayList<IacucProtocolSpeciesStudyGroup>();
        for(IacucProtocolSpeciesStudyGroup iacucProtocolSpeciesStudyGroup : iacucProtocolSpeciesStudyGroups) {
            HashSet<Integer> studyGroupProcedures = new HashSet<Integer>();
            List<IacucProtocolStudyGroupBean> responsibleProcedures = new ArrayList<IacucProtocolStudyGroupBean>();
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolSpeciesStudyGroup.getIacucProtocolStudyGroups()) {
                if(studyGroupProcedures.add(iacucProtocolStudyGroup.getIacucProtocolStudyGroupHeaderId())) {
                    IacucProtocolStudyGroupBean newIacucProtocolStudyGroupBean = getNewCopyOfStudyGroupBean(iacucProtocolStudyGroup.getIacucProtocolStudyGroupBean());
                    if(isPersonResponsibleForProcedure(iacucProtocolPerson, iacucProtocolStudyGroup)) {
                        newIacucProtocolStudyGroupBean.setProcedureSelected(true);
                        newIacucProtocolStudyGroupBean.setNewProcedure(false);
                    }
                    responsibleProcedures.add(newIacucProtocolStudyGroupBean);
                }
            }
            iacucProtocolSpeciesStudyGroup.setResponsibleProcedures(responsibleProcedures);
            personProcedureDetails.add(iacucProtocolSpeciesStudyGroup);
        }
        return personProcedureDetails;
    }

    @Override
    public void populateIacucSpeciesLocationProcedures(IacucProtocol iacucProtocol) {
        populateProcedureStudyGroupLocations(iacucProtocol);
        populateSpeciesLocationProcedures(iacucProtocol);
    }
    
    private void populateSpeciesLocationProcedures(IacucProtocol iacucProtocol) {
        List<IacucProtocolStudyGroup> iacucProtocolStudyGroups = getAllProcedureStudyGroups(iacucProtocol);
        if(isProcedureViewedBySpecies()) {
            setLocationProceduresBySpecies(iacucProtocol, iacucProtocolStudyGroups);
        }else {
            setLocationProceduresByGroups(iacucProtocol, iacucProtocolStudyGroups);
        }
        
    }

    /**
     * This method is to set procedures handled in each location arranged by species
     * @param iacucProtocol
     * @param iacucProtocolStudyGroups
     */
    private void setLocationProceduresBySpecies(IacucProtocol iacucProtocol, List<IacucProtocolStudyGroup> iacucProtocolStudyGroups) {
        for(IacucProtocolStudyGroupLocation iacucProtocolStudyGroupLocation : iacucProtocol.getIacucProtocolStudyGroupLocations()) {
            List<IacucProtocolSpeciesStudyGroup> iacucProtocolSpeciesStudyGroups = getListOfProcedureStudyBySpecies(iacucProtocolStudyGroups);
            iacucProtocolStudyGroupLocation.setProcedureDetails(getLocationProcedureDetails(iacucProtocolSpeciesStudyGroups, iacucProtocolStudyGroupLocation));
            iacucProtocolStudyGroupLocation.setAllProceduresSelected(isAllProceduresChecked(iacucProtocolStudyGroupLocation.getProcedureDetails()));
        }
    }

    /**
     * This method is to set procedures handled in each location arranged by groups
     * @param iacucProtocol
     * @param iacucProtocolStudyGroups
     */
    private void setLocationProceduresByGroups(IacucProtocol iacucProtocol, List<IacucProtocolStudyGroup> iacucProtocolStudyGroups) {
        for(IacucProtocolStudyGroupLocation iacucProtocolStudyGroupLocation : iacucProtocol.getIacucProtocolStudyGroupLocations()) {
            List<IacucProtocolSpeciesStudyGroup> iacucProtocolSpeciesStudyGroups = getListOfProcedureStudyBySpeciesGroup(iacucProtocolStudyGroups);
            iacucProtocolStudyGroupLocation.setProcedureDetails(getLocationProcedureDetails(iacucProtocolSpeciesStudyGroups, iacucProtocolStudyGroupLocation));
            iacucProtocolStudyGroupLocation.setAllProceduresSelected(isAllProceduresChecked(iacucProtocolStudyGroupLocation.getProcedureDetails()));
        }
    }
    
    /**
     * This method is to get procedure details performed at each location
     * @param iacucProtocolSpeciesStudyGroups
     * @param iacucProtocolStudyGroupLocation
     * @return
     */
    private List<IacucProtocolSpeciesStudyGroup> getLocationProcedureDetails(List<IacucProtocolSpeciesStudyGroup> iacucProtocolSpeciesStudyGroups, 
            IacucProtocolStudyGroupLocation iacucProtocolStudyGroupLocation) {
        List<IacucProtocolSpeciesStudyGroup> locationProcedureDetails = new ArrayList<IacucProtocolSpeciesStudyGroup>();
        for(IacucProtocolSpeciesStudyGroup iacucProtocolSpeciesStudyGroup : iacucProtocolSpeciesStudyGroups) {
            HashSet<Integer> studyGroupProcedures = new HashSet<Integer>();
            List<IacucProtocolStudyGroupBean> responsibleProcedures = new ArrayList<IacucProtocolStudyGroupBean>();
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolSpeciesStudyGroup.getIacucProtocolStudyGroups()) {
                if(studyGroupProcedures.add(iacucProtocolStudyGroup.getIacucProtocolStudyGroupHeaderId())) {
                    IacucProtocolStudyGroupBean newIacucProtocolStudyGroupBean = getNewCopyOfStudyGroupBean(iacucProtocolStudyGroup.getIacucProtocolStudyGroupBean());
                    if(isLocationResponsibleForProcedure(iacucProtocolStudyGroupLocation, iacucProtocolStudyGroup)) {
                        newIacucProtocolStudyGroupBean.setProcedureSelected(true);
                        newIacucProtocolStudyGroupBean.setNewProcedure(false);
                    }
                    responsibleProcedures.add(newIacucProtocolStudyGroupBean);
                }
            }
            iacucProtocolSpeciesStudyGroup.setResponsibleProcedures(responsibleProcedures);
            locationProcedureDetails.add(iacucProtocolSpeciesStudyGroup);
        }
        return locationProcedureDetails;
    }
    
    /**
     * This method is to populate distinct study group locations
     * @param iacucProtocol
     */
    private void populateProcedureStudyGroupLocations(IacucProtocol iacucProtocol) {
        List<IacucProtocolStudyGroup> iacucProtocolStudyGroups = getAllProcedureStudyGroups(iacucProtocol);
        List<IacucProtocolStudyGroupLocation> iacucProtocolStudyGroupLocations = new ArrayList<IacucProtocolStudyGroupLocation>();
        HashSet<Integer> studyLocations = new HashSet<Integer>();
        for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroups) {
            for(IacucProtocolStudyGroupLocation iacucProtocolStudyGroupLocation : iacucProtocolStudyGroup.getIacucProcedureLocationResponsibleList()) {
                if(studyLocations.add(iacucProtocolStudyGroupLocation.getStudyGroupLocationId())) {
                    iacucProtocolStudyGroupLocations.add(iacucProtocolStudyGroupLocation);
                }
            }
        }
        iacucProtocol.setIacucProtocolStudyGroupLocations(iacucProtocolStudyGroupLocations);
    }
    
    /**
     * This method is to get all procedure study groups
     * @param iacucProtocol
     * @return
     */
    private List<IacucProtocolStudyGroup> getAllProcedureStudyGroups(IacucProtocol iacucProtocol) {
        List<IacucProtocolStudyGroup> iacucProtocolStudyGroup = new ArrayList<IacucProtocolStudyGroup>();
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : iacucProtocol.getIacucProtocolStudyGroups()) {
            iacucProtocolStudyGroup.addAll(iacucProtocolStudyGroupBean.getIacucProtocolStudyGroups());
        }
        return iacucProtocolStudyGroup;
    }
    
    /**
     * This method is to get a new copy of current study group bean.
     * This is used to identify the procedure performed for species
     * @param iacucProtocolStudyGroupBean
     * @return
     */
    private IacucProtocolStudyGroupBean getNewCopyOfStudyGroupBean(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean) {
        IacucProtocolStudyGroupBean newIacucProtocolStudyGroupBean = new IacucProtocolStudyGroupBean();
        newIacucProtocolStudyGroupBean.setProcedureCode(iacucProtocolStudyGroupBean.getProcedureCode());
        newIacucProtocolStudyGroupBean.setProcedureCategoryCode(iacucProtocolStudyGroupBean.getProcedureCategoryCode());
        newIacucProtocolStudyGroupBean.setIacucProcedure(iacucProtocolStudyGroupBean.getIacucProcedure());
        newIacucProtocolStudyGroupBean.setIacucProcedureCategory(iacucProtocolStudyGroupBean.getIacucProcedureCategory());
        newIacucProtocolStudyGroupBean.setProcedureSelected(false);
        newIacucProtocolStudyGroupBean.setNewProcedure(true);
        return newIacucProtocolStudyGroupBean;
    }
    
    /**
     * This method is to verify whether person is responsible for a procedure
     * @param iacucProtocolPerson
     * @param iacucProtocolStudyGroup
     * @return
     */
    private boolean isPersonResponsibleForProcedure(IacucProtocolPerson iacucProtocolPerson, IacucProtocolStudyGroup iacucProtocolStudyGroup) {
        boolean personResponsibleForProcedure = false;
        for(IacucProcedurePersonResponsible IacucProcedurePersonResponsible : iacucProtocolStudyGroup.getIacucProcedurePersonResponsibleList()) {
            if(IacucProcedurePersonResponsible.getProtocolPersonId().equals(iacucProtocolPerson.getProtocolPersonId())) {
                personResponsibleForProcedure = true;
                break;
            }
        }
        return personResponsibleForProcedure;
    }

    /**
     * This method is to verify whether a procedure is assigned to a location
     * @param iacucProtocolStudyGroupLocation
     * @param iacucProtocolStudyGroup
     * @return
     */
    private boolean isLocationResponsibleForProcedure(IacucProtocolStudyGroupLocation iacucProtocolStudyGroupLocation, IacucProtocolStudyGroup iacucProtocolStudyGroup) {
        boolean locationResponsibleForProcedure = false;
        for(IacucProtocolStudyGroupLocation iacucProcedureLocationResponsible : iacucProtocolStudyGroup.getIacucProcedureLocationResponsibleList()) {
            if(iacucProcedureLocationResponsible.getStudyGroupLocationId().equals(iacucProtocolStudyGroupLocation.getStudyGroupLocationId())) {
                locationResponsibleForProcedure = true;
                break;
            }
        }
        return locationResponsibleForProcedure;
    }
    
    @Override
    public void synchronizeProtocolStudyGroups(IacucProtocol iacucProtocol) {
        if(isProcedureViewedBySpecies()) {
            List<IacucProtocolStudyGroupLocation> newProtocolStudyLocationList = iacucProtocol.getIacucProtocolStudyGroupLocations();
            for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : iacucProtocol.getIacucProtocolStudyGroups()) {
                synchronizeProtocolStudyGroups(iacucProtocolStudyGroupBean);
                synchronizeProcedureLocationList(iacucProtocolStudyGroupBean, newProtocolStudyLocationList);
            }
        }
    }
    
    /**
     * This method is to update study details collection that are grouped by species
     * @param selectedProtocolStudyGroupBean
     */
    private void synchronizeProtocolStudyGroups(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean) {
        for(IacucProtocolSpeciesStudyGroup iacucProtocolSpeciesStudyGroup : selectedProtocolStudyGroupBean.getIacucProtocolSpeciesStudyGroups()) {
            IacucProtocolStudyGroup newIacucProtocolStudyGroup = iacucProtocolSpeciesStudyGroup.getIacucProtocolStudyGroup();
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolSpeciesStudyGroup.getIacucProtocolStudyGroups()) {
                iacucProtocolStudyGroup.setPainCategoryCode(newIacucProtocolStudyGroup.getPainCategoryCode());
                iacucProtocolStudyGroup.setCount(newIacucProtocolStudyGroup.getCount());
                synchronizeProcedureCustomDataList(newIacucProtocolStudyGroup.getIacucProtocolStudyCustomDataList(), iacucProtocolStudyGroup);
            }
        }
    }

    /**
     * This method is to update custom data list grouped by species
     */
    private void synchronizeProcedureCustomDataList(List<IacucProtocolStudyCustomData> newProtocolStudyCustomDataList, IacucProtocolStudyGroup iacucProtocolStudyGroup) {
        for(IacucProtocolStudyCustomData newIacucProtocolStudyCustomData : newProtocolStudyCustomDataList) {
            for(IacucProtocolStudyCustomData iacucProtocolStudyCustomData : iacucProtocolStudyGroup.getIacucProtocolStudyCustomDataList()) {
                if(iacucProtocolStudyCustomData.getProcedureCustomAttributeId().equals(newIacucProtocolStudyCustomData.getProcedureCustomAttributeId())) {
                    iacucProtocolStudyCustomData.setValue(newIacucProtocolStudyCustomData.getValue());
                }
            }
        }
    }

    /**
     * This method is to update location list grouped by species
     * @param iacucProtocolStudyGroupBean
     * @param newProtocolStudyLocationList
     */
    private void synchronizeProcedureLocationList(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean , List<IacucProtocolStudyGroupLocation> newProtocolStudyLocationList) {
        for(IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation : newProtocolStudyLocationList) {
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroupBean.getIacucProtocolStudyGroups()) {
                for(IacucProtocolStudyGroupLocation iacucProtocolStudyGroupLocation : iacucProtocolStudyGroup.getIacucProcedureLocationResponsibleList()) {
                    if(iacucProtocolStudyGroupLocation.getStudyGroupLocationId().equals(newIacucProtocolStudyGroupLocation.getStudyGroupLocationId())) {
                        iacucProtocolStudyGroupLocation.setLocationTypeCode(newIacucProtocolStudyGroupLocation.getLocationTypeCode());
                        iacucProtocolStudyGroupLocation.setLocationId(newIacucProtocolStudyGroupLocation.getLocationId());
                        iacucProtocolStudyGroupLocation.setStudyGroupLocationDescription(newIacucProtocolStudyGroupLocation.getStudyGroupLocationDescription());
                        iacucProtocolStudyGroupLocation.setLocationRoom(newIacucProtocolStudyGroupLocation.getLocationRoom());
                    }
                }
            }
        }
    }
    
    /**
     * This method is to get a list of new protocol study groups.
     * @param protocol
     * @param protocolSpeciesAndGroups
     * @return a new set of protocol study groups based on selected group and species
     */
    private List<IacucProtocolStudyGroup> getNewProtocolStudyGroups(List<String> protocolSpeciesAndGroups, 
            IacucProtocol protocol) {
        List<IacucProtocolStudyGroup> protocolStudyGroups = new ArrayList<IacucProtocolStudyGroup>();
        for(String iacucProtocolSpeciesId : protocolSpeciesAndGroups) {
            IacucProtocolStudyGroup iacucProtocolStudyGroup = new IacucProtocolStudyGroup();
            iacucProtocolStudyGroup.setIacucProtocolSpeciesId(Integer.parseInt(iacucProtocolSpeciesId));
            iacucProtocolStudyGroup.setCount(iacucProtocolStudyGroup.getIacucProtocolSpecies().getSpeciesCount());
            iacucProtocolStudyGroup.setPainCategoryCode(iacucProtocolStudyGroup.getIacucProtocolSpecies().getPainCategoryCode());
            iacucProtocolStudyGroup.setIacucPainCategory(iacucProtocolStudyGroup.getIacucPainCategory());
            protocolStudyGroups.add(iacucProtocolStudyGroup);
        }
        return protocolStudyGroups;
    }
    
    /**
     * This method is to add procedure custom data to each new study group.
     * Custom data attributes are added based on attributes configured for each procedure code.
     * @param selectedIacucProtocolStudyGroupBean
     * @param newStudyGroups
     */
    private void addProcedureCustomData(IacucProtocolStudyGroupBean selectedIacucProtocolStudyGroupBean, 
            List<IacucProtocolStudyGroup> newStudyGroups, IacucProtocol protocol) {
        List<IacucProcedureCategoryCustomData> procedureCustomDataList = getIacucProcedureCustomData(selectedIacucProtocolStudyGroupBean.getProcedureCategoryCode());
        for(IacucProtocolStudyGroup iacucProtocolStudyGroup : newStudyGroups) {
            List<IacucProtocolStudyCustomData> protocolStudyCustomDataList = new ArrayList<IacucProtocolStudyCustomData>();
            for(IacucProcedureCategoryCustomData procedureCategoryCustomData : procedureCustomDataList) {
                if(procedureCategoryCustomData.isActive()) {
                    IacucProtocolStudyCustomData newIacucProtocolStudyCustomData = new IacucProtocolStudyCustomData();
                    newIacucProtocolStudyCustomData.setProcedureCustomAttributeId(procedureCategoryCustomData.getId());
                    newIacucProtocolStudyCustomData.setIacucProcedureCategoryCustomData(procedureCategoryCustomData);
                    protocolStudyCustomDataList.add(newIacucProtocolStudyCustomData);
                }
            }
            iacucProtocolStudyGroup.getIacucProtocolStudyCustomDataList().addAll(protocolStudyCustomDataList);
        }
    }
    
    /**
     * This method is to set attribute values for new study group bean (header)
     * @param selectedIacucProtocolStudyGroupBean
     * @param protocol
     */
    private void setAttributesForNewStudyGroupBean(IacucProtocolStudyGroupBean selectedIacucProtocolStudyGroupBean, IacucProtocol protocol) {
        selectedIacucProtocolStudyGroupBean.setIacucProtocolStudyGroupHeaderId(getNextSequenceNumber(PROTOCOL_STUDY_GROUP_HEADER_SEQUENCE_ID, selectedIacucProtocolStudyGroupBean.getClass()));
        selectedIacucProtocolStudyGroupBean.setProtocolId(protocol.getProtocolId());
        selectedIacucProtocolStudyGroupBean.setProtocolNumber(protocol.getProtocolNumber());
        selectedIacucProtocolStudyGroupBean.setSequenceNumber(protocol.getSequenceNumber());
    }
    
    @Override
    public void deleteProtocolStudyGroup(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, 
            IacucProtocolStudyGroup deletedIacucProtocolStudyGroup, IacucProtocol iacucProtocol) {
        selectedProtocolStudyGroupBean.getIacucProtocolStudyGroups().remove(deletedIacucProtocolStudyGroup);
        if(selectedProtocolStudyGroupBean.getIacucProtocolStudyGroups().size() == 0) {
            iacucProtocol.getIacucProtocolStudyGroups().remove(selectedProtocolStudyGroupBean);
        }
    }

    @Override
    public void deleteProcedureGroupPersonResponsible(IacucProtocolStudyGroup selectedProtocolStudyGroup, IacucProcedurePersonResponsible deletedProcedurePersonResponsible, 
            IacucProtocol iacucProtocol) {
        selectedProtocolStudyGroup.getIacucProcedurePersonResponsibleList().remove(deletedProcedurePersonResponsible);
    }
    
    @Override
    public void deleteProcedureGroupLocation(IacucProtocolStudyGroup selectedProtocolStudyGroup, IacucProtocolStudyGroupLocation deletedProtocolStudyGroupLocation, 
            IacucProtocol iacucProtocol) {
        selectedProtocolStudyGroup.getIacucProcedureLocationResponsibleList().remove(deletedProtocolStudyGroupLocation);
    }
    
    @Override
    public void deleteProtocolStudyGroup(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, IacucProtocolSpeciesStudyGroup deletedIacucProtocolStudyGroup) {
        selectedProtocolStudyGroupBean.getIacucProtocolStudyGroups().removeAll(deletedIacucProtocolStudyGroup.getIacucProtocolStudyGroups());
        selectedProtocolStudyGroupBean.getIacucProtocolSpeciesStudyGroups().remove(deletedIacucProtocolStudyGroup);
    }
    
    @Override
    public void addProcedureLocation(IacucProtocolStudyGroupLocation newStudyGroupLocation, IacucProtocol protocol) {
        updateAttributesForNewProcedureLocation(newStudyGroupLocation, protocol);
        protocol.getIacucProtocolStudyGroupLocations().add(newStudyGroupLocation);
        populateSpeciesLocationProcedures(protocol);
    }

    @Override
    public void addProcedureGroupLocation(IacucProtocolStudyGroupLocation newStudyGroupLocation, IacucProtocolStudyGroup selectedStudyGroup, IacucProtocol protocol) {
        populateProcedureStudyGroupLocations(protocol);
        updateAttributesForNewProcedureLocation(newStudyGroupLocation, protocol);
        protocol.getIacucProtocolStudyGroupLocations().add(newStudyGroupLocation);
        selectedStudyGroup.getIacucProcedureLocationResponsibleList().add(newStudyGroupLocation);
        selectedStudyGroup.setNewIacucProtocolStudyGroupLocation(new IacucProtocolStudyGroupLocation());
    }
    
    @Override
    public void deleteProcedureLocation(IacucProtocolStudyGroupLocation deletedIacucProtocolStudyGroupLocation, IacucProtocol iacucProtocol) {
        deleteProcedureLocationList(iacucProtocol, deletedIacucProtocolStudyGroupLocation);
        iacucProtocol.getIacucProtocolStudyGroupLocations().remove(deletedIacucProtocolStudyGroupLocation);
    }
    
    /**
     * This method is to remove deleted location from study group list
     * @param protocol
     * @param deletedProtocolStudyGroupLocation
     */
    private void deleteProcedureLocationList(IacucProtocol protocol, IacucProtocolStudyGroupLocation deletedProtocolStudyGroupLocation) {
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : protocol.getIacucProtocolStudyGroups()) {
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroupBean.getIacucProtocolStudyGroups()) {
                List<IacucProtocolStudyGroupLocation> deletedProtocolStudyGroupLocations = new ArrayList<IacucProtocolStudyGroupLocation>();
                for(IacucProtocolStudyGroupLocation iacucProtocolStudyGroupLocation : iacucProtocolStudyGroup.getIacucProcedureLocationResponsibleList()) {
                    if(iacucProtocolStudyGroupLocation.getStudyGroupLocationId().equals(deletedProtocolStudyGroupLocation.getStudyGroupLocationId())) {
                        deletedProtocolStudyGroupLocations.add(iacucProtocolStudyGroupLocation);
                    }
                }
                iacucProtocolStudyGroup.getIacucProcedureLocationResponsibleList().removeAll(deletedProtocolStudyGroupLocations);
            }
        }
    }
    
    /**
     * This method is to set study group reference.
     */
    private void updateAttributesForNewProcedureLocation(IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation, 
            IacucProtocol protocol) { 
        newIacucProtocolStudyGroupLocation.setStudyGroupLocationId(getNextStudyGroupLocationId(protocol));
    }
 
    /**
     * This method is to get the next study group location id.
     * generate a serial number generated based on the list of study group locations
     * Use this to identify distinct locations
     * @param iacucProtocol
     * @return
     */
    private Integer getNextStudyGroupLocationId(IacucProtocol iacucProtocol) {
        Integer nextStudyGroupLocationId = 1;
        if(!iacucProtocol.getIacucProtocolStudyGroupLocations().isEmpty()) {
            List<IacucProtocolStudyGroupLocation> sortedStudyGroupLocations = getSortedStudyGroupLocations(iacucProtocol);
            int totalStudyGroupLocs = sortedStudyGroupLocations.size();
            nextStudyGroupLocationId = sortedStudyGroupLocations.get(totalStudyGroupLocs - 1).getStudyGroupLocationId() + 1;
        }
        return nextStudyGroupLocationId;
    }
 
    /**
     * This method is to get a sorted list of current study group locations.
     * @param iacucProtocol
     * @return
     */
    private List<IacucProtocolStudyGroupLocation> getSortedStudyGroupLocations(IacucProtocol iacucProtocol) {
        List<IacucProtocolStudyGroupLocation> protocolStudyGroupLocations = iacucProtocol.getIacucProtocolStudyGroupLocations();
        Collections.sort(protocolStudyGroupLocations, new Comparator<IacucProtocolStudyGroupLocation>() {
            public int compare(IacucProtocolStudyGroupLocation location1, IacucProtocolStudyGroupLocation location2) {
                return location1.getStudyGroupLocationId().compareTo(location2.getStudyGroupLocationId());
            }
        });
        return protocolStudyGroupLocations;
    }
    
    @Override
    public void addLocationResponsibleProcedures(IacucProtocol protocol) {
        for(IacucProtocolStudyGroupLocation iacucProtocolStudyGroupLocation : protocol.getIacucProtocolStudyGroupLocations()) {
            for(IacucProtocolSpeciesStudyGroup protocolSpeciesStudyGroup : iacucProtocolStudyGroupLocation.getProcedureDetails()) {
                for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : protocolSpeciesStudyGroup.getResponsibleProcedures()) {
                    if(iacucProtocolStudyGroupBean.isProcedureSelected()) {
                        addLocationResponsibleProcedures(iacucProtocolStudyGroupBean, iacucProtocolStudyGroupLocation, protocolSpeciesStudyGroup);
                    }else {
                        deleteLocationResponsibleProcedures(iacucProtocolStudyGroupBean, iacucProtocolStudyGroupLocation, protocolSpeciesStudyGroup);
                    }
                }
            }
        }
        populateSpeciesLocationProcedures(protocol);
    }

    /**
     * This method is to add person responsible locations to study groups
     * Add in invoked based on checked procedures
     * @param iacucProtocolStudyGroupBean
     * @param iacucProtocolStudyGroupLocation
     * @param protocolSpeciesStudyGroup
     */
    private void addLocationResponsibleProcedures(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean, IacucProtocolStudyGroupLocation iacucProtocolStudyGroupLocation, 
            IacucProtocolSpeciesStudyGroup protocolSpeciesStudyGroup) {
        if(iacucProtocolStudyGroupBean.isNewProcedure()) {
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : protocolSpeciesStudyGroup.getIacucProtocolStudyGroups()) {
                if(iacucProtocolStudyGroup.getIacucProtocolStudyGroupBean().getProcedureCode().equals(iacucProtocolStudyGroupBean.getProcedureCode())) {
                    IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation = (IacucProtocolStudyGroupLocation)deepCopy(iacucProtocolStudyGroupLocation);
                    newIacucProtocolStudyGroupLocation.resetPersistenceState();
                    iacucProtocolStudyGroup.getIacucProcedureLocationResponsibleList().add(newIacucProtocolStudyGroupLocation);
                }
            }
        }
    }
    
    /**
     * This method is to delete person responsible procedures from study groups
     * Delete is invoked based on unchecked procedures
     * @param iacucProtocolStudyGroupBean
     * @param iacucProtocolStudyGroupLocation
     * @param protocolSpeciesStudyGroup
     */
    private void deleteLocationResponsibleProcedures(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean, IacucProtocolStudyGroupLocation iacucProtocolStudyGroupLocation, 
            IacucProtocolSpeciesStudyGroup protocolSpeciesStudyGroup) {
        if(!iacucProtocolStudyGroupBean.isNewProcedure()) {
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : protocolSpeciesStudyGroup.getIacucProtocolStudyGroups()) {
                List<IacucProtocolStudyGroupLocation> deletedProcedureLocationResponsible = new ArrayList<IacucProtocolStudyGroupLocation>();
                if(iacucProtocolStudyGroup.getIacucProtocolStudyGroupBean().getProcedureCode().equals(iacucProtocolStudyGroupBean.getProcedureCode())) {
                    for(IacucProtocolStudyGroupLocation iacucProcedureLocationResponsible : iacucProtocolStudyGroup.getIacucProcedureLocationResponsibleList()) {
                        if(iacucProcedureLocationResponsible.getStudyGroupLocationId().equals(iacucProtocolStudyGroupLocation.getStudyGroupLocationId())) {
                            deletedProcedureLocationResponsible.add(iacucProcedureLocationResponsible);
                        }
                    }
                }
                iacucProtocolStudyGroup.getIacucProcedureLocationResponsibleList().removeAll(deletedProcedureLocationResponsible);
            }
        }
    }
    
    @Override
    public void addPersonResponsibleProcedures(IacucProtocol protocol) {
        for(ProtocolPersonBase protocolPerson : protocol.getProtocolPersons()) {
            IacucProtocolPerson iacucProtocolPerson = (IacucProtocolPerson)protocolPerson; 
            for(IacucProtocolSpeciesStudyGroup protocolSpeciesStudyGroup : iacucProtocolPerson.getProcedureDetails()) {
                for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : protocolSpeciesStudyGroup.getResponsibleProcedures()) {
                    if(iacucProtocolStudyGroupBean.isProcedureSelected()) {
                        addPersonResponsibleProcedures(iacucProtocolStudyGroupBean, iacucProtocolPerson, protocolSpeciesStudyGroup);
                    }else {
                        deletePersonResponsibleProcedures(iacucProtocolStudyGroupBean, iacucProtocolPerson, protocolSpeciesStudyGroup);
                    }
                }
            }
        }
        populateIacucSpeciesPersonProcedures(protocol);
    }
    
    /**
     * This method is to add person responsible procedures to study groups
     * Add in invoked based on checked procedures
     * @param iacucProtocolStudyGroupBean
     * @param protocolPerson
     * @param protocolSpeciesStudyGroup
     */
    private void addPersonResponsibleProcedures(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean, IacucProtocolPerson protocolPerson, 
            IacucProtocolSpeciesStudyGroup protocolSpeciesStudyGroup) {
        if(iacucProtocolStudyGroupBean.isNewProcedure()) {
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : protocolSpeciesStudyGroup.getIacucProtocolStudyGroups()) {
                if(iacucProtocolStudyGroup.getIacucProtocolStudyGroupBean().getProcedureCode().equals(iacucProtocolStudyGroupBean.getProcedureCode())) {
                    IacucProcedurePersonResponsible newIacucProcedurePersonResponsible = getNewPersonResponsibleProcedure(protocolPerson, iacucProtocolStudyGroup);
                    iacucProtocolStudyGroup.getIacucProcedurePersonResponsibleList().add(newIacucProcedurePersonResponsible);
                }
            }
        }
    }
    
    /**
     * This method is to delete person responsible procedures from study groups
     * Delete is invoked based on unchecked procedures
     * @param iacucProtocolStudyGroupBean
     * @param protocolPerson
     * @param protocolSpeciesStudyGroup
     */
    private void deletePersonResponsibleProcedures(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean, IacucProtocolPerson protocolPerson, 
            IacucProtocolSpeciesStudyGroup protocolSpeciesStudyGroup) {
        if(!iacucProtocolStudyGroupBean.isNewProcedure()) {
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : protocolSpeciesStudyGroup.getIacucProtocolStudyGroups()) {
                List<IacucProcedurePersonResponsible> deletedProcedurePersonResponsible = new ArrayList<IacucProcedurePersonResponsible>();
                if(iacucProtocolStudyGroup.getIacucProtocolStudyGroupBean().getProcedureCode().equals(iacucProtocolStudyGroupBean.getProcedureCode())) {
                    for(IacucProcedurePersonResponsible iacucProcedurePersonResponsible : iacucProtocolStudyGroup.getIacucProcedurePersonResponsibleList()) {
                        if(iacucProcedurePersonResponsible.getProtocolPersonId().equals(protocolPerson.getProtocolPersonId())) {
                            deletedProcedurePersonResponsible.add(iacucProcedurePersonResponsible);
                        }
                    }
                }
                iacucProtocolStudyGroup.getIacucProcedurePersonResponsibleList().removeAll(deletedProcedurePersonResponsible);
            }
        }
    }
    
    /**
     * This method is to map iacuc protocol persons based on person id
     * @param iacucProtocol
     * @return
     */
    private HashMap<String, IacucProtocolPerson> getProtocolPersons(IacucProtocol iacucProtocol) {
        HashMap<String, IacucProtocolPerson> protocolPersons = new HashMap<String, IacucProtocolPerson>();
        for(ProtocolPersonBase protocolPersonBase : iacucProtocol.getProtocolPersons()) {
            IacucProtocolPerson iacucProtocolPerson = (IacucProtocolPerson)protocolPersonBase;
            protocolPersons.put(iacucProtocolPerson.getPersonId(), iacucProtocolPerson);
        }
        return protocolPersons;
    }
    
    /**
     * This method is to get a new person responsible procedure information
     * Person responsibility for a procedure is recorded based on species
     * @param protocolPerson
     * @param iacucProtocolStudyGroup
     * @return
     */
    private IacucProcedurePersonResponsible getNewPersonResponsibleProcedure(IacucProtocolPerson protocolPerson, IacucProtocolStudyGroup iacucProtocolStudyGroup) {
        IacucProcedurePersonResponsible resposibleProcedure = new IacucProcedurePersonResponsible();
        setAttributesForPersonResponsibleProcedure(resposibleProcedure, protocolPerson, iacucProtocolStudyGroup);
        return resposibleProcedure;
    }
    
    /**
     * This method is to set protocol and person attributes for a new person responsible procedure
     * @param resposibleProcedure
     * @param protocolPerson
     * @param iacucProtocolStudyGroup
     */
    private void setAttributesForPersonResponsibleProcedure(IacucProcedurePersonResponsible resposibleProcedure, IacucProtocolPerson protocolPerson, 
            IacucProtocolStudyGroup iacucProtocolStudyGroup) {
        resposibleProcedure.setProtocolPersonId(protocolPerson.getProtocolPersonId());
        resposibleProcedure.setProtocolPerson(protocolPerson);
        resposibleProcedure.setIacucProtocolStudyGroupId(iacucProtocolStudyGroup.getIacucProtocolStudyGroupId());
    }
    
    @Override
    public void setProcedureSummaryGroupedBySpecies(IacucProtocol protocol) {
        List<IacucProtocolStudyGroup> iacucProtocolStudyGroups = getAllProcedureStudyGroups(protocol);
        List<IacucProtocolSpeciesStudyGroup> iacucProtocolStudyGroupSpeciesList = getListOfProcedureStudyBySpecies(iacucProtocolStudyGroups);
        updateSpeciesCount(iacucProtocolStudyGroupSpeciesList, protocol);
        protocol.setIacucProtocolStudyGroupSpeciesList(iacucProtocolStudyGroupSpeciesList);
        addStudyGroupProceduresForSpecies(protocol);
        addStudyGroupProcedureDetailsForSpecies(protocol);
    }

    private void updateSpeciesCount(List<IacucProtocolSpeciesStudyGroup> iacucProtocolSpeciesStudyGroups, IacucProtocol protocol) {
        for(IacucProtocolSpeciesStudyGroup iacucProtocolSpeciesStudyGroup : iacucProtocolSpeciesStudyGroups) {
            for(IacucProtocolSpecies iacucProtocolSpecies : protocol.getIacucProtocolSpeciesList()) {
                if(iacucProtocolSpecies.getSpeciesCode().equals(iacucProtocolSpeciesStudyGroup.getSpeciesCode())) {
                    iacucProtocolSpeciesStudyGroup.addSpeciesCount(iacucProtocolSpecies.getSpeciesCount());
                }
            }
        }
    }
    
    @Override
    public void setProcedureSummaryBySpeciesGroup(IacucProtocol protocol) {
        List<IacucProtocolStudyGroup> iacucProtocolStudyGroups = getAllProcedureStudyGroups(protocol);
        List<IacucProtocolSpeciesStudyGroup> iacucProtocolStudyGroupSpeciesList = getListOfProcedureStudyBySpeciesGroup(iacucProtocolStudyGroups);
        protocol.setIacucProtocolStudyGroupSpeciesList(iacucProtocolStudyGroupSpeciesList);
        addProceduresForSpeciesGroups(protocol);
        addProcedureDetailsForSpeciesGroups(protocol);
    }
    
    /**
     * This method is to identify study group details for species group used in the study
     * This grouping is used for summary display (studies grouped by species group)
     * @param protocol
     */
    private void addProceduresForSpeciesGroups(IacucProtocol protocol) {
        for(IacucProtocolSpeciesStudyGroup protocolStudyGroupSpecies : protocol.getIacucProtocolStudyGroupSpeciesList()) {
            protocolStudyGroupSpecies.setResponsibleProcedures(new ArrayList<IacucProtocolStudyGroupBean>());
            protocolStudyGroupSpecies.getResponsibleProcedures().addAll(getStudyGroupProceduresForSpeciesGroup(protocol, protocolStudyGroupSpecies.getIacucProtocolSpecies()));
        }
    }
    
    /**
     * This method is to get all study procedures based on species group
     * @param protocol
     * @param iacucProtocolSpecies
     * @return
     */
    private List<IacucProtocolStudyGroupBean> getStudyGroupProceduresForSpeciesGroup(IacucProtocol protocol, IacucProtocolSpecies iacucProtocolSpecies) {
        List<IacucProtocolStudyGroupBean> protocolStudyGroups = new ArrayList<IacucProtocolStudyGroupBean>();
        for(IacucProtocolStudyGroupBean protocolStudyGroupBean : protocol.getIacucProtocolStudyGroups()) {
            List<IacucProtocolStudyGroup> iacucProtocolStudyGroups = new ArrayList<IacucProtocolStudyGroup>();
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : protocolStudyGroupBean.getIacucProtocolStudyGroups()) {
                if(iacucProtocolStudyGroup.getIacucProtocolSpeciesId().equals(iacucProtocolSpecies.getIacucProtocolSpeciesId())) {
                    iacucProtocolStudyGroups.add(iacucProtocolStudyGroup);
                }
            }
            addProceduresDetails(iacucProtocolStudyGroups, protocolStudyGroups, protocolStudyGroupBean);
        }
        return protocolStudyGroups;
    }

    /**
     * This method is to set procedure related studies
     * @param iacucProtocolStudyGroups
     * @param protocolStudyGroups
     * @param protocolStudyGroupBean
     */
    private void addProceduresDetails(List<IacucProtocolStudyGroup> iacucProtocolStudyGroups, List<IacucProtocolStudyGroupBean> protocolStudyGroups, 
            IacucProtocolStudyGroupBean protocolStudyGroupBean) {
        if(!iacucProtocolStudyGroups.isEmpty()) {
            IacucProtocolStudyGroupBean newProtocolStudyGroupBean = getNewProtocolStudyGroupBean(protocolStudyGroupBean);
            newProtocolStudyGroupBean.getIacucProtocolStudyGroups().addAll(iacucProtocolStudyGroups);
            protocolStudyGroups.add(newProtocolStudyGroupBean);
        }
    }
    
    /**
     * This method is to identify study procedures for species used in the study
     * This grouping is used for summary display
     * @param protocol
     */
    private void addStudyGroupProceduresForSpecies(IacucProtocol protocol) {
        for(IacucProtocolSpeciesStudyGroup protocolStudyGroupSpecies : protocol.getIacucProtocolStudyGroupSpeciesList()) {
            protocolStudyGroupSpecies.setResponsibleProcedures(new ArrayList<IacucProtocolStudyGroupBean>());
            Integer speciesCode = protocolStudyGroupSpecies.getSpeciesCode();
            protocolStudyGroupSpecies.getResponsibleProcedures().addAll(getStudyGroupProceduresForSpecies(protocol, speciesCode));
        }
    }
    
    /**
     * This method is to collect study details based on species code
     * @param protocol
     * @param speciesCode
     * @return
     */
    private List<IacucProtocolStudyGroupBean> getStudyGroupProceduresForSpecies(IacucProtocol protocol, Integer speciesCode) {
        List<IacucProtocolStudyGroupBean> protocolStudyGroups = new ArrayList<IacucProtocolStudyGroupBean>();
        for(IacucProtocolStudyGroupBean protocolStudyGroupBean : protocol.getIacucProtocolStudyGroups()) {
            List<IacucProtocolStudyGroup> iacucProtocolStudyGroups = new ArrayList<IacucProtocolStudyGroup>();
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : protocolStudyGroupBean.getIacucProtocolStudyGroups()) {
                if(iacucProtocolStudyGroup.getIacucProtocolSpecies().getSpeciesCode().equals(speciesCode)) {
                    iacucProtocolStudyGroups.add(iacucProtocolStudyGroup);
                }
            }
            addProceduresDetails(iacucProtocolStudyGroups, protocolStudyGroups, protocolStudyGroupBean);
        }
        return protocolStudyGroups;
    }
    
    /**
     * This method is to get a new copy of an existing study group header bean
     * @param protocolStudyGroupBean
     * @return
     */
    private IacucProtocolStudyGroupBean getNewProtocolStudyGroupBean(IacucProtocolStudyGroupBean protocolStudyGroupBean) {
        IacucProtocolStudyGroupBean newProtocolStudyGroupBean = new IacucProtocolStudyGroupBean();
        newProtocolStudyGroupBean.setIacucProtocolStudyGroupHeaderId(protocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId());
        newProtocolStudyGroupBean.setIacucProcedureCategory(protocolStudyGroupBean.getIacucProcedureCategory());
        newProtocolStudyGroupBean.setIacucProcedure(protocolStudyGroupBean.getIacucProcedure());
        return newProtocolStudyGroupBean;
    }
    
    /**
     * This method is to add related procedure details for each species
     * This includes person responsibilities, location and custom data.
     * This grouping is used for summary display
     * @param protocol
     */
    private void addStudyGroupProcedureDetailsForSpecies(IacucProtocol protocol) {
        for(IacucProtocolSpeciesStudyGroup protocolStudyGroupSpecies : protocol.getIacucProtocolStudyGroupSpeciesList()) {
            for(IacucProtocolStudyGroupBean protocolStudyGroupBean : protocolStudyGroupSpecies.getResponsibleProcedures()) {
                setAllProcedureDetailsForSpecies(protocolStudyGroupSpecies, protocolStudyGroupBean);
                Integer totalProcSpeciesCount = 0;
                for(IacucProtocolStudyGroup studyGroup : protocolStudyGroupBean.getIacucProtocolStudyGroups()) {
                    if(protocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId().equals(studyGroup.getIacucProtocolStudyGroupHeaderId()) &&
                            studyGroup.getIacucProtocolSpecies().getSpeciesCode().equals(protocolStudyGroupSpecies.getSpeciesCode())) {
                        totalProcSpeciesCount = totalProcSpeciesCount + studyGroup.getCount();
                    }
                }
                protocolStudyGroupBean.setSpeciesCount(totalProcSpeciesCount);
            }
        }
    }
    
    /**
     * This method is to set all related collections for a procedure
     * say Person responsible, Location and Custom data list
     * @param protocolStudyGroupSpecies
     * @param protocolStudyGroupBean
     */
    private void setAllProcedureDetails(IacucProtocolSpeciesStudyGroup protocolStudyGroupSpecies, IacucProtocolStudyGroupBean protocolStudyGroupBean) {
        List<IacucProtocolStudyGroupLocation> allIacucProtocolStudyGroupLocations = new ArrayList<IacucProtocolStudyGroupLocation>();
        List<IacucProcedurePersonResponsible> allIacucProtocolStudyGroupPersons = new ArrayList<IacucProcedurePersonResponsible>();
        List<IacucProtocolStudyCustomData> allIacucProtocolStudyCustomDataList = new ArrayList<IacucProtocolStudyCustomData>();
        for(IacucProtocolStudyGroup iacucProtocolStudyGroup : protocolStudyGroupSpecies.getIacucProtocolStudyGroups()) {
            if(iacucProtocolStudyGroup.getIacucProtocolStudyGroupHeaderId().equals(protocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId())) {
                allIacucProtocolStudyGroupLocations.addAll(iacucProtocolStudyGroup.getIacucProcedureLocationResponsibleList());
                allIacucProtocolStudyGroupPersons.addAll(iacucProtocolStudyGroup.getIacucProcedurePersonResponsibleList());
                allIacucProtocolStudyCustomDataList.addAll(iacucProtocolStudyGroup.getIacucProtocolStudyCustomDataList());
            }
        }
        protocolStudyGroupBean.setIacucProtocolStudyGroupLocations(allIacucProtocolStudyGroupLocations);
        protocolStudyGroupBean.setIacucProtocolStudyGroupPersons(allIacucProtocolStudyGroupPersons);
        protocolStudyGroupBean.setIacucProtocolStudyCustomDataList(allIacucProtocolStudyCustomDataList);
    }
    
    /**
     * This method is to set all related collections for a procedure
     * say Person responsible, Location and Custom data list
     * We need to consider the distinct case where procedures are grouped by species
     * @param protocolStudyGroupSpecies
     * @param protocolStudyGroupBean
     */
    private void setAllProcedureDetailsForSpecies(IacucProtocolSpeciesStudyGroup protocolStudyGroupSpecies, IacucProtocolStudyGroupBean protocolStudyGroupBean) {
        List<IacucProtocolStudyGroupLocation> allIacucProtocolStudyGroupLocations = new ArrayList<IacucProtocolStudyGroupLocation>();
        List<IacucProcedurePersonResponsible> allIacucProtocolStudyGroupPersons = new ArrayList<IacucProcedurePersonResponsible>();
        List<IacucProtocolStudyCustomData> allIacucProtocolStudyCustomDataList = new ArrayList<IacucProtocolStudyCustomData>();
        Map<IacucSpecies,IacucProtocolStudyGroup> protocolSpeciesStudyGroups = new HashMap<IacucSpecies,IacucProtocolStudyGroup>();
        for(IacucProtocolStudyGroup iacucProtocolStudyGroup : protocolStudyGroupSpecies.getIacucProtocolStudyGroups()) {
            if(iacucProtocolStudyGroup.getIacucProtocolStudyGroupHeaderId().equals(protocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId())) {
                IacucSpecies iacucSpecies = iacucProtocolStudyGroup.getIacucProtocolSpecies().getIacucSpecies();
                IacucProtocolStudyGroup groupedProtocolStudyGroup = protocolSpeciesStudyGroups.get(iacucSpecies);
                if(ObjectUtils.isNull(groupedProtocolStudyGroup)) {
                    allIacucProtocolStudyGroupLocations.addAll(iacucProtocolStudyGroup.getIacucProcedureLocationResponsibleList());
                    allIacucProtocolStudyGroupPersons.addAll(iacucProtocolStudyGroup.getIacucProcedurePersonResponsibleList());
                    allIacucProtocolStudyCustomDataList.addAll(iacucProtocolStudyGroup.getIacucProtocolStudyCustomDataList());
                    protocolSpeciesStudyGroups.put(iacucSpecies, iacucProtocolStudyGroup);
                }
            }
        }
        protocolStudyGroupBean.setIacucProtocolStudyGroupLocations(allIacucProtocolStudyGroupLocations);
        protocolStudyGroupBean.setIacucProtocolStudyGroupPersons(allIacucProtocolStudyGroupPersons);
        protocolStudyGroupBean.setIacucProtocolStudyCustomDataList(allIacucProtocolStudyCustomDataList);
    }

    /**
     * This method is to add related procedure details for each species group
     * This includes person responsibilities, location and custom data.
     * This grouping is used for summary display
     * @param protocol
     */
    private void addProcedureDetailsForSpeciesGroups(IacucProtocol protocol) {
        for(IacucProtocolSpeciesStudyGroup protocolStudyGroupSpecies : protocol.getIacucProtocolStudyGroupSpeciesList()) {
            for(IacucProtocolStudyGroupBean protocolStudyGroupBean : protocolStudyGroupSpecies.getResponsibleProcedures()) {
                setAllProcedureDetails(protocolStudyGroupSpecies, protocolStudyGroupBean);
                Integer totalProcSpeciesCount = 0;
                IacucProtocolSpecies iacucProtocolSpecies = protocolStudyGroupSpecies.getIacucProtocolSpecies();
                for(IacucProtocolStudyGroup studyGroup : protocolStudyGroupBean.getIacucProtocolStudyGroups()) {
                    if(protocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId().equals(studyGroup.getIacucProtocolStudyGroupHeaderId()) &&
                            studyGroup.getIacucProtocolSpeciesId().equals(iacucProtocolSpecies.getIacucProtocolSpeciesId())) {
                        totalProcSpeciesCount = totalProcSpeciesCount + studyGroup.getCount();
                    }
                }
                protocolStudyGroupBean.setSpeciesCount(totalProcSpeciesCount);
            }
        }
    }
    
    @Override
    public void createNewProtocolStudyProcedures(IacucProtocol sourceProtocol, IacucProtocol destProtocol) {
        createNewStudyProcedures(sourceProtocol, destProtocol);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void mergeProtocolSpecies(IacucProtocol sourceProtocol, IacucProtocol destProtocol) {
        destProtocol.setIacucProtocolSpeciesList((List<IacucProtocolSpecies>) deepCopy(sourceProtocol.getIacucProtocolSpeciesList()));
        setAttributesForIacucProtocolSpecies(destProtocol);
        synchronizeProcedureSpecies(destProtocol);
    }
    
    /**
     * This method is to update reference - procedure details where species in use
     * @param destProtocol
     */
    private void synchronizeProcedureSpecies(IacucProtocol destProtocol) {
        HashMap<String, IacucProtocolSpecies> newIacucProtocolSpeciesMapping = getIacucProtocolSpeciesMapping(destProtocol.getIacucProtocolSpeciesList());
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : destProtocol.getIacucProtocolStudyGroups()) {
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroupBean.getIacucProtocolStudyGroups()) {
                IacucProtocolSpecies destIacucProtocolSpecies = newIacucProtocolSpeciesMapping.get(iacucProtocolStudyGroup.getIacucProtocolSpecies().getGroupAndSpecies());
                iacucProtocolStudyGroup.setIacucProtocolSpeciesId(destIacucProtocolSpecies.getIacucProtocolSpeciesId());
                iacucProtocolStudyGroup.setIacucProtocolSpecies(destIacucProtocolSpecies);
            }
        }
    }
    
    /**
     * This method is to reset persistence and set new attributes for iacuc protocol species.
     * Purpose is to link protocol species for procedures
     * @param destProtocol
     */
    private void setAttributesForIacucProtocolSpecies(IacucProtocol destProtocol) {
        for(IacucProtocolSpecies iacucProtocolSpecies : destProtocol.getIacucProtocolSpeciesList()) {
            iacucProtocolSpecies.resetPersistenceState();
            getIacucProtocolSpeciesService().getNewProtocolSpecies(destProtocol, iacucProtocolSpecies);
        }
    }
    
    @Override
    public void mergeProtocolProcedures(IacucProtocol sourceProtocol, IacucProtocol destProtocol) {
        createNewStudyProcedures(sourceProtocol, destProtocol);
        synchronizeProcedurePersonnel(sourceProtocol, destProtocol);
    }
    
    /**
     * This method is to update personnel procedure references
     * changes made during amendment/renewal
     * @param destProtocol
     */
    private void synchronizeProcedurePersonnel(IacucProtocol sourceProtocol, IacucProtocol destProtocol) {
        HashMap<String, IacucProtocolPerson> newProtocolPersons = getProtocolPersons(sourceProtocol);       
        for(ProtocolPersonBase protocolPersonBase : destProtocol.getProtocolPersons()) {
            IacucProtocolPerson destIacucProtocolPerson = (IacucProtocolPerson)protocolPersonBase;
            IacucProtocolPerson sourceIacucProtocolPerson = newProtocolPersons.get(destIacucProtocolPerson.getPersonId());
            destIacucProtocolPerson.setProcedureQualificationDescription(sourceIacucProtocolPerson.getProcedureQualificationDescription());
        }
    }
    
    @Override
    public void mergeProtocolProcedurePersonnel(IacucProtocol destProtocol) {
        HashMap<String, IacucProtocolPerson> newProtocolPersons = getProtocolPersons(destProtocol);       
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : destProtocol.getIacucProtocolStudyGroups()) {
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroupBean.getIacucProtocolStudyGroups()) {
                for(IacucProcedurePersonResponsible iacucProcedurePersonResponsible : iacucProtocolStudyGroup.getIacucProcedurePersonResponsibleList()) {
                    IacucProtocolPerson newIacucProtocolPerson = newProtocolPersons.get(iacucProcedurePersonResponsible.getPersonId());
                    iacucProcedurePersonResponsible.setProtocolPersonId(newIacucProtocolPerson.getProtocolPersonId());
                    iacucProcedurePersonResponsible.setProtocolPerson(newIacucProtocolPerson);
                }
            }
        }
    }
    
    @Override
    public void resetAllProtocolStudyProcedures(IacucProtocol iacucProtocol) {
        setAttributesForIacucProtocolSpecies(iacucProtocol);
        HashMap<String, IacucProtocolSpecies> newIacucProtocolSpeciesMapping = getIacucProtocolSpeciesMapping(iacucProtocol.getIacucProtocolSpeciesList());
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : iacucProtocol.getIacucProtocolStudyGroups()) {
            setAttributesForStudyProcedures(iacucProtocolStudyGroupBean, iacucProtocol, newIacucProtocolSpeciesMapping);
        }
    }

    /**
     * This method is to create a new set of study details in procedures tab.
     * invoked during copy protocol
     * @param sourceProtocol
     * @param destProtocol
     */
    private void createNewStudyProcedures(IacucProtocol sourceProtocol, IacucProtocol destProtocol) {
        destProtocol.setIacucProtocolStudyGroups(new ArrayList<IacucProtocolStudyGroupBean>());
        HashMap<String, IacucProtocolSpecies> newIacucProtocolSpeciesMapping = getIacucProtocolSpeciesMapping(destProtocol.getIacucProtocolSpeciesList());
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : sourceProtocol.getIacucProtocolStudyGroups()) {
            IacucProtocolStudyGroupBean newIacucProtocolStudyGroupBean = (IacucProtocolStudyGroupBean)deepCopy(iacucProtocolStudyGroupBean);
            setAttributesForStudyProcedures(newIacucProtocolStudyGroupBean, destProtocol, newIacucProtocolSpeciesMapping);
            destProtocol.getIacucProtocolStudyGroups().add(newIacucProtocolStudyGroupBean);
        }
    }
    
    /**
     * This method is to set attributes for new study groups
     * @param newIacucProtocolStudyGroupBean
     * @param destProtocol
     * @param newIacucProtocolSpeciesMapping
     */
    private void setAttributesForStudyProcedures(IacucProtocolStudyGroupBean newIacucProtocolStudyGroupBean, IacucProtocol destProtocol, 
            HashMap<String, IacucProtocolSpecies> newIacucProtocolSpeciesMapping) {
        newIacucProtocolStudyGroupBean.resetPersistenceState();
        setAttributesForNewStudyGroupBean(newIacucProtocolStudyGroupBean, destProtocol);
        updateNewStudyProcedures(newIacucProtocolStudyGroupBean, destProtocol, newIacucProtocolSpeciesMapping);
    }
    
    /**
     * This method to update new study group procedure collection
     * reset state for custom data, person and location
     * @param newIacucProtocolStudyGroupBean
     * @param destProtocol
     * @param newIacucProtocolSpeciesMapping
     */
    private void updateNewStudyProcedures(IacucProtocolStudyGroupBean newIacucProtocolStudyGroupBean, IacucProtocol destProtocol, 
            HashMap<String, IacucProtocolSpecies> newIacucProtocolSpeciesMapping) {
        HashMap<String, IacucProtocolPerson> newProtocolPersons = getProtocolPersons(destProtocol);       
        for(IacucProtocolStudyGroup newIacucProtocolStudyGroup : newIacucProtocolStudyGroupBean.getIacucProtocolStudyGroups()) {
            newIacucProtocolStudyGroup.resetPersistenceState();
            newIacucProtocolStudyGroup.setIacucProtocolStudyGroupHeaderId(newIacucProtocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId());
            newIacucProtocolStudyGroup.setIacucProtocolStudyGroupBean(newIacucProtocolStudyGroupBean);
            IacucProtocolSpecies destIacucProtocolSpecies = newIacucProtocolSpeciesMapping.get(newIacucProtocolStudyGroup.getIacucProtocolSpecies().getGroupAndSpecies());
            newIacucProtocolStudyGroup.setIacucProtocolSpeciesId(destIacucProtocolSpecies.getIacucProtocolSpeciesId());
            newIacucProtocolStudyGroup.setIacucProtocolSpecies(destIacucProtocolSpecies);
            for(IacucProtocolStudyCustomData newIacucProtocolStudyCustomData : newIacucProtocolStudyGroup.getIacucProtocolStudyCustomDataList()) {
                newIacucProtocolStudyCustomData.resetPersistenceState();
                newIacucProtocolStudyCustomData.setIacucProtocolStudyGroupId(newIacucProtocolStudyGroup.getIacucProtocolStudyGroupId());
            }
            for(IacucProcedurePersonResponsible newIacucProcedurePersonResponsible : newIacucProtocolStudyGroup.getIacucProcedurePersonResponsibleList()) {
                newIacucProcedurePersonResponsible.resetPersistenceState();
                IacucProtocolPerson newIacucProtocolPerson = newProtocolPersons.get(newIacucProcedurePersonResponsible.getPersonId());
                newIacucProcedurePersonResponsible.setProtocolPersonId(newIacucProtocolPerson.getProtocolPersonId());
                newIacucProcedurePersonResponsible.setProtocolPerson(newIacucProtocolPerson);
                newIacucProcedurePersonResponsible.setIacucProtocolStudyGroupId(newIacucProtocolStudyGroup.getIacucProtocolStudyGroupId());
            }
            for(IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation : newIacucProtocolStudyGroup.getIacucProcedureLocationResponsibleList()) {
                newIacucProtocolStudyGroupLocation.resetPersistenceState();
                newIacucProtocolStudyGroupLocation.setIacucProtocolStudyGroupId(newIacucProtocolStudyGroup.getIacucProtocolStudyGroupId());
            }
        }
    }
    
    protected Object deepCopy(Object obj) {
        if (obj instanceof Serializable) {
            return ObjectUtils.deepCopy((Serializable) obj);
        }
        return obj;
    }

    
    /**
     * This method is to get a map of list of protocol species
     * Map protocol species to get the right protocol species for procedures during copy
     * @param iacucProtocolSpeciesList
     * @return
     */
    private HashMap<String, IacucProtocolSpecies> getIacucProtocolSpeciesMapping(List<IacucProtocolSpecies> iacucProtocolSpeciesList) {
        HashMap<String, IacucProtocolSpecies> protocolSpeciesList = new HashMap<String, IacucProtocolSpecies>();
        for(IacucProtocolSpecies iacucProtocolSpecies : iacucProtocolSpeciesList) {
            protocolSpeciesList.put(iacucProtocolSpecies.getGroupAndSpecies(), iacucProtocolSpecies);
        }
        return protocolSpeciesList;
    }

    public boolean isProcedureViewedBySpecies() {
        String procedureViewModeParam = getProcedureViewModeParameter();
        if(ObjectUtils.isNull(procedureViewModeParam)) {
            procedureViewModeParam = PROCEDURE_VIEW_MODE_SPECIES;
        }
        return procedureViewModeParam.equals(PROCEDURE_VIEW_MODE_SPECIES);
    }
    protected String getProcedureViewModeParameter() {
        return getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_IACUC, ParameterConstants.DOCUMENT_COMPONENT, PROCEDURE_VIEW_MODE);
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
}

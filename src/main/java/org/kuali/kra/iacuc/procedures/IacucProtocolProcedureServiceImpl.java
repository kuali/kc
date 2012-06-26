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
package org.kuali.kra.iacuc.procedures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.kuali.kra.iacuc.IacucPainCategory;
import org.kuali.kra.iacuc.IacucPersonTraining;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.IacucSpecies;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonTrainingService;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * This class...
 */
/**
 * This class...
 */
/**
 * This class...
 */
public class IacucProtocolProcedureServiceImpl implements IacucProtocolProcedureService {

    private BusinessObjectService businessObjectService;
    private IacucProtocolPersonTrainingService iacucProtocolPersonTrainingService;
    private static final String PROTOCOL_STUDY_GROUP_SEQUENCE_ID = "SEQ_IACUC_PROTO_STUDY_GROUP_ID";
    private static final String PROTOCOL_PERSON_RESPONSIBLE_SEQUENCE_ID = "SEQ_IACUC_PROC_PERS_RESP_ID";
    private static final String PROTOCOL_STUDY_GROUP_LOCATION_SEQUENCE_ID = "SEQ_IACUC_PROT_STUD_GRP_LOC_ID";
    
    
    private SequenceAccessorService sequenceAccessorService;

    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#getAllProcedures()
     */
    public List<IacucProcedure> getAllProcedures() {
        return (List<IacucProcedure>)getBusinessObjectService().findAllOrderBy(IacucProcedure.class, "procedureCategoryCode", true);
    }

    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#getAllProcedureCategories()
     */
    public List<IacucProcedureCategory> getAllProcedureCategories() {
        return (List<IacucProcedureCategory>)getBusinessObjectService().findAllOrderBy(IacucProcedureCategory.class, "procedureCategoryCode", true);
    }
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#getProtocolSpecies()
     */
    public List<IacucProtocolSpecies> getProtocolSpecies() {
        Long protocolId = ((IacucProtocolForm) KNSGlobalVariables.getKualiForm()).getIacucProtocolDocument().getProtocol().getProtocolId();
        Map<String, Object> keyMap = new HashMap<String, Object> ();
        keyMap.put("protocolId", protocolId);
        return (List<IacucProtocolSpecies>) getBusinessObjectService().findMatching(IacucProtocolSpecies.class, keyMap);
    }

    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#addProtocolStudyGroup(org.kuali.kra.iacuc.IacucProtocolForm)
     */
    public void addProtocolStudyGroup(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, IacucProtocol iacucProtocol) {
        List<String> protocolSpeciesAndGroups =  selectedProtocolStudyGroupBean.getProtocolSpeciesAndGroups(); 
        List<String> protocolPersonsResponsible =  selectedProtocolStudyGroupBean.getProtocolPersonsResponsible(); 
        //List<IacucProtocolStudyGroup> protocolStudyGroups = addStudyGroupBeans(protocolSpeciesAndGroups, selectedProtocolStudyGroupBean); 
        List<IacucProtocolStudyGroupDetailBean> updatedDetailBeans = addStudyGroupBeans(protocolSpeciesAndGroups, selectedProtocolStudyGroupBean);
        List<IacucProcedurePersonResponsible> procedurePersonsResponsible = addPersonResponsible(protocolPersonsResponsible, null);
        updateStudyGroupDetailBean(updatedDetailBeans, procedurePersonsResponsible,selectedProtocolStudyGroupBean, iacucProtocol);
        addIacucProtocolStudyGroup(selectedProtocolStudyGroupBean, iacucProtocol);
    }

    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#deleteProtocolStudyGroup(org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupBean, org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupDetailBean)
     */
    public void deleteProtocolStudyGroup(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, 
            IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean, IacucProtocol iacucProtocol) {
        List<IacucProtocolStudyGroup> protocolStudyGroupsMarkedToDelete = selectedProcedureDetailBean.getIacucProtocolStudyGroups();
        iacucProtocol.getIacucProtocolStudyGroups().removeAll(protocolStudyGroupsMarkedToDelete);
        selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans().remove(selectedProcedureDetailBean);
    }
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#deleteProcedurePersonResponsible(org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupDetailBean, org.kuali.kra.iacuc.procedures.IacucProcedurePersonResponsible, org.kuali.kra.iacuc.IacucProtocol)
     */
    public void deleteProcedurePersonResponsible(IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean, 
            IacucProcedurePersonResponsible selectedPersonResponsible, IacucProtocol iacucProtocol) {
        for(IacucProtocolStudyGroup beanProtocolStudyGroup : selectedProcedureDetailBean.getIacucProtocolStudyGroups()) {
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocol.getIacucProtocolStudyGroups()) {
                if(iacucProtocolStudyGroup.equals(beanProtocolStudyGroup)) {
                    IacucProcedurePersonResponsible tempPersonResponsible = getNewCopyOfPersonResponsible(selectedPersonResponsible);
                    tempPersonResponsible.setIacucProtocolStudyGroupId(beanProtocolStudyGroup.getIacucProtocolStudyGroupId());
                    iacucProtocolStudyGroup.getIacucProcedurePersonsResponsible().remove(tempPersonResponsible);
                }
            }
            beanProtocolStudyGroup.getIacucProcedurePersonsResponsible().remove(selectedPersonResponsible);
        }
        selectedProcedureDetailBean.getIacucProcedurePersonsResponsible().remove(selectedPersonResponsible);
    }
    
       
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#deleteStudyGroupLocation(org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupDetailBean, org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupLocation, org.kuali.kra.iacuc.IacucProtocol)
     */
    public void deleteStudyGroupLocation(IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean, 
            IacucProtocolStudyGroupLocation selectedStudyGroupLocation, IacucProtocol iacucProtocol) {
        for(IacucProtocolStudyGroup beanProtocolStudyGroup : selectedProcedureDetailBean.getIacucProtocolStudyGroups()) {
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocol.getIacucProtocolStudyGroups()) {
                if(iacucProtocolStudyGroup.equals(beanProtocolStudyGroup)) {
                    IacucProtocolStudyGroupLocation tempStudyGroupLocation = getNewCopyOfStudyGroupLocation(selectedStudyGroupLocation);
                    tempStudyGroupLocation.setIacucProtocolStudyGroupId(beanProtocolStudyGroup.getIacucProtocolStudyGroupId());
                    iacucProtocolStudyGroup.getIacucProtocolStudyGroupLocations().remove(tempStudyGroupLocation);
                    //break;
                }
            }
            beanProtocolStudyGroup.getIacucProtocolStudyGroupLocations().remove(selectedStudyGroupLocation);
        }
        selectedProcedureDetailBean.getIacucProtocolStudyGroupLocations().remove(selectedStudyGroupLocation);
    }
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#addProcedurePersonResponsible(org.kuali.kra.iacuc.procedures.IacucProcedurePersonResponsible, org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupDetailBean)
     */
    public void addProcedurePersonResponsible(IacucProcedurePersonResponsible newIacucProcedurePersonResponsible, IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean) {
        List<String> protocolPersonsResponsible =  newIacucProcedurePersonResponsible.getProtocolPersonsResponsible(); 
        List<IacucProcedurePersonResponsible> procedurePersonsResponsible = addPersonResponsible(protocolPersonsResponsible, newIacucProcedurePersonResponsible);
        addPersonToStudyGroupAndDetailBean(selectedProcedureDetailBean, procedurePersonsResponsible);
    }
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#addProcedureLocation(org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupLocation, org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupDetailBean)
     */
    public void addProcedureLocation(IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation, IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean, 
            IacucProtocol protocol) {
        updateAttributesForNewProcedureLocation(newIacucProtocolStudyGroupLocation, protocol);
        int locationGroupIndex = selectedProcedureDetailBean.getIacucProtocolStudyGroupLocations().size()+1;
        newIacucProtocolStudyGroupLocation.setLocationGroupIndex(locationGroupIndex);
        selectedProcedureDetailBean.getIacucProtocolStudyGroupLocations().add(newIacucProtocolStudyGroupLocation);
        for(IacucProtocolStudyGroup protocolStudyGroup : selectedProcedureDetailBean.getIacucProtocolStudyGroups()) {
            IacucProtocolStudyGroupLocation newStudyGroupLocation = (IacucProtocolStudyGroupLocation)ObjectUtils.deepCopy(newIacucProtocolStudyGroupLocation);
            newStudyGroupLocation.setLocationGroupIndex(locationGroupIndex);
            updateAttributesForNewProcedureLocation(newStudyGroupLocation, protocolStudyGroup, protocol);
            protocolStudyGroup.getIacucProtocolStudyGroupLocations().add(newStudyGroupLocation);
        }
    }
    
    /**
     * This method is to add protocol study group bean and format display values.
     * Return a list of original study group detail beans
     * @param protocol
     * @param protocolSpeciesAndGroups
     * @param iacucProtocolStudyGroupBean
     * @return
     */
    private List<IacucProtocolStudyGroupDetailBean> addStudyGroupBeans(List<String> protocolSpeciesAndGroups, 
            IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean) {
        List<IacucProtocolStudyGroupDetailBean> updatedDetailBeans = new ArrayList<IacucProtocolStudyGroupDetailBean>();
        HashMap<Integer, List<IacucProtocolStudyGroup>> studiesGroupedBySpecies = getNewProcedureStudyGroupedBySpecies(protocolSpeciesAndGroups);
        for (Map.Entry<Integer, List<IacucProtocolStudyGroup>> entry : studiesGroupedBySpecies.entrySet()) {
            Integer speciesCode = entry.getKey();
            List<IacucProtocolStudyGroup> studyGroups = entry.getValue();
            IacucProtocolStudyGroupDetailBean selectedDetailBean = getDetailBeanGroup(selectedProtocolStudyGroupBean, speciesCode);
            if(ObjectUtils.isNotNull(selectedDetailBean)) {
                addIacucProtocolStudyGroups(studyGroups, selectedDetailBean, speciesCode);
            }else {
                selectedDetailBean = addNewStudyGroupDetailBean(studyGroups, selectedProtocolStudyGroupBean, speciesCode);
            }
            updatedDetailBeans.add(selectedDetailBean);
        }
        return updatedDetailBeans;
    }
    
    
    private IacucProtocolStudyGroupDetailBean getDetailBeanGroup(IacucProtocolStudyGroupBean  selectedProtocolStudyGroupBean, Integer speciesCode) {
        IacucProtocolStudyGroupDetailBean protocolStudyGroupDetailBean = null;
        for(IacucProtocolStudyGroupDetailBean detailBean : selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans()) {
            if(detailBean.getSpeciesCode().equals(speciesCode)) {
                protocolStudyGroupDetailBean = detailBean;
                break;
            }
        }
        return protocolStudyGroupDetailBean;
    }
    
    /**
     * This method is to add a new study group detail bean
     * Detail bean is grouped by species and all study groups linked to this group
     * are stored here.
     * @param studyGroups
     * @param selectedProtocolStudyGroupBean
     */
    private IacucProtocolStudyGroupDetailBean addNewStudyGroupDetailBean(List<IacucProtocolStudyGroup> studyGroups, 
            IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, Integer speciesCode) {
        
        /*
        Integer speciesCount = 0;
        TreeSet<IacucPainCategory> painCategory = new TreeSet<IacucPainCategory>();
        //HashMap<String, Integer> painCategoryCodes = new HashMap<String, Integer>();
        List<String> speciesAndGroups = new ArrayList<String>();
        */
        
        IacucProtocolStudyGroupDetailBean newIacucProtocolStudyGroupDetailBean = new IacucProtocolStudyGroupDetailBean();
        addIacucProtocolStudyGroups(studyGroups, newIacucProtocolStudyGroupDetailBean, speciesCode);
        selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans().add(newIacucProtocolStudyGroupDetailBean);
        return newIacucProtocolStudyGroupDetailBean;
        
        /*
        for(IacucProtocolStudyGroup iacucProtocolStudyGroup : studyGroups) {
            IacucProtocolSpecies protocolSpecies = iacucProtocolStudyGroup.getIacucProtocolSpecies();
            painCategory.add(protocolSpecies.getIacucPainCategory());
            //painCategoryCodes.put(protocolSpecies.getIacucPainCategory().getPainCategory(), protocolSpecies.getIacucPainCategory().getPainCategoryCode());
            speciesCount = speciesCount + protocolSpecies.getSpeciesCount();
            speciesAndGroups.add(protocolSpecies.getGroupAndSpecies());
            //protocolStudyGroups.add(iacucProtocolStudyGroup);
            newIacucProtocolStudyGroupDetailBean.getIacucProtocolStudyGroups().add(iacucProtocolStudyGroup);
        }
        newIacucProtocolStudyGroupDetailBean.setSpeciesCode(speciesCode);
        newIacucProtocolStudyGroupDetailBean.setMaxPainCategory(painCategory.last().getPainCategory());
        newIacucProtocolStudyGroupDetailBean.setMaxPainCategoryCode(painCategory.last().getPainCategoryCode());
        newIacucProtocolStudyGroupDetailBean.setSpeciesAndGroupsText(speciesAndGroups);
        newIacucProtocolStudyGroupDetailBean.setTotalSpeciesCount(speciesCount);
        selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans().add(newIacucProtocolStudyGroupDetailBean);
        */
    }
    
    private void addIacucProtocolStudyGroups(List<IacucProtocolStudyGroup> studyGroups, 
            IacucProtocolStudyGroupDetailBean iacucProtocolStudyGroupDetailBean, Integer speciesCode) {
        Integer speciesCount = 0;
        TreeSet<IacucPainCategory> painCategory = new TreeSet<IacucPainCategory>();
        List<String> speciesAndGroups = new ArrayList<String>();
        for(IacucProtocolStudyGroup iacucProtocolStudyGroup : studyGroups) {
            IacucProtocolSpecies protocolSpecies = iacucProtocolStudyGroup.getIacucProtocolSpecies();
            painCategory.add(protocolSpecies.getIacucPainCategory());
            speciesCount = speciesCount + protocolSpecies.getSpeciesCount();
            speciesAndGroups.add(protocolSpecies.getGroupAndSpecies());
            iacucProtocolStudyGroupDetailBean.getIacucProtocolStudyGroups().add(iacucProtocolStudyGroup);
        }
        
        if(ObjectUtils.isNotNull(iacucProtocolStudyGroupDetailBean.getSpeciesCode())) {
            painCategory.add(iacucProtocolStudyGroupDetailBean.getMaxIacucPainCategory());
            speciesAndGroups.addAll(iacucProtocolStudyGroupDetailBean.getSpeciesAndGroupsText());
            speciesCount = speciesCount + iacucProtocolStudyGroupDetailBean.getTotalSpeciesCount();
        }

        iacucProtocolStudyGroupDetailBean.setSpeciesCode(speciesCode);
        iacucProtocolStudyGroupDetailBean.setMaxPainCategory(painCategory.last().getPainCategory());
        iacucProtocolStudyGroupDetailBean.setMaxPainCategoryCode(painCategory.last().getPainCategoryCode());
        iacucProtocolStudyGroupDetailBean.setMaxIacucPainCategory(painCategory.last());
        iacucProtocolStudyGroupDetailBean.setSpeciesAndGroupsText(speciesAndGroups);
        iacucProtocolStudyGroupDetailBean.setTotalSpeciesCount(speciesCount);
    }
    
    /**
     * This method is to build studies grouped by species.
     * We have a list of protocol species and groups as defined in the protocol and we group them
     * by species.
     * @param protocolSpeciesAndGroups
     * @return
     */
    private HashMap<Integer, List<IacucProtocolStudyGroup>> getNewProcedureStudyGroupedBySpecies(List<String> protocolSpeciesAndGroups) {
        HashMap<Integer, List<IacucProtocolStudyGroup>> studiesGroupedBySpecies = new HashMap<Integer, List<IacucProtocolStudyGroup>>();
        List<IacucProtocolStudyGroup> studyGroups = null;
        for(String iacucProtocolSpeciesId : protocolSpeciesAndGroups) {
            IacucProtocolStudyGroup iacucProtocolStudyGroup = new IacucProtocolStudyGroup();
            iacucProtocolStudyGroup.setIacucProtocolSpeciesId(Integer.parseInt(iacucProtocolSpeciesId));
            Integer speciesCode = getSpeciesCodeFromIacucProtocolSpecies(iacucProtocolStudyGroup);
            studyGroups = studiesGroupedBySpecies.get(speciesCode) == null ? new ArrayList<IacucProtocolStudyGroup>() : studiesGroupedBySpecies.get(speciesCode);
            studyGroups.add(iacucProtocolStudyGroup);
            studiesGroupedBySpecies.put(speciesCode, studyGroups);
        }
        return studiesGroupedBySpecies;
    }

    /**
     * This method is to group existing study group records based on species 
     * @param protocolSpeciesAndGroups
     * @param iacucProtocolStudyGroups
     * @return
     */
    private HashMap<Integer, List<IacucProtocolStudyGroup>> getProcedureStudyGroupedBySpecies(List<String> protocolSpeciesAndGroups, List<IacucProtocolStudyGroup> iacucProtocolStudyGroups) {
        HashMap<Integer, List<IacucProtocolStudyGroup>> studiesGroupedBySpecies = new HashMap<Integer, List<IacucProtocolStudyGroup>>();
        List<IacucProtocolStudyGroup> studyGroups = null;
        for(String iacucProtocolSpeciesId : protocolSpeciesAndGroups) {
            List<IacucProtocolStudyGroup> currentStudyGroups = getStudyGroupsForProtocolSpeciesId(iacucProtocolStudyGroups, Integer.parseInt(iacucProtocolSpeciesId));
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : currentStudyGroups) {
                Integer speciesCode = getSpeciesCodeFromIacucProtocolSpecies(iacucProtocolStudyGroup);
                studyGroups = studiesGroupedBySpecies.get(speciesCode) == null ? new ArrayList<IacucProtocolStudyGroup>() : studiesGroupedBySpecies.get(speciesCode);
                studyGroups.add(iacucProtocolStudyGroup);
                studiesGroupedBySpecies.put(speciesCode, studyGroups);
            }
        }
        return studiesGroupedBySpecies;
    }
    
    /**
     * This method is to get species code from iacuc protocol species
     * @param iacucProtocolStudyGroup
     * @return
     */
    private Integer getSpeciesCodeFromIacucProtocolSpecies(IacucProtocolStudyGroup iacucProtocolStudyGroup) {
        iacucProtocolStudyGroup.refreshReferenceObject("iacucProtocolSpecies");
        IacucProtocolSpecies protocolSpecies = iacucProtocolStudyGroup.getIacucProtocolSpecies();
        protocolSpecies.refreshReferenceObject("iacucPainCategory");
        protocolSpecies.refreshReferenceObject("iacucSpecies");
        IacucSpecies iacucSpecies = protocolSpecies.getIacucSpecies();
        return iacucSpecies.getSpeciesCode();
    }
    
    /**
     * This method is to get protocol study groups filtered by protocol species id
     * Protocol species id - species selected for this protocol
     * @param iacucProtocolStudyGroups
     * @param iacucProtocolSpeciesId
     * @return
     */
    private List<IacucProtocolStudyGroup> getStudyGroupsForProtocolSpeciesId(List<IacucProtocolStudyGroup> iacucProtocolStudyGroups, Integer iacucProtocolSpeciesId) {
        List<IacucProtocolStudyGroup> studyGroups = new ArrayList<IacucProtocolStudyGroup>();
        for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroups) {
            if(iacucProtocolStudyGroup.getIacucProtocolSpeciesId().equals(iacucProtocolSpeciesId)) {
                studyGroups.add(iacucProtocolStudyGroup);
            }
        }
        return studyGroups;
    }
    
    /**
     * This method is to add person responsible to each study group.
     * This method is invoked when user select group/species and responsible persons and
     * when responsible persons are added under each group - section Persons Responsible
     * @param protocol
     * @param protocolPersonsResponsible
     * @return
     */
    private List<IacucProcedurePersonResponsible> addPersonResponsible(List<String> protocolPersonsResponsible, 
            IacucProcedurePersonResponsible newIacucProcedurePersonResponsible) {
        List<IacucProcedurePersonResponsible> iacucProcedurePersonsResponsible = new ArrayList<IacucProcedurePersonResponsible>();
        for(String personKey : protocolPersonsResponsible) {
            IacucProcedurePersonResponsible iacucProcedurePersonResponsible = new IacucProcedurePersonResponsible();
            String personId = null;
            String personName = null;
            StringTokenizer personInfo = new StringTokenizer(personKey, Constants.IACUC_PROCEDURE_PERSON_RESPONSIBLE_DELIMITER); 
            while(personInfo.hasMoreTokens()) { 
                personId = personInfo.nextToken(); 
                personName = personInfo.nextToken(); 
            } 
            //personResponsible.add(personName);
            iacucProcedurePersonResponsible.setPersonId(personId);
            iacucProcedurePersonResponsible.setPersonName(personName);
            String description =  ObjectUtils.isNull(newIacucProcedurePersonResponsible) ? null : newIacucProcedurePersonResponsible.getPersonResponsibleDescription();
            iacucProcedurePersonResponsible.setPersonResponsibleDescription(description);
            iacucProcedurePersonResponsible.setTrainings(getIacucPersonTrainingDetails(personId));
            iacucProcedurePersonsResponsible.add(iacucProcedurePersonResponsible);
        }
        return iacucProcedurePersonsResponsible;
    }
    
    protected List<IacucPersonTraining> getIacucPersonTrainingDetails(String personId) {
        return getIacucProtocolPersonTrainingService().getIacucPersonTrainingDetails(personId);
    }
    
    /**
     * This method is to update the bean detail collection
     * update bean details - persons responsible
     * Since user can select multiple person responsible, create new person responsible 
     * for each detail
     * add study group bean to the list
     * @param iacucProcedurePersonsResponsible
     * @param iacucProtocolStudyGroupBean
     */
    private void updateStudyGroupDetailBean(List<IacucProtocolStudyGroupDetailBean> studyGroupDetailBeans, 
            List<IacucProcedurePersonResponsible> iacucProcedurePersonsResponsible, 
            IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, IacucProtocol iacucProtocol) {

        for(IacucProtocolStudyGroupDetailBean detailBean : studyGroupDetailBeans) {
            updateAttributesForNewStudyGroupDetails(detailBean,iacucProtocol,selectedProtocolStudyGroupBean);
            addPersonToStudyGroupAndDetailBean(detailBean, iacucProcedurePersonsResponsible);
        }
        
        /*
        for(IacucProtocolStudyGroupDetailBean detailBean : selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans()) {
            updateAttributesForNewStudyGroupDetails(detailBean,iacucProtocol,selectedProtocolStudyGroupBean);
            addPersonToStudyGroupAndDetailBean(detailBean, iacucProcedurePersonsResponsible);
        }
        */
    }
    
    /**
     * This method is to add new person to study group list and set it in the bean detail
     * @param detailBean
     * @param iacucProcedurePersonsResponsible
     */
    private void addPersonToStudyGroupAndDetailBean(IacucProtocolStudyGroupDetailBean detailBean, 
            List<IacucProcedurePersonResponsible> iacucProcedurePersonsResponsible) {
        detailBean.setNewIacucProcedurePersonResponsible(new IacucProcedurePersonResponsible());
        for(IacucProcedurePersonResponsible personResponsible : iacucProcedurePersonsResponsible) {
            if(!isPersonExists(detailBean, personResponsible.getPersonId())) {
                IacucProcedurePersonResponsible newPersonResponsible = getNewCopyOfPersonResponsible(personResponsible);
                detailBean.getIacucProcedurePersonsResponsible().add(newPersonResponsible);
            }
        }
        addNewStudyGroupPersonResponsible(detailBean, iacucProcedurePersonsResponsible);
    }
    
    /**
     * This method is to check whether a person already exists in the detail bean
     * @param detailBean
     * @param personId
     * @return
     */
    private boolean isPersonExists(IacucProtocolStudyGroupDetailBean detailBean, String personId) {
        boolean personExists = false;
        for(IacucProcedurePersonResponsible personResponsible : detailBean.getIacucProcedurePersonsResponsible()) {
            if(personResponsible.getPersonId().equals(personId)) {
                personExists = true;
                break;
            }
        }
        return personExists;
    }

    
    /**
     * This method is to set attribute values for new study groups
     * @param detailBean
     * @param iacucProtocol
     * @param selectedProtocolStudyGroupBean
     */
    private void updateAttributesForNewStudyGroupDetails(IacucProtocolStudyGroupDetailBean detailBean, IacucProtocol iacucProtocol,
            IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean) {
        for(IacucProtocolStudyGroup protocolStudyGroup : detailBean.getIacucProtocolStudyGroups()) {
            updateAttributesForNewProtocolStudyGroup(protocolStudyGroup, iacucProtocol, detailBean, 
                    selectedProtocolStudyGroupBean);
        }
    }
    
    /**
     * This method is to add new responsible person information to study group collection.
     * @param detailBean
     * @param iacucProcedurePersonsResponsible
     */
    private void addNewStudyGroupPersonResponsible(IacucProtocolStudyGroupDetailBean detailBean, List<IacucProcedurePersonResponsible> iacucProcedurePersonsResponsible) {
        for(IacucProtocolStudyGroup protocolStudyGroup : detailBean.getIacucProtocolStudyGroups()) {
            for(IacucProcedurePersonResponsible personResponsible : iacucProcedurePersonsResponsible) {
                if(!isPersonExists(detailBean, personResponsible.getPersonId())) {
                    IacucProcedurePersonResponsible newPersonResponsible = getNewCopyOfPersonResponsible(personResponsible);
                    updateAttributesForNewProcedurePersonResponsible(newPersonResponsible, protocolStudyGroup);
                    protocolStudyGroup.getIacucProcedurePersonsResponsible().add(newPersonResponsible);
                }
            }
        }
    }
    
    /**
     * This method is to get a copy of person responsible
     * @param personResponsible
     * @return
     */
    private IacucProcedurePersonResponsible getNewCopyOfPersonResponsible(IacucProcedurePersonResponsible personResponsible) {
        return (IacucProcedurePersonResponsible)ObjectUtils.deepCopy(personResponsible);
    }

    /**
     * This method is to get a copy of study group location
     * @param studyGroupLocation
     * @return
     */
    private IacucProtocolStudyGroupLocation getNewCopyOfStudyGroupLocation(IacucProtocolStudyGroupLocation studyGroupLocation) {
        return (IacucProtocolStudyGroupLocation)ObjectUtils.deepCopy(studyGroupLocation);
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

    /**
     * This method is to set required information before it can be stored in database.
     * Basically set all information required for a new record
     * @param newIacucProtocolStudyGroupLocation
     * @param protocol
     */
    private void updateAttributesForNewProcedureLocation(IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation, IacucProtocol protocol) { 
        newIacucProtocolStudyGroupLocation.setIacucProtocolStudyGroupLocationId(getNextSequenceNumber(PROTOCOL_STUDY_GROUP_LOCATION_SEQUENCE_ID));
        newIacucProtocolStudyGroupLocation.setProtocolId(protocol.getProtocolId());
        newIacucProtocolStudyGroupLocation.setProtocolNumber(protocol.getProtocolNumber());
        newIacucProtocolStudyGroupLocation.setSequenceNumber(protocol.getSequenceNumber());
    }
    
    /**
     * This method is to set study group reference
     * It is not valid to add this reference to group bean detail data 
     * @param newIacucProtocolStudyGroupLocation
     * @param protocolStudyGroup
     * @param protocol
     */
    private void updateAttributesForNewProcedureLocation(IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation, IacucProtocolStudyGroup protocolStudyGroup, 
            IacucProtocol protocol) { 
        newIacucProtocolStudyGroupLocation.setStudyGroupLocationId(getNextStudyGroupLocationId(protocolStudyGroup));
        newIacucProtocolStudyGroupLocation.setIacucProtocolStudyGroupId(protocolStudyGroup.getIacucProtocolStudyGroupId());
        updateAttributesForNewProcedureLocation(newIacucProtocolStudyGroupLocation, protocol);
    }
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#getRevisedStudyGroupBeans(java.util.List)
     */
    public List<IacucProtocolStudyGroupBean> getRevisedStudyGroupBeans(IacucProtocol iacucProtocol) {
        List<IacucProtocolStudyGroupBean> studyGroupBeans = iacucProtocol.getIacucProtocolStudyGroupBeans();
        List<IacucProtocolStudyGroup> iacucProtocolStudyGroups = iacucProtocol.getIacucProtocolStudyGroups();
        if(studyGroupBeans.isEmpty()) {
            studyGroupBeans = getNewListOfStudyGroupBeans();
            if(!iacucProtocolStudyGroups.isEmpty()) {
                groupProtocolStudyAndBeans(studyGroupBeans, iacucProtocol);
            }
        }
       return studyGroupBeans;
    }
    
    /**
     * This method is to get all procedure categories and format study group bean to display in ui
     * This method is invoked initially when there are no procedures added to the protocol
     * A study group bean is created for each procedure/category code
     * @return
     */
    private List<IacucProtocolStudyGroupBean> getNewListOfStudyGroupBeans() {
        List<IacucProtocolStudyGroupBean> studyGroupBeans = new ArrayList<IacucProtocolStudyGroupBean>();
        for(IacucProcedure iacucProcedure : getAllProcedures()) {
            IacucProtocolStudyGroupBean newIacucProtocolStudyGroupBean = new IacucProtocolStudyGroupBean();
            newIacucProtocolStudyGroupBean.setProcedureCategoryCode(iacucProcedure.getProcedureCategoryCode());
            newIacucProtocolStudyGroupBean.setProcedureCode(iacucProcedure.getProcedureCode());
            newIacucProtocolStudyGroupBean.setProcedureDescription(iacucProcedure.getProcedureDescription());
            newIacucProtocolStudyGroupBean.setProcedureCategory(iacucProcedure.getIacucProcedureCategory().getProcedureCategory());
            studyGroupBeans.add(newIacucProtocolStudyGroupBean);
        }
        return studyGroupBeans;
    }

    /**
     * This method is to group protocol study group and the display beans.
     * @param studyGroupBeans
     * @param iacucProtocolStudyGroup
     * @return
     */
    private void groupProtocolStudyAndBeans(List<IacucProtocolStudyGroupBean> studyGroupBeans, IacucProtocol iacucProtocol) {
        List<IacucProtocolStudyGroup> iacucProtocolStudyGroups = iacucProtocol.getIacucProtocolStudyGroups();
        List<String> protocolSpeciesIds = getExistingProtocolSpeciesId(iacucProtocolStudyGroups);
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : studyGroupBeans) {
            List<IacucProtocolStudyGroup> matchingProtocolStudyGroups = getMatchingStudyGroups(iacucProtocolStudyGroupBean, iacucProtocolStudyGroups);
            HashMap<Integer, List<IacucProtocolStudyGroup>> studiesGroupedBySpecies = getProcedureStudyGroupedBySpecies(protocolSpeciesIds, matchingProtocolStudyGroups);
            for (Map.Entry<Integer, List<IacucProtocolStudyGroup>> entry : studiesGroupedBySpecies.entrySet()) {
                //List<String> speciesAndGroups = new ArrayList<String>();
                List<IacucProtocolStudyGroup> protocolStudyGroups = entry.getValue();
                //List<String> beanProtocolSpeciesIds = getExistingProtocolSpeciesId(protocolStudyGroups);
                Integer speciesCode = entry.getKey();
                addNewStudyGroupDetailBean(protocolStudyGroups, iacucProtocolStudyGroupBean, speciesCode);
            }
        }
        syncStudyGroupAndBeans(studyGroupBeans);

    }
    
    
    private void syncStudyGroupAndBeans(List<IacucProtocolStudyGroupBean> studyGroupBeans) {
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : studyGroupBeans) {
            for(IacucProtocolStudyGroupDetailBean studyGroupDetailBean : iacucProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans()) {
                syncPersonsResposible(studyGroupDetailBean);
                List<IacucProtocolStudyGroupLocation> protocolGroupLocations = getDistinctGroupLocationsForDetailBean(studyGroupDetailBean);
                for(IacucProtocolStudyGroupLocation groupLocation : protocolGroupLocations) {
                    IacucProtocolStudyGroupLocation newStudyGroupLocation = (IacucProtocolStudyGroupLocation)ObjectUtils.deepCopy(groupLocation);
                    studyGroupDetailBean.getIacucProtocolStudyGroupLocations().add(newStudyGroupLocation);
                }
            }
        }
    }
    
    /**
     * This method is to sync detail bean and study group persons responsible
     * @param studyGroupDetailBean
     */
    private void syncPersonsResposible(IacucProtocolStudyGroupDetailBean studyGroupDetailBean) {
        List<IacucProcedurePersonResponsible> protocolPersonsResponsible = getDistinctPersonsResponsibleForDetailBean(studyGroupDetailBean);
        for(IacucProcedurePersonResponsible personResponsible : protocolPersonsResponsible) {
            IacucProcedurePersonResponsible newPersonResponsible = getNewCopyOfPersonResponsible(personResponsible);
            newPersonResponsible.setTrainings(getIacucPersonTrainingDetails(newPersonResponsible.getPersonId()));
            studyGroupDetailBean.getIacucProcedurePersonsResponsible().add(newPersonResponsible);
        }
    }
    
    /**
     * This method is to get a list of distinct list of persons responsible and
     * add to the corresponding detail bean so that it gets populated correctly in the tab
     * @param studyGroupDetailBean
     * @return
     */
    private List<IacucProcedurePersonResponsible> getDistinctPersonsResponsibleForDetailBean(IacucProtocolStudyGroupDetailBean studyGroupDetailBean) {
        Set<String> distinctPersonIds = new HashSet<String>();
        List<IacucProcedurePersonResponsible> beanPersonsResponsible = new ArrayList<IacucProcedurePersonResponsible>();
        for(IacucProtocolStudyGroup protocolStudyGroup : studyGroupDetailBean.getIacucProtocolStudyGroups()) {
            for(IacucProcedurePersonResponsible personResponsible : protocolStudyGroup.getIacucProcedurePersonsResponsible()) {
                if(!distinctPersonIds.contains(personResponsible.getPersonId())) {
                    distinctPersonIds.add(personResponsible.getPersonId());
                    beanPersonsResponsible.add(personResponsible);
                }
            }
        }
        return beanPersonsResponsible;
    }

    /**
     * This method is to get a list of distinct list of locations and
     * add to the corresponding detail bean so that it gets populated correctly in the tab
     * @param studyGroupDetailBean
     * @return
     */
    private List<IacucProtocolStudyGroupLocation> getDistinctGroupLocationsForDetailBean(IacucProtocolStudyGroupDetailBean studyGroupDetailBean) {
        Set<Integer> distinctLocationIndexes = new HashSet<Integer>();
        List<IacucProtocolStudyGroupLocation> beanGroupLocations = new ArrayList<IacucProtocolStudyGroupLocation>();
        for(IacucProtocolStudyGroup protocolStudyGroup : studyGroupDetailBean.getIacucProtocolStudyGroups()) {
            for(IacucProtocolStudyGroupLocation groupLocation : protocolStudyGroup.getIacucProtocolStudyGroupLocations()) {
                if(!distinctLocationIndexes.contains(groupLocation.getLocationGroupIndex())) {
                    distinctLocationIndexes.add(groupLocation.getLocationGroupIndex());
                    beanGroupLocations.add(groupLocation);
                }
            }
        }
        return beanGroupLocations;
    }

    /**
     * This method is to add study group and it collection details when a new study group
     * is added. 
     * @param selectedProtocolStudyGroupBean
     * @param iacucProtocol
     * @param protocolStudyGroups
     */
    private void addIacucProtocolStudyGroup(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, IacucProtocol iacucProtocol) {
        for(IacucProtocolStudyGroupDetailBean detailBean : selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans()) {
            for(IacucProtocolStudyGroup studyGroup : detailBean.getIacucProtocolStudyGroups()) {
                studyGroup.setStudyGroupId(getNextStudyGroupId(iacucProtocol));
                iacucProtocol.getIacucProtocolStudyGroups().add(studyGroup);
            }
        }
    }
    
    /**
     * This method is to set values to reference attributes in person responsible entity
     * Basically set all information required for a new record
     * Also set the protocol references to record information in database.
     * @param personResponsible
     * @param studyGroup
     */
    private void updateAttributesForNewProcedurePersonResponsible(IacucProcedurePersonResponsible personResponsible, 
            IacucProtocolStudyGroup studyGroup) {
        personResponsible.setIacucProcedurePersonResponsibleId(getNextSequenceNumber(PROTOCOL_PERSON_RESPONSIBLE_SEQUENCE_ID));
        personResponsible.setIacucProtocolStudyGroupId(studyGroup.getIacucProtocolStudyGroupId());
        personResponsible.setProtocolNumber(studyGroup.getProtocolNumber());
        personResponsible.setSequenceNumber(studyGroup.getSequenceNumber());
    }
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#updateIacucProtocolStudyGroup(org.kuali.kra.iacuc.IacucProtocol)
     */
    public void updateIacucProtocolStudyGroup(IacucProtocol protocol) {
        List<IacucProtocolStudyGroupBean> iacucProtocolStudyGroupBeans = protocol.getIacucProtocolStudyGroupBeans();
        List<IacucProtocolStudyGroup> iacucProtocolStudyGroups = protocol.getIacucProtocolStudyGroups();
        
        List<String> protocolSpeciesIds = getExistingProtocolSpeciesId(protocol.getIacucProtocolStudyGroups());

        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : iacucProtocolStudyGroupBeans) {
            List<IacucProtocolStudyGroup> matchingProtocolStudyGroups = getMatchingStudyGroups(iacucProtocolStudyGroupBean, iacucProtocolStudyGroups);
            HashMap<Integer, List<IacucProtocolStudyGroup>> studiesGroupedBySpecies = getProcedureStudyGroupedBySpecies(protocolSpeciesIds, matchingProtocolStudyGroups);
            for(IacucProtocolStudyGroupDetailBean iacucProtocolStudyGroupDetailBean : iacucProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans()) {
                for (Map.Entry<Integer, List<IacucProtocolStudyGroup>> entry : studiesGroupedBySpecies.entrySet()) {
                    List<String> speciesAndGroups = new ArrayList<String>();
                    List<IacucProtocolStudyGroup> protocolStudyGroups = entry.getValue();
                    for(IacucProtocolStudyGroup iacucProtocolStudyGroup : protocolStudyGroups) {
                        IacucProtocolSpecies protocolSpecies = iacucProtocolStudyGroup.getIacucProtocolSpecies();
                        speciesAndGroups.add(protocolSpecies.getGroupAndSpecies());
                    }
                    if(isMatchingSpeciesAndGroups(iacucProtocolStudyGroupDetailBean.getSpeciesAndGroupsText(), speciesAndGroups)) {
                        updateExistingStudyGroupPersonResponsible(iacucProtocolStudyGroupDetailBean.getIacucProcedurePersonsResponsible(), 
                                protocolStudyGroups);
                        updateExistingStudyGroupLocation(iacucProtocolStudyGroupDetailBean.getIacucProtocolStudyGroupLocations(), 
                                protocolStudyGroups);
                    }
                }
            }
        }
    }

    private void printStudyGroup(List<IacucProtocolStudyGroup> iacucProtocolStudyGroups, String message) {
        System.out.println("printing ==========> " + message);
        for(IacucProtocolStudyGroup studyGroup : iacucProtocolStudyGroups) {
            printForStudyGroup(studyGroup);
        }
        System.out.println("--------------------------------------------------------------------------\r\n");
    }
    
    private void printForStudyGroup(IacucProtocolStudyGroup studyGroup) {
        System.out.println("pk study grp id ==========> " + studyGroup.getIacucProtocolStudyGroupId());
        System.out.println("protocol id ==========> " + studyGroup.getProtocolId());
        System.out.println("protocol num ==========> " + studyGroup.getProtocolNumber());
        System.out.println("seq num ==========> " + studyGroup.getSequenceNumber());
        System.out.println("study grp id ==========> " + studyGroup.getStudyGroupId());
        System.out.println("prot species id ==========> " + studyGroup.getIacucProtocolSpeciesId());
        System.out.println("proc cat code ==========> " + studyGroup.getProcedureCategoryCode());
        System.out.println("proc code ==========> " + studyGroup.getProcedureCode());
        printDetailBeanPersons(studyGroup.getIacucProcedurePersonsResponsible());
        printGroupLocations(studyGroup.getIacucProtocolStudyGroupLocations());
    }
    private void printDetailBeanPersons(List<IacucProcedurePersonResponsible> personsResponsible) {
        System.out.println("################ printing bean person information #####################");
        for(IacucProcedurePersonResponsible personResponsible : personsResponsible) {
            System.out.println("pr study grp id ==========> " + personResponsible.getIacucProtocolStudyGroupId());
            System.out.println("person id ==========> " + personResponsible.getPersonId());
            System.out.println("person nm ==========> " + personResponsible.getPersonName());
            System.out.println("descr ==========> " + personResponsible.getPersonResponsibleDescription());
        }
        System.out.println("--------------------------------------------------------------------------\r\n");
    }

    private void printGroupLocations(List<IacucProtocolStudyGroupLocation> groupLocations) {
        System.out.println("################ printing group locations #####################");
        for(IacucProtocolStudyGroupLocation groupLocation : groupLocations) {
            System.out.println("group desc ==========> " + groupLocation.getStudyGroupLocationDescription());
            System.out.println("study grp id ==========> " + groupLocation.getIacucProtocolStudyGroupId());
            System.out.println("grp location id ==========> " + groupLocation.getIacucProtocolStudyGroupLocationId());
            System.out.println("group index ==========> " + groupLocation.getLocationGroupIndex());
        }
        System.out.println("--------------------------------------------------------------------------\r\n");
    }
    
    /**
     * This method is to check elements in species and groups
     * @param beanSpeciesAndGroups
     * @param protocolSpeciesAndGroups
     * @return
     */
    private boolean isMatchingSpeciesAndGroups(List<String> beanSpeciesAndGroups, List<String> protocolSpeciesAndGroups) {
        List<String> copyOfProtocolSpeciesAndGroups = new ArrayList<String>();
        copyOfProtocolSpeciesAndGroups.addAll(protocolSpeciesAndGroups);
        copyOfProtocolSpeciesAndGroups.removeAll(beanSpeciesAndGroups);
        return copyOfProtocolSpeciesAndGroups.size() == 0;
    }
    
    /**
     * This method is to get list of current protocol species id
     * @param iacucProtocolStudyGroups
     * @return
     */
    private List<String> getExistingProtocolSpeciesId(List<IacucProtocolStudyGroup> iacucProtocolStudyGroups) {
        List<String> protocolSpeciesAndGroups = new ArrayList<String>();
        for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroups) {
            protocolSpeciesAndGroups.add(iacucProtocolStudyGroup.getIacucProtocolSpeciesId().toString());
        }
        return protocolSpeciesAndGroups;
    }
    
    /**
     * This method is to update individual study group records that are part of the grouped category.
     * Here attributes linked to person responsible are updated.
     * @param beanStudyGroups
     */
    private void updateExistingStudyGroupPersonResponsible(List<IacucProcedurePersonResponsible> beanPersonsResponsible, List<IacucProtocolStudyGroup> iacucProtocolStudyGroups) {
        for(IacucProcedurePersonResponsible beanPersonResponsible : beanPersonsResponsible) {
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroups) {
                for(IacucProcedurePersonResponsible protocolPersonResponsible : iacucProtocolStudyGroup.getIacucProcedurePersonsResponsible()) {
                    if(protocolPersonResponsible.getPersonId().equalsIgnoreCase(beanPersonResponsible.getPersonId())) {
                        protocolPersonResponsible.setPersonResponsibleDescription(beanPersonResponsible.getPersonResponsibleDescription());
                    }
                }
            }
        }
    }
    
    /**
     * This method is to find a matching list of grouped bean protocol study groups
     * and study groups found in the protocol collection.
     * @param studyGroupBean
     * @param protocolStudyGroups
     * @return
     */
    private List<IacucProtocolStudyGroup> getMatchingStudyGroups(IacucProtocolStudyGroupBean studyGroupBean, List<IacucProtocolStudyGroup> protocolStudyGroups) {
        List<IacucProtocolStudyGroup> matchingStudyGroups = new ArrayList<IacucProtocolStudyGroup>();
        for(IacucProtocolStudyGroup protocolStudyGroup : protocolStudyGroups) {
            if(isMatchingStudyGroup(studyGroupBean, protocolStudyGroup)) {
                matchingStudyGroups.add(protocolStudyGroup);
            }
        }
        return matchingStudyGroups;
    }
    
    /**
     * This method is to check whether study group listed under bean grouping is the same
     * as study group listed in protocol collection.
     * @param studyGroupBean
     * @param iacucProtocolStudyGroup
     * @return
     */
    private boolean isMatchingStudyGroup(IacucProtocolStudyGroupBean studyGroupBean, IacucProtocolStudyGroup iacucProtocolStudyGroup) {
        boolean isMatching = false;
        if(studyGroupBean.getProcedureCategoryCode().equals(iacucProtocolStudyGroup.getProcedureCategoryCode()) && 
                studyGroupBean.getProcedureCode().equals(iacucProtocolStudyGroup.getProcedureCode())) {
            isMatching = true;
        }
        return isMatching;
    }

    /**
     * This method is to update individual study group records that are part of the grouped category.
     * Here attributes linked to study group location are updated.
     * @param beanStudyGroupLocations
     */
    private void updateExistingStudyGroupLocation(List<IacucProtocolStudyGroupLocation> beanLocations, List<IacucProtocolStudyGroup> iacucProtocolStudyGroups) {
        for(IacucProtocolStudyGroupLocation beanLocation : beanLocations) {
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroups) {
                for(IacucProtocolStudyGroupLocation protocolLocation : iacucProtocolStudyGroup.getIacucProtocolStudyGroupLocations()) {
                    if(protocolLocation.getLocationGroupIndex().equals(beanLocation.getLocationGroupIndex())) { 
                        protocolLocation.setLocationId(beanLocation.getLocationId());
                        protocolLocation.setLocationTypeCode(beanLocation.getLocationTypeCode());
                        protocolLocation.setLocationRoom(beanLocation.getLocationRoom());
                        protocolLocation.setStudyGroupLocationDescription(beanLocation.getStudyGroupLocationDescription());
                    }
                }
            }
        }
    }
    
    /**
     * This method is to update the study group information found in the group bean.
     * Basically set all information required for a new record
     * Also set the protocol references to record information in database.
     * @param studyGroup
     * @param protocol
     * @param studyGroupDetailBean
     * @param studyGroupBean
     */
    private void updateAttributesForNewProtocolStudyGroup(IacucProtocolStudyGroup studyGroup, IacucProtocol protocol, 
            IacucProtocolStudyGroupDetailBean studyGroupDetailBean, IacucProtocolStudyGroupBean studyGroupBean) {
        studyGroup.setIacucProtocolStudyGroupId(getNextSequenceNumber(PROTOCOL_STUDY_GROUP_SEQUENCE_ID));
        updateAttributesForProtocolStudyGroup(studyGroup, protocol, studyGroupDetailBean, studyGroupBean);
    }

    /**
     * This method is to update the study group information found in the group bean.
     * set all information required for existing record
     * Also set the protocol references to record information in database.
     * @param studyGroup
     * @param protocol
     * @param studyGroupDetailBean
     * @param studyGroupBean
     */
    private void updateAttributesForProtocolStudyGroup(IacucProtocolStudyGroup studyGroup, IacucProtocol protocol, 
            IacucProtocolStudyGroupDetailBean studyGroupDetailBean, IacucProtocolStudyGroupBean studyGroupBean) {
        studyGroup.setProtocolNumber(protocol.getProtocolNumber());
        studyGroup.setSequenceNumber(protocol.getSequenceNumber());
        studyGroup.setPainCategory(studyGroupDetailBean.getMaxPainCategory());
        studyGroup.setPainCategoryCode(studyGroupDetailBean.getMaxPainCategoryCode());
        studyGroup.setProcedureCategoryCode(studyGroupBean.getProcedureCategoryCode());
        studyGroup.setProcedureCode(studyGroupBean.getProcedureCode());
        studyGroup.setCount(studyGroupDetailBean.getTotalSpeciesCount());
    }
    
    /**
     * This method is to get the next study group id.
     * It is just a serial number generated based on the list of study groups
     * @param protocol
     * @return
     */
    private Integer getNextStudyGroupId(IacucProtocol protocol) {
        int totalStudyGroups = protocol.getIacucProtocolStudyGroups().size();
        Integer nextStudyGroupId = 1;
        if(totalStudyGroups > 0) {
            List<IacucProtocolStudyGroup> sortedStudyGroups = getSortedStudyGroups(protocol);
            IacucProtocolStudyGroup studyGroup = sortedStudyGroups.get(totalStudyGroups - 1);
            nextStudyGroupId = studyGroup.getStudyGroupId() + 1;
        }
        return nextStudyGroupId;
    }
    
    /**
     * This method is to get the next study group location id.
     * It is just a serial number generated based on the list of study group locations
     * @param iacucProtocolStudyGroup
     * @return
     */
    private Integer getNextStudyGroupLocationId(IacucProtocolStudyGroup iacucProtocolStudyGroup) {
        int totalStudyGroupLocs = iacucProtocolStudyGroup.getIacucProtocolStudyGroupLocations().size();
        Integer nextStudyGroupLocationId = 1;
        if(totalStudyGroupLocs > 0) {
            List<IacucProtocolStudyGroupLocation> sortedStudyGroupLocations = getSortedStudyGroupLocations(iacucProtocolStudyGroup);
            IacucProtocolStudyGroupLocation studyGroupLocation = sortedStudyGroupLocations.get(totalStudyGroupLocs - 1);
            nextStudyGroupLocationId = studyGroupLocation.getStudyGroupLocationId() + 1;
        }
        return nextStudyGroupLocationId;
    }

    /**
     * This method is to get a sorted list of current study groups.
     * @param protocol
     * @return
     */
    public List<IacucProtocolStudyGroup> getSortedStudyGroups(IacucProtocol protocol) {
        List<IacucProtocolStudyGroup> protocolStudyGroups = new ArrayList<IacucProtocolStudyGroup>();
        for (IacucProtocolStudyGroup studyGroup : protocol.getIacucProtocolStudyGroups()) {
            protocolStudyGroups.add((IacucProtocolStudyGroup) ObjectUtils.deepCopy(studyGroup));
        }
        Collections.sort(protocolStudyGroups, new Comparator<IacucProtocolStudyGroup>() {
            public int compare(IacucProtocolStudyGroup group1, IacucProtocolStudyGroup group2) {
                return group1.getStudyGroupId().compareTo(group2.getStudyGroupId());
            }
        });
        return protocolStudyGroups;
    }

    /**
     * This method is to get a sorted list of current study group locations.
     * @param iacucProtocolStudyGroup
     * @return
     */
    public List<IacucProtocolStudyGroupLocation> getSortedStudyGroupLocations(IacucProtocolStudyGroup iacucProtocolStudyGroup) {
        List<IacucProtocolStudyGroupLocation> protocolStudyGroupLocations = new ArrayList<IacucProtocolStudyGroupLocation>();
        for (IacucProtocolStudyGroupLocation studyGroupLocation : iacucProtocolStudyGroup.getIacucProtocolStudyGroupLocations()) {
            protocolStudyGroupLocations.add((IacucProtocolStudyGroupLocation) ObjectUtils.deepCopy(studyGroupLocation));
        }
        Collections.sort(protocolStudyGroupLocations, new Comparator<IacucProtocolStudyGroupLocation>() {
            public int compare(IacucProtocolStudyGroupLocation location1, IacucProtocolStudyGroupLocation location2) {
                return location1.getStudyGroupLocationId().compareTo(location2.getStudyGroupLocationId());
            }
        });
        return protocolStudyGroupLocations;
    }
    
    
    /**
     * This method is to get the next sequence number
     * This is the primary key
     * @return
     */
    private Integer getNextSequenceNumber(String sequenceKey) {
        return getSequenceAccessorService().getNextAvailableSequenceNumber(sequenceKey).intValue();
    }

    public SequenceAccessorService getSequenceAccessorService() {
        return sequenceAccessorService;
    }

    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }

}
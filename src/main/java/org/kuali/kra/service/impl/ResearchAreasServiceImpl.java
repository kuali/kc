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
package org.kuali.kra.service.impl;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipExpertise;
import org.kuali.kra.committee.bo.CommitteeResearchArea;
import org.kuali.kra.dao.ResearchAreaReferencesDao;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.protocol.research.ProtocolResearchArea;
import org.kuali.kra.service.ResearchAreaCurrentReferencerHolder;
import org.kuali.kra.service.ResearchAreasService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KNSConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * This class...
 */
public class ResearchAreasServiceImpl implements ResearchAreasService {

    private static final String COLUMN_CODE_1 = "%3A";
    private static final String COLUMN_CODE_2 = "%4A";
    private static final String CREATE_RESEARCH_AREA = "RaCreate";
    private static final String UPDATE_PARENT_RESEARCH_AREA = "RaUpdateParent";
    private static final String UPDATE_RESEARCH_AREA_ACTIVE_FLAG = "RaUpdateActiveIndicator";
    private static final String UPDATE_RESEARCH_AREA_DESCRIPTION = "RaUpdateDescription";
    private static final String RESEARCH_AREA_CODE = "researchAreaCode";
    private static final String COMMITTEE_ID = "committeeId";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    private static final String CODE = "Code";
    private static final String TRUE = "true";
    private static final String ACTIVE = "Active";
    private static final String DESCRIPTION = "Description";
    private static final String PARENT_CODE = "ParentCode";
    private static final String PARENT_RESEARCH_AREA_CODE = "parentResearchAreaCode";
    private static final String NEW_PARENT = "NewParent";
    private BusinessObjectService businessObjectService;
    private ResearchAreaReferencesDao researchAreaReferencesDao;
    
   
    /**
     * 
     * @see org.kuali.kra.service.ResearchAreasService#getSubResearchAreasForTreeView(java.lang.String)
     */
    public String getSubResearchAreasForTreeView(String researchAreaCode, boolean activeOnly) {
        String researchAreas = "<h3>";
        for (ResearchArea researchArea : getSubResearchAreas(researchAreaCode, activeOnly)) {
            researchAreas = researchAreas + researchArea.getResearchAreaCode() +KNSConstants.BLANK_SPACE+COLUMN_CODE_1+KNSConstants.BLANK_SPACE+ researchArea.getDescription() +KNSConstants.BLANK_SPACE+COLUMN_CODE_2+KNSConstants.BLANK_SPACE+ researchArea.isActive()+"</h3><h3>";
        }
        researchAreas = researchAreas.substring(0, researchAreas.length() - 4);        
        return researchAreas;
        
    }
   
    /*
     * call businessobjectservice to get a list of sub research areas of 'researchareacode'
     */
    @SuppressWarnings("unchecked")
    protected List<ResearchArea> getSubResearchAreas(String researchAreaCode, boolean activeOnly) {
        List<ResearchArea> researchAreasList = new ArrayList<ResearchArea>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(PARENT_RESEARCH_AREA_CODE, researchAreaCode);
        if (activeOnly) {
            fieldValues.put("active", activeOnly);
        }
        researchAreasList.addAll(businessObjectService.findMatchingOrderBy(ResearchArea.class, fieldValues, RESEARCH_AREA_CODE, true));
        return researchAreasList;
    }

    /**
     * 
     * @see org.kuali.kra.service.ResearchAreasService#isResearchAreaExist(java.lang.String, java.lang.String)
     */
    public boolean isResearchAreaExist(String researchAreaCode, String researchAreas) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(RESEARCH_AREA_CODE, researchAreaCode);
        boolean isExist = businessObjectService.findByPrimaryKey(ResearchArea.class, fieldValues) != null;
        if (isExist && StringUtils.isNotBlank(researchAreas)) {
            for (String raCode : researchAreas.split(";")) {
                if (raCode.equals(researchAreaCode)) {
                    isExist = false;
                    break;
                } else {
                    if (isExistInDeletedChildren(researchAreaCode, raCode)) {
                        isExist = false;
                        break;                        
                    }
                }
            }
            
        }
        return isExist;
    }
    
    
    /**
     * @see org.kuali.kra.service.ResearchAreasService#checkResearchAreaAndDescendantsNotReferenced(java.lang.String)
     * TODO: optimize by checking only active descendants? Could use the getSubResearchAreas method above
     */  
    public boolean checkResearchAreaAndDescendantsNotReferenced(String researchAreaCode) {
        boolean retValue = true;
        ResearchArea researchArea = getBusinessObjectService().findBySinglePrimaryKey(ResearchArea.class, researchAreaCode);
        if ( (researchArea != null)) {
            retValue = checkResearchAreaTree(researchArea);
        }
        else {
            // TODO throw exception? 
        }
        return retValue;
    }
    
    
    
    // recursive method to check the tree in depth-first fashion
    @SuppressWarnings("unchecked")
    private boolean checkResearchAreaTree(ResearchArea researchArea) {
        boolean retValue = true;
        String researchAreaCode = researchArea.getResearchAreaCode();
        if(  (this.getResearchAreaReferencesDao().isResearchAreaReferencedByAnyCommittee(researchAreaCode)) || 
             (this.getResearchAreaReferencesDao().isResearchAreaReferencedByAnyCommitteeMember(researchAreaCode)) ||
             (this.getResearchAreaReferencesDao().isResearchAreaReferencedByAnyProtocol(researchAreaCode))  ) {
            retValue = false;            
        }
        else {
            // get the children of this research area
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(PARENT_RESEARCH_AREA_CODE, researchAreaCode);
            List<ResearchArea> subResearchAreas = (List<ResearchArea>) getBusinessObjectService().findMatching(ResearchArea.class, fieldValues);
            for (ResearchArea subResearchArea: subResearchAreas) {
                // recursive call
                if(false == checkResearchAreaTree(subResearchArea)) {
                    // no need to check remaining children
                    retValue = false;
                    break;
                }
            } 
        }
        return retValue;
    }
    
    
    
    
    /**
     * @see org.kuali.kra.service.ResearchAreasService#deleteResearchAreaAndDescendants(java.lang.String)
     */
    public void deleteResearchAreaAndDescendants(String researchAreaCode) {
        ResearchArea researchArea = getBusinessObjectService().findBySinglePrimaryKey(ResearchArea.class, researchAreaCode);
        if (researchArea != null) {
            getBusinessObjectService().delete(researchArea);
            deleteChildrenResearchAreas(researchAreaCode);
            updateHasChildrenFlag(researchArea.getParentResearchAreaCode());
        }
    }
    
    /**
     * This method recursively deletes all child research areas.
     * @param parentResearchAreaCode
     */
    @SuppressWarnings("unchecked")
    private void deleteChildrenResearchAreas(String parentResearchAreaCode) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(PARENT_RESEARCH_AREA_CODE, parentResearchAreaCode);
        List<ResearchArea> researchAreas = (List<ResearchArea>) businessObjectService.findMatching(ResearchArea.class, fieldValues);
        for (ResearchArea researchArea: researchAreas) {
            deleteChildrenResearchAreas(researchArea.getResearchAreaCode());
            businessObjectService.delete(researchArea);
        }
    }
    
    /**
     * This method determines if the hasChildrenFlag needs to be set or cleared.
     * @param researchAreaCode
     */
    private void updateHasChildrenFlag(String researchAreaCode) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(PARENT_RESEARCH_AREA_CODE, researchAreaCode);
        if (businessObjectService.countMatching(ResearchArea.class, fieldValues) > 0) {
            setHasChildrenFlag(researchAreaCode, true);
        } else {
            setHasChildrenFlag(researchAreaCode, false);
        }
    }
    
    /**
     * This method sets the hasChildrenFlag to a specific value.
     * @param researchAreaCode
     * @param hasChildrenFlag
     */
    private void setHasChildrenFlag(String researchAreaCode, boolean hasChildrenFlag) {
        ResearchArea researchArea = businessObjectService.findBySinglePrimaryKey(ResearchArea.class, researchAreaCode);
        if (researchArea.getHasChildrenFlag() != hasChildrenFlag) {
            researchArea.setHasChildrenFlag(hasChildrenFlag);
            businessObjectService.save(researchArea);
        }
    }
    
    
    

    /*
     * This method is a recursive call to check whether the new 'researchAreaCode' matched
     * raCode's Descendants' code
     */
    protected boolean isExistInDeletedChildren(String researchAreaCode, String raCode) {
        boolean isExist = false;
        for (ResearchArea researchArea : getSubResearchAreas(raCode, false)) {
            if (researchAreaCode.equals(researchArea.getResearchAreaCode())) {
                isExist = true;
                break;
            } else {
                isExist = isExistInDeletedChildren(researchAreaCode, researchArea.getResearchAreaCode());
            }
        }
        return isExist;
    }
    
    /**
     * The format is as follows:
     * <pre>
     * {@code
     * <RaChanges>
     *   <RaChangesElement>
     *       <RaCreate>
     *           <Code></Code>
     *           <ParentCode></ParentCode>
     *           <Description></Description>
     *           <Active></Active>
     *       </RaCreate>
     *       <RaUpdateDescription>
     *           <Code></Code>
     *           <Description></Description>
     *       </RaUpdateDescription>
     *       <RaUpdateActiveIndicator>
     *           <Code></Code>
     *           <Active></Active>
     *       </RaUpdateActiveIndicator>
     *       <RaUpdateParent>
     *           <Code></Code>
     *           <OldParent></OldParent>
     *           <NewParent></NewParent>
     *       </RaUpdateParent>
     *       <RaDelete>
     *           <Code></Code>
     *       </RaDelete>
     *   </RaChangesElement>
     * </RaChanges>
     * }
     * </pre>
     * @throws SAXException 
     * @throws IOException 
     * @throws ParserConfigurationException 
     * 
     * @see org.kuali.kra.service.ResearchAreasService#saveResearchAreas(java.lang.String)
     */
    public void saveResearchAreas(String raChangeXML) throws ParserConfigurationException, IOException, SAXException {
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(raChangeXML));
        Document raChangesDocument;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        raChangesDocument = db.parse(is);
        List<Element> raChanges = getRaChanges(raChangesDocument);
        for (Element raChange : raChanges) {
            Map<String, Map<String, String>> details = getRaChangeDetails(raChange);
            if (details.containsKey(CREATE_RESEARCH_AREA)) {
                boolean active = StringUtils.equalsIgnoreCase(details.get(CREATE_RESEARCH_AREA).get(ACTIVE), TRUE) ? true : false;
                ResearchArea researchArea = new ResearchArea(details.get(CREATE_RESEARCH_AREA).get(CODE), details.get(CREATE_RESEARCH_AREA)
                        .get(PARENT_CODE), details.get(CREATE_RESEARCH_AREA).get(DESCRIPTION), active);
                businessObjectService.save(researchArea);
                setHasChildrenFlag(details.get(CREATE_RESEARCH_AREA).get(PARENT_CODE), true);

            }
            if (details.containsKey(UPDATE_RESEARCH_AREA_DESCRIPTION)) {
                ResearchArea researchArea = businessObjectService.findBySinglePrimaryKey(ResearchArea.class, details.get(UPDATE_RESEARCH_AREA_DESCRIPTION).get(CODE));
                researchArea.setDescription(details.get(UPDATE_RESEARCH_AREA_DESCRIPTION).get(DESCRIPTION));
                businessObjectService.save(researchArea);
            }
            if (details.containsKey(UPDATE_RESEARCH_AREA_ACTIVE_FLAG)) {
                // with kcirb-1424's changes, the 'update' will now always be an activation (deactivations are carried out seperately).
                // boolean active = StringUtils.equalsIgnoreCase(details.get(UPDATE_RESEARCH_AREA_ACTIVE_FLAG).get(ACTIVE), TRUE) ? true : false;
                ResearchArea researchArea = businessObjectService.findBySinglePrimaryKey(ResearchArea.class, details.get(UPDATE_RESEARCH_AREA_ACTIVE_FLAG).get(CODE));
                researchArea.setActive(true);
                businessObjectService.save(researchArea);
                // inactivateChildrenResearchAreas(details.get(UPDATE_RESEARCH_AREA_ACTIVE_FLAG).get(CODE));
            }
            if (details.containsKey(UPDATE_PARENT_RESEARCH_AREA)) {
                ResearchArea researchArea = businessObjectService.findBySinglePrimaryKey(ResearchArea.class, details.get(UPDATE_PARENT_RESEARCH_AREA).get(CODE));
                researchArea.setParentResearchAreaCode(details.get(UPDATE_PARENT_RESEARCH_AREA).get(NEW_PARENT));
                businessObjectService.save(researchArea);
                setHasChildrenFlag(details.get(UPDATE_PARENT_RESEARCH_AREA).get(NEW_PARENT), true);
                updateHasChildrenFlag(details.get(UPDATE_PARENT_RESEARCH_AREA).get("OldParent"));
            }
        }
    }
    
    
    
    
    List<Element> getRaChanges(Document doc) {
        List<Element> elements = new ArrayList<Element>();
        NodeList nodes = doc.getElementsByTagName("RaChangesElement");
        for (int i=0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE){
                elements.add((Element) nodes.item(i));
            }
        }
        return elements;
    }
    
    /**
     * This method populates the research area change details.
     * The map contains the change type as the key and a map of the detail attributes (see raChangeAttribute()).
     * e.g. [RaUpdateActiveIndicator", ["Code", "01."], ["Active", "false"]]
     * @param raChange
     * @return map with the research area details.
     */
    Map<String, Map<String, String>> getRaChangeDetails(Element raChange) {
        Map<String, Map<String, String>> raChangeDetails = new HashMap<String, Map<String, String>>();
        NodeList raChangeDetail = raChange.getChildNodes();
        for (int i = 0; i < raChangeDetail.getLength(); i++) {
            if ((raChangeDetail.item(i).getNodeType() == Node.ELEMENT_NODE)) {
                raChangeDetails.put(raChangeDetail.item(i).getNodeName(), raChangeAttribute(raChangeDetail.item(i)));
            }
        }
        return raChangeDetails;
    }
    
    /**
     * This method gets the detail attributes for a specific change.
     * The map contains the attribute name as the key and attribute value as the value.
     * e.g. ["code", "01.123"], ["ParentCode", "01."], ["Description","This is a sample"],["Active", "true"]  
     * @param raChangeDetail
     * @return map of the research area change attribute's names and values;
     */
    private Map<String, String> raChangeAttribute(Node raChangeDetail) {
        Map<String, String> raChangeAttributes = new HashMap<String, String>();
        NodeList attributes = raChangeDetail.getChildNodes();
        for (int i = 0; i < attributes.getLength(); i++) {
            if (attributes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                raChangeAttributes.put(attributes.item(i).getNodeName(), attributes.item(i).getFirstChild().getNodeValue());
            }
        }
        return raChangeAttributes;
    }

    

    /**
     * @see org.kuali.kra.service.ResearchAreasService#getCurrentProtocolReferencingResearchArea(java.lang.String)
     */
    public Protocol getCurrentProtocolReferencingResearchArea(String researchAreaCode) {
        Protocol retValue = null;
        // get the collection of all ProtocolResearchArea instances that have the given research area code
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(RESEARCH_AREA_CODE, researchAreaCode);
        @SuppressWarnings("unchecked")
        List<ProtocolResearchArea> pras = (List<ProtocolResearchArea>) this.getBusinessObjectService().findMatching(ProtocolResearchArea.class, fieldValues);
        // loop through the collection checking the parent protocol of each instance for 'currentness'
        for(ProtocolResearchArea pra:pras) {
            // get the parent protocol instance via getter (it is populated because of auto-retrieve in repository)
            Protocol parentProtocol = pra.getProtocol();
            //check if protocol is active
            if( (null != parentProtocol) && parentProtocol.isActive()) {
                retValue = parentProtocol;
                break;
            }
        }
        return retValue;
    }
    
    // helper method that checks that the given committee instance has the highest sequence number of all other 
    // committee instances in the database with the same committee id.
    // note: See replacement for this method in ResearchAreaReferencesDaoOjb if efficiency becomes a concern
    private boolean isCurrentVersion(Committee committee) {
        boolean retValue = false;
        // get the list of all Committee instances that have the same id as the argument instance, 
        // sorted in descending order of their sequence numbers
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(COMMITTEE_ID, committee.getCommitteeId());
        @SuppressWarnings("unchecked")
        List<Committee> committees = (List<Committee>) this.getBusinessObjectService().findMatchingOrderBy(Committee.class, fieldValues, SEQUENCE_NUMBER, false);
        // check the first element's sequence number with the argument's sequence number
        if( (committees != null) && (!committees.isEmpty()) && (committees.get(0).getSequenceNumber().equals(committee.getSequenceNumber())) ) {
            retValue = true;
        }  
        return retValue;
    }

    /**
     * @see org.kuali.kra.service.ResearchAreasService#getCurrentCommitteeReferencingResearchArea(java.lang.String)
     */
    public Committee getCurrentCommitteeReferencingResearchArea(String researchAreaCode) {
        Committee retValue = null;
        // get the collection of all CommitteeResearchArea instances that have the given research area code
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(RESEARCH_AREA_CODE, researchAreaCode);
        @SuppressWarnings("unchecked")
        List<CommitteeResearchArea> cras = (List<CommitteeResearchArea>) this.getBusinessObjectService().findMatching(CommitteeResearchArea.class, fieldValues);
        // loop through the collection checking the parent committee of each instance for currentness
        for(CommitteeResearchArea cra:cras) {
            // get the parent committee using the FK (auto-retrieve is false in the repository)
            Committee parentCommittee = this.getBusinessObjectService().findBySinglePrimaryKey(Committee.class, cra.getCommitteeIdFk());
            // check if the committee is the current version
            if( (null != parentCommittee) && this.isCurrentVersion(parentCommittee) ) {
                retValue = parentCommittee;
                break;
            }
        }
        return retValue;
    }

    /**
     * @see org.kuali.kra.service.ResearchAreasService#getCurrentCommitteeMembershipReferencingResearchArea(java.lang.String)
     */
    public CommitteeMembership getCurrentCommitteeMembershipReferencingResearchArea(String researchAreaCode) {
        CommitteeMembership retValue = null;
        // get the collection of all CommitteeMembershipExpertise instances that have the given research area code
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(RESEARCH_AREA_CODE, researchAreaCode);
        @SuppressWarnings("unchecked")
        List<CommitteeMembershipExpertise> cmes = (List<CommitteeMembershipExpertise>) this.getBusinessObjectService().findMatching(CommitteeMembershipExpertise.class, fieldValues);
        // loop through the collection checking the parent committee of each instance for currentness
        for(CommitteeMembershipExpertise cme:cmes) {
            // first get the parent committee membership using the FK
            CommitteeMembership parentCommitteeMembership = this.getBusinessObjectService().findBySinglePrimaryKey(CommitteeMembership.class, cme.getCommitteeMembershipIdFk());
            // check if the parent committee membership's term is still open
            if(null != parentCommitteeMembership && (!parentCommitteeMembership.hasTermEnded()) ) {
                // then get the parent committee using the FK
                Committee parentCommittee = this.getBusinessObjectService().findBySinglePrimaryKey(Committee.class, parentCommitteeMembership.getCommitteeIdFk());
                // check if the committee is the current version
                if( (null != parentCommittee) && this.isCurrentVersion(parentCommittee) ) {
                    retValue = parentCommitteeMembership;
                    break;
                }
            }
        }
        return retValue;
    }

    
    /**
     * This method recursively inactivates all child research areas.
     * @param parentResearchAreaCode
     */
    @SuppressWarnings("unchecked")
    private void inactivateChildrenResearchAreas(String parentResearchAreaCode) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(PARENT_RESEARCH_AREA_CODE, parentResearchAreaCode);
        List<ResearchArea> researchAreas = (List<ResearchArea>) businessObjectService.findMatching(ResearchArea.class, fieldValues);
        for (ResearchArea researchArea: researchAreas) {
            inactivateChildrenResearchAreas(researchArea.getResearchAreaCode());
            researchArea.setActive(false);
            businessObjectService.save(researchArea);
        }
    }
    
    /**
     * @see org.kuali.kra.service.ResearchAreasService#deactivateResearchAreaAndDescendants(java.lang.String)
     */
    public void deactivateResearchAreaAndDescendants(String researchAreaCode) throws Exception {
        ResearchArea researchArea = businessObjectService.findBySinglePrimaryKey(ResearchArea.class, researchAreaCode);
        if (researchArea != null) {
            researchArea.setActive(false);
            businessObjectService.save(researchArea);
            inactivateChildrenResearchAreas(researchAreaCode);
        }
    }

    /**
     * @see org.kuali.kra.service.ResearchAreasService#getAnyCurrentReferencerForResearchAreaOrDescendant(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public ResearchAreaCurrentReferencerHolder getAnyCurrentReferencerForResearchAreaOrDescendant(String researchAreaCode) {
        ResearchAreaCurrentReferencerHolder retValue = ResearchAreaCurrentReferencerHolder.NO_REFERENCER;
        Protocol referencingProtocol = this.getCurrentProtocolReferencingResearchArea(researchAreaCode);
        if(null != referencingProtocol) {
            retValue = new ResearchAreaCurrentReferencerHolder(researchAreaCode, referencingProtocol, null, null);
        }
        else {
            Committee referencingCommittee = this.getCurrentCommitteeReferencingResearchArea(researchAreaCode);
            if(null != referencingCommittee) {
                retValue = new ResearchAreaCurrentReferencerHolder(researchAreaCode, null, referencingCommittee, null);
            }
            else {
                CommitteeMembership referencingCommitteeMembership = this.getCurrentCommitteeMembershipReferencingResearchArea(researchAreaCode);
                if(null != referencingCommitteeMembership) {
                    Committee parentCommittee = this.getBusinessObjectService().findBySinglePrimaryKey(Committee.class, referencingCommitteeMembership.getCommitteeIdFk());
                    retValue = new ResearchAreaCurrentReferencerHolder(researchAreaCode, null, parentCommittee, referencingCommitteeMembership);
                }              
                else {
                    // if we came here, then this RA is not referenced, so check its active descendants recursively
                    Map<String, Object> fieldValues = new HashMap<String, Object>();
                    fieldValues.put(PARENT_RESEARCH_AREA_CODE, researchAreaCode);
                    fieldValues.put("active", true);
                    List<ResearchArea> subResearchAreas = (List<ResearchArea>) getBusinessObjectService().findMatching(ResearchArea.class, fieldValues);
                    
                    for (ResearchArea subResearchArea: subResearchAreas) {
                        // recursive call
                        retValue = getAnyCurrentReferencerForResearchAreaOrDescendant(subResearchArea.getResearchAreaCode());
                        if(retValue != ResearchAreaCurrentReferencerHolder.NO_REFERENCER) {
                            // no need to check remaining children
                            break;
                        }
                    } 
                }
            }
        }
        return retValue;   
    }
    
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }


    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public ResearchAreaReferencesDao getResearchAreaReferencesDao() {
        if(null == this.researchAreaReferencesDao) {
            this.setResearchAreaReferencesDao(KraServiceLocator.getService(ResearchAreaReferencesDao.class));
        }
        return researchAreaReferencesDao;
    }

    public void setResearchAreaReferencesDao(ResearchAreaReferencesDao researchAreaReferencesDao) {
        this.researchAreaReferencesDao = researchAreaReferencesDao;
    }

}

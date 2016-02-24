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
package org.kuali.kra.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipExpertiseBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeResearchAreaBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.dao.ResearchAreaReferencesDao;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaBase;
import org.kuali.kra.service.ResearchAreaCurrentReferencerHolderBase;
import org.kuali.kra.service.ResearchAreasServiceBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.KRADConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class ResearchAreasServiceBaseImpl implements ResearchAreasServiceBase {

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


    @Override
    public String getSubResearchAreasForTreeView(String researchAreaCode, boolean activeOnly) {
        String researchAreas = "<h3>";
        for (ResearchAreaBase researchArea : getSubResearchAreas(researchAreaCode, activeOnly)) {
            researchAreas = researchAreas + researchArea.getResearchAreaCode() +KRADConstants.BLANK_SPACE+COLUMN_CODE_1+KRADConstants.BLANK_SPACE+ researchArea.getDescription() +KRADConstants.BLANK_SPACE+COLUMN_CODE_2+KRADConstants.BLANK_SPACE+ researchArea.isActive()+"</h3><h3>";
        }
        researchAreas = researchAreas.substring(0, researchAreas.length() - 4);
        return researchAreas;

    }

    /*
     * call businessobjectservice to get a list of sub research areas of 'researchareacode'
     */
    @SuppressWarnings("unchecked")
    protected List<ResearchAreaBase> getSubResearchAreas(String researchAreaCode, boolean activeOnly) {
        List<ResearchAreaBase> researchAreasList = new ArrayList<ResearchAreaBase>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(PARENT_RESEARCH_AREA_CODE, researchAreaCode);
        if (activeOnly) {
            fieldValues.put("active", activeOnly);
        }
        researchAreasList.addAll(businessObjectService.findMatchingOrderBy(getResearchAreaBOClassHook(), fieldValues, RESEARCH_AREA_CODE, true));
        return researchAreasList;
    }

    @Override
    public boolean isResearchAreaExist(String researchAreaCode, String researchAreas) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(RESEARCH_AREA_CODE, researchAreaCode);
        boolean isExist = businessObjectService.findByPrimaryKey(getResearchAreaBOClassHook(), fieldValues) != null;
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
     * @see org.kuali.kra.irb.service.ResearchAreasService#checkResearchAreaAndDescendantsNotReferenced(java.lang.String)
     * TODO: optimize by checking only active descendants? Could use the getSubResearchAreas method above
     */  
    public boolean checkResearchAreaAndDescendantsNotReferenced(String researchAreaCode) {
        boolean retValue = true;
        ResearchAreaBase researchArea = getBusinessObjectService().findBySinglePrimaryKey(getResearchAreaBOClassHook(), researchAreaCode);
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
    private boolean checkResearchAreaTree(ResearchAreaBase researchArea) {
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
            List<ResearchAreaBase> subResearchAreas = (List<ResearchAreaBase>) getBusinessObjectService().findMatching(getResearchAreaBOClassHook(), fieldValues);
            for (ResearchAreaBase subResearchArea: subResearchAreas) {
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
    
    
    
    
    @Override
    public void deleteResearchAreaAndDescendants(String researchAreaCode) {
        ResearchAreaBase researchArea = getBusinessObjectService().findBySinglePrimaryKey(getResearchAreaBOClassHook(), researchAreaCode);
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
        List<ResearchAreaBase> researchAreas = (List<ResearchAreaBase>) businessObjectService.findMatching(getResearchAreaBOClassHook(), fieldValues);
        for (ResearchAreaBase researchArea: researchAreas) {
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
        if (businessObjectService.countMatching(getResearchAreaBOClassHook(), fieldValues) > 0) {
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
        ResearchAreaBase researchArea = businessObjectService.findBySinglePrimaryKey(getResearchAreaBOClassHook(), researchAreaCode);
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
        for (ResearchAreaBase researchArea : getSubResearchAreas(raCode, false)) {
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
     * @see org.kuali.kra.irb.service.ResearchAreasService#saveResearchAreas(java.lang.String)
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

                /*
                ResearchArea researchArea = new ResearchArea(details.get(CREATE_RESEARCH_AREA).get(CODE), details.get(CREATE_RESEARCH_AREA)
                        .get(PARENT_CODE), details.get(CREATE_RESEARCH_AREA).get(DESCRIPTION), active);
                        */

                ResearchAreaBase researchArea = getNewResearchAreaInstanceHook(details.get(CREATE_RESEARCH_AREA).get(CODE), details.get(CREATE_RESEARCH_AREA)
                        .get(PARENT_CODE), details.get(CREATE_RESEARCH_AREA).get(DESCRIPTION), active);
                
                businessObjectService.save(researchArea);
                setHasChildrenFlag(details.get(CREATE_RESEARCH_AREA).get(PARENT_CODE), true);

            }
            if (details.containsKey(UPDATE_RESEARCH_AREA_DESCRIPTION)) {
                ResearchAreaBase researchArea = businessObjectService.findBySinglePrimaryKey(getResearchAreaBOClassHook(), details.get(UPDATE_RESEARCH_AREA_DESCRIPTION).get(CODE));
                researchArea.setDescription(details.get(UPDATE_RESEARCH_AREA_DESCRIPTION).get(DESCRIPTION));
                businessObjectService.save(researchArea);
            }
            if (details.containsKey(UPDATE_RESEARCH_AREA_ACTIVE_FLAG)) {
                // with kcirb-1424's changes, the 'update' will now always be an activation (deactivations are carried out seperately).
                // boolean active = StringUtils.equalsIgnoreCase(details.get(UPDATE_RESEARCH_AREA_ACTIVE_FLAG).get(ACTIVE), TRUE) ? true : false;
                ResearchAreaBase researchArea = businessObjectService.findBySinglePrimaryKey(getResearchAreaBOClassHook(), details.get(UPDATE_RESEARCH_AREA_ACTIVE_FLAG).get(CODE));
                researchArea.setActive(true);
                businessObjectService.save(researchArea);
                // inactivateChildrenResearchAreas(details.get(UPDATE_RESEARCH_AREA_ACTIVE_FLAG).get(CODE));
            }
            if (details.containsKey(UPDATE_PARENT_RESEARCH_AREA)) {
                ResearchAreaBase researchArea = businessObjectService.findBySinglePrimaryKey(getResearchAreaBOClassHook(), details.get(UPDATE_PARENT_RESEARCH_AREA).get(CODE));
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

    

    @Override
    public ProtocolBase getCurrentProtocolReferencingResearchArea(String researchAreaCode) {
        ProtocolBase retValue = null;
        // get the collection of all ProtocolResearchAreaBase instances that have the given research area code
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(RESEARCH_AREA_CODE, researchAreaCode);
        @SuppressWarnings("unchecked")
        List<ProtocolResearchAreaBase> pras = (List<ProtocolResearchAreaBase>) this.getBusinessObjectService().findMatching(getProtocolResearchAreaBOClassHook(), fieldValues);
        // loop through the collection checking the parent protocol of each instance for 'currentness'
        for(ProtocolResearchAreaBase pra : pras) {
            // get the parent protocol instance via getter (it is populated because of auto-retrieve in repository)
            ProtocolBase parentProtocol = pra.getProtocol();
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
    private boolean isCurrentVersion(CommitteeBase committee) {
        boolean retValue = false;
        // get the list of all CommitteeBase instances that have the same id as the argument instance, 
        // sorted in descending order of their sequence numbers
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(COMMITTEE_ID, committee.getCommitteeId());
        @SuppressWarnings("unchecked")
        List<CommitteeBase> committees = (List<CommitteeBase>) this.getBusinessObjectService().findMatchingOrderBy(getCommitteeBOClassHook(), fieldValues, SEQUENCE_NUMBER, false);
        // check the first element's sequence number with the argument's sequence number
        if( (committees != null) && (!committees.isEmpty()) && (committees.get(0).getSequenceNumber().equals(committee.getSequenceNumber())) ) {
            retValue = true;
        }  
        return retValue;
    }

    @Override
    public CommitteeBase getCurrentCommitteeReferencingResearchArea(String researchAreaCode) {
        CommitteeBase retValue = null;
        // get the collection of all CommitteeResearchAreaBase instances that have the given research area code
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(RESEARCH_AREA_CODE, researchAreaCode);
        @SuppressWarnings("unchecked")
        List<CommitteeResearchAreaBase> cras = (List<CommitteeResearchAreaBase>) this.getBusinessObjectService().findMatching(getCommitteeResearchAreaBOClassHook(), fieldValues);
        // loop through the collection checking the parent committee of each instance for currentness
        for(CommitteeResearchAreaBase cra:cras) {
            // get the parent committee using the FK (auto-retrieve is false in the repository)
            CommitteeBase parentCommittee = this.getBusinessObjectService().findBySinglePrimaryKey(getCommitteeBOClassHook(), cra.getCommitteeIdFk());
            // check if the committee is the current version
            if( (null != parentCommittee) && this.isCurrentVersion(parentCommittee) ) {
                retValue = parentCommittee;
                break;
            }
        }
        return retValue;
    }

    

    @Override
    public CommitteeMembershipBase getCurrentCommitteeMembershipReferencingResearchArea(String researchAreaCode) {
        CommitteeMembershipBase retValue = null;
        // get the collection of all CommitteeMembershipExpertise instances that have the given research area code
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(RESEARCH_AREA_CODE, researchAreaCode);
        @SuppressWarnings("unchecked")
        List<CommitteeMembershipExpertiseBase> cmes = (List<CommitteeMembershipExpertiseBase>) this.getBusinessObjectService().findMatching(getCommitteeMembershipExpertiseClassHook(), fieldValues);
        // loop through the collection checking the parent committee of each instance for currentness
        for(CommitteeMembershipExpertiseBase cme:cmes) {
            // first get the parent committee membership using the FK
            CommitteeMembershipBase parentCommitteeMembership = this.getBusinessObjectService().findBySinglePrimaryKey(getCommitteeMembershipBOClassHook(), cme.getCommitteeMembershipIdFk());
            // check if the parent committee membership's term is still open
            if(null != parentCommitteeMembership && (!parentCommitteeMembership.hasTermEnded()) ) {
                // then get the parent committee using the FK
                CommitteeBase parentCommittee = this.getBusinessObjectService().findBySinglePrimaryKey(getCommitteeBOClassHook(), parentCommitteeMembership.getCommitteeIdFk());
                // check if the committee is the current version
                if( (null != parentCommittee) && this.isCurrentVersion(parentCommittee) ) {
                    retValue = parentCommitteeMembership;
                    break;
                }
            }
        }
        return retValue;
    }

    protected abstract Class<? extends CommitteeMembershipBase> getCommitteeMembershipBOClassHook();

    protected abstract Class<? extends CommitteeMembershipExpertiseBase> getCommitteeMembershipExpertiseClassHook();    
    
    /**
     * This method recursively inactivates all child research areas.
     * @param parentResearchAreaCode
     */
    @SuppressWarnings("unchecked")
    private void inactivateChildrenResearchAreas(String parentResearchAreaCode) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(PARENT_RESEARCH_AREA_CODE, parentResearchAreaCode);
        List<ResearchAreaBase> researchAreas = (List<ResearchAreaBase>) businessObjectService.findMatching(getResearchAreaBOClassHook(), fieldValues);
        for (ResearchAreaBase researchArea: researchAreas) {
            inactivateChildrenResearchAreas(researchArea.getResearchAreaCode());
            researchArea.setActive(false);
            businessObjectService.save(researchArea);
        }
    }
    
    @Override
    public void deactivateResearchAreaAndDescendants(String researchAreaCode) throws Exception {
        ResearchAreaBase researchArea = businessObjectService.findBySinglePrimaryKey(getResearchAreaBOClassHook(), researchAreaCode);
        if (researchArea != null) {
            researchArea.setActive(false);
            businessObjectService.save(researchArea);
            inactivateChildrenResearchAreas(researchAreaCode);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResearchAreaCurrentReferencerHolderBase getAnyCurrentReferencerForResearchAreaOrDescendant(String researchAreaCode) {
        ResearchAreaCurrentReferencerHolderBase retValue = ResearchAreaCurrentReferencerHolderBase.NO_REFERENCER;
        ProtocolBase referencingProtocol = this.getCurrentProtocolReferencingResearchArea(researchAreaCode);
        if(null != referencingProtocol) {
            retValue = new ResearchAreaCurrentReferencerHolderBase(researchAreaCode, referencingProtocol, null, null);
        }
        else {
            CommitteeBase referencingCommittee = this.getCurrentCommitteeReferencingResearchArea(researchAreaCode);
            if(null != referencingCommittee) {
                retValue = new ResearchAreaCurrentReferencerHolderBase(researchAreaCode, null, referencingCommittee, null);
            }
            else {
                CommitteeMembershipBase referencingCommitteeMembership = this.getCurrentCommitteeMembershipReferencingResearchArea(researchAreaCode);
                if(null != referencingCommitteeMembership) {
                    CommitteeBase parentCommittee = this.getBusinessObjectService().findBySinglePrimaryKey(getCommitteeBOClassHook(), referencingCommitteeMembership.getCommitteeIdFk());
                    retValue = new ResearchAreaCurrentReferencerHolderBase(researchAreaCode, null, parentCommittee, referencingCommitteeMembership);
                }              
                else {
                    // if we came here, then this RA is not referenced, so check its active descendants recursively
                    Map<String, Object> fieldValues = new HashMap<String, Object>();
                    fieldValues.put(PARENT_RESEARCH_AREA_CODE, researchAreaCode);
                    fieldValues.put("active", true);
                    List<ResearchAreaBase> subResearchAreas = (List<ResearchAreaBase>) getBusinessObjectService().findMatching(getResearchAreaBOClassHook(), fieldValues);
                    
                    for (ResearchAreaBase subResearchArea: subResearchAreas) {
                        // recursive call
                        retValue = getAnyCurrentReferencerForResearchAreaOrDescendant(subResearchArea.getResearchAreaCode());
                        if(retValue != ResearchAreaCurrentReferencerHolderBase.NO_REFERENCER) {
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
            this.setResearchAreaReferencesDao(KcServiceLocator.getService(ResearchAreaReferencesDao.class));
        }
        return researchAreaReferencesDao;
    }

    public void setResearchAreaReferencesDao(ResearchAreaReferencesDao researchAreaReferencesDao) {
        this.researchAreaReferencesDao = researchAreaReferencesDao;
    }

    protected abstract Class<? extends CommitteeBase> getCommitteeBOClassHook();
    protected abstract Class<? extends CommitteeResearchAreaBase> getCommitteeResearchAreaBOClassHook();
    protected abstract Class<? extends ProtocolResearchAreaBase> getProtocolResearchAreaBOClassHook();
    protected abstract Class<? extends ResearchAreaBase> getResearchAreaBOClassHook();
    protected abstract ResearchAreaBase getNewResearchAreaInstanceHook(String researchAreaCode, String parentResearchAreaCode, String description, boolean active);

}

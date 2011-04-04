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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.service.ResearchAreasService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KNSConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ResearchAreasServiceImpl implements ResearchAreasService {

    private static final String COLUMN_CODE_1 = "%3A";
    private static final String COLUMN_CODE_2 = "%4A";
    private static final String DELETE_RESEARCH_AREA = "RaDelete";
    private static final String CREATE_RESEARCH_AREA = "RaCreate";
    private static final String UPDATE_PARENT_RESEARCH_AREA = "RaUpdateParent";
    private static final String UPDATE_RESEARCH_AREA_ACTIVE_FLAG = "RaUpdateActiveIndicator";
    private static final String UPDATE_RESEARCH_AREA_DESCRIPTION = "RaUpdateDescription";
    private static final String RESEARCH_AREA_CODE = "researchAreaCode";
    private static final String CODE = "Code";
    private static final String TRUE = "true";
    private static final String ACTIVE = "Active";
    private static final String DESCRIPTION = "Description";
    private static final String PARENT_CODE = "ParentCode";
    private static final String PARENT_RESEARCH_AREA_CODE = "parentResearchAreaCode";
    private static final String NEW_PARENT = "NewParent";
    private BusinessObjectService businessObjectService;
    
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
            // delete before create to allow a research area code to be deleted and reused for a new entry.
            if (details.containsKey(DELETE_RESEARCH_AREA)) {
                ResearchArea researchArea = businessObjectService.findBySinglePrimaryKey(ResearchArea.class, details.get(DELETE_RESEARCH_AREA).get(CODE));
                if (researchArea != null) {
                    businessObjectService.delete(researchArea);
                    deleteChildrenResearchAreas(details.get(DELETE_RESEARCH_AREA).get(CODE));
                    updateHasChildrenFlag(researchArea.getParentResearchAreaCode());
                }
            }
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
                boolean active = StringUtils.equalsIgnoreCase(details.get(UPDATE_RESEARCH_AREA_ACTIVE_FLAG).get(ACTIVE), TRUE) ? true : false;
                ResearchArea researchArea = businessObjectService.findBySinglePrimaryKey(ResearchArea.class, details.get(UPDATE_RESEARCH_AREA_ACTIVE_FLAG).get(CODE));
                researchArea.setActive(active);
                businessObjectService.save(researchArea);
                inactivateChildrenResearchAreas(details.get(UPDATE_RESEARCH_AREA_ACTIVE_FLAG).get(CODE));
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

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }


    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}

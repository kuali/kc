/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.s2s.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.kuali.kra.bo.Parameter;
import org.kuali.kra.bo.SponsorFormTemplate;
import org.kuali.kra.bo.SponsorFormTemplateList;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.s2s.service.PrintService;
import org.kuali.rice.kns.service.BusinessObjectService;

import edu.mit.coeus.exception.CoeusException;
import edu.mit.coeus.propdev.ProposalPrintReader;
import edu.mit.coeus.sponsormaint.bean.SponsorTemplateBean;

/**
 * This class...
 */
public class PrintServiceImpl implements PrintService {

    private BusinessObjectService businessObjectService;
    /**
     * @see org.kuali.kra.s2s.service.PrintService#printProposalSponsorForms(java.lang.String, java.util.List)
     */
    public byte[] printProposalSponsorForms(String proposalNumber, List<SponsorFormTemplate> sponsorFormTemplates) {
        List listData = new ArrayList();
        for (SponsorFormTemplate sponsorFormTemplate : sponsorFormTemplates) {
            SponsorTemplateBean coeusSponsorTemplate = new SponsorTemplateBean();
            try {
                BeanUtils.copyProperties(coeusSponsorTemplate,sponsorFormTemplate);
            }catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Hashtable htData = new Hashtable();
            htData.put("PROPOSAL_NUMBER",proposalNumber);
            htData.put("SPONSOR_CODE",sponsorFormTemplate.getSponsorCode());
            htData.put("PACKAGE_NUMBER",sponsorFormTemplate.getPackageNumber());
            htData.put("PAGE_NUMBER",sponsorFormTemplate.getPageNumber());
            htData.put("PAGE_DATA",coeusSponsorTemplate);
            listData.add(htData);
        }
        Map<String,List> map = new HashMap<String,List>();
        map.put("PRINT_PROPOSAL", listData);
        ProposalPrintReader proposalPrintReader = new ProposalPrintReader();
        try {
            return proposalPrintReader.read(map).getDocumentData();
        }catch (CoeusException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public void populateSponsorForms(List<SponsorFormTemplateList> sponsorFormTemplates, String sponsorCode) {
        /* check if sponsor forms isEmpty */
        if(!sponsorFormTemplates.isEmpty()) {
            /* if exists - check if sponsor code has changed */
            if(!sponsorCode.equalsIgnoreCase(sponsorFormTemplates.get(0).getSponsorCode())) {
                sponsorFormTemplates.clear();
            }
        }

        if(sponsorFormTemplates.isEmpty()) {
            /* get templates for proposal sponsor code */
            String genericSponsorCode = null;
            Collection<SponsorFormTemplateList> clsponsorFormTemplates = getSponsorTemplatesList(sponsorCode);
            sponsorFormTemplates.addAll(clsponsorFormTemplates);
            /* get templates for generic sponsor code */
            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("parameter", Constants.GENERIC_SPONSOR_CODE);
            Collection<Parameter> parameters = getBusinessObjectService().findMatching(Parameter.class, parameterMap);
            for(Parameter parameter : parameters) {
                genericSponsorCode = parameter.getValue();
            }
            clsponsorFormTemplates = getSponsorTemplatesList(genericSponsorCode);
            sponsorFormTemplates.addAll(clsponsorFormTemplates);
        }else {
            resetSelectedFormList(sponsorFormTemplates);
        }
        Collections.sort(sponsorFormTemplates);
    }
    
    private void resetSelectedFormList(List<SponsorFormTemplateList> sponsorFormTemplates) {
        for(SponsorFormTemplateList sponsorFormTemplateList : sponsorFormTemplates) {
            sponsorFormTemplateList.setSelectToPrint(false);
        }
    }
    
    public List<SponsorFormTemplate> getSponsorFormTemplates(List<SponsorFormTemplateList> sponsorFormTemplateLists) {
        List<SponsorFormTemplate> sponsorFormTemplates = new ArrayList<SponsorFormTemplate>();
        Set<String> sponsorCodes = new HashSet<String>();
        Set<String> formKeys = new HashSet<String>();
        for(SponsorFormTemplateList sponsorFormTemplateList : sponsorFormTemplateLists) {
            if(sponsorFormTemplateList.getSelectToPrint()) {
                if(!sponsorCodes.contains(sponsorFormTemplateList.getSponsorCode())) {
                    sponsorCodes.add(sponsorFormTemplateList.getSponsorCode());
                }
            }else {
                String fKeys = sponsorFormTemplateList.getSponsorCode() + sponsorFormTemplateList.getPackageNumber().toString() + sponsorFormTemplateList.getPageNumber().toString();
                if(!formKeys.contains(fKeys)) {
                    formKeys.add(fKeys);
                }
            }
        }

        String sponsorCode = null;
        Iterator iter = sponsorCodes.iterator();
        /* get all templates for applicable sponsor codes */
        while(iter.hasNext()) { 
            sponsorCode = iter.next().toString();
            Collection<SponsorFormTemplate> clsponsorFormTemplates = getSponsorTemplates(sponsorCode);
            sponsorFormTemplates.addAll(clsponsorFormTemplates);
            iter.remove();
        }

        /* verify selected package */
        List<SponsorFormTemplate> printFormTemplates = new ArrayList<SponsorFormTemplate>();
        HashMap formTemplates = new HashMap<String, SponsorFormTemplate>();
        for(SponsorFormTemplate sponsorFormTemplate : sponsorFormTemplates) {
            String templateKey = sponsorFormTemplate.getSponsorCode() + sponsorFormTemplate.getPackageNumber().toString() + sponsorFormTemplate.getPageNumber().toString();
            formTemplates.put(templateKey, sponsorFormTemplate);
        }
        Iterator keyIter = formKeys.iterator();
        while(keyIter.hasNext()) {
            String formKey = keyIter.next().toString();
            formTemplates.remove(formKey);
            keyIter.remove();
        }
        printFormTemplates.addAll(formTemplates.values());
        Collections.sort(printFormTemplates);
        resetSelectedFormList(sponsorFormTemplateLists);
        return printFormTemplates;
    }

    private Collection<SponsorFormTemplate> getSponsorTemplates(String sponsorCode) {
        Map<String, String> sponsorCodeMap = new HashMap<String, String>();
        sponsorCodeMap.put("sponsorCode", sponsorCode);
        Collection<SponsorFormTemplate> sponsorFormTemplates = getBusinessObjectService().findMatchingOrderBy(SponsorFormTemplate.class, sponsorCodeMap, "pageNumber", false);
        return sponsorFormTemplates;
    }

    private Collection<SponsorFormTemplateList> getSponsorTemplatesList(String sponsorCode) {
        Map<String, String> sponsorCodeMap = new HashMap<String, String>();
        sponsorCodeMap.put("sponsorCode", sponsorCode);
        Collection<SponsorFormTemplateList> sponsorFormTemplates = getBusinessObjectService().findMatching(SponsorFormTemplateList.class, sponsorCodeMap);
        return sponsorFormTemplates;
    }

    /**
     * Gets the businessObjectService attribute.
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
}

/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.kuali.kra.bo.SponsorFormTemplate;
import org.kuali.kra.s2s.service.PrintService;

import edu.mit.coeus.exception.CoeusException;
import edu.mit.coeus.propdev.ProposalPrintReader;
import edu.mit.coeus.sponsormaint.bean.SponsorTemplateBean;

/**
 * This class...
 */
public class PrintServiceImpl implements PrintService {

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

}

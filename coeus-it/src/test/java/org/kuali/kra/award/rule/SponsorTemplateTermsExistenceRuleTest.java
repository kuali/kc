/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award.rule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.sponsor.term.SponsorTerm;
import org.kuali.coeus.common.framework.sponsor.term.SponsorTermType;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.kra.award.home.AwardTemplateTerm;
import org.kuali.kra.award.rule.SponsorTemplateTermsExistenceRule;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class SponsorTemplateTermsExistenceRuleTest extends KcIntegrationTestBase {
    MaintenanceDocument sponsorTemplateDoc;
    SponsorTemplateTermsExistenceRule rule;
    DocumentService docService;
    AwardTemplate awardTemplate;

    @Before
    public void setUp() throws Exception {
        docService = KcServiceLocator.getService(DocumentService.class);
        sponsorTemplateDoc = (MaintenanceDocumentBase)docService.getNewDocument(MaintenanceDocumentBase.class);
        rule = new SponsorTemplateTermsExistenceRule();
        awardTemplate = new AwardTemplate();
        KualiMaintainableImpl maintainable = new KualiMaintainableImpl(); 
        maintainable.setBusinessObject(awardTemplate);
        sponsorTemplateDoc.setNewMaintainableObject(maintainable);     
    }

    @After
    public void tearDown() throws Exception {
        docService = null;
        sponsorTemplateDoc = null;
        rule = null;
        awardTemplate = null;
    }
    
    @Test
    public void testSponsorTemplateTermsExistenceRule() throws Exception {
        
        assertFalse(rule.processCustomRouteDocumentBusinessRules(sponsorTemplateDoc));
        
        BusinessObjectService businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        Collection<SponsorTermType> types = businessObjectService.findAll(SponsorTermType.class);
        Collection<SponsorTerm> terms;
        Map map;
        String aCode;
        for(SponsorTermType aType : types) {
            aCode = aType.getSponsorTermTypeCode();
            map = Collections.singletonMap("sponsorTermTypeCode", aCode);
            terms = businessObjectService.findMatching(SponsorTerm.class, map);
            
            if(!terms.isEmpty()) {
                SponsorTerm aSponsorTerm = terms.iterator().next();
                Long aSponsorTermId = aSponsorTerm.getSponsorTermId();
                map = Collections.singletonMap("sponsorTermId", aSponsorTermId.toString());
                Collection<AwardTemplateTerm> templateTerms = (Collection<AwardTemplateTerm>)businessObjectService.findMatching(AwardTemplateTerm.class, map);
                if(!templateTerms.isEmpty()) {
                    awardTemplate.getTemplateTerms().add(templateTerms.iterator().next());
                } else {
                    AwardTemplateTerm aTemplateTerm = new AwardTemplateTerm();
                    aTemplateTerm.setSponsorTerm(aSponsorTerm);
                    aTemplateTerm.setSponsorTermId(aSponsorTermId);
                    awardTemplate.getTemplateTerms().add(aTemplateTerm);
                    
                }
            }
        }
        
        assertTrue(rule.processCustomRouteDocumentBusinessRules(sponsorTemplateDoc));
    }
}

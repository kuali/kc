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
package org.kuali.coeus.propdev.impl.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.propdev.impl.attachment.LegacyNarrativeService;
import org.kuali.rice.krad.service.LookupService;
import org.kuali.rice.krad.uif.service.impl.ViewHelperServiceImpl;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("proposalDevelopmentViewHelperService")
@Scope("prototype")
public class ProposalDevelopmentViewHelperServiceImpl extends ViewHelperServiceImpl {

    private static final long serialVersionUID = -5122498699317873886L;

    @Autowired
    @Qualifier("legacyNarrativeService")
    private LegacyNarrativeService narrativeService;
    
    @Autowired
    @Qualifier("lookupService")
    private LookupService lookupService;

    @Override
    public void processBeforeAddLine(ViewModel model, Object addLine, String collectionId, String collectionPath) {
        ProposalDevelopmentDocumentForm form = (ProposalDevelopmentDocumentForm) model;
        ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
        if (addLine instanceof Narrative) {
            Narrative narrative = (Narrative) addLine;
            getNarrativeService().prepareNarrative(document, narrative);
            if (StringUtils.equals(narrative.getNarrativeType().getNarrativeTypeGroup(), Constants.INSTITUTE_NARRATIVE_TYPE_GROUP_CODE)) {
                narrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_COMPLETE);
            }
        } else if (addLine instanceof ProposalPersonBiography) {
            document.getDevelopmentProposal().addProposalPersonBiography((ProposalPersonBiography) addLine);
		} else if (addLine instanceof ProposalPersonUnit) {
			((ProposalPersonUnit)addLine).setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
        } else if (addLine instanceof ProposalAbstract) {
            ProposalAbstract proposalAbstract = (ProposalAbstract) addLine;
            proposalAbstract.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
            proposalAbstract.refreshReferenceObject("abstractType");
        }
    }


    @Override
    public void processAfterSaveLine(ViewModel model, Object lineObject, String collectionId, String collectionPath) {
        if (lineObject instanceof ProposalAbstract) {
            getLegacyDataAdapter().save(lineObject);
        }
    }

    public static class SponsorSuggestResult {
        private Sponsor sponsor;
        public SponsorSuggestResult(Sponsor sponsor) {
            this.sponsor = sponsor;
        }
        public String getValue() {
            return sponsor.getSponsorCode();
        }
        public String getLabel() {
            return "<b>" + sponsor.getSponsorCode() + "</b><br/><i>" + sponsor.getSponsorName() + "</i>";
        }
        public String getSponsorName() {
            return sponsor.getSponsorName();
        }
    }
    
    public List<SponsorSuggestResult> performSponsorFieldSuggest(String sponsorCode) {
        List<SponsorSuggestResult> result = new ArrayList<SponsorSuggestResult>();
        List<Sponsor> allSponsors = new ArrayList<Sponsor>();
        Map<String, String> values = new HashMap<String, String>();
        values.put("sponsorCode", sponsorCode + "*");
        allSponsors.addAll(getLookupService().findCollectionBySearchHelper(Sponsor.class, values, Collections.EMPTY_LIST, false, 10));
        values.clear();
        values.put("acronym", sponsorCode + "*");
        allSponsors.addAll(getLookupService().findCollectionBySearchHelper(Sponsor.class, values, Collections.EMPTY_LIST, false, 10));
        
        for (Sponsor sponsor : allSponsors) {
            result.add(new SponsorSuggestResult(sponsor));
        }
        return result;
    }

    protected LegacyNarrativeService getNarrativeService() {
    	if (narrativeService == null) {
    		narrativeService = KcServiceLocator.getService(LegacyNarrativeService.class);
    	}
        return narrativeService;
    }

    public void setNarrativeService(LegacyNarrativeService narrativeService) {
        this.narrativeService = narrativeService;
    }
    
    protected LookupService getLookupService() {
    	if (lookupService == null) {
    		lookupService = KcServiceLocator.getService(LookupService.class);
    	}
        return lookupService;
    }

    public void setLookupService(LookupService lookupService) {
        this.lookupService = lookupService;
    }

}

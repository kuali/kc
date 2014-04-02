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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.service.NarrativeService;
import org.kuali.kra.proposaldevelopment.web.krad.ProposalDevelopmentDocumentForm;
import org.kuali.rice.krad.service.LegacyDataAdapter;
import org.kuali.rice.krad.service.LookupService;
import org.kuali.rice.krad.uif.container.CollectionGroup;
import org.kuali.rice.krad.uif.service.impl.ViewHelperServiceImpl;
import org.kuali.rice.krad.uif.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProposalDevelopmentViewHelperServiceImpl extends ViewHelperServiceImpl {

    private static final long serialVersionUID = -5122498699317873886L;

    private transient NarrativeService narrativeService;
    private transient LegacyDataAdapter legacyDataAdapter;
    private transient LookupService lookupService;

    public void processBeforeAddLine(View view, CollectionGroup collectionGroup, Object model, Object addLine) {
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

    public NarrativeService getNarrativeService() {
        if (narrativeService == null) {
            narrativeService = KcServiceLocator.getService(NarrativeService.class);
        }
        return narrativeService;
    }

    public void setNarrativeService(NarrativeService narrativeService) {
        this.narrativeService = narrativeService;
    }

    public LegacyDataAdapter getLegacyDataAdapter() {
        if (legacyDataAdapter == null) {
            legacyDataAdapter = KcServiceLocator.getService(LegacyDataAdapter.class);
        }
        return legacyDataAdapter;
    }

    public void setLegacyDataAdapter(LegacyDataAdapter legacyDataAdapter) {
        this.legacyDataAdapter = legacyDataAdapter;
    }

    public LookupService getLookupService() {
        if (lookupService == null) {
            lookupService = KcServiceLocator.getService(LookupService.class);
        }
        return lookupService;
    }

    public void setLookupService(LookupService lookupService) {
        this.lookupService = lookupService;
    }

}

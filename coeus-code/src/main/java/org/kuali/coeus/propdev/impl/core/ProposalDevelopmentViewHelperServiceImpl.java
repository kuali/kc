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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.propdev.impl.person.ProposalPersonDegree;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.coeus.propdev.impl.attachment.LegacyNarrativeService;
import org.kuali.rice.krad.data.DataObjectWrapper;
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
    public void processBeforeAddLine(ViewModel model, Object addLine, String collectionId, final String collectionPath) {
        ProposalDevelopmentDocumentForm form = (ProposalDevelopmentDocumentForm) model;
        ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
        DevelopmentProposal proposal = document.getDevelopmentProposal();
        if (addLine instanceof Narrative) {
            Narrative narrative = (Narrative) addLine;
            getNarrativeService().prepareNarrative(document, narrative);
            if (StringUtils.equals(narrative.getNarrativeType().getNarrativeTypeGroup(), Constants.INSTITUTE_NARRATIVE_TYPE_GROUP_CODE)) {
                narrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_COMPLETE);
            }
        } else if (addLine instanceof ProposalPersonBiography) {
            document.getDevelopmentProposal().addProposalPersonBiography((ProposalPersonBiography) addLine);
		} else if (addLine instanceof ProposalPersonDegree) {
			((ProposalPersonDegree)addLine).setDegreeSequenceNumber(document.getDocumentNextValue(Constants.PROPOSAL_PERSON_DEGREE_SEQUENCE_NUMBER));
        } else if (addLine instanceof ProposalAbstract) {
            ProposalAbstract proposalAbstract = (ProposalAbstract) addLine;
            proposalAbstract.setProposalNumber(proposal.getProposalNumber());
            proposalAbstract.refreshReferenceObject("abstractType");
        } else if (addLine instanceof ProposalSpecialReview) {
        	ProposalSpecialReview proposalSpecialReview = (ProposalSpecialReview) addLine;
        	proposalSpecialReview.setDevelopmentProposal(document.getDevelopmentProposal());
        } else if (addLine instanceof ProposalSite && model.getPageId().equalsIgnoreCase("PropDev-OrganizationLocationsPage")) {
       	 	((ProposalSite) addLine).setLocationTypeCode(ProposalSite.PROPOSAL_SITE_OTHER_ORGANIZATION);        	
        } else if (addLine instanceof CongressionalDistrict && model.getPageId().equalsIgnoreCase("PropDev-OrganizationLocationsPage")) {
       	 	CongressionalDistrict congressionalDistrict =(CongressionalDistrict) addLine;
       	 	((CongressionalDistrict) addLine).setCongressionalDistrict(congressionalDistrict.getNewState(), congressionalDistrict.getNewDistrictNumber());       	 	
        }
    }

    @Override   
    protected boolean performAddLineValidation(ViewModel viewModel, Object newLine, String collectionId,
            String collectionPath) {
      boolean isValid = true;

        Collection <Object> collectionItems = ObjectPropertyUtils.getPropertyValue(viewModel, collectionPath);

        if (viewModel.getViewPostMetadata().getComponentPostData(collectionId,
                UifConstants.PostMetadata.DUPLICATE_LINE_PROPERTY_NAMES) == null) {
            return isValid;
        }

        List<String> duplicateLinePropertyNames = (List<String>) viewModel.getViewPostMetadata().getComponentPostData(
                collectionId, UifConstants.PostMetadata.DUPLICATE_LINE_PROPERTY_NAMES);

        String collectionLabel = null;
        if (viewModel.getViewPostMetadata().getComponentPostData(collectionId, UifConstants.PostMetadata.COLL_LABEL)
                != null) {
            collectionLabel = (String) viewModel.getViewPostMetadata().getComponentPostData(collectionId,
                    UifConstants.PostMetadata.COLL_LABEL);
        }

        String duplicateLineLabelString = null;
        if (viewModel.getViewPostMetadata().getComponentPostData(collectionId,
                UifConstants.PostMetadata.DUPLICATE_LINE_LABEL_STRING) != null) {
            duplicateLineLabelString = (String) viewModel.getViewPostMetadata().getComponentPostData(collectionId,
                    UifConstants.PostMetadata.DUPLICATE_LINE_LABEL_STRING);
        }

        if (containsDuplicateLine(newLine, collectionItems, duplicateLinePropertyNames)) {
            isValid = false;
            GlobalVariables.getMessageMap().putErrorForSectionId(collectionId, RiceKeyConstants.ERROR_DUPLICATE_ELEMENT,
                    collectionLabel, duplicateLineLabelString);
        }
        
        if (newLine instanceof CongressionalDistrict) {        	
        	Collection<CongressionalDistrict> CongressionalDistricts= ObjectPropertyUtils.getPropertyValue(viewModel, collectionPath);
        	CongressionalDistrict newCongressionalDistrict = (CongressionalDistrict) newLine;
        	for(CongressionalDistrict congressionalDistrict: CongressionalDistricts){
        		if (congressionalDistrict.getCongressionalDistrict().equalsIgnoreCase(newCongressionalDistrict.getCongressionalDistrict())) {
        			GlobalVariables.getMessageMap().putErrorForSectionId(collectionId, RiceKeyConstants.ERROR_DUPLICATE_ELEMENT,
                            collectionLabel,newCongressionalDistrict.getCongressionalDistrict() );
            		 isValid = false;
            		 break;
        		} 
        	}
        }
        	
        if (newLine instanceof ProposalSite) {        	
        	Collection<ProposalSite> proposalSiteList= ObjectPropertyUtils.getPropertyValue(viewModel, collectionPath);
        	ProposalSite newProposalSite = (ProposalSite) newLine;            	
        	if (newProposalSite.getLocationTypeCode()==ProposalSite.PROPOSAL_SITE_OTHER_ORGANIZATION) {            		
        		for (ProposalSite proposalSite: proposalSiteList) {
            		if (proposalSite.getLocationTypeCode()==ProposalSite.PROPOSAL_SITE_OTHER_ORGANIZATION && proposalSite.getOrganization()!=null 
            				&& proposalSite.getOrganization().getOrganizationId().equals(newProposalSite.getOrganization().getOrganizationId())) {
            			GlobalVariables.getMessageMap().putErrorForSectionId(collectionId, RiceKeyConstants.ERROR_DUPLICATE_ELEMENT,
                                collectionLabel,newProposalSite.getOrganization().getOrganizationName());
                		 isValid = false;
                		 break;
            		} 
        		}
        	}        		
        }
        return isValid;
    }
    
    private boolean containsDuplicateLine(Object addLine, Collection<Object> collectionItems,
            List<String> duplicateLinePropertyNames) {
        if (collectionItems.isEmpty() || duplicateLinePropertyNames.isEmpty()) {
            return false;
        }

        for (Object collectionItem : collectionItems) {
            if (isDuplicateLine(addLine, collectionItem, duplicateLinePropertyNames)) {
                return true;
            }
        }

        return false;
    }    
   
    private boolean isDuplicateLine(Object addLine, Object collectionItem, List<String> duplicateLinePropertyNames) {
        if (duplicateLinePropertyNames.isEmpty()) {
            return false;
        }

        for (String duplicateLinePropertyName : duplicateLinePropertyNames) {
            Object addLinePropertyValue = ObjectPropertyUtils.getPropertyValue(addLine, duplicateLinePropertyName);
            Object duplicateLinePropertyValue = ObjectPropertyUtils.getPropertyValue(collectionItem,
                    duplicateLinePropertyName);

            if (!ObjectUtils.equals(addLinePropertyValue, duplicateLinePropertyValue)) {
                return false;
            }
        }

        return true;
    }
    
    @Override
    public void processAfterSaveLine(ViewModel model, Object lineObject, String collectionId, String collectionPath) {
           getDataObjectService().save(lineObject);
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

    public boolean isAttachmentEditable(String selectedCollectionPath, String index, HashMap<String,List<String>> editableAttachments) {
        boolean retVal = false;
        if (editableAttachments.containsKey(selectedCollectionPath)) {
            if (editableAttachments.get(selectedCollectionPath).contains(index)) {
                retVal = true;
            }
        }
        return retVal;
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

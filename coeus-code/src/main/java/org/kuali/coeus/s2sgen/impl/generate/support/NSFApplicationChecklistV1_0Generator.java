/*
 * Copyright 2005-2014 The Kuali Foundation.
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
package org.kuali.coeus.s2sgen.impl.generate.support;

import gov.grants.apply.forms.nsfApplicationChecklistV10.NSFApplicationChecklistDocument;
import gov.grants.apply.forms.nsfApplicationChecklistV10.NSFApplicationChecklistDocument.NSFApplicationChecklist;
import gov.grants.apply.forms.nsfApplicationChecklistV10.NSFApplicationChecklistDocument.NSFApplicationChecklist.*;
import gov.grants.apply.system.globalLibraryV10.YesNoDataType;
import gov.grants.apply.system.globalLibraryV10.YesNoNotApplicableDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;


/**
 * 
 * This class is used to generate XML Document object for grants.gov NSFApplicationChecklistV1.0. This form is generated using
 * XMLBean classes and is based on NSF_ApplicationChecklist-V1.0 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("NSFApplicationChecklistV1_0Generator")
public class NSFApplicationChecklistV1_0Generator extends NSFApplicationChecklistBaseGenerator {

    /**
     * 
     * This method returns NSFApplicationChecklistDocument object based on proposal development document which contains the
     * NSFApplicationChecklistDocument information for a particular proposal
     * 
     * @return nsfChecklistDocument {@link XmlObject} of type NSFApplicationChecklistDocument.
     */
    private NSFApplicationChecklistDocument getNSFApplicationChecklist() {
        NSFApplicationChecklistDocument nsfChecklistDocument = NSFApplicationChecklistDocument.Factory.newInstance();
        NSFApplicationChecklist nsfChecklist = NSFApplicationChecklist.Factory.newInstance();
        nsfChecklist.setFormVersion(FormVersion.v1_0.getVersion());
        nsfChecklist.setCoverSheet(getCoverSheet());
        nsfChecklist.setCheckRRSite(YesNoDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_6)));
        nsfChecklist.setCheckRROtherInfo(YesNoDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_7)));
        nsfChecklist.setCheckProjectSummary(YesNoDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_8)));
        nsfChecklist.setProjectNarrative(getProjectNarrative());
        nsfChecklist.setCheckBiblio(YesNoDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_NSF_SMALL_GRANT)));
        nsfChecklist.setCheckFacilities(YesNoDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_OTHER_AGENCY)));
        nsfChecklist.setEquipment(getEquipment());
        nsfChecklist.setRRSrProfile(getRRSrProfile());
        nsfChecklist.setCheckRRPersonalData(YesNoDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_PI_CHANGE)));
        nsfChecklist.setRRBudget(getRRBudget());
        nsfChecklist.setNSFCover(getNSFCover());
        nsfChecklistDocument.setNSFApplicationChecklist(nsfChecklist);
        return nsfChecklistDocument;
    }

    /**
     * 
     * This method gets NSF Cover information such as NSF Deviation authorization, Do not include, NSF FLL, NSF unit.
     * 
     * @return NSFCover object containing nsf cover details.
     */
    private NSFCover getNSFCover() {
        NSFCover nsfCover = NSFCover.Factory.newInstance();
        nsfCover.setCheckNSFCover(YesNoDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_EDUCATION_DEPT)));
        nsfCover.setCheckNSFUnit(YesNoDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_ENVIRINMENT_IMPACT)));
        nsfCover.setCheckNSFOtherInfo(YesNoNotApplicableDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_28)));
        nsfCover.setCheckNSFSFLLL(YesNoNotApplicableDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_29)));
        nsfCover.setCheckNSFDevAuth(YesNoNotApplicableDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_30)));
        nsfCover.setCheckNSFReg(YesNoNotApplicableDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_31)));
        nsfCover.setCheckDoNotInclude(YesNoNotApplicableDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_32)));
        return nsfCover;
    }

    /**
     * 
     * This method returns RRBudget,RRBudgetJustification and Cost sharing information for the RRBudget type.
     * 
     * @return RRBudget object containing RRbudget related information.
     */
    private RRBudget getRRBudget() {
        RRBudget rrBudget = RRBudget.Factory.newInstance();
        rrBudget.setCheckRRBudget(YesNoDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_GRANTEE_INSTITUTION_CHANGE)));
        rrBudget.setCheckRRBudgetJustification(YesNoNotApplicableDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_PI_PARTICIPATION_US)));
        rrBudget.setCheckCostSharing(YesNoNotApplicableDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_PI_PARTICIPATION_FOREIGN)));
        return rrBudget;
    }

    /**
     * 
     * This method returns CoverSheet,Renewal,Full Application,Type of application and Application certification information for the
     * CoverSheet type.
     * 
     * @return CoverSheet object containing cover sheet information details.
     */
    private CoverSheet getCoverSheet() {
        CoverSheet coverSheet = CoverSheet.Factory.newInstance();
        coverSheet.setCheckCoverSheet(YesNoDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_1)));
        coverSheet.setCheckRenewal(YesNoNotApplicableDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_2)));
        coverSheet.setCheckFullApp(YesNoNotApplicableDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_3)));
        coverSheet.setCheckTypeApp(YesNoDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_4)));
        coverSheet.setCheckAppCert(YesNoDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_5)));
        return coverSheet;
    }

    /**
     * 
     * This method checks for the informations like Does narrative include merit review criteria, URL's should not be included in
     * the narrative Does narrative include info regarding prior support,HR Info that is mandatory for renewals from academic
     * institutions
     * 
     * @return ProjectNarrative object containing project narrative information details.
     */
    private ProjectNarrative getProjectNarrative() {
        ProjectNarrative projectNarrative = ProjectNarrative.Factory.newInstance();
        projectNarrative.setCheckProjectNarrative(YesNoDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_9)));
        projectNarrative.setCheckMeritReview(YesNoNotApplicableDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_10)));
        projectNarrative.setCheckPriorSupport(YesNoNotApplicableDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_NIH_INVESTIGAYOR)));
        projectNarrative.setCheckHRInfo(YesNoNotApplicableDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_NSF_INVESTIGATOR)));
        projectNarrative.setCheckURL(YesNoNotApplicableDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_SPACE_CHANGE)));
        return projectNarrative;
    }

    /**
     * 
     * This method gets informations like attachment of Equipments,Supplementary information,Additional items relevant to NSF
     * Program complete.
     * 
     * @return Equipment object containing equipment information details.
     */
    private Equipment getEquipment() {
        Equipment equipment = Equipment.Factory.newInstance();
        equipment.setCheckEquipment(YesNoDataType.Enum.forInt(getChecklistAnswer(16)));
        equipment.setCheckSuppDoc(YesNoNotApplicableDataType.Enum.forInt(getChecklistAnswer(17)));
        equipment.setCheckAdditionalItems(YesNoNotApplicableDataType.Enum.forInt(getChecklistAnswer(18)));
        return equipment;
    }

    /**
     * 
     * This method returns CurrentPendingSupport, BioSketch and RRSeniorProfile information for the the RRSrProfile type.
     * 
     * @return RRSrProfile object containing profile details.
     */
    private RRSrProfile getRRSrProfile() {
        RRSrProfile rrSrProfile = RRSrProfile.Factory.newInstance();
        rrSrProfile.setCheckRRSrProfile(YesNoDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_CURRENT_SERVING_PI)));
        rrSrProfile.setCheckBioSketch(YesNoDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_HIGH_RESOLUTION)));
        rrSrProfile.setCheckCurrentPendingSupport(YesNoDataType.Enum.forInt(getChecklistAnswer(QUESTION_ID_FULL_APPLICATION)));
        return rrSrProfile;
    }


    /**
     * This method creates {@link XmlObject} of type {@link NSFApplicationChecklistDocument} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getNSFApplicationChecklist();
    }

}

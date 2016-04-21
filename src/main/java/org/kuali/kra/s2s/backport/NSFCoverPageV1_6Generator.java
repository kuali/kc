package org.kuali.kra.s2s.backport;

import gov.grants.apply.forms.nsfCoverPage16V16.NSFCoverPage16Document;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin1Max100DataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.s2s.generator.impl.NSFCoverPageBaseGenerator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NSFCoverPageV1_6Generator extends NSFCoverPageBaseGenerator {

    private static final int MENTORING_PLAN = 147;
    private static final int DATA_MANAGEMENT_PLAN = 146;
    private static final int LOBBYING_ACTIVITIES_QUESTION = 11;
    private static final int FUNDING_MECHANISM_QUESTION = 10;

    private NSFCoverPage16Document getNSFCoverPage16() {
        NSFCoverPage16Document nsfCoverPage16Document = NSFCoverPage16Document.Factory.newInstance();
        NSFCoverPage16Document.NSFCoverPage16 nsfCoverPage16 = NSFCoverPage16Document.NSFCoverPage16.Factory.newInstance();
        nsfCoverPage16.setFormVersion(FormVersion.v1_6.getVersion());
        setFundingOpportunityNumber(nsfCoverPage16);

        if (pdDoc.getDevelopmentProposal().getS2sOpportunity() != null && pdDoc.getDevelopmentProposal().getS2sOpportunity().getClosingDate() != null) {
            final Calendar closingDate = Calendar.getInstance();
            closingDate.setTime(pdDoc.getDevelopmentProposal().getS2sOpportunity().getClosingDate());
            nsfCoverPage16.setDueDate(closingDate);
        }
        nsfCoverPage16.setNSFUnitConsideration(getNSFUnitConsideration());
        setOtherInfo(nsfCoverPage16);
        AttachmentGroupMin1Max100DataType attachmentGroup = AttachmentGroupMin1Max100DataType.Factory.newInstance();
        attachmentGroup.setAttachedFileArray(getAttachedFileDataTypes());
        if (attachmentGroup.getAttachedFileArray().length > 0) {
            nsfCoverPage16.setSingleCopyDocuments(attachmentGroup);
        }
        final AttachedFileDataType dataManagementPlan = getAttachedNarrativeFile(DATA_MANAGEMENT_PLAN);
        if (dataManagementPlan != null) {
            nsfCoverPage16.setDataManagementPlan(dataManagementPlan);
        }

        final AttachedFileDataType mentoringPlan = getAttachedNarrativeFile(MENTORING_PLAN);
        if (mentoringPlan != null) {
            nsfCoverPage16.setMentoringPlan(mentoringPlan);
        }
        nsfCoverPage16Document.setNSFCoverPage16(nsfCoverPage16);
        return nsfCoverPage16Document;
    }


    private void setFundingOpportunityNumber(NSFCoverPage16Document.NSFCoverPage16 nsfCoverPage16) {
        nsfCoverPage16.setFundingOpportunityNumber(StringUtils.substring(pdDoc.getDevelopmentProposal().getProgramAnnouncementNumber(), 0, PROGRAM_ANNOUNCEMENT_NUMBER_MAX_LENGTH));
    }

    private void setOtherInfo(NSFCoverPage16Document.NSFCoverPage16 nsfCoverPage16) {
        NSFCoverPage16Document.NSFCoverPage16.OtherInfo otherInfo = NSFCoverPage16Document.NSFCoverPage16.OtherInfo.Factory.newInstance();
        NSFCoverPage16Document.NSFCoverPage16.PIInfo pInfo = NSFCoverPage16Document.NSFCoverPage16.PIInfo.Factory.newInstance();
        for (Answer questionnaireAnswer : s2sUtilService.getQuestionnaireAnswers(pdDoc.getDevelopmentProposal(), getNamespace(), getFormName())) {
            String answer = questionnaireAnswer.getAnswer();
            int questionId = questionnaireAnswer.getQuestionNumber();

            if (answer != null) {
                switch (questionId) {
                    case QUESTION_CURRENT_PI:
                        pInfo.setIsCurrentPI(answer.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES : YesNoDataType.N_NO);
                        break;
                    case QUESTION_BEGIN_INVESTIGATOR:
                        otherInfo.setIsBeginInvestigator(answer.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES : YesNoDataType.N_NO);
                        break;
                    case QUESTION_ACCOMPLISHMENT_RENEWAL:
                        otherInfo.setIsAccomplishmentRenewal(answer.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES : YesNoDataType.N_NO);
                        break;
                    case FUNDING_MECHANISM_QUESTION:
                        setFundingMechanism(nsfCoverPage16, answer);
                        break;
                    case LOBBYING_ACTIVITIES_QUESTION:
                        otherInfo.setIsDisclosureLobbyingActivities(answer.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES : YesNoDataType.N_NO);
                    default:
                        break;
                }
            }
        }
        nsfCoverPage16.setPIInfo(pInfo);
        nsfCoverPage16.setOtherInfo(otherInfo);
    }

    private void setFundingMechanism(NSFCoverPage16Document.NSFCoverPage16 nsfCoverPage16, String answer) {
        NSFCoverPage16Document.NSFCoverPage16.FundingMechanism.Enum fundingMechanism = NSFCoverPage16Document.NSFCoverPage16.FundingMechanism.RAPID;
        if (StringUtils.equalsIgnoreCase(answer, NSFCoverPage16Document.NSFCoverPage16.FundingMechanism.CONFERENCE.toString())) {
            fundingMechanism = NSFCoverPage16Document.NSFCoverPage16.FundingMechanism.CONFERENCE;
        } else if (StringUtils.equalsIgnoreCase(answer, NSFCoverPage16Document.NSFCoverPage16.FundingMechanism.EAGER.toString())) {
            fundingMechanism = NSFCoverPage16Document.NSFCoverPage16.FundingMechanism.EAGER;
        } else if (StringUtils.equalsIgnoreCase(answer, NSFCoverPage16Document.NSFCoverPage16.FundingMechanism.EQUIPMENT.toString())) {
            fundingMechanism = NSFCoverPage16Document.NSFCoverPage16.FundingMechanism.EQUIPMENT;
        } else if (StringUtils.equalsIgnoreCase(answer, NSFCoverPage16Document.NSFCoverPage16.FundingMechanism.FACILITY_CENTER.toString())) {
            fundingMechanism = NSFCoverPage16Document.NSFCoverPage16.FundingMechanism.FACILITY_CENTER;
        } else if (StringUtils.equalsIgnoreCase(answer, NSFCoverPage16Document.NSFCoverPage16.FundingMechanism.FELLOWSHIP.toString())) {
            fundingMechanism = NSFCoverPage16Document.NSFCoverPage16.FundingMechanism.FELLOWSHIP;
        } else if (StringUtils.equalsIgnoreCase(answer, NSFCoverPage16Document.NSFCoverPage16.FundingMechanism.IDEAS_LAB.toString())) {
            fundingMechanism = NSFCoverPage16Document.NSFCoverPage16.FundingMechanism.IDEAS_LAB;
        }
        nsfCoverPage16.setFundingMechanism(fundingMechanism);
    }

    private NSFCoverPage16Document.NSFCoverPage16.NSFUnitConsideration getNSFUnitConsideration() {
        NSFCoverPage16Document.NSFCoverPage16.NSFUnitConsideration nsfConsideration = NSFCoverPage16Document.NSFCoverPage16.NSFUnitConsideration.Factory.newInstance();
        nsfConsideration.setDivisionCode(pdDoc.getDevelopmentProposal().getAgencyDivisionCode());
        nsfConsideration.setProgramCode(pdDoc.getDevelopmentProposal().getAgencyProgramCode());
        return nsfConsideration;
    }

    private AttachedFileDataType[] getAttachedFileDataTypes() {
        List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeType().getNarrativeTypeCode() != null) {
                int narrativeTypeCode = Integer.parseInt(narrative.getNarrativeType().getNarrativeTypeCode());
                if (narrativeTypeCode == PERSONAL_DATA || narrativeTypeCode == PROPRIETARY_INFORMATION || narrativeTypeCode == SINGLE_COPY_DOCUMENT) {
                    AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        attachedFileDataTypeList.add(attachedFileDataType);
                    }
                }
            }
        }
        return attachedFileDataTypeList.toArray(new AttachedFileDataType[attachedFileDataTypeList.size()]);
    }

    private AttachedFileDataType getAttachedNarrativeFile(int narrativeCode) {
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeType().getNarrativeTypeCode() != null && Integer.parseInt(narrative.getNarrativeType().getNarrativeTypeCode()) == narrativeCode) {
                AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
                if (attachedFileDataType != null) {
                    return attachedFileDataType;
                }
            }
        }
        return null;
    }

    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getNSFCoverPage16();
    }

    public String getFormName() {
        return "NSF_CoverPage_1_6-V1.6";
    }
}

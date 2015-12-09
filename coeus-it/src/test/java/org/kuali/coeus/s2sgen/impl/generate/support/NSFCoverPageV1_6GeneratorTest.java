package org.kuali.coeus.s2sgen.impl.generate.support;


import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.coeus.propdev.api.questionnaire.PropDevQuestionAnswerService;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeAttachment;
import org.kuali.coeus.propdev.impl.attachment.NarrativeStatus;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentModuleQuestionnaireBean;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.coeus.propdev.impl.s2s.question.ProposalDevelopmentS2sModuleQuestionnaireBean;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.ClassLoaderUtils;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.util.*;


public class NSFCoverPageV1_6GeneratorTest extends S2STestBase<NSFCoverPageV1_6Generator> {

    private static final String NAMESPACE = "http://apply.grants.gov/forms/NSF_CoverPage_1_6-V1.6";
    private static final String FORM_NAME = "NSF_CoverPage_1_6-V1.6";


    @Override
    protected void prepareS2sData(ProposalDevelopmentDocument document) {
        super.prepareS2sData(document);
        S2sOpportunity s2sOpportunity = new S2sOpportunity();
        s2sOpportunity.setProviderCode("1");
        s2sOpportunity.setOpportunity("13-540");
        s2sOpportunity.setDevelopmentProposal(document.getDevelopmentProposal());


        s2sOpportunity.setOpportunityId("13-540");
        List<S2sOppForms> S2sOppFormsList = new ArrayList<S2sOppForms>();
        S2sOppForms s2sOppForms = new S2sOppForms();
        s2sOppForms.setAvailable(true);
        s2sOppForms.setInclude(true);

        S2sOppForms.S2sOppFormsId s2sOppFormsId = new S2sOppForms.S2sOppFormsId();
        s2sOppFormsId.setOppNameSpace(NAMESPACE);
        s2sOppForms.setFormName(FORM_NAME);
        s2sOppForms.setS2sOppFormsId(s2sOppFormsId);
        s2sOppForms.getS2sOppFormsId().setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
        S2sOppFormsList.add(s2sOppForms);
        s2sOpportunity.setS2sOppForms(S2sOppFormsList);

        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date(System.currentTimeMillis()));
        XMLGregorianCalendar date1 = null;
        try {
            date1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        s2sOpportunity.setClosingDate(date1.toGregorianCalendar());

        document.getDevelopmentProposal().setS2sOpportunity(s2sOpportunity);
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {

        document.getDevelopmentProposal().setAgencyDivisionCode("88888888");
        document.getDevelopmentProposal().setAgencyProgramCode("4444");
        document.getDevelopmentProposal().setProgramAnnouncementNumber("13-540");

        document = setMentoringPlan(document);
        document = setDmPlan(document);

        saveAnswers(document);

    }

    protected ProposalDevelopmentDocument setMentoringPlan(ProposalDevelopmentDocument document) throws Exception {
        // mentoring plan
        NarrativeAttachment narrativeAttachment2 = new NarrativeAttachment();
        DefaultResourceLoader resourceLoader2 = new DefaultResourceLoader(ClassLoaderUtils.getDefaultClassLoader());
        Resource resource2 = resourceLoader2.getResource(S2STestConstants.ATT_PACKAGE + "/exercise5.pdf");
        InputStream inStream2 = resource2.getInputStream();
        BufferedInputStream bis2 = new BufferedInputStream(inStream2);
        byte[] narrativePdf2 = new byte[bis2.available()];
        narrativeAttachment2.setData(narrativePdf2);
        narrativeAttachment2.setName("exercise5");
        Narrative mentoringPlan = new Narrative();
        mentoringPlan.setDevelopmentProposal(document.getDevelopmentProposal());
        mentoringPlan.setModuleNumber(1);
        mentoringPlan.setModuleSequenceNumber(1);
        mentoringPlan.setModuleStatusCode("C");
        mentoringPlan.setNarrativeAttachment(narrativeAttachment2);
        mentoringPlan.setObjectId("12345678890efgh");
        mentoringPlan.setName("mPlan");

        Map map1 = new HashMap();
        map1.put("code", "147");
        List<NarrativeType> type = (List<NarrativeType>) getBusinessObjectService().findMatching(NarrativeType.class, map1);

        mentoringPlan.setNarrativeTypeCode(type.get(0).getCode());

        document.getDevelopmentProposal().getNarratives().add(mentoringPlan);

        return document;
    }

    protected ProposalDevelopmentDocument setDmPlan(ProposalDevelopmentDocument document) throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "test.txt",                //filename
                "Hello World".getBytes()); //content
        Narrative narrative = new Narrative();
        narrative.setName("test");
        narrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_INCOMPLETE);

        Map map = new HashMap();
        map.put("code", "146");
        List<NarrativeType> types = (List<NarrativeType>) getBusinessObjectService().findMatching(NarrativeType.class, map);

        map = new HashMap();
        map.put("code", "C");
        List<NarrativeStatus> status = (List<NarrativeStatus>) getBusinessObjectService().findMatching(NarrativeStatus.class, map);
        narrative.setNarrativeStatus(status.get(0));

        narrative.setModuleNumber(2);
        narrative.setModuleSequenceNumber(2);
        try {
            narrative.init(mockMultipartFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        narrative.getNarrativeAttachment().setName("dmPlan");
        narrative.setNarrativeTypeCode(types.get(0).getCode());
        narrative.setDevelopmentProposal(document.getDevelopmentProposal());

        document.getDevelopmentProposal().getNarratives().add(narrative);

        return document;

    }

    protected BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

    protected void saveAnswers(ProposalDevelopmentDocument document) {
        QuestionnaireAnswerService service2 = KcServiceLocator.getService(QuestionnaireAnswerService.class);

        List<AnswerHeader> rawAnswerHeaders = service2.getQuestionnaireAnswer(new ProposalDevelopmentS2sModuleQuestionnaireBean(document.getDevelopmentProposal()));

        for(AnswerHeader answerHeader:rawAnswerHeaders) {
            if (answerHeader.getLabel().contains("NSF")) {
                List<Answer> answerDetails = answerHeader.getAnswers();
                for (Answer answers : answerDetails) {
                    answers.setAnswer("Y");
                    if (answerHeader.getLabel().contains("Funding Mechanism")) {
                        answers.setAnswer("RAPID");

                    }
                }
                KNSServiceLocator.getBusinessObjectService().save(answerHeader);
            }
        }


        List<AnswerHeader> answerHeaders;
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ProposalDevelopmentModuleQuestionnaireBean(document.getDevelopmentProposal());
        answerHeaders = KcServiceLocator.getService(QuestionnaireAnswerService.class).getQuestionnaireAnswer(moduleQuestionnaireBean);

        KNSServiceLocator.getBusinessObjectService().save(answerHeaders);
    }

    @Override
    protected String getFormGeneratorName() {
        return NSFCoverPageV1_6Generator.class.getSimpleName();
    }

    public PropDevQuestionAnswerService getPropDevQuestionAnswerService() {
        return KcServiceLocator.getService(PropDevQuestionAnswerService.class);
    }
}

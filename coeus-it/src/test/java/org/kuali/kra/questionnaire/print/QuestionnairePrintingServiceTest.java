/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.questionnaire.print;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.questionnaire.framework.print.QuestionnairePrint;
import org.kuali.coeus.common.questionnaire.framework.print.QuestionnairePrintingService;
import org.kuali.coeus.common.questionnaire.impl.print.QuestionnairePrintingServiceImpl;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.printing.util.PrintingServiceTestBase;
import org.kuali.kra.printing.util.PrintingTestUtils;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.kra.protocol.actions.print.QuestionnairePrintOption;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
public class QuestionnairePrintingServiceTest extends PrintingServiceTestBase {
    private QuestionnairePrintingService questionnairePrintingService;
    private Mockery context = new JUnit4Mockery();

    /**
     * This method tests QuestionnairePrintingService. It generates Questionnaire report.
     */
    @Test
    public void testQuestionnairePrinting() {
        try {
            String docNumber = createQuestionnaireMaintDocument();
            Map<String, Object> reportParameters = new HashMap<String, Object>();
            reportParameters.put("documentNumber", docNumber);
            reportParameters.put("questionnaireSeqId", 1);
            AttachmentDataSource pdfBytes = getPrintingService().printQuestionnaire(null, reportParameters);

            // FIXME Writing PDF to disk for testing purpose only.
            PrintingTestUtils.writePdftoDisk(pdfBytes,"Questionnaire");
            assertNotNull(pdfBytes);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            //assert false;
            throw new RuntimeException(e);
        }
    }

    private String createQuestionnaireMaintDocument() throws WorkflowException {
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KcServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Questionnaire.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestionnaire("test1", "desc 1"));
        maintDocument.getNewMaintainableObject().setMaintenanceAction(KRADConstants.MAINTENANCE_NEW_ACTION);
        documentService.routeDocument(maintDocument,null,null);
        // not sure why it is not persisted in DB.  also need to do this save, so getcustomactionurls can retrieve it with bos
        Questionnaire questionnaire = (Questionnaire)maintDocument.getNewMaintainableObject().getDataObject();
        questionnaire.setDocumentNumber(maintDocument.getDocumentNumber());
        KNSServiceLocator.getBusinessObjectService().save((Questionnaire) maintDocument.getNewMaintainableObject().getDataObject());

        String docNumber = maintDocument.getDocumentNumber();
        return docNumber;
    }
    /**
     * 
     * This method to create questionnaire for maintenance document manipulation
     * @param name
     * @param desc
     * @return
     */
    private Questionnaire createQuestionnaire(String name, String desc) {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setName(name);
        questionnaire.setDescription(desc);
        questionnaire.setSequenceNumber(1);

        return questionnaire;
    }

    /**
     * This method tests QuestionnairePrintingService. It generates Questionnaire Answer report.
     */
    @Test
    public void testQuestionnaireAnswerPrinting() {
        try {
            Map<String, Object> reportParameters = new HashMap<String, Object>();
            reportParameters.put("documentNumber", createQuestionnaireMaintDocument());
            reportParameters.put("questionnaireSeqId", 1);
            ProtocolDocument document = new ProtocolDocument();
            document.getProtocol().setProtocolNumber("1234");
            AttachmentDataSource pdfBytes = getPrintingService().printQuestionnaireAnswer(document.getProtocol(), reportParameters);

            // FIXME Writing PDF to disk for testing purpose only.
            PrintingTestUtils.writePdftoDisk(pdfBytes,
                    "QuestionnaireAnswer");
            assertNotNull(pdfBytes);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetQuestionnairePrintable() {
        List<QuestionnairePrintOption> questionnairesToPrints = new ArrayList<QuestionnairePrintOption>();
        QuestionnairePrintOption printOption1 = new QuestionnairePrintOption();
        printOption1.setLabel("Test1");
        printOption1.setItemKey("1234");
        printOption1.setSubItemKey("0");
        printOption1.setQuestionnaireId(1L);
        printOption1.setQuestionnaireSeqId(1);
        printOption1.setSelected(true);
        questionnairesToPrints.add(printOption1);
        QuestionnairePrintOption printOption2 = new QuestionnairePrintOption();
        printOption2.setLabel("Test2");
        printOption2.setQuestionnaireId(2L);
        printOption2.setQuestionnaireSeqId(1);
        printOption2.setSelected(false);
        questionnairesToPrints.add(printOption2);
        final Map  pkMap = new HashMap();
        pkMap.put("id", 1L);
        try {
            final Questionnaire questionnaire = new Questionnaire();
            questionnaire.setQuestionnaireSeqId("1");
            questionnaire.setQuestionnaireRefIdFromLong(1L);
            ProtocolDocument document = new ProtocolDocument();
            final List<Protocol>protocols = new ArrayList<Protocol>(); 
            protocols.add(document.getProtocol());
            document.getProtocol().setProtocolNumber("1234");
            final Map keyValues = new HashMap();
            keyValues.put("protocolNumber", "1234");
            keyValues.put("sequenceNumber", "0");
           final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
           QuestionnairePrint questionnairePrint = new QuestionnairePrint();
           QuestionnairePrintingServiceImpl qnPrintingServiceImpl = new QuestionnairePrintingServiceImpl();
            context.checking(new Expectations() {{
                one(businessObjectService).findByPrimaryKey(Questionnaire.class, pkMap); will(returnValue(questionnaire));
                one(businessObjectService).findMatching(Protocol.class, keyValues); will(returnValue(protocols));
            }});
            qnPrintingServiceImpl.setBusinessObjectService(businessObjectService);
            qnPrintingServiceImpl.setQuestionnairePrint(questionnairePrint);
            
            List<Printable> printables = qnPrintingServiceImpl.getQuestionnairePrintable(document.getProtocol(), questionnairesToPrints);
            // FIXME Writing PDF to disk for testing purpose only.
            assertEquals(printables.size(),1);
            assertEquals(((AbstractPrint)printables.get(0)).getReportParameters().get("questionnaireSeqId"), 1);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private QuestionnairePrintingService getPrintingService() {
        if (questionnairePrintingService == null) {
            questionnairePrintingService = KcServiceLocator
                    .getService(QuestionnairePrintingService.class);
        }
        return questionnairePrintingService;
    }


}

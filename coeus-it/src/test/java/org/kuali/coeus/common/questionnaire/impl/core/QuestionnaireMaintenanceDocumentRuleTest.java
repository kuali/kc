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
package org.kuali.coeus.common.questionnaire.impl.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class QuestionnaireMaintenanceDocumentRuleTest extends MaintenanceRuleTestBase {
    private QuestionnaireMaintenanceDocumentRule rule = null;

    @Before
    public void setUp() throws Exception {
        rule = new QuestionnaireMaintenanceDocumentRule();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
    }
    
    @Test
    public void testQuestionnaireRule() throws Exception {

        Questionnaire questionnaire = new Questionnaire();
        MaintenanceDocument questionnaireMaintenanceDocument = newMaintDoc(questionnaire);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionnaireMaintenanceDocument));
        questionnaire.setName("test name");
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionnaireMaintenanceDocument));
        questionnaire.setDescription("test name");
        assertTrue(rule.processCustomRouteDocumentBusinessRules(questionnaireMaintenanceDocument));
        Questionnaire questionnaire1 = new Questionnaire();
        questionnaire1.setName("test name");
        questionnaire1.setDescription("test name");
        questionnaire1.setSequenceNumber(1);
        questionnaireMaintenanceDocument = newMaintDoc(questionnaire1);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(questionnaireMaintenanceDocument));
        KcServiceLocator.getService(BusinessObjectService.class).save(questionnaire1);
        questionnaire1.setId(null);
        questionnaire1.setQuestionnaireSeqId(null);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(questionnaireMaintenanceDocument));
        

    }
    
}

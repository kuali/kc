--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

update krew_doc_typ_t set AUTHORIZER = 'org.kuali.coeus.propdev.impl.auth.ProposalDevelopmentWorkflowDocumentAuthorizer' where AUTHORIZER = 'org.kuali.kra.proposaldevelopment.document.authorization.ProposalDevelopmentWorkflowDocumentAuthorizer';
update krew_doc_typ_t set POST_PRCSR = 'org.kuali.coeus.common.questionnaire.impl.core.QuestionnairePostProcessor' where POST_PRCSR = 'org.kuali.kra.questionnaire.QuestionnairePostProcessor';
update krew_doc_typ_t set POST_PRCSR = 'org.kuali.coeus.sys.framework.workflow.KcPostProcessor' where POST_PRCSR = 'org.kuali.kra.workflow.KcPostProcessor';
update krew_rte_node_cfg_parm_t set VAL = REPLACE(VAL, 'org.kuali.kra.proposaldevelopment.service.impl.ProposalDevelopmentRulesEngineExecutorImpl', 'org.kuali.coeus.propdev.impl.core.ProposalDevelopmentRulesEngineExecutorImpl') where VAL like '%org.kuali.kra.proposaldevelopment.service.impl.ProposalDevelopmentRulesEngineExecutorImpl%';
update krew_rte_node_cfg_parm_t set VAL = REPLACE(VAL, 'org.kuali.kra.kew.SimpleBooleanSplitNode', 'org.kuali.coeus.sys.framework.workflow.SimpleBooleanSplitNode') where VAL like '%org.kuali.kra.kew.SimpleBooleanSplitNode%';
update krew_rte_node_t set TYP = 'org.kuali.coeus.sys.framework.workflow.SimpleBooleanSplitNode' where TYP = 'org.kuali.kra.kew.SimpleBooleanSplitNode';
update krew_rule_attr_t set CLS_NM = 'org.kuali.coeus.common.framework.unit.admin.UnitAdministratorRoleAttribute' where CLS_NM = 'org.kuali.kra.workflow.UnitAdministratorRoleAttribute';
update krew_rule_attr_t set CLS_NM = 'org.kuali.coeus.common.framework.unit.crrspndnt.UnitCorrespondentRoleAttribute' where CLS_NM = 'org.kuali.kra.workflow.UnitCorrespondentRoleAttribute';
update krew_rule_attr_t set CLS_NM = 'org.kuali.coeus.common.impl.org.crrspndnt.OrganizationCorrespondentRoleAttribute' where CLS_NM = 'org.kuali.kra.workflow.OrganizationCorrespondentRoleAttribute';
update krew_rule_attr_t set XML = REPLACE(XML, 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 'org.kuali.coeus.propdev.impl.core.DevelopmentProposal') where XML like '%org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal%';
update krew_rule_attr_t set XML = REPLACE(XML, 'org.kuali.kra.budget.versions.BudgetVersionOverview', 'org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview') where XML like '%org.kuali.kra.budget.versions.BudgetVersionOverview%';
update krew_rule_attr_t set XML = REPLACE(XML, 'org.kuali.kra.bo.KcPerson', 'org.kuali.coeus.common.framework.person.KcPerson') where XML like '%org.kuali.kra.bo.KcPerson%';
update krew_rule_attr_t set XML = REPLACE(XML, 'org.kuali.kra.bo.Unit', 'org.kuali.coeus.common.framework.unit.Unit') where XML like '%org.kuali.kra.bo.Unit%';
update krew_rule_attr_t set XML = REPLACE(XML, 'org.kuali.kra.bo.Sponsor', 'org.kuali.coeus.common.framework.sponsor.Sponsor') where XML like '%org.kuali.kra.bo.Sponsor%';
update krew_rule_rsp_t set NM = 'org.kuali.coeus.common.framework.unit.admin.UnitAdministratorRoleAttribute!UnitAdministratorRoleAttribute!UnitAdministrator' where NM = 'org.kuali.kra.workflow.UnitAdministratorRoleAttribute!UnitAdministratorRoleAttribute!UnitAdministrator';
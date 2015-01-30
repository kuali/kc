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

--multiplePI
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 'multiplePI', 'Multiple PI check', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
--s2sBudgetRule
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 's2sBudgetRule', 'checks for form inlusion', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID,KC_KRMS_TERM_FUNCTION_ID,PARAM_NAME,PARAM_TYPE,PARAM_ORDER,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'GrantsGov Form Name', 'java.lang.Object', 2, sysdate, 'admin', 1, sys_guid())
/
--monitoredSponsorRule
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 'monitoredSponsorRule', 'checks if proposal is using a passed in sposnor hirerarchy', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID,KC_KRMS_TERM_FUNCTION_ID,PARAM_NAME,PARAM_TYPE,PARAM_ORDER,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
		values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'monitored sponsor hierarchies', 'java.lang.Object', 2, sysdate, 'admin', 1, sys_guid())
/
--s2sResplanRule
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 's2sResplanRule', 'checks for maximum attachments in narrative types.', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID,KC_KRMS_TERM_FUNCTION_ID,PARAM_NAME,PARAM_TYPE,PARAM_ORDER,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
		values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'narative types', 'java.lang.Object', 2, sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID,KC_KRMS_TERM_FUNCTION_ID,PARAM_NAME,PARAM_TYPE,PARAM_ORDER,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
		values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'maximum number', 'java.lang.Object', 3, sysdate, 'admin', 1, sys_guid())
/
--grantsFormRule
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 'grantsFormRule', 'checks for forms existence.', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID,KC_KRMS_TERM_FUNCTION_ID,PARAM_NAME,PARAM_TYPE,PARAM_ORDER,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
		values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'form name', 'java.lang.Object', 2, sysdate, 'admin', 1, sys_guid())
/
--biosketchFileNameRule
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 'biosketchFileNameRule', 'checks for special characters in the biosketch file name.', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
--ospAdminPropPersonRule
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 'ospAdminPropPersonRule', 'checks to see if an OSP administrator is a proposal person.', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
--costElementVersionLimit
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 'costElementVersionLimit', 'checks that a cost element limit has not been exceeded.', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID,KC_KRMS_TERM_FUNCTION_ID,PARAM_NAME,PARAM_TYPE,PARAM_ORDER,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'version number', 'java.lang.Object', 2, sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID,KC_KRMS_TERM_FUNCTION_ID,PARAM_NAME,PARAM_TYPE,PARAM_ORDER,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'cost element name', 'java.lang.Object', 3, sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID,KC_KRMS_TERM_FUNCTION_ID,PARAM_NAME,PARAM_TYPE,PARAM_ORDER,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'limit', 'java.lang.Object', 4, sysdate, 'admin', 1, sys_guid())
/
--divisionCodeRule
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 'divisionCodeRule', 'checks the division code is null.', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
--divisionCodeIsFellowship
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 'divisionCodeIsFellowship', 'checks if the proposal is a fellowship.', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID,KC_KRMS_TERM_FUNCTION_ID,PARAM_NAME,PARAM_TYPE,PARAM_ORDER,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'fellowship codes', 'java.lang.Object', 2, sysdate, 'admin', 1, sys_guid())
/
--budgetSubawardOrganizationnameRule
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 'budgetSubawardOrganizationnameRule', 'checks subaward organization for special characters.', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
--checkProposalPerson
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 'checkProposalPerson', 'Checks if the passes in person ID is a proposal person.', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID,KC_KRMS_TERM_FUNCTION_ID,PARAM_NAME,PARAM_TYPE,PARAM_ORDER,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'person ID', 'java.lang.Object', 2, sysdate, 'admin', 1, sys_guid())
/
--agencyProgramCodeNullRule
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 'agencyProgramCodeNullRule', 'Checks if the passes in person ID is a proposal personrule to CHECK IF a proposal agency program code field  is  null.', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
--allProposalsRule
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 'allProposalsRule', 'Pointless rule, just returns true.', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
--proposalLeadUnitInHierarchy
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 'proposalLeadUnitInHierarchy', 'checks if the proposals lead unit is in the passed in unit hierarchy.', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID,KC_KRMS_TERM_FUNCTION_ID,PARAM_NAME,PARAM_TYPE,PARAM_ORDER,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'unit number to check', 'java.lang.Object', 2, sysdate, 'admin', 1, sys_guid())
/
--s2sSubawardRule
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 's2sSubawardRule', 'verifies only one set of unique forms.', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID,KC_KRMS_TERM_FUNCTION_ID,PARAM_NAME,PARAM_TYPE,PARAM_ORDER,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'rr form names', 'java.lang.Object', 2, sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID,KC_KRMS_TERM_FUNCTION_ID,PARAM_NAME,PARAM_TYPE,PARAM_ORDER,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'phs form names', 'java.lang.Object', 3, sysdate, 'admin', 1, sys_guid())
/
--proposalGrantsRule
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 'proposalGrantsRule', 'verifies that there are grans.gov submissions.', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
--narrativeTypeRule
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 'narrativeTypeRule', 'verfies activity type code is specified.', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
--s2s398CoverRule
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 's2s398CoverRule', 'verify PHS Cover letter narrative(39) is attached, must include s2s cover letter form', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID,KC_KRMS_TERM_FUNCTION_ID,PARAM_NAME,PARAM_TYPE,PARAM_ORDER,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'PHS Cover letters', 'java.lang.Object', 2, sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID,KC_KRMS_TERM_FUNCTION_ID,PARAM_NAME,PARAM_TYPE,PARAM_ORDER,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'narrative type code', 'java.lang.Object', 3, sysdate, 'admin', 1, sys_guid())
/
--narrativeFileName
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 'narrativeFileName', 'verify no special characters are used.', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
--costElementInVersion
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 'costElementInVersion', 'verify that a cost element is used in the specified version of the proposal', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID,KC_KRMS_TERM_FUNCTION_ID,PARAM_NAME,PARAM_TYPE,PARAM_ORDER,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'version number', 'java.lang.Object', 2, sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID,KC_KRMS_TERM_FUNCTION_ID,PARAM_NAME,PARAM_TYPE,PARAM_ORDER,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'cost element', 'java.lang.Object', 3, sysdate, 'admin', 1, sys_guid())
/
--investigatorKeyPersonCertificationRule
insert into KC_KRMS_TERM_FUNCTION (KC_KRMS_TERM_FUNCTION_ID,KRMS_TERM_NM, DESCRIPTION, RETURN_TYPE, FUNCTION_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUNCTION_ID.NEXTVAL, 'investigatorKeyPersonCertificationRule', 'verify that each investigator and key person are certified.', 'java.lang.String', '2', sysdate, 'admin', 1, sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM(KC_KRMS_TERM_FUN_PARAM_ID, KC_KRMS_TERM_FUNCTION_ID, PARAM_NAME, PARAM_TYPE, PARAM_ORDER, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.NEXTVAL, SEQ_KC_KRMS_TERM_FUNCTION_ID.CURRVAL, 'DevelopmentProposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
        1, sysdate, 'admin', 1, sys_guid())
/

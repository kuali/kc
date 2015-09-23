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

INSERT INTO KRIM_TYP_ID_S VALUES(NULL);
INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
VALUES( CONCAT('KC', (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S)), UUID(), 1, 'Derived Role: Proposal Department Unit Heads', 'departmentLevelUnitHeadDerivedRoleTypeService', 'Y', 'KC-PD');

INSERT INTO KRIM_ROLE_ID_S VALUES(NULL);
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S)), UUID(), 1, 'Proposal Department Unit Heads', 'KC-PD', 'Department Unit Heads for all Proposal Units', CONCAT('KC', (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S)), 'Y', NOW());

INSERT INTO KRIM_TYP_ID_S VALUES(NULL);
INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
VALUES(CONCAT('KC', (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S)), UUID(), 1, 'Derived Role: Proposal College Unit Heads', 'collegeLevelUnitHeadDerivedRoleTypeService', 'Y', 'KC-PD');

INSERT INTO KRIM_ROLE_ID_S VALUES(NULL);
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S)), UUID(), 1, 'Proposal College Unit Heads', 'KC-PD', 'College Unit Heads for all Proposal Units', CONCAT('KC', (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S)), 'Y', NOW());

insert into KREW_TYP_S VALUES(NULL);
insert into KREW_TYP_T (TYP_ID, NM, NMSPC_CD, SRVC_NM, ACTV, VER_NBR) 
	values (CONCAT('KC', (select max(ID) from KREW_TYP_S)), 'Development Proposal Peopleflow', 'KC-WKFLW', '{http://kc.kuali.org/core/v5_0}proposalPeopleFlowTypeService', 'Y', 1);

insert into KREW_PPL_FLW_S VALUES(NULL);
insert into KREW_PPL_FLW_T (PPL_FLW_ID, NM, NMSPC_CD, TYP_ID, ACTV, VER_NBR, DESC_TXT) 
	values (CONCAT('KC', (select max(ID) from KREW_PPL_FLW_S)), 'Proposal Development Standard Workflow', 'KC-PD', 
		CONCAT('KC', (select max(ID) from KREW_TYP_S)), 'Y', 1, 'Proposal Development Standard Workflow that includes Investigators, OSP Approvers, Department and College-Level Department Heads');

insert into KREW_PPL_FLW_MBR_S VALUES(NULL);
insert into KREW_RSP_S VALUES(NULL);
insert into KREW_PPL_FLW_MBR_T (PPL_FLW_MBR_ID, PPL_FLW_ID, MBR_TYP_CD, MBR_ID, PRIO, VER_NBR, ACTN_RQST_PLCY_CD, RSP_ID, FRC_ACTN)
 	values (CONCAT('KC', (select max(ID) from KREW_PPL_FLW_MBR_S)), CONCAT('KC', (select max(ID) from KREW_PPL_FLW_S)), 'R', 
 		(select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'OSPApprover' and NMSPC_CD = 'KC-WKFLW'), 100, 1, 'F', (select max(ID) from KREW_RSP_S), 1);
		
insert into KREW_PPL_FLW_MBR_S VALUES(NULL);
insert into KREW_RSP_S VALUES(NULL);
insert into KREW_PPL_FLW_MBR_T (PPL_FLW_MBR_ID, PPL_FLW_ID, MBR_TYP_CD, MBR_ID, PRIO, VER_NBR, ACTN_RQST_PLCY_CD, RSP_ID, FRC_ACTN)
 	values (CONCAT('KC', (select max(ID) from KREW_PPL_FLW_MBR_S)), CONCAT('KC', (select max(ID) from KREW_PPL_FLW_S)), 'R', 
 		(select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'Investigators' and NMSPC_CD = 'KC-PD'), 200, 1, 'A', (select max(ID) from KREW_RSP_S), 1);

insert into KREW_PPL_FLW_MBR_S VALUES(NULL);
insert into KREW_RSP_S VALUES(NULL);
insert into KREW_PPL_FLW_MBR_T (PPL_FLW_MBR_ID, PPL_FLW_ID, MBR_TYP_CD, MBR_ID, PRIO, VER_NBR, ACTN_RQST_PLCY_CD, RSP_ID, FRC_ACTN)
 	values (CONCAT('KC', (select max(ID) from KREW_PPL_FLW_MBR_S)), CONCAT('KC', (select max(ID) from KREW_PPL_FLW_S)), 'R', 
 		(select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'Proposal Department Unit Heads' and NMSPC_CD = 'KC-PD'), 300, 1, 'F', (select max(ID) from KREW_RSP_S), 0);

insert into KREW_PPL_FLW_MBR_S VALUES(NULL);
insert into KREW_RSP_S VALUES(NULL);
insert into KREW_PPL_FLW_MBR_T (PPL_FLW_MBR_ID, PPL_FLW_ID, MBR_TYP_CD, MBR_ID, PRIO, VER_NBR, ACTN_RQST_PLCY_CD, RSP_ID, FRC_ACTN)
 	values (CONCAT('KC', (select max(ID) from KREW_PPL_FLW_MBR_S)), CONCAT('KC', (select max(ID) from KREW_PPL_FLW_S)), 'R', 
 		(select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'Proposal College Unit Heads' and NMSPC_CD = 'KC-PD'), 400, 1, 'F', (select max(ID) from KREW_RSP_S), 0);

insert into KREW_PPL_FLW_MBR_S VALUES(NULL);
insert into KREW_RSP_S VALUES(NULL);
insert into KREW_PPL_FLW_MBR_T (PPL_FLW_MBR_ID, PPL_FLW_ID, MBR_TYP_CD, MBR_ID, PRIO, VER_NBR, ACTN_RQST_PLCY_CD, RSP_ID, FRC_ACTN)
 	values (CONCAT('KC', (select max(ID) from KREW_PPL_FLW_MBR_S)), CONCAT('KC', (select max(ID) from KREW_PPL_FLW_S)), 'R', 
 		(select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'OSPApprover' and NMSPC_CD = 'KC-WKFLW'), 500, 1, 'F', (select max(ID) from KREW_RSP_S), 0);

insert into KRMS_RULE_S VALUES(NULL);
insert into KRMS_RULE_T (RULE_ID, NMSPC_CD, NM, DESC_TXT, TYP_ID, PROP_ID, ACTV, VER_NBR)
	values (CONCAT('KC', (select max(ID) from KRMS_RULE_S)), 'KC-PD', 'Proposal Development Standard Workflow', NULL, NULL, NULL, 'Y', 1);
	
insert into KRMS_ACTN_S VALUES(NULL);
insert into KRMS_ACTN_T (ACTN_ID, NM, NMSPC_CD, DESC_TXT, TYP_ID, RULE_ID, SEQ_NO, VER_NBR)
	values (CONCAT('KC', (select max(ID) from KRMS_ACTN_S)), 'Proposal Development Standard Workflow', 'KC-PD', NULL, 
		(select TYP_ID from KRMS_TYP_T where NM = 'Route to PeopleFlow' and NMSPC_CD = 'KR-RULE'), CONCAT('KC', (select max(ID) from KRMS_RULE_S)), 1, 1);
		
insert into KRMS_ACTN_ATTR_S VALUES(NULL);
insert into KRMS_ACTN_ATTR_T (ACTN_ATTR_DATA_ID, ACTN_ID, ATTR_DEFN_ID, ATTR_VAL, VER_NBR)
	values (CONCAT('KC', (select max(ID) from KRMS_ACTN_ATTR_S)), CONCAT('KC', (select max(ID) from KRMS_ACTN_S)),
		(select ATTR_DEFN_ID from KRMS_ATTR_DEFN_T where NM = 'peopleFlowId' and NMSPC_CD = 'KR-RULE'), CONCAT('KC', (select max(ID) from KREW_PPL_FLW_S)), 1);
		
insert into KRMS_ACTN_ATTR_S VALUES(NULL);
insert into KRMS_ACTN_ATTR_T (ACTN_ATTR_DATA_ID, ACTN_ID, ATTR_DEFN_ID, ATTR_VAL, VER_NBR)
	values (CONCAT('KC', (select max(ID) from KRMS_ACTN_ATTR_S)), CONCAT('KC', (select max(ID) from KRMS_ACTN_S)),
		(select ATTR_DEFN_ID from KRMS_ATTR_DEFN_T where NM = 'peopleFlowName' and NMSPC_CD = 'KR-RULE'), ' Proposal Development Standard Workflow', 1);
 		
insert into KRMS_AGENDA_S VALUES(NULL);
insert into KRMS_AGENDA_T (AGENDA_ID, NM, CNTXT_ID, INIT_AGENDA_ITM_ID, TYP_ID, ACTV, VER_NBR)
	values (CONCAT('KC', (select max(ID) from KRMS_AGENDA_S)), 'Proposal Development Standard Workflow', 'KC-PD-CONTEXT', CONCAT('KC', (select max(ID) from KRMS_RULE_S)), 
		(select TYP_ID from KRMS_TYP_T where NM = 'Unit Agenda' and NMSPC_CD = 'KC-KRMS'), 'N', 1);
		
insert into KRMS_AGENDA_ATTR_S VALUES(NULL);
insert into KRMS_AGENDA_ATTR_T (AGENDA_ATTR_ID, AGENDA_ID, ATTR_VAL, ATTR_DEFN_ID, VER_NBR)
	values (CONCAT('KC', (select max(ID) from KRMS_AGENDA_ATTR_S)), CONCAT('KC', (select max(ID) from KRMS_AGENDA_S)), '000001', 
		(select ATTR_DEFN_ID from KRMS_ATTR_DEFN_T where NM = 'Unit Number' and NMSPC_CD = 'KC-KRMS'), 1);
		
insert into KRMS_AGENDA_ITM_S VALUES(NULL);
insert into KRMS_AGENDA_ITM_T (AGENDA_ITM_ID, RULE_ID, SUB_AGENDA_ID, AGENDA_ID, VER_NBR, WHEN_TRUE, WHEN_FALSE, ALWAYS)
	values (CONCAT('KC', (select max(ID) from KRMS_AGENDA_ITM_S)), CONCAT('KC', (select max(ID) from KRMS_RULE_S)), NULL,
		CONCAT('KC', (select max(ID) from KRMS_AGENDA_S)), 1, NULL, NULL, NULL);


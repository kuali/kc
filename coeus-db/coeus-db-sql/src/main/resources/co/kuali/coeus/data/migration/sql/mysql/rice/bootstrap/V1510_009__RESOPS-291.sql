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

insert into KRMS_TERM_SPEC_S values (null);
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, NMSPC_CD, TYP, ACTV, VER_NBR, DESC_TXT)
	values (CONCAT('KC', (select max(ID) from KRMS_TERM_SPEC_S)), 'createTimestamp', 'KC-PD', 'java.sql.Timestamp', 'Y', 1, 'Create Timestamp');
	
insert into KRMS_CNTXT_VLD_TERM_SPEC_S values (null);
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ)
	values (CONCAT('KC', (select max(ID) from KRMS_CNTXT_VLD_TERM_SPEC_S)), 'KC-PD-CONTEXT', CONCAT('KC', (select max(ID) from KRMS_TERM_SPEC_S)), 'N');
	
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID)
	values (CONCAT('KC', (select max(ID) from KRMS_TERM_SPEC_S)), (select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD = 'KC-PD' and NM = 'Property'));
	
insert into KRMS_TERM_S values (null);
insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT)
	values (CONCAT('KC', (select max(ID) from KRMS_TERM_S)), CONCAT('KC', (select max(ID) from KRMS_TERM_SPEC_S)), 1, 'Create Timestamp');
	
insert into KRMS_PROP_S values (null);
insert into KRMS_PROP_T (PROP_ID, DESC_TXT, TYP_ID, DSCRM_TYP_CD, CMPND_OP_CD, RULE_ID, VER_NBR, CMPND_SEQ_NO)
	values (CONCAT('KC', (select max(ID) from KRMS_PROP_S)), 'Proposal Created After KEW Change', null, 'S', NULL, 
		(select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-PD' and NM = 'Proposal Development Standard Workflow'), 1, null);

insert into KRMS_PROP_PARM_S values (null);
insert into KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR)
	values (CONCAT('KC', (select max(ID) from KRMS_PROP_PARM_S)), CONCAT('KC', (select max(ID) from KRMS_PROP_S)),
		CONCAT('KC', (select max(ID) from KRMS_TERM_S)), 'T', 0, 1);

insert into KRMS_PROP_PARM_S values (null);
insert into KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR)
	values (CONCAT('KC', (select max(ID) from KRMS_PROP_PARM_S)), CONCAT('KC', (select max(ID) from KRMS_PROP_S)),
		'>', 'O', 2, 1);

insert into KRMS_PROP_PARM_S values (null);
insert into KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR)
	values (CONCAT('KC', (select max(ID) from KRMS_PROP_PARM_S)), CONCAT('KC', (select max(ID) from KRMS_PROP_S)),
		DATE_FORMAT(NOW(), '%m/%d/%Y %H:%i:%s'), 'C', 1, 1);
		
update KRMS_RULE_T set PROP_ID = CONCAT('KC', (select max(ID) from KRMS_PROP_S)) where NMSPC_CD = 'KC-PD' and NM = 'Proposal Development Standard Workflow';

delete from KREW_PPL_FLW_MBR_T where PPL_FLW_ID = (select PPL_FLW_ID from KREW_PPL_FLW_T where NMSPC_CD = 'KC-PD' and NM = 'Proposal Development Standard Workflow') and
	PRIO = 500 and MBR_ID = (select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'OSPApprover' and NMSPC_CD = 'KC-WKFLW');


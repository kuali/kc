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

insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, NMSPC_CD, TYP, ACTV, VER_NBR, DESC_TXT)
	values ('KC' || KRMS_TERM_SPEC_S.nextval, 'createTimestamp', 'KC-PD', 'java.sql.Timestamp', 'Y', 1, 'Create Timestamp');
	
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ)
	values ('KC' || KRMS_CNTXT_VLD_TERM_SPEC_S.nextval, 'KC-PD-CONTEXT', 'KC' || KRMS_TERM_SPEC_S.currval, 'N');
	
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID)
	values ('KC' || KRMS_TERM_SPEC_S.currval, (select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD = 'KC-PD' and NM = 'Property'));
	
insert into KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT)
	values ('KC' || KRMS_TERM_S.nextval, 'KC' || KRMS_TERM_SPEC_S.currval, 1, 'Create Timestamp');
	
insert into KRMS_PROP_T (PROP_ID, DESC_TXT, TYP_ID, DSCRM_TYP_CD, CMPND_OP_CD, RULE_ID, VER_NBR, CMPND_SEQ_NO)
	values ('KC' || KRMS_PROP_S.nextval, 'Proposal Created After KEW Change', null, 'S', NULL, 
		(select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-PD' and NM = 'Proposal Development Standard Workflow'), 1, null);

insert into KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR)
	values ('KC' || KRMS_PROP_PARM_S.nextval, 'KC' || KRMS_PROP_S.currval,
		'KC' || KRMS_TERM_S.currval, 'T', 0, 1);

insert into KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR)
	values ('KC' || KRMS_PROP_PARM_S.nextval, 'KC' || KRMS_PROP_S.currval,
		'>', 'O', 2, 1);

insert into KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR)
	values ('KC' || KRMS_PROP_PARM_S.nextval, 'KC' || KRMS_PROP_S.currval,
		TO_CHAR(SYSDATE,'mm/dd/yyyy HH24:MI:SS'), 'C', 1, 1);
		
update KRMS_RULE_T set PROP_ID = 'KC' || KRMS_PROP_S.currval where NMSPC_CD = 'KC-PD' and NM = 'Proposal Development Standard Workflow';

delete from KREW_PPL_FLW_MBR_T where PPL_FLW_ID = (select PPL_FLW_ID from KREW_PPL_FLW_T where NMSPC_CD = 'KC-PD' and NM = 'Proposal Development Standard Workflow') and
	PRIO = 500 and MBR_ID = (select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'OSPApprover' and NMSPC_CD = 'KC-WKFLW');


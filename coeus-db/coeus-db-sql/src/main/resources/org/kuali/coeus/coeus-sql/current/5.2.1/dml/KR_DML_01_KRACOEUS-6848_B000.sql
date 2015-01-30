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

-- investigatorCitizenshipTypeRule(DevelopmentProposal developmentProposal);    
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
values (CONCAT('KC', KRMS_FUNC_S.NEXTVAL),'investigatorCitizenshipTypeRule','Citizenship type of the Principal Investigator','java.lang.String',1,'Y',
(select TYP_ID from KRMS_TYP_T where NMSPC_CD = 'KC-PD' and NM = 'ProposalDevelopment Java Function Term Service'),'KC-PD')
/

insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_SPEC_S.NEXTVAL),'KC-PD',(select FUNC_ID from KRMS_FUNC_T where  NM='investigatorCitizenshipTypeRule' and NMSPC_CD='KC-PD'),
					'Check citizenship type of the Principal Investigator','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values (CONCAT('KC', KRMS_TERM_S.NEXTVAL),(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='investigatorCitizenshipTypeRule' and NMSPC_CD='KC-PD')),
			1,'Check citizenship type of the Principal Investigator')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values (CONCAT('KC', KRMS_CNTXT_VLD_TERM_SPEC_S.NEXTVAL),'KC-PD-CONTEXT',
					(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='investigatorCitizenshipTypeRule' and NMSPC_CD='KC-PD')),'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='investigatorCitizenshipTypeRule' and NMSPC_CD='KC-PD')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL),'KC-PD','Citizenship type of PI Resolver',
	(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
			(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='investigatorCitizenshipTypeRule' and NMSPC_CD='KC-PD')),'Y',1)
/

insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'DevelopmentProposal', 'Development Proposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
	(select FUNC_ID from KRMS_FUNC_T where  NM='investigatorCitizenshipTypeRule' and NMSPC_CD='KC-PD'), 1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'CitizenshipTypeToCheck', 'Citizenship Type', 'java.lang.String', 
	(select FUNC_ID from KRMS_FUNC_T where  NM='investigatorCitizenshipTypeRule' and NMSPC_CD='KC-PD'), 2)
/

insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL), (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Citizenship type of PI Resolver' and NMSPC_CD='KC-PD'), 
	'CitizenshipTypeToCheck', 1)
/

-- proposalCampusRule(DevelopmentProposal developmentProposal);    
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
values (CONCAT('KC', KRMS_FUNC_S.NEXTVAL),'proposalCampusRule','Check if the lead unit of the Proposal belong to campus','java.lang.String',1,'Y',
(select TYP_ID from KRMS_TYP_T where NMSPC_CD = 'KC-PD' and NM = 'ProposalDevelopment Java Function Term Service'),'KC-PD')
/

insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_SPEC_S.NEXTVAL),'KC-PD',(select FUNC_ID from KRMS_FUNC_T where  NM='proposalCampusRule' and NMSPC_CD='KC-PD'),
					'Check if the lead unit of the Proposal belong to campus','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values (CONCAT('KC', KRMS_TERM_S.NEXTVAL),(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='proposalCampusRule' and NMSPC_CD='KC-PD')),
			1,'Check if the lead unit of the Proposal belong to campus')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values (CONCAT('KC', KRMS_CNTXT_VLD_TERM_SPEC_S.NEXTVAL),'KC-PD-CONTEXT',
					(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='proposalCampusRule' and NMSPC_CD='KC-PD')),'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='proposalCampusRule' and NMSPC_CD='KC-PD')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL),'KC-PD','Proposal belong to campus Resolver',
	(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
			(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='proposalCampusRule' and NMSPC_CD='KC-PD')),'Y',1)
/

insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'DevelopmentProposal', 'Development Proposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
	(select FUNC_ID from KRMS_FUNC_T where  NM='proposalCampusRule' and NMSPC_CD='KC-PD'), 1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'CampusCode', 'Campus Code', 'java.lang.String', 
	(select FUNC_ID from KRMS_FUNC_T where  NM='proposalCampusRule' and NMSPC_CD='KC-PD'), 2)
/

insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL), (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Proposal belong to campus Resolver' and NMSPC_CD='KC-PD'), 
	'CampusCode', 1)
/


-- routedToOSPRule(DevelopmentProposal developmentProposal);    
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
values (CONCAT('KC', KRMS_FUNC_S.NEXTVAL),'routedToOSPRule','Check if the proposal has been approved or rejected by OSP','java.lang.String',1,'Y',
(select TYP_ID from KRMS_TYP_T where NMSPC_CD = 'KC-PD' and NM = 'ProposalDevelopment Java Function Term Service'),'KC-PD')
/

insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_SPEC_S.NEXTVAL),'KC-PD',(select FUNC_ID from KRMS_FUNC_T where  NM='routedToOSPRule' and NMSPC_CD='KC-PD'),
					'Check if the proposal has been approved or rejected by OSP','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values (CONCAT('KC', KRMS_TERM_S.NEXTVAL),(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='routedToOSPRule' and NMSPC_CD='KC-PD')),
			1,'Check if the proposal has been approved or rejected by OSP')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values (CONCAT('KC', KRMS_CNTXT_VLD_TERM_SPEC_S.NEXTVAL),'KC-PD-CONTEXT',
					(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='routedToOSPRule' and NMSPC_CD='KC-PD')),'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='routedToOSPRule' and NMSPC_CD='KC-PD')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL),'KC-PD','Proposal approved or rejected Resolver',
	(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
			(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='routedToOSPRule' and NMSPC_CD='KC-PD')),'Y',1)
/

insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'DevelopmentProposal', 'Development Proposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
	(select FUNC_ID from KRMS_FUNC_T where  NM='routedToOSPRule' and NMSPC_CD='KC-PD'), 1)
/

-- isUserProposalPI(DevelopmentProposal developmentProposal);    
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
values (CONCAT('KC', KRMS_FUNC_S.NEXTVAL),'isUserProposalPI','Check if the given user is the PI of the proposal','java.lang.String',1,'Y',
(select TYP_ID from KRMS_TYP_T where NMSPC_CD = 'KC-PD' and NM = 'ProposalDevelopment Java Function Term Service'),'KC-PD')
/

insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_SPEC_S.NEXTVAL),'KC-PD',(select FUNC_ID from KRMS_FUNC_T where  NM='isUserProposalPI' and NMSPC_CD='KC-PD'),
					'Check if the given user is the PI of the proposal','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values (CONCAT('KC', KRMS_TERM_S.NEXTVAL),(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isUserProposalPI' and NMSPC_CD='KC-PD')),
			1,'Check if the given user is the PI of the proposal')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values (CONCAT('KC', KRMS_CNTXT_VLD_TERM_SPEC_S.NEXTVAL),'KC-PD-CONTEXT',
					(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isUserProposalPI' and NMSPC_CD='KC-PD')),'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isUserProposalPI' and NMSPC_CD='KC-PD')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL),'KC-PD','Proposal PI Resolver',
	(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
			(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isUserProposalPI' and NMSPC_CD='KC-PD')),'Y',1)
/

insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'DevelopmentProposal', 'Development Proposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
	(select FUNC_ID from KRMS_FUNC_T where  NM='isUserProposalPI' and NMSPC_CD='KC-PD'), 1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'PrincipalId', 'Principal Id', 'java.lang.String', 
	(select FUNC_ID from KRMS_FUNC_T where  NM='isUserProposalPI' and NMSPC_CD='KC-PD'), 2)
/

insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL), (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Proposal PI Resolver' and NMSPC_CD='KC-PD'), 
	'PrincipalId', 1)
/


-- proposalUnitBelow(DevelopmentProposal developmentProposal);    
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
values (CONCAT('KC', KRMS_FUNC_S.NEXTVAL),'proposalUnitBelow','Check if any proposal unit is below a specified unit in the hierarchy','java.lang.String',1,'Y',
(select TYP_ID from KRMS_TYP_T where NMSPC_CD = 'KC-PD' and NM = 'ProposalDevelopment Java Function Term Service'),'KC-PD')
/

insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_SPEC_S.NEXTVAL),'KC-PD',(select FUNC_ID from KRMS_FUNC_T where  NM='proposalUnitBelow' and NMSPC_CD='KC-PD'),
					'Check if any proposal unit is below a specified unit in the hierarchy','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values (CONCAT('KC', KRMS_TERM_S.NEXTVAL),(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='proposalUnitBelow' and NMSPC_CD='KC-PD')),
			1,'Check if any proposal unit is below a specified unit in the hierarchy')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values (CONCAT('KC', KRMS_CNTXT_VLD_TERM_SPEC_S.NEXTVAL),'KC-PD-CONTEXT',
					(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='proposalUnitBelow' and NMSPC_CD='KC-PD')),'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='proposalUnitBelow' and NMSPC_CD='KC-PD')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL),'KC-PD','Proposal unit below specified unit Resolver',
	(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
			(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='proposalUnitBelow' and NMSPC_CD='KC-PD')),'Y',1)
/

insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'DevelopmentProposal', 'Development Proposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
	(select FUNC_ID from KRMS_FUNC_T where  NM='proposalUnitBelow' and NMSPC_CD='KC-PD'), 1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'UnitNumber', 'Unit Number', 'java.lang.String', 
	(select FUNC_ID from KRMS_FUNC_T where  NM='proposalUnitBelow' and NMSPC_CD='KC-PD'), 2)
/

insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL), (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Proposal unit below specified unit Resolver' and NMSPC_CD='KC-PD'), 
	'UnitNumber', 1)
/



-- usesRolodex(DevelopmentProposal developmentProposal);    
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
values (CONCAT('KC', KRMS_FUNC_S.NEXTVAL),'usesRolodex','Check if the proposal involves a specific rolodex id','java.lang.String',1,'Y',
(select TYP_ID from KRMS_TYP_T where NMSPC_CD = 'KC-PD' and NM = 'ProposalDevelopment Java Function Term Service'),'KC-PD')
/

insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_SPEC_S.NEXTVAL),'KC-PD',(select FUNC_ID from KRMS_FUNC_T where  NM='usesRolodex' and NMSPC_CD='KC-PD'),
					'Check if the proposal involves a specific rolodex id','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values (CONCAT('KC', KRMS_TERM_S.NEXTVAL),(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='usesRolodex' and NMSPC_CD='KC-PD')),
			1,'Check if the proposal involves a specific rolodex id')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values (CONCAT('KC', KRMS_CNTXT_VLD_TERM_SPEC_S.NEXTVAL),'KC-PD-CONTEXT',
					(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='usesRolodex' and NMSPC_CD='KC-PD')),'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='usesRolodex' and NMSPC_CD='KC-PD')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL),'KC-PD','Proposal involves specific rolodex id Resolver',
	(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
			(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='usesRolodex' and NMSPC_CD='KC-PD')),'Y',1)
/

insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'DevelopmentProposal', 'Development Proposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
	(select FUNC_ID from KRMS_FUNC_T where  NM='usesRolodex' and NMSPC_CD='KC-PD'), 1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'RolodexId', 'Rolodex Id', 'java.lang.Integer', 
	(select FUNC_ID from KRMS_FUNC_T where  NM='usesRolodex' and NMSPC_CD='KC-PD'), 2)
/

insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL), (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Proposal involves specific rolodex id Resolver' and NMSPC_CD='KC-PD'), 
	'RolodexId', 1)
/


-- competitionIdRule(DevelopmentProposal developmentProposal);    
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
values (CONCAT('KC', KRMS_FUNC_S.NEXTVAL),'competitionIdRule','Check s2s competition id','java.lang.String',1,'Y',
(select TYP_ID from KRMS_TYP_T where NMSPC_CD = 'KC-PD' and NM = 'ProposalDevelopment Java Function Term Service'),'KC-PD')
/

insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_SPEC_S.NEXTVAL),'KC-PD',(select FUNC_ID from KRMS_FUNC_T where  NM='competitionIdRule' and NMSPC_CD='KC-PD'),
					'Check s2s competition id','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values (CONCAT('KC', KRMS_TERM_S.NEXTVAL),(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='competitionIdRule' and NMSPC_CD='KC-PD')),
			1,'Check s2s competition id')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values (CONCAT('KC', KRMS_CNTXT_VLD_TERM_SPEC_S.NEXTVAL),'KC-PD-CONTEXT',
					(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='competitionIdRule' and NMSPC_CD='KC-PD')),'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='competitionIdRule' and NMSPC_CD='KC-PD')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL),'KC-PD','S2S Competition id Resolver',
	(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
			(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='competitionIdRule' and NMSPC_CD='KC-PD')),'Y',1)
/

insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'DevelopmentProposal', 'Development Proposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
	(select FUNC_ID from KRMS_FUNC_T where  NM='competitionIdRule' and NMSPC_CD='KC-PD'), 1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'CompetitionId', 'Competition Id', 'java.lang.String', 
	(select FUNC_ID from KRMS_FUNC_T where  NM='competitionIdRule' and NMSPC_CD='KC-PD'), 2)
/

insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL), (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='S2S Competition id Resolver' and NMSPC_CD='KC-PD'), 
	'CompetitionId', 1)
/


-- specialReviewDateRule(DevelopmentProposal developmentProposal);    
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
values (CONCAT('KC', KRMS_FUNC_S.NEXTVAL),'specialReviewDateRule','Check proposal Animal or Human Special review approval date is in the future','java.lang.String',1,'Y',
(select TYP_ID from KRMS_TYP_T where NMSPC_CD = 'KC-PD' and NM = 'ProposalDevelopment Java Function Term Service'),'KC-PD')
/

insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_SPEC_S.NEXTVAL),'KC-PD',(select FUNC_ID from KRMS_FUNC_T where  NM='specialReviewDateRule' and NMSPC_CD='KC-PD'),
					'Check proposal Animal or Human Special review approval date is in the future','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values (CONCAT('KC', KRMS_TERM_S.NEXTVAL),(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='specialReviewDateRule' and NMSPC_CD='KC-PD')),
			1,'Check proposal Animal or Human Special review approval date is in the future')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values (CONCAT('KC', KRMS_CNTXT_VLD_TERM_SPEC_S.NEXTVAL),'KC-PD-CONTEXT',
					(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='specialReviewDateRule' and NMSPC_CD='KC-PD')),'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='specialReviewDateRule' and NMSPC_CD='KC-PD')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL),'KC-PD','Proposal Animal or Human Special review approval date Resolver',
	(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
			(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='specialReviewDateRule' and NMSPC_CD='KC-PD')),'Y',1)
/

insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'DevelopmentProposal', 'Development Proposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
	(select FUNC_ID from KRMS_FUNC_T where  NM='specialReviewDateRule' and NMSPC_CD='KC-PD'), 1)
/

-- deadlineDateRule(DevelopmentProposal developmentProposal);    
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
values (CONCAT('KC', KRMS_FUNC_S.NEXTVAL),'deadlineDateRule','Check if the proposal deadline date is before a specified date','java.lang.String',1,'Y',
(select TYP_ID from KRMS_TYP_T where NMSPC_CD = 'KC-PD' and NM = 'ProposalDevelopment Java Function Term Service'),'KC-PD')
/

insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_SPEC_S.NEXTVAL),'KC-PD',(select FUNC_ID from KRMS_FUNC_T where  NM='deadlineDateRule' and NMSPC_CD='KC-PD'),
					'Check if the proposal deadline date is before a specified date','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values (CONCAT('KC', KRMS_TERM_S.NEXTVAL),(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='deadlineDateRule' and NMSPC_CD='KC-PD')),
			1,'Check if the proposal deadline date is before a specified date')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values (CONCAT('KC', KRMS_CNTXT_VLD_TERM_SPEC_S.NEXTVAL),'KC-PD-CONTEXT',
					(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='deadlineDateRule' and NMSPC_CD='KC-PD')),'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='deadlineDateRule' and NMSPC_CD='KC-PD')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL),'KC-PD','Proposal deadline date Resolver',
	(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
			(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='deadlineDateRule' and NMSPC_CD='KC-PD')),'Y',1)
/

insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'DevelopmentProposal', 'Development Proposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
	(select FUNC_ID from KRMS_FUNC_T where  NM='deadlineDateRule' and NMSPC_CD='KC-PD'), 1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'DeadlineDate', 'Deadline Date', 'java.lang.String', 
	(select FUNC_ID from KRMS_FUNC_T where  NM='deadlineDateRule' and NMSPC_CD='KC-PD'), 2)
/

insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL), (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Proposal deadline date Resolver' and NMSPC_CD='KC-PD'), 
	'DeadlineDate', 1)
/


-- routingSequenceRule(DevelopmentProposal developmentProposal);    
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
values (CONCAT('KC', KRMS_FUNC_S.NEXTVAL),'routingSequenceRule','Check if the proposal is being routed for the first time','java.lang.String',1,'Y',
(select TYP_ID from KRMS_TYP_T where NMSPC_CD = 'KC-PD' and NM = 'ProposalDevelopment Java Function Term Service'),'KC-PD')
/

insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_SPEC_S.NEXTVAL),'KC-PD',(select FUNC_ID from KRMS_FUNC_T where  NM='routingSequenceRule' and NMSPC_CD='KC-PD'),
					'Check if the proposal is being routed for the first time','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values (CONCAT('KC', KRMS_TERM_S.NEXTVAL),(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='routingSequenceRule' and NMSPC_CD='KC-PD')),
			1,'Check if the proposal is being routed for the first time')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values (CONCAT('KC', KRMS_CNTXT_VLD_TERM_SPEC_S.NEXTVAL),'KC-PD-CONTEXT',
					(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='routingSequenceRule' and NMSPC_CD='KC-PD')),'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='routingSequenceRule' and NMSPC_CD='KC-PD')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL),'KC-PD','Proposal routed for the first time Resolver',
	(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
			(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='routingSequenceRule' and NMSPC_CD='KC-PD')),'Y',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'DevelopmentProposal', 'Development Proposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
	(select FUNC_ID from KRMS_FUNC_T where  NM='routingSequenceRule' and NMSPC_CD='KC-PD'), 1)
/

-- piAppointmentTypeRule(DevelopmentProposal developmentProposal);    
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
values (CONCAT('KC', KRMS_FUNC_S.NEXTVAL),'piAppointmentTypeRule','PIs have PI status','java.lang.String',1,'Y',
(select TYP_ID from KRMS_TYP_T where NMSPC_CD = 'KC-PD' and NM = 'ProposalDevelopment Java Function Term Service'),'KC-PD')
/

insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_SPEC_S.NEXTVAL),'KC-PD',(select FUNC_ID from KRMS_FUNC_T where  NM='piAppointmentTypeRule' and NMSPC_CD='KC-PD'),
					'Check PIs have PI status','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values (CONCAT('KC', KRMS_TERM_S.NEXTVAL),(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='piAppointmentTypeRule' and NMSPC_CD='KC-PD')),
			1,'Check PIs have PI status')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values (CONCAT('KC', KRMS_CNTXT_VLD_TERM_SPEC_S.NEXTVAL),'KC-PD-CONTEXT',
					(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='piAppointmentTypeRule' and NMSPC_CD='KC-PD')),'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='piAppointmentTypeRule' and NMSPC_CD='KC-PD')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values (CONCAT('KC', KRMS_TERM_RSLVR_S.NEXTVAL),'KC-PD','PI Status Resolver',
	(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
			(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='piAppointmentTypeRule' and NMSPC_CD='KC-PD')),'Y',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID, NM, DESC_TXT, TYP, FUNC_ID, SEQ_NO)
	values (CONCAT('KC', KRMS_FUNC_PARM_S.NEXTVAL), 'DevelopmentProposal', 'Development Proposal', 'org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
	(select FUNC_ID from KRMS_FUNC_T where  NM='piAppointmentTypeRule' and NMSPC_CD='KC-PD'), 1)
/

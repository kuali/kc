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

DELIMITER /
update KRMS_TYP_T set SRVC_NM='{http://kc.kuali.org/core/v5_0}javaFunctionTermResolverTypeService' where SRVC_NM = 'functionTermResolverTypeService'
/
insert into KRMS_TYP_T values ('KC1005','Stored Function Term Resolver Type Service','KC-PD','{http://kc.kuali.org/core/v5_0}storedFunctionTermResolverTypeService','Y',1)
/
insert into KRMS_TYP_T values ('KC1006','ProposalDevelopment Java Function Term Service','KC-PD','propDevJavaFunctionKrmsTermService','Y',1)
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1001','IS_SPONSOR_FEDERAL','Is Sponsor Federal','java.lang.String',1,'Y','KC1005','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1002','specifiedGGForm','Specified GG Form','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1003','leadUnitRule','Lead Unit Rule','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1004','sponsorGroupRule','Sponsor Group Rule','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1005','proposalAwardTypeRule','Award Type Rule','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1006','s2sLeadershipRule','S2S Leadership Rule','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1007','checkProposalPiRule','Check Proposal Principal Investigator','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1008','checkProposalCoiRule','Check Proposal Co-Investigator','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1009','leadUnitBelowRule','Lead Unit Below In Hierarchy','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1010','specialReviewRule','Special Review Rule','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1011','proposalUnitRule','Unit in Proposal','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1012','sponsorTypeRule','Sponsor Type Rule','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1013','s2sAttachmentNarrativeRule','S2S Attachment Narrative Rule','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1014','s2sModularBudgetRule','S2S Modular Budget Rule','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1015','s2sFederalIdRule','S2S Federal ID Rule','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1016','mtdcDeviation','MTDC Deviation in Final Budget Version','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1017','s2sExemptionRule','S2S Exemption Rule','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1018','costElement','Cost Element Exists','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1019','activityTypeRule','Activity Type Rule','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1020','sponsor','Sponsor Rule','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1021','nonFacultyPi','Non Faculty Principal Investigator','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1022','attachmentFileNameRule','Attachment File Name Rule','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1023','mtdcDeviationInVersion','MTDC Deviation In Budget Version','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1024','proposalTypeRule','Proposal Type Rule','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1025','incompleteNarrativeRule','Incomplete Narrative Rule','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1026','multiplePI','Multiple PI check','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1027','s2sBudgetRule','checks for form inlusion','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1028','monitoredSponsorRule','checks if proposal is using a passed in sposnor hirerarchy','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1029','s2sResplanRule','checks for maximum attachments in narrative types.','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1030','grantsFormRule','checks for forms existence.','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1031','biosketchFileNameRule','checks for special characters in the biosketch file name.','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1032','ospAdminPropPersonRule','checks to see if an OSP administrator is a proposal person.','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1033','costElementVersionLimit','checks that a cost element limit has not been exceeded.','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1034','divisionCodeRule','checks the division code is null.','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1035','divisionCodeIsFellowship','checks if the proposal is a fellowship.','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1036','budgetSubawardOrganizationnameRule','checks subaward organization for special characters.','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1037','checkProposalPerson','Checks if the passes in person ID is a proposal person.','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1038','agencyProgramCodeNullRule','Checks if the passes in person ID is a proposal personrule to CHECK IF a proposal agency program code field  is  null.','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1039','allProposalsRule','Pointless rule, just returns true.','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1040','proposalLeadUnitInHierarchy','checks if the proposals lead unit is in the passed in unit hierarchy.','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1041','s2sSubawardRule','verifies only one set of unique forms.','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1042','proposalGrantsRule','verifies that there are grans.gov submissions.','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1043','narrativeTypeRule','verfies activity type code is specified.','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1044','s2s398CoverRule','verify PHS Cover letter narrative(39) is attached, must include s2s cover letter form','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1045','narrativeFileName','verify no special characters are used.','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1046','costElementInVersion','verify that a cost element is used in the specified version of the proposal','java.lang.String',1,'Y','KC1006','KC-PD')
/
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) values ('KC1047','investigatorKeyPersonCertificationRule','verify that each investigator and key person are certified.','java.lang.String',1,'Y','KC1006','KC-PD')
/
update KRMS_TERM_SPEC_T A set NM = (select FUNC_ID from KRMS_FUNC_T B where B.NM=A.NM) 
  where exists (select FUNC_ID from KRMS_FUNC_T C where C.NM=A.NM)
/
DELIMITER ;

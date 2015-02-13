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

INSERT INTO krns_parm_t (appl_nmspc_cd, nmspc_cd, parm_dtl_typ_cd,parm_nm,ver_nbr,parm_typ_cd,txt,parm_desc_txt,cons_cd,obj_id) 
   VALUES ('KC','KC-AWARD','Document','awardSponsorTemplateHelp',1,'HELP','default.htm?turl=Documents/sponsortemplate.htm','Sponsor Template Help','A',UUID())
/
INSERT INTO krns_parm_t (appl_nmspc_cd, nmspc_cd, parm_dtl_typ_cd,parm_nm,ver_nbr,parm_typ_cd,txt,parm_desc_txt,cons_cd,obj_id) 
   VALUES ('KC','KC-AWARD','Document','awardDetailsDatesHelp',1,'HELP','default.htm?turl=Documents/detailsdates.htm','Details and Dates Help','A',UUID())
/
INSERT INTO krns_parm_t (appl_nmspc_cd, nmspc_cd, parm_dtl_typ_cd,parm_nm,ver_nbr,parm_typ_cd,txt,parm_desc_txt,cons_cd,obj_id) 
   VALUES ('KC','KC-AWARD','Document','awardNotesHelp',1,'HELP','default.htm?turl=Documents/notes2.htm','Notes Help','A',UUID())
/
INSERT INTO krns_parm_t (appl_nmspc_cd, nmspc_cd, parm_dtl_typ_cd,parm_nm,ver_nbr,parm_typ_cd,txt,parm_desc_txt,cons_cd,obj_id) 
   VALUES ('KC','KC-IP','Document','ipNotesAttachmentsHelp',1,'HELP','default.htm?turl=Documents/notesandattachments1.htm','Notes and Attachments Help','A',UUID())
/
INSERT INTO krns_parm_t (appl_nmspc_cd, nmspc_cd, parm_dtl_typ_cd,parm_nm,ver_nbr,parm_typ_cd,txt,parm_desc_txt,cons_cd,obj_id) 
   VALUES ('KC','KC-IP','Document','ipDistCostSharingHelp',1,'HELP','default.htm?turl=Documents/costsharing1.htm','Cost Sharing Help','A',UUID())
/
INSERT INTO krns_parm_t (appl_nmspc_cd, nmspc_cd, parm_dtl_typ_cd,parm_nm,ver_nbr,parm_typ_cd,txt,parm_desc_txt,cons_cd,obj_id) 
   VALUES ('KC','KC-IP','Document','ipDistUnrecoveredFandAHelp',1,'HELP','default.htm?turl=Documents/unrecoveredfa1.htm','Unrecovered F and A Help','A',UUID())
/
INSERT INTO krns_parm_t (appl_nmspc_cd, nmspc_cd, parm_dtl_typ_cd,parm_nm,ver_nbr,parm_typ_cd,txt,parm_desc_txt,cons_cd,obj_id) 
   VALUES ('KC','KC-AWARD','Document','awardAddFundingProposalsHelp',1,'HELP','default.htm?turl=Documents/addfundingproposals.htm','Add Funding Proposals Help','A',UUID())
/
INSERT INTO krns_parm_t (appl_nmspc_cd, nmspc_cd, parm_dtl_typ_cd,parm_nm,ver_nbr,parm_typ_cd,txt,parm_desc_txt,cons_cd,obj_id) 
   VALUES ('KC','KC-AWARD','Document','awardCurrentFundingProposalsHelp',1,'HELP','default.htm?turl=Documents/currentfundingproposals.htm','Current Funding Proposals Help','A',UUID())
/
INSERT INTO krns_parm_t (appl_nmspc_cd, nmspc_cd, parm_dtl_typ_cd,parm_nm,ver_nbr,parm_typ_cd,txt,parm_desc_txt,cons_cd,obj_id) 
   VALUES ('KC','KC-AWARD','Document','awardBudgetOverviewHelp',1,'HELP','default.htm?turl=Documents/budgetoverview.htm','Budget Overview Help','A',UUID())
/
INSERT INTO krns_parm_t (appl_nmspc_cd, nmspc_cd, parm_dtl_typ_cd,parm_nm,ver_nbr,parm_typ_cd,txt,parm_desc_txt,cons_cd,obj_id) 
   VALUES ('KC','KC-PROTOCOL','Document','protocolAssignedRolesHelp',1,'HELP','default.htm?turl=Documents/assignedroles1.htm','Assigned Roles Help','A',UUID())
/
update krns_parm_t set txt='default.htm?turl=Documents/award1.htm' where parm_nm='awardHomeHelp'
/
update krns_parm_t set txt='default.htm?turl=Documents/award.htm' where parm_nm='awardHelpUrl'
/

COMMIT
/

DELIMITER ;

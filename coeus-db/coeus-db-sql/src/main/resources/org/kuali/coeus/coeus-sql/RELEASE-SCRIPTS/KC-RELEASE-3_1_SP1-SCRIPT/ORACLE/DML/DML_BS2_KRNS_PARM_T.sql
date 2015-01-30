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

-- Staging data
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KC','KC-AWARD','D','AWARD_ACTIVE_STATUS_CODES',1,'CONFG','1','Comma delimited list of award status codes considered active.','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KC','KC-AWARD','D','AWARD_COST_SHARING',1,'CONFG','009906','Numeric code from the Sponsor table that defines an award as being for Cost Sharing for sync descendants.','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KC','KC-AWARD','D','AWARD_FABRICATED_EQUIPMENT',1,'CONFG','2','Numeric code from Account Type table that defines an award as being for Fabricated Equipment for sync descendants.','A',SYS_GUID());

--INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
--  VALUES ('KC','A','KC-B',SYS_GUID(),'Broad F','D','BROAD_F_AND_A','CONFG','421502',1);

--INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
--  VALUES ('KC','A','KC-B',SYS_GUID(),'Subcontract F greater than 25K','D','SUBCONTRACTOR_F_AND_A_GT_25K','CONFG','420630',1);

--INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
--  VALUES ('KC','A','KC-B',SYS_GUID(),'Subcontract F less than 25K','D','SUBCONTRACTOR_F_AND_A_LT_25K','CONFG','420610',1);

UPDATE KRNS_PARM_T T SET T.PARM_DTL_TYP_CD = 'Document' where T.PARM_DTL_TYP_CD = 'D'; 
UPDATE KRNS_PARM_T T SET T.PARM_DTL_TYP_CD = 'All' where T.PARM_DTL_TYP_CD = 'A'; 
UPDATE KRNS_PARM_T T SET T.PARM_DTL_TYP_CD = 'Lookup' where T.PARM_DTL_TYP_CD = 'L';
UPDATE KRNS_PARM_T T SET T.APPL_NMSPC_CD = 'KC' WHERE NMSPC_CD LIKE 'KC%';

update KRNS_PARM_T
set txt = 'Modify Protocol:KC-PROTOCOL;Maintain Questionnaire Usage:KC-PD;Maintain Questionnaire Usage:KC-PROTOCOL;Edit Institutional Proposal:KC-IP'
where nmspc_cd = 'KC-QUESTIONNAIRE' and parm_nm = 'associateModuleQuestionnairePermission' and parm_dtl_typ_cd = 'P';

commit;

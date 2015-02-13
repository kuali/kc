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
--
--

-- add mapping for Preaward authorization help and correct link for Budget Versions help
INSERT INTO KRCR_PARM_T (APPL_ID, nmspc_cd, CMPNT_CD,parm_nm,ver_nbr,parm_typ_cd,VAL,parm_desc_txt,EVAL_OPRTR_CD,obj_id) 
   VALUES ('KC','KC-AWARD','Document','awardSponsorAuthHelp',1,'HELP','default.htm?turl=Documents/sponsorauthorization.htm','Sponsor Authorization Help','A',UUID())
/
INSERT INTO KRCR_PARM_T (APPL_ID, nmspc_cd, CMPNT_CD,parm_nm,ver_nbr,parm_typ_cd,VAL,parm_desc_txt,EVAL_OPRTR_CD,obj_id) 
   VALUES ('KC','KC-AWARD','Document','awardInstitutionalAuthHelp',1,'HELP','default.htm?turl=Documents/institutionalauthorization.htm','Institutional Authorization Help','A',UUID())
/
INSERT INTO KRCR_PARM_T (APPL_ID, nmspc_cd, CMPNT_CD,parm_nm,ver_nbr,parm_typ_cd,VAL,parm_desc_txt,EVAL_OPRTR_CD,obj_id) 
   VALUES ('KC','KC-AWARD','Document','awardBudgetVersionsHelp',1,'HELP','default.htm?turl=Documents/budgetversions3.htm','Budget Versions Help','A',UUID())
/

COMMIT
/

DELIMITER ;

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

INSERT INTO KRCR_PARM_T (APPL_ID, nmspc_cd, CMPNT_CD,parm_nm,ver_nbr,parm_typ_cd,VAL,parm_desc_txt,EVAL_OPRTR_CD,obj_id)
   VALUES ('KC','KC-AWARD','Document','option.warning.error.award.FnA.validation',1,'CONFG','E','This parameter is used when paramater enable.award.FnA.validation = 2.If this parameter value = "W" then warning is given after validation and if value= "E" then error is given after validation.','A',UUID())
/
update KRCR_PARM_T set parm_nm='enable.award.FnA.validation',parm_desc_txt='0=No validation of Award F&A or Benefits rates, 1=Award rates must be entered as valid rate pairs, 2=Single Award rates are validated against Valid Rates table' where parm_nm='mit.idc.validation.enabled'
/

DELIMITER ;

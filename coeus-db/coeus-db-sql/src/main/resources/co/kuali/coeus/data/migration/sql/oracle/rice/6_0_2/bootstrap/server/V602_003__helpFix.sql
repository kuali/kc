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

INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-IACUC','Document','iacucBatchCorrespondenceHelpUrl',SYS_GUID(),1,'HELP','default.htm?turl=Documents/iacucbatchcorrespondence1.htm','IACUC batch corresspondence help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-PROTOCOL','Document','irbBatchCorrespondenceHelpUrl',SYS_GUID(),1,'HELP','default.htm?turl=Documents/batchcorrespondence3.htm','IRB batch corresspondence help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-PROTOCOL','Document','irbCorrespondenceTemplateUrl',SYS_GUID(),1,'HELP','default.htm?turl=Documents/correspondencetemplate.htm','IRB correspondence template help','A','KC')
/
INSERT INTO KRCR_PARM_T (nmspc_cd, cmpnt_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
VALUES ('KC-IACUC','Document','iacucCorrespondenceTemplateUrl',SYS_GUID(),1,'HELP','default.htm?turl=Documents/iacuccorrespondencetemplate.htm','IACUC correspondence template help','A','KC')
/

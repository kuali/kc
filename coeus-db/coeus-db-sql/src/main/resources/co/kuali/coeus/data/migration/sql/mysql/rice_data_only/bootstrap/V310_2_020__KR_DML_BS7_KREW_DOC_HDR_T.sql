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

INSERT INTO KREW_DOC_HDR_T (DOC_HDR_ID,DOC_TYP_ID,DOC_HDR_STAT_CD,RTE_LVL,STAT_MDFN_DT,CRTE_DT,APRV_DT,FNL_DT,RTE_STAT_MDFN_DT,TTL,DOC_VER_NBR,INITR_PRNCPL_ID,VER_NBR,RTE_PRNCPL_ID,OBJ_ID) 
  VALUES (10138,(SELECT DOC_TYP_ID FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'QuestionnaireMaintenanceDocument' AND CUR_IND = 1),'F',0,STR_TO_DATE('20100708','%Y%m%d'),STR_TO_DATE('20100708','%Y%m%d'),STR_TO_DATE('20100708','%Y%m%d'),STR_TO_DATE('20100708','%Y%m%d'),STR_TO_DATE('20100708','%Y%m%d'),'NSF s2s form supporting questions',1,'admin',10,'admin',UUID());

INSERT INTO KREW_DOC_HDR_T (DOC_HDR_ID,DOC_TYP_ID,DOC_HDR_STAT_CD,RTE_LVL,STAT_MDFN_DT,CRTE_DT,APRV_DT,FNL_DT,RTE_STAT_MDFN_DT,TTL,DOC_VER_NBR,INITR_PRNCPL_ID,VER_NBR,RTE_PRNCPL_ID,OBJ_ID) 
  VALUES (10139,(SELECT DOC_TYP_ID FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'QuestionnaireMaintenanceDocument' AND CUR_IND = 1),'F',0,STR_TO_DATE('20100708','%Y%m%d'),STR_TO_DATE('20100708','%Y%m%d'),STR_TO_DATE('20100708','%Y%m%d'),STR_TO_DATE('20100708','%Y%m%d'),STR_TO_DATE('20100708','%Y%m%d'),'PHS Fellowship Supplemental Form V1-0 & 1-1',1,'admin',10,'admin',UUID());
  
INSERT INTO KREW_DOC_HDR_T (DOC_HDR_ID,DOC_TYP_ID,DOC_HDR_STAT_CD,RTE_LVL,STAT_MDFN_DT,CRTE_DT,APRV_DT,FNL_DT,RTE_STAT_MDFN_DT,TTL,DOC_VER_NBR,INITR_PRNCPL_ID,VER_NBR,RTE_PRNCPL_ID,OBJ_ID) 
  VALUES (10140,(SELECT DOC_TYP_ID FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'QuestionnaireMaintenanceDocument' AND CUR_IND = 1),'F',0,STR_TO_DATE('20100708','%Y%m%d'),STR_TO_DATE('20100708','%Y%m%d'),STR_TO_DATE('20100708','%Y%m%d'),STR_TO_DATE('20100708','%Y%m%d'),STR_TO_DATE('20100708','%Y%m%d'),'PHS398 Training Budget Form version 1-0',1,'admin',10,'admin',UUID());
  
INSERT INTO KREW_DOC_HDR_T (DOC_HDR_ID,DOC_TYP_ID,DOC_HDR_STAT_CD,RTE_LVL,STAT_MDFN_DT,CRTE_DT,APRV_DT,FNL_DT,RTE_STAT_MDFN_DT,TTL,DOC_VER_NBR,INITR_PRNCPL_ID,VER_NBR,RTE_PRNCPL_ID,OBJ_ID) 
  VALUES (10146,(SELECT DOC_TYP_ID FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'QuestionnaireMaintenanceDocument' AND CUR_IND = 1),'F',0,STR_TO_DATE('20100708','%Y%m%d'),STR_TO_DATE('20100708','%Y%m%d'),STR_TO_DATE('20100708','%Y%m%d'),STR_TO_DATE('20100708','%Y%m%d'),STR_TO_DATE('20100708','%Y%m%d'),'PHS Fellowship Supplemental Form V1-2',1,'admin',10,'admin',UUID());

COMMIT;

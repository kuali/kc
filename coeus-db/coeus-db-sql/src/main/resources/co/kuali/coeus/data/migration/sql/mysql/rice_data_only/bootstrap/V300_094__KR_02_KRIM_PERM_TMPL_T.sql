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

delimiter /
INSERT INTO KRIM_PERM_TMPL_ID_S VALUES (null)
/
INSERT INTO KRIM_PERM_TMPL_T (PERM_TMPL_ID,KIM_TYP_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_PERM_TMPL_ID_S),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),'KC-IDM','Question Permission','Modify/View Question','Y',UUID(),1)
/
INSERT INTO KRIM_PERM_TMPL_ID_S VALUES (null)
/
INSERT INTO KRIM_PERM_TMPL_T (PERM_TMPL_ID,KIM_TYP_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_PERM_TMPL_ID_S),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),'KC-IDM','Questionnaire Permission','Modify/View Questionnaire','Y',UUID(),1)
/
INSERT INTO KRIM_PERM_TMPL_ID_S VALUES (null)
/
INSERT INTO KRIM_PERM_TMPL_T (PERM_TMPL_ID,KIM_TYP_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_PERM_TMPL_ID_S),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),'KC-IDM','Route All Documents','Route All Documents','Y',UUID(),1)
/
INSERT INTO KRIM_PERM_TMPL_ID_S VALUES (null)
/
INSERT INTO KRIM_PERM_TMPL_T (PERM_TMPL_ID,KIM_TYP_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_PERM_TMPL_ID_S),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),'KC-IDM','Perform Any Document Action','Perform Any Document Action','Y',UUID(),1)
/
INSERT INTO KRIM_PERM_TMPL_ID_S VALUES (null)
/
INSERT INTO KRIM_PERM_TMPL_T (PERM_TMPL_ID,KIM_TYP_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_PERM_TMPL_ID_S),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),'KC-IDM','View All Documents','View All Documents','Y',UUID(),1)
/
INSERT INTO KRIM_PERM_TMPL_ID_S VALUES (null)
/
INSERT INTO KRIM_PERM_TMPL_T (PERM_TMPL_ID,KIM_TYP_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_PERM_TMPL_ID_S),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),'KC-IDM','Modify All Documents','Modify All Documents','Y',UUID(),1)
/
INSERT INTO KRIM_PERM_TMPL_ID_S VALUES (null)
/
INSERT INTO KRIM_PERM_TMPL_T (PERM_TMPL_ID,KIM_TYP_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_PERM_TMPL_ID_S),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),'KC-IDM','Perform Document Action','Perform Document Action','Y',UUID(),1)
/
INSERT INTO KRIM_PERM_TMPL_ID_S VALUES (null)
/
INSERT INTO KRIM_PERM_TMPL_T (PERM_TMPL_ID,KIM_TYP_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_PERM_TMPL_ID_S),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),'KC-IDM','View Document Section','View Document Section','Y',UUID(),1)
/
INSERT INTO KRIM_PERM_TMPL_ID_S VALUES (null)
/
INSERT INTO KRIM_PERM_TMPL_T (PERM_TMPL_ID,KIM_TYP_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_PERM_TMPL_ID_S),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),'KC-IDM','Edit Document Section','Edit Document Section','Y',UUID(),1)
/
delimiter ;

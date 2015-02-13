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

INSERT INTO KRCR_CMPNT_T (CMPNT_CD,NMSPC_CD,NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('D','KC-AB','Document','Y',UUID(),1)
/
INSERT INTO KRCR_CMPNT_T (CMPNT_CD,NMSPC_CD,NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('D','KC-AWARD','Document','Y',UUID(),1)
/
INSERT INTO KRCR_CMPNT_T (CMPNT_CD,NMSPC_CD,NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('A','KC-B','All','Y',UUID(),1)
/
INSERT INTO KRCR_CMPNT_T (CMPNT_CD,NMSPC_CD,NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('D','KC-B','Document','Y',UUID(),1)
/
INSERT INTO KRCR_CMPNT_T (CMPNT_CD,NMSPC_CD,NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('A','KC-GEN','All','Y',UUID(),1)
/
INSERT INTO KRCR_CMPNT_T (CMPNT_CD,NMSPC_CD,NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('D','KC-GEN','Document','Y',UUID(),1)
/
INSERT INTO KRCR_CMPNT_T (CMPNT_CD,NMSPC_CD,NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('DocumentType','KC-GEN','Cusatom Attribute Document Type','Y',UUID(),1)
/
INSERT INTO KRCR_CMPNT_T (CMPNT_CD,NMSPC_CD,NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('D','KC-IP','Document','Y',UUID(),1)
/
INSERT INTO KRCR_CMPNT_T (CMPNT_CD,NMSPC_CD,NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('D','KC-M','Document','Y',UUID(),1)
/
INSERT INTO KRCR_CMPNT_T (CMPNT_CD,NMSPC_CD,NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('A','KC-PD','All','Y',UUID(),1)
/
INSERT INTO KRCR_CMPNT_T (CMPNT_CD,NMSPC_CD,NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('D','KC-PD','Document','Y',UUID(),1)
/
INSERT INTO KRCR_CMPNT_T (CMPNT_CD,NMSPC_CD,NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('L','KC-PD','Lookup','Y',UUID(),1)
/
INSERT INTO KRCR_CMPNT_T (CMPNT_CD,NMSPC_CD,NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('D','KC-PROTOCOL','Document','Y',UUID(),1)
/
INSERT INTO KRCR_CMPNT_T (CMPNT_CD,NMSPC_CD,NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('P','KC-QUESTIONNAIRE','Permissions','Y',UUID(),1)
/
INSERT INTO KRCR_CMPNT_T (CMPNT_CD,NMSPC_CD,NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('D','KC-T','Document','Y',UUID(),1)
/

DELIMITER ;

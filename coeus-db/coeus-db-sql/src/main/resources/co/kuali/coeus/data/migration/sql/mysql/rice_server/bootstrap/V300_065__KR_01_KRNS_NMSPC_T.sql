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
INSERT INTO KRNS_NMSPC_T (NMSPC_CD,NM,APPL_NMSPC_CD,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('KC-AB','Award Budget',null,'Y',UUID(),1)
/
INSERT INTO KRNS_NMSPC_T (NMSPC_CD,NM,APPL_NMSPC_CD,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('KC-ADM','Kuali Coeus-Office of Sponsored Projects',null,'Y',UUID(),1)
/
INSERT INTO KRNS_NMSPC_T (NMSPC_CD,NM,APPL_NMSPC_CD,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('KC-AWARD','Award',null,'Y',UUID(),1)
/
INSERT INTO KRNS_NMSPC_T (NMSPC_CD,NM,APPL_NMSPC_CD,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('KC-B','Budget','RICE','Y',UUID(),1)
/
INSERT INTO KRNS_NMSPC_T (NMSPC_CD,NM,APPL_NMSPC_CD,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('KC-COMMITTEE','KC IRB Committee',null,'Y',UUID(),1)
/
INSERT INTO KRNS_NMSPC_T (NMSPC_CD,NM,APPL_NMSPC_CD,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('KC-GEN','General Kuali Coeus',null,'Y',UUID(),1)
/
INSERT INTO KRNS_NMSPC_T (NMSPC_CD,NM,APPL_NMSPC_CD,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('KC-IDM','Kuali Coeus KIM IDM & AuthZ',null,'Y',UUID(),1)
/
INSERT INTO KRNS_NMSPC_T (NMSPC_CD,NM,APPL_NMSPC_CD,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('KC-IP','Institutional Proposal',null,'Y',UUID(),1)
/
INSERT INTO KRNS_NMSPC_T (NMSPC_CD,NM,APPL_NMSPC_CD,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('KC-M','Maintenance','RICE','Y',UUID(),1)
/
INSERT INTO KRNS_NMSPC_T (NMSPC_CD,NM,APPL_NMSPC_CD,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('KC-PD','Proposal Development','RICE','Y',UUID(),1)
/
INSERT INTO KRNS_NMSPC_T (NMSPC_CD,NM,APPL_NMSPC_CD,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('KC-PROTOCOL','KC IRB Protocol',null,'Y',UUID(),1)
/
INSERT INTO KRNS_NMSPC_T (NMSPC_CD,NM,APPL_NMSPC_CD,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('KC-QUESTIONNAIRE','KC Questionnaire',null,'Y',UUID(),1)
/
INSERT INTO KRNS_NMSPC_T (NMSPC_CD,NM,APPL_NMSPC_CD,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('KC-SYS','Kuali Coeus System',null,'Y',UUID(),1)
/
INSERT INTO KRNS_NMSPC_T (NMSPC_CD,NM,APPL_NMSPC_CD,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('KC-T','Time And Money',null,'Y',UUID(),1)
/
INSERT INTO KRNS_NMSPC_T (NMSPC_CD,NM,APPL_NMSPC_CD,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('KC-UNT','Kuali Coeus - Department',null,'Y',UUID(),1)
/
INSERT INTO KRNS_NMSPC_T (NMSPC_CD,NM,APPL_NMSPC_CD,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ('KC-WKFLW','KC Workflow Infrastructure',null,'Y',UUID(),1)
/
delimiter ;

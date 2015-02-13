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
TRUNCATE TABLE BUDGET_CATEGORY_MAPS
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NIH_PRINTING','04','Subcontract','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NIH_PRINTING','39','Other Direct Costs','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NIH_PRINTING','40','Alterations and Renovations','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NIH_PRINTING','41','Patient Care','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NIH_PRINTING','42','Purchased Equipment','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NIH_PRINTING','43','Materials and Supplies','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NIH_PRINTING','73','Travel','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NIH_PRINTING','81','Consultant Costs','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NIH_PRINTING','90','Outpatient Services','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','01','SENIOR PERSONNEL','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','01-Graduates','GRADUATE STUDENTS','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','01-Other','OTHER','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','01-Other Profs','OTHER PROFESSIONALS','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','01-PostDocs','POST DOCTORAL','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','01-Secretarial','SECRETARIAL','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','01-Undergrads','UNDERGRADUATE STUDENTS','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','04','Subcontract','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','39','Other Direct Costs','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','42','Purchased Equipment','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','43','Materials and Supplies','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','73','Domestic Travel','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','74','Foreign Travel','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','75','Participant Stipends','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','77','Participant Travel','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','78','Participant Other','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','80','Publication Costs','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','81','Consultant Costs','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','82','Computer Services','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_194','88','Cost Sharing','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','01','Senior Personnel','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','01-Graduates','Graduate Students','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','01-Other','Other Personnel','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','01-Other Profs','Other Professionals','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','01-PostDocs','Post Docs','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','01-Secretarial','Secretarial - Clerical','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','01-Undergrads','Undergraduate Students','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','04','Subcontract','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','39','Other Direct Costs','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','42','Purchased Equipment','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','43','Materials and Supplies','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','73','Domestic Travel','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','74','Foreign Travel','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','75','Participant Stipends','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','77','Participant Travel','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','78','Participant Other','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','79','Participant Subsistence','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','80','Publication Costs','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','81','Consultant Costs','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NSF_PRINTING','82','Computer Services','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','01','Senior Personnel','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','01-Graduates','Graduate Students','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','01-Other','Other Personnel','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','01-Other Profs','Other Professionals','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','01-PostDocs','Post Doctoral Associates','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','01-Secretarial','Secretarial / Clerical','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','01-Undergrads','Undergraduate Students','P                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','04','Subcontract','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','39','Other Direct Costs','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','40','Alterations','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','42','Purchased Equipment','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','43','Materials and Supplies','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','45','Equipment Rental','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','73','Domestic Travel','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','74','Foreign Travel','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','75','Participant Stipends','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','76','Participant Tuition','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','77','Participant Travel','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','78','Participant Other','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','79','Participant Subsistence','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','80','Publication Costs','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','81','Consultant Costs','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME,TARGET_CATEGORY_CODE,DESCRIPTION,CATEGORY_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('S2S','82','Computer Services','O                                                                                                                                                                                                       ','admin',NOW(),UUID(),1)
/
delimiter ;

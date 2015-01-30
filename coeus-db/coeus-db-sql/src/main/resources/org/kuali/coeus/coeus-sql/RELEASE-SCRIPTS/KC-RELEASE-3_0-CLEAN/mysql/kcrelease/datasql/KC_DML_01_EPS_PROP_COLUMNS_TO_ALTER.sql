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
TRUNCATE TABLE EPS_PROP_COLUMNS_TO_ALTER
/
INSERT INTO EPS_PROP_COLUMNS_TO_ALTER (COLUMN_NAME,COLUMN_LABEL,DATA_TYPE,DATA_LENGTH,HAS_LOOKUP,LOOKUP_ARGUMENT,LOOKUP_RETURN,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('ACTIVITY_TYPE_CODE','Activity Type','STRING',3,'Y','org.kuali.kra.proposaldevelopment.bo.ActivityType','description','admin',NOW(),UUID(),1)
/
INSERT INTO EPS_PROP_COLUMNS_TO_ALTER (COLUMN_NAME,COLUMN_LABEL,DATA_TYPE,DATA_LENGTH,HAS_LOOKUP,LOOKUP_ARGUMENT,LOOKUP_RETURN,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('DEADLINE_DATE','Sponsor Deadline Date','DATE',10,'N',null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO EPS_PROP_COLUMNS_TO_ALTER (COLUMN_NAME,COLUMN_LABEL,DATA_TYPE,DATA_LENGTH,HAS_LOOKUP,LOOKUP_ARGUMENT,LOOKUP_RETURN,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('DEADLINE_TYPE','Deadline Type','STRING',1,'Y','org.kuali.kra.proposaldevelopment.bo.DeadlineType','description','admin',NOW(),UUID(),1)
/
INSERT INTO EPS_PROP_COLUMNS_TO_ALTER (COLUMN_NAME,COLUMN_LABEL,DATA_TYPE,DATA_LENGTH,HAS_LOOKUP,LOOKUP_ARGUMENT,LOOKUP_RETURN,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('MAILING_ADDRESS_ID','Mailing Address','NUMBER',22,'Y','org.kuali.kra.bo.Rolodex','organization','admin',NOW(),UUID(),1)
/
INSERT INTO EPS_PROP_COLUMNS_TO_ALTER (COLUMN_NAME,COLUMN_LABEL,DATA_TYPE,DATA_LENGTH,HAS_LOOKUP,LOOKUP_ARGUMENT,LOOKUP_RETURN,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NOTICE_OF_OPPORTUNITY_CODE','Notice of Opportunity','NUMBER',3,'Y','org.kuali.kra.bo.NoticeOfOpportunity','description','admin',NOW(),UUID(),1)
/
INSERT INTO EPS_PROP_COLUMNS_TO_ALTER (COLUMN_NAME,COLUMN_LABEL,DATA_TYPE,DATA_LENGTH,HAS_LOOKUP,LOOKUP_ARGUMENT,LOOKUP_RETURN,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('PROGRAM_ANNOUNCEMENT_NUMBER','Program Number','STRING',50,'N',null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO EPS_PROP_COLUMNS_TO_ALTER (COLUMN_NAME,COLUMN_LABEL,DATA_TYPE,DATA_LENGTH,HAS_LOOKUP,LOOKUP_ARGUMENT,LOOKUP_RETURN,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('PROGRAM_ANNOUNCEMENT_TITLE','Program Title','STRING',255,'N',null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO EPS_PROP_COLUMNS_TO_ALTER (COLUMN_NAME,COLUMN_LABEL,DATA_TYPE,DATA_LENGTH,HAS_LOOKUP,LOOKUP_ARGUMENT,LOOKUP_RETURN,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('PROPOSAL_TYPE_CODE','Proposal Type','NUMBER',3,'Y','org.kuali.kra.proposaldevelopment.bo.ProposalType','description','admin',NOW(),UUID(),1)
/
INSERT INTO EPS_PROP_COLUMNS_TO_ALTER (COLUMN_NAME,COLUMN_LABEL,DATA_TYPE,DATA_LENGTH,HAS_LOOKUP,LOOKUP_ARGUMENT,LOOKUP_RETURN,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('TITLE','Title','STRING',150,'N',null,null,'admin',NOW(),UUID(),1)
/
delimiter ;

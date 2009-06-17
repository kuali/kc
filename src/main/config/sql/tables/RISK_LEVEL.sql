/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* Table Script */ 
CREATE TABLE RISK_LEVEL ( 
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL, 
	RISK_LEVEL_CODE VARCHAR2(3) NOT NULL, 
	DESCRIPTION VARCHAR2(200), 
	UPDATE_TIMESTAMP DATE, 
	UPDATE_USER VARCHAR2(60));
/* Primary Key Constraint */ 
ALTER TABLE RISK_LEVEL 
ADD CONSTRAINT PK_RISK_LEVEL 
PRIMARY KEY (RISK_LEVEL_CODE);
/* Foreign Key Constraint(s) */ 

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
CREATE TABLE VALID_RATES ( 
	VALID_RATES_ID NUMBER(12,0) NOT NULL, 
	ON_CAMPUS_RATE NUMBER(5,2) NOT NULL, 
	OFF_CAMPUS_RATE NUMBER(5,2) NOT NULL, 
	RATE_CLASS_TYPE VARCHAR2(1) NOT NULL, 
	ADJUSTMENT_KEY VARCHAR2(6) NOT NULL, 
	OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL, 
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
	UPDATE_TIMESTAMP DATE NOT NULL, 
	UPDATE_USER VARCHAR2(60) NOT NULL);

/* Primary Key Constraint */ 
ALTER TABLE VALID_RATES 
ADD CONSTRAINT PK_VALID_RATES 
PRIMARY KEY (VALID_RATES_ID);

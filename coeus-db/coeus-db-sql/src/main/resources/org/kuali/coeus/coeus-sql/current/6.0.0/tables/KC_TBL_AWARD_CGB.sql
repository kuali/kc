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

create table AWARD_CGB (
	AWARD_ID number(22,0) not null,
	AWARD_NUMBER varchar2(12) not null,
	SEQUENCE_NUMBER number(4,0) not null,
	ADDITIONAL_FORMS_REQ char(1),
	MIN_INVOICE_AMT number(19,2),
	AUTO_APPROVE_INVOICE char(1),
	INVOICING_OPTION varchar2(120),
	STOP_WORK char(1),
	DUNNING_CAMPAIGN_ID varchar2(4),
	LAST_BILLED_DATE date,
	PREV_LAST_BILLED_DATE date,
	FINAL_BILL char(1) default 'N' not null,
	AMT_TO_DRAW number(19,2),
	LETTER_OF_CREDIT_REVIEW char(1) default 'N' not null,
	INVOICE_DOCUMENT_STATUS varchar2(45),
	LOC_CREATION_TYPE varchar2(45),
	SUSPEND_INVOICING char(1) default 'N' not null,
	UPDATE_TIMESTAMP date not null,
	UPDATE_USER varchar2(60) not null,
 	VER_NBR number(8,0) default 1 not null,
 	OBJ_ID varchar2(36) not null
)
/

ALTER TABLE AWARD_CGB
   ADD CONSTRAINT AWARD_CGBP1
PRIMARY KEY (AWARD_ID)
/

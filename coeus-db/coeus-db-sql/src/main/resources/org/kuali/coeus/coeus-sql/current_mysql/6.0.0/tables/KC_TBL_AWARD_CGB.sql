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

create table AWARD_CGB (
AWARD_ID decimal(22,0) primary key,
AWARD_NUMBER varchar(12) not null,
SEQUENCE_NUMBER decimal(4,0) not null,
ADDITIONAL_FORMS_REQ char(1),
MIN_INVOICE_AMT decimal(19,2),
AUTO_APPROVE_INVOICE char(1),
INVOICING_OPTION varchar(120),
STOP_WORK char(1),
DUNNING_CAMPAIGN_ID varchar(4),
LAST_BILLED_DATE date,
PREV_LAST_BILLED_DATE date,
FINAL_BILL char(1) not null default 'N',
AMT_TO_DRAW decimal(19,2),
LETTER_OF_CREDIT_REVIEW char(1) default 'N',
INVOICE_DOCUMENT_STATUS varchar(45),
LOC_CREATION_TYPE varchar(45),
SUSPEND_INVOICING char(1) default 'N',
UPDATE_TIMESTAMP datetime not null,
UPDATE_USER varchar(60) not null,
VER_NBR decimal(8,0) default 1 not null,
OBJ_ID varchar(36) not null
)
/

DELIMITER ;

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

alter table BUDGET add PARENT_DOCUMENT_TYPE_CODE varchar(10)
/

update BUDGET set PARENT_DOCUMENT_TYPE_CODE = (select PARENT_DOCUMENT_TYPE_CODE from BUDGET_DOCUMENT where BUDGET.DOCUMENT_NUMBER = BUDGET_DOCUMENT.DOCUMENT_NUMBER)
/

alter table BUDGET modify PARENT_DOCUMENT_TYPE_CODE varchar(10) not null
/

alter table BUDGET modify DOCUMENT_NUMBER varchar(40) null
/

alter table BUDGET add BUDGET_NAME varchar(255)
/

update BUDGET set BUDGET_NAME = (select FDOC_DESC from KRNS_DOC_HDR_T where BUDGET.DOCUMENT_NUMBER = KRNS_DOC_HDR_T.DOC_HDR_ID)
/

ALTER TABLE BUDGET
ADD (CREATE_TIMESTAMP DATETIME )
/

ALTER TABLE BUDGET
ADD (CREATE_USER VARCHAR(60) )
/

DELIMITER ;

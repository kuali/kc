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

CREATE TABLE SUBAWARD_TEMPLATE_INFO  
(
  SUBAWARD_ID                          NUMBER(12,0) NOT NULL,
  SUBAWARD_CODE                        VARCHAR2(20 BYTE) NOT NULL, 
  SEQUENCE_NUMBER                      NUMBER(4,0) NOT NULL, 
  SOW_OR_SUB_PROPOSAL_BUDGET           VARCHAR2(1 BYTE),
  SUB_PROPOSAL_DATE                    DATE,
  INVOICE_OR_PAYMENT_CONTACT           NUMBER(3,0),
  FINAL_STMT_OF_COSTS_CONTACT          NUMBER(3,0),
  CHANGE_REQUESTS_CONTACT	           NUMBER(3,0),
  TERMINATION_CONTACT	               NUMBER(3,0),
  NO_COST_EXTENSION_CONTACT	           NUMBER(3,0),
  PERF_SITE_DIFF_FROM_ORG_ADDR         VARCHAR2(1 BYTE),
  PERF_SITE_SAME_AS_SUB_PI_ADDR	       VARCHAR2(1 BYTE),
  SUB_REGISTERED_IN_CCR                VARCHAR2(1 BYTE),
  SUB_EXEMPT_FROM_REPORTING_COMP	   VARCHAR2(1 BYTE),
  PARENT_DUNS_NUMBER                   VARCHAR2(20 BYTE),
  PARENT_CONGRESSIONAL_DISTRICT	       VARCHAR2(20 BYTE),
  EXEMPT_FROM_RPRTG_EXEC_COMP	       VARCHAR2(1 BYTE),
  COPYRIGHT_TYPE 	                   NUMBER(3,0),
  AUTOMATIC_CARRY_FORWARD 	           VARCHAR2(1 BYTE),
  CARRY_FORWARD_REQUESTS_SENT_TO 	   NUMBER(3,0),
  TREATMENT_PRGM_INCOME_ADDITIVE 	   VARCHAR2(1 BYTE),
  APPLICABLE_PROGRAM_REGULATIONS 	   VARCHAR2(50 BYTE),
  APPLICABLE_PROGRAM_REGS_DATE 	       DATE,
  UPDATE_TIMESTAMP                     DATE NOT NULL, 
  UPDATE_USER VARCHAR2(60)             NOT NULL
)
/

ALTER TABLE SUBAWARD_TEMPLATE_INFO 
ADD CONSTRAINT SUBAWARD_ID_PK 
PRIMARY KEY (SUBAWARD_ID) 
/

ALTER TABLE SUBAWARD_TEMPLATE_INFO 
ADD CONSTRAINT SUBAWARD_ID_FK 
FOREIGN KEY (SUBAWARD_ID) 
REFERENCES SUBAWARD (SUBAWARD_ID) 
/

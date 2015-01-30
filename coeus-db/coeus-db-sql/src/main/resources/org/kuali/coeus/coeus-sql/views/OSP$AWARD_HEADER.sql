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

CREATE OR REPLACE VIEW OSP$AWARD_HEADER
   ("MIT_AWARD_NUMBER", 
	"SEQUENCE_NUMBER", 
	"PROPOSAL_NUMBER", 
	"TITLE", 
	"AWARD_TYPE_CODE", 
	"SPECIAL_EB_RATE_OFF_CAMPUS", 
	"SPECIAL_EB_RATE_ON_CAMPUS", 
	"PRE_AWARD_AUTHORIZED_AMOUNT", 
	"PRE_AWARD_EFFECTIVE_DATE", 
	"CFDA_NUMBER", 
	"DFAFS_NUMBER", 
	"SUB_PLAN_FLAG", 
	"PROCUREMENT_PRIORITY_CODE", 
	"PRIME_SPONSOR_CODE", 
	"NON_COMPETING_CONT_PRPSL_DUE", 
	"COMPETING_RENEWAL_PRPSL_DUE", 
	"BASIS_OF_PAYMENT_CODE", 
	"METHOD_OF_PAYMENT_CODE", 
	"PAYMENT_INVOICE_FREQ_CODE", 
	"INVOICE_NUMBER_OF_COPIES", 
	"FINAL_INVOICE_DUE", 
	"ACTIVITY_TYPE_CODE", 
	"ACCOUNT_TYPE_CODE", 
	"UPDATE_TIMESTAMP", 
	"UPDATE_USER") AS
select 
AWARD_NUMBER, 
SEQUENCE_NUMBER, 
PROPOSAL_NUMBER, 
TITLE,
AWARD_TYPE_CODE,
SPECIAL_EB_RATE_OFF_CAMPUS, 
SPECIAL_EB_RATE_ON_CAMPUS,
PRE_AWARD_AUTHORIZED_AMOUNT, 
PRE_AWARD_EFFECTIVE_DATE, 
CFDA_NUMBER, 
DFAFS_NUMBER, 
SUB_PLAN_FLAG,
PROCUREMENT_PRIORITY_CODE, 
PRIME_SPONSOR_CODE,
NON_COMPETING_CONT_PRPSL_DUE, 
COMPETING_RENEWAL_PRPSL_DUE, 
BASIS_OF_PAYMENT_CODE, 
METHOD_OF_PAYMENT_CODE,
PAYMENT_INVOICE_FREQ_CODE, 
INVOICE_NUMBER_OF_COPIES, 
FINAL_INVOICE_DUE, 
ACTIVITY_TYPE_CODE, 
ACCOUNT_TYPE_CODE, 
UPDATE_TIMESTAMP, 
UPDATE_USER
FROM AWARD;

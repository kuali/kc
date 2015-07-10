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

-- Bootstrap data for Valid Special Review Approval ID update --

ALTER TABLE VALID_SP_REV_APPROVAL 
DROP CONSTRAINT VALID_SP_REV_APPROVALP1;

ALTER TABLE VALID_SP_REV_APPROVAL 
ADD (VALID_SP_REV_APPROVAL_ID NUMBER(12, 0));

UPDATE VALID_SP_REV_APPROVAL sra1 
SET VALID_SP_REV_APPROVAL_ID = (select VALID_SP_REV_APPROVAL_ID from (select min(rownum) VALID_SP_REV_APPROVAL_ID, SPECIAL_REVIEW_CODE, APPROVAL_TYPE_CODE 
from valid_sp_rev_approval group by SPECIAL_REVIEW_CODE, approval_type_code) sra2 
where sra2.approval_type_code = sra1.approval_type_code and sra2.special_review_code = sra1.special_review_code);

COMMIT;

ALTER TABLE VALID_SP_REV_APPROVAL 
MODIFY (VALID_SP_REV_APPROVAL_ID NUMBER(12, 0) NOT NULL);

ALTER TABLE VALID_SP_REV_APPROVAL
ADD CONSTRAINT VALID_SP_REV_APPROVALP1 PRIMARY KEY (VALID_SP_REV_APPROVAL_ID);

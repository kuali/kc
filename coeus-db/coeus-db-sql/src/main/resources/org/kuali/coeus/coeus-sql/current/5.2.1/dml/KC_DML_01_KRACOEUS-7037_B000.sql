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

INSERT INTO SUBCONTRACT_COST_TYPE (COST_TYPE_CODE,DESCRIPTION,OBJ_ID,VER_NBR,UPDATE_TIMESTAMP,UPDATE_USER)
VALUES ('1','Cost Reimbursement',SYS_GUID(),1,SYSDATE,'admin')
/
INSERT INTO SUBCONTRACT_COST_TYPE (COST_TYPE_CODE,DESCRIPTION,OBJ_ID,VER_NBR,UPDATE_TIMESTAMP,UPDATE_USER)
VALUES ('2','Firm Fixed Price',SYS_GUID(),1,SYSDATE,'admin')
/
INSERT INTO SUBCONTRACT_COST_TYPE (COST_TYPE_CODE,DESCRIPTION,OBJ_ID,VER_NBR,UPDATE_TIMESTAMP,UPDATE_USER)
VALUES ('3','Time and Materials',SYS_GUID(),1,SYSDATE,'admin')
/
INSERT INTO SUBCONTRACT_COST_TYPE (COST_TYPE_CODE,DESCRIPTION,OBJ_ID,VER_NBR,UPDATE_TIMESTAMP,UPDATE_USER)
VALUES ('4','Labor Hours',SYS_GUID(),1,SYSDATE,'admin')
/
INSERT INTO SUBCONTRACT_COST_TYPE (COST_TYPE_CODE,DESCRIPTION,OBJ_ID,VER_NBR,UPDATE_TIMESTAMP,UPDATE_USER)
VALUES ('5','Cost Plus Fixed Fee',SYS_GUID(),1,SYSDATE,'admin')
/
INSERT INTO SUBCONTRACT_COST_TYPE (COST_TYPE_CODE,DESCRIPTION,OBJ_ID,VER_NBR,UPDATE_TIMESTAMP,UPDATE_USER)
VALUES ('6','Billing Agreement',SYS_GUID(),1,SYSDATE,'admin')
/

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

INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) 
VALUES ( 2, 'Unusual requirement', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) 
VALUES ( 16, 'Substantive subcontracting of SOW requires prior approval', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR  ) 
VALUES ( 17, 'Approval of subcontracts in excess of $2,500 not included in approved budget', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ,OBJ_ID,VER_NBR ) 
VALUES ( 18, 'Approval authority delegated to ACO', sysdate, 'admin',SYS_GUID(),1 ) 
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR  ) 
VALUES ( 3, 'No subcontracting restrictions', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR  ) 
VALUES ( 4, 'Approval of all subcontracting of SOW required', sysdate, 'admin',SYS_GUID(),1 ) 
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ,OBJ_ID,VER_NBR ) 
VALUES ( 19, 'Prior consent required for all unbudgeted subcontracts over $5,000', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ,OBJ_ID,VER_NBR ) 
VALUES ( 20, 'Prior consent required for all unbudgeted sole source subcontracts over $25,000', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ,OBJ_ID,VER_NBR ) 
VALUES ( 21, 'Inclusion of a subcontract in the approved budget constitute prior approval if the subcontractors name and subcontract amount are identified in the budget.', sysdate, 'admin',SYS_GUID(),1 ) 
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR  ) 
VALUES ( 1, 'No clause', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR  ) 
VALUES ( 5, 'FAR 52.244-2, Subcontracts (Cost-reimbursement,...)', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR  ) 
VALUES ( 6, 'FAR 52.244-2, Alt 1, Subcontracts (Cost-reimbursement,....)', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR  ) 
VALUES ( 7, 'NASA 667 required for all purchases over $25,000', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR  ) 
VALUES ( 8, 'Authorized use of GSE supply sources', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ,OBJ_ID,VER_NBR ) 
VALUES ( 9, 'Prior consent required for all unbudgeted subcontracts over $25,000', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR  ) 
VALUES ( 10, 'Subcontract approval delegated to ONRRR', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR  ) 
VALUES ( 11, 'Advanced notification required prior to issuance of subcontracts of SOW', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ,OBJ_ID,VER_NBR ) 
VALUES ( 12, 'NASA 667 required for all purchases over $10,000', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR  ) 
VALUES ( 13, 'Approval of all cost reimbursement, time and material and labor hours subcontracts over $10,000 required', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR  ) 
VALUES ( 14, 'Subcontracts may include cost-sharing requirements', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ,OBJ_ID,VER_NBR ) 
VALUES ( 15, 'Approval of subcontracts over $100,000 requires special documentation', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ,OBJ_ID,VER_NBR ) 
VALUES ( 22, 'Advance notification of all cost reimbursement, time and material and labor hours subcontracts over $10,000 required.', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ,OBJ_ID,VER_NBR ) 
VALUES ( 23, 'Approval of all unbudgeted subcontracts of SOW is required', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ,OBJ_ID,VER_NBR ) 
VALUES ( 24, 'Prior consent required for unbudgeted sole source subcontracts in excess of $100,000.', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR  ) 
VALUES ( 25, 'FAR 52.244-2, Alt II, Subcontracts (Cost-reimbursement,....)', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR  ) 
VALUES ( 28, 'FAR 52.244-6', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ,OBJ_ID,VER_NBR ) 
VALUES ( 26, 'FAR 52.244-2, Alt 1, Subcontracts (Cost-reimbursement,....) Add subparagraph (e) None.  No approval of subcontracts required', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_APPROVAL_TYPE ( SUBAWARD_APPROVAL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR  ) 
VALUES ( 27, 'Approval of subcontracts in excess of $5,000 not included in approved budget', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_STATUS ( SUBAWARD_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) 
VALUES ( 1, 'Active', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_STATUS ( SUBAWARD_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) 
VALUES ( 3, 'Archive', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_STATUS ( SUBAWARD_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) 
VALUES ( 2, 'Inactive', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_STATUS ( SUBAWARD_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) 
VALUES ( 4, 'Void', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO SUBAWARD_STATUS ( SUBAWARD_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) 
VALUES ( 5, 'Closed', sysdate, 'admin',SYS_GUID(),1 )
/

INSERT INTO CLOSEOUT_TYPE (CLOSEOUT_TYPE_CODE,DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) values (1,'10.2B Closeout of Subaward', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO CLOSEOUT_TYPE (CLOSEOUT_TYPE_CODE,DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) values (2,'10.2C1 Final Payment under Subaward: Financial Assistance', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO CLOSEOUT_TYPE (CLOSEOUT_TYPE_CODE,DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) values (3,'10.2C2 Final Payment under Subaward: Federal Contracts', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO CLOSEOUT_TYPE (CLOSEOUT_TYPE_CODE,DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) values (4,'10.2D1 Transmittal of Final Patent Report', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO CLOSEOUT_TYPE (CLOSEOUT_TYPE_CODE,DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) values (5,'10.2.D2 Transmittal of Residual Property Report', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO CLOSEOUT_TYPE (CLOSEOUT_TYPE_CODE,DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) values (6,'10.2F Subcontractor''s Release: Subaward Federal Contracts', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO CLOSEOUT_TYPE (CLOSEOUT_TYPE_CODE,DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) values (7,'10.2G Subcontractor''s Assignment of Refunds, Rebates, Credits, and other', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO CLOSEOUT_TYPE (CLOSEOUT_TYPE_CODE,DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) values (8,'Audit Requested', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO CLOSEOUT_TYPE (CLOSEOUT_TYPE_CODE,DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) values (9,'Audit Completed', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO CLOSEOUT_TYPE (CLOSEOUT_TYPE_CODE,DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) values (10,'Negative Assurance Letter', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO CLOSEOUT_TYPE (CLOSEOUT_TYPE_CODE,DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) values (11,'Closeout Complete', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO CLOSEOUT_TYPE (CLOSEOUT_TYPE_CODE,DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) values (12,'Files Sent to Archives', sysdate, 'admin',SYS_GUID(),1 )
/
INSERT INTO CLOSEOUT_TYPE (CLOSEOUT_TYPE_CODE,DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,OBJ_ID,VER_NBR ) values (13,'Files Destroyed', sysdate, 'admin',SYS_GUID(),1 )
/
COMMIT
/

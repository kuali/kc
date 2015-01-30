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

delimiter /
TRUNCATE TABLE EPS_PROPOSAL_STATUS
/
INSERT INTO EPS_PROPOSAL_STATUS (STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP) 
    VALUES (1,'In Progress','admin',NOW())
/
INSERT INTO EPS_PROPOSAL_STATUS (STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP) 
    VALUES (2,'Approval In Progress','admin',NOW())
/
INSERT INTO EPS_PROPOSAL_STATUS (STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP) 
    VALUES (3,'Rejected','admin',NOW())
/
INSERT INTO EPS_PROPOSAL_STATUS (STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP) 
    VALUES (4,'Approved','admin',NOW())
/
INSERT INTO EPS_PROPOSAL_STATUS (STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP) 
    VALUES (5,'Submitted','admin',NOW())
/
INSERT INTO EPS_PROPOSAL_STATUS (STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP) 
    VALUES (6,'Post-Submission Approval','admin',NOW())
/
INSERT INTO EPS_PROPOSAL_STATUS (STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP) 
    VALUES (7,'Post-Submission Rejection','admin',NOW())
/
delimiter ;

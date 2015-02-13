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

-- These must be run together to avoid errors --
ALTER TABLE EPS_PROPOSAL 
  DROP FOREIGN KEY FK_EPS_PROPOSAL_DOCUMENT;
ALTER TABLE EPS_PROPOSAL MODIFY DOCUMENT_NUMBER VARCHAR(40);
ALTER TABLE EPS_PROPOSAL_DOCUMENT MODIFY DOCUMENT_NUMBER VARCHAR(40);
ALTER TABLE EPS_PROPOSAL
  ADD CONSTRAINT FK_EPS_PROPOSAL_DOCUMENT FOREIGN KEY (DOCUMENT_NUMBER)
  REFERENCES EPS_PROPOSAL_DOCUMENT (DOCUMENT_NUMBER);

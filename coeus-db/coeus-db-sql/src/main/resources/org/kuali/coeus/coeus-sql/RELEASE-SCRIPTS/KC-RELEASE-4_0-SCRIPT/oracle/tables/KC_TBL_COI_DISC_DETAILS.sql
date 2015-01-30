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

alter TABLE COI_DISC_DETAILS add (COPIED_COI_DISC_DETAILS_ID NUMBER(12,0), ORIGINAL_COI_DISCLOSURE_ID  NUMBER(12,0))
/
alter TABLE COI_DISC_DETAILS add COI_DISCL_PROJECT_ID NUMBER(12,0) NOT NULL
/
alter TABLE COI_DISC_DETAILS modify COI_DISCLOSURE_ID NUMBER(12,0) NULL
/

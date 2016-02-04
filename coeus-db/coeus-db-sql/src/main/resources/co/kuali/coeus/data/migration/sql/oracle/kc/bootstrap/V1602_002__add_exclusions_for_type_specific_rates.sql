--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

insert into RATE_CLASS_BASE_EXCLUSION (RATE_CLASS_BASE_EXCL_ID, RATE_CLASS_CODE, RATE_TYPE_CODE, RATE_CLASS_CODE_EXCL, RATE_TYPE_CODE_EXCL, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
  select SEQ_RATE_CLASS_BASE_EXCL_ID.nextval, RATE_CLASS_CODE, RATE_TYPE_CODE, '0', null, SYSDATE, 'admin', 1, SYS_GUID() from RATE_CLASS_BASE_INCLUSION where RATE_TYPE_CODE is not null;

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

alter table SUBAWARD add SUBAWARD_SEQUENCE_STATUS varchar2(16) default 'PENDING' not null;

update SUBAWARD set SUBAWARD_SEQUENCE_STATUS = 
	(select VERSION_STATUS from VERSION_HISTORY where SEQ_OWNER_CLASS_NAME = 'org.kuali.kra.subaward.bo.SubAward' 
		and SEQ_OWNER_VERSION_NAME_VALUE = SUBAWARD.SUBAWARD_CODE and SEQ_OWNER_SEQ_NUMBER = SUBAWARD.SEQUENCE_NUMBER);

create index SUBAWARD_CODE_AND_STATUS_IDX on SUBAWARD (SUBAWARD_CODE, SUBAWARD_SEQUENCE_STATUS);
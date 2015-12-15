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

create sequence SEQ_INSTITUTE_RATES_ID INCREMENT BY 1 START WITH 1 NOCACHE;

alter table INSTITUTE_RATES add INSTITUTE_RATE_ID number(22,0);

update INSTITUTE_RATES set INSTITUTE_RATE_ID = SEQ_INSTITUTE_RATES_ID.nextval;

alter table INSTITUTE_RATES drop primary key;

alter table INSTITUTE_RATES add constraint INSTITUTE_RATES_UQ unique(RATE_CLASS_CODE, RATE_TYPE_CODE, ACTIVITY_TYPE_CODE, FISCAL_YEAR, START_DATE, ON_OFF_CAMPUS_FLAG, UNIT_NUMBER);

alter table INSTITUTE_RATES add primary key (institute_rate_id);

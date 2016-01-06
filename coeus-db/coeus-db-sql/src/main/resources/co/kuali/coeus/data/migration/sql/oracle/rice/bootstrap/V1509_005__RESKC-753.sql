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
insert into krcr_parm_t values ('KC-AWARD', 'Document','MAKE_AWD_CUM_ANTICIPATED_OBL_EDITABLE', SYS_GUID(), '1', 'CONFG', 'Y',
'This parameter turns on or off the ability for a user to enter obligated and anticipated amounts as a cumulative value via the Award document or the Hierarchy panel on the Time and Money document. When parameter is sent to on, data can be entered as either change amount or as cumulative amount. When parameter is sent to off, only change values can be entered.', 'A', 'KC');

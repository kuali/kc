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

declare
    MaxTblVal INTEGER;
begin
    select max(ID) + 1 into MaxTblVal from 
        ((select max(DOC_HDR_ID) as ID from KREW_DOC_HDR_T)
        union (select max(DOC_TYP_ID) as ID from KREW_DOC_TYP_T) 
        union (select max(APP_DOC_STAT_TRAN_ID) as ID from KREW_APP_DOC_STAT_TRAN_T));
    execute immediate 'DROP SEQUENCE KREW_DOC_HDR_S';
    execute immediate 'CREATE SEQUENCE KREW_DOC_HDR_S START WITH ' || MaxTblVal || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
end;
/

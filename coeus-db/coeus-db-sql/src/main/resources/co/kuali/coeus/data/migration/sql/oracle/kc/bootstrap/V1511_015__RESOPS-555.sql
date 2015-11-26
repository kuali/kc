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

alter table SUBAWARD_AMOUNT_INFO add FILE_DATA_ID varchar2(36);

update SUBAWARD_AMOUNT_INFO set FILE_DATA_ID = SYS_GUID() where DOCUMENT is not null;

insert into FILE_DATA (select FILE_DATA_ID, DOCUMENT from SUBAWARD_AMOUNT_INFO where FILE_DATA_ID is not null);

alter table SUBAWARD_AMOUNT_INFO drop column DOCUMENT;

alter table SUBAWARD_AMOUNT_INFO drop column FILE_ID;

alter table SUBAWARD_AMOUNT_INFO add constraint FK2_SUBAWARD_AMOUNT_INFO foreign key (FILE_DATA_ID) references FILE_DATA (ID);


alter table SUBAWARD_ATTACHMENTS add FILE_DATA_ID varchar2(36);

update SUBAWARD_ATTACHMENTS set FILE_DATA_ID = SYS_GUID() where DOCUMENT is not null;

insert into FILE_DATA (select FILE_DATA_ID, DOCUMENT from SUBAWARD_ATTACHMENTS where FILE_DATA_ID is not null);

alter table SUBAWARD_ATTACHMENTS drop column DOCUMENT;

alter table SUBAWARD_ATTACHMENTS add constraint FK2_SUBAWARD_ATTACHMENTS foreign key (FILE_DATA_ID) references FILE_DATA (ID);

alter table SUBAWARD_ATTACHMENTS add MIME_TYPE varchar2(255);

update SUBAWARD_ATTACHMENTS set MIME_TYPE = (select CONTENT_TYPE from ATTACHMENT_FILE where ATTACHMENT_FILE.FILE_ID = SUBAWARD_ATTACHMENTS.FILE_ID);

create table ATTACHMENT_FILE_OLD as select * from ATTACHMENT_FILE where FILE_ID in (select FILE_ID from SUBAWARD_ATTACHMENTS);

delete from ATTACHMENT_FILE where FILE_ID in (select FILE_ID from SUBAWARD_ATTACHMENTS);

alter table SUBAWARD_ATTACHMENTS drop column FILE_ID;

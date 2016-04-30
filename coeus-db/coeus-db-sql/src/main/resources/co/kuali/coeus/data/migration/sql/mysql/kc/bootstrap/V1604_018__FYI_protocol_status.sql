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
insert into protocol_status (protocol_status_code, description, update_timestamp, update_user, ver_nbr, obj_id)
  values ('900', 'FYI in Progress', NOW(), 'kc', 1, UUID());
insert into protocol_status (protocol_status_code, description, update_timestamp, update_user, ver_nbr, obj_id)
  values ('901', 'FYI Incorporated into Protocol', NOW(), 'kc', 1, UUID());
insert into coeus_sub_module (coeus_sub_module_id, module_code, sub_module_code, description, update_timestamp, update_user, ver_nbr, obj_id, require_unique_questionnaire)
  values ('900', '7', '900', 'FYI', NOW(), 'admin', 1, UUID(), 'N');

insert into iacuc_protocol_status (protocol_status_code, description, update_timestamp, update_user, ver_nbr, obj_id)
  values ('900', 'FYI in Progress', NOW(), 'kc', 1, UUID());
insert into iacuc_protocol_status (protocol_status_code, description, update_timestamp, update_user, ver_nbr, obj_id)
  values ('901', 'FYI Incorporated into Protocol', NOW(), 'kc', 1, UUID());
insert into coeus_sub_module (coeus_sub_module_id, module_code, sub_module_code, description, update_timestamp, update_user, ver_nbr, obj_id, require_unique_questionnaire)
  values ('901', '9', '900', 'FYI', NOW(), 'admin', 1, UUID(), 'N');

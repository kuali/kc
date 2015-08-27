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
insert into notification_type (notification_type_id, module_code, action_code, description, subject, message, prompt_user, send_notification, update_user, update_timestamp, ver_nbr, obj_id)
  values (seq_notification_type_id.nextval, 1, '556', 'Award Notice', 'Award Notice for Award {AWARD_NUMBER}',
          'The Award Notice for Award {AWARD_NUMBER} is available for printing: <a title="" target="_self" href="{DOCUMENT_PREFIX}/awardActions.do?methodToCall=printNoticeFromNotification&amp;awardNoticeId={AWARD_NOTICE_ID}"><img src="{DOCUMENT_PREFIX}/static/images/tinybutton-print.gif" alt="Print Award Notice for Award {AWARD_NUMBER}"></a>',
          'N', 'Y', 'kc', current_timestamp, 1, sys_guid());

-- Award Notice Recipient Role
insert into notification_type_recipient (notification_type_recipient_id, notification_type_id, role_name, update_user, update_timestamp, ver_nbr, obj_id)
  values (seq_notification_type_id.nextval, (select notification_type_id from notification_type where module_code = '1' and action_code = '556'), 'KC-AWARD:Investigators', 'kc', current_timestamp, 1, sys_guid());

insert into notification_type_recipient (notification_type_recipient_id, notification_type_id, role_name, update_user, update_timestamp, ver_nbr, obj_id)
  values (seq_notification_type_id.nextval, (select notification_type_id from notification_type where module_code = '1' and action_code = '556'), 'KC-AWARD:All Unit Administrators', 'kc', current_timestamp, 1, sys_guid());

create table award_notice (
  award_notice_id number(22) not null primary key,
  award_id number(22) not null,
  award_number varchar(13),
  unit_number varchar(8),
  pdf_content clob
);

alter table award_notice add constraint fk_award_notice foreign key (award_id) references award(award_id);

create sequence SEQ_AWARD_NOTICE_ID
  minvalue 1
  start with 1
  increment by 1
  nocache;
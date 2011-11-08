CREATE OR REPLACE PACKAGE          RESULT_SETS is
type user_roles_rectype is RECORD
	(user_id				 osp$user_roles.user_id%TYPE,
    role_id				 osp$user_roles.ROLE_ID%TYPE,
    unit_number		 osp$user_roles.UNIT_NUMBER%TYPE,
    descend_flag		 osp$user_roles.DESCEND_FLAG%TYPE,
    update_timestamp	 osp$user_roles.UPDATE_TIMESTAMP%TYPE,
    update_user		 osp$user_roles.UPDATE_USER%TYPE,
    user_name			 osp$user.user_name%TYPE,
    role_name			 osp$role.role_name%TYPE);
-- type inbox_rectype is RECORD
-- 	(module_item_key	 osp$inbox.module_item_key%TYPE,  -- Case #1799
-- 	 module_code	 osp$inbox.module_code%TYPE, -- Case #1799
--     to_user				 osp$inbox.to_user%TYPE,
--     message_id		 	 osp$inbox.message_id%TYPE,
--     from_user			 osp$inbox.from_user%TYPE,
--     arrival_date		 osp$inbox.arrival_date%TYPE,
--     subject_type		 osp$inbox.subject_type%TYPE,
--     opened_flag		 osp$inbox.opened_flag%TYPE,
--     user_name			 osp$user.user_name%TYPE);
type cur_rolodex is ref cursor  return osp$rolodex%ROWTYPE;
type cur_proposal is ref cursor return osp$eps_proposal%ROWTYPE;
type cur_sponsor is ref cursor return osp$sponsor%ROWTYPE;
--type cur_message is ref cursor  return osp$message%ROWTYPE;
type cur_roles_for_unit is ref cursor  return osp$role%ROWTYPE;
type cur_users_for_role is ref cursor  return user_roles_rectype;
--type cur_inbox_for_message is ref cursor  return inbox_rectype;
type cur_generic is ref cursor;
end;
/


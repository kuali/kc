-- This procedure selects all users for the proposal from the OSP$NARRATIVE_USERS_RIGHTS
-- Input: Proposal Number
-- Created by Ramesh B NAir

create or replace procedure dw_get_narr_users_all_mod
   ( AW_PROP_NO IN osp$eps_prop_user_roles.proposal_number%TYPE,
     cur_generic IN OUT result_sets.cur_generic) is 

begin

open cur_generic for
  SELECT *
    FROM OSP$NARRATIVE_USER_RIGHTS
   WHERE PROPOSAL_NUMBER = AW_PROP_NO
	ORDER BY MODULE_NUMBER, USER_ID;

end;

/




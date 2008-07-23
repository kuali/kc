CREATE OR REPLACE PROCEDURE          SELECT_ROLODEX ( al_RolodexId in number, acur_rolodex IN OUT result_sets.cur_rolodex) is

begin


open acur_rolodex for select * from osp$rolodex where rolodex_id = al_RolodexId;
end;
/


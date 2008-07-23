CREATE OR REPLACE procedure dw_get_organization_ynq
   ( as_organization_id IN osp$organization_ynq.organization_id%TYPE,
     cur_organization_ynq IN OUT result_sets.cur_generic) is

begin
open cur_organization_ynq for
  SELECT *
    FROM OSP$ORGANIZATION_YNQ
   WHERE UPPER(OSP$ORGANIZATION_YNQ.ORGANIZATION_ID) = UPPER(as_organization_id);
end;
/


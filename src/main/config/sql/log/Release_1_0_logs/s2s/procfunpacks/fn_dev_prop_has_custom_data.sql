 create or replace function fn_dev_prop_has_custom_data ( 
   aw_prop_num IN osp$eps_proposal.proposal_number%TYPE) 
 RETURN smallint 
 
is 
 
li_count smallint; 
li_ret_code smallint := NULL; 
 
 
begin 
 
  select count(*) 
  into   li_count 
  from   OSP$EPS_PROP_CUSTOM_DATA 
  where ltrim(rtrim(PROPOSAL_NUMBER)) = ltrim(rtrim(aw_prop_num)); 
 
 IF li_count > 0 THEN 
  li_ret_code := 1; 
 ELSE 
  li_ret_code := 0; 
 END IF; 
 
 RETURN (li_ret_code); 
end;

/
  
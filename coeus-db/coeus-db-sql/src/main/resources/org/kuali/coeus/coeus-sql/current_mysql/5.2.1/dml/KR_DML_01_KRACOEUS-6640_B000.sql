DELIMITER /
insert into krcr_parm_t (nmspc_cd, cmpnt_cd, parm_nm, obj_id, ver_nbr, parm_typ_cd, val, parm_desc_txt, eval_oprtr_cd, appl_id)
  values ('KC-GEN', 'All', 'LOOKUP_CONTACT_EMAIL', UUID(), 1, 'CONFG', 'test@kuali.org', 'When a user does a Lookup, they have the option to contact the System Administrator if a record was not found. This parameter defines the email address that will be contacted.', 'A', 'KC')
/
DELIMITER ;

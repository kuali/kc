update krew_doc_typ_t
   set parnt_id = (select parnt_id
                     from krew_doc_typ_t
                    where doc_typ_nm = (select doc_typ_nm
                                          from krew_doc_typ_t 
                                         where parnt_id = 2681
                                           and cur_ind = 0
                                           and rownum = 1)
                      and cur_ind = 1)
 where parnt_id = 2681
   and cur_ind = 1;
 
commit;
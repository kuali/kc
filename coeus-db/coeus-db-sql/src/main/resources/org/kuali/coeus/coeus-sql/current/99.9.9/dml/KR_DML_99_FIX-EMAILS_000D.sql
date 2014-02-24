update krim_entity_email_t a set email_addr=(select prncpl_nm from krim_prncpl_t b where a.entity_id=b.entity_id)||'@yourschool.edu' where  lower(email_addr) like '%@kuali.org'
/


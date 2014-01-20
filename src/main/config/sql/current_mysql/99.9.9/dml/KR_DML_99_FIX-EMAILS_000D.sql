DELIMITER /

update krim_entity_email_t as a, krim_prncpl_t as b set email_addr=concat(b.prncpl_nm,'@yourschool.edu') where a.entity_id = b.entity_id and lcase(email_addr) like '%@kuali.org'
/

DELIMITER ;

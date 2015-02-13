update krim_entity_t set ACTV_IND='Y' where ENTITY_ID in (select ENTITY_ID from krim_prncpl_t where ACTV_IND='N');

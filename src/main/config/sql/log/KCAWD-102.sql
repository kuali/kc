alter table AWARD_COST_SHARE
add
   (VERIFICATION_DATE DATE, 
    COST_SHARE_MET NUMBER(12,2));
commit;    

spool update-rice-principal-columns.txt
PROMPT *************************************
PROMPT Running update rice principal columns
PROMPT *************************************
@kim-migration/update-rice-principal-columns.sql 

spool 08-21-2009-KRACOEUS-2596.txt
PROMPT *************************************
PROMPT Running KRACOEUS-2596
PROMPT *************************************
@kim-migration/08-21-2009-KRACOEUS-2596.sql 

spool 08-31-2009-KRACOEUS-2558-part-0.txt
PROMPT *************************************
PROMPT Running KRACOEUS-2558 Part 0
PROMPT *************************************
@kim-migration/08-31-2009-KRACOEUS-2558-part-0.sql 

spool 08-31-2009-KRACOEUS-2558-part-1.txt
PROMPT *************************************
PROMPT Running KRACOEUS-2558 part 1
PROMPT *************************************
@kim-migration/08-31-2009-KRACOEUS-2558-part-1.sql 

spool 08-31-2009-KRACOEUS-2558-part-2.txt
PROMPT *************************************
PROMPT Running KRACOEUS-2558 part 2 if you get a buffer error just run it again :-)
PROMPT *************************************
@kim-migration/08-31-2009-KRACOEUS-2558-part-2.sql - if you get a buffer error just run it again :-) 

spool 08-31-2009-KRACOEUS-2558-part-3.txt
PROMPT *************************************
PROMPT Running KRACOEUS-2558 part 3
PROMPT *************************************
@kim-migration/08-31-2009-KRACOEUS-2558-part-3.sql 

spool 08-31-2009-KRACOEUS-2558-part-4.txt
PROMPT *************************************
PROMPT Running KRACOEUS-2558 part 4
PROMPT *************************************
@kim-migration/08-31-2009-KRACOEUS-2558-part-4.sql 

spool reset_sequences.txt
PROMPT *************************************
PROMPT Running reset sequences procedure
PROMPT *************************************
@kim-migration/reset_sequences.sql 

spool KRACOEUS-2573.txt
PROMPT *************************************
PROMPT Running KRACOEUS-2573
PROMPT *************************************
@kim-migration/KRACOEUS-2573.sql 

spool KRACOEUS-2576.txt
PROMPT *************************************
PROMPT Running KRACOEUS-2576
PROMPT *************************************
@kim-migration/KRACOEUS-2576.sql 

spool KRACOEUS-2577.txt
PROMPT *************************************
PROMPT Running KRACOEUS-2577
PROMPT *************************************
@kim-migration/KRACOEUS-2577.SQL 

spool KRACOEUS-2730.txt
PROMPT *************************************
PROMPT Running KRACOEUS-2730
PROMPT *************************************
@kim-migration/KRACOEUS-2730.sql 

spool KRACOEUS-2763.txt
PROMPT *************************************
PROMPT Running KRACOEUS-2763
PROMPT *************************************
@kim-migration/KRACOEUS-2763.sql 

spool unit_acl_migration.txt
PROMPT *************************************
PROMPT Running unit acl migration
PROMPT *************************************
@kim-migration/unit_acl_migration.sql 

spool admin_user_principal.txt
PROMPT *************************************
PROMPT Running admin user principal
PROMPT *************************************
@kim-migration/admin_user_principal.sql 

spool 10-26-2009-KRACOEUS-2832.txt
PROMPT *************************************
PROMPT Running KRACOEUS-2832
PROMPT *************************************
@kim-migration/10-26-2009-KRACOEUS-2832.sql

spool KRACOEUS-3037.txt
PROMPT *************************************
PROMPT Running KRACOEUS-3037
PROMPT *************************************
@kim-migration/KRACOEUS-3037.sql

spool KRACOEUS-3074.txt
PROMPT *************************************
PROMPT Running KRACOEUS-3074
PROMPT *************************************
@kim-migration/KRACOEUS-3074.sql

spool 11-20-2009-KRACOEUS-3099.txt
PROMPT *************************************
PROMPT Running KRACOEUS-3099
PROMPT *************************************
@kim-migration/11-20-2009-KRACOEUS-3099.sql

spool KRACOEUS-3123.txt
PROMPT *************************************
PROMPT Running KRACOEUS-3123
PROMPT *************************************
@kim-migration/KRACOEUS-3123.sql

spool KRACOEUS-3822.txt
PROMPT *************************************
PROMPT Running KRACOEUS-3822
PROMPT *************************************
@kim-migration/KRACOEUS-3822.sql

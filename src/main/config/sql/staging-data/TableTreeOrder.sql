
WITH TEMP_TABLES AS
(
SELECT     RPAD ('*' , (LEVEL - 1) * 2 ,'*') || table_name TABLE_TREE, TABLE_NAME, LEVEL TBL_LVL
FROM       (
SELECT   b.table_name
        ,a.constraint_name pkey_constraint
        ,NULL fkey_constraint
        ,NULL r_constraint_name
FROM     user_tables b LEFT OUTER JOIN user_constraints a 
                        ON a.table_name = b.table_name
                        AND a.constraint_type = 'P'
UNION ALL
SELECT a.table_name
      ,a.constraint_name pkey_constraint
      ,b.constraint_name fkey_constraint
      ,b.r_constraint_name
FROM   user_constraints a, user_constraints b
WHERE  a.table_name = b.table_name
AND    a.constraint_type = 'P'
AND    B.CONSTRAINT_TYPE = 'R')
START WITH fkey_constraint IS NULL
CONNECT BY pkey_constraint <> r_constraint_name
AND        PRIOR PKEY_CONSTRAINT = R_CONSTRAINT_NAME
)
SELECT TABLE_NAME, TBL_LVL, ROWNUM
FROM (
SELECT TABLE_NAME, MAX(TBL_LVL) TBL_LVL 
FROM TEMP_TABLES 
GROUP BY TABLE_NAME
order by TBL_LVL, TABLE_NAME)
/

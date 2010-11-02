
select "Running schema.sql...";
\. RICE-1_0_2-Client\MYSQL\sql\schema.sql

select "Running KRNS_NTE_TYP_T.sql...";
\. RICE-1_0_2-Client\MYSQL\datasql\KRNS_NTE_TYP_T.sql

select "Running KRSB_QRTZ_LOCKS.sql...";
\. RICE-1_0_2-Client\MYSQL\datasql\KRSB_QRTZ_LOCKS.sql

select "Running report.sql...";
\. RICE-1_0_2-Client\MYSQL\sql\report.sql.generation

select "Running schema-constraints.sql...";
\. RICE-1_0_2-Client\MYSQL\sql\schema-constraints.sql

COMMIT;
EXIT

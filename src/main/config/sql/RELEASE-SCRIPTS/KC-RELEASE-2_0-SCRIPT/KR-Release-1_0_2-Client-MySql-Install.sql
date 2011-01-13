select "Running schema.sql...";
\. RICE/EMBEDDEDCLIENT_MYSQL/sql/schema.sql

select "Running KRNS_NTE_TYP_T.sql...";
\. RICE/EMBEDDEDCLIENT_MYSQL/datasql/KRNS_NTE_TYP_T.sql

select "Running KRSB_QRTZ_LOCKS.sql...";
\. RICE/EMBEDDEDCLIENT_MYSQL/datasql/KRSB_QRTZ_LOCKS.sql

select "Running schema-constraints.sql...";
\. RICE/EMBEDDEDCLIENT_MYSQL/sql/schema-constraints.sql

COMMIT;
quit

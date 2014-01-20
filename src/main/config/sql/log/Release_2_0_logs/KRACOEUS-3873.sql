SET SERVEROUTPUT ON;

DECLARE
    CURSOR TBLSWDEFCUR IS
        SELECT DISTINCT TABLE_NAME 
        FROM USER_TAB_COLUMNS 
        WHERE DATA_DEFAULT IS NOT NULL AND TABLE_NAME NOT LIKE 'BIN$%' 
        ORDER BY TABLE_NAME;
        
    CURSOR COLSWDEFCUR (TAB_NM IN VARCHAR2) IS
        SELECT TABLE_NAME, COLUMN_NAME, CASE WHEN DATA_TYPE IN ('VARCHAR2','CHAR') THEN DATA_TYPE  || '(' || DATA_LENGTH || ')'
                        WHEN DATA_TYPE IN ('NUMBER') THEN DATA_TYPE || '(' || DATA_PRECISION || ',' || DATA_SCALE || ')'
                        ELSE DATA_TYPE
                    END DATATYPE, CASE NULLABLE WHEN 'N' THEN ' NOT NULL ' ELSE NULL END NULLABLE
        FROM USER_TAB_COLUMNS
        WHERE DATA_DEFAULT IS NOT NULL
        AND TABLE_NAME = TAB_NM
        ORDER BY TABLE_NAME, COLUMN_NAME;
        
    CURSOR CONCOLSCUR (TBL_NM IN VARCHAR2) IS
        SELECT UCC.COLUMN_NAME, UCC.POSITION
        FROM USER_CONSTRAINTS UC, USER_CONS_COLUMNS UCC
        WHERE UCC.CONSTRAINT_NAME = UC.CONSTRAINT_NAME AND UCC.OWNER = UC.OWNER
        AND UC.TABLE_NAME = TBL_NM AND UC.CONSTRAINT_TYPE = 'P'
        ORDER BY 2;
        
    TEXT_C1 VARCHAR2(32767);
    TBLCNT NUMBER;
    CURRENT_TABLE VARCHAR2(30);
    TMP_TABLE VARCHAR2(30);
    CURRENT_COLUMN VARCHAR2(2000);
    SQL_PROMPT VARCHAR2(2000);
    SQL_DEFCOL VARCHAR2(2000);
    SQL_TBLCRT VARCHAR2(2000);
    SQL_COLLST VARCHAR2(2000);
    SQL_ADDCOLLST VARCHAR2(2000);
    SQL_TBLDRP VARCHAR2(2000);
    SQL_TBLADD VARCHAR2(2000);
    SQL_MODCOLLST VARCHAR2(2000);
    SQL_TBLMOD VARCHAR2(2000);
    SQL_TBLUPD VARCHAR2(2000);
    SQL_TBLCNT VARCHAR2(2000);
    WHERECONCOLLIST VARCHAR2(4000);
    CRLF VARCHAR2(2) := CHR(10);
    STEP NUMBER := 0;

BEGIN
    CURRENT_TABLE := '';
    DBMS_OUTPUT.ENABLE(1000000);
    DBMS_OUTPUT.PUT_LINE('Beginning Processing');
    FOR TBLSWDEF IN TBLSWDEFCUR LOOP
        SQL_PROMPT := '';
        SQL_TBLCRT := '';
        SQL_COLLST := '';
        SQL_ADDCOLLST := '';
        SQL_TBLDRP := '';
        SQL_TBLADD := '';
        SQL_MODCOLLST := '';
        SQL_TBLMOD := '';
        SQL_TBLUPD := '';
        SQL_TBLCNT := 0;
        WHERECONCOLLIST := '';
        CURRENT_TABLE := TBLSWDEF.TABLE_NAME;
        TMP_TABLE := SUBSTR('TMP_TBL_' || CURRENT_TABLE,1,30);
        SQL_PROMPT := 'PROMPT Processing table: ' || CURRENT_TABLE || ' ' || length('TMP_DEF_' || CURRENT_TABLE);
        SQL_TBLCRT := 'CREATE TABLE ' || TMP_TABLE || ' AS SELECT * FROM ' || CURRENT_TABLE;
        SQL_TBLCNT := 'SELECT COUNT(*) FROM ' || CURRENT_TABLE;
        EXECUTE IMMEDIATE SQL_TBLCNT INTO TBLCNT;
        IF TBLCNT > 0 THEN
            FOR CONCOLS IN CONCOLSCUR(CURRENT_TABLE) LOOP
                WHERECONCOLLIST := WHERECONCOLLIST || 'NT.' || CONCOLS.COLUMN_NAME || ' = OT.' || CONCOLS.COLUMN_NAME || ' AND ';
            END LOOP;
            WHERECONCOLLIST := SUBSTR(WHERECONCOLLIST,1,LENGTH(WHERECONCOLLIST) - 5);
        END IF;
        FOR COLSWDEF IN COLSWDEFCUR(CURRENT_TABLE) LOOP
            CURRENT_COLUMN := COLSWDEF.COLUMN_NAME;
            SQL_DEFCOL := 'SELECT DATA_DEFAULT FROM USER_TAB_COLUMNS WHERE TABLE_NAME = ''' || CURRENT_TABLE || ''' AND COLUMN_NAME = ''' || CURRENT_COLUMN || '''';
            EXECUTE IMMEDIATE SQL_DEFCOL INTO TEXT_C1;
            TEXT_C1 := TRIM(SUBSTR(TEXT_C1, 1, 4000));
            CASE
                WHEN UPPER(TEXT_C1) IN ('SYS_GUID()','SYS_GUID ()','SYS_GUID () ','SYSDATE','NULL','EMPTY_BLOB()','EMPTY_BLOB ()','EMPTY_BLOB () ','EMPTY_CLOB()','EMPTY_CLOB ()','EMPTY_CLOB () ','USER') THEN 
                    SQL_COLLST := SQL_COLLST || CURRENT_COLUMN || ', ';
                    IF TBLCNT > 0 THEN
                        SQL_ADDCOLLST := SQL_ADDCOLLST || CURRENT_COLUMN || ' ' || COLSWDEF.DATATYPE || ', ';
                        IF COLSWDEF.NULLABLE = ' NOT NULL ' THEN
                           SQL_MODCOLLST := SQL_MODCOLLST || CURRENT_COLUMN || ' ' || COLSWDEF.DATATYPE || COLSWDEF.NULLABLE || ', ';
                        END IF;
                    ELSE
                        SQL_ADDCOLLST := SQL_ADDCOLLST || CURRENT_COLUMN || ' ' || COLSWDEF.DATATYPE || COLSWDEF.NULLABLE || ', ';
                    END IF;
                ELSE NULL;
            END CASE;
        END LOOP;
        SQL_COLLST := SUBSTR(SQL_COLLST,1,LENGTH(SQL_COLLST) - 2);
        SQL_ADDCOLLST := SUBSTR(SQL_ADDCOLLST,1,LENGTH(SQL_ADDCOLLST) - 2);
        SQL_MODCOLLST := SUBSTR(SQL_MODCOLLST,1,LENGTH(SQL_MODCOLLST) - 2);
        IF LENGTH(SQL_COLLST) > 0 THEN
            SQL_PROMPT := SQL_PROMPT ||  ' Column(s): ' || SQL_COLLST;
            BEGIN
                IF TBLCNT > 0 THEN
                    BEGIN
                        EXECUTE IMMEDIATE 'DROP TABLE ' || TMP_TABLE || ' purge';
                    EXCEPTION
                        WHEN OTHERS
                            THEN NULL;
                    END;
                    STEP := 1;
                    EXECUTE IMMEDIATE SQL_TBLCRT;
                END IF;
                STEP := 2;
                SQL_TBLDRP := 'ALTER TABLE ' || CURRENT_TABLE || ' DROP (' || SQL_COLLST || ')';
                EXECUTE IMMEDIATE SQL_TBLDRP;
                STEP := 3;
                SQL_TBLADD := 'ALTER TABLE ' || CURRENT_TABLE || ' ADD (' || SQL_ADDCOLLST || ')';
                EXECUTE IMMEDIATE SQL_TBLADD;
                IF LENGTH(SQL_MODCOLLST) > 0 THEN
                    STEP := 4;
                    SQL_TBLMOD := 'ALTER TABLE ' || CURRENT_TABLE || ' MODIFY (' || SQL_MODCOLLST || ')';
                    EXECUTE IMMEDIATE SQL_TBLMOD;
                END IF;
                IF TBLCNT > 0 THEN
                    STEP := 5;
                    SQL_TBLUPD := 'UPDATE ' || CURRENT_TABLE || ' nt set ( ' || SQL_COLLST || ') = (SELECT ' || SQL_COLLST || ' FROM ' || TMP_TABLE;
                    SQL_TBLUPD := SQL_TBLUPD || ' OT WHERE ' || WHERECONCOLLIST || ')';
                    EXECUTE IMMEDIATE SQL_TBLUPD;
                    STEP := 6;
                    EXECUTE IMMEDIATE 'DROP TABLE ' || TMP_TABLE || ' purge';
                END IF;
            EXCEPTION
                WHEN OTHERS THEN
                    DBMS_OUTPUT.PUT_LINE(SQL_PROMPT);
                    CASE STEP
                        WHEN 0 THEN DBMS_OUTPUT.PUT_LINE('Step 0 Error');
                        WHEN 1 THEN DBMS_OUTPUT.PUT_LINE('Error Creating Temp Table: ' || SQL_TBLCRT);
                        WHEN 2 THEN DBMS_OUTPUT.PUT_LINE('Error Dropping Columns: ' || SQL_TBLDRP);
                        WHEN 3 THEN DBMS_OUTPUT.PUT_LINE('Error Adding Columns: ' || SQL_TBLADD);
                        WHEN 4 THEN DBMS_OUTPUT.PUT_LINE('Error Modifying Columns: ' || SQL_TBLMOD);
                        WHEN 5 THEN DBMS_OUTPUT.PUT_LINE('Error Updating Table: ' || SQL_TBLUPD);
                        WHEN 6 THEN DBMS_OUTPUT.PUT_LINE('Error Dropping Temp Table: ' || SQL_TBLUPD);
                        ELSE NULL;
                    END CASE;
                    DBMS_OUTPUT.PUT_LINE(SQLERRM);
            END;
        END IF;
    END LOOP;
DBMS_OUTPUT.PUT_LINE('PROMPT Finished Process');
END;
/
#/bin/bash

## Your local database creditentials
export DEV_USER=KRADEV
export DEV_PASS=dev174kra
export UNT_USER=KRAUNT2
export UNT_PASS=unt2174kra

## For embedded mode
export EMB_CLIENT_USER=KCDEV
export EMB_CLIENT_PASS=KCDEV
export EMB_SERVER_USER=RICEDEV
export EMB_SERVER_PASS=RICEDEV

## URL of SQL scripts
export DEV_SCRIPT_URL=https://ci.kc.kuali.net/userContent/dba/dbadb_oracle.zip
export UNT_SCRIPT_URL=https://ci.kc.kuali.net/userContent/unt/untdb_oracle.zip
##URL of SQL scripts for embedded mode. 
export EMB_CLIENT_SCRIPT_URL=https://ci.kc.kuali.org/userContent/embedded/embeddeddb_client.zip
export EMB_SERVER_SCRIPT_URL=http://ci.kc.kuali.org/userContent/embedded/embeddeddb_server.zip



## External program locations

export ORACLE_HOME=/usr/lib/oracle/xe/app/oracle/product/10.2.0/server
export SQLPLUS=$ORACLE_HOME/bin/sqlplus
export ORACLE_HOST=127.0.0.1
export ORACLE_SID=XE
export ORACLE_PORT=1521

## /* Advanced configuration */

export DEV_SQL_DIR=$HOME/dbscript/dbadb
export UNT_SQL_DIR=$HOME/dbscript/untdb
export EMB_CLIENT_SQL_DIR=$HOME/dbscript/embdb_client
export EMB_SERVER_SQL_DIR=$HOME/dbscript/embdb_server

export DEV_CMD=dev
export UNT_CMD=unt
export EMB_CMD=emb
export ALL_CMD=all
export SKIP_DNLD_OPT=dlskip

export ARG="$1"

export CURL_OPTS="--insecure -o tmp.zip"

## /* The code below should not need to be changed */

export BASE_DIR="$HOME/dbscript/"

if [ "$ARG" ==  "$ALL_CMD" -o "$ARG"  ==  "$UNT_CMD" -o "$ARG"  ==  "$DEV_CMD" -o "$ARG" = "$EMB_CMD" ]
then
   echo Running update for $ARG.
else
   echo .
   echo  Invalid argument: $ARG
   echo  "     To update the DEV database specify: $0 $DEV_CMD"
   echo  "     To update the UNT database specify: $0 $UNT_CMD"
   echo  "     To update the EMB database specify: $0 $EMB_CMD"
   echo  "     To update both the DEV and UNT database specify: $0 $ALL_CMD"
   echo .
   exit
fi


if [ $ARG == $ALL_CMD -o $ARG == $DEV_CMD ]
then
    
    	curl $CURL_OPTS $DEV_SCRIPT_URL
    
    rm -rf $DEV_SQL_DIR
    unzip tmp.zip -d $DEV_SQL_DIR
    cd ${DEV_SQL_DIR}/sql/oracle/
    echo `pwd`
    echo "running sql plus cmd: $SQLPLUS $DEV_USER/$DEV_PASS@$ORACLE_HOST:$ORACLE_PORT/$ORACLE_SID @oracle.sql"
    $SQLPLUS $DEV_USER/$DEV_PASS@$ORACLE_HOST:$ORACLE_PORT/$ORACLE_SID @oracle.sql
    cd $BASE_DIR
    echo .
    echo  DEV has been updated
fi

if [ $ARG == "$ALL_CMD" -o $ARG == $UNT_CMD ]
then
    curl $CURL_OPTS $UNT_SCRIPT_URL
    rm -rf $UNT_SQL_DIR
    mkdir $UNT_SQL_DIR
    unzip tmp.zip -d $UNT_SQL_DIR
    cd ${UNT_SQL_DIR}/sql/oracle/
    echo `pwd`
    echo "running sql plus cmd: $SQLPLUS $UNT_USER/$UNT_PASS@$ORACLE_HOST:$ORACLE_PORT/$ORACLE_SID @oracle.sql"
    $SQLPLUS $UNT_USER/$UNT_PASS@$ORACLE_HOST:$ORACLE_PORT/$ORACLE_SID @oracle.sql
    cd $BASE_DIR
    echo  UNT has been updated
fi

if [ $ARG == "$EMB_CMD" -o $ARG == $EMB_CMD ]
then

	curl $CURL_OPTS $EMB_CLIENT_SCRIPT_URL
	rm -rf $EMB_CLIENT_SQL_DIR
	mkdir $EMB_CLIENT_SQL_DIR
	unzip tmp.zip -d $EMB_CLIENT_SQL_DIR
	cd ${EMB_CLIENT_SQL_DIR}/sql/client
	echo 'pwd'
    echo "running sql plus cmd: $SQLPLUS $EMB_CLIENT_USER/$EMB_CLIENT_PASS@$ORACLE_HOST:$ORACLE_PORT/$ORACLE_SID @oracle.sql"
    $SQLPLUS $EMB_CLIENT_USER/$EMB_CLIENT_PASS@$ORACLE_HOST:$ORACLE_PORT/$ORACLE_SID @client.sql 
    cd $BASE_DIR
    echo  EMB_CLIENT has been updated

	curl $CURL_OPTS $EMB_SERVER_SCRIPT_URL
	rm -rf $EMB_SERVER_SQL_DIR
	mkdir $EMB_SERVER_SQL_DIR
	unzip tmp.zip -d $EMB_SERVER_SQL_DIR
	cd ${EMB_SERVER_SQL_DIR}/sql/server
	echo 'pwd'
    echo "running sql plus cmd: $SQLPLUS $EMB_SERVER_USER/$EMB_SERVER_PASS@$ORACLE_HOST:$ORACLE_PORT/$ORACLE_SID @oracle.sql"
    $SQLPLUS $EMB_SERVER_USER/$EMB_SERVER_PASS@$ORACLE_HOST:$ORACLE_PORT/$ORACLE_SID @server
    cd $BASE_DIR
    echo  EMB_SERVER has been updated

fi




echo
echo  ------        fin        ------
echo

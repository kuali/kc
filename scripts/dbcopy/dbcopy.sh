#/bin/bash

## Your local database creditentials
export DEV_USER=KRADEV
export DEV_PASS=<your local passwd>
export UNT_USER=KRAUNT2
export UNT_PASS=<your local passwd>

## URL of SQL scrips
export DEV_SCRIPT_URL=https://ci.kc.kuali.net/userContent/dba/dbadb_oracle.zip
export UNT_SCRIPT_URL=https://ci.kc.kuali.net/userContent/unt/untdb_oracle.zip

## External program locations

export CURL_PROG=/usr/bin/curl
export ZIP_PROG=/usr/bin/unzip
export SQLPLUS=/Oracle_10204Client_MAC_X86/ohome/bin/sqlplus

## /* Advanced configuration */

export DEV_SQL_DIR=$HOME/dbscript/dbadb
export UNT_SQL_DIR=$HOME/dbscript/untdb

export DEV_CMD=dev
export UNT_CMD=unt
export ALL_CMD=all

export ARG="$1"

export CURL_OPTS="--insecure -o tmp.zip"

## /* The code below should not need to be changed */

export CD_BASE_DIR="cd $HOME/dbscript/"

if [ "$ARG" ==  "$ALL_CMD" -o "$ARG"  ==  "$UNT_CMD" -o "$ARG"  ==  "$DEV_CMD" ]
then
   echo Running update for $ARG.
else
   echo .
   echo  Invalid argument: $ARG
   echo  "     To update the DEV database specify: $0 $DEV_CMD"
   echo  "     To update the UNT database specify: $0 $UNT_CMD"
   echo  "     To update both the DEV and UNT database specify: $0 $ALL_CMD"
   echo .
   exit
fi


if [ $ARG == $ALL_CMD -o $ARG == $DEV_CMD ]
then
    $CURL_PROG $CURL_OPTS $DEV_SCRIPT_URL
    rm -rf $DEV_SQL_DIR
    $ZIP_PROG tmp.zip -d $DEV_SQL_DIR
    cd ${DEV_SQL_DIR}/sql/oracle/
    echo `pwd`
    echo "running sql plus cmd: $SQLPLUS $DEV_USER/$DEV_PASS@10.37.129.6:1521/xe @oracle.sql"
    $SQLPLUS $DEV_USER/$DEV_PASS@10.37.129.6:1521/xe @oracle.sql
    $CD_BASE_DIR
    echo .
    echo  DEV has been updated
fi

if [ $ARG == "$ALL_CMD" -o $ARG == $UNT_CMD ]
then
    $CURL_PROG $CURL_OPTS $UNT_SCRIPT_URL
    rm -rf $UNT_SQL_DIR
    mkdir $UNT_SQL_DIR
    $ZIP_PROG tmp.zip -d $UNT_SQL_DIR
    cd ${UNT_SQL_DIR}/sql/oracle/
    echo `pwd`
    echo "running sql plus cmd: $SQLPLUS $UNT_USER/$UNT_PASS@10.37.129.6:1521/xe @oracle.sql"
    $SQLPLUS $UNT_USER/$UNT_PASS@10.37.129.6:1521/xe @oracle.sql
    $CD_BASE_DIR
    echo  UNT has been updated
fi

echo
echo  ------        fin        ------
echo
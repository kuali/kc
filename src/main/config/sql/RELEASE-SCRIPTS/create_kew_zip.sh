#!/bin/sh

cd ../../kew/xml
kew_files=`find . -name '*.xml'`
jar cvfM ../../sql/RELEASE-SCRIPTS/Full-KC-Kew.zip ${kew_files}


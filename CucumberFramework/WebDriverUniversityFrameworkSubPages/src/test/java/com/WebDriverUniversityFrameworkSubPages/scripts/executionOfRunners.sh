#!/bin/bash

echo "*** Start of execution of maven test ***"

export CURRENT_DIR=`pwd`
echo "Current directory = ${CURRENT_DIR}"
export POM_FILE_PATH=" ../../../../../../"

cd ${POM_FILE_PATH}

mvn clean compile test

cd "\"${CURRENT_DIR}\""


echo "*** End of execution of the maven test ***"
exit 0
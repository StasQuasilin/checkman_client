#! /bin/bash

git add .
git add -u

date=`date +"%d.%m.Y dev"`
git commit -m "${date}" 

#!/bin/bash

#
# Note: this script must be run from the MiniC directory or you should set MiniC directory with the first argument.
# if there's a problem with getting MiniC directory, this script may make some folders in wrong path
if [ $# == 1 ]; then
	TOPDIR=$1
else
	TOPDIR='pwd'
fi
tst=$TOPDIR/Parser/tst/base/AST_testcases
unp=$TOPDIR/Parser/tst/base/AST_answer_unparsed
unptw=$TOPDIR/Parser/tst/base/AST_answer_unparsed_twice
report=$unptw/report.txt
all=0
ok=0
classpath=$TOPDIR/../build/classes/main
minic=$TOPDIR/../build/libs/MiniC-AstGen.jar

rm -rf $unp
rm -rf $unptw
mkdir -p $unp
mkdir -p $unptw
echo "Unparse Test Report" >$report
echo "generated "`date` >>$report
#
# Run testcases:
#
echo "Testing the AST..."
for file in $tst/c*.mc
do
     all=$(( $all + 1 ))
     f=`basename $file`
     
     java -jar $minic -u $unp/s_$f.u $file > tmp1
     if [ ! -f $unp/s_$f.u ]; then
     	cat tmp > $unp/s_$f.u
     fi
     java -jar $minic -u $unptw/s_$f.u.u $unp/s_$f.u  > tmp2
     if [ ! -f $unptw/s_$f.u.u ]; then
     	cat tmp > $unptw/s_$f.u.u
     fi
     # Alternative invocation via classpath:
     # java -cp $classpath MiniC.MiniC $file > $ans/s_$f
     diff -u $unptw/s_$f.u.u $unp/s_$f.u > $unptw/diff_$f
     if [ "$?" -eq 1 ]
     then
		 echo -n "-"
                 echo "$f failed" >> $report

     else
                 echo -n "+"
                 echo "$f succeded" >> $report
                 rm -rf $unptw/diff_$f $unptw/s_$f
                 ok=$(( $ok + 1 ))
     fi
     rm tmp1
     rm tmp2
done
echo
echo "Testing finished, pls. consult the test report in ${report}"
echo "$ok out of $all testcases succeeded."

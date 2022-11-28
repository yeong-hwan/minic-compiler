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
sol=$TOPDIR/Parser/tst/base/AST_solutions_trees
ans=$TOPDIR/Parser/tst/base/AST_answer
report=$ans/report.txt
all=0
ok=0
classpath=$TOPDIR/../build/classes/main
minic=$TOPDIR/../build/libs/MiniC-AstGen.jar

rm -rf $ans
mkdir -p $ans
echo "AST Test Report" >$report
echo "generated "`date` >>$report
#
# Run testcases:
#
echo "Testing the AST..."
for file in $tst/c*.mc
do
     all=$(( $all + 1 ))
     f=`basename $file`
     
     java -jar $minic -t $ans/s_$f.ast $file > tmp
     if [ ! -f $ans/s_$f.ast ]; then
     	cat tmp > $ans/s_$f.ast
     fi
     # Alternative invocation via classpath:
     # java -cp $classpath MiniC.MiniC $file > $ans/s_$f
     diff -u $ans/s_$f.ast $sol/$f.ast > $ans/diff_$f
     if [ "$?" -eq 1 ]
     then
		 echo -n "-"
                 echo "$f failed" >> $report

     else
                 echo -n "+"
                 echo "$f succeded" >> $report
                 rm -rf $ans/diff_$f $ans/s_$f
                 ok=$(( $ok + 1 ))
     fi
     rm tmp
done
echo
echo "Testing finished, pls. consult the test report in ${report}"
echo "$ok out of $all testcases succeeded."

TOPDIR=`pwd`
tst=$TOPDIR/SemanticAnalysis/tst/base/testcases
sol=$TOPDIR/SemanticAnalysis/tst/base/solutions
ans=$TOPDIR/SemanticAnalysis/tst/base/answers
minic=$TOPDIR/../build/libs/MiniC-SemAnalysis.jar
all=0
ok=0

report="incorrect cases:"

mkdir -p $ans

for file in $tst/c*.mc
do
  all=$(($all + 1))
  f=`basename $file`
  java -jar $minic $file > $ans/s_$f
  diff -u $ans/s_$f $sol/$f.sol > $ans/diff_$f
  if [ "$?" -eq 1 ]
  then
    echo -n "-"
    report="${report} $f"
  else
    echo -n "+"
    ok=$(($ok + 1))
  fi
done

echo " $ok out of $all testcases succeeded."
echo $report

# java -jar build/libs/MiniC-SemAnalysis.jar -ast2 MiniC/SemanticAnalysis/tst/base/testcases/c4.mc
# java -jar build/libs/MiniC-SemAnalysis.jar -envast MiniC/SemanticAnalysis/tst/base/testcases/c4.mc
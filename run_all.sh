set -euo pipefail
cd "$(dirname "$0")"
ROOT="$PWD"
mkdir -p build

mkdir -p "build/Task01"
javac -g -encoding UTF-8 -cp "." -d "build/Task01" $(find shared Task01 -name "*.java")
TASK_BUILD="Task01"
mkdir -p "runs/Task01"
cd "runs/Task01"
rm -f results.csv
: > output.txt
printf "TicketText\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task01" TicketText 2>&1 | tee -a output.txt
java -cp "$ROOT/build/$TASK_BUILD" SummarizeResults
cd "$ROOT"

mkdir -p "build/Task02"
javac -g -encoding UTF-8 -cp "." -d "build/Task02" $(find shared Task02 -name "*.java")
TASK_BUILD="Task02"
mkdir -p "runs/Task02"
cd "runs/Task02"
rm -f results.csv
: > output.txt
printf "Playlists\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task02" Playlists 2>&1 | tee -a output.txt
java -cp "$ROOT/build/$TASK_BUILD" SummarizeResults
cd "$ROOT"

mkdir -p "build/Task03"
javac -g -encoding UTF-8 -cp "." -d "build/Task03" $(find shared Task03 -name "*.java")
TASK_BUILD="Task03"
mkdir -p "runs/Task03"
cd "runs/Task03"
rm -f results.csv
: > output.txt
printf "Attendance\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task03" Attendance 2>&1 | tee -a output.txt
printf "EqualityInvestigation\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task03" EqualityInvestigation 2>&1 | tee -a output.txt
java -cp "$ROOT/build/$TASK_BUILD" SummarizeResults
cd "$ROOT"

mkdir -p "build/Task04"
javac -g -encoding UTF-8 -cp "." -d "build/Task04" $(find shared Task04 -name "*.java")
TASK_BUILD="Task04"
mkdir -p "runs/Task04"
cd "runs/Task04"
rm -f results.csv
: > output.txt
printf "PharmacyMaps\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task04" PharmacyMaps 2>&1 | tee -a output.txt
java -cp "$ROOT/build/$TASK_BUILD" SummarizeResults
cd "$ROOT"

mkdir -p "build/Task05"
javac -g -encoding UTF-8 -cp "." -d "build/Task05" $(find shared Task05 -name "*.java")
TASK_BUILD="Task05"
mkdir -p "runs/Task05"
cd "runs/Task05"
rm -f results.csv
: > output.txt
printf "LeaderboardDemo\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task05" LeaderboardDemo 2>&1 | tee -a output.txt
printf "ComparatorValidation\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task05" ComparatorValidation 2>&1 | tee -a output.txt
java -cp "$ROOT/build/$TASK_BUILD" SummarizeResults
cd "$ROOT"

mkdir -p "build/Task06"
javac -g -encoding UTF-8 -cp "." -d "build/Task06" $(find shared Task06 -name "*.java")
TASK_BUILD="Task06"
mkdir -p "runs/Task06"
cd "runs/Task06"
rm -f results.csv
: > output.txt
printf "Triage\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task06" Triage 2>&1 | tee -a output.txt
printf "QueueInvestigation\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task06" QueueInvestigation 2>&1 | tee -a output.txt
java -cp "$ROOT/build/$TASK_BUILD" SummarizeResults
cd "$ROOT"

mkdir -p "build/Task07"
javac -g -encoding UTF-8 -cp "." -d "build/Task07" $(find shared Task07 -name "*.java")
TASK_BUILD="Task07"
mkdir -p "runs/Task07"
cd "runs/Task07"
rm -f results.csv
: > output.txt
printf "InventoryRedesign\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task07" InventoryRedesign 2>&1 | tee -a output.txt
java -cp "$ROOT/build/$TASK_BUILD" SummarizeResults
cd "$ROOT"

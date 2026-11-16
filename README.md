# Strings Collections and Generic Components

Athisaya U  
192571001  
CSA09 Programming in Java  
Assignment 2

This is my Java Assignment 2 on strings, collections and generics. The 7 tasks include playlists, attendance, pharmacy records, leaderboards and queues.

The task folders contain the Java programs, saved output and screenshots. Input files or generators and timing CSV files are included where needed.

## How to run

Install a full JDK 21, then open a terminal in this repository.

- Windows PowerShell: `./run_all.ps1`
- Linux or macOS: `bash run_all.sh`

The scripts compile and run each task separately. New output and timing summaries go into `runs/TaskNN`, so the saved results in the task folders stay unchanged.

## Tasks

| Folder | Programs to run |
| --- | --- |
| Task01 | TicketText |
| Task02 | Playlists |
| Task03 | Attendance, EqualityInvestigation |
| Task04 | PharmacyMaps |
| Task05 | LeaderboardDemo, ComparatorValidation |
| Task06 | Triage, QueueInvestigation |
| Task07 | InventoryRedesign |

## Timing results

Where timing is needed, the results use two warmup runs and five measured runs. The report tables use milliseconds, while the raw CSV files use nanoseconds. Input generation is outside the timed part.

The heap value is a change in memory usage, not the total bytes allocated. Timing can vary with JVM warmup, garbage collection and other work on the computer, so small differences may not repeat exactly.

## Report

[PDF report](report/Athisaya_U_Java_Assignment2_Completed.pdf)  

## Output and screenshots

Each task has a `real-execution.png` terminal screenshot and a `terminal-run.txt` file with the compiler and program output.

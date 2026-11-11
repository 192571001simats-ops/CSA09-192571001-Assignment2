# Strings Collections and Generic Components

Athisaya U  
192571001  
CSA09 Programming in Java  
Assignment 2

Java 21 implementations and captured execution evidence for 7 tasks. Every task folder contains its Java files, input fixtures or generators, output.txt and output screenshots. Measurements also have results.csv and summary.csv.

## Execution

A full JDK 21 is required. Windows PowerShell: `./run_all.ps1`. Linux or macOS: `bash run_all.sh`. These scripts compile each task independently and run its validation entry points. New execution logs and timing summaries are written under runs/TaskNN, keeping the captured task-folder evidence together with its report. Supplied code completions are separate from intentionally incorrect reference variants.

## Tasks

| Folder | Executed entry points |
| --- | --- |
| Task01 | TicketText |
| Task02 | Playlists |
| Task03 | Attendance, EqualityInvestigation |
| Task04 | PharmacyMaps |
| Task05 | LeaderboardDemo, ComparatorValidation |
| Task06 | Triage, QueueInvestigation |
| Task07 | InventoryRedesign |

## Measurements

Two warmups and five measured trials are retained where timing is requested. Tables use milliseconds; the raw CSV stores nanoseconds and heap occupancy delta. Input generation is outside timed sections. Heap occupancy is not an allocated-byte count. JVM warmup, garbage collection, caching and shared-machine scheduling limit small timing comparisons. The report separates actual observations from asymptotic analysis.

## Report

[Completed report PDF](report/Athisaya_U_Java_Assignment2_Completed.pdf)  
[Editable Word report](report/Athisaya_U_Java_Assignment2_Completed.docx)

## Implementation screenshots

Each task includes real-execution.png, captured directly from its running terminal window on the cloud desktop. The full compiler and program output is in terminal-run.txt. The reports follow the purple, rose and light blue assignment format. Assignment 4 also contains screenshots of the running applications.

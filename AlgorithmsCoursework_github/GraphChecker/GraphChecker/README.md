# Graph Acyclicity Checker
### 5SENG003W Algorithms Coursework – University of Westminster

---

## What this program does

Reads a directed graph from a text file and tells you whether it contains a cycle or not.

- If the graph is **acyclic** → prints `YES` and shows every sink elimination step.
- If the graph is **cyclic** → prints `NO`, shows where the algorithm got stuck, then finds and prints an actual cycle.

---

## Files

```
GraphChecker/
└── src/
    ├── Main.java           ← Entry point, reads args, runs the program
    ├── Graph.java          ← Adjacency-list graph data structure
    ├── GraphParser.java    ← Reads the graph from a .txt file
    ├── AcyclicChecker.java ← Sink elimination + DFS cycle finder
    └── CycleResult.java    ← Simple container for the cycle result
```

---

## How to compile

Open a terminal in the project root — the folder that contains both `src` and `benchmarks`.

### Compile

```
cd src
javac *.java
cd ..
```

That's it. No libraries needed — pure standard Java.

---

## How to run

Run the program from the project root.

```
java -cp src Main <path-to-graph-file>
```

### Example with a cyclic graph:
```
java -cp src Main benchmarks/cyclic/c_40_0.txt
```

### Example with an acyclic graph:
```
java -cp src Main benchmarks/acyclic/a_40_0.txt
```

You can use any benchmark file from the `benchmarks/` folder.

### One-command run (PowerShell)

From the project root:

```powershell
.\run.ps1
```

With a specific input file:

```powershell
.\run.ps1 -InputFile benchmarks/acyclic/a_40_0.txt
```

### Full example

```
cd src
javac *.java
cd ..
java -cp src Main benchmarks/cyclic/c_40_0.txt
```

---

## Input file format

The first line is the number of vertices.
Each line after that is one directed edge: `u v` means an edge from u to v.

```
4
0 1
1 2
2 3
3 1
```

---

## Example output (cyclic graph)

```
Loaded graph from: benchmarks/cyclic/c_40_0.txt
Number of vertices: 40

--- Sink Elimination Algorithm ---
Step 1: Sink found -> vertex 2  (outDegree = 0). Removing it.
Step 2: Sink found -> vertex 4  (outDegree = 0). Removing it.
Step 3: No sink found in remaining graph.
Result: CYCLIC (no)

Answer: NO - the graph contains a cycle.

--- Finding a Cycle ---
Cycle found: 0 -> 6 -> 15 -> 0
```

## Example output (acyclic graph)

```
Loaded graph from: benchmarks/acyclic/a_40_0.txt
Number of vertices: 40

--- Sink Elimination Algorithm ---
Step 1: Sink found -> vertex 24  (outDegree = 0). Removing it.
Step 2: Sink found -> vertex 35  (outDegree = 0). Removing it.
...
Graph is now empty.
Result: ACYCLIC (yes)

Answer: YES - the graph is acyclic.
```

---

## Requirements

- Java 8 or later (`java -version` to check)
- No external libraries needed

---

## Submission Checklist

Before submitting to the university, follow these steps:

### Step 1: Remove compiled files (`.class`)

From the project root, run:

```powershell
Get-ChildItem -Path . -Filter *.class -Recurse | Remove-Item -Force
```

This removes all `.class` files — they are generated artifacts, not part of your source code.

### Step 2: Verify your submission folder contains:

**Include (✓):**
- `src/Main.java`
- `src/Graph.java`
- `src/GraphParser.java`
- `src/AcyclicChecker.java`
- `src/CycleResult.java`
- `benchmarks/` folder (all benchmark files)
- `README.md`
- `run.ps1` (optional, but helpful for your marker)

**Exclude (✗):**
- `*.class` files
- `.idea/` folder (IDE configuration)
- `.iml` file (IDE project file)
- Any other temporary/generated files

### Step 3: Verify your code compiles and runs

Test once more before submitting:

```powershell
cd src
javac *.java
cd ..
java -cp src Main benchmarks/cyclic/c_40_0.txt
```

You should see output showing the graph analysis (not error messages).

### Step 4: Create your submission

Zip the project folder (containing `src`, `benchmarks`, `README.md`, `run.ps1`).

Upload to the university submission portal.

---

## Notes

- **`.class` files are NOT AI-generated** — they are standard Java compiler output.
- Removing them is standard practice; your marker will recompile from source.
- Your `.java` source files are what will be reviewed.
- Include `run.ps1` if your assignment allows scripts; it shows good practice.

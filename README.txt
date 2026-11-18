HOMEWORK 10 — REQUIRED PARTS ONLY

Part 1: separate chaining. Part 2: linear probing.
No bonus, GUI, external libraries, or random testing framework.
Each part has an independent package and a Driver class.
The small node/iterator/queue classes support the textbook implementation;
each is in a separate file to meet the assignment requirement.

INTELLIJ IDEA
Open this folder. Select JDK 17 or newer in Project Structure > Project > SDK.
Run Part 1 or Part 2 from the run configuration menu.

TERMINAL (macOS/Linux), from this folder
mkdir -p out
javac -encoding UTF-8 -d out src/part1/*.java src/part2/*.java
java -ea -cp out part1.Driver
java -ea -cp out part2.Driver

TESTS
Drivers show actual chains/slots and check collisions, wraparound (Part 2),
lookup, updates, deletion, missing keys, null inputs, negative hashes,
equal keys, resizing and iteration. A failed check throws AssertionError.
TEST_RESULTS.txt contains the actual output from both drivers.
Unchecked generic-array warnings are expected from the reference code.

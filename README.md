# Lattice Linear Predicate HW3
A Java application to solve PPx, BellmanFord, Prim, and OptimalBST using Lattice-Linear Predicate algorithms.

Brian Eubanks - be6363

Tom Miller - tm37479

## Overview

We implemented most of the algorithms sequentially first. This allowed us to get familiar with the LLP idea template without having to worry about and debug threads. These are in the hw3llpseq directory.

The tricky part was setting up and managing the threads. Once it was running, the same template could be applied to all algorithms, only changing what was needed for the data structures and calculating the forbidden predicate. The parallel implementation is in the hw3llppar directory.

We implemented OBST in parallel only, since we had the threaded LLP framework in place. It took days to debug an issue with th e

Room for improvement and optimization exists in implementing some synchronization. The algorithms do not need synchronization, however the ensure threads are constantly checking if their index is forbidden and the main thread is always polling if any index is forbidden. Due to this, Prim's main thread would occasionally hit an edge case trying to peek from an empty heap. A check was added to address this. OBST could implement the **priority** line to further reduce the number of updates for each G index, as well as only starting threads for the upper half of the matrix. The threads for the diagonal and the lower half of the matrix are never forbidden.


## Files - hw3llppar
These are parallel implementation of the LLP algorithms

 * LlpTestCases.java - JUnit Test cases for PPx, Prim, BellmanFord, and OBST
   
 * LLP.java  - Main executable file. (Test cases are commented out)
   
 * LlpAlg.java - abstract class definition template (unused, but I think this would be an improvemennt by reducing code duplication make a true API)

 * LlpBellmanFord.java - BellmanFord
   
 * LlpOptimalBst.java - OBST algorithm

 * LlpPrim.java - Prims algorithm

 * LlpReduce.java - LLP Reduce. Helper for LlpScan Algorithm

 * LlpScan.java - LLP PPx Scan. 


## LLP Generic Functions and File Structure

 * initialize() - Initialize data structures for G, threads, and others required for algorithm.
 * forbidden() - Checks the forbidden predicate defined in the algorithm for the current index. Returns True or False
 * ensure() - Runs for each thread index until the algorithm is done. Checks the forbidden predicate for the current index. Updates the value to ensure it is True.

 * isAnyForbidden() - the main thread runs checking all indexes to see if any is forbidden. The algorithm is done when no indexes are forbidden and sets all threads to done.
   



# Lattice Linear Predicate HW3
A Java application to solve PPx, BellmanFord, Prim, and OptimalBST using Lattice-Linear Predicate algorithms.

Brian Eubanks - be6363

Tom Miller - tm37479

## Overview

We implemented most of the algorithms sequentially first. This allowed us to get familiar with the LLP idea template without having to worry about and debug threads. (These are in the hw3llpseq directory)




## Files - hw3llppar
These are parallel implementation of the LLP algorithms

 * LlpTestCases.java - JUnit Test cases for PPx, Prim, BellmanFord, and OBST
   
 * LLP.java  - Main executable file. (Test cases are commented out)
   
 * LlpAlg.java - abstract class definition template (unused)

 * LlpBellmanFord.java - BellmanFord
   
 * LlpOptimalBst.java - OBST algorithm

 * LlpPrim.java - Prims algorithm

 * LlpReduce.java - LLP Reduce. Helper for LlpScan

 * LlpScan.java - LLP PPx Scan. 


## LLP Generic Functions and File Structure

 * initialize() - Initialize data structures for G, threads, and others required for algorithm.
 * forbidden() - Checks the forbidden predicate defined in the algorithm for the current index. Returns True or False
 * ensure() - Runs for each thread index until the algorithm is done. Checks the forbidden predicate for the current index. Updates the value to ensure it is True.

 * isAnyForbidden() - the main thread runs checking all indexes to see if any is forbidden. The algorithm is done when no indexes are forbidden and sets all threads to done.
   



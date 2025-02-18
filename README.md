# algorithms (Fall 2021) (Java)
Assignments from the course "Algorithms and Algorithmic Complexity".
#### TODO: refactoring

## Fibonacci.java
Finds the nth term in the Fibonacci Sequence, with F(0) = 0, F(1) = 1, F(n) = F(n-1) + F(n-2) for n > 1

## GeometricSequence.java
Finds the longest geometrically increasing (strictly) subsequence of an array.

## Inversions.java
Counts the number of inversions of a given permutation using the Divide and Conquer paradigm.
A permutation of the original with elements (0, 1, ..., n-1) is a different ordering of those elements 0,1,..., n-1
An inversion is a swap that reorders them to be like the original array.

## VerifyThreeSat.java
Input:
- X: boolean array of length n
- Y: 2d boolean array with m rows and 3 columns
- Z: 2d int array with m rows and 3 columns,  where each element is between 1 and n (inclusive)
Output:
return true if all constraints are satisfied with the specified variable settings; return false if otherwise

`Factor`
========

A trivial method for testing primality of an integer `n` is to try dividing `n` by all numbers between `2` and `√n`. While naive, this process should scale well in a parallel context.

Develop a program Factor, that takes 2 arguments:

```bash
$java Factor p n
```

Let `n` be a `BigInteger` type so it can run on large numbers. The program analyzes `n` and either print out “prime” or a space-separated list of the prime factors of `n` all on one line (in any order).

It uses `p` threads to speed up this process as much as possible.

Time the useful part of your code, ignoring I/O costs as much as possible. On a multiprocessor machine, find a value `n` that takes at least a minute to run, and then increase `p` until the speedup curve peaks. Show a graph of the resulting speedup in relation to number of threads, and briefly explain why you get the results you do.
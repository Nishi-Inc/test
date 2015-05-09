Test
========
This repository contains multiple small test projects. It has one parent pom in the root and 
modules have their own pom files. It has following modules.

- **dynamicFactorial:** This module calculates factorial using dynamic algorithm. I have tried it up to `7100!` and 
found that heap-size, memory-size issues come up when trying to calculate `90000!`. Uses in-memory cache and on-disk cache.
Uses recursion but `StackOverFlowException` is avoided.
- **invoiceNumber:** This module creates unique numbers to use as Invoice/estimate serial numbers with 1 check-digit.
This also includes a verifier. It's a console application, includes a `main` method.
 
License
----------

The MIT License

# failsafe-flakecount-reproducer
Reproducer for a bug in Maven Failsafe (regarding setting failOnFlakeCount)

## Expected behavior
When using the failsafe configuration property `failOnFlakeCount`, I'd expect that the build fails in case there are
more than the specified count of flakes.

## Actual behavior
The build succeeds, even when there are more than the specified count of flakes.
On the console, and the _TEST-***.xml_ report files, flakes are correctly reported.
However, the report _failsafe-summary.xml_, does not contain a `flakes` property, and thus the verify step fails.


## How to reproduce

Run integration tests for this simple maven project:

```
mvn verify
```

The build shows the following output, which is correct, except for the information that the build succeeded
(and status code 0):

```
...
[INFO] Running de.fesenmeyer.reproducer.ReproducerIT
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.003 s -- in de.fesenmeyer.reproducer.ReproducerIT
[INFO] 
[INFO] Results:
[INFO] 
[WARNING] Flakes: 
[WARNING] de.fesenmeyer.reproducer.ReproducerIT.test1
[ERROR]   Run 1: ReproducerIT.test1:12 » IllegalState Try 0 failed
[ERROR]   Run 2: ReproducerIT.test1:12 » IllegalState Try 1 failed
[INFO]   Run 3: PASS
[INFO] 
[WARNING] de.fesenmeyer.reproducer.ReproducerIT.test2
[ERROR]   Run 1: ReproducerIT.test2:17 » IllegalState Try 0 failed
[ERROR]   Run 2: ReproducerIT.test2:17 » IllegalState Try 1 failed
[INFO]   Run 3: PASS
[INFO] 
[INFO] 
[WARNING] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Flakes: 2
[INFO] 
[INFO] 
[INFO] --- failsafe:3.5.0:verify (default) @ failsafe-flakecount-reproducer ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
...
```

## Hint
Probably the Feature currently works for Surefire, it seems like it has been only tested with Surefire.
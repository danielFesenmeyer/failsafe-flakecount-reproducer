package de.fesenmeyer.reproducer;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class FailOnTries {

    private static final ConcurrentHashMap<String, AtomicInteger> tries = new ConcurrentHashMap<>();

    private FailOnTries() {
        throw new AssertionError();
    }

    static void failOnTry(final String id, int... triesToFail) {
        final var triesAmountAtomic = tries.compute(id, (k, v) -> {
            if (v == null) {
                return new AtomicInteger(0);
            } else {
                v.incrementAndGet();
                return v;
            }
        });

        final var triesAmount = triesAmountAtomic.get();
        if (Arrays.stream(triesToFail).anyMatch(tryToFail -> triesAmount == tryToFail)) {
            throw new IllegalStateException("Try " + triesAmount + " failed");
        }
    }
}

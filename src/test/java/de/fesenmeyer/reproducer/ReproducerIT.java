package de.fesenmeyer.reproducer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReproducerIT {


    @Test
    void test1() {
        FailOnTries.failOnTry("test1", 0, 1);
    }

    @Test
    void test2() {
        FailOnTries.failOnTry("test2", 0, 1);
    }

    @Test
    void test3() {
        // always succeeds
        assertTrue(true);
    }
}

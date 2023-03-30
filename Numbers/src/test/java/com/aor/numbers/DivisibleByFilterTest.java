package com.aor.numbers;

import org.junit.jupiter.api.Test;

public class DivisibleByFilterTest implements GenericListFilter {
    private int num;

    public DivisibleByFilterTest(int num) {
        this.num = num;
    }

    @Test
    public boolean accept (Integer number) {
        if(number % num != 0) return false;
        return true;
    }
}

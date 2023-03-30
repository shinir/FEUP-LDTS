package com.aor.numbers;

public class PositiveFilterTest {
    public boolean accept (Integer number) {
        if(number <= 0) return false;
        return true;
    }
}

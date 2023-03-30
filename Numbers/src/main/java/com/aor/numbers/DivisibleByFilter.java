package com.aor.numbers;

public class DivisibleByFilter implements GenericListFilter {
    private int num;

    public DivisibleByFilter (int num) {
        this.num = num;
    }

    public boolean accept (Integer number) {
        if(number % num != 0) return false;
        return true;
    }
}

package com.aor.numbers;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ListFiltererTest {
    private GenericListFilter filter;

    public ListFiltererTest(GenericListFilter filter) {
        this.filter = filter;
    }

    @Test
    public void filter() {
        List<Integer> list = Arrays.asList(1,2,4,2,5);
        for (Integer number : list)
            if(filter.accept(number) == false) list.remove(number);
    }
}
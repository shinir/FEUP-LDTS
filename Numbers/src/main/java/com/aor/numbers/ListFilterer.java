package com.aor.numbers;

import com.aor.numbers.GenericListFilter;

import java.util.List;

public class ListFilterer {
    private GenericListFilter filter;

    public ListFilterer(GenericListFilter filter) {
        this.filter = filter;
    }

    public List<Integer> filter(List<Integer> list) {
        for (Integer number : list)
            if(filter.accept(number) == false) list.remove(number);
        return list;
    }
}

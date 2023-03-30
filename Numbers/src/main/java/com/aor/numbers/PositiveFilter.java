package com.aor.numbers;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PositiveFilter implements GenericListFilter {

    @Test
    public boolean accept (Integer number) {

        if(number <= 0) return false;
        return true;
    }
}

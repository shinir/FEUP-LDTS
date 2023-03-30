package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class ListAggregatorTest {

    class ListDeduplicatorTest implements GenericListDeduplicator {
        public List<Integer> deduplicate(List<Integer> list) {
            return Arrays.asList(1,2,4);
        }
    }

    @Test
    public void sum() {
        List<Integer> list = Arrays.asList(1,2,4,2,5);

        ListAggregator aggregator = new ListAggregator(new ListDeduplicator());
        int sum = aggregator.sum(list);

        Assertions.assertEquals(14, sum);
    }

    @Test
    public void max() {
        List<Integer> list = Arrays.asList(1,2,4,2,5);

        ListAggregator aggregator = new ListAggregator(new ListDeduplicator());
        int max = aggregator.max(list);

        Assertions.assertEquals(5, max);
    }

    @Test
    public void min() {
        List<Integer> list = Arrays.asList(1,2,4,2,5);

        ListAggregator aggregator = new ListAggregator(new ListDeduplicator());
        int min = aggregator.min(list);

        Assertions.assertEquals(1, min);
    }

    @Test
    public void distinct() {
        List<Integer> list = Arrays.asList(1,2,4,2);
        GenericListDeduplicator deduplicator = Mockito.mock(GenericListDeduplicator.class);
        GenericListDeduplicator d = new ListDeduplicatorTest();
        ListAggregator a = new ListAggregator(d);
        int x = a.distinct(list);
        Assertions.assertEquals(3, x);
        Mockito.when(deduplicator.deduplicate(Mockito.anyList())).thenReturn(Arrays.asList(1, 2, 4));
    }

    List<Integer> list;

    @BeforeEach
    public void helper() {
        list = Arrays.asList(1,2,4,2,5);
    }
}

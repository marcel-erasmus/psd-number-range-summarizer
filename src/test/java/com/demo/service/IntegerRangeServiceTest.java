package com.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IntegerRangeServiceTest {

  @Autowired
  private IntegerRangeService integerRangeService;

  @Test
  public void getDistinctIntegerRange_WhenGiven_ListWithDuplicates_Return_ListWithoutDuplicates() {
    List<Integer> elements = Arrays.asList(5, 10, 15, 20, 20, 20, 15);

    List<Integer> distinctIntegerRange = integerRangeService.getDistinctIntegerRange(elements);

    assertEquals(4, distinctIntegerRange.size());
    assertTrue(distinctIntegerRange.containsAll(Arrays.asList(5, 10, 15, 20)));
  }

  @Test
  public void isSortedAscending_WhenGiven_SortedAscendingList_Return_True() {
    List<Integer> elements = Arrays.asList(1, 2, 3, 4, 5);

    assertTrue(integerRangeService.isSortedAscending(elements));
  }

  @Test
  public void isSortedAscending_WhenGiven_UnsortedList_Return_False() {
    List<Integer> elements = Arrays.asList(1, 5, 3, 4, 2);

    assertFalse(integerRangeService.isSortedAscending(elements));
  }

  @Test
  public void isSortedAscending_WhenGiven_EmptyList_Return_False() {
    assertFalse(integerRangeService.isSortedAscending(new ArrayList<>()));
  }

}

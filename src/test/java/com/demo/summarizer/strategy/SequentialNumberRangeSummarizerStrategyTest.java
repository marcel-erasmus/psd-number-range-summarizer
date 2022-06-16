package com.demo.summarizer.strategy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SequentialNumberRangeSummarizerStrategyTest {

  @Autowired
  private SequentialNumberRangeSummarizerStrategy sequentialNumberRangeSummarizerStrategy;

  @Test
  public void whenGiven_ListPositiveInts_Return_CorrectSummary() {
    List<Integer> elements = Arrays.asList(1, 2, 3, 4, 5, 10);

    String numberRangeSummary = sequentialNumberRangeSummarizerStrategy.getSummarizedCollection(elements);

    assertEquals("1-5, 10", numberRangeSummary);
  }

  @Test
  public void whenGiven_ListWithNegativeInts_Return_CorrectSummary() {
    List<Integer> elements = Arrays.asList(-1, -2, -3, -4, 10, 11, 12, 13, 14, 19);

    String numberRangeSummary = sequentialNumberRangeSummarizerStrategy.getSummarizedCollection(elements);

    assertEquals("-4--1, 10-14, 19", numberRangeSummary);
  }

  @Test
  public void whenGiven_ListContainingDuplicates_Return_CorrectSummary() {
    List<Integer> elements = Arrays.asList(5, 5, 5, 5, 5, 5, 5, 6, 3, 3, 9);

    String numberRangeSummary = sequentialNumberRangeSummarizerStrategy.getSummarizedCollection(elements);

    assertEquals("3, 5-6, 9", numberRangeSummary);
  }

  @Test
  public void whenGiven_ListContainingOnlyDuplicates_Return_CorrectSummary() {
    List<Integer> elements = Arrays.asList(1, 1, 1, 1, 1);

    String numberRangeSummary = sequentialNumberRangeSummarizerStrategy.getSummarizedCollection(elements);

    assertEquals("1", numberRangeSummary);
  }

  @Test
  public void whenGiven_ListWithOneInt_Return_CorrectSummary() {
    String numberRangeSummary = sequentialNumberRangeSummarizerStrategy.getSummarizedCollection(Collections.singletonList(4));

    assertEquals("4", numberRangeSummary);
  }

  @Test
  public void whenGiven_EmptyList_Return_EmptyString() {
    List<Integer> elements = new ArrayList<>();

    String numberRangeSummary = sequentialNumberRangeSummarizerStrategy.getSummarizedCollection(elements);

    assertEquals("", numberRangeSummary);
  }

}

package com.demo.summarizer.impl;

import com.demo.summarizer.NumberRangeSummarizer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class NumberRangeSummarizerImplTest {

  @Autowired
  private NumberRangeSummarizer numberRangeSummarizer;

  @Test
  public void collect_WhenGiven_EmptyString_Return_EmptyCollection() {
    Collection<Integer> elements = numberRangeSummarizer.collect("");

    assertTrue(elements.isEmpty());
  }

  @Test
  public void collect_WhenGiven_ValidString_Return_IntCollection() {
    Collection<Integer> elements = numberRangeSummarizer.collect("1,2,3,4,5");

    assertEquals(5, elements.size());
    assertTrue(elements.containsAll(Arrays.asList(1, 2, 3, 4, 5)));
  }

  @Test
  public void collect_WhenGiven_StringWithDuplicates_Return_IntCollectionWithDuplicates() {
    Collection<Integer> elements = numberRangeSummarizer.collect("1,2,2,3,3,3");

    assertEquals(6, elements.size());
    assertTrue(elements.containsAll(Arrays.asList(1, 2, 3)));
  }

  @Test
  public void collect_WhenGiven_StringWithFloats_Return_IntCollectionWithoutFloats() {
    Collection<Integer> elements = numberRangeSummarizer.collect("1.0,2.0,3");

    assertEquals(1, elements.size());
    assertTrue(elements.contains(3));
  }

  @Test
  public void collect_WhenGiven_StringWithSpaces_Return_AllValidInts() {
    Collection<Integer> elements = numberRangeSummarizer.collect("1 , 2 , 3 ,4 ,5 , ");

    assertEquals(5, elements.size());
    assertTrue(elements.containsAll(Arrays.asList(1, 2, 3, 4, 5)));
  }

  @Test
  public void collect_WhenGiven_StringWithNaNs_Return_AllValidInts() {
    Collection<Integer> elements = numberRangeSummarizer.collect("##,1,$$,%%,^,?,1A,2, 3 ");

    assertEquals(3, elements.size());
    assertTrue(elements.containsAll(Arrays.asList(1, 2, 3)));
  }

  /**
   * For a more robust set of tests on <code>summarizeCollection()</code> take a look at the <code>com.demo.summarizer.strategy.NumberRangeSummarizerStrategy</code> implementation(s).
   * @see com.demo.summarizer.strategy.SequentialNumberRangeSummarizerStrategyTest
   */
  @Test
  public void summarizeCollection_WhenGiven_ListPositiveInts_Return_CorrectSummary() {
    String numberRangeSummary = numberRangeSummarizer.summarizeCollection(Arrays.asList(1, 2, 3, 4, 5, 10));

    assertEquals("1-5, 10", numberRangeSummary);
  }

}

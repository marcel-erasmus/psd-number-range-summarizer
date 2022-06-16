package com.demo.summarizer.impl;

import com.demo.summarizer.NumberRangeSummarizer;
import com.demo.summarizer.strategy.NumberRangeSummarizerStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class NumberRangeSummarizerImpl implements NumberRangeSummarizer {

  private final NumberRangeSummarizerStrategy numberRangeSummarizerStrategy;

  public NumberRangeSummarizerImpl(NumberRangeSummarizerStrategy numberRangeSummarizerStrategy) {
    this.numberRangeSummarizerStrategy = numberRangeSummarizerStrategy;
  }

  /**
   * Receives a raw comma-separated value String and converts it into a valid <code>Integer</code> collection. NaNs are filtered out.
   */
  @Override
  public Collection<Integer> collect(String rawInput) {
    return Arrays.stream(rawInput.replace(" ", "").split(","))
        .filter(entry -> entry.matches("-?[1-9]\\d*|0"))
        .map(Integer::parseInt)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  public String summarizeCollection(Collection<Integer> elements) {
    return numberRangeSummarizerStrategy.getSummarizedCollection(elements);
  }

}

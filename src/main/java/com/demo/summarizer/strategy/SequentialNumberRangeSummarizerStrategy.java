package com.demo.summarizer.strategy;

import com.demo.service.IntegerRangeService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class SequentialNumberRangeSummarizerStrategy implements NumberRangeSummarizerStrategy {

  private final IntegerRangeService integerRangeService;

  public SequentialNumberRangeSummarizerStrategy(IntegerRangeService integerRangeService) {
    this.integerRangeService = integerRangeService;
  }

  @Override
  public String getSummarizedCollection(Collection<Integer> elements) {
    //  Do some basic validation.
    if (elements == null || elements.isEmpty()) {
      return "";
    }

    //  Get a list of distinct elements.
    List<Integer> sanitizedElements = integerRangeService.getDistinctIntegerRange(elements);

    //  Sort the distinct list if it's not sorted or if the list size is relatively small (<10K).
    if (sanitizedElements.size() < 10000 || !integerRangeService.isSortedAscending(sanitizedElements)) {
      Collections.sort(sanitizedElements);
    }

    return getSummarizedCollection(sanitizedElements);
  }

  private String getSummarizedCollection(List<Integer> sanitizedElements) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(sanitizedElements.get(0));

    int firstSequential = sanitizedElements.get(0);
    int lastSequential = sanitizedElements.get(0);

    for (int i = 1; i < sanitizedElements.size(); i++) {
      int element = sanitizedElements.get(i);

      //  If the difference between the current and previous element isn't 1 stop the sequential build-up.
      if (element - sanitizedElements.get(i - 1) != 1) {
        if (firstSequential != lastSequential) {
          stringBuilder.append("-").append(lastSequential);
        }

        firstSequential = element;
      }

      lastSequential = element;

      //  If a sequence hasn't been detected, simply add the element to the list.
      if (firstSequential == lastSequential) {
        stringBuilder.append(", ").append(element);
      }
    }

    return stringBuilder.toString();
  }

}

package com.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IntegerRangeService {

  public List<Integer> getDistinctIntegerRange(Collection<Integer> elements) {
    if (CollectionUtils.isEmpty(elements)) {
      return new ArrayList<>();
    }

    return new ArrayList<>(elements)
        .stream()
        .distinct()
        .collect(Collectors.toList());
  }

  public boolean isSortedAscending(List<Integer> elements) {
    if (CollectionUtils.isEmpty(elements)) {
      return false;
    }

    for (int i = 1; i < elements.size(); i++) {
      if (elements.get(i) < elements.get(i - 1)) {
        return false;
      }
    }

    return true;
  }

}

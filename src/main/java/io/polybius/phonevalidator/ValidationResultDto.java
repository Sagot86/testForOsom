package io.polybius.phonevalidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationResultDto {
  public Map<String, List<String>> validPhonesByCountry = new HashMap<>();
  List<String> invalidPhones = new ArrayList<>();
}

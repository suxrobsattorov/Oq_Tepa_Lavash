package ok.suxrob.lists;

import ok.suxrob.dto.Zakaz;
import ok.suxrob.value.Values;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RepositoryList {
    HashMap<String, String> userButton = new HashMap<>();
    HashMap<String, String> userButtonReply = new HashMap<>();
    HashMap<String, String> phototext = new HashMap<>();
    Map<String, List<Zakaz>> zakazMap = new HashMap<>();
    Map<String, Integer> userSelectedProductCount = new HashMap<>();
    Map<String, Integer> totalSum = new HashMap<>();
    Map<String, Values> userValues = new HashMap<>();
}

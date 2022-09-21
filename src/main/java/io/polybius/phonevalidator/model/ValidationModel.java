package io.polybius.phonevalidator.model;

import java.util.List;

public class ValidationModel {
    private final String numericCode;
    private final String alphabeticCode;
    private final List<String> firstSymbols;
    private final List<Integer> numberDigitsCount;

    public ValidationModel(String numericCode, String alphabeticCode, List<String> firstSymbols, List<Integer> numbersCount) {
        this.numericCode = numericCode;
        this.alphabeticCode = alphabeticCode;
        this.firstSymbols = firstSymbols;
        this.numberDigitsCount = numbersCount;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public String getAlphabeticCode() {
        return alphabeticCode;
    }

    public List<String> getFirstSymbols() {
        return firstSymbols;
    }

    public List<Integer> getNumberDigitsCount() {
        return numberDigitsCount;
    }
}

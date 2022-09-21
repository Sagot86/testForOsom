package io.polybius.phonevalidator;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.polybius.phonevalidator.model.Constants.LT_CODE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MobilePhoneNumberValidatorTest {

    private MobilePhoneNumberValidator validator = new MobilePhoneNumberValidator();

    @Test
    public void validate() {
        ValidationResultDto result = validator.validate(List.of("+37061234567"));
        assertEquals(List.of("+37061234567"), result.validPhonesByCountry.get("LT"));

        result = validator.validate(List.of("+37091234567"));
        assertEquals(List.of("+37091234567"), result.invalidPhones);

        result = validator.validate(List.of("+3706123456"));
        assertEquals(List.of("+3706123456"), result.invalidPhones);
    }

    @Test
    public void validatePhonesTest() {
        String phoneValidLt = "+37-061 234+5-6 7";
        String phoneInvalidByCountryCode = "+36061234567";
        String phoneInvalidByDigitsCount = "+370612345672";
        String phoneInvalidByStartDigits = "+370512345672";

        ValidationResultDto result = validator.validate(List.of(phoneValidLt, phoneInvalidByCountryCode, phoneInvalidByDigitsCount, phoneInvalidByStartDigits));

        assertTrue(result.validPhonesByCountry.get(LT_CODE).contains(phoneValidLt));
        assertTrue(result.invalidPhones.containsAll(Arrays.asList(phoneInvalidByCountryCode, phoneInvalidByDigitsCount, phoneInvalidByStartDigits)));
    }
}
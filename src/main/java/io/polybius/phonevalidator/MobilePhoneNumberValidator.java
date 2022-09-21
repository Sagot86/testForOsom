package io.polybius.phonevalidator;

import io.polybius.phonevalidator.model.ValidationModel;

import java.util.List;
import java.util.Optional;

import static io.polybius.phonevalidator.model.Constants.*;

public class MobilePhoneNumberValidator {

    private final List<ValidationModel> validators = buildValidationModelsList();

    public ValidationResultDto validate(List<String> phoneNumbers) {
        ValidationResultDto result = new ValidationResultDto();

        for (String phoneNumber : phoneNumbers) {
            String cleanPhoneNumber = removeNonDigits(phoneNumber);

            Optional<ValidationModel> phoneValidator = validators
                    .stream()
                    .filter(it -> cleanPhoneNumber.startsWith(it.getNumericCode()))
                    .findFirst();

            if (phoneValidator.isPresent() && phoneIsValid(phoneValidator.get(), cleanPhoneNumber)) {
                addPhoneToValid(result, phoneValidator.get().getAlphabeticCode(), phoneNumber);
            } else {
                result.invalidPhones.add(phoneNumber);
            }
        }

        return result;
    }

    private boolean phoneIsValid(ValidationModel validator, String cleanPhone) {
        String phoneWithoutCode = cleanPhone.substring(validator.getNumericCode().length());

        if (phoneDigitsCountIsValid(validator, phoneWithoutCode)) {
            return phoneStartDigitsIsValid(validator, phoneWithoutCode);
        }

        return false;
    }

    private boolean phoneDigitsCountIsValid(ValidationModel validator, String phone) {
        return validator.getNumberDigitsCount().stream().anyMatch(it -> it == phone.length());
    }

    private boolean phoneStartDigitsIsValid(ValidationModel validator, String phone) {
        return validator.getFirstSymbols()
                .stream()
                .anyMatch(phone::startsWith);
    }

    private void addPhoneToValid(ValidationResultDto result, String code, String phoneNumber) {
        List<String> phonesForCountry = result.validPhonesByCountry.get(code);
        if (phonesForCountry != null) {
            phonesForCountry.add(phoneNumber);
        } else {
            result.validPhonesByCountry.put(code, List.of(phoneNumber));
        }
    }

    private String removeNonDigits(String phone) {
        return phone.replaceAll("[+\\)\\( -]", "");
    }

    private List<ValidationModel> buildValidationModelsList() {
        return List.of(
                new ValidationModel("370", LT_CODE, List.of("6"), List.of(8)),
                new ValidationModel("371", LV_CODE, List.of("2"), List.of(8)),
                new ValidationModel("372", EE_CODE, List.of("5"), List.of(8, 7)),
                new ValidationModel("32", BE_CODE, List.of("456", "47", "48", "49"), List.of(9))
        );
    }

}

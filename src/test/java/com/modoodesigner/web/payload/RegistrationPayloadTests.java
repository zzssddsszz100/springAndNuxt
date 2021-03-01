package com.modoodesigner.web.payload;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegistrationPayloadTests {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validate_blankPayload_shouldFail(){
        RegistrationPayload payload = new RegistrationPayload();

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(3,violations.size());
    }

    @Test
    void validate_payloadWithInvalidEmail_shouldFail(){
        RegistrationPayload payload = new RegistrationPayload();
        payload.setEmailAddress("BadEmailAddress");
        payload.setUsername("myUsername");
        payload.setPassword("MyPassword!@");
        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1,violations.size());
    }

    @Test
    public void validate_payloadWithEmailAddressLongerThan100_shouldFail() {
        // The maximum allowed localPart is 64 characters
        // http://www.rfc-editor.org/errata_search.php?rfc=3696&eid=1690
        int maxLocalPathLength = 64;
        String localPart = RandomStringUtils.random(maxLocalPathLength, true, true);
        int usedLength = maxLocalPathLength + "@".length() + ".com".length();
        String domain = RandomStringUtils.random(101 - usedLength, true, true);

        RegistrationPayload payload = new RegistrationPayload();
        payload.setEmailAddress(localPart + "@" + domain + ".com");
        payload.setUsername("MyUsername");
        payload.setPassword("MyPassword");

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1, violations.size());
    }

    @Test
    public void validate_payloadWithUsernameLongerThan50_shouldFail() {
        RegistrationPayload payload = new RegistrationPayload();
        String usernameTooLong = RandomStringUtils.random(51);
        payload.setUsername(usernameTooLong);
        payload.setPassword("MyPassword!@");
        payload.setEmailAddress("test@test.com");

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1, violations.size());
    }

    @Test
    public void validate_payloadWithPasswordShorterThan6_shouldFail() {
        RegistrationPayload payload = new RegistrationPayload();
        String passwordTooShort = RandomStringUtils.random(5);
        payload.setUsername("MyUsername");
        payload.setPassword(passwordTooShort);
        payload.setEmailAddress("test@test.com");

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1, violations.size());
    }

    @Test
    public void validate_payloadWithPasswordLongerThan30_shouldFail() {
        RegistrationPayload payload = new RegistrationPayload();
        String passwordTooLong = RandomStringUtils.random(31);
        payload.setUsername("MyUsername");
        payload.setPassword(passwordTooLong);
        payload.setEmailAddress("test@test.com");

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1, violations.size());
    }

    @Test
    public void validate_payloadWithCorrectly_shouldSuccess() {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("MyUsername");
        payload.setPassword("MyPassword!@");
        payload.setEmailAddress("test@test.com");

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(0, violations.size());
    }






}
package dev.accelators.converter;

import dev.accelators.model.ValidStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ValidStatusConverter implements AttributeConverter<ValidStatus, String> {
    @Override
    public String convertToDatabaseColumn(ValidStatus attribute) {
        return attribute.getStatus();
    }

    @Override
    public ValidStatus convertToEntityAttribute(String dbData) {
        return ValidStatus.fromString(dbData);
    }
}

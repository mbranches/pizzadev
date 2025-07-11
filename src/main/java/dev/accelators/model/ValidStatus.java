package dev.accelators.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum ValidStatus {
    PENDENTE("pendente"),
    EM_PREPARO("em preparo"),
    SAIU_PARA_ENTREGA("saiu pra entrega"),
    ENTREGUE("entregue");

    private final String status;

    ValidStatus(String status) {
        this.status = status;
    }

    public static ValidStatus fromString(String status) {
        Optional<ValidStatus> optionalStatus = Arrays.stream(ValidStatus.values())
                .filter(s -> s.getStatus().equalsIgnoreCase(status))
                .findFirst();

        return optionalStatus
                .orElseThrow(() -> new IllegalArgumentException("Status inválido: " + status));
    }
}

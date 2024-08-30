package com.flab.skilltrademarket.domain.proposal;

import java.util.Arrays;

public enum Status {
    PENDING(1),
    PROCEEDING(2),
    FINISHED(3);

    private final int sequence;

    Status(int sequence) {
        this.sequence = sequence;
    }

    public static Status findNextStatus(Status status) {

        return Arrays.stream(values())
                .filter(s -> s.sequence == status.sequence +1)
                .findFirst()
                .orElse(status);
    }
}

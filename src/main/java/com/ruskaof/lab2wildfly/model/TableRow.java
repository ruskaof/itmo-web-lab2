package com.ruskaof.lab2wildfly.model;

import java.io.Serializable;

public record TableRow(
        float x,
        float y,
        float r,
        boolean wasHit,
        String attemptTime,
        float processTimeMills
) implements Serializable {
}

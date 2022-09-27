package com.ruskaof.lab2wildfly.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public record TableData(List<TableRow> tableRowList) implements Serializable {
    public TableData() {
        this(new ArrayList<>());
    }
}

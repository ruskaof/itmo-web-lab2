package com.ruskaof.lab2wildfly.model.repository;

import com.ruskaof.lab2wildfly.controller.TableData;
import com.ruskaof.lab2wildfly.controller.TableRow;

import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

@Stateless
public class ClicksRepositoryImpl implements ClicksRepository {
    private static final String SESSION_DATA_NAME_ATTRIBUTE = "table_data";

    private static TableData getDataFromSession(HttpSession session) {
        var tableData = (TableData) session.getAttribute(SESSION_DATA_NAME_ATTRIBUTE);
        if (tableData == null) tableData = new TableData();
        return tableData;
    }

    @Override
    public TableData getClicks(HttpSession session) {
        return getDataFromSession(session);
    }

    @Override
    public void addNote(HttpSession session, TableRow tableRow) {
        final var tableData = getDataFromSession(session);
        tableData.tableRowList().add(tableRow);
        session.setAttribute(SESSION_DATA_NAME_ATTRIBUTE, tableData);
    }

    @Override
    public void clear(HttpSession session) {
        session.setAttribute(SESSION_DATA_NAME_ATTRIBUTE, null);
    }
}

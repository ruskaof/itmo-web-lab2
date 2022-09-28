package com.ruskaof.lab2wildfly.model.repository;

import com.ruskaof.lab2wildfly.controller.TableData;
import com.ruskaof.lab2wildfly.controller.TableRow;

import javax.ejb.Local;
import javax.servlet.http.HttpSession;

@Local
public interface ClicksRepository {
    TableData getClicks(HttpSession session);

    void addNote(HttpSession session, TableRow tableRow);

    void clear(HttpSession session);
}

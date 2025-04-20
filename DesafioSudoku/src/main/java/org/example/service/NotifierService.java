package org.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.service.EventEnum.CLEAR_SPACE;

public class NotifierService {

    private final Map<EventEnum, List<EventListener>> listener = new HashMap<>(){{
        put(CLEAR_SPACE, new ArrayList<EventListener>());
    }};

    public void subscribe(final EventEnum eventType, final EventListener listener) {
        var selectedListerner = this.listener.get(eventType);
        selectedListerner.add(listener);
    }

    public void notify(final EventEnum eventType) {
        this.listener.get(eventType).forEach(listener -> listener.update(eventType));
    }
}

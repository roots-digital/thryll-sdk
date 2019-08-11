package tech.rootsdigital.thryll_integration.interfaces;

import java.util.List;

import tech.rootsdigital.thryll_integration.data.models.Event;

public interface EventsAdapter {
    public void setEvents(List<Event> posts);
    public void addEvents(List<Event> posts);
    public void addEvent(Event event);
}

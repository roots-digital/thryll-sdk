package tech.rootsdigital.thryll_integration.interfaces;

import tech.rootsdigital.thryll_integration.data.models.RequestResult;

public interface EventsRequestListener {
    void OnComplete(RequestResult result);
}

package tech.rootsdigital.thryll_integration_sdk.interfaces;

import tech.rootsdigital.thryll_integration_sdk.data.models.Event;
import tech.rootsdigital.thryll_integration_sdk.data.models.RequestResult;

public interface EventsRequestListener {
    void OnComplete(RequestResult result);
}

package tech.rootsdigital.thryll_integration_sdk.data.source;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import tech.rootsdigital.thryll_integration_sdk.data.models.GetEventsResponse;

public interface ApiService {

    @GET("v1.5/events/getbystatusforeventaccount/{accountId}/1")
    Call<GetEventsResponse> getActiveEvents(@Path("accountId") String accountId);

    @GET("v1.5/events/getbystatusforeventaccount/{accountId}/2")
    Call<GetEventsResponse> getPastAccountEvents(@Path("accountId") String accountId);

    @GET("v1.5/events/eventbyeventaccountid/{accountId}")
    Call<GetEventsResponse> getAllEvents(@Path("accountId") String accountId);

    @GET("v1.5/events/event/{identifier}")
    Call<GetEventsResponse> getEvent(@Path("identifier") String param);
}

package tech.rootsdigital.thryll_integration_sdk.data.source.repository;


import tech.rootsdigital.thryll_integration_sdk.data.source.ApiService;
import tech.rootsdigital.thryll_integration_sdk.data.source.ApiClient;

public class RemoteDataRequests implements DataRequests {

    private final String TAG = RemoteDataRequests.class.getSimpleName();
    private ApiService api;

    RemoteDataRequests(){
        api = ApiClient.getAPIService();
    }


}

package tech.rootsdigital.thryll_integration.data.source.repository;


import tech.rootsdigital.thryll_integration.data.source.ApiService;
import tech.rootsdigital.thryll_integration.data.source.ApiClient;

public class RemoteDataRequests implements DataRequests {

    private final String TAG = RemoteDataRequests.class.getSimpleName();
    private ApiService api;

    RemoteDataRequests(){
        api = ApiClient.getAPIService();
    }


}

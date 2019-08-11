package tech.rootsdigital.thryll_integration_sdk;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.GridView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.rootsdigital.thryll_integration_sdk.adapters.EventsGridAdapter;
import tech.rootsdigital.thryll_integration_sdk.adapters.EventsRecyclerAdapter;
import tech.rootsdigital.thryll_integration_sdk.data.models.Event;
import tech.rootsdigital.thryll_integration_sdk.data.models.GetEventsResponse;
import tech.rootsdigital.thryll_integration_sdk.data.models.RequestResult;
import tech.rootsdigital.thryll_integration_sdk.data.source.ApiClient;
import tech.rootsdigital.thryll_integration_sdk.interfaces.EventsAdapter;
import tech.rootsdigital.thryll_integration_sdk.interfaces.EventsRequestListener;
import tech.rootsdigital.thryll_integration_sdk.ui.webview.WebviewActivity;

public class Thryll {

    private static final String TAG = "Thryll SDK";

    private static final String ACCOUNT_ID = "thryll_organiser_id";

    private static Thryll instance = null;

    private Context context;

    private EventsAdapter displayAdapter;

    private EventsRequestListener requestListener;

    private EventsRecyclerAdapter recyclerAdapter;

    private EventsGridAdapter gridAdapter;

    private ArrayList<Event> events;

    private boolean loadRawEvents;


    protected Thryll(){

    }

    /**
     *
     * @param context
     * @return
     */
    public static Thryll Initialize(Application context) {
        if(instance == null) {
            instance = new Thryll();

            ApiClient.Initialize(context);
        }
        return instance;
    }


    /**
     *
     * @param context
     */
    public Thryll (Context context){
        this.context = context;
    }

    /**
     * Sets a {@link RecyclerView} to be populated with organiser's events using the
     * {@link EventsRecyclerAdapter} provided with the sdk.
     *
     * @param recyclerView to display events after data is received from Thryll server.
     * @return the current instance of {@link Thryll} class.
     */
    public Thryll loadEventsInto(RecyclerView recyclerView){
        recyclerAdapter = new EventsRecyclerAdapter(context, Glide.with(context));
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);

        displayAdapter = recyclerAdapter;
        return this;
    }

    /**
     * Sets a {@link GridView} to be populated with organiser's events using the {@link EventsGridAdapter}
     * provided with the sdk.
     *
     * @param gridView to display events after data is received from Thryll server.
     * @return the current instance of {@link Thryll} class.
     */
    public Thryll loadEventsInto(GridView gridView){
        gridAdapter = new EventsGridAdapter(context, Glide.with(context));
        gridView.setAdapter(gridAdapter);
        displayAdapter = gridAdapter;
        return this;
    }

    /**
     *
     * @param adapter
     * @return the current instance of {@link Thryll} class.
     */
    public Thryll loadEventsInto(EventsAdapter adapter){
        this.displayAdapter = adapter;
        return this;
    }

    /**
     *
     * @return the current instance of {@link Thryll} class.
     */
    public Thryll loadEventsRaw(){
        loadRawEvents = true;
        return this;
    }

    /**
     * Sets the {@link EventsRequestListener} interface instance to receive callback after
     * asynchronous fetch of organiser events.
     *
     * @param requestListener interface implementation
     * @return the current instance of {@link Thryll} class.
     */
    public Thryll withListener(EventsRequestListener requestListener){
        this.requestListener = requestListener;
        return this;
    }

    /**
     * Calls {@link #fetchEvents()} to perform asynchronous load of organiser's events.
     *
     * @throws IllegalArgumentException when none of the following have been set before method is called.
     * <ol>
     * <li>{@link #requestListener} </li>
     * <li>{@link #displayAdapter} </li>
     * <li>{@link #loadEventsRaw()} </li>
     * </ol>
     */
    public void makeRequest() throws IllegalArgumentException{
        if(!loadRawEvents & requestListener == null & displayAdapter == null){
            throw new IllegalArgumentException("No adapter or listener set for request. " +
                    "Ensure you have called #loadEventsInto() or #loadEventsRaw() methods beforehand");
        }
        fetchEvents();
    }

    /**
     * Request the sdk to process the event on behalf of your app.
     * Opens an event details page where users can select and purchase tickets.
     *
     * @param event being processed.
     * @param context of the activity or fragment the call is made from.
     */
    public static void ProcessEvent(Event event, Context context){
        String url = "https://www.thryll.app/event?eventCode=" + event.getEventCode();
        Intent intent = new Intent(context, WebviewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", event.getEventName());
        context.startActivity(intent);
    }

    /**
     *
     */
    private void fetchEvents() {
        final int organiserId = context.getResources().getIdentifier(ACCOUNT_ID, "string", context.getPackageName());

        ApiClient.getAPIService().getActiveEvents(context.getString(organiserId)).enqueue(new Callback<GetEventsResponse>() {
            @Override
            public void onResponse(Call<GetEventsResponse> call, Response<GetEventsResponse> response) {

                if (response.isSuccessful() && response.body().isSuccess()) {

                    Log.i(TAG, "Events loaded for account " + context.getString(organiserId) +
                                " is: " + response.body().getData().size());

                        if(displayAdapter != null)
                            displayAdapter.setEvents(response.body().getData());
                        if(requestListener != null){
                            requestListener.OnComplete(new RequestResult(true, response.body().getMessage(), response.body().getData()));
                        }
                }
                else if (response.body() != null) {
                    if(requestListener != null){
                        requestListener.OnComplete(new RequestResult(false, response.body().getMessage()));
                    }

                    Log.e(TAG, "Action failed: " + response.body().getMessage());
                }
                else {
                    if(requestListener != null){
                        requestListener.OnComplete(new RequestResult(false, response.message()));
                    }

                    Log.e(TAG, "Call unsuccessful: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<GetEventsResponse> call, Throwable t) {
                if(requestListener != null){
                    requestListener.OnComplete(new RequestResult(false, t.getLocalizedMessage()));
                }

                Log.e(TAG, t.getMessage());
            }
        });
    }

}

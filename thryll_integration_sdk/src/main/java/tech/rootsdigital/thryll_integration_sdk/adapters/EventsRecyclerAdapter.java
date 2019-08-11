package tech.rootsdigital.thryll_integration_sdk.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import tech.rootsdigital.thryll_integration_sdk.R;
import tech.rootsdigital.thryll_integration_sdk.Thryll;
import tech.rootsdigital.thryll_integration_sdk.data.models.Event;
import tech.rootsdigital.thryll_integration_sdk.interfaces.EventClickListener;
import tech.rootsdigital.thryll_integration_sdk.interfaces.EventsAdapter;
import tech.rootsdigital.thryll_integration_sdk.utils.DateUtils;


public class EventsRecyclerAdapter extends RecyclerView.Adapter<EventsRecyclerAdapter.PostViewHolder> implements EventsAdapter {

    private RequestManager glide;
    private Context mContext;
    private EventClickListener listener;
    private List<Event> eventList;


    public void setListener(EventClickListener listener) {
        this.listener = listener;
    }


    public static class PostViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView time, title, venue;

        public PostViewHolder(View itemView) {
            super(itemView);
            this.venue = (TextView) itemView.findViewById(R.id.event_venue);
            this.title = (TextView) itemView.findViewById(R.id.event_name);
            this.time = (TextView) itemView.findViewById(R.id.event_time);
            this.image = (ImageView) itemView.findViewById(R.id.news_image);
        }
    }

    public EventsRecyclerAdapter(Context context, RequestManager glide) {
        this.mContext = context;
        this.glide = glide;
        this.eventList = new ArrayList<>();
    }

    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item_layout, parent, false));
    }

    public void onBindViewHolder(PostViewHolder postView, int position) {

        Event item = eventList.get(position);

        postView.venue.setText(item.getVenue());
        postView.title.setText(item.getEventName());
        postView.time.setText(DateUtils.FormatDateToDayMMdd(item.getDates().get(0).getDate()));

        glide.load(item.getImages().get(0)).into(postView.image);

        postView.image.setOnClickListener(v -> Thryll.ProcessEvent(item, v.getContext()));
    }



    public int getItemCount() {
        return this.eventList.size();
    }

    public void setEvents(List<Event> posts) {
        this.eventList = posts;
        notifyDataSetChanged();
        Log.i("Posts Adapter", "Number of eventList passed to adapter: " + posts.size());
    }

    @Override
    public void addEvents(List<Event> posts) {
        int lastIndex = this.eventList.size();
        this.eventList.addAll(posts);
        notifyItemRangeInserted(lastIndex, posts.size());
        Log.i("Posts Adapter", "Number of eventList passed to adapter: " + posts.size());
    }

    @Override
    public void addEvent(Event event) {

    }
}

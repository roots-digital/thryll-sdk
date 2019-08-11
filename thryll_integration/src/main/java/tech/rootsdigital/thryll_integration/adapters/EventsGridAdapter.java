package tech.rootsdigital.thryll_integration.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import tech.rootsdigital.thryll_integration.R;
import tech.rootsdigital.thryll_integration.Thryll;
import tech.rootsdigital.thryll_integration.data.models.Event;
import tech.rootsdigital.thryll_integration.interfaces.EventClickListener;
import tech.rootsdigital.thryll_integration.interfaces.EventsAdapter;
import tech.rootsdigital.thryll_integration.utils.DateUtils;

/**
 * Created by Shinigami on 1/12/2018.
 */
public class EventsGridAdapter extends BaseAdapter implements EventsAdapter {

    private Context mContext;
    private RequestManager glide;
    private EventClickListener listener;
    private List<Event> eventList;

    public void setListener(EventClickListener listener) {
        this.listener = listener;
    }



    public EventsGridAdapter(Context context, RequestManager glide) {
        mContext = context;
        this.glide = glide;
        this.eventList = new ArrayList<>();
    }

    class ViewHolder {

        View v;
        ImageView image;
        TextView name, date;
    }

    public int getCount() {
        return eventList.size();
    }

    public Event getItem(int position) {
        return eventList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.event_item_small_layout, null);
            holder.v = convertView.findViewById(R.id.main_layout);
            holder.name = convertView.findViewById(R.id.label_event_name);
            holder.date = convertView.findViewById(R.id.label_event_date);
            holder.image = convertView.findViewById(R.id.image_event_pic);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(getItem(position).getEventName());
        holder.date.setText(DateUtils.FormatDateToDayMMdd(getItem(position).getDates().get(0).getDate()));
        glide.load(getItem(position).getImages().get(0)).into(holder.image);

        holder.image.setOnClickListener(v -> {
            Thryll.ProcessEvent(getItem(position), v.getContext());
        });

        return convertView;
    }

    @Override
    public void setEvents(List<Event> posts) {
        this.eventList = posts;
        notifyDataSetChanged();
        Log.i("Posts Adapter", "Number of events passed to adapter: " + posts.size());
    }

    @Override
    public void addEvents(List<Event> posts) {
        this.eventList.addAll(posts);
        notifyDataSetChanged();
        Log.i("Posts Adapter", "Number of events added to adapter: " + posts.size());

    }

    @Override
    public void addEvent(Event event) {

    }

}

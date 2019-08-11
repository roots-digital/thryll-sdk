package tech.rootsdigital.thryll_sample_integration;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import tech.rootsdigital.thryll_integration_sdk.Thryll;
import tech.rootsdigital.thryll_integration_sdk.data.models.Event;
import tech.rootsdigital.thryll_integration_sdk.data.models.RequestResult;
import tech.rootsdigital.thryll_integration_sdk.interfaces.EventsRequestListener;


public class EventsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        final GridView gridView = view.findViewById(R.id.grid_view);
        final ProgressBar progressBar = view.findViewById(R.id.progress_bar);

        gridView.setVisibility(View.GONE);

        new Thryll(requireContext())
                .loadEventsInto(recyclerView)
                .withListener(new EventsRequestListener() {
                    @Override
                    public void OnComplete(RequestResult result) {
                        progressBar.setVisibility(View.GONE);
                    }
                })
                .makeRequest();

        new Thryll(requireContext())
                .loadEventsInto(gridView)
                .withListener(new EventsRequestListener() {
                    @Override
                    public void OnComplete(RequestResult result) {
                        progressBar.setVisibility(View.GONE);
                    }
                })
                .makeRequest();

        final TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_list));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_grid));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    recyclerView.setVisibility(View.VISIBLE);
                    gridView.setVisibility(View.GONE);
                }
                else {
                    gridView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}

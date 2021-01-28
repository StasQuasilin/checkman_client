package checkman.ui.transport;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import checkman.adapters.SimpleAdapter;
import checkman.client.R;
import checkman.entity.Transportation;
import checkman.utils.AdapterViewBuilder;
import checkman.utils.subscribes.SubscribeType;
import checkman.utils.subscribes.Subscriber;

public class TransportFragment extends Fragment {
    private Subscriber<Transportation> subscriber;

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        SimpleAdapter simpleAdapter = new SimpleAdapter((Context)getActivity(), 2131427439, new AdapterViewBuilder<Transportation>() {
            public View build(Transportation param1Transportation, View param1View) {
                return null;
            }
        });
        this.subscriber = new Subscriber(SubscribeType.transportation, simpleAdapter);
        View view = paramLayoutInflater.inflate(R.layout.transport_view, paramViewGroup, false);
        ((ListView)view.findViewById(R.id.transportList)).setAdapter((ListAdapter)simpleAdapter);
        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
    }
}

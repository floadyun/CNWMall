package pv.com.pvcloudgo.vc.view.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.vc.adapter.ShopCirRecyclerViewAdapter;
import pv.com.pvcloudgo.vc.view.ui.fragment.dummy.DummyContent;
import static com.umeng.socialize.utils.DeviceConfig.context;


/**
 * 云商圈seg
 */
public class ShopFragment extends BaseFragment {

    @BindView(R.id.list)
    XRecyclerView mRecyclerView;
    // TODO: Customize parameters

    private OnListFragmentInteractionListener mListener;

    public ShopFragment() {
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cir_funslist, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setAdapter(new ShopCirRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //refresh data here
                new Handler(getActivity().getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.refreshComplete();
                    }
                },3000);
            }

            @Override
            public void onLoadMore() {
                // load more data here
                new Handler(getActivity().getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.loadMoreComplete();
                    }
                },3000);
            }
        });
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallBeat);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallPulseSync);

    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyContent.DummyItem item);
    }
}

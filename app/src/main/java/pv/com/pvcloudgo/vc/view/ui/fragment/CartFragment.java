package pv.com.pvcloudgo.vc.view.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pv.com.pvcloudgo.vc.view.ui.activity.order.CreateOrderActivity;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.vc.adapter.ShopCartAdapter;
import pv.com.pvcloudgo.model.bean.ShoppingCart;
import pv.com.pvcloudgo.vc.view.ui.fragment.dummy.DummyContent;
import pv.com.pvcloudgo.utils.CartProvider;

import static com.umeng.socialize.utils.DeviceConfig.context;


public class CartFragment extends BaseFragment implements View.OnClickListener {


    public static final int ACTION_EDIT = 1;
    public static final int ACTION_CAMPLATE = 2;
    private static final String TAG = "CartFragment";
    @BindView(R.id.recycler_view)
    XRecyclerView mRecyclerView;
    @BindView(R.id.checkbox_all)
    CheckBox mCheckBox;
    @BindView(R.id.btn_order)
    Button mBtnOrder;
    @BindView(R.id.btn_del)
    Button mBtnDel;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_logo)
    ImageView toolbarLogo;
    @BindView(R.id.toolbar_left_logo)
    ImageView toolbarLeftLogo;
    @BindView(R.id.toolbar_left_title)
    TextView toolbarLeftTitle;
    @BindView(R.id.toolbar_right_title)
    TextView toolbarRightTitle;
    @BindView(R.id.image_right)
    ImageView imageRight;
    @BindView(R.id.image_exit)
    ImageView imageExit;


    private ShopCartAdapter mAdapter;
    private CartProvider cartProvider;


    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }


    @Override
    public void init() {

        initXRecyclerView();
        cartProvider = new CartProvider(getActivity());

        changeToolbar();
        showData();
    }

    private void initXRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter=new ShopCartAdapter(DummyContent.ITEMS, null);
        mRecyclerView.setAdapter(mAdapter);
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


    @OnClick(R.id.btn_del)
    public void delCart(View view) {

    }

    @OnClick(R.id.btn_order)
    public void toOrder(View view) {

        Intent intent = new Intent(getActivity(), CreateOrderActivity.class);

        startActivity(intent, true);
    }


    private void showData() {


        List<ShoppingCart> carts = cartProvider.getAll();

//        mAdapter = new CartAdapter(getActivity(), carts, mCheckBox, mTextTotal);
//
//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));



    }


    public void refData() {

        List<ShoppingCart> carts = cartProvider.getAll();


    }


    public void changeToolbar() {
//        toolbarLeftLogo.setImageResource(R.drawable.ic);
        toolbarTitle.setText(R.string.cart);
        toolbarRightTitle.setText("编辑");
        toolbarRightTitle.setOnClickListener(this);
//        toolbarLeftLogo.setVisibility(View.VISIBLE);
//        toolbarLeftLogo.setImageResource(R.drawable.ic_s_msg);

    }
    private void showDelControl() {
        toolbarRightTitle.setText("完成");
        mBtnOrder.setVisibility(View.GONE);
        mBtnDel.setVisibility(View.VISIBLE);
        toolbarRightTitle.setTag(ACTION_CAMPLATE);

        mCheckBox.setChecked(false);
    }

    private void hideDelControl() {
        mBtnOrder.setVisibility(View.VISIBLE);

        mBtnDel.setVisibility(View.GONE);
        toolbarRightTitle.setText("编辑");
        toolbarRightTitle.setTag(ACTION_EDIT);

        mCheckBox.setChecked(true);
    }
    @Override
    public void onClick(View v) {
        int action = (int) v.getTag();
        if (ACTION_EDIT == action) {

            showDelControl();
        } else if (ACTION_CAMPLATE == action) {

            hideDelControl();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}

package pv.com.pvcloudgo.vc.view.ui.activity.other;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.squareup.okhttp.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.vc.base.BaseActivity;
import pv.com.pvcloudgo.vc.base.BaseAdapter;
import pv.com.pvcloudgo.vc.adapter.FavoriteAdatper;
import pv.com.pvcloudgo.vc.adapter.decoration.CardViewtemDecortion;
import pv.com.pvcloudgo.app.App;
import pv.com.pvcloudgo.model.bean.Favorites;
import pv.com.pvcloudgo.http.SpotsCallBack;
import pv.com.pvcloudgo.vc.widget.PVToolBar;
import pv.com.pvcloudgo.utils.Contants;

public class MyFavoriteActivity extends BaseActivity {

    @BindView(R.id.toolbar)
     PVToolBar mToolbar;

    @BindView(R.id.recycler_view)
     RecyclerView mRecyclerview;

    private FavoriteAdatper mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite);
        ButterKnife.bind(this);

        initToolBar();
        getFavorites();
    }
    private void initToolBar(){

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getFavorites(){

        Long userId = App.getInstance().getUser().getId();

        Map<String, Object> params = new HashMap<>();
        params.put("user_id",userId);


        mHttpHelper.get(Contants.API.FAVORITE_LIST, params, new SpotsCallBack<List<Favorites>>(this) {
            @Override
            public void onSuccess(Response response, List<Favorites> favorites) {
                showFavorites(favorites);
            }

            @Override
            public void onError(Response response, int code, Exception e) {

                Log.d("xx","code:"+code);
            }

            @Override
            public void onServerError(Response response, int code, String errmsg) {

            }
        });
    }

    private void showFavorites(List<Favorites> favorites) {

        mAdapter = new FavoriteAdatper(this,favorites);
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.addItemDecoration(new CardViewtemDecortion());

        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }


}

package pv.com.pvcloudgo.vc.view.ui.activity.mine;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.DraweeView;
import com.squareup.okhttp.Response;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import pv.com.pvcloudgo.vc.base.BaseActivity;
import pv.com.pvcloudgo.utils.Contants;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.app.App;
import pv.com.pvcloudgo.model.bean.User;
import pv.com.pvcloudgo.http.SpotsCallBack;
import pv.com.pvcloudgo.model.msg.LoginRespMsg;
import pv.com.pvcloudgo.utils.DESUtil;
import pv.com.pvcloudgo.utils.Utils;


public class PersonalInfoActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_left_logo)
    ImageView toolbarLeftLogo;
    @BindView(R.id.toolbar_logo)
    ImageView toolbarLogo;
    @BindView(R.id.toolbar_left_title)
    TextView toolbarLeftTitle;
    @BindView(R.id.toolbar_right_title)
    TextView toolbarRightTitle;
    @BindView(R.id.image_right)
    ImageView imageRight;
    @BindView(R.id.image_exit)
    ImageView imageExit;
    @BindView(R.id.user_avater)
    DraweeView userAvater;
    @BindView(R.id.user_nickname_tv)
    TextView userNicknameTv;
    @BindView(R.id.user_phone_tv)
    TextView userPhoneTv;
    @BindView(R.id.user_sex_tv)
    TextView userSexTv;
    @BindView(R.id.user_birth_tv)
    TextView userBirthTv;
    @BindView(R.id.user_work_tv)
    TextView userWorkTv;
    @BindView(R.id.user_homeland_tv)
    TextView userHomelandTv;
    @BindView(R.id.user_home_tv)
    TextView userHomeTv;
    @BindView(R.id.user_personid_tv)
    TextView userPersonidTv;
    @BindView(R.id.receip_ll)
    LinearLayout receipLl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinfo);
        ButterKnife.bind(this);


        initToolBar();

        bindData();
    }


    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        setupToolbar(toolbar, true);

        toolbarTitle.setText("个人信息");

    }

    private void bindData() {
        User user = App.getInstance().getUser();
        if(user==null)return;
        Utils.bindStrText(userNicknameTv,user.getNicheng());
        Utils.bindStrText(userPhoneTv,user.getTelPhone());
        Utils.bindStrText(userSexTv,user.getSex()==1?"男":"女");
        Utils.bindStrText(userBirthTv,user.getZhuceDate());
        Utils.bindStrText(userWorkTv,user.getZhiye());
        Utils.bindStrText(userHomeTv,user.getJiatingzhuzhi());
        Utils.bindStrText(userHomelandTv,user.getJiatingzhuzhi());
//        receipLl.setOnClickListener(v ->);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

    public void login(View view) {


        String phone = null;

        String pwd = null;


        Map<String, Object> params = new HashMap<>(2);
        params.put("phone", phone);
        params.put("password", DESUtil.encode(Contants.DES_KEY, pwd));

        mHttpHelper.post(Contants.API.LOGIN, params, new SpotsCallBack<LoginRespMsg<User>>(this) {


            @Override
            public void onSuccess(Response response, LoginRespMsg<User> userLoginRespMsg) {


                App application = App.getInstance();
                application.putUser(userLoginRespMsg.getData(), userLoginRespMsg.getToken());

                if (application.getIntent() == null) {
                    setResult(RESULT_OK);
                    finish();
                } else {

                    application.jumpToTargetActivity(mContext);
                    finish();

                }


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }

            @Override
            public void onServerError(Response response, int code, String errmsg) {

            }
        });
    }
}

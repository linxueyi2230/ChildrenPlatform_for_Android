package com.neusoft.yl.childrenplatform.Activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;

import com.neusoft.yl.childrenplatform.Adapters.CollectListAdapter;
import com.neusoft.yl.childrenplatform.Base.RecyclerViewForEmpty;
import com.neusoft.yl.childrenplatform.Bean.CollectBean;
import com.neusoft.yl.childrenplatform.Listener.RetrofitListener;
import com.neusoft.yl.childrenplatform.Model.UserModel;
import com.neusoft.yl.childrenplatform.R;

import java.util.List;

public class CollectActivity extends BaseActivity implements RetrofitListener<List<CollectBean>>{

    private Button back_btn;
    private RecyclerViewForEmpty collect_recycle;
    private View emptyView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    void initViews() {
        back_btn = findViewById(R.id.back_btn);
        collect_recycle =  findViewById(R.id.collect_recycle);
        emptyView = findViewById(R.id.id_empty_view);
        swipeRefreshLayout = findViewById(R.id.srl);
    }

    @Override
    void initEvents() {
    }

    @Override
    public void initView() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initData() {
        UserModel userModel = new UserModel();
        userModel.getUuserCollect(getUser_id(),this);
    }

    @Override
    public void onSuccess(List<CollectBean> collectBeen, int flag) {

        CollectListAdapter adapter = new CollectListAdapter(this,collectBeen);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        collect_recycle.setAdapter(adapter);
        collect_recycle.setEmptyView(emptyView);
        collect_recycle.setLayoutManager(layoutManager);
        collect_recycle.setItemAnimator(new DefaultItemAnimator());
        //取消刷新读条
        swipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onFail() {
        showToast("网路错误");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_collect);
        initViews();
        swipeRefreshLayout.setRefreshing(true);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.color_pink);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });

    }
}

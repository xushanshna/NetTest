package com.example.nettest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.nettest.bean.WxBean;
import com.example.nettest.net.Fault;
import com.example.nettest.net.RetrofitHelper;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_tv)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        testWanAndroid();
    }

    private void testWanAndroid() {
        RetrofitHelper helper = new RetrofitHelper();
        helper.getWxList()
                .subscribe(new Action1<List<WxBean>>() {
                    @Override
                    public void call(List<WxBean> wxBeans) {
                        textView.setText("收到数据\n" + wxBeans.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.d("收到异常：%s", throwable.getMessage());
                        //根据返回的错误码处理错误
                        if (throwable instanceof Fault) {
                            Logger.d("异常码：%s\n异常信息：%s",
                                    ((Fault) throwable).getErrorStatus(),
                                    ((Fault) throwable).getErrorMessage());
                        }
                    }
                });

    }

}

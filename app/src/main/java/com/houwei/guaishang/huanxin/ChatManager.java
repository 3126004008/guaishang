package com.houwei.guaishang.huanxin;

import android.content.Context;

import com.houwei.guaishang.activity.newui.MyInfoActivity;
import com.houwei.guaishang.bean.BaseResponse;
import com.houwei.guaishang.bean.LocationBean;
import com.houwei.guaishang.bean.MyInfoBean;
import com.houwei.guaishang.bean.event.TopicHomeEvent;
import com.houwei.guaishang.data.Contants;
import com.houwei.guaishang.tools.DealResult;
import com.houwei.guaishang.tools.HttpUtil;
import com.houwei.guaishang.tools.JsonParser;
import com.houwei.guaishang.tools.SPUtils;
import com.houwei.guaishang.tools.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.schedulers.Schedulers;

/**
 * Created by ${lei} on 2018/5/14.
 */

public class ChatManager {
    private Context context;

    public ChatManager(Context context) {
        this.context = context;
    }

    /**
     *
     * @param cid 抢单方id
     * @param sid 发单方id
     * @param orderid 订单id
     */
    private  OrderInfoResponse orderInfoResponse;
    public OrderInfoResponse queryOffer(String cid, String sid, final String orderid) {

        OkGo.<String>get(HttpUtil.IP+"topic/rob")
                .params("order_id",orderid)
                .params("user_id",cid)
                .params("offer_id",sid)
//					.params("payMoney",payMoney)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        orderInfoResponse = null;
                        orderInfoResponse = DealResult.getInstace().dealBean(context, response, OrderInfoResponse.class);
                    }
                });

        return orderInfoResponse;
    }

    //报价
    public void offer(LocationBean currentLocationBean,String price,String circle,String cid,String sid,String orderid) {
        String city;
        if (currentLocationBean == null) {
            city = (String) SPUtils.get(context, Contants.LOCATION_CITY_KEY, "上海市");

        } else {
            city = currentLocationBean.getCity() + currentLocationBean.getDistrict();
        }

        offerMsg(orderid,cid,sid,price,circle,circle,city);
    }
    private void offerMsg(String orderid,String cid,String sid,String money, String time,String remark, String address) {

        OkGo.<String>post(HttpUtil.IP+"topic/rob")
                .params("order_id",orderid)
                .params("user_id",cid)
                .params("offer_id",sid)
                .params("price",money)
                .params("cycle",time)
                .params("address",address)
                .params("beizhu",remark)
//					.params("payMoney",payMoney)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        BaseResponse baseResponse= DealResult.getInstace().dealBase(context,response);
                        if(baseResponse==null){
                            return;
                        }
                        if(baseResponse.getCode()==1){
                            ToastUtils.toastForShort(context,baseResponse.getMessage());
                            try {
                                EventBus.getDefault().post(new TopicHomeEvent());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

    //打款
    public void remit(){

    }


}

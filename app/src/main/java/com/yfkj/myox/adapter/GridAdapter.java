package com.yfkj.myox.adapter;

import android.app.Activity;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.yfkj.myox.R;
import com.yfkj.myox.bean.LookBean;
import java.util.List;
import card.com.allcard.net.HttpRequestPort;

/**
 * @author wmx
 */
public class GridAdapter extends CommonAdapter<LookBean.ResultBean.ListBean> {
    private Activity act;

    public GridAdapter(Activity act, List<LookBean.ResultBean.ListBean> data, int layoutId) {
        super(act, data, layoutId);
        this.act = act;
    }

    @Override
    public void convert(ViewHolder holder, LookBean.ResultBean.ListBean data) {
        holder.setText(R.id.name, data.getPictureName());
        ImageView view = holder.getView(R.id.im);
        if ("png".equals(data.getRemark1())) {
            Glide.with(act).load(HttpRequestPort.IM_URL + data.getAbsoluteUrl()).into(view);
        } else {
            holder.setImageResource(R.id.im, R.drawable.img_spt);
        }
    }
}

package androiddeveloper.hazzaa.yasser.elsyanaedaraa.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import androiddeveloper.hazzaa.yasser.elsyanaedaraa.R;
import androiddeveloper.hazzaa.yasser.elsyanaedaraa.model.ShowIOS;

public class ordersAdapter extends RecyclerView.Adapter<ordersAdapter.ViewHolder> {


    private List<ShowIOS> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public ordersAdapter(Context context, List<ShowIOS> data, ItemClickListener mClickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mClickListener = mClickListener;
    }

    // data is passed into the constructor
    public ordersAdapter(Context context, List<ShowIOS> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.orderitem, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ShowIOS mShowIOS = mData.get(position);
        holder.TxtTitle.setText(mShowIOS.getName());
        holder.TxtRegion.setText(mShowIOS.getAddress());
        holder.TxtPhone.setText(mShowIOS.getPhone());
        Picasso.get().load("http://maintenanceksa.com/WebServices/images/" + mShowIOS.getImg()).placeholder(R.drawable.no_image).into(holder.imageView);
        if (mShowIOS.getView() != null) {
            if (mShowIOS.getView().equals("0")) {

                if (mShowIOS.getType().equals("1")) {
                    holder.Viewd.setImageResource(R.color.color1);
                    //Picasso.get().load(R.color.color1).into(holder.Viewd);
                } else if (mShowIOS.getType().equals("2")) {
                    holder.Viewd.setImageResource(R.color.color2);
                    //Picasso.get().load(R.color.color2).into(holder.Viewd);
                } else if (mShowIOS.getType().equals("3")) {
                    holder.Viewd.setImageResource(R.color.color3);
                    //Picasso.get().load(R.color.color3).into(holder.Viewd);
                } else if (mShowIOS.getType().equals("4")) {
                    holder.Viewd.setImageResource(R.color.color4);
                    //Picasso.get().load(R.color.color4).into(holder.Viewd);
                } else if (mShowIOS.getType().equals("5")) {
                    holder.Viewd.setImageResource(R.color.color5);
                    //Picasso.get().load(R.color.color5).into(holder.Viewd);
                } else {
                    holder.Viewd.setImageResource(R.color.color6);
                    //Picasso.get().load(R.color.color6).into(holder.Viewd);
                }
            }else{
                holder.Viewd.setVisibility(View.INVISIBLE);
            }
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView TxtTitle, TxtRegion, TxtPhone;
        ImageView imageView, Viewd;

        ViewHolder(final View itemView) {
            super(itemView);
            TxtTitle = itemView.findViewById(R.id.xtitle);
            TxtRegion = itemView.findViewById(R.id.TxtAddress);
            TxtPhone = itemView.findViewById(R.id.TxtPhone);
            imageView = itemView.findViewById(R.id.image);
            Viewd = itemView.findViewById(R.id.viewd);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    ShowIOS getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

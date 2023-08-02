package cs.hku.wetrade;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    private List<HomeItem> mList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView id, name, stock, price, category, seller;
        ImageView image;
        public ViewHolder(View view){
            super(view);
            id = (TextView)view.findViewById(R.id.tid);
            name = (TextView)view.findViewById(R.id.tname);
            image = (ImageView)view.findViewById(R.id.timage);
            stock = (TextView)view.findViewById(R.id.tstock);
            price = (TextView)view.findViewById(R.id.tprice);
            category = (TextView)view.findViewById(R.id.tcategory);
            seller = (TextView)view.findViewById(R.id.tseller);
        }
    }
    public HomeAdapter(List<HomeItem>homeItemList){
        mList = homeItemList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HomeItem item = mList.get(position);
        holder.id.setText(String.valueOf(item.getId()));
        holder.name.setText(item.getName());
        holder.image.setImageBitmap(item.getImage());
        holder.stock.setText(String.valueOf(item.getStock()));
        holder.price.setText(String.valueOf(item.getPrice()));
        holder.category.setText(item.getCategory());
        holder.seller.setText(item.getSeller());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
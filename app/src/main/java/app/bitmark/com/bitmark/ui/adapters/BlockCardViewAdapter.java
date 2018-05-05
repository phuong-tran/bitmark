package app.bitmark.com.bitmark.ui.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.bitmark.com.bitmark.BlockCache;
import app.bitmark.com.bitmark.R;
import app.bitmark.com.bitmark.network.response.block.detail.BlockDetail;

public class BlockCardViewAdapter extends RecyclerView.Adapter<BlockCardViewAdapter.BlockCardViewHolder> {

   private Context mContext;
   private List<BlockDetail> blockDetails = new ArrayList<>(BlockCache.getBlockDetails());
   public static class BlockCardViewHolder extends RecyclerView.ViewHolder {
       public TextView tvBlockNumber, tvDescription;
       public BlockCardViewHolder(View view) {
           super(view);
           tvBlockNumber = view.findViewById(R.id.tvBlockNumber);
           tvDescription = view.findViewById(R.id.tvDescription);
       }
   }

    @NonNull
    @Override
    public BlockCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.block_item, parent, false);
        return new BlockCardViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull BlockCardViewHolder holder, int position) {
        BlockDetail blockDetail = blockDetails.get(position);
        holder.tvBlockNumber.setText(blockDetail.getBlockNumber().toString());
        holder.tvDescription.setText(blockDetail.getDescription());
    }


    @Override
    public int getItemCount() {
        return blockDetails.size();
    }

    public BlockCardViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public List<BlockDetail> getBlockDetails() {
        return blockDetails;
    }

    public void setBlockDetails(List<BlockDetail> blockDetails) {
        this.blockDetails = blockDetails;
    }
}

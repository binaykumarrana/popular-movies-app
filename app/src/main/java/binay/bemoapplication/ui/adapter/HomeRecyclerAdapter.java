package binay.bemoapplication.ui.adapter;

import android.content.Context;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import binay.bemoapplication.R;
import binay.bemoapplication.ui.adapter.holder.MovieViewHolder;
import binay.bemoapplication.ui.interfaces.ItemClickListener;
import binay.bemoapplication.ui.model.MovieResultsModel;
import binay.bemoapplication.utils.PaletteTransformation;

/**
 * Created by Binay on 15/06/17.
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<MovieResultsModel> itemList;
    private List<MovieResultsModel> allObjects;
    private Context context;
    ItemClickListener itemClickListener;

    public HomeRecyclerAdapter(Context context, List<MovieResultsModel> itemList, ItemClickListener itemClickListener) {
        this.itemList = itemList;
        this.allObjects = itemList;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_dashboard, null);
        MovieViewHolder rcv = new MovieViewHolder(layoutView, itemClickListener);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {
        if (itemList.get(position).getBackdrop_path() != null) {
            holder.itemLabel.setText(itemList.get(position).getTitle());
            String imgUrl = "http://image.tmdb.org/t/p/" + "w342" + itemList.get(position).getBackdrop_path();
            holder.itemImage.setTag(itemList.get(position));
            Picasso.with(context).load(imgUrl).placeholder(R.drawable.ic_placeholder_movie).error(R.drawable.ic_placeholder_movie).transform(PaletteTransformation.instance()).into(holder.itemImage, new PaletteTransformation.PaletteCallback(holder.itemImage) {
                @Override
                protected void onSuccess(Palette palette) {
                    Palette.Swatch vibrant = palette.getDarkVibrantSwatch();
                    if (vibrant != null) {
                        // Update the title TextView with the proper text color
                        holder.itemLabel.setBackgroundColor(vibrant.getRgb());
                    }
                }

                @Override
                public void onError() {

                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public MovieResultsModel getItemAtPos(int pos) {
        return itemList.get(pos);
    }
}


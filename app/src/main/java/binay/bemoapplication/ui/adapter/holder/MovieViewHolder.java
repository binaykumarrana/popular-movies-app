package binay.bemoapplication.ui.adapter.holder;

import android.app.ActivityOptions;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import binay.bemoapplication.R;
import binay.bemoapplication.ui.interfaces.ItemClickListener;
import binay.bemoapplication.ui.model.MovieResultsModel;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Binay on 15/06/17.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.itemImage)
    public ImageView itemImage;
    @BindView(R.id.itemLabel)
    public TextView itemLabel;
    ActivityOptions options = null;
    ItemClickListener itemClickListener;

    public MovieViewHolder(View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemClickListener = itemClickListener;

        itemImage.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        MovieResultsModel model = (MovieResultsModel) view.getTag();

        if(itemClickListener!=null)
        {
            itemClickListener.movieClicked(model,view);
        }

    }


}

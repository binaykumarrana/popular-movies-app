package binay.bemoapplication.ui.interfaces;

import android.view.View;

import binay.bemoapplication.ui.model.MovieResultsModel;

/**
 * Created by Binay on 15/06/17.
 */

public interface ItemClickListener {
    void movieClicked(MovieResultsModel model, View view);
}

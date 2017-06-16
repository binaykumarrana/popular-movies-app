package binay.bemoapplication.ui.interfaces;

import android.view.View;

import binay.bemoapplication.ui.model.MovieResultsModel;

/**
 * Created by Binay on 15/06/17.
 */

public interface MovieDetailsFragmentInterface {
    void loadDetailsFragment(View v, String imageUrl, MovieResultsModel model);
}

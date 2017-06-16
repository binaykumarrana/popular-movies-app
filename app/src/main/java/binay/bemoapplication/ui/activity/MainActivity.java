package binay.bemoapplication.ui.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;

import binay.bemoapplication.R;
import binay.bemoapplication.ui.fragments.MovieDetailsFragment;
import binay.bemoapplication.ui.fragments.MovieGridFragment;
import binay.bemoapplication.ui.interfaces.ItemClickListener;
import binay.bemoapplication.ui.model.MovieResultsModel;
import butterknife.BindBool;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    @BindView(R.id.dashboard_toolbar)
    Toolbar dashboardToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    @BindView(R.id.fragmentGridContainer)
    FrameLayout fragmentContainer;
    @BindString(R.string.popularMovies)
    String pMovieTag;
    @BindString(R.string.app_name)
    String appName;
    @BindBool(R.bool.portrait)
    boolean isPortrait;


    MovieGridFragment gridFragment;
    MovieDetailsFragment detailsFragment;
    ArrayAdapter<String> menuSortAdapter;
    MaterialDialog sortDialog;


    FragmentTransaction transaction;
    private ActivityOptions options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolbar();
        setUpViews();
    }

    private void setUpViews() {


        if (isPortrait) {
            transaction = getSupportFragmentManager().beginTransaction();
            gridFragment = (MovieGridFragment) getSupportFragmentManager().findFragmentByTag("fragGrid");
            if (gridFragment == null)
                gridFragment = new MovieGridFragment();
            transaction.replace(R.id.fragmentGridContainer, gridFragment, "fragGrid");
            transaction.commit();
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            transaction = getSupportFragmentManager().beginTransaction();
            gridFragment = (MovieGridFragment) getSupportFragmentManager().findFragmentByTag("fragGrid");
            if (gridFragment == null)
                gridFragment = new MovieGridFragment();
            transaction.replace(R.id.fragmentGridContainer, gridFragment, "fragGrid");
            transaction.commit();
            if (findViewById(R.id.fragmentDetailContainer) != null) {
                transaction = getSupportFragmentManager().beginTransaction();
                detailsFragment = (MovieDetailsFragment) getSupportFragmentManager().findFragmentByTag("fragDetails");
                if (detailsFragment == null)
                    detailsFragment = new MovieDetailsFragment();
                transaction.replace(R.id.fragmentDetailContainer, detailsFragment, "fragDetails");
                transaction.commit();

            }
        }
    }


    private void setupToolbar() {
        setSupportActionBar(dashboardToolbar);
        getSupportActionBar().setTitle(appName);
    }



    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public void loadDetailsFragment(View v, String imageUrl, MovieResultsModel model) {
        if (findViewById(R.id.fragmentDetailContainer) != null) {

            transaction = getSupportFragmentManager().beginTransaction();
            Bundle args = new Bundle();
            args.putString("imageUrl", imageUrl);
            args.putParcelable("movieObject", model);
            detailsFragment = MovieDetailsFragment.newInstance(args);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                detailsFragment.setSharedElementReturnTransition(TransitionInflater.from(MainActivity.this).inflateTransition(R.transition.trans_move));
                detailsFragment.setExitTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.fade));
                transaction.addSharedElement(v, "toolbarImage");
            }

            transaction.replace(R.id.fragmentDetailContainer, detailsFragment, "fragDetails");
            transaction.commit();
        }
    }

    @Override
    public void movieClicked(MovieResultsModel model, View view) {
        String imgUrl;

        if (isPortrait) {
            Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
            imgUrl = "http://image.tmdb.org/t/p/" + "w342" + model.getBackdrop_path();
            intent.putExtra("imageUrl", imgUrl);
            intent.putExtra("movieObject", model);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, view, "toolbarImage");
                MainActivity.this.startActivity(intent, options.toBundle());
            } else {
                MainActivity.this.startActivity(intent);
            }

        } else {
            imgUrl = "http://image.tmdb.org/t/p/" + "w342" + model.getBackdrop_path();

            loadDetailsFragment(view, imgUrl, model);

        }

    }
}


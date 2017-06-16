package binay.bemoapplication;

import android.app.Application;

/**
 * Created by Binay on 15/06/17.
 */

public class BemoApplication extends Application {
    public static BemoApplication application;

    public static BemoApplication getApplication() {
        return application;
    }
}

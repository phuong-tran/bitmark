
package app.bitmark.com.bitmark;

import android.content.Context;
import android.widget.Toast;

public class Utils {
   public  static void showNetworkError(Context context) {
       Toast.makeText(context, context.getText(R.string.network_error), Toast.LENGTH_LONG).show();
   }
}


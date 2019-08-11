package tech.rootsdigital.thryll_integration.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Layout;
import android.widget.TextView;

public class TextUtils {
    public static boolean isTextTruncated(String text, TextView textView) {

        if (!(textView == null || text == null)) {

            Layout layout = textView.getLayout();

            if (layout != null) {

                int lines = layout.getLineCount();

                if (lines > 0 && layout.getEllipsisCount(lines - 1) > 0) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void openLink(String link, Context context){

        Uri uri = Uri.parse(link);

        Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(sendIntent);
    }
}

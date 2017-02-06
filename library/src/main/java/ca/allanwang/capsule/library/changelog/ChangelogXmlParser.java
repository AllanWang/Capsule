package ca.allanwang.capsule.library.changelog;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.XmlRes;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import ca.allanwang.capsule.library.logging.CLog;

/**
 * @author Allan Wang
 */
class ChangelogXmlParser {

    static ArrayList<ChangelogItem> parse(@NonNull Context context, @XmlRes int xmlRes) {
        ArrayList<ChangelogItem> mChangelogItems = new ArrayList<>();

        XmlResourceParser parser = null;
        try {
            parser = context.getResources().getXml(xmlRes);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        final String tagName = parser.getName();
                        if (tagName.equalsIgnoreCase("version")) {
                            if (!parser.getAttributeValue(null, "title").isEmpty()) {
                                mChangelogItems.add(new ChangelogItem(parser.getAttributeValue
                                        (null, "title"), true));
                            }
                        } else if (tagName.equalsIgnoreCase("item")) {
                            try {
                                if (!parser.getAttributeValue(null, "text").isEmpty()) {
                                    mChangelogItems.add(new ChangelogItem(parser
                                            .getAttributeValue(null, "text"), false));
                                }
                            } catch (Exception e) {
                                throw new UnsupportedOperationException("The tag you used is not " +
                                        "supported. Be sure to use \"text\" tag");
                            }
                        }
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        } finally {
            if (parser != null)
                parser.close();
        }
        CLog.d("Returning parsed changelog xml");
        return mChangelogItems;
    }

    static class ChangelogItem implements Parcelable {

        public static final Creator<ChangelogItem> CREATOR = new Creator<ChangelogItem>() {
            @Override
            public ChangelogItem createFromParcel(Parcel source) {
                return new ChangelogItem(source);
            }

            @Override
            public ChangelogItem[] newArray(int size) {
                return new ChangelogItem[size];
            }
        };
        private final String text;
        private final boolean isTitle;

        ChangelogItem(String text, boolean isTitle) {
            this.text = text;
            this.isTitle = isTitle;
        }

        protected ChangelogItem(Parcel in) {
            this.text = in.readString();
            this.isTitle = in.readByte() != 0;
        }

        public String getText() {
            return text;
        }

        public boolean isTitle() {
            return isTitle;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.text);
            dest.writeByte(this.isTitle ? (byte) 1 : (byte) 0);
        }
    }
}
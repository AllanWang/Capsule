package com.pitchedapps.capsule.library.changelog;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.XmlRes;
import android.util.Log;

import com.pitchedapps.capsule.library.R;
import com.pitchedapps.capsule.library.logging.CLog;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Allan Wang
 */
class ChangelogXmlParser {

    static class ChangelogItem implements Parcelable {

        private final String mTitle;
        private final ArrayList<String> mPoints;

        ChangelogItem(String name) {
            mTitle = name;
            mPoints = new ArrayList<>();
        }

        public String getTitle() {
            return mTitle;
        }

        List<String> getItems() {
            return mPoints;
        }

        void addItem(String s) {
            mPoints.add(s);
        }

        public int size() {
            return mPoints.size();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.mTitle);
            dest.writeStringList(this.mPoints);
        }

        ChangelogItem(Parcel in) {
            this.mTitle = in.readString();
            this.mPoints = in.createStringArrayList();
        }

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
    }

    static ArrayList<ChangelogItem> parse(@NonNull Context context, @XmlRes int xmlRes) {
        ChangelogItem mCurrentChangelogItem = null;
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
                                mCurrentChangelogItem = new ChangelogItem(parser.getAttributeValue(null, "title"));
                                mChangelogItems.add(mCurrentChangelogItem);
                            }
                        } else if (tagName.equalsIgnoreCase("item")) {
                            if (!parser.getAttributeValue(null, "text").isEmpty()) {
                                if (mCurrentChangelogItem == null) {
                                    mCurrentChangelogItem = new ChangelogItem(context.getString(R.string.default_new_version_title));
                                    mChangelogItems.add(mCurrentChangelogItem);
                                }
                                mCurrentChangelogItem.addItem(parser.getAttributeValue(null, "text"));
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
}
package com.example.assemalturifi.week2day2;

import android.provider.BaseColumns;

public class MassageContract {
    public static final String NAME="Person.db";
    public static final int VERSION=1;

    public static final String CREATE_TABLE="CREATE TABLE "+
            FeedEntry.TABLE_NAME+" ("+
            FeedEntry.COL_ID+" Text,"+
            FeedEntry.COL_DESK+" Text,"+
            FeedEntry.COL_PHOTO+" Text)";

    public static final String GET_ALL="SELECT * FROM "+FeedEntry.TABLE_NAME;

    public static class FeedEntry  implements BaseColumns {

        public static final String TABLE_NAME="image";
        public static final String COL_ID="id";
        public static final String COL_DESK="desk";
        public static final String COL_PHOTO="photo";
    }

}

package com.example.habittracker;

import android.provider.BaseColumns;

/**
 * Created by root on 5/20/17.
 */

public class HabitsContract {

    private HabitsContract() {
    }

    /**
     * Class containing the constants for HabitTracker Table
     * */
    public static abstract class HabitsEntry implements BaseColumns{

        // habit tracker  table name
        public static final String TABLE_NAME = "habits";
        // id which is unique and auto increment
        public static final String _ID = BaseColumns._ID;
        // name constant for habit name
        public static final String HABIT_NAME = "name";
        // habit module constant for habit module name
        public static final String HABIT_MODULE_NAME = "moduleName";
        // resource constant for habit source
        public static final String HABIT_RESOURCE = "resource";
        // TimeSpent constant for habit time spent
        public static final String HABIT_TIME_SPENT = "timeSpent";
        // isLearned constant for habit lesson Finished
        public static final String HABIT_IS_MODULE_FINISHED = "isModuleLearned";


        /**
         * Constants for the boolean is habit module took is finished
         * Module is nothing but a task taken inside the habit
         */
        public static final int IS_MODULE_COMPLETED = 1;
        public static final int IS_MODULE_NOT_COMPLETED = 2;

    }
}

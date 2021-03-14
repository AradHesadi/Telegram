package org.telegram.messenger.sendmesages;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.TestTools;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.ui.LaunchActivity;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/*
        a simple test class which checks if sent messages have been inserted to local database
        it sends messages to a specific peer (to the owner of account) and
        checks count of messages before and after sending them
*/

@RunWith(AndroidJUnit4.class)
public class SendMessagesHelperTest {

    public static final int ACCOUNT_INSTANCE = 0;
    public static final String MSG = "test-message";
    public static final int THREAD_SLEEP_TIME = 2; //in second
    public static final int MSG_COUNT = 5;

    @Rule
    public ActivityTestRule<LaunchActivity> activityRule
            = new ActivityTestRule<>(
            LaunchActivity.class,
            true,     // initialTouchMode
            false);   // launchActivity. False to customize the intent

    @Test
    public void sendMessage() {
        try {
            activityRule.launchActivity(new Intent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!UserConfig.getInstance(ACCOUNT_INSTANCE).isClientActivated()) { //to check if user logged in
            fail();
        }
        long peer = UserConfig.getInstance(ACCOUNT_INSTANCE).getCurrentUser().id;
        final int countBefore = getMessagesCount(peer);

        SendMessagesHelper sendMessagesHelper = SendMessagesHelper.getInstance(ACCOUNT_INSTANCE);
        ConnectionsManager connectionsManager = ConnectionsManager.getInstance(ACCOUNT_INSTANCE);

        while (connectionsManager.getConnectionState() != ConnectionsManager.ConnectionStateConnected) {
            TestTools.sleep();
        }

        for (int i = 0; i < MSG_COUNT; i++) {
            if (connectionsManager.getConnectionState() == ConnectionsManager.ConnectionStateConnected)
                sendMessagesHelper.sendMessage(MSG + (i+1), peer, null, null, null, false, null, null, null, false, 0);
            TestTools.sleep(THREAD_SLEEP_TIME);
        }

        final int countAfter = getMessagesCount(peer);
        assertEquals(countBefore + MSG_COUNT, countAfter);
    }

    private int getMessagesCount(long peer) {
        SQLiteDatabase database = MessagesStorage.getInstance(ACCOUNT_INSTANCE).getDatabase();
        int count = 0;
        try {
            SQLiteCursor cursor = database.queryFinalized(String.format(Locale.US, "SELECT COUNT(*) FROM messages WHERE uid = %d", peer));
            if (cursor.next()) {
                count = cursor.intValue(0);
            }
            cursor.dispose();
        } catch (Exception e) {
            FileLog.e(e);
        }
        return count;
    }
}
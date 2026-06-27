/**
 * ANDROID ACTIVITY LIFECYCLE - CONCEPT DEMO (Plain Java, no Android SDK)
 *
 * In real Android development, an Activity is a single screen with a UI.
 * Android manages Activity instances through a well-defined lifecycle.
 * The OS can pause, stop, or even destroy your Activity at any time
 * (e.g., incoming phone call, user pressing Home, screen rotation).
 *
 * Why the lifecycle matters:
 *  - onCreate()  : Initialize UI, set up data structures, bind views
 *  - onStart()   : Activity becomes visible (but may not be in foreground)
 *  - onResume()  : Activity is in foreground and interactive — START ANIMATIONS, SENSORS
 *  - onPause()   : Activity partially obscured — STOP SENSORS, save draft data
 *  - onStop()    : Activity fully hidden — release heavy resources
 *  - onDestroy() : Activity being destroyed — final cleanup
 *
 * Configuration changes (screen rotation, language change) destroy and
 * recreate the Activity unless you handle them explicitly with ViewModel.
 * This is why Android ViewModel was invented — to survive rotation!
 *
 * Real Android: class MyActivity extends AppCompatActivity { ... }
 * This demo: plain Java state machine simulating the same behavior.
 */
public class ActivityLifecycleConcept {

    // Enum representing all possible Activity states
    enum LifecycleState {
        CREATED,
        STARTED,
        RESUMED,
        PAUSED,
        STOPPED,
        DESTROYED,
        NON_EXISTENT  // Before onCreate or after onDestroy
    }

    // Simulated Activity
    static class SimulatedActivity {
        private String name;
        private LifecycleState currentState;
        private String savedInstanceData; // simulates savedInstanceState Bundle

        SimulatedActivity(String name) {
            this.name = name;
            this.currentState = LifecycleState.NON_EXISTENT;
        }

        void onCreate(String restoredData) {
            assertTransition(LifecycleState.NON_EXISTENT, LifecycleState.CREATED);
            currentState = LifecycleState.CREATED;
            System.out.println("[" + name + "] onCreate() called");
            if (restoredData != null) {
                System.out.println("  -> Restoring saved state: \"" + restoredData + "\"");
            } else {
                System.out.println("  -> Fresh start, no saved state");
            }
            System.out.println("  -> Initialize UI components, set content view");
            System.out.println("  -> Set up ViewModel, observe LiveData");
        }

        void onStart() {
            assertTransition(LifecycleState.CREATED, LifecycleState.STARTED);
            currentState = LifecycleState.STARTED;
            System.out.println("[" + name + "] onStart() called");
            System.out.println("  -> Activity is now VISIBLE to user");
            System.out.println("  -> Register broadcast receivers");
        }

        void onResume() {
            assertTransition(LifecycleState.STARTED, LifecycleState.RESUMED);
            currentState = LifecycleState.RESUMED;
            System.out.println("[" + name + "] onResume() called");
            System.out.println("  -> Activity is in FOREGROUND, user can interact");
            System.out.println("  -> Start camera, sensors, animations");
            System.out.println("  -> Current state: " + currentState);
        }

        void onPause() {
            assertTransition(LifecycleState.RESUMED, LifecycleState.PAUSED);
            currentState = LifecycleState.PAUSED;
            System.out.println("[" + name + "] onPause() called");
            System.out.println("  -> Activity partially hidden (dialog, another app coming)");
            System.out.println("  -> STOP sensors, pause video, save unsaved user input");
            System.out.println("  -> Keep this method FAST — next Activity waits for it");
        }

        void onStop() {
            assertTransition(LifecycleState.PAUSED, LifecycleState.STOPPED);
            currentState = LifecycleState.STOPPED;
            System.out.println("[" + name + "] onStop() called");
            System.out.println("  -> Activity fully hidden");
            System.out.println("  -> Release heavy resources (heavy bitmaps, network connections)");
            System.out.println("  -> Save persistent data to database");
            savedInstanceData = "user_scroll_position=42;draft_text=Hello";
            System.out.println("  -> Saving instance state: \"" + savedInstanceData + "\"");
        }

        void onDestroy() {
            assertTransition(LifecycleState.STOPPED, LifecycleState.DESTROYED);
            currentState = LifecycleState.DESTROYED;
            System.out.println("[" + name + "] onDestroy() called");
            System.out.println("  -> Activity is being destroyed");
            System.out.println("  -> Final cleanup — cancel coroutines, close connections");
        }

        // Simulate going back to foreground from stopped state
        void onRestart() {
            if (currentState != LifecycleState.STOPPED) {
                throw new IllegalStateException("onRestart() can only be called from STOPPED state, currently: " + currentState);
            }
            System.out.println("[" + name + "] onRestart() called");
            System.out.println("  -> Activity coming back from background");
            currentState = LifecycleState.CREATED; // transitions to onStart next
        }

        String getSavedData() { return savedInstanceData; }
        LifecycleState getState() { return currentState; }

        private void assertTransition(LifecycleState expected, LifecycleState next) {
            if (currentState != expected) {
                throw new IllegalStateException(
                    "Invalid lifecycle transition to " + next +
                    ": expected current state " + expected + " but was " + currentState
                );
            }
        }
    }

    static void printSection(String title) {
        System.out.println("\n" + "=".repeat(55));
        System.out.println("  " + title);
        System.out.println("=".repeat(55));
    }

    public static void main(String[] args) throws InterruptedException {

        printSection("SCENARIO 1: Normal Activity Launch & Exit");

        SimulatedActivity mainActivity = new SimulatedActivity("MainActivity");
        mainActivity.onCreate(null);
        mainActivity.onStart();
        mainActivity.onResume();

        System.out.println("\n  [App is running — user is interacting...]\n");
        Thread.sleep(500);

        // User presses Home button
        System.out.println("  [User presses HOME button]");
        mainActivity.onPause();
        mainActivity.onStop();

        // User comes back to the app
        System.out.println("\n  [User returns to app from Recent Apps]");
        mainActivity.onRestart();
        mainActivity.onStart();
        mainActivity.onResume();

        System.out.println("\n  [User presses BACK button — exits app]");
        mainActivity.onPause();
        mainActivity.onStop();
        mainActivity.onDestroy();

        // ------------------------------------------------------------------
        printSection("SCENARIO 2: Configuration Change (Screen Rotation)");

        SimulatedActivity rotatingActivity = new SimulatedActivity("RotatingActivity");
        rotatingActivity.onCreate(null);
        rotatingActivity.onStart();
        rotatingActivity.onResume();

        System.out.println("\n  [User ROTATES the device — configuration change!]");
        System.out.println("  Android destroys and recreates the Activity automatically.");
        System.out.println("  Without ViewModel, all in-memory data is LOST.");

        rotatingActivity.onPause();
        rotatingActivity.onStop();
        rotatingActivity.onDestroy();

        String savedData = rotatingActivity.getSavedData();

        System.out.println("\n  [Android recreates the Activity after rotation]");
        SimulatedActivity recreatedActivity = new SimulatedActivity("RotatingActivity (recreated)");
        recreatedActivity.onCreate(savedData); // state is restored via Bundle
        recreatedActivity.onStart();
        recreatedActivity.onResume();

        System.out.println("\n  KEY INSIGHT: This destroy-recreate cycle is why");
        System.out.println("  Android ViewModel exists — it SURVIVES rotation!");
        System.out.println("  See android_mvvm_pattern_demo.java for the solution.");

        // Clean up
        recreatedActivity.onPause();
        recreatedActivity.onStop();
        recreatedActivity.onDestroy();

        System.out.println("\nDemo complete. See ANDROID_REAL_SETUP.md to run this on a real device.");
    }
}

import java.util.ArrayList;
import java.util.List;

/**
 * ANDROID MVVM PATTERN - CONCEPT DEMO (Plain Java, no Android SDK)
 *
 * MVVM = Model - View - ViewModel
 *
 * This is the OFFICIAL recommended architecture for Android apps (Google's Android Jetpack).
 *
 * HOW IT MAPS TO REAL ANDROID:
 * ┌─────────────────────────────────────────────────────────────┐
 * │  This Demo           │  Real Android                        │
 * │─────────────────────│──────────────────────────────────────│
 * │  Observer interface  │  androidx.lifecycle.Observer<T>      │
 * │  Observable<T>       │  androidx.lifecycle.LiveData<T>      │
 * │  UserViewModel       │  extends androidx.lifecycle.ViewModel│
 * │  SimulatedView       │  Activity/Fragment                   │
 * │  notify observers    │  LiveData.setValue() / postValue()   │
 * └─────────────────────────────────────────────────────────────┘
 *
 * WHY MVVM?
 * - ViewModel SURVIVES configuration changes (screen rotation) — data isn't lost
 * - View has NO logic — just observes and renders
 * - Model is pure data — easy to test
 * - Separation of concerns: UI code and business logic never mix
 *
 * REAL ANDROID EXAMPLE:
 *   class UserViewModel extends ViewModel {
 *       private MutableLiveData<User> user = new MutableLiveData<>();
 *       public LiveData<User> getUser() { return user; }
 *       public void loadUser(int id) { user.setValue(repository.findById(id)); }
 *   }
 *   // In Activity:
 *   viewModel.getUser().observe(this, user -> textView.setText(user.getName()));
 */
public class AndroidMvvmPatternDemo {

    // =========================================================================
    // MODEL LAYER — Pure data, no UI, no Android dependencies
    // =========================================================================

    static class User {
        private int id;
        private String name;
        private String email;
        private boolean isLoggedIn;

        User(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.isLoggedIn = false;
        }

        // Getters
        int getId() { return id; }
        String getName() { return name; }
        String getEmail() { return email; }
        boolean isLoggedIn() { return isLoggedIn; }

        // Setters return new User (immutable-style updates are safer)
        User withName(String name) { return new User(id, name, email); }
        User withEmail(String email) { return new User(id, name, email); }
        User withLoggedIn(boolean loggedIn) {
            User u = new User(id, name, email);
            u.isLoggedIn = loggedIn;
            return u;
        }

        @Override
        public String toString() {
            return "User{id=" + id + ", name='" + name + "', email='" + email +
                   "', loggedIn=" + isLoggedIn + "}";
        }
    }

    // =========================================================================
    // OBSERVER PATTERN — In real Android, this is LiveData<T>
    // =========================================================================

    interface Observer<T> {
        void onChanged(T newValue);
    }

    // Equivalent of LiveData<T> in Android — holds data and notifies observers
    static class Observable<T> {
        private T value;
        private List<Observer<T>> observers = new ArrayList<>();

        Observable(T initialValue) {
            this.value = initialValue;
        }

        // Real Android: LiveData.observe(lifecycleOwner, observer)
        void observe(Observer<T> observer) {
            observers.add(observer);
            // Immediately deliver current value (same as LiveData behavior)
            if (value != null) {
                observer.onChanged(value);
            }
        }

        // Real Android: MutableLiveData.setValue() — must be called on main thread
        void setValue(T newValue) {
            this.value = newValue;
            notifyObservers();
        }

        // Real Android: MutableLiveData.postValue() — can be called from background thread
        void postValue(T newValue) {
            System.out.println("  [postValue] Posting from background thread → dispatching to main thread");
            setValue(newValue); // In this demo, same as setValue
        }

        T getValue() { return value; }

        private void notifyObservers() {
            for (Observer<T> observer : observers) {
                observer.onChanged(value);
            }
        }

        void removeObserver(Observer<T> observer) {
            observers.remove(observer);
        }
    }

    // =========================================================================
    // VIEWMODEL LAYER — Survives configuration changes, no UI references
    // =========================================================================
    // Real Android: class UserViewModel extends ViewModel { ... }

    static class UserViewModel {
        // Observable data — the View watches this
        private Observable<User> currentUser;
        private Observable<String> statusMessage;
        private Observable<Boolean> isLoading;

        // Simulated repository (in real app, this would be injected)
        private UserRepository repository;

        UserViewModel() {
            this.repository = new UserRepository();
            this.currentUser = new Observable<>(null);
            this.statusMessage = new Observable<>("Ready");
            this.isLoading = new Observable<>(false);
        }

        // Expose read-only Observables to the View
        Observable<User> getCurrentUser() { return currentUser; }
        Observable<String> getStatusMessage() { return statusMessage; }
        Observable<Boolean> getIsLoading() { return isLoading; }

        // Business logic methods called by the View
        void loadUser(int id) {
            isLoading.setValue(true);
            statusMessage.setValue("Loading user " + id + "...");

            // In real Android: this would run on background thread via Coroutines/RxJava
            User user = repository.findById(id);
            if (user != null) {
                currentUser.setValue(user);
                statusMessage.setValue("User loaded successfully");
            } else {
                statusMessage.setValue("User not found: " + id);
            }
            isLoading.setValue(false);
        }

        void updateUserName(String newName) {
            User user = currentUser.getValue();
            if (user == null) {
                statusMessage.setValue("Error: No user loaded");
                return;
            }
            User updated = user.withName(newName);
            repository.save(updated);
            currentUser.setValue(updated); // triggers all observers!
            statusMessage.setValue("Name updated to: " + newName);
        }

        void loginUser() {
            User user = currentUser.getValue();
            if (user == null) return;
            currentUser.setValue(user.withLoggedIn(true));
            statusMessage.setValue(user.getName() + " logged in");
        }

        void logoutUser() {
            User user = currentUser.getValue();
            if (user == null) return;
            currentUser.setValue(user.withLoggedIn(false));
            statusMessage.setValue("Logged out");
        }
    }

    // Simple in-memory repository (not part of MVVM, but a common companion)
    static class UserRepository {
        private java.util.Map<Integer, User> store = new java.util.HashMap<>();

        UserRepository() {
            store.put(1, new User(1, "Alice Johnson", "alice@example.com"));
            store.put(2, new User(2, "Bob Smith", "bob@example.com"));
        }

        User findById(int id) { return store.get(id); }
        void save(User user) { store.put(user.getId(), user); }
    }

    // =========================================================================
    // VIEW LAYER — Observes ViewModel, renders UI, sends events back
    // =========================================================================
    // Real Android: Activity or Fragment — contains no business logic

    static class SimulatedView {
        private String viewName;
        private UserViewModel viewModel;

        SimulatedView(String viewName, UserViewModel viewModel) {
            this.viewName = viewName;
            this.viewModel = viewModel;
            registerObservers();
        }

        private void registerObservers() {
            // Real Android: viewModel.getCurrentUser().observe(this, user -> render(user));
            viewModel.getCurrentUser().observe(user -> {
                if (user != null) {
                    renderUser(user);
                } else {
                    System.out.println("[" + viewName + "] No user to display");
                }
            });

            viewModel.getStatusMessage().observe(msg ->
                System.out.println("[" + viewName + "] Status: " + msg)
            );

            viewModel.getIsLoading().observe(loading ->
                System.out.println("[" + viewName + "] Loading indicator: " + (loading ? "VISIBLE" : "HIDDEN"))
            );
        }

        private void renderUser(User user) {
            System.out.println("[" + viewName + "] Rendering user:");
            System.out.println("  Name:   " + user.getName());
            System.out.println("  Email:  " + user.getEmail());
            System.out.println("  Status: " + (user.isLoggedIn() ? "LOGGED IN" : "logged out"));
        }

        // Simulates user pressing a button
        void onLoadButtonClick(int userId) {
            System.out.println("\n[" + viewName + "] User tapped 'Load User' button (id=" + userId + ")");
            viewModel.loadUser(userId); // View delegates to ViewModel — no logic here!
        }

        void onUpdateNameButtonClick(String newName) {
            System.out.println("\n[" + viewName + "] User tapped 'Update Name' button");
            viewModel.updateUserName(newName);
        }

        void onLoginButtonClick() {
            System.out.println("\n[" + viewName + "] User tapped 'Login' button");
            viewModel.loginUser();
        }
    }

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("  ANDROID MVVM PATTERN DEMO");
        System.out.println("=".repeat(60));

        // In real Android: ViewModel is created by ViewModelProvider
        // ViewModelProvider(this).get(UserViewModel.class)
        UserViewModel viewModel = new UserViewModel();

        // In real Android: this = Activity/Fragment, observing within lifecycle
        System.out.println("\n--- Creating View and registering observers ---");
        SimulatedView mainView = new SimulatedView("UserProfileScreen", viewModel);

        System.out.println("\n--- User interaction: Load User 1 ---");
        mainView.onLoadButtonClick(1);

        System.out.println("\n--- User interaction: Update the name ---");
        mainView.onUpdateNameButtonClick("Alice Johnson-Williams");

        System.out.println("\n--- User interaction: Login ---");
        mainView.onLoginButtonClick();

        System.out.println("\n--- Load a non-existent user ---");
        mainView.onLoadButtonClick(99);

        System.out.println("\n--- Load User 2 ---");
        mainView.onLoadButtonClick(2);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("KEY TAKEAWAY:");
        System.out.println("  View only calls ViewModel methods and observes data.");
        System.out.println("  ViewModel never holds a reference to View.");
        System.out.println("  This is why ViewModel can survive screen rotation —");
        System.out.println("  the View is destroyed and recreated, but ViewModel stays.");
        System.out.println("=".repeat(60));
    }
}

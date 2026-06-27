# Real Android Development Setup

The three `.java` files in this folder are **plain Java concept demos** — they teach you the patterns
without requiring any Android SDK. This guide shows you how to take those concepts to a real Android app.

---

## What Is Android Studio?

Android Studio is Google's official IDE for Android development, built on IntelliJ IDEA. It provides:

- A visual layout editor (drag-and-drop UI builder)
- An Android emulator (virtual phone/tablet on your PC)
- A Gradle build system configured for Android
- Integrated Logcat (device log viewer)
- Profilers for CPU, memory, and network
- Direct USB deployment to physical Android devices

Android Studio is **free** and available at: https://developer.android.com/studio

---

## System Requirements

| Component | Minimum | Recommended |
|-----------|---------|-------------|
| RAM | 8 GB | **16 GB** |
| Disk (IDE + SDK) | 8 GB | 16 GB |
| CPU | Any modern x86_64 | 8+ core CPU |
| GPU | Any | Hardware virtualization (Intel HAXM or AMD-V) |

**Honest note about RAM:**  
8 GB is technically enough to run Android Studio, but if you also run the emulator alongside
your IDE, Chrome, and other apps, 8 GB becomes very tight. You may experience sluggishness,
slow build times, or emulator crashes. 16 GB gives you a comfortable experience.

**Best solution for 8 GB machines: use a physical device instead of the emulator** (see below).

---

## Emulator vs Physical Device

| | Emulator | Physical Device |
|-|----------|-----------------|
| Cost | Free | Need an Android phone |
| RAM impact | HIGH — emulator uses 1.5–2 GB RAM | Zero (runs on the device) |
| Speed | Can be slow on low RAM | Full native speed |
| Setup | Automatic via Android Studio | Enable Developer Options + USB debugging |
| Sensors | Simulated (may be inaccurate) | Real GPS, camera, accelerometer |

**Recommendation:** If you have a spare Android phone (any phone running Android 5.0+), use it.
Enable **Developer Options** (tap "Build Number" 7 times in Settings > About Phone),
then enable **USB Debugging**. Android Studio detects it automatically.

---

## Installing Android Studio

1. Go to https://developer.android.com/studio
2. Click "Download Android Studio"
3. Run the installer — it includes the Android SDK automatically
4. On first launch, complete the Setup Wizard:
   - Accept licenses
   - Choose Standard installation
   - Let it download the Android SDK (this takes 5–10 minutes)

---

## Creating Your First Real Android Java Project

1. Open Android Studio → "New Project"
2. Select **"Empty Views Activity"** template
3. Configure:
   - Name: `MyFirstApp`
   - Package name: `com.yourname.myfirstapp`
   - Language: **Java**
   - Minimum SDK: API 24 (Android 7.0) — covers ~95% of devices
4. Click Finish. Android Studio sets up the project with Gradle.

### Key files in your project:
```
app/
├── src/main/
│   ├── java/com/yourname/myfirstapp/
│   │   └── MainActivity.java    ← Your first Activity
│   ├── res/layout/
│   │   └── activity_main.xml   ← The XML UI layout
│   └── AndroidManifest.xml     ← App configuration
└── build.gradle                 ← Dependencies (like pom.xml)
```

---

## Connecting the Concept Demos to Real Android

### activity_lifecycle_concept.java → MainActivity
```java
// Real Android:
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // savedInstanceState contains data from onSaveInstanceState()
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Start sensors, animations
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Stop sensors, save drafts
    }
}
```

### android_mvvm_pattern_demo.java → ViewModel + LiveData
Add to `build.gradle` (app module):
```gradle
implementation 'androidx.lifecycle:lifecycle-viewmodel:2.7.0'
implementation 'androidx.lifecycle:lifecycle-livedata:2.7.0'
```

```java
// Real Android ViewModel:
public class UserViewModel extends ViewModel {
    private MutableLiveData<User> currentUser = new MutableLiveData<>();

    public LiveData<User> getCurrentUser() { return currentUser; }

    public void loadUser(int id) {
        // Background thread via ExecutorService or Coroutines
        currentUser.postValue(repository.findById(id));
    }
}

// In your Activity:
UserViewModel viewModel = new ViewModelProvider(this).get(UserViewModel.class);
viewModel.getCurrentUser().observe(this, user -> {
    nameTextView.setText(user.getName()); // auto-called when data changes
});
```

### room_database_concept_demo.java → Room + SQLite
Add to `build.gradle`:
```gradle
implementation 'androidx.room:room-runtime:2.6.1'
annotationProcessor 'androidx.room:room-compiler:2.6.1'
```

The `@Entity`, `@Dao`, `@Database`, `@Query`, `@Insert`, `@Delete` annotations work
exactly as modeled in the demo — Room auto-generates the DAO implementation.

---

## Learning Resources

| Resource | URL | Cost |
|----------|-----|------|
| Official Android Docs | https://developer.android.com | Free |
| Android Codelabs | https://developer.android.com/codelabs | Free |
| Android Courses | https://developer.android.com/courses | Free |
| Modern Android Dev (MAD) | https://www.youtube.com/c/AndroidDevelopers | Free |
| Kotlin (recommended next step) | https://kotlinlang.org/docs/android-overview.html | Free |

> **Note on Kotlin:** Google officially recommends Kotlin over Java for new Android development
> (since 2019). All new Android documentation and official samples use Kotlin. Java still works
> fully, but learning Kotlin is worth it if you plan to do Android professionally.

---

## Running the Concept Demos (Plain Java)

These demos run without Android Studio:

```batch
# Activity Lifecycle
javac activity_lifecycle_concept.java && java ActivityLifecycleConcept

# MVVM Pattern
javac android_mvvm_pattern_demo.java && java AndroidMvvmPatternDemo

# Room Database Concept
javac room_database_concept_demo.java && java RoomDatabaseConceptDemo
```

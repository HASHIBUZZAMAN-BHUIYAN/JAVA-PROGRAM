# ADVANCED — Android (Concept Demos)

Android development requires Android Studio and an Android SDK (~8GB install). These files demonstrate the **core concepts as plain Java** so you can learn the patterns before setting up the full toolchain.

## Files

| File | Topic | Run |
|------|-------|-----|
| `activity_lifecycle_concept.java` | Activity state machine (onCreate/onStart/onResume/onPause/onStop/onDestroy) | `build_activity_lifecycle.bat` |
| `android_mvvm_pattern_demo.java` | MVVM architecture: ViewModel, LiveData-style observable, Repository | `build_mvvm.bat` |
| `room_database_concept_demo.java` | DAO/Repository/Entity pattern (how Room works without Room) | `build_room.bat` |

## Real Android Setup

See `ANDROID_REAL_SETUP.md` for detailed instructions to set up Android Studio, create an AVD (Android Virtual Device), and run a real Android project.

## Key Android Concepts Covered

### Activity Lifecycle
```
onCreate → onStart → onResume (app visible and interactive)
         → onPause → onStop  (app going to background)
         → onDestroy         (app closing)
         ↑ onRestart ← onStop (app coming back to foreground)
```

### MVVM Pattern
```
Activity/Fragment (View) — observes → ViewModel — calls → Repository → Data Source
```
The ViewModel survives configuration changes (screen rotation).  
The View never touches the data layer directly.

### Room Database
```
@Entity class → @Dao interface → @Database → repository uses DAO
```
Room generates the SQL boilerplate at compile time.

## Recommended Learning Path
1. Run and study the plain-Java concept demos here
2. Follow `ANDROID_REAL_SETUP.md` to install Android Studio
3. Create a fresh project and implement the same patterns with real Android APIs

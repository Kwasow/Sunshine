# Sunshine

[![Android](https://github.com/Kwasow/Sunshine/actions/workflows/android.yml/badge.svg)](https://github.com/Kwasow/Sunshine/actions/workflows/android.yml)
[![CodeQL Advanced](https://github.com/Kwasow/Sunshine/actions/workflows/codeql.yml/badge.svg)](https://github.com/Kwasow/Sunshine/actions/workflows/codeql.yml)

An app for couples for storing and celebrating memories, sharing wishes, sending
nudges and much more.

It is made to be self-hosted, so that you can keep your data safe and under your
control. The backend can run on any PHP enabled server and the mobile app
supports Android 6 or newer. iOS is currently unsupported, but might come later
when Compose Multiplatform matures.

## Features

📆 **Memories** - keep track of your memories and show your relationship timeline.

🎶 **Music** - record songs for each other and listen to them in the app.

✨ **Wishlist** - keep track of gift ideas and wishes.

💔 **Missing You** - send a nudge to your loved once to tell them that you're
missing them.

🧭 **Location** - help each other find you and keep your significant other safe
when they are alone. Privacy controls included.

## Setup

The backend is written in PHP and tested with version 8.2. Dependencies are
managed by composer. The project also uses Firebase for authentication and
sending notifications.

### Project setup

Go to the [Firebase Console](https://console.firebase.google.com/) and create
a new project - I disabled Gemini and Analytics in my case, as these are not
needed.

Now enable "Sign in with Google" by going into Build > Authentication > Set up
sign-ing method and select "Google". Enable it, set a recognizable name (like
"Sunshine"), select an email address from the list and press "Save".

### Firebase setup

_TODO: Setup firebase functions_

### Android setup

For sign-in with Google to work, we have to add our signing keys to Firebase.
In the Firebase Console navigate to Project Overview > Cog icon > Project
settings > General and then follow the steps to add your app at the bottom of
the page. You'll have to add at least the release version of your app (package
name is `pl.kwasow.sunshine`), but it is advised to also add the debug versions,
so you can test before releasing (package name is `pl.kwasow.sunshine.beta`).

For generating a new signing key and checking it's fingerprint follow the
[official guide](https://developer.android.com/studio/publish/app-signing) from
Google.

After adding both versions of the app, click the "google-services.json" button
to download the JSON file containing details about our Firebase project and place
it in the `android/app` directory. Do not change the filename.

_TODO: Configuration in secrets.properties_

### Server setup

_TODO: Setup server and messaging_

## Building

To build the app for testing, just open the `android/` directory in
[Android Studio](https://developer.android.com/studio) and press the "play"
icon in the upper-right hand corner.

To build the app for release and sign it, select the menu in the upper-left
hand corner and the select Build > Generate signed App Bundle/APK. If you
don't know which one to choose, please follow to the next section, to help you
decide.

## Distribution

## Architecture

The mobile app is built in Kotlin with Jetpack Compose and uses Koin for
dependency injection.

Firebase Auth and Sign in with Google are used for authentication as I don't
feel confident building my own solution, but I'm sure that I can keep everything
safe with a little help from those. I know it's not ideal, but I want to keep
that part simple. Firebase tokens are used to verify the users identity on the
server and are sent with every request in the `Authorization` header.

Memory notifications are sent through a notification that is triggered by
a Firebase Functions daily job. In the past I used Android's `Alarm manager`,
but it was unreliable and often failed on some brands of phones that managed
background processes to aggressively. I've found that sending a Firebase
notifications reliably wakes up the app and allows it to do it's job. The
Firebase Functions task itself doesn't have any memories related logic, it
doesn't check if there are any memories for the day and it doesn't communicate
with the server. It's only job is to wake up the app so it can do it's thing.

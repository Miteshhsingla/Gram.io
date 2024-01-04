# Gram.io - Android Whatsapp Clone

## Overview

Gram.io is a feature-rich Android chat application developed in Kotlin, offering seamless communication with an intuitive user interface. Leveraging Firebase Realtime Database (RTDB) and Firebase Storage, Gram.io ensures reliable and efficient chat functionality. The app also incorporates OTP authentication through Firebase Authentication to enhance security and restrict access to legitimate users only.

## Features

- **Real-time Chat:** Engage in instant, real-time chat with friends and contacts.
- **Firebase Integration:** Utilizes Firebase Realtime Database and Firebase Storage for a robust and scalable backend.
- **OTP Authentication:** Secure user authentication via Firebase Authentication using One-Time Passwords (OTPs).
- **User Profile:** Customize your profile with a profile picture.

## Requirements

- Android Studio (version X.X.X)
- Kotlin (version X.X.X)
- Firebase Account (for Realtime Database, Storage, and Authentication)

## Getting Started

1. Clone the repository to your local machine.
   ```bash
   git clone https://github.com/your-username/gram-io.git
   ```

2. Open the project in Android Studio.

3. Connect the app to your Firebase project by adding the `google-services.json` file from your Firebase Console.

4. Build and run the application on an emulator or physical device.

## Firebase Configuration

Ensure that you have set up Firebase for your project by following these steps:

1. Create a new project on the [Firebase Console](https://console.firebase.google.com/).
2. Add an Android app to your project and download the `google-services.json` file.
3. Enable Firebase Authentication, Realtime Database, and Firebase Storage in the Firebase Console.

Update the `google-services.json` file in the `app` directory with the configuration from your Firebase project.

## Dependencies

- Firebase Realtime Database
- Firebase Authentication
- Firebase Storage

Dependencies are specified in the `build.gradle` files.

```gradle
// Example dependencies
implementation 'com.google.firebase:firebase-database:X.X.X'
implementation 'com.google.firebase:firebase-auth:X.X.X'
implementation 'com.google.firebase:firebase-storage:X.X.X'
```


## License

This project is licensed under the [MIT License](LICENSE).

## Contact

For any inquiries or issues, please contact our support team at miteshhsingla@gmail.com.

Happy chatting with Gram.io! 🚀

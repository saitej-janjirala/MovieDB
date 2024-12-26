# 🎥 MoviesApp

MoviesApp is an Android application built with modern development practices, leveraging **MVVM** architecture and **Clean Code** principles. The app showcases a collection of movies categorized into **Now Playing**, **Top Rated**, and **Trending**. Users can explore movie details and navigate through paginated movie lists using a seamless, user-friendly interface.

## ✨ Features

- **Home Screen**: Displays movies in three categories:
    - Now Playing
    - Top Rated
    - Trending
- **Detail Screen**: Displays detailed information about a selected movie, including a custom background generated using the Palette library.
- **Pagination**: Efficiently loads more movies in each category using Paging 3.
- **Smooth Navigation**: Navigate between screens seamlessly with Jetpack Navigation Component.
- **Dynamic UI**: Built with Jetpack Compose for a modern and reactive UI experience.
- **Image Loading**: Uses Coil for fast and efficient image loading.
- **Reactive Asynchronous Code**: Implements Kotlin Coroutines, Flow, and StateFlow for state management and asynchronous data handling.

## 🛠️ Tech Stack

- **Architecture**: MVVM (Model-View-ViewModel) with Clean Code principles
- **UI Framework**: Jetpack Compose
- **Networking**: Retrofit
- **Image Loading**: Coil
- **Navigation**: Jetpack Navigation Component
- **Pagination**: Paging 3
- **Dynamic UI Backgrounds**: Palette library
- **Asynchronous Programming**: Kotlin Coroutines, Flow & StateFlow

## 🏗️ Project Structure

The project is structured using **Clean Code Architecture** principles to ensure scalability and maintainability:

```plaintext
MovieDB/
├── data/                 # Handles data sources (network, local)
│   ├── api/              # Contains Retrofit services for API communication
│   ├── repository/       # Repositories for data abstraction and management
├── domain/               # Contains business logic of the app
│   ├── usecases/         # Use cases encapsulating app-specific logic
│   └── models/           # Models representing domain-specific entities
├── ui/                   # UI layer for the application
│   ├── home/             # Components for the Home screen UI and logic
│   ├── detail/           # Components for the Detail screen UI and logic
│   └── more/             # Components for the Screen with Pagination List of Data
└── utils/                # Utility classes and extensions
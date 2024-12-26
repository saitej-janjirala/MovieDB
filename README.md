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

MoviesApp/ ├── data/ # Handles data sources (network, local) │ ├── api/ # Retrofit services │ ├── repository/ # Repositories for data abstraction │ └── models/ # Data models ├── domain/ # Business logic │ ├── usecases/ # Use cases for app logic │ └── models/ # Domain-specific models ├── presentation/ # UI layer │ ├── home/ # Home screen UI and logic │ ├── details/ # Detail screen UI and logic │ └── navigation/ # Navigation components └── utils/ # Utility classes and extensions
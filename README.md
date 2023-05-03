
![Logo](https://docs.google.com/uc?export=download&id=1qZl5jfa_CSm_nmpqx-at_FSibt1brLC1)
# News App Compose

**News App Compose** is a simple Android application that displays the latest news from the United States and France using the NewsAPI.org API. 

***This project serves as an introduction and demonstration of Clean Architecture and Jetpack Compose for building modern Android applications.***

## Architecture

The project adheres to the principles of clean architecture and is composed of several layers.

**The main layers of the project include:**

- **Presentation Layer:** This layer is responsible for managing the user interface of the application. It includes components such as views, controllers, and presenters.

- **Domain Layer:** This layer contains the business logic of the application. It defines the rules and operations that govern the behavior of the system. This layer should be independent of any specific technology or framework.

- **Data Layer:** This layer is responsible for managing the data sources used by the application. It includes components such as repositories, data mappers, and data access objects.

In addition, the project includes a **DI (Dependency Injection) layer** that provides dependency injection for the different layers of the application. This helps to keep the code modular and maintainable by allowing each layer to be developed and tested independently.

Finally, the project also includes a **Common layer** that contains shared classes and methods used by multiple layers of the application. This helps to reduce code duplication and improve consistency across the project.

 
![App structure](https://docs.google.com/uc?export=download&id=1KRAxO3NV4rhRQP9pjsQDCwZJ0m99e7Ar
)

The `mob.nereek.compose` follows a well-organized architecture to facilitate the development and maintenance of Android applications, with the aim of creating flexible and long-term maintainable software applications.

## Adding API token to the project

To add the API key, you need to replace `API_NEWS_TOKEN` with your actual News API key in the `Constants.kt` file.



```Kotlin
object Constants {
    ...
    const val API_NEWS_TOKEN = "\"your_api_key_here\""
    ...
}
```
You can get your API key by signing up for News API on their website: https://newsapi.org/register
## Libraries Used
Several third-party libraries were used to facilitate the development of the application. Here is a list of these libraries and the reasons why they were chosen:

- **Coroutines/Flow**: Asynchronous programming framework for performing non-blocking operations and managing data flows.

- **Dagger Hilt:** Dependency injection library that simplifies the process of managing and injecting dependencies.
- **Retrofit:** HTTP client for making network requests and handling responses in Android apps.
- **OkHttp:** Library for sending and receiving HTTP requests and responses, with support for interceptors and asynchronous calls.
- **Chuck Interceptor:** library to intercept and record HTTP traffic between an Android application and its network.
- **Moshi:** JSON parsing library for serializing and deserializing objects to and from JSON.
- **Coil:** Image loading library for Android with support for fetching and caching images from various sources.
- **Jetpack Compose:** Android UI toolkit for creating composable and declarative UIs.
- **Material3:** Material Design components built for Jetpack Compose, providing predefined UI elements.

**\# Libraries for unit testing and instrumentation testing are also included.**

**[** *These libraries are up-to-date as of the project's publication date* **]**


## Screenshots

<img alt="App Screenshot" height="960" src="https://docs.google.com/uc?export=download&id=1X07GfnM7E_7v4hOb1_am6BxlJyHSEc_2" width="540"/>

<img alt="App Screenshot" height="960" src="https://docs.google.com/uc?export=download&id=114cGQhUqUXZL4UA0K2hjv4e7YHVqAT0l" width="540"/>

<img alt="App Screenshot" height="960" src="https://docs.google.com/uc?export=download&id=1TP7Govt5N0oj8yo2974IqMMxAZ6FSaTj" width="540"/>

<img alt="App Screenshot" height="960" src="https://docs.google.com/uc?export=download&id=14y9a_iQ6V8RiMjhdxBZ3uR4Zc_yVkSMR" width="540"/>

## 🛠 Skills
Kotlin, Android SDK, Clean Architecture, MVVM, Android Architecture Components, Retrofit, Dagger Hilt, Coroutines, Flow, OkHttp, Moshi, Coil, JetPack Compose, Material Design, Git.

## License

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)

## Authors

- [Yassine Mourig](https://www.linkedin.com/in/yassine-mourig-computer-engineer/)

## Used By

This structure is used by the following companies:

- www.nereek.io (**YOFIFY LLC**)

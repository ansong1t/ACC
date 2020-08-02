## Appetizer Coding Challenge ##

## The Architechture ##
* MVVM Pattern with Clean Architecture (includes Domain, Repository layer). MVVM separates your view (i.e. `Activity`s and `Fragment`s) from your business logic.
![MVVM Android](https://paper.dropbox.com/ep/redirect/image?url=https%3A%2F%2Fpaper-attachments.dropbox.com%2Fs_1A49CF2CDECE18C3C45D4A8C294D3A6AB820A24086C8F58664C667EE1D3DEE15_1596363961850_ACCArch.png&hmac=wOsjhLaoH6IqBp%2FNSf6WIPKDz37j5MkBQriVBVcD4xQ%3D&width=1490)

### Tech Stack ###
- [Kotlin](https://kotlinlang.org/) - app primary programming language
- [Kotlin Coroutine](https://github.com/Kotlin/kotlinx.coroutines) - for asynchronous and reactive programming
- [Databinding](https://developer.android.com/topic/libraries/data-binding) - binding observable data to xml
- [Epoxy](https://github.com/airbnb/epoxy) - building complex screens in a RecyclerView
- [Koin](https://github.com/InsertKoinIO/koin) - dependency injection framework
- [Room](https://developer.android.com/topic/libraries/architecture/room) - persistent database library
- [Retrofit](https://square.github.io/retrofit/) - a type-safe HTTP client
- [Moshi](https://github.com/square/moshi) - JSON library
- [Navigation](https://developer.android.com/guide/navigation) - app navigation
- [Paging](https://developer.android.com/guide/navigation) - pagination
- [Coil](https://github.com/coil-kt/coil) - image loading library
- [Ktlint](https://github.com/pinterest/ktlint)/[Detekt](https://github.com/detekt/detekt) - code linting and code smells

### App Features List###
- Modular app architecture
- Offline mode
- Installed precommit hook to scan code first
- Separate details page for Tv Show, Movie, Song and Audio Book

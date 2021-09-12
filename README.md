# jokeapp

a small app to request a joke from [iCanHazDadJoke](https://icanhazdadjoke.com)

## design

### architecture

- main activity with navigation
- joke data model and network service
- injection modules for database, network service and dispatchers
- a repository to coordinate local and remote data
- view models to bridge the repository and fragments
- fragments use data binding and live data to sync

### tests

- coroutine rule to replace the main thread
- a fake network service and data
- injection modules to use a memory database and fake data

### ui

- random joke in big font
- action button to add/remove favourites
- action button to share joke
- bottom buttons to sync and view favourites
- favourites a card list with back button
- can swipe back or use remove button to remove a favourite
- material vector icons

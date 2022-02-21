# jokeapp

a small app to request a joke from [iCanHazDadJoke](https://icanhazdadjoke.com)

## analysis

### ui requirements

- random joke in big font
- action button to add/remove favourites
- action button to share joke
- bottom buttons to sync and view favourites
- favourites a card list

## design

### architecture

- uncle bob's clean architecture in modular android
* separation of data, domain and presentation layers
- compose and kotlin state flow
- redux reducers for viewmodel state

### tests

- rules and/or injection to handle threading and coroutines
- a fake network service and data
- a memory database and fake data

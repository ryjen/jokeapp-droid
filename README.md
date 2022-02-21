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
- separation of data, domain and presentation layers
- compose and material ui frameworks
- kotlin state flow and coroutines
- redux reducers for viewmodel state
- data IO is categorized: `asyncronous`, `syncronous`, `observable`, `local`, and `remote`

## implementation

### project layout

- **app**: the entry point to the application
- **domain**: business entities, interfaces and use cases between layers
- **data**: implementation of categorized data storage and retrieval
- **ui**: presentation of data and user interaction
- **meta**: code related to programming languages, frameworks or algorithms
- **test**: unit tests and instrumented tests
- **buildSrc**: dependency definitions

### tests

- rules and/or injection to handle threading and coroutines
- fake data generator
- mocking and mock network server
- in-memory database
- user interface tag verifications

## maintenance

### TODO:

- [ ] improve UX its quite horrible, think different
- [ ] abstract to clean-architecture template
- [ ] generalize and add new remote sources, like quotes
- [ ] export to photo for sharing
- [ ] analytics
- [ ] allow user to add content
- [ ] home screen widget
- [ ] multiplatform (pending kotlin multiplatform release)
- [ ] splash screen and logo
- [ ] speech bubbles
- [ ] ML to know sentiment of joke and predict favorites
- [ ] speech bubble characters based on category or sentiment
- [ ] Text-to-speech, laugh track
- [ ] Animations
- [ ] monetize
- [ ] security analysis

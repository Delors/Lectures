This is a very simple template for a quiz web-component. 

It uses standard technologies for web components.

The quiz' questions are stored in a JSON object. The user can select an answer 
and submit it. The application will then display the correct answer and keep 
track of the score.

# Server

To run the quizzy server using a different port, you can simply set the environment variable QUIZZY_PORT and then start the server:

```bash
$ QUIZZY_PORT=6600 node game.js 
```

or 

```bash
$ export QUIZZY_PORT=5557 
$ node game.js
```

# Client / Web Component

Simply use the custom element `<ld-quizzy>` and specify the target address of the server in the `ws-url` attribute.
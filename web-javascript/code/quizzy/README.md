This is a very simple template for a quiz application! 

It is written in JavaScript and uses the DOM API to manipulate the HTML elements. 
The quiz' questions are stored in a JSON object. The user can select an answer 
and submit it. The application will then display the correct answer and keep 
track of the score.

To run quizzy using a different port, you can simply set the environment variable QUIZZY_PORT and then start the server:


```bash
$ QUIZZY_PORT=6600 node game.js 
```

or 

```bash
$ export QUIZZY_PORT=5557 
$ node game.js
```

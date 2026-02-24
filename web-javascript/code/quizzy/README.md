# Overview 

This is a very simple template for a quiz application! 

It is written in JavaScript as a single-page application; i. e., depending
on the state, we manipulate the HTML elements. 
The quiz' questions are stored in a JSON object. The user can select an answer 
and submit it. The application will then display the correct answer and keep 
track of the score.

# Running Quizzy

To run quizzy using a different port, you can simply set the environment 
variable QUIZZY_PORT and then start the server:

```bash
$ QUIZZY_PORT=6600 node game.js 
```

or 

```bash
$ export QUIZZY_PORT=5557 
$ node game.js
```

# Playing

Open the browser and open the player.html and admin.html webpages. The port and 
the IP address depends on your configuration. It may look something like this:

http://192.168.120.12:8000/player.html
http://192.168.120.12:8000/admin.html

Please note, there is not index.html.
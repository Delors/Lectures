Hints
=======

Falls es zu einem EADDRINUSE kommt
-----------------------------------

Um die PID des Prozesses zu finden, die den Port (hier: Port 5000) nutzt:

.. code:: bash
    :number-lines:
   
    $ lsof -i :5000
    $ ls -e | grep <PID>


Senden von HTTP POST mit Hilfe von CURL
---------------------------------------

Request
_________

.. code:: bash
    :number-lines:

    $ curl -X POST -H "Content-Type: application/json" \
      -d '{"user4": {"name": "Jango", "password": "luke", "job": "headhunter", "id": 4}}' \
      http://localhost:4080/

Response
_________

.. scrollable::

    .. code:: json
        :number-lines:

        {
            "user1": {
                "name": "dingo",
                "password": "1234",
                "job": "dealer",
                "id": 1
            },
            "user2": {
                "name": "bingo",
                "password": "0987",
                "job": "farmer",
                "id": 2
            },
            "user3": {
                "name": "ringo",
                "password": "asdf",
                "job": "boss",
                "id": 3
            },
            "user4": {
                "name": "Jango",
                "password": "luke",
                "job": "headhunter",
                "id": 4
            }
        }
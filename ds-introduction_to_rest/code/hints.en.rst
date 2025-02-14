Hints
=======



In case you get an EADDRINUSE error
-----------------------------------

To get the PID of the Process that uses a specific port (here: port 5000):

.. code:: bash
   
   $ lsof -i :5000
   $ ls -e | grep <PID>


Sending an HTTP POST request using CURL
---------------------------------------

Request
_________

.. code:: bash
    :class: far-far-smaller

    $ curl -X POST -H "Content-Type: application/json" \
      -d '{"user4": {"name": "Jango", "password": "luke", "job": "headhunter", "id": 4}}' \
      http://localhost:4080/

Response
_________

.. container:: scrollable

    .. code:: json
        :class: far-smaller

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
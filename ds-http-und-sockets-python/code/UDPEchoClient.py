#!/usr/bin/env python3
from datetime import datetime

import socket

HOST = "localhost"
PORT = 5678  

try:
    client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    message = "Hallo, Server! "+datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    client.sendto(message.encode(), (HOST, PORT))

    data, addr = client.recvfrom(65507)  # buffer size is 65507 bytes
    print(f"Received from server: {data.decode()}")
except socket.error as e:
    print(f"Socket error: {e}")
except Exception as e:
    print(f"Other error occurred: {e}")
finally:
    client.close()
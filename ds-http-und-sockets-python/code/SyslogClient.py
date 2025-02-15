#!/usr/bin/env python3
import socket

HOST = "localhost"
PORT = 5678  
MAX_PACKET_SIZE = 65507

try:
    client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    while True:
        message = input("Log message: ").encode()
        if len(message) > MAX_PACKET_SIZE:
            print(f"Message too long. Max length is {MAX_PACKET_SIZE} bytes.")
            continue
        client.sendto(message, (HOST, PORT))
except socket.error as e:
    print(f"Socket error: {e}")
except Exception as e:
    print(f"Other error occurred: {e}")
finally:
    client.close()
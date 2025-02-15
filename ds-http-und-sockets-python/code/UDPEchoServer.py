#!/usr/bin/env python3
import socket

HOST = "localhost"
PORT = 5678  

with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as server:
    server.bind((HOST, PORT))

    while True:
        data, addr = server.recvfrom(65507)  # buffer size is 65507 bytes
        print(f"received message: {data} from: {addr}")
        server.sendto(data, addr)

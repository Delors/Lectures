#!/usr/bin/env python3
import socket

HOST = "localhost"
PORT = 5678  
MAX_PACKET_SIZE = 65507;

with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as server:
    server.bind((HOST, PORT))

    while True:
        data, addr = server.recvfrom(MAX_PACKET_SIZE)  
        print(f"[{addr}] {data}")
        
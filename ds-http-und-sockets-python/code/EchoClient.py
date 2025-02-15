#!/usr/bin/env python3
import socket

HOST = "localhost"
PORT = 5678

def receive_all(conn, chunk_size=1024):
    data = b''
    while True:
        part = conn.recv(chunk_size)
        data += part
        if len(part) == 0: break
    return data

while True:
    the_line = input()
    if the_line == ".":
        break
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect((HOST, PORT))  # Connect to localhost on port 7
        s.sendall(the_line.encode())
        data = receive_all(s)
    print(data.decode())
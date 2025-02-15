#!/usr/bin/env python3
import socket

HOST = "www.michael-eichberg.de"
PORT = 80
FILE = "/index.html"

the_request = f"GET {FILE} HTTP/1.1\r\nHost: {HOST}\r\n\r\n"

def receive_all(conn, chunk_size=1024):
    data = b''
    while True:
        part = conn.recv(chunk_size)
        data += part
        if len(part) == 0: break
    return data

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))  

    s.sendall(the_request.encode())
    data = receive_all(s)
print(data.decode())
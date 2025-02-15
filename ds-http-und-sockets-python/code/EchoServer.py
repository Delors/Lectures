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

def echo_server():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server:
        server.bind((HOST, PORT)) 
        server.listen(1) # Handle at most one connection at a time
        while True:
            conn, addr = server.accept()
            with conn:
                print(f"Connection from {addr}.")
                data = receive_all(conn, 1024)
                print(f"Received {data}.")
                if data:
                    conn.sendall(data)

if __name__ == "__main__":
    echo_server()
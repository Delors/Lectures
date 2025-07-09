#!/usr/bin/env python3
from multiprocessing import Process, current_process

"""
This demonstrates how to create a new process in Python. In this case real OS processes are created.
"""

def computation():
    j = 1
    for i in range(100*1000*1000):
        j += (i/j)
    print("Done:"+str(j))

print(current_process().name)

if __name__ == '__main__':
    p1 = Process(target=computation)
    p2 = Process(target=computation)
    p1.start()
    p2.start()
    p1.join()
    p2.join()

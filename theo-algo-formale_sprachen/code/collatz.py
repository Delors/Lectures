def do_collatz(a, b, n, k, ns):
    print(a,b,n,k,ns)
    if n == 1:
        return str(k);

    if n in ns:
        return "repetition: " + str(n)
   
    ns.add(n)

    if n % 2 == 0:
        fn = n // 2
    else:
        fn = a * n + b
    
    return do_collatz(a, b, fn, k + 1, ns)

def collatz(a, b, n):
    return do_collatz(a, b, n, 0, set())


# Beispielausgae:
print(collatz(3, 1, 6))
# 8
print(collatz(3, 1, 27))
# 111


def collatzK(a, b, l, h):
    for i in range(l, h + 1):
        if collatz(a, b, i).startswith("rep"):
            yield i

print(list(collatzK(3, 7, 1, 20)))
# [1, 5, 7, 10, 11, 14]

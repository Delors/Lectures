def binary_search(A, l, u, needle):
    lower = l
    upper = u
    while upper >= lower:
        print(f"binary-search: l: {lower}, u: {upper}")
        pos = round((upper + lower) / 2)
        value = A[pos]
        if value == needle:
            return pos
        elif value > needle:
            upper = pos - 1
        else:
            lower = pos + 1
    return None


def exponential_search(A, needle):
    print(f"exponential-search: needle: {needle}")
    if A[0] == needle:
        return 0
    i = 1
    while i < len(A) and A[i] < needle:
        print(f"exponential-search: i: {i}")
        i = i * 2
    return binary_search(A, i // 2 + 1, min(i, len(A) - 1), needle)


def eval(name, A):
    for needle in range(0, 12):
        print(f"\n\n\nSuche nach {needle}")

        """
        result = binary_search(A, 0, len(A)-1, needle)
        if result is not None:
            print(f"Binäre Suche - Element gefunden an Position {result}")
        else:
            print("Binäre Suche - Element nicht gefunden")
        """

        result = exponential_search(A, needle)
        if result is not None:
            print(f"Binäre Suche - Element gefunden an Position {result}")
        else:
            print("Binäre Suche - Element nicht gefunden")


# eval("A", A=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10])

print(binary_search([1.01**(x/20) for x in range(64000)],0,64000-1, 1.01))
print(exponential_search([1.01**(x/20) for x in range(64000)], 1.01))

print(binary_search([1.01**(x/20) for x in range(64000)],0,64000-1, 1.01**0.5))
print(exponential_search([1.01**(x/20) for x in range(64000)], 1.01**0.5))


print(binary_search([1.01**(x/20) for x in range(64000)],0,64000-1, 1.01**(48737/20)))
print(exponential_search([1.01**(x/20) for x in range(64000)], 1.01**(48737/20)))

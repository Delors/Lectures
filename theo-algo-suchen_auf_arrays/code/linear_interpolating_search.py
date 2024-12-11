def linear_interpolating_search(A : list[int], needle: int) -> tuple[int|None, int]:
    steps = 0
    lower = 0
    upper = len(A) - 1
    vL = A[lower]
    if vL == needle:
        return (lower, steps)
    vU = A[upper]
    if vU == needle:
        return (upper, steps)

    while upper > lower:
        steps += 1
        pos = round(
            lower * (needle - vU) / (vL - vU) + upper * (needle - vL) / (vU - vL)
        )
        pos = max(lower + 1, min(upper - 1, pos))
        value = A[pos]
        if value == needle:
            return (pos, steps)
        elif value < needle:
            lower = max(pos, lower + 1)
            vL = A[lower]
        else:
            upper = min(upper - 1, pos)
            vU = A[upper]

    return (None, steps)

def eval(name : str, A : list[int]):
    eval_range(name, A, min = A[0] - 1, max = A[len(A) - 1] + 1)

def eval_range(name : str, A : list[int], min , max):
    steps = 0
    value = 0
    for i in range(min, max):
        (index, this_steps) = linear_interpolating_search(A, i)
        if this_steps > steps:
            steps = this_steps
            value = i
        print(f"Gefundener Index für {i} in {name}: {index} [steps: {this_steps}]\n")
    print(f"Maximale Schritte: {steps} für Wert: {value}\n")


#eval("A", [1, 3, 5, 7, 9, 11, 13, 15])
#print("\n\n\n-----------------------------------------------------------")
#eval("B", [0, 7, 13, 22, 27, 32, 44, 49])
#print("\n\n\n-----------------------------------------------------------")
# eval("C", [0, 4, 16, 36, 64, 100, 144, 196]) # 4x^2
C = [0, 2, 16, 54, 128, 250, 432, 686]
#print(C)
#eval("C", C) # 2x^3
C.reverse()
print(C)
eval_range("C", C,-1,689) # 2x^3

# typing checked using mypy: /Users/Michael/Library/Python/3.13/bin/mypy
# Primfaktorzerlegung, wenn die Primzahlen bereits bekannt sind.
p = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37]    


def primfaktorzerlegung(x,i = 0):
    c = 0
    while x % p[i] == 0:
        c += 1
        x = x // p[i]
    factor = str(p[i]) + "^" + str(c)
    if x == 1:
        return factor
    else:
        return factor + " * " + primfaktorzerlegung(x,i+1)


print("10 = " + primfaktorzerlegung(10))
print("12 = " + primfaktorzerlegung(12))
print("88 = " + primfaktorzerlegung(88))
print("93 = " + primfaktorzerlegung(93))
print("100 = " +primfaktorzerlegung(100))
print("31104 = " + primfaktorzerlegung(31104))
print("182.250.000.000 = "    + primfaktorzerlegung(182250000000))
print("37.968.750.000.000 = " + primfaktorzerlegung(37968750000000))
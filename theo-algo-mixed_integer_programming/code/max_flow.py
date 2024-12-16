from pulp import (
    LpProblem,
    LpVariable,     # Erzeugt eine Variable
    LpMaximize,
    LpStatus,
    value,          # Gibt den Wert einer Variablen zurück
    lpSum,
)

CAPACITIES = {      # Kapazitäten der Kanten des Netzwerks
    "S": {"V1": 16, "V2": 13},
    "V1": {"V3": 12},
    "V2": {"V1": 4, "V4": 14},
    "V3": {"V2": 9, "T": 20},
    "V4": {"V3": 7, "T": 4},
    "T": {},
}

prob = LpProblem("Maximum Flow", LpMaximize)

# Solution
flows = LpVariable.dicts(
    "Flows", [s + "->" + t for s in CAPACITIES for t in CAPACITIES[s]], lowBound=0
)

prob += lpSum([flows["S->" + v] for v in CAPACITIES["S"]])

for u in CAPACITIES:
    for v in CAPACITIES[u]:
        prob += flows[u + "->" + v] <= CAPACITIES[u][v] # Kapazitätsbeschränkung / capacity constraint

    if u != "S" and u != "T":
        outgoingFlow = lpSum([flows[u + "->" + v] for v in CAPACITIES[u]])
        incomingFlow = lpSum([flows[v + "->" + u] for v in CAPACITIES if u in CAPACITIES[v]])
        prob += outgoingFlow == incomingFlow # Flusserhaltung / flow conservation
        # prob += outgoingFlow - incomingFlow == 0 

prob.solve()
print(LpStatus[prob.status])
print(value(prob.objective))
for f in flows:
    print(f, ":", value(flows[f]) )

# group_assignment.py
# Based on the framework from "Classic Computer Science Problems in Python Chapter 5"
# Copyright 2024 Michael Eichberg
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
from typing import Self
from lib.chromosome import Chromosome
from lib.genetic_algorithm import GeneticAlgorithm
from random import shuffle, sample, randint
from copy import deepcopy
from collections import Counter

# Assessment of user X by user X.
# -1 is used for the reflexive case (i.e., the assessment of a user by themselves)
ASSESSMENT = [
    [-1, 10, 6, 6, 9, 6, 4, 1, 1, 9, 1, 7, 9, 3, 5, 10],
    [7, -1, 10, 8, 1, 2, 8, 9, 8, 7, 1, 2, 8, 3, 1, 10],
    [8, 9, -1, 10, 9, 4, 8, 6, 3, 5, 1, 1, 10, 10, 5, 8],
    [1, 2, 6, -1, 4, 6, 9, 9, 7, 9, 1, 1, 8, 4, 2, 4],
    [6, 8, 9, 2, -1, 6, 5, 10, 3, 8, 1, 1, 9, 7, 7, 1],
    [4, 2, 4, 8, 7, -1, 4, 9, 6, 6, 1, 10, 1, 5, 7, 7],
    [2, 3, 5, 1, 4, 4, -1, 3, 4, 10, 1, 5, 7, 3, 6, 10],
    [10, 5, 2, 5, 9, 8, 1, -1, 7, 4, 5, 1, 3, 10, 3, 3],
    [5, 8, 9, 3, 8, 3, 2, 10, -1, 10, 6, 1, 1, 6, 5, 4],
    [7, 3, 4, 7, 7, 8, 6, 2, 1, -1, 2, 1, 5, 1, 1, 8],
    [1, 8, 1, 2, 10, 6, 10, 10, 5, 10, -1, 1, 4, 9, 3, 1],
    [7, 3, 10, 7, 6, 5, 2, 3, 4, 3, 5, -1, 7, 1, 1, 5],
    [2, 8, 6, 10, 9, 4, 8, 1, 5, 8, 1, 4, -1, 8, 1, 9],
    [5, 9, 6, 9, 5, 10, 5, 10, 5, 3, 6, 7, 9, -1, 1, 9],
    [4, 10, 6, 9, 7, 8, 6, 8, 4, 9, 10, 9, 8, 9, -1, 1],
    [3, 2, 1, 4, 8, 4, 3, 2, 10, 7, 1, 1, 10, 10, 4, -1],
]
GROUPS = 4
MEMBERS = 16  # same as len(ASSESSMENT)
GROUP_SIZE = 4


class GroupAssignment(Chromosome):
    def __init__(self, groups: list[list[int]]) -> None:
        self.groups: list[list[int]] = groups

    def fitness_of_group(self, group: list[int]) -> float:
        total: float = 0
        for i in range(len(group)):
            for j in range(i + 1, len(group)):
                # Mutual Assessment
                total += ASSESSMENT[group[i]][group[j]]
                total += ASSESSMENT[group[j]][group[i]]
        return total

    def fitness(self) -> float:
        fitnessOfGroups = list(map(self.fitness_of_group, self.groups))
        average = sum(fitnessOfGroups) / len(fitnessOfGroups)
        total = sum(fitnessOfGroups)
        total -= sum(map(lambda g: abs(g - average), fitnessOfGroups)) ** 1.25
        return total

    @classmethod
    def random_instance(cls) -> Self:
        members = list(range(0, MEMBERS))
        shuffle(members)
        return GroupAssignment(
            [members[x : x + 4] for x in range(0, MEMBERS, GROUP_SIZE)]
        )

    """
    def crossover(
        self, other: Self
    ) -> tuple[Self, Self]:
        child1: GroupAssignment = deepcopy(self)
        child2: GroupAssignment = deepcopy(other)

        def find_position(assignment, p1):
            g = m = 0
            for i in range(GROUPS):
                for j in range(
                    len(assignment.groups[i])
                ):  # the last group is not always full
                    if p1 == assignment.groups[i][j]:
                        g = i
                        m = j
                        break
            return g, m

        c1g = c1m = c1p = c2g = c2m = c2p = -1

        count = 0
        # we want to ensure that a person from one group is moved to another group
        while c1g == c2g and count < 4: 
            c1g = randint(0, GROUPS - 1)
            c1m = randint(0, len(child1.groups[c1g]) - 1)
            c1p = child1.groups[c1g][c1m]  # p = person (identified by their index)
            c2g, c2m = find_position(child2, c1p)
            count += 1

        c2p = child2.groups[c2g][c2m] = child2.groups[c1g][c1m]
        child2.groups[c1g][c1m] = c1p

        c1p2g, c1p2m = find_position(child1, c2p)
        child1.groups[c1p2g][c1p2m] = child1.groups[c2g][c2m]
        child1.groups[c2g][c2m] = c2p

        return child1, child2
    """

    def crossover(self, other: Self) -> tuple[Self, Self]:
        child1: GroupAssignment = deepcopy(self)
        child2: GroupAssignment = deepcopy(other)

        # Idee: wir mischen die beiden Eltern, in dem wir in den Kindern
        # zwei Gruppen eins zu eins übernehmen und zwei weitere Gruppen
        # vom anderen Elternteil einmischen. Da es bei Einmischen zu
        # Duplikaten kommen kann, müssen wir diese eingemischten Gruppen
        # noch anpassen.
        def flatten(xs):
            ys = list()
            for x in xs:
                ys.extend(x)
            return ys

        def fix(assignments, rows):
            members = flatten(assignments)
            duplicates = [item for item, count in Counter(members).items() if count > 1]
            missing = list(set(range(0, MEMBERS)).difference(members))
            shuffle(missing)
            # delete duplicates
            for i in rows:
                for j in range(len(assignments[i]) - 1, -1, -1):
                    if assignments[i][j] in duplicates:
                        assignments[i].pop(j)
            # add missing
            for i in rows:
                selected_missing = missing[: GROUP_SIZE - len(assignments[i])]
                missing = missing[GROUP_SIZE - len(assignments[i]) :]
                assignments[i].extend(selected_missing)

        c1g2 = child1.groups[2]
        c1g3 = child1.groups[3]
        child1.groups[2] = child2.groups[0]
        child1.groups[3] = child2.groups[1]
        child2.groups[0] = c1g2
        child2.groups[1] = c1g3

        fix(child1.groups, [2, 3])
        fix(child2.groups, [0, 1])

        return child1, child2

    def mutate(self) -> None:  # swap two assignments
        g1, g2 = sample(range(len(self.groups)), k=2)
        m1 = randint(0, len(self.groups[g1]) - 1)
        m2 = randint(0, len(self.groups[g2]) - 1)
        self.groups[g1][m1], self.groups[g2][m2] = (
            self.groups[g2][m2],
            self.groups[g1][m1],
        )

    def __str__(self) -> str:
        return "Zuteilung:\n\t" + (
            "\n\t".join(
                map(
                    lambda g: str(g) + " - fitness: " + str(self.fitness_of_group(g)),
                    self.groups,
                )
            )
        )

if __name__ == "__main__":
    initial_population: list[GroupAssignment] = [
        GroupAssignment.random_instance() for _ in range(500)
    ]
    # Maximale Glücklichkeit bei unskalierter Bewertung:
    # 3! * 10 * 2 = 120
    # (pro Gruppe, wenn in einer Gruppe alle sich gegenseitig mit "10" bewerten)
    threshold = 314
    print("Configured threshold:", threshold)
    ga: GeneticAlgorithm[GroupAssignment] = GeneticAlgorithm(
        initial_population=initial_population,
        threshold=threshold,
        max_generations=1000,
        mutation_chance=0.1,
        crossover_chance=0.7,
        selection_type=GeneticAlgorithm.SelectionType.TOURNAMENT,  # ROULETTE was worse!
    )
    result: GroupAssignment = ga.run()
    print(result)

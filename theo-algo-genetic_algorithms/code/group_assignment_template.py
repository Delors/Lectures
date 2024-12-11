from typing import Self
from lib.chromosome import Chromosome
from lib.genetic_algorithm import GeneticAlgorithm
from random import shuffle, sample, randint
from copy import deepcopy
from collections import Counter

# Assessment of user X by user X. 
# -1 is used for the reflexive case 
# (i.e., the assessment of a user by themselves)
ASSESSMENT = [
    [-1, 10,  6,  6,  9,  6,  4,  1,  1,  9,  1,  7,  9,  3,  5, 10],
    [ 7, -1, 10,  8,  1,  2,  8,  9,  8,  7,  1,  2,  8,  3,  1, 10],
    [ 8,  9, -1, 10,  9,  4,  8,  6,  3,  5,  1,  1, 10, 10,  5,  8],
    [ 1,  2,  6, -1,  4,  6,  9,  9,  7,  9,  1,  1,  8,  4,  2,  4],
    [ 6,  8,  9,  2, -1,  6,  5, 10,  3,  8,  1,  1,  9,  7,  7,  1],
    [ 4,  2,  4,  8,  7, -1,  4,  9,  6,  6,  1, 10,  1,  5,  7,  7],
    [ 2,  3,  5,  1,  4,  4, -1,  3,  4, 10,  1,  5,  7,  3,  6, 10],
    [10,  5,  2,  5,  9,  8,  1, -1,  7,  4,  5,  1,  3, 10,  3,  3],
    [ 5,  8,  9,  3,  8,  3,  2, 10, -1, 10,  6,  1,  1,  6,  5,  4],
    [ 7,  3,  4,  7,  7,  8,  6,  2,  1, -1,  2,  1,  5,  1,  1,  8],
    [ 1,  8,  1,  2, 10,  6, 10, 10,  5, 10, -1,  1,  4,  9,  3,  1],
    [ 7,  3, 10,  7,  6,  5,  2,  3,  4,  3,  5, -1,  7,  1,  1,  5],
    [ 2,  8,  6, 10,  9,  4,  8,  1,  5,  8,  1,  4, -1,  8,  1,  9],
    [ 5,  9,  6,  9,  5, 10,  5, 10,  5,  3,  6,  7,  9, -1,  1,  9],
    [ 4, 10,  6,  9,  7,  8,  6,  8,  4,  9, 10,  9,  8,  9, -1,  1],
    [ 3,  2,  1,  4,  8,  4,  3,  2, 10,  7,  1,  1, 10, 10,  4, -1],
]
GROUPS = 4
MEMBERS = 16    # same as len(ASSESSMENT)
GROUP_SIZE = 4  # except of the last group


class GroupAssignment(Chromosome):
    def __init__(self, groups: list[list[int]]) -> None:
        self.groups: list[list[int]] = groups

    def fitness(self) -> float:
        raise NotImplementedError

    @classmethod
    def random_instance(cls) -> Self:
        raise NotImplementedError
        
    def crossover(
        self, other: Self
    ) -> tuple[Self, Self]:
        raise NotImplementedError

    def mutate(self) -> None:  
        raise NotImplementedError

    def __str__(self) -> str:
        return "Gruppe: " + ("Gruppe: ".join(map(lambda l: str(l) + "; ", self.groups)))


if __name__ == "__main__":
    initial_population: list[GroupAssignment] = [
        GroupAssignment.random_instance() for _ in range(250)
    ]
    threshold = 345 
    print("Configured threshold:", threshold)
    ga: GeneticAlgorithm[GroupAssignment] = GeneticAlgorithm(
        initial_population=initial_population,
        threshold=threshold,
        max_generations=250,
        mutation_chance=0.05, 
        crossover_chance=0.7, 
        selection_type=GeneticAlgorithm.SelectionType.TOURNAMENT, 
    )
    result: GroupAssignment = ga.run()
    print(result)

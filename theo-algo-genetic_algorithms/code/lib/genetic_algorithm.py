# genetic_algorithm.py
# From Classic Computer Science Problems in Python Chapter 5
# Copyright 2018 David Kopec
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

# 2024: Typehints adapted for Python 3.13
from typing import TypeVar, Generic, Callable
from enum import Enum
from random import choices, random
from heapq import nlargest
from statistics import mean
from lib.chromosome import Chromosome

C = TypeVar("C", bound=Chromosome)  # type of the chromosomes


class GeneticAlgorithm(Generic[C]):
    SelectionType = Enum("SelectionType", "ROULETTE TOURNAMENT")

    def __init__(
        self,
        initial_population: list[C],
        threshold: float,
        max_generations: int = 100,
        mutation_chance: float = 0.01,
        crossover_chance: float = 0.7,
        selection_type: SelectionType = SelectionType.TOURNAMENT,
    ) -> None:
        self._population: list[C] = initial_population
        self._threshold: float = threshold
        self._max_generations: int = max_generations
        self._mutation_chance: float = mutation_chance
        self._crossover_chance: float = crossover_chance
        self._selection_type: GeneticAlgorithm.SelectionType = selection_type
        self._fitness_key: Callable = type(self._population[0]).fitness

    # Use the probability distribution wheel to pick 2 parents
    # Note: will not work with negative fitness results
    def _pick_roulette(
            self, wheel: list[float]) -> tuple[C, C]:
        c: tuple[C, C] = \
            tuple(choices(
                    self._population, 
                    weights=wheel, k=2))
        return c

    # Choose num_participants at random and take the best 2
    def _pick_tournament(
            self, num_participants: int) -> tuple[C, C]:
        participants: list[C] = \
            choices(self._population, k=num_participants)
        return tuple(
            nlargest(
                2, 
                participants, key=self._fitness_key)) # type: ignore

    # Replace the population with a new generation of individuals
    def _reproduce_and_replace(self) -> None:
        new_population: list[C] = []
        # Selektiere und Kreuze Individuen, bis die neue Population steht
        while len(new_population) < len(self._population):
            # Wahl der zwei Eltern gemäß Selektionsmethode
            # (Roulette oder Turnier)
            if self._selection_type == \
                GeneticAlgorithm.SelectionType.ROULETTE:
                parents: tuple[C, C] = self._pick_roulette(
                    [x.fitness() for x in self._population]
                )
            else:
                parents = \
                    self._pick_tournament(len(self._population) // 2)
            # Kreuze ggf. die Eltern
            if random() < self._crossover_chance:
                new_population.extend(parents[0].crossover(parents[1]))
            else:
                new_population.extend(parents)
        # Falls die Populationsgröße ungerade ist, 
        # entferne das letzte Individuum
        if len(new_population) > len(self._population):
            new_population.pop()
        self._population = new_population  # replace reference

    # With _mutation_chance probability mutate each individual
    def _mutate(self) -> None:
        for individual in self._population:
            if random() < self._mutation_chance:
                individual.mutate()

    # Run the genetic algorithm for max_generations iterations
    # and return the best individual found
    def run(self) -> C:
        best: C = max(self._population, key=self._fitness_key)
        for generation in range(self._max_generations):
            # early exit if we beat threshold
            if best.fitness() >= self._threshold:
                return best
            print(
                f"Generation {generation} Best {best.fitness()} Avg {
                    mean(map(self._fitness_key, self._population))
                }")
            self._reproduce_and_replace() # selection and crossover
            self._mutate()
            highest: C = max(self._population, key=self._fitness_key)
            if highest.fitness() > best.fitness():
                best = highest  # found a new best
        return best  # best we found in _max_generations

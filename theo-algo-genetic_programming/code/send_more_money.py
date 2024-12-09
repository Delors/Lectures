# send_more_money2.py
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

from typing import Self
from lib.chromosome import Chromosome
from lib.genetic_algorithm import GeneticAlgorithm
from random import shuffle, sample
from copy import deepcopy

# class SendMoreMoney(Chromosome)
class SendMoreMoney(Chromosome):
    def __init__(self, letters: list[str]) -> None:
        self.letters: list[str] = letters

    # The fitness function 
    # Fitness is calculated as the 
    # difference between the sum of the words SEND and MORE and 
    # the word MONEY.
    # fitness()
    def fitness(self) -> float:
        s: int = self.letters.index("S")
        e: int = self.letters.index("E")
        n: int = self.letters.index("N")
        d: int = self.letters.index("D")
        m: int = self.letters.index("M")
        o: int = self.letters.index("O")
        r: int = self.letters.index("R")
        y: int = self.letters.index("Y")
        send: int = s * 1000 + e * 100 + n * 10 + d
        more: int = m * 1000 + o * 100 + r * 10 + e
        money: int = m * 10000 + o * 1000 + n * 100 + e * 10 + y
        return 1 / (abs(money - (send + more)) + 1)

    @classmethod
    # random_instance()
    def random_instance(cls) -> Self:
        letters = [ 
            "S", "E", "N", "D", "M", "O", "R", "Y", 
            " ", " "]
        shuffle(letters)
        return SendMoreMoney(letters)

    # crossover()
    def crossover(self, other: Self) -> tuple[Self, Self]:
        child1: SendMoreMoney = deepcopy(self)
        child2: SendMoreMoney = deepcopy(other)
        idx1, idx2 = sample(range(len(self.letters)), k=2)
        l1, l2 = child1.letters[idx1], child2.letters[idx2]
        child1.letters[child1.letters.index(l2)], \
        child1.letters[idx2] = \
            child1.letters[idx2], l2
        
        child2.letters[child2.letters.index(l1)], \
        child2.letters[idx1] = \
            child2.letters[idx1], l1
        
        return child1, child2

    # mutate()
    def mutate(self) -> None: # swap two letters' locations
        idx1, idx2 = sample(range(len(self.letters)), k=2)
        self.letters[idx1], self.letters[idx2] = \
            self.letters[idx2], self.letters[idx1]

    # __str__()
    def __str__(self) -> str:
        s: int = self.letters.index("S")
        e: int = self.letters.index("E")
        n: int = self.letters.index("N")
        d: int = self.letters.index("D")
        m: int = self.letters.index("M")
        o: int = self.letters.index("O")
        r: int = self.letters.index("R")
        y: int = self.letters.index("Y")
        send: int = s * 1000 + e * 100 + n * 10 + d
        more: int = m * 1000 + o * 100 + r * 10 + e
        money: int = m * 10000 + o * 1000 + n * 100 + e * 10 + y
        difference: int = abs(money - (send + more))
        return f"{send} + {more} = {money} Difference: {difference}"


if __name__ == "__main__":
    initial_population: list[SendMoreMoney] = \
        [SendMoreMoney.random_instance() for _ in range(1000)]
    ga: GeneticAlgorithm[SendMoreMoney] = \
        GeneticAlgorithm(
            initial_population=initial_population, 
            threshold=1.0,
            max_generations = 1000, 
            mutation_chance = 0.2, 
            crossover_chance = 0.7, 
            selection_type= \
                GeneticAlgorithm.SelectionType.ROULETTE)
    result: SendMoreMoney = ga.run()
    print(result)

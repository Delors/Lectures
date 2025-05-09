# chromosome.py
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
from typing import TypeVar, Type
from abc import ABC, abstractmethod

T = TypeVar('T', bound='Chromosome') # for returning self


# Base class for all chromosomes
class Chromosome(ABC):
    @abstractmethod
    def fitness(self) -> float:
        raise NotImplementedError

    @classmethod
    @abstractmethod
    def random_instance(cls: Type[T]) -> T:
        raise NotImplementedError

    @abstractmethod
    def crossover(self: T, other: T) -> tuple[T, T]:
        raise NotImplementedError

    @abstractmethod
    def mutate(self) -> None:
        raise NotImplementedError

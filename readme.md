## Construire un framework CQRS et Event Sourcing de zero

Objectif : Faire emerger un simple framework ayant les composants de base du CQRS et Event Sourcing.

Les composants principals :

- Des composants de logiques metier postant des evenements -> commandHandler
- Un composant prenant un evenement et generant un state -> reducer
- Des abstractions des repositories
- un engine pour exposer les handlers et commands


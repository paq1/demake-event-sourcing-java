package org.example;

import org.example.myOntology.repository.JournalRepositoryMock;
import org.example.myOntology.repository.StoreRepositoryMock;
import org.example.myontology.CancelCommand;
import org.example.myontology.CreateCommand;
import org.example.myontology.MyOntologyCommands;
import org.example.myontology.commandHandlers.CancelMyOntology;
import org.example.myontology.commandHandlers.CreateMyOntology;
import org.example.myontology.events.MyOntologyEvents;
import org.example.myontology.reducer.MyOntologyReducer;
import org.example.myontology.states.MyOntologyStates;
import org.example.shared.eventsourcing.dispatcher.CommandDispatcher;
import org.example.shared.eventsourcing.engine.SimpleCommandEngine;

public class Main {
    public static void main(String[] args) {
        var store = new StoreRepositoryMock();
        var journal = new JournalRepositoryMock();

        var commandDispatcher = new CommandDispatcher()
                .add(new CreateMyOntology())
                .add(new CancelMyOntology());

        var reducer = new MyOntologyReducer();

        var engine = new SimpleCommandEngine<MyOntologyStates, MyOntologyCommands, MyOntologyEvents>(
                commandDispatcher,
                store,
                journal,
                reducer
        );

        var resultCreate = engine.computeAndSave(
                new CreateCommand("toto"),
                "1",
                CreateMyOntology.NAME
        );

        var entityId = resultCreate.get().entityId(); // osef c pour test mdr :)

        System.out.println("journal :");
        System.out.println(journal.findAll());
        System.out.println("store :");
        System.out.println(store.findOne(entityId));
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        engine.computeAndSave(
                new CancelCommand(),
                "1",
                CancelMyOntology.NAME
        );

        System.out.println("journal :");
        System.out.println(journal.findAll());
        System.out.println("store :");
        System.out.println(store.findOne(entityId));
    }
}
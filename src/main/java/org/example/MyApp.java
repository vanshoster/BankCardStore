package org.example;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Environment;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import org.example.config.AppConfig;
import org.example.model.Card;
import org.example.repository.CardRepository;
import org.example.resourses.CardResource;

import java.time.LocalDate;

public class MyApp extends Application<AppConfig> {
    public static void main(String[] args) throws Exception {
        new MyApp().run(args);
    }

    @Override
    public void run(AppConfig configuration, Environment environment) {
        MongoClient mongoClient = MongoClients.create(
                String.format("mongodb://localhost:27017", configuration.getMongoHost(), configuration.getMongoPort())
        );

        CardRepository cardRepository = new CardRepository(mongoClient, configuration.getMongoDB(), "cards");

        Card ininalCard = new Card("4609628810815555", "Ivan Nekrasov", LocalDate.of(2028, 8, 31),
                "123", "ININAL", "Debit", "1234567890", "Turkye", true );

        Card ziraatCard = new Card("4609458810815555", "Ivan Nekrasov", LocalDate.of(2030, 9, 30),
                "321", "Ziraat Bank", "Credit", "0987654321", "Turkye", true );



        cardRepository.insertCard(ininalCard);
        cardRepository.insertCard(ziraatCard);

        environment.jersey().register(new CardResource(cardRepository));
    }
}


package org.example.repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.example.model.Card;

import java.util.ArrayList;
import java.util.List;

public class CardRepository {
    private final MongoCollection<Document> collection;

    public CardRepository(MongoClient client, String dbName, String collectionName) {
        MongoDatabase database = client.getDatabase(dbName);
        this.collection = database.getCollection(collectionName);
    }

    public void insertCard(Card card) {
        Document doc = new Document("cardNumber", card.getCardNumber())
                .append("cardholderName", card.getCardholderName())
                .append("expirationDate", card.getExpirationDate().toString())
                .append("securityCode", card.getSecurityCode())
                .append("bankName", card.getBankName())
                .append("cardType", card.getCardType())
                .append("accountNumber", card.getAccountNumber())
                .append("issuingCountry", card.getIssuingCountry())
                .append("chipEnabled", card.isChipEnabled());
        collection.insertOne(doc);
    }

    // Метод поиска карты по номеру карты
    public Document findCardByNumber(String cardNumber) {
        return collection.find(Filters.eq("cardNumber", cardNumber)).first();
    }

    // Метод поиска всех карт по названию банка
    public List<Document> findCardsByBankName(String bankName) {
        List<Document> cards = new ArrayList<>();
        collection.find(Filters.eq("bankName", bankName)).into(cards);
        return cards;
    }

    // Метод удаления карты по номеру карты
    public void deleteCardByNumber(String cardNumber) {
        collection.deleteOne(Filters.eq("cardNumber", cardNumber));
    }

    // Метод для получения списка всех карт
    public List<Document> findAllCards() {
        List<Document> cards = new ArrayList<>();
        FindIterable<Document> documents = collection.find();
        for (Document doc : documents) {
            cards.add(doc);
        }
        return cards;
    }
}

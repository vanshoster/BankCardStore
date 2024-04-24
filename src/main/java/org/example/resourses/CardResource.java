package org.example.resourses;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.example.model.Card;
import org.example.repository.CardRepository;
import java.util.List;
import java.util.stream.Collectors;

@Path("/cards")
@Produces(MediaType.APPLICATION_JSON)
public class CardResource {

    private final CardRepository cardRepository;

    public CardResource(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GET
    public Response getAllCards() {
        try {
            List<Document> cards = cardRepository.findAllCards();
            return Response.ok(cards.stream().map(Document::toJson).collect(Collectors.toList())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/number/{cardNumber}")
    public Response getCardByNumber(@PathParam("cardNumber") String cardNumber) {
        Document card = cardRepository.findCardByNumber(cardNumber);
        if (card == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(card.toJson()).build();
    }

    @GET
    @Path("/bank/{bankName}")
    public Response getCardsByBankName(@PathParam("bankName") String bankName) {
        List<Document> cards = cardRepository.findCardsByBankName(bankName);
        return Response.ok(cards.stream().map(Document::toJson).collect(Collectors.toList())).build();
    }

    @DELETE
    @Path("/number/{cardNumber}")
    public Response deleteCard(@PathParam("cardNumber") String cardNumber) {
        cardRepository.deleteCardByNumber(cardNumber);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    public Response addCard(Card card) {
        try {
            cardRepository.insertCard(card);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}

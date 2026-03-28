package com.frontend.service;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import com.frontend.dto.MemberDTO;
import com.frontend.dto.EntityDTO;
import com.frontend.dto.EndpointDTO;

/**
 * Single source of truth for team members and their API endpoints.
 * Injected into TeamController — keeps controller lean.
 */
@Component
public class TeamDataService {

    public List<MemberDTO> getMembers() {
        return Arrays.asList(
            biswajit(),
            debapriya(),
            rajarshi(),
            harsh(),
            divyansh(),
            deblina()
        );
    }

    // ── 1. Biswajit Das — Film, Actor, Film_Actor, Film_Text ─────────────────

    private MemberDTO biswajit() {
        return new MemberDTO(0,
            "Biswajit Das", "🎬", "#e8ff47",
            "Film & Cast Module",
            Arrays.asList("Film", "Actor", "Film_Actor", "Film_Text"),
            Arrays.asList(
                new EntityDTO("Film", Arrays.asList(
                    new EndpointDTO("GET",    "/api/films",                      "Get all films"),
                    new EndpointDTO("GET",    "/api/films/{id}",                 "Get film by ID"),
                    new EndpointDTO("GET",    "/api/films/title/{title}",        "Search by title"),
                    new EndpointDTO("POST",   "/api/films",                      "Create a new film"),
                    new EndpointDTO("PUT",    "/api/films/{id}",                 "Update film by ID"),
                    new EndpointDTO("DELETE", "/api/films/{id}",                 "Delete film by ID")
                )),
                new EntityDTO("Actor", Arrays.asList(
                    new EndpointDTO("GET",    "/api/actors",                     "Get all actors"),
                    new EndpointDTO("GET",    "/api/actors/{id}",                "Get actor by ID"),
                    new EndpointDTO("POST",   "/api/actors",                     "Create a new actor"),
                    new EndpointDTO("PUT",    "/api/actors/{id}",                "Update actor by ID"),
                    new EndpointDTO("DELETE", "/api/actors/{id}",                "Delete actor by ID")
                )),
                new EntityDTO("Film Actor", Arrays.asList(
                    new EndpointDTO("GET",    "/api/film-actors",                     "Get all film-actor links"),
                    new EndpointDTO("GET",    "/api/film-actors/film/{filmId}",       "Get actors by film"),
                    new EndpointDTO("GET",    "/api/film-actors/actor/{actorId}",     "Get films by actor"),
                    new EndpointDTO("POST",   "/api/film-actors",                     "Link actor to film"),
                    new EndpointDTO("DELETE", "/api/film-actors/{filmId}/{actorId}",  "Unlink actor from film")
                )),
                new EntityDTO("Film Text", Arrays.asList(
                    new EndpointDTO("GET", "/api/film-texts",      "Get all film texts"),
                    new EndpointDTO("GET", "/api/film-texts/{id}", "Get film text by ID")
                ))
            )
        );
    }

    // ── 2. Debapriya Das — Category, Film_Category, Language ─────────────────

    private MemberDTO debapriya() {
        return new MemberDTO(1,
            "Debapriya Das", "🏷️", "#ff6b35",
            "Category & Language Module",
            Arrays.asList("Category", "Film_Category", "Language"),
            Arrays.asList(
                new EntityDTO("Category", Arrays.asList(
                    new EndpointDTO("GET",    "/api/categories",          "Get all categories"),
                    new EndpointDTO("GET",    "/api/categories/{id}",     "Get category by ID"),
                    new EndpointDTO("POST",   "/api/categories",          "Create a category"),
                    new EndpointDTO("PUT",    "/api/categories/{id}",     "Update category"),
                    new EndpointDTO("DELETE", "/api/categories/{id}",     "Delete category")
                )),
                new EntityDTO("Film Category", Arrays.asList(
                    new EndpointDTO("GET",    "/api/film-categories",                       "Get all film-category links"),
                    new EndpointDTO("GET",    "/api/film-categories/film/{filmId}",         "Get categories of a film"),
                    new EndpointDTO("POST",   "/api/film-categories",                       "Link film to category"),
                    new EndpointDTO("DELETE", "/api/film-categories/{filmId}/{categoryId}", "Unlink film from category")
                )),
                new EntityDTO("Language", Arrays.asList(
                    new EndpointDTO("GET",    "/api/languages",           "Get all languages"),
                    new EndpointDTO("GET",    "/api/languages/{id}",      "Get language by ID"),
                    new EndpointDTO("POST",   "/api/languages",           "Create a language"),
                    new EndpointDTO("PUT",    "/api/languages/{id}",      "Update language"),
                    new EndpointDTO("DELETE", "/api/languages/{id}",      "Delete language")
                ))
            )
        );
    }

    // ── 3. Rajarshi — Country, City, Address ─────────────────────────────────

    private MemberDTO rajarshi() {
        return new MemberDTO(2,
            "Rajarshi Karmakar", "🗺️", "#47c8ff",
            "Geography Module",
            Arrays.asList("Country", "City", "Address"),
            Arrays.asList(
                new EntityDTO("Country", Arrays.asList(
                    new EndpointDTO("GET",    "/api/countries",           "Get all countries"),
                    new EndpointDTO("GET",    "/api/countries/{id}",      "Get country by ID"),
                    new EndpointDTO("POST",   "/api/countries",           "Create a country"),
                    new EndpointDTO("PUT",    "/api/countries/{id}",      "Update country"),
                    new EndpointDTO("DELETE", "/api/countries/{id}",      "Delete country")
                )),
                new EntityDTO("City", Arrays.asList(
                    new EndpointDTO("GET",    "/api/cities",              "Get all cities"),
                    new EndpointDTO("GET",    "/api/cities/{id}",         "Get city by ID"),
                    new EndpointDTO("POST",   "/api/cities",              "Create a city"),
                    new EndpointDTO("PUT",    "/api/cities/{id}",         "Update city"),
                    new EndpointDTO("DELETE", "/api/cities/{id}",         "Delete city")
                )),
                new EntityDTO("Address", Arrays.asList(
                    new EndpointDTO("GET",    "/api/addresses",           "Get all addresses"),
                    new EndpointDTO("GET",    "/api/addresses/{id}",      "Get address by ID"),
                    new EndpointDTO("POST",   "/api/addresses",           "Create an address"),
                    new EndpointDTO("PUT",    "/api/addresses/{id}",      "Update address"),
                    new EndpointDTO("DELETE", "/api/addresses/{id}",      "Delete address")
                ))
            )
        );
    }

    // ── 4. Harsh Kumar — Store, Staff, Inventory ─────────────────────────────

    private MemberDTO harsh() {
        return new MemberDTO(3,
            "Harsh Kumar", "🏪", "#c47fff",
            "Store & Inventory Module",
            Arrays.asList("Store", "Staff", "Inventory"),
            Arrays.asList(
                new EntityDTO("Store", Arrays.asList(
                    new EndpointDTO("GET",    "/api/stores",              "Get all stores"),
                    new EndpointDTO("GET",    "/api/stores/{id}",         "Get store by ID"),
                    new EndpointDTO("POST",   "/api/stores",              "Create a store"),
                    new EndpointDTO("PUT",    "/api/stores/{id}",         "Update store"),
                    new EndpointDTO("DELETE", "/api/stores/{id}",         "Delete store")
                )),
                new EntityDTO("Staff", Arrays.asList(
                    new EndpointDTO("GET",    "/api/staff",               "Get all staff"),
                    new EndpointDTO("GET",    "/api/staff/{id}",          "Get staff by ID"),
                    new EndpointDTO("POST",   "/api/staff",               "Create staff member"),
                    new EndpointDTO("PUT",    "/api/staff/{id}",          "Update staff member"),
                    new EndpointDTO("DELETE", "/api/staff/{id}",          "Delete staff member")
                )),
                new EntityDTO("Inventory", Arrays.asList(
                    new EndpointDTO("GET",    "/api/inventory",                    "Get all inventory"),
                    new EndpointDTO("GET",    "/api/inventory/{id}",               "Get inventory by ID"),
                    new EndpointDTO("GET",    "/api/inventory/store/{storeId}",    "Inventory by store"),
                    new EndpointDTO("POST",   "/api/inventory",                    "Add film to inventory"),
                    new EndpointDTO("DELETE", "/api/inventory/{id}",               "Remove inventory item")
                ))
            )
        );
    }

    // ── 5. Divyansh Poddar — Customer, Rental ────────────────────────────────

    private MemberDTO divyansh() {
        return new MemberDTO(4,
            "Divyansh Poddar", "👤", "#ff47a8",
            "Customer & Rental Module",
            Arrays.asList("Customer", "Rental"),
            Arrays.asList(
                new EntityDTO("Customer", Arrays.asList(
                    new EndpointDTO("GET",    "/api/customers",                     "Get all customers"),
                    new EndpointDTO("GET",    "/api/customers/{id}",                "Get customer by ID"),
                    new EndpointDTO("GET",    "/api/customers/store/{storeId}",     "Customers by store"),
                    new EndpointDTO("POST",   "/api/customers",                     "Create a customer"),
                    new EndpointDTO("PUT",    "/api/customers/{id}",                "Update customer"),
                    new EndpointDTO("DELETE", "/api/customers/{id}",                "Delete customer")
                )),
                new EntityDTO("Rental", Arrays.asList(
                    new EndpointDTO("GET",    "/api/rentals",                        "Get all rentals"),
                    new EndpointDTO("GET",    "/api/rentals/{id}",                   "Get rental by ID"),
                    new EndpointDTO("GET",    "/api/rentals/customer/{customerId}",  "Rentals by customer"),
                    new EndpointDTO("POST",   "/api/rentals",                        "Create a rental"),
                    new EndpointDTO("PUT",    "/api/rentals/{id}/return",            "Return a rental"),
                    new EndpointDTO("DELETE", "/api/rentals/{id}",                   "Delete rental")
                ))
            )
        );
    }

    // ── 6. Deblina Dey — Payments ────────────────────────────────────────────

    private MemberDTO deblina() {
        return new MemberDTO(5,
            "Deblina Dey", "💳", "#47ffc8",
            "Payments Module",
            Arrays.asList("Payments"),
            Arrays.asList(
                new EntityDTO("Payments", Arrays.asList(
                    new EndpointDTO("GET",    "/api/payments",                          "Get all payments"),
                    new EndpointDTO("GET",    "/api/payments/{id}",                     "Get payment by ID"),
                    new EndpointDTO("GET",    "/api/payments/customer/{customerId}",    "Payments by customer"),
                    new EndpointDTO("GET",    "/api/payments/rental/{rentalId}",        "Payments by rental"),
                    new EndpointDTO("GET",    "/api/payments/staff/{staffId}",          "Payments by staff"),
                    new EndpointDTO("POST",   "/api/payments",                          "Create a payment"),
                    new EndpointDTO("PUT",    "/api/payments/{id}",                     "Update payment"),
                    new EndpointDTO("DELETE", "/api/payments/{id}",                     "Delete payment")
                ))
            )
        );
    }
}
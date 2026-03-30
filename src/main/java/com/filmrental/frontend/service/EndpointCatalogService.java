package com.filmrental.frontend.service;

import com.filmrental.frontend.model.EndpointDefinition;
import com.filmrental.frontend.model.TeamMember;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EndpointCatalogService {

    private final List<TeamMember> members;
    private final Map<String, List<EndpointDefinition>> endpointsByMember;

    public EndpointCatalogService() {
        this.members = List.of(
                new TeamMember("biswajit-das", "Biswajit Das", "Category, Film_Category, Language", "https://picsum.photos/seed/biswajit/720/420", "#6ee7ff"),
                new TeamMember("debapriya-das", "Debapriya Das", "Film, Actor, Film_Actor, Film_Text", "https://picsum.photos/seed/debapriya/720/420", "#ffd166"),
                new TeamMember("rajarshi", "Rajarshi Karmakar", "Country, City, Address", "https://picsum.photos/seed/rajarshi/720/420", "#98f5a7"),
                new TeamMember("harsh-kumar", "Harsh Kumar", "Store, Staff", "https://picsum.photos/seed/harsh/720/420", "#ff9ec9"),
                new TeamMember("divyansh-poddar", "Divyansh Poddar", "Rental, Customer, Payment, Inventory", "https://picsum.photos/seed/divyansh/720/420", "#c1a4ff")
        );

        Map<String, List<EndpointDefinition>> map = new LinkedHashMap<>();


        // 1. BISWAJIT DAS (Category, Language)

        map.put("biswajit-das", List.of(
                // Category Endpoints
                endpoint("cat-all", "Category", "GET", "/api/category", "Fetch All Categories"),
                endpoint("cat-search", "Category", "GET", "/api/category/search?name={name}", "Search Category by Name"),
                endpoint("cat-id", "Category", "GET", "/api/category/{id}", "Get Category by ID"),
                endpoint("cat-post", "Category", "POST", "/api/category", "Create Category"),
                endpoint("cat-put", "Category", "PUT", "/api/category/{id}", "Update Category"),
                endpoint("cat-delete", "Category", "DELETE", "/api/category/{id}", "Delete Category"),

                // Language Endpoints
                endpoint("lang-all", "Language", "GET", "/api/language", "Fetch All Languages"),
                endpoint("lang-search", "Language", "GET", "/api/language/search?name={name}", "Search Language by Name"),
                endpoint("lang-exists", "Language", "GET", "/api/language/exists?name={name}", "Check Language Exists"),
                endpoint("lang-id", "Language", "GET", "/api/language/{id}", "Get Language by ID"),
                endpoint("lang-films", "Language", "GET", "/api/language/{id}/with-films", "Get Language with Films"),
                endpoint("lang-count", "Language", "GET", "/api/language/{id}/film-count", "Count Films by Language"),
                endpoint("lang-post", "Language", "POST", "/api/language", "Create Language"),
                endpoint("lang-put", "Language", "PUT", "/api/language/{id}", "Update Language"),
                endpoint("lang-delete", "Language", "DELETE", "/api/language/{id}", "Delete Language")
        ));


        // 2. DEBAPRIYA DAS (Film, Actor)

        map.put("debapriya-das", List.of(
                // Film Endpoints
                endpoint("film-all", "Film", "GET", "/api/film", "Fetch All Films"),
                endpoint("film-id", "Film", "GET", "/api/film/{id}", "Get Film by ID"),
                endpoint("film-title", "Film", "GET", "/api/film/title/{title}", "Search by Title"),
                endpoint("film-year", "Film", "GET", "/api/film/year/{year}", "Search by Release Year"),
                endpoint("film-dur", "Film", "GET", "/api/film/duration/gt/{rd}", "Duration Greater Than"),
                endpoint("film-rate", "Film", "GET", "/api/film/rate/gt/{rate}", "Rental Rate Greater Than"),
                endpoint("film-post", "Film", "POST", "/api/film/post", "Create Film"),

                // Actor Endpoints
                endpoint("actor-all", "Actor", "GET", "/api/actors/toptenbyfilmcount", "Top Ten Actors by Films"),
                endpoint("actor-id", "Actor", "GET", "/api/actors/{id}", "Get Actor by ID"),
                endpoint("actor-last", "Actor", "GET", "/api/actors/lastname/{ln}", "Search by Last Name"),
                endpoint("actor-first", "Actor", "GET", "/api/actors/firstname/{fn}", "Search by First Name"),
                endpoint("actor-films", "Actor", "GET", "/api/actors/{id}/films", "Films for Actor"),
                endpoint("actor-links", "Actor", "GET", "/api/actors/{id}/film", "Actor Film Links"),
                endpoint("actor-post", "Actor", "POST", "/api/actors/post", "Create Actor"),
                endpoint("actor-put-last", "Actor", "PUT", "/api/actors/update/lastname/{id}?lastName={lastName}", "Update Last Name")
        ));


        // 3. RAJARSHI KARMAKAR (Country, City, Address)

        map.put("rajarshi", List.of(
                // Country Endpoints
                endpoint("country-all", "Country", "GET", "/api/country", "Fetch All Countries"),
                endpoint("country-id", "Country", "GET", "/api/country/{id}", "Get Country by ID"),
                endpoint("country-search", "Country", "GET", "/api/country/search?name={name}", "Search Country by Name"),
                endpoint("country-exists", "Country", "GET", "/api/country/exists?name={name}", "Check Country Exists"),
                endpoint("country-post", "Country", "POST", "/api/country", "Create Country"),
                endpoint("country-put", "Country", "PUT", "/api/country/{id}", "Update Country"),
                endpoint("country-delete", "Country", "DELETE", "/api/country/{id}", "Delete Country"),

                // City Endpoints
                endpoint("city-all", "City", "GET", "/api/city", "Fetch All Cities"),
                endpoint("city-id", "City", "GET", "/api/city/{id}", "Get City by ID"),
                endpoint("city-search", "City", "GET", "/api/city/search/name?city={city}", "Search City by Name"),
                endpoint("city-country-search", "City", "GET", "/api/city/search/country?country={country}", "Cities by Country Name"),
                endpoint("city-country-id", "City", "GET", "/api/city/country/{countryId}", "Cities by Country ID"),
                endpoint("city-post", "City", "POST", "/api/city/country/{countryId}", "Create City"),
                endpoint("city-put", "City", "PUT", "/api/city/{id}", "Update City"),
                endpoint("city-delete", "City", "DELETE", "/api/city/{id}", "Delete City"),

                // Address Endpoints
                endpoint("addr-all", "Address", "GET", "/api/address", "Fetch All Addresses"),
                endpoint("addr-id", "Address", "GET", "/api/address/{id}", "Get Address by ID"),
                endpoint("addr-district", "Address", "GET", "/api/address/search?district={district}", "Search by District"),
                endpoint("addr-city", "Address", "GET", "/api/address/city/{cityId}", "Addresses by City"),
                endpoint("addr-post", "Address", "POST", "/api/address", "Create Address"),
                endpoint("addr-put", "Address", "PUT", "/api/address/{id}", "Update Address"),
                endpoint("addr-delete", "Address", "DELETE", "/api/address/{id}", "Delete Address")
        ));


        // 4. HARSH KUMAR (Store, Staff)

        map.put("harsh-kumar", List.of(
                // Store Endpoints
                endpoint("store-all", "Store", "GET", "/api/store", "Fetch All Stores"),
                endpoint("store-id", "Store", "GET", "/api/store/{id}", "Get Store by ID"),
                endpoint("store-staff", "Store", "GET", "/api/store/manager-staff/{staffId}", "Stores by Manager"),
                endpoint("store-post", "Store", "POST", "/api/store/post", "Create Store"),
                endpoint("store-put", "Store", "PUT", "/api/store/{id}", "Update Store"),
                endpoint("store-delete", "Store", "DELETE", "/api/store/{id}", "Delete Store"),

                // Staff Endpoints
                endpoint("staff-all", "Staff", "GET", "/api/staff", "Fetch All Staff"),
                endpoint("staff-id", "Staff", "GET", "/api/staff/{id}", "Get Staff by ID"),
                endpoint("staff-email", "Staff", "GET", "/api/staff/email/{email}", "Search by Email"),
                endpoint("staff-last", "Staff", "GET", "/api/staff/lastname/{ln}", "Search by Last Name"),
                endpoint("staff-first", "Staff", "GET", "/api/staff/firstname/{fn}", "Search by First Name"),
                endpoint("staff-post", "Staff", "POST", "/api/staff/post", "Create Staff"),
                endpoint("staff-put", "Staff", "PUT", "/api/staff/{id}", "Update Staff"),
                endpoint("staff-activate", "Staff", "PATCH", "/api/staff/{id}/activate", "Activate Staff"),
                endpoint("staff-deactivate", "Staff", "PATCH", "/api/staff/{id}/deactivate", "Deactivate Staff")
        ));


        // 5. DIVYANSH PODDAR (Rental, Customer, Payment, Inventory)

        map.put("divyansh-poddar", List.of(
                // Rental Endpoints
                endpoint("rent-all", "Rental", "GET", "/api/rental", "Fetch All Rentals"),
                endpoint("rent-id", "Rental", "GET", "/api/rental/{id}", "Find Rental by ID"),
                endpoint("rent-customer", "Rental", "GET", "/api/rental/customer/{customerId}", "Rentals by Customer"),
                endpoint("rent-top", "Rental", "GET", "/api/rental/toptenfilms", "Top 10 Rented Films"),
                endpoint("rent-top-store", "Rental", "GET", "/api/rental/toptenfilms/store/{storeId}", "Top 10 Films per Store"),
                endpoint("rent-due", "Rental", "GET", "/api/rental/due/store/{storeId}", "Due Rentals per Store"),
                endpoint("rent-add", "Rental", "POST", "/api/rental/add?customerId={customerId}&staffId={staffId}&inventoryId={inventoryId}", "Create New Rental"),
                endpoint("rent-put", "Rental", "PUT", "/api/rental/{id}?customerId={customerId}&staffId={staffId}&inventoryId={inventoryId}", "Update Entire Rental"),
                endpoint("rent-return", "Rental", "PUT", "/api/rental/update/returndate/{id}?returnDate={returnDate}", "Update Return Date"),

                // Customer Endpoints
                endpoint("cust-all", "Customer", "GET", "/api/customer", "Fetch All Customers"),
                endpoint("cust-id", "Customer", "GET", "/api/customer/{id}", "Get Customer by ID"),
                endpoint("cust-email", "Customer", "GET", "/api/customer/email/{email}", "Search by Email"),
                endpoint("cust-last", "Customer", "GET", "/api/customer/lastname/{ln}", "Search by Last Name"),
                endpoint("cust-first", "Customer", "GET", "/api/customer/firstname/{fn}", "Search by First Name"),
                endpoint("cust-store", "Customer", "GET", "/api/customer/store/{storeId}", "Customers by Store"),
                endpoint("cust-post", "Customer", "POST", "/api/customer/post", "Create Customer"),
                endpoint("cust-put", "Customer", "PUT", "/api/customer/{id}", "Update Customer"),
                endpoint("cust-activate", "Customer", "PATCH", "/api/customer/{id}/activate", "Activate Customer"),
                endpoint("cust-deactivate", "Customer", "PATCH", "/api/customer/{id}/deactivate", "Deactivate Customer"),
                endpoint("cust-del", "Customer", "DELETE", "/api/customer/{id}", "Delete Customer"),

                // Payment Endpoints
                endpoint("pay-all", "Payment", "GET", "/api/payment", "Fetch All Payments"),
                endpoint("pay-id", "Payment", "GET", "/api/payment/{id}", "Get Payment by ID"),
                endpoint("pay-cust", "Payment", "GET", "/api/payment/customer/{customerId}", "Payments by Customer"),
                endpoint("pay-staff", "Payment", "GET", "/api/payment/staff/{staffId}", "Payments by Staff"),
                endpoint("pay-rental", "Payment", "GET", "/api/payment/rental/{rentalId}", "Payments by Rental"),
                endpoint("pay-rev-date", "Payment", "GET", "/api/payment/revenue/datewise", "Revenue Datewise"),
                endpoint("pay-rev-store", "Payment", "GET", "/api/payment/revenue/datewise/store/{id}", "Revenue Datewise by Store"),
                endpoint("pay-add", "Payment", "POST", "/api/payment/add?customerId={customerId}&staffId={staffId}&rentalId={rentalId}", "Add Payment"),
                endpoint("pay-put", "Payment", "PUT", "/api/payment/{id}?customerId={customerId}&staffId={staffId}&rentalId={rentalId}", "Update Payment"),

                // Inventory Endpoints
                endpoint("inv-all", "Inventory", "GET", "/api/inventory", "Fetch All Inventory"),
                endpoint("inv-id", "Inventory", "GET", "/api/inventory/{id}", "Get Inventory by ID"),
                endpoint("inv-store", "Inventory", "GET", "/api/inventory/store/{id}", "Inventory by Store"),
                endpoint("inv-film", "Inventory", "GET", "/api/inventory/film/{id}", "Inventory by Film"),
                endpoint("inv-film-store", "Inventory", "GET", "/api/inventory/film/{filmId}/store/{storeId}", "Inventory by Film & Store"),
                endpoint("inv-add", "Inventory", "POST", "/api/inventory/add", "Add Inventory"),
                endpoint("inv-del", "Inventory", "DELETE", "/api/inventory/{id}", "Delete Inventory")
        ));

        this.endpointsByMember = Map.copyOf(map);
    }

    public List<TeamMember> findAllMembers() {
        return members;
    }

    public Optional<TeamMember> findMemberById(String memberId) {
        return members.stream().filter(member -> member.getId().equals(memberId)).findFirst();
    }

    public List<EndpointDefinition> findEndpointsByMember(String memberId) {
        return endpointsByMember.getOrDefault(memberId, List.of());
    }

    public Optional<EndpointDefinition> findEndpointById(String memberId, String endpointId) {
        return findEndpointsByMember(memberId)
                .stream()
                .filter(endpoint -> endpoint.getId().equals(endpointId))
                .findFirst();
    }

    public List<String> getOwnershipNotes(String memberId) {
        return List.of();
    }

    // UPDATED HELPER METHOD: Accepts the beautiful 'title' parameter
    private EndpointDefinition endpoint(String id, String entity, String method, String path, String title) {
        return new EndpointDefinition(id, entity, method, path, title);
    }
}
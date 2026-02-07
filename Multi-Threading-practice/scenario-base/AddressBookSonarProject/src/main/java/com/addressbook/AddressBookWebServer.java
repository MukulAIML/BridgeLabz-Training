package com.addressbook;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.Executors;

/**
 * Web Server for Address Book Application
 * Provides REST API and Web UI
 */
public class AddressBookWebServer {
    static AddressBook addressBook = new AddressBook();
    private static int PORT = 8080;

    public static void main(String[] args) throws IOException {
        // Add sample data
        addSampleData();

        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

        // API Routes
        server.createContext("/api/contacts", new ContactsHandler());
        server.createContext("/api/contact", new ContactHandler());
        server.createContext("/api/search", new SearchHandler());
        server.createContext("/api/stats", new StatsHandler());

        // Web UI Routes
        server.createContext("/", new StaticHandler());
        server.createContext("/index.html", new StaticHandler());
        server.createContext("/app.js", new StaticHandler());
        server.createContext("/style.css", new StaticHandler());

        server.setExecutor(Executors.newFixedThreadPool(10));
        server.start();

        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║   Address Book Web Server Started Successfully!       ║");
        System.out.println("╠═══════════════════════════════════════════════════════╣");
        System.out.println("║   Web UI:    http://localhost:" + PORT + "                 ║");
        System.out.println("║   API Base:  http://localhost:" + PORT + "/api              ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
    }

    private static void addSampleData() {
        try {
            addressBook.addContact(new Contact("C001", "John Doe", "1234567890", "john@example.com", "123 Main St"));
            addressBook.addContact(new Contact("C002", "Jane Smith", "0987654321", "jane@example.com", "456 Oak Ave"));
            addressBook.addContact(new Contact("C003", "Bob Johnson", "5551234567", "bob@example.com", "789 Pine Rd"));
            addressBook.addContact(new Contact("C004", "Alice Brown", "9998887777", "alice@example.com", "321 Elm St"));
            addressBook.addContact(new Contact("C005", "Charlie Wilson", "4445556666", "charlie@example.com", "654 Maple Dr"));
        } catch (Exception e) {
            // Ignore if sample data already exists
        }
    }

    // Static methods for JSON utilities
    static String readRequestBody(HttpExchange exchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody());
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    static void sendResponse(HttpExchange exchange, String response) throws IOException {
        byte[] bytes = response.getBytes();
        exchange.sendResponseHeaders(200, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }

    static Contact parseContact(String json) {
        Contact contact = new Contact("", "", "", "", "");

        String id = extractJsonValue(json, "contactId");
        String name = extractJsonValue(json, "name");
        String phone = extractJsonValue(json, "phoneNumber");
        String email = extractJsonValue(json, "email");
        String address = extractJsonValue(json, "address");

        contact.setContactId(id);
        contact.setName(name);
        contact.setPhoneNumber(phone);
        contact.setEmail(email);
        contact.setAddress(address);

        return contact;
    }

    static String extractJsonValue(String json, String key) {
        String pattern = "\"" + key + "\":\"";
        int start = json.indexOf(pattern);
        if (start == -1) {
            pattern = "\"" + key + "\":";
            start = json.indexOf(pattern);
            if (start == -1) return "";
            start += pattern.length();
            int end = json.indexOf(",", start);
            if (end == -1) end = json.indexOf("}", start);
            return json.substring(start, end).replace("\"", "").trim();
        }
        start += pattern.length();
        int end = json.indexOf("\"", start);
        if (end == -1) end = json.indexOf(",", start);
        return json.substring(start, end);
    }

    static String toJson(Object obj) {
        if (obj instanceof List) {
            StringBuilder sb = new StringBuilder("[");
            boolean first = true;
            for (Object item : (List<?>) obj) {
                if (!first) sb.append(",");
                sb.append(toJson(item));
                first = false;
            }
            sb.append("]");
            return sb.toString();
        } else if (obj instanceof Contact) {
            Contact c = (Contact) obj;
            return String.format(
                "{\"contactId\":\"%s\",\"name\":\"%s\",\"phoneNumber\":\"%s\",\"email\":\"%s\",\"address\":\"%s\"}",
                c.getContactId(), c.getName(), c.getPhoneNumber(), c.getEmail(), c.getAddress()
            );
        }
        return "{}";
    }
}

// Handler for /api/contacts endpoint
class ContactsHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response = "";

        try {
            if ("GET".equals(method)) {
                response = AddressBookWebServer.toJson(AddressBookWebServer.addressBook.getAllContacts());
                exchange.getResponseHeaders().set("Content-Type", "application/json");
            } else if ("POST".equals(method)) {
                String body = AddressBookWebServer.readRequestBody(exchange);
                Contact contact = AddressBookWebServer.parseContact(body);
                AddressBookWebServer.addressBook.addContact(contact);
                response = "{\"success\":true,\"message\":\"Contact added successfully\"}";
                exchange.getResponseHeaders().set("Content-Type", "application/json");
            } else if ("DELETE".equals(method)) {
                String contactId = exchange.getRequestURI().getQuery();
                if (contactId != null && contactId.startsWith("id=")) {
                    String id = contactId.substring(3);
                    AddressBookWebServer.addressBook.deleteContact(id);
                    response = "{\"success\":true,\"message\":\"Contact deleted successfully\"}";
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                }
            }
        } catch (Exception e) {
            response = "{\"success\":false,\"message\":\"" + e.getMessage() + "\"}";
            exchange.getResponseHeaders().set("Content-Type", "application/json");
        }

        AddressBookWebServer.sendResponse(exchange, response);
    }
}

// Handler for /api/contact endpoint
class ContactHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response = "";

        try {
            if ("GET".equals(method)) {
                String contactId = exchange.getRequestURI().getQuery();
                if (contactId != null && contactId.startsWith("id=")) {
                    String id = contactId.substring(3);
                    Contact contact = AddressBookWebServer.addressBook.searchById(id);
                    response = AddressBookWebServer.toJson(contact);
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                }
            } else if ("PUT".equals(method)) {
                String body = AddressBookWebServer.readRequestBody(exchange);
                Contact contact = AddressBookWebServer.parseContact(body);
                AddressBookWebServer.addressBook.updateContact(contact.getContactId(), contact);
                response = "{\"success\":true,\"message\":\"Contact updated successfully\"}";
                exchange.getResponseHeaders().set("Content-Type", "application/json");
            }
        } catch (Exception e) {
            response = "{\"success\":false,\"message\":\"" + e.getMessage() + "\"}";
            exchange.getResponseHeaders().set("Content-Type", "application/json");
        }

        AddressBookWebServer.sendResponse(exchange, response);
    }
}

// Handler for /api/search endpoint
class SearchHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();

        if ("GET".equals(method)) {
            String query = exchange.getRequestURI().getQuery();
            String response = "";

            if (query != null && query.startsWith("name=")) {
                String name = query.substring(5);
                List<Contact> results = AddressBookWebServer.addressBook.searchByName(name);
                response = AddressBookWebServer.toJson(results);
            } else {
                response = "[]";
            }

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            AddressBookWebServer.sendResponse(exchange, response);
        }
    }
}

// Handler for /api/stats endpoint
class StatsHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        int total = AddressBookWebServer.addressBook.getTotalContacts();
        String response = "{\"totalContacts\":" + total + "}";
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        AddressBookWebServer.sendResponse(exchange, response);
    }
}

// Handler for static files (HTML, CSS, JS)
class StaticHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();

        if (path.equals("/") || path.equals("/index.html")) {
            path = "/index.html";
        }

        // Remove leading slash
        if (path.startsWith("/")) {
            path = path.substring(1);
        }

        // Default to webapp folder
        if (path.isEmpty()) {
            path = "webapp/index.html";
        } else {
            path = "webapp/" + path;
        }

        File file = new File(path);

        if (file.exists()) {
            String contentType = getContentType(path);
            byte[] content = Files.readAllBytes(file.toPath());

            exchange.getResponseHeaders().set("Content-Type", contentType);
            exchange.sendResponseHeaders(200, content.length);
            OutputStream os = exchange.getResponseBody();
            os.write(content);
            os.close();
        } else {
            String response = "File not found: " + path;
            exchange.sendResponseHeaders(404, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    private String getContentType(String path) {
        if (path.endsWith(".html")) return "text/html";
        if (path.endsWith(".css")) return "text/css";
        if (path.endsWith(".js")) return "application/javascript";
        if (path.endsWith(".json")) return "application/json";
        if (path.endsWith(".png")) return "image/png";
        if (path.endsWith(".jpg")) return "image/jpeg";
        if (path.endsWith(".ico")) return "image/x-icon";
        return "text/plain";
    }
}


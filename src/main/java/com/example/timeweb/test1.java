package com.example.timeweb;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "mapServlet", value = "/map-servlet")
public class MapServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><head>");
        out.println("<title>Leaflet Map</title>");
        out.println("<link rel='stylesheet' href='https://unpkg.com/leaflet/dist/leaflet.css' />");
        out.println("<style>#map { height: 400px; }</style>");
        out.println("</head><body>");
        out.println("<div id='map'></div>");
        out.println("<script src='https://unpkg.com/leaflet/dist/leaflet.js'></script>");
        out.println("<script>");
        out.println("var map = L.map('map').setView([51.505, -0.09], 13);");
        out.println("L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {");
        out.println("   attribution: '© OpenStreetMap contributors'");
        out.println("}).addTo(map);");
        out.println("</script>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
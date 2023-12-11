package com.example.timeweb;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    private List<String> locations = new ArrayList<>();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
//        PrintWriter out = response.getWriter();
//        out.println("<html><head>");
//
//        out.println("<title>Leaflet Map</title>");
//        out.println("<link rel='stylesheet' href='https://unpkg.com/leaflet/dist/leaflet.css' />");
//        out.println("<style>#map { height: 400px; }</style>");
//        out.println("</head><body>");
//        out.println("<h1>" + message + "</h1>");
//        out.println("<div id='map'></div>");
//        out.println("<script src='https://unpkg.com/leaflet/dist/leaflet.js'></script>");
//        out.println("<script>");
//        out.println("var map = L.map('map').setView([51.505, -0.09], 13);");
//        out.println("L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {");
//        out.println("   attribution: '© OpenStreetMap contributors'");
//        out.println("}).addTo(map);");
//        out.println("</script>");
//        out.println("</body></html>");

        String htmlResponse = "<html><head>" +
                "<title>Leaflet Map</title>" +
                "<link rel='stylesheet' href='https://unpkg.com/leaflet/dist/leaflet.css' />" +
                "<style>#map { height: 400px; }</style>" +
                "</head><body>" +
                "<div id='map'></div>" +
                "<script src='https://unpkg.com/leaflet/dist/leaflet.js'></script>" +
                "<script>" +
                "var map = L.map('map').setView([51.505, -0.09], 13);" +
                "L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {" +
                "   attribution: '© OpenStreetMap contributors'" +
                "}).addTo(map);" +
                // Thêm sự kiện click vào bản đồ
                "map.on('click', function(e) {" +
                "    var lat = e.latlng.lat;" +
                "    var lon = e.latlng.lng;" +
                "    fetchTime(lat, lon);" +
                "});" +
                "function fetchTime(lat, lon) {" +
                // Sử dụng API của OpenStreetMap Nominatim để lấy thông tin về địa điểm và thời gian
                "    fetch('https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=' + lat + '&lon=' + lon)" +
                "        .then(response => response.json())" +
                "        .then(data => {" +
                "            var displayName = data.display_name;" +
                "            var timestamp = new Date().toLocaleString('en-US', { timeZone: data.timezone });" +
                "            var info = 'Địa điểm: ' + displayName + '<br/> Thời gian tại đây: ' + timestamp;" +
                // Hiển thị thông tin trong popup
                "            L.popup()" +
                "                .setLatLng([lat, lon])" +
                "                .setContent(info)" +
                "                .openOn(map);" +
                "        })" +
                "        .catch(error => console.error('Lỗi:', error));" +
                "}" +
                // Hàm để lưu thông tin vào danh sách locations
                "function saveLocationAndTime(info) {" +
                "    locations.push(info);" +
                "}" +
                "</script>" +
                "</body></html>";

        System.out.println("Danh sách các địa điểm và thời gian:");
        for (String location : locations) {
            System.out.println(location);
        }



        response.getWriter().write(htmlResponse);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void destroy() {
    }
}
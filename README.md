# SwadSprint 🍕⚡

> **SwadSprint** is a modern, full-stack Java JEE food ordering and delivery web application built with Servlets, JSP, JDBC, and MySQL. It offers a seamless food discovery experience, real-time cart quantity updates, secure user profile management, and order history tracking.

---

## 🌟 Key Features

- 🔐 **User Authentication & Security**: Secure user registration and login powered by BCrypt password hashing and session management.
- 🍽️ **Restaurant & Menu Discovery**: Explore curated kitchens, cuisines, ratings, delivery times, and interactive menu items.
- 🛒 **Seamless Real-Time Cart**: Adjust item quantities (`+` / `-` / `delete`) with instant live total calculations (Subtotal, 18% GST, Grand Total) without full-page reloads.
- 📌 **Fixed View Cart Screen Floating Bar**: Screen-viewport fixed floating action bar popping up whenever items exist in your cart.
- 📦 **Order Management & MySQL Persistence**: Checkout workflow persisting orders and items to MySQL database tables using auto-generated primary key linkages.
- 👤 **Profile Management & Order History**: Update user credentials, addresses, and passwords directly to MySQL, with automated order history loading upon login.
- 🎨 **Modern Dual-Color Branding**: Signature **Swad** (Crimson Red `#FF1E27`) and **Sprint** (Fiery Speed Orange `#FF6A00`) brand theme.

---

## 🛠️ Technology Stack

| Component | Technologies Used |
| :--- | :--- |
| **Backend** | Java JEE, Servlets, JSP, JDBC, DAO Design Pattern |
| **Database** | MySQL Server |
| **Security** | BCrypt Password Hashing |
| **Frontend** | HTML5, CSS3 (Vanilla), JavaScript (ES6+), Font Awesome 6 |
| **Web Server** | Apache Tomcat (v10.0+) |
| **IDE / Tools** | Eclipse IDE for Enterprise Java Developers, Git |

---

## 📁 Project Structure

```
firstJEEproject/
├── src/main/java/com/tap/
│   ├── DAO/             # Data Access Object Interfaces (UserDAO, OrderDAO, etc.)
│   ├── DAOImpl/         # MySQL DAO Implementations
│   ├── JEE/             # Servlets (LoginServlet, CartServlet, PlaceOrderServlet, etc.)
│   ├── model/           # Entity Classes (User, Restaurant, Menu, Order, Cart)
│   └── utility/         # Helper Classes (DBConnection, OrderHistoryLoader)
└── src/main/webapp/
    ├── css/             # Custom Style Sheets (premium-system.css, menu.css, index.css)
    ├── js/              # Client-side UI Logic (premium-ui.js)
    ├── images/          # Assets and Food Photography
    ├── index.html       # Landing Page
    ├── restaurants.jsp  # Restaurant Hub Listing
    ├── menu.jsp         # Restaurant Menu & Dynamic Quantity Form
    ├── cart.jsp         # Shopping Cart & Order Summary
    ├── userProfile.jsp  # User Profile Management
    └── orderHistory.jsp # Historical Orders View
```

---

## 🚀 Getting Started

### 1. Prerequisites
- **Java Development Kit (JDK)**: 17 or higher
- **Apache Tomcat**: Version 10.0+
- **MySQL Database Server**: Version 8.0+
- **Eclipse IDE** for Enterprise Java and Web Developers

### 2. Database Setup
Create your MySQL database and update `DBConnection.java` with your MySQL credentials:
```java
String url = "jdbc:mysql://localhost:3306/foodapp";
String username = "YOUR_MYSQL_USERNAME";
String password = "YOUR_MYSQL_PASSWORD";
```

### 3. Build & Run
1. Clone or import `firstJEEproject` into Eclipse IDE.
2. Configure **Apache Tomcat** in Eclipse Server runtime.
3. Right-click project -> **Run As** -> **Run on Server**.
4. Open your browser and navigate to:
   ```
   http://localhost:8080/firstJEEproject/
   ```

---

## 📄 License & Credits

© 2026 **SwadSprint Inc.** All rights reserved. Designed and developed with ❤️ for food lovers.

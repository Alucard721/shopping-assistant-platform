# shopping-assistant-platform

A shopping assistant platform that collects product data from major e-commerce platforms to provide price comparisons and product recommendations.

## Project Background

### Background:

With the rapid advancement of internet technologies and the acceleration of global trade, e-commerce has quickly become a vital part of the global economy. Attracted by its convenience, a growing number of consumers are turning to online shopping—especially with the rise of mobile payments and social media marketing.

Major platforms such as JD.com, Taobao have emerged, bringing with them a surge of merchants. While this has offered consumers greater variety and convenience, it has also introduced two major challenges:

1. Consumers often find it difficult to make decisions due to the need to switch between multiple platforms. Even for the same product, price and quality can vary significantly across different sites, making the process time-consuming and inefficient—undermining the core value of online shopping.

2. The lack of standardized evaluation systems and the prevalence of fake reviews make it hard for consumers to assess product authenticity and quality. This leads to poor purchase decisions and dissatisfaction, as low-quality or counterfeit products may be bought based on misleading information.

### Objective:

Addressing issues mentioned in the background by building a shopping assistant platform that collects product data across platforms, enables precise price comparison, and provides reliable product recommendations to support better consumer decision-making.

## How It's Made:

Tech used: Spring Boot, Selenium, Lucene, Vue, Electron.

### Server

> 📂 See `shopping-assistant` folder for implementation details.

The server side is built with **Spring Boot** and is composed of the following five modules:

---

#### 1. `crawler` — Web Crawling Module

- Utilizes the **Selenium** framework to scrape product data from major e-commerce websites.
- Stores the collected data into a **MySQL** database for later retrieval and analysis.

---

#### 2. `interact` — Data Interaction Module

This module provides the user-facing logic for:

- Searching products to compare
- Viewing product details, scores, and other key attributes
- Comparing products based on price, sales volume, and other metrics

---

#### 3. `search` — Full-text Search Module

- Uses the **Lucene** search engine to build inverted indexes from the MySQL database.
- Supports **tokenized keyword search** for efficient and accurate query results.

---

#### 4. `recommendation` — Recommendation Engine

- Analyzes product data and assigns scores based on various quality metrics.
- Highlights and recommends high-quality products to users.

---

#### 5. `management` — Admin Panel Module

An internal tool for administrators that enables:

- Product data management
- Score and recommendation model configuration

### Client

> 📂 See `vue-store-master` folder for implementation details.

The client is built using the **Vue.js** framework. It communicates with the server-side API using **Axios** to fetch product data, which is then rendered dynamically on the web interface.

Finally, the project is packaged using **electron-builder**, allowing it to run as a cross-platform desktop application.
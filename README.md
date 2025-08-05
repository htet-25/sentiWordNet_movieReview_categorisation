# Movie Review Sentiment Categorisation using SentiWordNet

This Java web application categorizes movie reviews into **positive**, **negative**, or **neutral** sentiment using **SentiWordNet**. The frontend is built with **JSF and PrimeFaces**, and the backend uses **Java** and **MySQL** for data storage and processing.

## 🎯 Project Objective

To implement a basic NLP-powered system that can analyze the sentiment of movie reviews by leveraging the SentiWordNet lexical resource.

## 🛠️ Tech Stack

- **Java 8+**
- **JSF (JavaServer Faces)** – Web UI framework
- **PrimeFaces** – Rich UI components for JSF
- **MySQL** – Database for storing reviews and metadata
- **JDBC** – For database access
- **Apache Tomcat** – Suggested server for deployment

## 📁 Project Structure
sentiWordNet_movieReview_categorisation/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/htetnaing/sentiment/
│       │       ├── ReviewBean.java
│       │       ├── SentiWordNetDemoCode.java
│       │       └── DBUtil.java
│       └── webapp/
│           ├── index.xhtml
│           ├── result.xhtml
│           └── WEB-INF/
│               └── web.xml
└── README.md

## 🚀 How It Works

1. User submits a movie review via the web UI.
2. The text is analyzed using **SentiWordNet**, which assigns sentiment scores to words.
3. Scores are aggregated to classify the review as:
   - 👍 Positive
   - 👎 Negative
   - ⚖️ Neutral
4. Results are stored and optionally displayed.

## 📊 Database Schema

You need to create a MySQL database (e.g., `movie_reviews_db`) with at least one table to store review text and classification result.

Example:

```sql
CREATE TABLE reviews (
    id INT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    sentiment VARCHAR(10) NOT NULL
);

Prerequisites:
	•	JDK 8 or higher
	•	MySQL running locally or on a server
	•	Apache Tomcat (or another servlet container)
	•	IDE (Eclipse / IntelliJ) with JSF/Servlet support

Steps:
	1.	Clone the project: git clone https://github.com/htet-25/sentiWordNet_movieReview_categorisation.git
  2.	Import into your IDE as a Dynamic Web Project (Eclipse) or Maven project.
	3.	Set up the database and update DBUtil.java with your connection details.
	4.	Deploy the app to Tomcat.
	5.	Access the app at:
http://localhost:8080/sentiWordNet_movieReview_categorisation

💡 Key Files
	•	SentiWordNetDemoCode.java: Implements the SentiWordNet-based sentiment scoring logic.
	•	ReviewBean.java: JSF managed bean to handle user input and interaction.
	•	index.xhtml: User input form.
	•	result.xhtml: Displays the sentiment result.

📌 Notes
	•	Currently uses a basic approach; could be enhanced with:
	•	Preprocessing (stopwords, stemming)
	•	Machine learning models (e.g., Naive Bayes, SVM)
	•	RESTful API endpoints
	•	Pagination, history, or charts with PrimeFaces

👤 Author

Htet Aung Naing
📍 Toronto, Canada
📧 hanmaple92@gmail.com
🔗 GitHub



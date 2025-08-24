
# Real-Time Data Pipeline with Kafka, Snowflake, and Grafana

This project demonstrates a **real-time data engineering pipeline** that ingests data from a simulated API, streams it through **Kafka**, stores and processes it in **Snowflake**, and visualizes insights using **Grafana**.  

---

## 🚀 Architecture Overview  

![Pipeline Architecture](./Streaming/pipeline-diagram.png)

1. **Simulated API**  
   - Generates real-time streaming data.  
   - Built using **Python** and deployed on **AWS EC2**.  
   - Stores backup data in **Amazon S3**.  

2. **Apache Kafka**  
   - Acts as the **message broker** for real-time data ingestion.  
   - Components:  
     - **Producer** → Publishes events from API to Kafka topics.  
     - **Broker** → Handles distributed streaming.  
     - **Consumer** → Reads and forwards data to Snowflake.  

3. **Snowflake**  
   - Stores **raw data** for archival purposes.  
   - Performs **data processing** and **transformations**.  
   - Stores **processed data** for visualization.  

4. **Grafana**  
   - Connects with Snowflake for **near real-time dashboards**.  
   - Provides monitoring and visualization of processed data.  

---

## ⚙️ Tech Stack  

- **Cloud**: AWS (EC2, S3)  
- **Streaming**: Apache Kafka  
- **Data Warehouse**: Snowflake  
- **Visualization**: Grafana  
- **Languages/Tools**: Python, SQL  

---

## 📂 Project Structure  

```bash
.
├── api/                 # Python scripts for simulating API data
├── kafka/               # Kafka producer & consumer setup
├── snowflake/           # SQL scripts for raw & processed tables
├── grafana/             # Dashboard configuration
├── images/              # Architecture diagrams & visuals
└── README.md            # Project documentation

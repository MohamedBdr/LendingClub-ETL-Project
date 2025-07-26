# LendingClub Credit Risk Analysis (Data Engineering Project)

## 🧠 Overview
This project focuses on analyzing LendingClub loan data to assess credit risk and build a data engineering pipeline using modern tools like PySpark, Apache Airflow, and PostgreSQL. 

## Objective
The goal is to extract insights about borrower behavior, payment history, and investor activity — and to prepare the data for downstream analytics and reporting.

## 🛠️ Tools Used
- PySpark
- Apache Airflow
- PostgreSQL
- Power BI
- Python

## 🧱 Project Structure
1. **Data Source**: [LendingClub loan dataset](https://www.kaggle.com/datasets/beatafaron/loan-credit-risk-and-population-stability) from Kaggle.
📚 You can read more about Lending Club [here](https://en.wikipedia.org/wiki/LendingClub).
2. **Exploration**: PySpark exploration and missing values analysis.
3. **Cleaning & Transformation**:
    - Dropped high null columns: `emp_title`, `title`, `revol_util` ...
    - Cleaned percentages and dates.
    - Casted monetary columns to float with 2 decimal places.
4. **Star Schema Modeling**:
    - Built dimension tables: `dim_customer`, `dim_investor`, `dim_time`, etc.
    - Built fact table: `fact_loans`.
5. **Data Load to DWH**:
    - PostgreSQL used for loading modeled data.
6. **Dashboard**:
    - Power BI dashboard to show loan performance, investor returns, and risk segmentation.

## 📊 Sample Dashboard
*(add screenshots here)*

## 📝 Project Status
✅ Data Cleaning  
✅ DWH Modeling  
⏳ Airflow DAGs  
✅ Power BI Dashboard


## 🛡️ License
This project is licensed under the MIT License. You are free to use, modify, and share this project with proper attribution.

## 🌟 About Me
Hi there! I'm Mohamed. A Data Engineer trainee at ITI with hands-on experience in data pipelines, warehousing, and big data tools. Passionate about turning data into insights and growing my technical skills.



Let's stay in touch! Feel free to connect with me on the following platforms:  
[![Gmail](https://img.shields.io/badge/Gmail-D14836?style=flat&logo=gmail&logoColor=white)](mailto:mo.badr.ismail@gmail.com)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=flat&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/mbi162/)

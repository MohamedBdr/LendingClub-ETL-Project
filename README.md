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

## 🔗 How to Run
```bash
# Install dependencies
pip install -r requirements.txt

# Run transformations
python scripts/transform_loans.py

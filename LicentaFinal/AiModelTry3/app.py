from flask import Flask, request, jsonify
import pandas as pd
import numpy as np
import joblib
import pickle

app = Flask(__name__)

# Load the trained model, scaler, encoder, and columns
rf_model = joblib.load('RandomForest_model.joblib')
# rf_model = pickle.load(open('RandomForest_model.pkl', 'rb'))

scaler = joblib.load('scaler.joblib')
encoder = joblib.load('encoder.joblib')
train_columns = joblib.load('train_columns.joblib')

def preprocess_input(input_df):
    # Preprocess the specific car instance
    input_df.columns = input_df.columns.str.lower().str.replace(' ', '_').str.replace('-', '_')
    input_df['leather_interior'] = input_df['leather_interior'].map({'Yes': 1, 'No': 0})
    input_df['mileage'] = input_df['mileage'].str.replace(' km', '').astype(int)

    input_df['engine_volume'] = input_df['engine_volume'].astype(str)
    # input_df['is_turbo'] = input_df['engine_volume'].apply(lambda x: 1 if 'Turbo' in x else 0)
    input_df['engine_volume'] = input_df['engine_volume'].str.replace(' Turbo', '').astype(float)

    # Apply logarithmic transformation to the car instance
    input_df['prod._year'] = np.log(input_df['prod._year'] + 1)
    input_df['mileage'] = np.log(input_df['mileage'] + 1)

    # Temporarily add the 'price' column to align with the encoder's expected input dimensions
    input_df['price'] = 0

    # Apply LeaveOneOut encoding to categorical columns using the same encoder as the training data
    categorical_columns = ['manufacturer', 'model', 'category', 'fuel_type', 'gear_box_type', 'drive_wheels', 'doors', 'wheel', 'color']
    input_df_encoded = encoder.transform(input_df)

    # Drop the temporary 'price' column
    input_df_encoded = input_df_encoded.drop(columns=['price'])

    # Ensure all columns match the training data
    input_df_encoded = input_df_encoded.reindex(columns=train_columns, fill_value=0)

    # Scale the features using the same scaler as the training data
    input_df_scaled = scaler.transform(input_df_encoded)

    return input_df_scaled

@app.after_request
def after_request(response):
    response.headers.add('Access-Control-Allow-Origin', '*')
    response.headers.add('Access-Control-Allow-Headers', 'Content-Type,Authorization')
    response.headers.add('Access-Control-Allow-Methods', 'GET,POST,OPTIONS')
    return response

@app.route('/predict', methods=['POST'])
def predict():
    data = request.json or {}
    input_df = pd.DataFrame([data])
    print(input_df)
    processed_input = preprocess_input(input_df)
    predicted_price = rf_model.predict(processed_input)
    predicted_value = int(predicted_price[0])
    print(predicted_value)
    return jsonify(predicted_value)

@app.route('/predict', methods=[ 'OPTIONS'])
def handle_browser():
    return '', 200

if __name__ == '__main__':
    app.run()

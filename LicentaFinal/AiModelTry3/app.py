from flask import Flask, request, jsonify
import pandas as pd
import joblib
pd.set_option('display.width', 1000)

app = Flask(__name__)

# Load the trained model, scaler, encoder, and columns
rf_model = joblib.load('RandomForestRegressor.joblib')
encoder = joblib.load('encoder.joblib')

def preprocess_input(input_df):
    # Add 'price' column as required by the encoder
    input_df['price'] = 0

    # Encode the categorical features
    input_df_encoded = encoder.transform(input_df)

    # Remove the 'price' column after encoding
    input_df_encoded = input_df_encoded.drop(columns=['price'])

    return input_df_encoded


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
    processed_input = preprocess_input(input_df)
    predicted_price = rf_model.predict(processed_input)
    predicted_value = int(predicted_price[0])
    return jsonify(predicted_value)

if __name__ == '__main__':
    app.run()

@app.route('/predict', methods=[ 'OPTIONS'])
def handle_browser():
    return '', 200

@app.route('/health', methods=[ 'GET'])
def health_check():
    return '', 200

